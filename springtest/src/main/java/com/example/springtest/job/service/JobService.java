package com.example.springtest.job.service;

import com.example.springtest.job.Job;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JobService implements IJobService{

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
}
