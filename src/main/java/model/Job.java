package model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "jobs")
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long jobId;

    private String jobTitle;
    private String jobDescription;
    private String jobLocation;
    private String jobType;
    private String jobSalary;
    private String jobCompany;
    private String jobPostedDate;


        public Job(String jobTitle, String jobDescription, String jobLocation, String jobType, String jobSalary, String jobCompany, String jobPostedDate) {
        this.jobTitle = jobTitle;
        this.jobDescription = jobDescription;
        this.jobLocation = jobLocation;
        this.jobType = jobType;
        this.jobSalary = jobSalary;
        this.jobCompany = jobCompany;
        this.jobPostedDate = jobPostedDate;

    }

    public Job(Long jobId, String jobTitle, String jobDescription, String jobLocation, String jobType, String jobSalary, String jobCompany, String jobPostedDate) {
        this.jobId = jobId;
        this.jobTitle = jobTitle;
        this.jobDescription = jobDescription;
        this.jobLocation = jobLocation;
        this.jobType = jobType;
        this.jobSalary = jobSalary;
        this.jobCompany = jobCompany;
        this.jobPostedDate = jobPostedDate;
    }

    public Long getJobId() {
        return jobId;
    }

    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getJobDescription() {
        return jobDescription;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }

    public String getJobLocation() {
        return jobLocation;
    }

    public void setJobLocation(String jobLocation) {
        this.jobLocation = jobLocation;
    }

    public String getJobType() {
        return jobType;
    }

    public void setJobType(String jobType) {
        this.jobType = jobType;
    }

    public String getJobSalary() {
        return jobSalary;
    }

    public void setJobSalary(String jobSalary) {
        this.jobSalary = jobSalary;
    }

    public String getJobCompany() {
        return jobCompany;
    }

    public void setJobCompany(String jobCompany) {
        this.jobCompany = jobCompany;
    }

    public String getJobPostedDate() {
        return jobPostedDate;
    }

    public void setJobPostedDate(String jobPostedDate) {
        this.jobPostedDate = jobPostedDate;
    }

    public String toString() {
        return "Job{" +
                "jobId=" + jobId +
                ", jobTitle='" + jobTitle + '\'' +
                ", jobDescription='" + jobDescription + '\'' +
                ", jobLocation='" + jobLocation + '\'' +
                ", jobType='" + jobType + '\'' +
                ", jobSalary='" + jobSalary + '\'' +
                ", jobCompany='" + jobCompany + '\'' +
                ", jobPostedDate='" + jobPostedDate + '\'' +
                '}';
    }

    
}
