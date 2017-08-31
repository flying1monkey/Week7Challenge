package edu.berliner.week6challenge.repositories;

import edu.berliner.week6challenge.models.Job;
import org.springframework.data.repository.CrudRepository;

public interface JobRepository extends CrudRepository<Job,Long>
{
}
