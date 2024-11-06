package org.launchcode.techjobs.persistent.models;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

// Employer inherits properties from AbstractEntity
@Entity
public class Employer extends AbstractEntity {

    @NotNull
    @Size(min=3, max=75)
    private String location;

    public Employer() {}

    // returns type "string"
    public String getLocation() {
        return location;
    }

    // doesn't return anything, only sets. Use void...
    public void setLocation(String location) {
        this.location = location;
    }
}