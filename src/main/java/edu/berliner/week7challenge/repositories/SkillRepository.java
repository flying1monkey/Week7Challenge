package edu.berliner.week7challenge.repositories;

import edu.berliner.week7challenge.models.Skill;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface SkillRepository extends CrudRepository<Skill,Long>
{
	ArrayList<Skill> findAllBySkillNameContains(String s);
}
