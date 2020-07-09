package com.example.sensorapp;

// Import generiert sich meist selbst (ALT + Enter) ... trotzdem checkern:

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

import com.example.sensorapp.database.ApplicationDatabase;
import com.example.sensorapp.database.dao.Datapoint;


import java.util.List;

//MAINACTIVITY - FÜR ALLE:

//1 Sensordatenauslesung Stephan Jarisch
//2 Live-Monitoring Clemens Buchenegger
//3 Datenbank Felix Edel

//----------------------------------------------------------------------------------------------

public class MainActivity extends AppCompatActivity {

    // Konstruktoren hier anlegen:

    private ApplicationDatabase database; //3

    private SensorManager sensorManager; //1

    private Sensor acceleration; //1

    private LineChart mChart; //2 ????

//-----------------------------------------------------------------------------------------------

    @Override

    // Screen on im Emulator -> Beginn von onCreate. Funktionen hier starten.

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 2. Erstellung des Diagramms
        mChart = (LineChart) findViewById(R.id.chart1);



        database = Room.databaseBuilder(this, ApplicationDatabase.class, "app_db_2")
                .allowMainThreadQueries()
                .build();

        final Datapoint d1 = new Datapoint(1.0, 0.0, 0.0, "BS_Sensor2");
        final Datapoint d2 = new Datapoint(2.0, 0.0, 0.0, "BS_Sensor2");
        final Datapoint d3 = new Datapoint(3.0, 0.0, 0.0, "BS_Sensor2");

        database.getDatapointTable();
        database.getDatapointTable().insert(d1);
        database.getDatapointTable().insert(d2);
        database.getDatapointTable().insert(d3);

        List<Datapoint> pointList = database.getDatapointTable().getItems();
        List<Datapoint> pointListSpec = database.getDatapointTable().findByOrigin("BS_Sensor2");

        TextView textView = findViewById(R.id.textView1);

        textView.setText(pointList.size());

        int x = 5;

    }

    public ApplicationDatabase getDatabase(){
        return this.database;
    }

}
