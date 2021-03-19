package com.uniyaz.core.enums;

/**
 * @author HAKAN DERELİ
 * @since 5.xxx.x
 */
public enum EnumSoruTipi {
    TextField("METIN GIRISI"),
    RadioButton("TEK SECIM"),
    CheckBox("COKLU SECIM"),
    DateField("TARIH");

    private final String soruTipi;

    EnumSoruTipi(String soruTipi) {
        this.soruTipi = soruTipi;
    }

    @Override
    public String toString() {
        return soruTipi;
    }
}
