package edu.berliner.week7challenge.models;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;

@Entity
public class Skill
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long skillId;

    @NotEmpty
    private String skillName;
    @NotEmpty
    private String skillLevel;

    //for "deletions"
    private boolean skillIsArchived;

    @ManyToOne
    private PersonUser skillPersonUser;

    public Skill()
    {
        this.setSkillIsArchived(false);
    }

    public long getSkillId()
    {
        return skillId;
    }

    public void setSkillId(long skillId)
    {
        this.skillId = skillId;
    }

    public String getSkillName()
    {
        return skillName;
    }

    public void setSkillName(String skillName)
    {
        this.skillName = skillName;
    }

    public String getSkillLevel()
    {
        return skillLevel;
    }

    public void setSkillLevel(String skillLevel)
    {
        this.skillLevel = skillLevel;
    }

    public boolean isSkillIsArchived()
    {
        return skillIsArchived;
    }

    public void setSkillIsArchived(boolean skillIsArchived)
    {
        this.skillIsArchived = skillIsArchived;
    }

    public PersonUser getSkillPersonUser()
    {
        return skillPersonUser;
    }

    public void setSkillPersonUser(PersonUser skillPersonUser)
    {
        this.skillPersonUser = skillPersonUser;
    }
}
