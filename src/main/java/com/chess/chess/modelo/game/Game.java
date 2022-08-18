package com.chess.chess.modelo.game;

import com.chess.chess.modelo.player.Player;
import com.chess.chess.utils.StringPrefixedSequenceIdGenerator;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.awt.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Game")
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "game_seq")
    @GenericGenerator(
            name = "game_seq",
            strategy = "com.chess.chess.utils.StringPrefixedSequenceIdGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = StringPrefixedSequenceIdGenerator.INCREMENT_PARAM, value = "1"),
                    @org.hibernate.annotations.Parameter(name = StringPrefixedSequenceIdGenerator.VALUE_PREFIX_PARAMETER, value = "GAME"),
                    @org.hibernate.annotations.Parameter(name = StringPrefixedSequenceIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%06d")
            })
    @Column(name = "id_game")
    private String idGame;

    @OneToOne
    @JoinColumn(name = "id_player1")
    private Player player1;

    @OneToOne
    @JoinColumn(name = "id_player2")
    private Player player2;

    @OneToOne
    @JoinColumn(name = "id_ganador")
    private Player ganador;

    @Column(name = "estado", nullable = false)
    @Enumerated(value = EnumType.STRING)
    private Estado estado;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "color_ganador")
    private Color colorGanador;

}
