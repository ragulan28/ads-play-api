package com.ragul.adsplayapi.Service;

import com.ragul.adsplayapi.Model.Company;
import com.ragul.adsplayapi.Repository.CompanyRepository;
import com.ragul.adsplayapi.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyService {
    @Autowired
    CompanyRepository companyRepository;
    @Autowired
    GameService gameService;

    public void save(Company company) {
        companyRepository.save(company);
    }

    public List<Company> findAll() {
        return companyRepository.findAll();
    }

    public Company findById(Long id) {
        Optional<Company> company = companyRepository.findById(id);
        return company.orElseThrow(() -> new ResourceNotFoundException("Company", id.toString()));
    }

    public void delete(Long id) {
        Company company = this.findById(id);
        gameService.deleteByCompany(company);
        companyRepository.delete(company);
    }
}
