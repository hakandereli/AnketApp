package com.uniyaz.core.domain;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * @author HAKAN DERELİ
 * @since 5.xxx.x
 */
@Entity
@Table(name = "SECENEK")
public class Secenek extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "AD", length = 200)
    @NotNull
    private String ad;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_SORU", foreignKey = @ForeignKey(name = "FK_SORU_SECENEK"))
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Soru soru;

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

    public Soru getSoru() {
        return soru;
    }

    public void setSoru(Soru soru) {
        this.soru = soru;
    }
}
