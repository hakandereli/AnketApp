package com.uniyaz.core.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * @author HAKAN DERELİ
 * @since 5.xxx.x
 */
@Entity
@Table(name = "ANKET")
public class Anket extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "AD", nullable = false, length = 100)
    @NotNull
    private String ad;


    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAd() {
        return ad;
    }

    public void setAd(String ad) {
        this.ad = ad;
    }

    @Override
    public String toString() {
        return ad;
    }
}
