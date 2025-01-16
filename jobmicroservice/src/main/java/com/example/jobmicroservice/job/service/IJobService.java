package com.example.jobmicroservice.job.service;

import com.example.jobmicroservice.job.Job;
import com.example.jobmicroservice.job.dto.JobWithCompanyDto;

import java.util.List;

public interface IJobService {
    List<JobWithCompanyDto> findAll();
    Job createJob(Job job);
    Job findById(Long id);
    String deleteJobById(Long jobId);
    Job updateById(Job job, Long jobId);
}
