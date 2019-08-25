package com.ragul.adsplayapi.Repository;

import com.ragul.adsplayapi.Model.GameUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameUserRepository extends JpaRepository<GameUser, Long> {

}
