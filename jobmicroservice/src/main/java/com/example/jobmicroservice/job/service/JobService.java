package com.example.jobmicroservice.job.service;

import com.example.jobmicroservice.job.Job;
import com.example.jobmicroservice.job.IJobRepository;
import com.example.jobmicroservice.job.dto.JobWithCompanyDto;
import com.example.jobmicroservice.job.external.Company;
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

    @Override
    public List<JobWithCompanyDto> findAll() {

        List<Job> jobs = jobRepository.findAll();
        List<JobWithCompanyDto> jobWithCompanies  = new ArrayList<>();

        return jobs.stream()
                .map(this::convertToDto).collect(Collectors.toList());

    }

    private JobWithCompanyDto convertToDto(Job job) {

        RestTemplate restTemplate = new RestTemplate();
        Company company = restTemplate.getForObject("http://localhost:8081/companies/" + job.getCompanyId(), Company.class);

        JobWithCompanyDto jobWithCompanyDto = new JobWithCompanyDto();

        jobWithCompanyDto.setJob(job);
        jobWithCompanyDto.setCompany(company);

        return jobWithCompanyDto;
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
