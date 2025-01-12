package com.example.demo.serviceImpl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.model.Job;
import com.example.demo.repository.JobRepository;
import com.example.demo.service.JobService;

import jakarta.websocket.server.ServerEndpoint;

@Service
public class JobServiceImpl implements JobService {


    private final JobRepository jobRepository;

    public JobServiceImpl(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @Override
    public Job createJob(Job job) {
        return jobRepository.save(job);
    }

    @Override
    public Job getJobById(Long id) {
        return jobRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Job not found with id: " + id));
    }

    @Override
    public List<Job> getAllJobs() {
        return jobRepository.findAll();
    }

    @Override
    public Job updateJob(Long id, Job updatedJob) {

        Job existingJob = getJobById(id);

        existingJob.setJobTitle(updatedJob.getJobTitle());
        existingJob.setJobDescription(updatedJob.getJobDescription());
        existingJob.setJobLocation(updatedJob.getJobLocation());
        existingJob.setJobType(updatedJob.getJobType());
        existingJob.setJobSalary(updatedJob.getJobSalary());
        existingJob.setJobCompany(updatedJob.getJobCompany());
        existingJob.setJobPostedDate(updatedJob.getJobPostedDate());

        return jobRepository.save(updatedJob);

    }

    @Override
    public void deleteJob(Long id) {
        if (jobRepository.existsById(id)) {
            jobRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("Job not found with id: " + id);
        }   

    }

    
}
