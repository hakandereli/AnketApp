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
@Table(name = "KATILIM")
public class Katilim extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_KATILIMCI", foreignKey = @ForeignKey(name = "FK_KATILIM_KATILIMCI"))
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Katilimci katilimci;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_ANKET", foreignKey = @ForeignKey(name = "FK_KATILIM_ANKET"))
    private Anket anket;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Katilimci getKatilimci() {
        return katilimci;
    }

    public void setKatilimci(Katilimci katilimci) {
        this.katilimci = katilimci;
    }

    public Anket getAnket() {
        return anket;
    }

    public void setAnket(Anket anket) {
        this.anket = anket;
    }

    @Override
    public String toString() {
        return id.toString();
    }
}
