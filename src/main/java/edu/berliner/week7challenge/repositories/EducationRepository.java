package edu.berliner.week7challenge.repositories;

import edu.berliner.week7challenge.models.Education;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface EducationRepository extends CrudRepository<Education,Long>
{
	ArrayList<Education> findAllByEducationSchoolName(String e);
}
