package edu.berliner.week6challenge.repositories;

import edu.berliner.week6challenge.models.Skill;
import org.springframework.data.repository.CrudRepository;

public interface SkillRepository extends CrudRepository<Skill,Long>
{
}
