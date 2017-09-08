package edu.berliner.week7challenge.repositories;

import edu.berliner.week7challenge.models.RoleSec;
import org.springframework.data.repository.CrudRepository;

public interface RoleSecRepository extends CrudRepository<RoleSec,Long>
{
	RoleSec findBySecRoleName(String r);

}
