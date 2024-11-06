package org.launchcode.techjobs.persistent.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

@Entity
public class Job extends AbstractEntity {

    // Task 3: Replace the employer field to be of type Employer instead of String
    // @ManyToOne indicates many jobs can reference one employer
    @ManyToOne
    private Employer employer;

    private String skills;

    public Job() {}

    // Task 3: Update constructor to take Employer object instead of String
    public Job(Employer anEmployer, String someSkills) {
        super();
        this.employer = anEmployer;
        this.skills = someSkills;
    }

    // Task 3: Update getter to return an Employer object
    public Employer getEmployer() { return employer; }

    // Task 3: Update setter to accept an Employer object
    public void setEmployer(Employer employer) { this.employer = employer; }

    public String getSkills() { return skills; }

    public void setSkills(String skills) { this.skills = skills; }
}