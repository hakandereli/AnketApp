package com.uniyaz.ui.component.button;

import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Button;
import com.vaadin.ui.themes.ValoTheme;

/**
 * @author HAKAN DERELİ
 * @since 5.xxx.x
 */
public class MyAddButton extends Button {
    public MyAddButton() {
        setWidth(100, Unit.PERCENTAGE);
        setIcon(FontAwesome.PLUS_CIRCLE);
        addStyleName(ValoTheme.BUTTON_FRIENDLY);
    }
}
