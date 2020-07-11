package com.example.sensorapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.room.Room;

import com.example.sensorapp.database.ApplicationDatabase;
import com.example.sensorapp.database.dao.Datapoint;
import com.example.sensorapp.model.MainModel;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;
import java.util.List;

// 1 Sensordatenauslesung Stephan Jarisch st19m006
// 2 Live-Monitoring Clemens Buchegger st19m019
// 3 Datenbank/Layout Felix Edel st19m023

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private ApplicationDatabase database; //3

    private SensorManager sensorManager; //1

    private Sensor acceleration; //1

    private LineChart mChart; //2

    @Override
    // Screen on -> Beginn von onCreate
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // 2. Erstellung des Diagramms
        mChart = (LineChart) findViewById(R.id.chart1);
        mChart.setVisibleXRangeMaximum(1000);
        mChart.setData(new LineData());
        mChart.setBackgroundColor(Color.WHITE);

        // 3. Erstellung der Datenbank
        database = Room.databaseBuilder(this, ApplicationDatabase.class, "app_db_4")
                .allowMainThreadQueries()
                .build();

        // 1. Erstellung von sensor und sensormanager - Objekterstellung
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        acceleration = sensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION);

        // 2.Erstellung des Observers // Livecharts - Anlegen des Screens - Depency: Github build.gradle

        MainModel model = new ViewModelProvider(this).get(MainModel.class);
        final Observer<List<Datapoint>> dataObserver = datapointList -> {
            // 2. 3 mal für X,Y,Z
            LineDataSet ldsx = new LineDataSet(getXEntryData(datapointList), "X_Val");
            ldsx.setColor(Color.GREEN);
            ldsx.setDrawCircles(false);
            LineDataSet ldsy = new LineDataSet(getYEntryData(datapointList), "Y_Val");
            ldsy.setColor(Color.BLUE);
            ldsy.setDrawCircles(false);
            LineDataSet ldsz = new LineDataSet(getZEntryData(datapointList), "Z_Val");
            ldsz.setColor(Color.GRAY);
            ldsz.setDrawCircles(false);
            LineData data = new LineData(ldsx, ldsy, ldsz);
            data.notifyDataChanged();
            mChart.setData(data);
            mChart.notifyDataSetChanged();
            mChart.moveViewToX(data.getEntryCount());
        };
        // 2. Implementierung der Buttons

        // 2. Start-Knopf
        Button buttonstart = (Button) findViewById(R.id.button1);
        // Starten des Data-Observer
        buttonstart.setOnClickListener(v -> model.getData(database).observe(this,  dataObserver));

        // 2. Stop-Knopf
        Button buttonstop = (Button) findViewById(R.id.button2);
        buttonstop.setOnClickListener(v -> {
            Intent myIntent = new Intent(v.getContext(), MainActivity.class);
            // Restarten des Screens
            startActivityForResult(myIntent, 0);
        });

    }

    // 1. Implementierung des Interfaces SensorEventlistener


    @Override
    public void onSensorChanged(SensorEvent event) {

        //Auswahl des lineraren Beschleunigungssensors
        if(event.sensor.getType() == Sensor.TYPE_LINEAR_ACCELERATION){
            Log.d("SENSOR", "Daten empfangen");
            final Datapoint datapoint = new Datapoint(event.values[0], event.values[1],
                    event.values[2], "AccelearationSensor", event.timestamp);
            database.getDatapointTable().insert(datapoint);
        }
    }

    // 1. Implementierung des Interfaces SensorEventlistener

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    // 3. Überschreiben der geerbten Methoden aus: AppCompatActivity
    @Override
    protected void onStart() {
        super.onStart();
        if (acceleration != null) {
            sensorManager.registerListener(this, acceleration, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    // 3. Überschreiben der geerbten Methoden aus: AppCompatActivity
    @Override
    protected void onStop() {
        super.onStop();
        sensorManager.unregisterListener(this);
    }

    // 3 Methoden zur Dateneintragung - aus Datapoint wird ArrayList
    private List<Entry> getXEntryData(final List<Datapoint> datapoints){
        int x = 0;
        List<Entry> result = new ArrayList<>();
        for(Datapoint d : datapoints){
            result.add(new Entry(x, d.getX_val()));
            x++;
        }
        return result;
    }

    private List<Entry> getYEntryData(final List<Datapoint> datapoints){
        int x = 0;
        List<Entry> result = new ArrayList<>();
        for(Datapoint d : datapoints){
            result.add(new Entry(x, d.getY_val()));
            x++;
        }
        return result;
    }

    private List<Entry> getZEntryData(final List<Datapoint> datapoints){
        int x = 0;
        List<Entry> result = new ArrayList<>();
        for(Datapoint d : datapoints){
            result.add(new Entry(x, d.getZ_val()));
            x++;
        }
        return result;
    }

}
