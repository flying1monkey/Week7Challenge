package edu.berliner.week7challenge.models;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Person
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long personId;

    @NotEmpty
    private String personFirstName;
    @NotEmpty
    private String personLastName;
    @NotEmpty
    private String personEmail;

    //for "deletions"
    private boolean personIsArchived;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    public Set<Education> educationSet;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    public Set<Experience> experienceSet;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    public Set<Skill> skillSet;

    public Person()
    {
        Set<Education> educationSet = new HashSet<Education>();
        Set<Experience> experienceSet = new HashSet<Experience>();
        Set<Skill> skillSet = new HashSet<Skill>();
        setPersonIsArchived(false);
    }

    public void addEducationToPerson(Education edu)
    {
        this.educationSet.add(edu);
    }

    public void addExpToPerson(Experience experience)
    {
        this.experienceSet.add(experience);
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

    public Set<Experience> getExperienceSet()
    {
        return experienceSet;
    }

    public void setExperienceSet(Set<Experience> experienceSet)
    {
        this.experienceSet = experienceSet;
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
