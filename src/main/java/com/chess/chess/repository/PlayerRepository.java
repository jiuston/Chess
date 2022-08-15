package com.chess.chess.repository;

import com.chess.chess.modelo.player.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlayerRepository extends JpaRepository<Player, String> {

    List<Player> findAllByNicknameLike(String value);
}
