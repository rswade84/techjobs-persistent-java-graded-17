// Subclass Template, Forces certain methods to be implemented
// Common fields (id, name)

package org.launchcode.techjobs.persistent.models;


import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Objects;

@MappedSuperclass // this is a base class for entities
public abstract class AbstractEntity {

    @Id // Primary Key
    @GeneratedValue // Auto-generates ID Values
    private int id;

    // NotNull = Only checks if value is not null
    // NotBlank = Checks value is not null AND not empty AND not just whitespace
    @NotNull
    @Size(min=3, max=100)
    private String name;

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
