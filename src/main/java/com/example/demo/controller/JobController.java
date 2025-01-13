package com.example.demo.controller;

import java.util.List;

import org.apache.catalina.connector.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Job;
import com.example.demo.service.JobService;
import org.springframework.web.bind.annotation.PostMapping;


@RestController
@RequestMapping("/api")
public class JobController {

    private final JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @GetMapping("/jobs")
    public ResponseEntity<List<Job>> getAllJobs() {
        List<Job> jobs = jobService.getAllJobs();
        return ResponseEntity.ok(jobs);
    }

    
    @PostMapping("/jobs/create")
    public ResponseEntity<Job> createJob(@RequestBody Job job) {

        try {
            Job createdJob = jobService.createJob(job);
            return ResponseEntity.ok(createdJob);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
    
        }  
    }
    
}
