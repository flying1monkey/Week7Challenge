package edu.berliner.week7challenge.repositories;

import edu.berliner.week7challenge.models.Job;
import org.springframework.data.repository.CrudRepository;

public interface JobRepository extends CrudRepository<Job,Long>{
}
