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
    // @JoinColumn creates a foreign key column in the job table to link back to employer
    @OneToMany
    @JoinColumn(name = "employer_id")    // This specifies the foreign key column
    private List<Job> jobs = new ArrayList<>();

    @NotNull // Ensures field is not null, throws validation error, if so
    @Size(min=3, max=75) // Ensure fields has a min/max character length
    private String location;

    public Employer() {}

    // Getter & Setter
    public String getLocation() { return location; }

    public void setLocation(String location) { this.location = location; }

    // Task 3: Added a getter to access array list of jobs
    public List<Job> getJobs() { return jobs; }
}