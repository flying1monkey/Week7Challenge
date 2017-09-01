package edu.berliner.week6challenge.models;

import javax.persistence.*;

@Entity
public class Job
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long jobId;

    private String jobCompany;
    private String jobTitle;
    private String jobStartDate;
    private String jobEndDate;
    private String jobFirstDuty;
    private String jobSecondDuty;

    //for "deletions"
    private boolean jobIsArchived;

    //Constructor
    public Job()
    {
        this.jobIsArchived=false;
    }

    public long getJobId()
    {
        return jobId;
    }

    public void setJobId(long jobId)
    {
        this.jobId = jobId;
    }

    public String getJobCompany()
    {
        return jobCompany;
    }

    public void setJobCompany(String jobCompany)
    {
        this.jobCompany = jobCompany;
    }

    public String getJobTitle()
    {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle)
    {
        this.jobTitle = jobTitle;
    }

    public String getJobStartDate()
    {
        return jobStartDate;
    }

    public void setJobStartDate(String jobStartDate)
    {
        this.jobStartDate = jobStartDate;
    }

    public String getJobEndDate()
    {
        return jobEndDate;
    }

    public void setJobEndDate(String jobEndDate)
    {
        this.jobEndDate = jobEndDate;
    }

    public String getJobFirstDuty()
    {
        return jobFirstDuty;
    }

    public void setJobFirstDuty(String jobFirstDuty)
    {
        this.jobFirstDuty = jobFirstDuty;
    }

    public String getJobSecondDuty()
    {
        return jobSecondDuty;
    }

    public void setJobSecondDuty(String jobSecondDuty)
    {
        this.jobSecondDuty = jobSecondDuty;
    }

    public boolean isJobIsArchived()
    {
        return jobIsArchived;
    }

    public void setJobIsArchived(boolean jobIsArchived)
    {
        this.jobIsArchived = jobIsArchived;
    }

}
