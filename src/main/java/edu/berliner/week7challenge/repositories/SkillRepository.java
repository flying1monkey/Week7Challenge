package edu.berliner.week7challenge.repositories;

import edu.berliner.week7challenge.models.Skill;
import org.springframework.data.repository.CrudRepository;

public interface SkillRepository extends CrudRepository<Skill,Long>
{
}
