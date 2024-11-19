// Template for (job,employer,skill), Forces certain methods to be implemented
package org.launchcode.techjobs.persistent.models;


import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Objects;

/**
 * Created by LaunchCode
 *  base/parent class (job,skill,emplyer)
 *  Msuper: shares common fields,
 */

@MappedSuperclass // Indicates a base class (mapped to database columns in subclasses, but is not a database table itself)
public abstract class AbstractEntity {

    @Id // Marks Primary Key, needed to uniquely identify instances in db.
    @GeneratedValue // Auto-generates ID Values
    private int id;

    @NotNull //Ensures field cannot be null; throws a validation error.
    @Size(min=3, max=100, message="Name must be between 3 and 100 characters long.") // Ensures field has a size (length) between 3 and 100 characters.
    private String name;

    // Getters & Setter
    public int getId() { return id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    @Override
    public String toString() { return name; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractEntity that = (AbstractEntity) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
