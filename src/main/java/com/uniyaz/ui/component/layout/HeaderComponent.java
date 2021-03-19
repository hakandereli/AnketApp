package com.uniyaz.ui.component.layout;

import com.vaadin.server.ThemeResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;

/**
 * @author HAKAN DERELİ
 * @since 5.xxx.x
 */
public class HeaderComponent extends HorizontalLayout {
    private HorizontalLayout mainLayout;
    private Image logo;

    public HeaderComponent() {
        setSizeFull();

        buildHeaderLayout();
        addComponent(mainLayout);

        setComponentAlignment(mainLayout, Alignment.TOP_CENTER);
    }

    private void buildHeaderLayout() {
        mainLayout = new HorizontalLayout();

        ThemeResource resource = new ThemeResource("img/anketFoto.png");
        logo = new Image("", resource);
        logo.setWidth(100, Unit.PIXELS);
        logo.setHeight(100, Unit.PIXELS);
        mainLayout.addComponents(logo);
    }
}

