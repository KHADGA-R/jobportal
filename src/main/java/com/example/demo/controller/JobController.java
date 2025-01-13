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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;



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

    @PutMapping("/jobs/{id}")
    public ResponseEntity<?> updateJob(@PathVariable("id") Long id, @RequestBody Job job) {
        try {
            // Set the ID from the path variable to ensure we update the correct job
            job.setJobId(id);
            
            //Call the service method to update the job
            Job updatedJob = jobService.updateJob(id, job);
            if (updatedJob != null) {
                String successMessage = "Job " + updatedJob.getJobTitle() + " updated successfully .";
                return ResponseEntity.ok(successMessage);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Error updating job: " + e.getMessage());
        }
       
    }
    
}
