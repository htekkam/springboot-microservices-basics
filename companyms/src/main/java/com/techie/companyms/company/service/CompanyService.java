package com.techie.companyms.company.service;


import com.techie.companyms.company.client.ReviewClient;
import com.techie.companyms.company.dto.ReviewMessage;
import com.techie.companyms.company.model.Company;
import com.techie.companyms.company.repository.CompanyRepository;
import jakarta.ws.rs.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyService {

   @Autowired
   CompanyRepository companyRepository;

   @Autowired
   ReviewClient reviewClient;

    public List<Company> getAllCompanies(){
        return companyRepository.findAll();
    }

    public void createCompany(Company company){
        companyRepository.save(company);
    }

    public Company getCompanyById(Long id){
        Optional<Company> companyById = companyRepository.findById(id);
        return companyById.orElse(null);
    }

    public boolean deleteCompany(Long id){
        try {

            companyRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean updateCompany(Long id,Company updatedCompany){
        Optional<Company> companyOptional = companyRepository.findById(id);
        if(companyOptional.isPresent()){
            Company company = companyOptional.get();
            company.setName(updatedCompany.getName());
            company.setDescription(updatedCompany.getDescription());
            companyRepository.save(company);
            return true;
        }
        return false;
    }

    public void updateCompanyRating(ReviewMessage reviewMessage) {
        System.out.println("review message consumed::"+reviewMessage.getDescription());
        Company company = companyRepository.findById(reviewMessage.getCompanyId())
                .orElseThrow(() -> new NotFoundException("Company not found for the id::"+ reviewMessage.getCompanyId()));
        double avgRating = reviewClient.getAvgRating(reviewMessage.getCompanyId());
        company.setAvgRating(avgRating);
        companyRepository.save(company);
    }
}
