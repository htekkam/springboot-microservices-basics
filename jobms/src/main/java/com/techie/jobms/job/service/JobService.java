package com.techie.jobms.job.service;


import com.techie.jobms.job.model.Job;
import com.techie.jobms.job.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JobService {

    @Autowired
    JobRepository jobRepository;

    public List<Job> findAll(){
        return jobRepository.findAll();
    }

    public void createJob(Job job){
        jobRepository.save(job);
    }

    public Job getJobById(Long id){
        return jobRepository.findById(id)
                .orElseThrow(RuntimeException::new);
    }

    public boolean deleteJob(Long id){
        try {
            jobRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean updateJob(Long id,Job updatedJob){
        Optional<Job> jobOptional = jobRepository.findById(id);
        if(jobOptional.isPresent()){
            Job job = jobOptional.get();
            job.setDescription(updatedJob.getDescription());
            job.setTitle(updatedJob.getTitle());
            job.setTitle(updatedJob.getTitle());
            job.setMaxSalary(updatedJob.getMaxSalary());
            job.setMinSalary(updatedJob.getMinSalary());
            job.setLocation(updatedJob.getLocation());
            jobRepository.save(job);
            return true;
        }
        return false;
    }
}
