package com.ragul.adsplayapi.Model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double amount;
    private Date dateTime;
    @OneToOne
    private Game game;
}
