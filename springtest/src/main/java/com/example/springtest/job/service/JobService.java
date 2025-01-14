package com.example.springtest.job.service;

import com.example.springtest.job.Job;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JobService implements IJobService {

    List<Job> jobs = new ArrayList<>();

    @Override
    public List<Job> findAll() {
        return jobs;
    }

    @Override
    public Job createJob(Job job) {
        jobs.add(job);
        return job;
    }

    @Override
    public Job findById(Long id) {
        return (Job) jobs
                .stream()
                .map(job -> job.getId().equals(id));
//                .filter(job -> job.getId().equals(id));
    }

    @Override
    public String deleteJobById(Long jobId) {
        Job job = jobs.stream().filter(x -> x.getId().equals(jobId)).findFirst().orElse(null);
        jobs.remove(job);
        return "Job Deleted";
    }

    @Override
    public Job updateById(Job newJob, Long jobId) {
        Job job = jobs.stream().filter(x -> x.getId().equals(jobId)).findFirst().orElse(null);
        assert job != null;
        job.setDescription(newJob.getDescription());
        job.setLocation(newJob.getLocation());
        job.setTitle(newJob.getTitle());

        return job;
    }
}
