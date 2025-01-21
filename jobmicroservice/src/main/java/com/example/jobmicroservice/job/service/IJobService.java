package com.example.jobmicroservice.job.service;

import com.example.jobmicroservice.job.Job;
import com.example.jobmicroservice.job.dto.JobDTO;

import java.util.List;

public interface IJobService {
    List<JobDTO> findAll();
    Job createJob(Job job);
    JobDTO findById(Long id);
    String deleteJobById(Long jobId);
    Job updateById(Job job, Long jobId);
}
