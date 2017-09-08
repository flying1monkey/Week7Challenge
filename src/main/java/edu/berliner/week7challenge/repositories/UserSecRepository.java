package edu.berliner.week7challenge.repositories;

import edu.berliner.week7challenge.models.UserSec;
import org.springframework.data.repository.CrudRepository;

public interface UserSecRepository extends CrudRepository<UserSec, Long>
{
	UserSec findByUsername(String username);
}
