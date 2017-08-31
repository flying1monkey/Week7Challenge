package edu.berliner.week6challenge.repositories;

import edu.berliner.week6challenge.models.Person;
import org.springframework.data.repository.CrudRepository;

public interface PersonRepository extends CrudRepository<Person,Long>
{
}
