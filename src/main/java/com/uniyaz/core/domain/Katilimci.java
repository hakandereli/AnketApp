package com.uniyaz.core.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * @author HAKAN DERELİ
 * @since 5.xxx.x
 */
@Entity
@Table(name = "KATILIMCI")
public class Katilimci extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "MAIL", nullable = false, length = 150)
    @NotNull
    private String mail;


    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    @Override
    public String toString() {
        return id.toString();
    }
}
