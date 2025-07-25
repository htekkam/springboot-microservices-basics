package com.techie.companyms.company.service;


import com.techie.companyms.company.model.Company;
import com.techie.companyms.company.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

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
}
