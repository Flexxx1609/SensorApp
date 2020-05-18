package com.example.sensorapp.database.repositoy;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.sensorapp.database.dao.Datapoint;

import java.util.List;

@Dao
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
    public List<Datapoint> getItems();

    @Query("SELECT * FROM datapoint WHERE origin = :origin")
    public List<Datapoint> findByOrigin(String origin);

    @Query("SELECT * FROM datapoint WHERE timestamp = :timestamp")
    public Datapoint findByTimestamp(long timestamp);

    @Query("SELECT * FROM datapoint WHERE timestamp >= :start AND timestamp <= :stop")
    public List<Datapoint> findByTimerange(long start, long stop);

}
