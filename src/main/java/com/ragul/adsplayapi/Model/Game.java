package com.ragul.adsplayapi.Model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@Entity
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String name;
    @NotBlank
    private String imageUrl;
    @NotBlank
    private String bannerImageUrl;
    private int playCont=0;
    private int highScore=0;
    @ManyToOne
    @JoinColumn
    private Company company;
}
