package edu.berliner.week7challenge.repositories;

import edu.berliner.week7challenge.models.Person;
import org.springframework.data.repository.CrudRepository;

public interface PersonRepository extends CrudRepository<Person,Long>
{
    //Iterable<Person> findAllByPersonIsArchivedEqualsFalse(); Doesn't work
    //Iterable<Person> findAllByPersonIsArchivedEquals(); Doesn't work
    //Iterable<Person> findByPersonIsArchivedFalse();  Doesn't work


}
