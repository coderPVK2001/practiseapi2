package com.practiseapi2.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "cricketers")
public class Cricketers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "player_name", nullable = false, unique = true, length = 50)
    private String playerName;

    @Column(name = "stats", nullable = false)
    private Integer stats;

    @Column(name = "country_name", nullable = false, length = 50)
    private String countryName;

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public Integer getStats() {
        return stats;
    }

    public void setStats(Integer stats) {
        this.stats = stats;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}