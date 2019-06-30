package com.ragul.adsplayapi.Repository;

import com.ragul.adsplayapi.Model.Company;
import com.ragul.adsplayapi.Model.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {

}
