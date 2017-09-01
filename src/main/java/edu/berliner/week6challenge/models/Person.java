package edu.berliner.week6challenge.models;

import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Person
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long personId;

    private String personFirstName;
    private String personLastName;
    private String personEmail;

    //for "deletions"
    private boolean personIsArchived;

    @OneToMany
    public Set<Education> educationSet;

    @OneToMany
    public Set<Job> jobSet;

    @OneToMany
    public Set<Skill> skillSet;

    public Person()
    {
        Set<Education> educationSet = new HashSet<Education>();
        Set<Job> jobSet = new HashSet<Job>();
        Set<Skill> skillSet = new HashSet<Skill>();
        setPersonIsArchived(false);
    }

    public void addEducationToPerson(Education edu)
    {
        this.educationSet.add(edu);
    }

    public void addJobToPerson(Job job)
    {
        this.jobSet.add(job);
    }
    public void addSkillToPerson(Skill skill)
    {
        this.skillSet.add(skill);
    }

    /************************
     * Getters and setters
     ************************/

    public long getPersonId()
    {
        return personId;
    }

    public void setPersonId(long personId)
    {
        this.personId = personId;
    }

    public String getPersonFirstName()
    {
        return personFirstName;
    }

    public void setPersonFirstName(String personFirstName)
    {
        this.personFirstName = personFirstName;
    }

    public String getPersonLastName()
    {
        return personLastName;
    }

    public void setPersonLastName(String personLastName)
    {
        this.personLastName = personLastName;
    }

    public String getPersonEmail()
    {
        return personEmail;
    }

    public void setPersonEmail(String personEmail)
    {
        this.personEmail = personEmail;
    }

    public boolean isPersonIsArchived()
    {
        return personIsArchived;
    }

    public void setPersonIsArchived(boolean personIsArchived)
    {
        this.personIsArchived = personIsArchived;
    }

    public Set<Education> getEducationSet()
    {
        return educationSet;
    }

    public void setEducationSet(Set<Education> educationSet)
    {
        this.educationSet = educationSet;
    }

    public Set<Job> getJobSet()
    {
        return jobSet;
    }

    public void setJobSet(Set<Job> jobSet)
    {
        this.jobSet = jobSet;
    }

    public Set<Skill> getSkillSet()
    {
        return skillSet;
    }

    public void setSkillSet(Set<Skill> skillSet)
    {
        this.skillSet = skillSet;
    }
}
