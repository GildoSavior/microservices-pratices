package com.example.springtest.job.service;

import com.example.springtest.job.IJobRepository;
import com.example.springtest.job.Job;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JobService implements IJobService {

    private final IJobRepository jobRepository;

    public JobService(IJobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @Override
    public List<Job> findAll() {
        return jobRepository.findAll();
    }

    @Override
    public Job createJob(Job job) {
        return jobRepository.save(job);
    }

    @Override
    public Job findById(Long id) {
        return jobRepository.findById(id).orElse(null);
    }

    @Override
    public String deleteJobById(Long jobId) {
        jobRepository.deleteById(jobId);
        return "Job Deleted";
    }

    @Override
    public Job updateById(Job newJob, Long jobId) {
        Job job = jobRepository.findById(jobId).orElse(null);

        job.setDescription(newJob.getDescription());
        job.setLocation(newJob.getLocation());
        job.setTitle(newJob.getTitle());

        return jobRepository.save(job);
    }
}
