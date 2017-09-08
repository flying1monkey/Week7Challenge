package edu.berliner.week7challenge.repositories;

import edu.berliner.week7challenge.models.Education;
import org.springframework.data.repository.CrudRepository;

public interface EducationRepository extends CrudRepository<Education,Long>
{
}
