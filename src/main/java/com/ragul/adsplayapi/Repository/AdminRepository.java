package com.ragul.adsplayapi.Repository;

import com.ragul.adsplayapi.Model.Admin;
import com.ragul.adsplayapi.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {
    Optional<Admin> findByEmail(String email);
}
