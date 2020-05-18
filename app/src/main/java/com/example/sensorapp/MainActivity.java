package com.example.sensorapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.widget.TextView;

import com.example.sensorapp.database.ApplicationDatabase;
import com.example.sensorapp.database.dao.Datapoint;

import java.util.List;


public class MainActivity extends AppCompatActivity {

    private ApplicationDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

    }

    public ApplicationDatabase getDatabase(){
        return this.database;
    }

}
