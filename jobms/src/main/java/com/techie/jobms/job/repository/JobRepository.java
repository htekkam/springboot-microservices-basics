package com.techie.jobms.job.repository;


import com.techie.jobms.job.model.Job;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<Job,Long> {
}
