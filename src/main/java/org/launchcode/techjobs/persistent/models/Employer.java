// Employer extends from AbstractEntity
package org.launchcode.techjobs.persistent.models;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Employer extends AbstractEntity {

    // Task 3: Add a jobs field to establish the one-to-many relationship
    // @OneToMany indicates one employer can have many jobs
    // @JoinColumn creates a column in the job table to store the employer ID
    @OneToMany
    @JoinColumn(name = "employer_id")    // This specifies the foreign key column
    private List<Job> jobs = new ArrayList<>();

    @NotNull
    @Size(min=3, max=75)
    private String location;

    public Employer() {}

    public String getLocation() { return location; }

    public void setLocation(String location) { this.location = location; }

    // Task 3: Added a getter to access array list of jobs
    public List<Job> getJobs() { return jobs; }
}