package edu.berliner.week6challenge.models;

import javax.persistence.*;

@Entity
public class Education
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long educationId;

    private String educationSchoolName;
    private long educationGradYear;
    private String educationDegree;

    //For "deletions"
    private boolean educationIsArchived;

    @ManyToOne
    private Person educationPerson;

    public long getEducationId()
    {
        return educationId;
    }

    public void setEducationId(long educationId)
    {
        this.educationId = educationId;
    }

    public String getEducationSchoolName()
    {
        return educationSchoolName;
    }

    public void setEducationSchoolName(String educationSchoolName)
    {
        this.educationSchoolName = educationSchoolName;
    }

    public long getEducationGradYear()
    {
        return educationGradYear;
    }

    public void setEducationGradYear(long educationGradYear)
    {
        this.educationGradYear = educationGradYear;
    }

    public String getEducationDegree()
    {
        return educationDegree;
    }

    public void setEducationDegree(String educationDegree)
    {
        this.educationDegree = educationDegree;
    }

    public boolean isEducationIsArchived()
    {
        return educationIsArchived;
    }

    public void setEducationIsArchived(boolean educationIsArchived)
    {
        this.educationIsArchived = educationIsArchived;
    }

    public Person getEducationPerson()
    {
        return educationPerson;
    }

    public void setEducationPerson(Person educationPerson)
    {
        this.educationPerson = educationPerson;
    }
}
