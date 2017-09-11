package edu.berliner.week7challenge.models;

import org.springframework.security.access.method.P;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long jobId;

    private String jobTitle;
    private String jobEmployer;
    private String jobSalaryRange;
    private String jobDescription;
    private String jobSkills;

    private boolean jobIsArchived;

    public Job()
    {
        this.jobIsArchived=false;
    }

    public long getJobId() {
        return jobId;
    }

    public void setJobId(long jobId) {
        this.jobId = jobId;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getJobEmployer() {
        return jobEmployer;
    }

    public void setJobEmployer(String jobEmployer) {
        this.jobEmployer = jobEmployer;
    }

    public String getJobSalaryRange() {
        return jobSalaryRange;
    }

    public void setJobSalaryRange(String jobSalaryRange) {
        this.jobSalaryRange = jobSalaryRange;
    }

    public String getJobDescription() {
        return jobDescription;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }

    public String getJobSkills() {
        return jobSkills;
    }

    public void setJobSkills(String jobSkills) {
        this.jobSkills = jobSkills;
    }
}
