package com.example.sensorapp.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.sensorapp.database.dao.Datapoint;
import com.example.sensorapp.database.repositoy.IDatapointRepository;

@Database(entities = {Datapoint.class}, version = 1)
public abstract class ApplicationDatabase extends RoomDatabase {

    public abstract IDatapointRepository getDatapointTable();

}
