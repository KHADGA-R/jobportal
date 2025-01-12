package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Job;
public interface JobService {

    Job createJob(Job job);
    Job getJobById(Long id);
    List<Job> getAllJobs();
    Job updateJob(Long id, Job job);
    void deleteJob(Long id);

    
}
