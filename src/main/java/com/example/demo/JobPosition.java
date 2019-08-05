package com.example.demo;

import com.sun.org.apache.xpath.internal.compiler.Keywords;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
public class JobPosition {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;



    private String company;
    private String title;



    private Set<Keywords> keyword;
    private Double salary;

    private String jobType;

    private String description;

    private String address;


    private Date posteddate;


    @OneToMany(mappedBy = "jobPosition")
    private Set<Interview> jobInterviews;
  //  private Set<JobList> joblist;
    public JobPosition(){
      new HashSet<String>();

    }

    public JobPosition(String company, String title, Set<Keywords> keyword, Double salary, String jobType, String description, String address,Date posteddate, Set<Interview> jobInterviews) {
        this.company = company;
        this.title = title;
        this.keyword = keyword;
        this.salary = salary;
        this.jobType = jobType;
        this.description = description;
        this.address = address;
        this.posteddate = posteddate;
        this.jobInterviews = jobInterviews;
    }


    public long getId() {
        return id;
    }
    public Set<Keywords> getKeywords() {
        return keyword;
    }

    public void  setKeywords(Keywords keywords) {
        this.keyword.add(keywords);
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
    public Date getPosteddate() {
        return posteddate;
    }

    public void setPosteddate(Date posteddate) {
           // new Date();
        this.posteddate =posteddate;


        posteddate.toString();


    }

    public Set<Interview> getJobInterviews() {
        return jobInterviews;
    }

    public void setJobInterviews(Set<Interview> jobInterviews)
    {

        this.jobInterviews = jobInterviews;


    }
}
