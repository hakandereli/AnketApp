package com.uniyaz.ui.component.combobox;

import com.uniyaz.core.enums.EnumSoruTipi;
import com.vaadin.data.Item;
import com.vaadin.ui.ComboBox;

/**
 * @author HAKAN DERELİ
 * @since 5.xxx.x
 */
public class SoruTipiComboBox extends ComboBox {
    public SoruTipiComboBox() {
        fillComboBox();
    }

    private void fillComboBox() {
        for (EnumSoruTipi enumSoruTipi : EnumSoruTipi.values()) {
            Item item = addItem(enumSoruTipi);
        }
    }
}
