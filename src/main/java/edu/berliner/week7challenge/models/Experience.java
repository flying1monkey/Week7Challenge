package edu.berliner.week7challenge.models;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;

@Entity
public class Experience
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long expId;

    @NotEmpty
    private String expCompany;
    @NotEmpty
    private String expTitle;
    @NotEmpty
    private String expStartDate;
    @NotEmpty
    private String expEndDate;
    @NotEmpty
    private String expFirstDuty;
    @NotEmpty
    private String expSecondDuty;

    //for "deletions"
    private boolean expIsArchived;

    @ManyToOne
    private Person experiencePerson;

    //Constructor
    public Experience()
    {
        this.expIsArchived=false;
    }

    public long getExpId()
    {
        return expId;
    }

    public void setExpId(long expId)
    {
        this.expId = expId;
    }

    public String getExpCompany()
    {
        return expCompany;
    }

    public void setExpCompany(String expCompany)
    {
        this.expCompany = expCompany;
    }

    public String getExpTitle()
    {
        return expTitle;
    }

    public void setExpTitle(String expTitle)
    {
        this.expTitle = expTitle;
    }

    public String getExpStartDate()
    {
        return expStartDate;
    }

    public void setExpStartDate(String expStartDate)
    {
        this.expStartDate = expStartDate;
    }

    public String getExpEndDate()
    {
        return expEndDate;
    }

    public void setExpEndDate(String expEndDate)
    {
        this.expEndDate = expEndDate;
    }

    public String getExpFirstDuty()
    {
        return expFirstDuty;
    }

    public void setExpFirstDuty(String expFirstDuty)
    {
        this.expFirstDuty = expFirstDuty;
    }

    public String getExpSecondDuty()
    {
        return expSecondDuty;
    }

    public void setExpSecondDuty(String expSecondDuty)
    {
        this.expSecondDuty = expSecondDuty;
    }

    public boolean isExpIsArchived()
    {
        return expIsArchived;
    }

    public void setExpIsArchived(boolean expIsArchived)
    {
        this.expIsArchived = expIsArchived;
    }

    public Person getExperiencePerson()
    {
        return experiencePerson;
    }

    public void setExperiencePerson(Person experiencePerson)
    {
        this.experiencePerson = experiencePerson;
    }
}
