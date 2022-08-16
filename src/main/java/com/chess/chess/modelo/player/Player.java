package com.chess.chess.modelo.player;

import com.chess.chess.utils.StringPrefixedSequenceIdGenerator;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Player")
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "player_seq")
    @GenericGenerator(
            name = "player_seq",
            strategy = "com.chess.chess.utils.StringPrefixedSequenceIdGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = StringPrefixedSequenceIdGenerator.INCREMENT_PARAM, value = "1"),
                    @org.hibernate.annotations.Parameter(name = StringPrefixedSequenceIdGenerator.VALUE_PREFIX_PARAMETER, value = "PLAYER"),
                    @org.hibernate.annotations.Parameter(name = StringPrefixedSequenceIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%06d")
            })
    @Column(name = "id_player")
    private String idPlayer;

    @NotEmpty
    @Column(name = "nickname", nullable = false, unique = true)
    private String nickname;

    @Email
    @NotEmpty
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "partidas", nullable = false)
    private Integer gamesPlayed;

    @Column(name = "ganadas", nullable = false)
    private Integer gamesWon;

    @Column(name = "estado", nullable = false)
    @Enumerated(value = EnumType.STRING)
    private Estado estado;

}
