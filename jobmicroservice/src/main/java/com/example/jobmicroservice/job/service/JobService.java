package com.example.jobmicroservice.job.service;

import com.example.jobmicroservice.job.Job;
import com.example.jobmicroservice.job.IJobRepository;
import com.example.jobmicroservice.job.dto.JobDTO;
import com.example.jobmicroservice.job.external.Company;
import com.example.jobmicroservice.job.external.Review;
import com.example.jobmicroservice.job.mapper.JobMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class JobService implements IJobService {


    private final IJobRepository jobRepository;

    public JobService(IJobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @Autowired
    RestTemplate restTemplate;

    @Override
    public List<JobDTO> findAll() {

        List<Job> jobs = jobRepository.findAll();
        List<JobDTO> jobWithCompanies  = new ArrayList<>();

        return jobs.stream()
                .map(this::convertToDto).collect(Collectors.toList());

    }

    private JobDTO convertToDto(Job job) {

        Company company = restTemplate.getForObject("http://localhost:8081/companies/" + job.getCompanyId(), Company.class);

        ResponseEntity<List<Review>> reviewsResponse = restTemplate.exchange(
                "http://localhost:8083/companies/" + job.getCompanyId() + "/reviews",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Review>>() {});

        List<Review> reviews = reviewsResponse.getBody();

        return JobMapper.mapToJobDto(job,company, reviews);
    }

    @Override
    public Job createJob(Job job) {
        return jobRepository.save(job);
    }

    @Override
    public JobDTO findById(Long id) {

        Job job = jobRepository.findById(id).orElse(null);

        return convertToDto(job);
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
