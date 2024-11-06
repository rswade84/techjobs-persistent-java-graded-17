package org.launchcode.techjobs.persistent.models;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity  // This is crucial - makes it a managed type
public class Skill extends AbstractEntity {

    @NotNull
    @Size(max=500)
    private String description;

    public Skill() {}  // Here is the required empty constructor

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}