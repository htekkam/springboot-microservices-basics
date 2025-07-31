package com.techie.jobms.job.service;


import com.techie.jobms.job.clients.CompanyClient;
import com.techie.jobms.job.clients.ReviewClient;
import com.techie.jobms.job.dto.JobDTO;
import com.techie.jobms.job.external.Company;
import com.techie.jobms.job.external.Review;
import com.techie.jobms.job.mapper.JobMapper;
import com.techie.jobms.job.model.Job;
import com.techie.jobms.job.repository.JobRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class JobService {

    @Autowired
    JobRepository jobRepository;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    CompanyClient companyClient;

    @Autowired
    ReviewClient reviewClient;

    @CircuitBreaker(name = "companyBreaker",
            fallbackMethod = "companyBreakerFallback")
    public List<JobDTO> findAll(){

        return jobRepository.findAll().stream().map(this::convertToDTO).toList();
    }

    private JobDTO convertToDTO(Job job){
        //RestTemplate restTemplate = new RestTemplate();
//        Company company = restTemplate.getForObject("http://COMPANY-MICROSERVICE:8081/companies/"+job.getCompanyId(), Company.class);
          Company company = companyClient.getCompany(job.getCompanyId());
//        ResponseEntity<List<Review>> reviewResponse= restTemplate.exchange("http://REVIEW-MICROSERVICE:8083/reviews?companyId=" + job.getCompanyId(),
//                HttpMethod.GET,
//                null,
//                new ParameterizedTypeReference<List<Review>>() {
//                });

        List<Review> reviewList = reviewClient.getReviews(job.getCompanyId());
        return JobMapper.mapToJobWithCompanyDTO(job,company,reviewList);

    }

    //defining fall back method
    


    public void createJob(Job job){
        jobRepository.save(job);
    }

    public JobDTO getJobById(Long id){
        return jobRepository.findById(id)
                .map(this::convertToDTO)
                .orElse(null);
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
