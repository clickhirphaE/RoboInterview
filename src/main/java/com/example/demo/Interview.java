package com.example.demo;




import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.*;

@Entity
public class Interview {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String dateEntry;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime startTime;
    private double checkTime;
    private LocalDateTime endTime;

    private String resume;

    private String status;

    @OneToMany(mappedBy = "interview")
    private List<Question> questions;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private User user;

    @ManyToOne
    private JobPosition jobPosition;

    public Interview() {
        questions = new ArrayList<>();

    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public JobPosition getJobPosition() {
        return jobPosition;
    }

    public void setJobPosition(JobPosition jobPosition) {
        this.jobPosition = jobPosition;
    }



    public void setQuestions(Question question) {

        this.questions.add(question);
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }


    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public double getCheckTime() {
        return checkTime;
    }

    public void setCheckTime(double checkTime) {
        this.checkTime = checkTime;
    }

    public String getDateEntry() {
        return dateEntry;
    }

    public void setDateEntry(String dateEntry) {
        this.dateEntry = dateEntry;
    }

    public String getResume() {
        return resume;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }

    public List<Question> getQuestions() {
        return questions;
    }
}
