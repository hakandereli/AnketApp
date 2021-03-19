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
@Table(name = "PANEL")
public class Panel extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "BASLIK", nullable = false, length = 150)
    @NotNull
    private String baslik;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_ANKET", foreignKey = @ForeignKey(name = "FK_PANEL_ANKET"))
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Anket anket;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBaslik() {
        return baslik;
    }

    public void setBaslik(String baslik) {
        this.baslik = baslik;
    }

    public Anket getAnket() {
        return anket;
    }

    public void setAnket(Anket anket) {
        this.anket = anket;
    }
}
