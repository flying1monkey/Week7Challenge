package edu.berliner.week7challenge.repositories;

import edu.berliner.week7challenge.models.Experience;
import org.springframework.data.repository.CrudRepository;

public interface ExperienceRepository extends CrudRepository<Experience,Long>
{
}
