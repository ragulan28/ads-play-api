package com.ragul.adsplayapi.Repository;

import com.ragul.adsplayapi.Model.Game;
import com.ragul.adsplayapi.Model.GameUser;
import com.ragul.adsplayapi.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameUserRepository extends JpaRepository<GameUser, Long> {
    List<GameUser> findAllByGame(Game game);
    List<GameUser> findAllByUser(User user);
}
