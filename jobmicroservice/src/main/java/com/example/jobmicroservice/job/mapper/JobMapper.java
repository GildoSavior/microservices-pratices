package com.example.jobmicroservice.job.mapper;

import com.example.jobmicroservice.job.Job;
import com.example.jobmicroservice.job.dto.JobDTO;
import com.example.jobmicroservice.job.external.Company;
import com.example.jobmicroservice.job.external.Review;

import java.util.List;

public class JobMapper {

    public static JobDTO mapToJobDto(Job job, Company company, List<Review> reviews) {
        JobDTO jobDTO = new JobDTO();
        jobDTO.setId(job.getId());
        jobDTO.setTitle(job.getTitle());
        jobDTO.setDescription(job.getDescription());
        jobDTO.setMinSalary(job.getMinSalary());
        jobDTO.setMaxSalary(job.getMaxSalary());
        jobDTO.setLocation(job.getLocation());
        jobDTO.setCompany(company);
        jobDTO.setReviews(reviews);
        return jobDTO;
    }
}
