package com.example.sensorapp.model;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.sensorapp.database.ApplicationDatabase;
import com.example.sensorapp.database.dao.Datapoint;

import java.util.List;

public class MainModel extends ViewModel {

    public LiveData<List<Datapoint>> getData(ApplicationDatabase database) {
        return database.getDatapointTable().getLastItems();
    }

}
