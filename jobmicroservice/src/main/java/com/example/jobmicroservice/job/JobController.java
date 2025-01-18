package com.example.jobmicroservice.job;

import com.example.jobmicroservice.job.dto.JobDTO;
import com.example.jobmicroservice.job.service.IJobService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jobs")
public class JobController {

    private final IJobService jobService;

    public JobController(IJobService jobService) {
        this.jobService = jobService;
    }

    @GetMapping
    public ResponseEntity<List<JobDTO>> findAll() {
        var jobs = jobService.findAll();
        return ResponseEntity.ok(jobs);
    }

    @PostMapping
    public ResponseEntity<Job> createJob(@RequestBody Job job) {
        var newJob = jobService.createJob(job);
        return new ResponseEntity<>(newJob, HttpStatus.CREATED);
    }

    @GetMapping("/{jobId}")
    public ResponseEntity<JobDTO> getJobById(@PathVariable Long jobId){
        var job = jobService.findById(jobId);
        return ResponseEntity.ok(job);
    }

    @DeleteMapping("/{jobId}")
    public ResponseEntity<String> deleteJobById(@PathVariable Long jobId) {
        String result = jobService.deleteJobById(jobId);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/{jobId}")
    public ResponseEntity<Job> updateJob(@RequestBody Job job, @PathVariable Long jobId){
        Job updatedJob = jobService.updateById(job,jobId);
        return ResponseEntity.ok(job);
    }
}
