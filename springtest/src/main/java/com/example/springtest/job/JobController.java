package com.example.springtest.job;

import com.example.springtest.job.service.IJobService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class JobController {

    private final IJobService jobService;

    public JobController(IJobService jobService) {
        this.jobService = jobService;
    }

    @GetMapping("/jobs")
    public ResponseEntity<List<Job>> findAll() {
        var jobs = jobService.findAll();
        return ResponseEntity.ok(jobs);

    }


    @PostMapping("/jobs")
    public ResponseEntity<Job> createJob(@RequestBody Job job) {
        var newJob = jobService.createJob(job);
        return new ResponseEntity<>(newJob, HttpStatus.CREATED);
    }

    @GetMapping("/jobs/{jobId}")
    public ResponseEntity<Job> getJobById(@PathVariable Long jobId){
        var job = jobService.findById(jobId);
        return ResponseEntity.ok(job);
    }
}
