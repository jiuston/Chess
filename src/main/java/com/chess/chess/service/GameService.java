package com.chess.chess.service;

import com.chess.chess.modelo.game.Game;
import com.chess.chess.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameService extends BaseService<Game, String, GameRepository>{

    @Autowired
    private GameRepository gameRepository;

}
