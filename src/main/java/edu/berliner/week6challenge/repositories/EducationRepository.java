package edu.berliner.week6challenge.repositories;

import edu.berliner.week6challenge.models.Education;
import org.springframework.data.repository.CrudRepository;

public interface EducationRepository extends CrudRepository<Education,Long>
{
}
