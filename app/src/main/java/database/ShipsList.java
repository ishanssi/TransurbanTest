package database;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "Shipsdata_TB")
public class ShipsList implements Serializable {


    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "ship_name")
    private String name;

    @ColumnInfo(name = "model")
    private String model;

    @ColumnInfo(name = "Manufacturer")
    private String Manufacturer;

    @ColumnInfo(name = "Cost_in")
    private String constin;

    @ColumnInfo(name = "Length")
    private String Length;

    @ColumnInfo(name = "Max_atmosphering")
    private String Max_atmosphering;

    @ColumnInfo(name = "Crew")
    private String Crew;

    @ColumnInfo(name = "Passengers")
    private String Passengers;

    @ColumnInfo(name = "Cargoo_Capacity")
    private String Cargoo_Capacity;

    @ColumnInfo(name = "Conumables")
    private String Conumables;

    @ColumnInfo(name = "Hyperdrive_rating")
    private String Hyperdrive_rating;

    @ColumnInfo(name = "MGLT")
    private String MGLT;

    @ColumnInfo(name = "Starshp_Class")
    private String Starshp_Class;

    @NonNull
    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getManufacturer() {
        return Manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        Manufacturer = manufacturer;
    }

    public String getConstin() {
        return constin;
    }

    public void setConstin(String constin) {
        this.constin = constin;
    }

    public String getLength() {
        return Length;
    }

    public void setLength(String length) {
        Length = length;
    }

    public String getMax_atmosphering() {
        return Max_atmosphering;
    }

    public void setMax_atmosphering(String max_atmosphering) {
        Max_atmosphering = max_atmosphering;
    }

    public String getCrew() {
        return Crew;
    }

    public void setCrew(String crew) {
        Crew = crew;
    }

    public String getPassengers() {
        return Passengers;
    }

    public void setPassengers(String passengers) {
        Passengers = passengers;
    }

    public String getCargoo_Capacity() {
        return Cargoo_Capacity;
    }

    public void setCargoo_Capacity(String cargoo_Capacity) {
        Cargoo_Capacity = cargoo_Capacity;
    }

    public String getConumables() {
        return Conumables;
    }

    public void setConumables(String conumables) {
        Conumables = conumables;
    }

    public String getHyperdrive_rating() {
        return Hyperdrive_rating;
    }

    public void setHyperdrive_rating(String hyperdrive_rating) {
        Hyperdrive_rating = hyperdrive_rating;
    }

    public String getMGLT() {
        return MGLT;
    }

    public void setMGLT(String MGLT) {
        this.MGLT = MGLT;
    }

    public String getStarshp_Class() {
        return Starshp_Class;
    }

    public void setStarshp_Class(String starshp_Class) {
        Starshp_Class = starshp_Class;
    }

//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name)
//
//    {
//        this.name = name;
//    }
}
