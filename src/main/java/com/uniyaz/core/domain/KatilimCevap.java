package com.uniyaz.core.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * @author HAKAN DERELİ
 * @since 5.xxx.x
 */
@Entity
@Table(name = "KATILIM_CEVAP")
public class KatilimCevap extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_KATILIM" , foreignKey = @ForeignKey(name = "FK_KATILIM_CEVAP_KATILIM"))
    private Katilim katilim;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_SORU", foreignKey = @ForeignKey(name = "FK_KATILIM_SORU"))
    private Soru soru;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_SECENEK", foreignKey = @ForeignKey(name = "FK_KATILIM_SECENEK"))
    private Secenek secenek;

    @Column(name = "CEVAP_AS_STR", nullable = false, length = 3000)
    @NotNull
    private String cevap_as_str;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Katilim getKatilim() {
        return katilim;
    }

    public void setKatilim(Katilim katilim) {
        this.katilim = katilim;
    }

    public Soru getSoru() {
        return soru;
    }

    public void setSoru(Soru soru) {
        this.soru = soru;
    }

    public Secenek getSecenek() {
        return secenek;
    }

    public void setSecenek(Secenek secenek) {
        this.secenek = secenek;
    }

    public String getCevap_as_str() {
        return cevap_as_str;
    }

    public void setCevap_as_str(String cevap_as_str) {
        this.cevap_as_str = cevap_as_str;
    }

    @Override
    public String toString() {
        return id.toString();
    }
}
