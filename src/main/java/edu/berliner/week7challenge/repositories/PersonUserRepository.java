package edu.berliner.week7challenge.repositories;

import edu.berliner.week7challenge.models.PersonUser;
import org.springframework.data.repository.CrudRepository;

public interface PersonUserRepository extends CrudRepository<PersonUser,Long>
{
    //Iterable<PersonUser> findAllByPersonIsArchivedEqualsFalse(); Doesn't work
    //Iterable<PersonUser> findAllByPersonIsArchivedEquals(); Doesn't work
    //Iterable<PersonUser> findByPersonIsArchivedFalse();  Doesn't work
    PersonUser findByUsername(String username);

    //PersonUser findAllByPersonFirstNameContainsOrPersonLastNameContains(String nameBit);

}
