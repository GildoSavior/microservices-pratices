package com.example.jobmicroservice.job.service;

import com.example.jobmicroservice.job.Job;
import com.example.jobmicroservice.job.IJobRepository;
import com.example.jobmicroservice.job.clients.CompanyClient;
import com.example.jobmicroservice.job.clients.ReviewClient;
import com.example.jobmicroservice.job.dto.JobDTO;
import com.example.jobmicroservice.job.external.Company;
import com.example.jobmicroservice.job.external.Review;
import com.example.jobmicroservice.job.mapper.JobMapper;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class   JobService implements IJobService {


    private final IJobRepository jobRepository;
    private final CompanyClient  companyClient;
    private final ReviewClient  reviewClient;

    int attempt = 0;

    public JobService(IJobRepository jobRepository, CompanyClient companyClient, ReviewClient reviewClient) {
        this.jobRepository = jobRepository;
        this.companyClient = companyClient;
        this.reviewClient = reviewClient;
    }

    @Autowired
    RestTemplate restTemplate;


    @Override
//    @CircuitBreaker(name = "companyBreaker", fallbackMethod = "companyBreakerFallback")
//    @Retry(name = "companyBreaker", fallbackMethod = "companyBreakerFallback")
    @RateLimiter(name = "companyBreaker", fallbackMethod = "companyBreakerFallback")
    public List<JobDTO> findAll() {

        List<Job> jobs = jobRepository.findAll();
        List<JobDTO> jobWithCompanies  = new ArrayList<>();

        return jobs.stream()
                .map(this::convertToDto).collect(Collectors.toList());

    }


    public  List<String> companyBreakerFallback() {
        List<String> list = new ArrayList<>();
        list.add("Dummy");
        return list;

    }

    private JobDTO convertToDto(Job job) {

        Company company = companyClient.getCompany(job.getCompanyId());
        List<Review> reviews = reviewClient.getReviews(company.getId());

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
