package com.ragul.adsplayapi.Model;

import com.ragul.adsplayapi.util.AdsType;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Data
@Entity
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String name;
    private String description;
    @NotBlank
    private String gameType;
    @NotBlank
    private String imageUrl;
    @NotBlank
    private String bannerImageUrl;
    private int playCont=0;
    private int highScore=0;
    @ManyToOne
    @JoinColumn
    private Company company;
    private AdsType adsType;
    private boolean enable = true;
}
