// Job extends from AbstractEntity
package org.launchcode.techjobs.persistent.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by LaunchCode
 * Manages job creation/viewing
 */

@Entity // Maps to a database (fields become columns)
public class Job extends AbstractEntity {

    // Task 3: Replace the employer field to be of type Employer instead of String
    @ManyToOne // jobs/employer
    private Employer employer;

    // Task 4: change return type to List<> named skills
    @ManyToMany
    private List<Skill> skills = new ArrayList<>();

    public Job() {}

    // Task 3: Update constructor to take Employer object instead of String
    public Job(Employer anEmployer, List<Skill> someSkills) {
        super(); // invokes no-args constructor of super-class
        this.employer = anEmployer;
        this.skills = someSkills;
    }

    // Task 3: Employer Getter and Setter
    public Employer getEmployer() { return employer; }

    public void setEmployer(Employer employer) { this.employer = employer; }

    // Task 4: Skills Getter and Setter
    public List<Skill> getSkills() { return skills; }

    public void setSkills(List<Skill> skills) { this.skills = skills; }
}