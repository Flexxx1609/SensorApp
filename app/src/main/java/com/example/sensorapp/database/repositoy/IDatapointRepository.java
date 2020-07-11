package com.example.sensorapp.database.repositoy;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.sensorapp.database.dao.Datapoint;

import java.util.List;

@Dao

// Datenbankzugriff - Android Room

public interface IDatapointRepository {

    @Insert
    public void insert(Datapoint datapoint);

    @Update
    public void update(Datapoint datapoint);

    @Delete
    public void delete(Datapoint datapoint);

    @Query("SELECT * FROM datapoint WHERE datapoint_id = :datapoint_id")
    public Datapoint findById(String datapoint_id);

    @Query("SELECT * FROM datapoint")
    public LiveData<List<Datapoint>> getItems();

    // 3. Beschränkung der eingelesenen Daten auf 1000 Stück mit dem aktuellsten Punkt als letztes ("aktuell")
    @Query("SELECT * FROM datapoint order by timestamp desc LIMIT 0, 1000")
    public LiveData<List<Datapoint>> getLastItems();

    @Query("SELECT * FROM datapoint WHERE origin = :origin")
    public List<Datapoint> findByOrigin(String origin);

    @Query("SELECT * FROM datapoint WHERE timestamp = :timestamp")
    public Datapoint findByTimestamp(long timestamp);

    @Query("SELECT * FROM datapoint WHERE timestamp >= :start AND timestamp <= :stop")
    public LiveData<List<Datapoint>> findByTimerange(long start, long stop);

}
