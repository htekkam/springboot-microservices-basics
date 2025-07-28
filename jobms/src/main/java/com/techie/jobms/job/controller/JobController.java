package com.techie.jobms.job.controller;


import com.techie.jobms.job.dto.JobDTO;
import com.techie.jobms.job.model.Job;
import com.techie.jobms.job.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/job")
public class JobController {

    @Autowired
    JobService jobService;

    @GetMapping("/findAll")
    public List<JobDTO> hello() {
        return jobService.findAll();
    }

    @PostMapping("/create")
    public ResponseEntity<String> createJob(@RequestBody Job job) {

        jobService.createJob(job);
        return ResponseEntity.ok("Job created successfully!");
    }

    @PutMapping("/update/{id}")
    public boolean updateJob(@PathVariable Long id, @RequestBody Job updatedJob) {

        return jobService.updateJob(id, updatedJob);
    }

    @DeleteMapping("/delete/{id}")
    public boolean deleteJob(@PathVariable Long id) {
        return jobService.deleteJob(id);

    }
    @GetMapping("/jobById/{id}")
    public ResponseEntity<JobDTO> getJobById(@PathVariable Long id) {
        JobDTO jobDTO = jobService.getJobById(id);
        return new ResponseEntity<>(jobDTO, HttpStatus.OK);
    }
}