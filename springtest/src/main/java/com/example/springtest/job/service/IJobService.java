package com.example.springtest.job.service;

import com.example.springtest.job.Job;

import java.util.List;

public interface IJobService {
    List<Job> findAll();
    Job createJob(Job job);
    Job findById(Long id);
}
