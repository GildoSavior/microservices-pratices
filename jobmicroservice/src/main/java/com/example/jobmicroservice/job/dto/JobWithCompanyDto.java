package com.example.jobmicroservice.job.dto;

import com.example.jobmicroservice.job.Job;
import com.example.jobmicroservice.job.external.Company;

public class JobWithCompanyDto {

    private Job job;
    private Company company;

    public Job getJob() {
        return job;
    }

    public Company getCompany() {
        return company;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
}
