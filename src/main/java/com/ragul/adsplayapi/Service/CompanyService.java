package com.ragul.adsplayapi.Service;

import com.ragul.adsplayapi.Model.Company;
import com.ragul.adsplayapi.Repository.CompanyRepository;
import com.ragul.adsplayapi.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;

@Service
public class CompanyService {
    @Autowired
    CompanyRepository companyRepository;
    @Autowired
    GameService gameService;

    public void save(Company company) {
        company.setPassword(hash(company.getPassword()));
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

    public Company login(String email, String password) {
        Optional<Company> user = companyRepository.findByEmail(email);
        if (user.isPresent()){
            if(verifyHash(password,user.get().getPassword())){
                return user.get();
            }
        }
        return null;
    }


    private String hash(String password) {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
            md.update(password.getBytes());
            byte[] digest = md.digest();
            String myHash = DatatypeConverter.printHexBinary(digest).toUpperCase();
            return myHash;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    private boolean verifyHash(String password, String hash) {
        return hash(password).equals(hash);
    }
}
