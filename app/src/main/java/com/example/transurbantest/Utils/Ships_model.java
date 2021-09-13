package com.example.transurbantest.Utils;

import java.io.Serializable;

public class Ships_model implements Serializable {

    String name, model, manufacturer, cost_in_credits, length, max_atmosphering_speed, crew, passengers, cargo_capacity, consumables, hyperdrive_rating, MGLT, starship_class;

    public Ships_model(String name, String model, String manufacturer, String cost_in_credits, String length, String max_atmosphering_speed, String crew, String passengers, String cargo_capacity, String consumables, String hyperdrive_rating, String MGLT, String starship_class) {
        this.name = name;
        this.model = model;
        this.manufacturer = manufacturer;
        this.cost_in_credits = cost_in_credits;
        this.length = length;
        this.max_atmosphering_speed = max_atmosphering_speed;
        this.crew = crew;
        this.passengers = passengers;
        this.cargo_capacity = cargo_capacity;
        this.consumables = consumables;
        this.hyperdrive_rating = hyperdrive_rating;
        this.MGLT = MGLT;
        this.starship_class = starship_class;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getCost_in_credits() {
        return cost_in_credits;
    }

    public void setCost_in_credits(String cost_in_credits) {
        this.cost_in_credits = cost_in_credits;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getMax_atmosphering_speed() {
        return max_atmosphering_speed;
    }

    public void setMax_atmosphering_speed(String max_atmosphering_speed) {
        this.max_atmosphering_speed = max_atmosphering_speed;
    }

    public String getCrew() {
        return crew;
    }

    public void setCrew(String crew) {
        this.crew = crew;
    }

    public String getPassengers() {
        return passengers;
    }

    public void setPassengers(String passengers) {
        this.passengers = passengers;
    }

    public String getCargo_capacity() {
        return cargo_capacity;
    }

    public void setCargo_capacity(String cargo_capacity) {
        this.cargo_capacity = cargo_capacity;
    }

    public String getConsumables() {
        return consumables;
    }

    public void setConsumables(String consumables) {
        this.consumables = consumables;
    }

    public String getHyperdrive_rating() {
        return hyperdrive_rating;
    }

    public void setHyperdrive_rating(String hyperdrive_rating) {
        this.hyperdrive_rating = hyperdrive_rating;
    }

    public String getMGLT() {
        return MGLT;
    }

    public void setMGLT(String MGLT) {
        this.MGLT = MGLT;
    }

    public String getStarship_class() {
        return starship_class;
    }

    public void setStarship_class(String starship_class) {
        this.starship_class = starship_class;
    }
}
