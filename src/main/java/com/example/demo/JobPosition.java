package com.example.demo;

import javax.persistence.*;
import java.util.Set;

@Entity
public class JobPosition {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String title;

    private Double salary;

    private String jobType;

    private String description;

    private String address;

    @OneToMany(mappedBy = "jobPosition")
    private Set<Interview> jobInterviews;

    public JobPosition(String title, Double salary, String jobType, String description, String address) {
        this.title = title;
        this.salary = salary;
        this.jobType = jobType;
        this.description = description;
        this.address = address;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public Set<Interview> getJobInterviews() {
        return jobInterviews;
    }

    public void setJobInterviews(Set<Interview> jobInterviews) {
        this.jobInterviews = jobInterviews;
    }
}
