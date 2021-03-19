package com.uniyaz.core.domain;

import com.uniyaz.core.enums.EnumSoruTipi;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * @author HAKAN DERELİ
 * @since 5.xxx.x
 */
@Entity
@Table(name = "SORU")
public class Soru extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "AD", nullable = false, length = 150)
    @NotNull
    private String ad;

    @Column(length = 50)
    @Enumerated(EnumType.STRING)
    private EnumSoruTipi enumSoruTipi;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_PANEL", foreignKey = @ForeignKey(name = "FK_SORU_PANEL"))
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Panel panel;

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

    public EnumSoruTipi getEnumSoruTipi() {
        return enumSoruTipi;
    }

    public void setEnumSoruTipi(EnumSoruTipi enumSoruTipi) {
        this.enumSoruTipi = enumSoruTipi;
    }

    public Panel getPanel() {
        return panel;
    }

    public void setPanel(Panel panel) {
        this.panel = panel;
    }
}
