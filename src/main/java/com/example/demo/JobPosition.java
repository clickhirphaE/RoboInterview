package com.example.demo;

import javax.persistence.*;
import java.util.Set;

@Entity
public class JobPosition {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String company;

    private String title;

    private String keywords;
    private Double salary;

    private String jobType;

    private String description;

    private String address;

    private  String posteddate;

    @OneToMany(mappedBy = "jobPosition")
    private Set<Interview> jobInterviews;
  //  private Set<JobList> joblist;
    public JobPosition(){

    }

    public JobPosition(String company, String title, Double salary, String jobType, String description,String keywords, String address, String posteddate) {
        this.title = title;
        this.salary = salary;
        this.jobType = jobType;
        this.description = description;
        this.address = address;
        this.company=company;
        this.keywords=keywords;
        this.posteddate=posteddate;
    }

    public long getId() {
        return id;
    }
    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public String getJobType() {
        return jobType;
    }

    public void setJobType(String jobType) {
        this.jobType = jobType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {

        this.address = address;
    }
    public String getPosteddate() {
        return posteddate;
    }

    public void setPosteddate(String posteddate) {


        this.posteddate =posteddate;
    }

    public Set<Interview> getJobInterviews() {
        return jobInterviews;
    }

    public void setJobInterviews(Set<Interview> jobInterviews)
    {

        this.jobInterviews = jobInterviews;


    }
}
