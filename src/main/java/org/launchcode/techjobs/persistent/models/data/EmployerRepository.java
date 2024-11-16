// Crud <Type, Primary Key>

package org.launchcode.techjobs.persistent.models.data;

import org.launchcode.techjobs.persistent.models.Employer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository // For storage (persistence)
public interface EmployerRepository extends CrudRepository<Employer, Integer> {


}
