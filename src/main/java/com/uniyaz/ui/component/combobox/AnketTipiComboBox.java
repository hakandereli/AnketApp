package com.uniyaz.ui.component.combobox;

import com.uniyaz.core.domain.Anket;
import com.uniyaz.core.enums.EnumSoruTipi;
import com.uniyaz.core.service.AnketService;
import com.vaadin.data.Item;
import com.vaadin.ui.ComboBox;

import java.util.List;

/**
 * @author HAKAN DERELİ
 * @since 5.xxx.x
 */
public class AnketTipiComboBox extends ComboBox {
    private AnketService anketService;

    public AnketTipiComboBox() {
        fillComboBox();
    }

    private void fillComboBox() {
        anketService = new AnketService();
        List<Anket> anketList = anketService.findAllHql();
        for (Anket anket : anketList) {
            Item item = addItem(anket);
        }
    }
}
