package com.chess.chess.service;

import com.chess.chess.modelo.player.Player;
import com.chess.chess.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerService extends BaseService<Player, String, PlayerRepository>{

    @Autowired
    private PlayerRepository playerRepository;

    public List<Player> findAllByNicknameLike(String value) {
        return playerRepository.findAllByNicknameLike(value);
    }
}
