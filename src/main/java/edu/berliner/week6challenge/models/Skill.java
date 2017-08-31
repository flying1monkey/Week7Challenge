package edu.berliner.week6challenge.models;

import javax.persistence.*;

@Entity
public class Skill
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long skillId;

    private String skillName;
    private String skillLevel;

    //for "deletions"
    private boolean skillIsArchived;

    @ManyToOne
    private Person skillPerson;

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

    public Person getSkillPerson()
    {
        return skillPerson;
    }

    public void setSkillPerson(Person skillPerson)
    {
        this.skillPerson = skillPerson;
    }
}
