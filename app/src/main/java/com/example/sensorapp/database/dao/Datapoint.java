package com.example.sensorapp.database.dao;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.time.Instant;
import java.util.UUID;

@Entity(tableName = "datapoint")
public class Datapoint {

    @PrimaryKey
    @NonNull
    private String datapoint_id;

    private float x_val;

    private float y_val;

    private float z_val;

    private long timestamp;

    private String origin;

    public Datapoint(@NonNull String datapoint_id, float x_val, float y_val, float z_val, String origin, long timestamp) {
        this.datapoint_id = datapoint_id;
        this.x_val = x_val;
        this.y_val = y_val;
        this.z_val = z_val;
        this.origin = origin;
        this.timestamp = timestamp;
    }

    @Ignore
    public Datapoint(float x_val, float y_val, float z_val, String origin, long timestamp){
        this.datapoint_id = UUID.randomUUID().toString();
        this.x_val = x_val;
        this.y_val = y_val;
        this.z_val = z_val;
        this.origin = origin;
        this.timestamp = timestamp;
    }

    @Ignore
    public Datapoint(float x_val, float y_val, float z_val, String origin){
        this.datapoint_id = UUID.randomUUID().toString();
        this.x_val = x_val;
        this.y_val = y_val;
        this.z_val = z_val;
        this.origin = origin;
        this.timestamp = Instant.now().toEpochMilli();
    }

    @NonNull
    public String getDatapoint_id() {
        return datapoint_id;
    }

    public void setDatapoint_id(@NonNull String datapoint_id) {
        this.datapoint_id = datapoint_id;
    }

    public float getX_val() {
        return x_val;
    }

    public void setX_val(float x_val) {
        this.x_val = x_val;
    }

    public float getY_val() {
        return y_val;
    }

    public void setY_val(float y_val) {
        this.y_val = y_val;
    }

    public float getZ_val() {
        return z_val;
    }

    public void setZ_val(float z_val) {
        this.z_val = z_val;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

}
