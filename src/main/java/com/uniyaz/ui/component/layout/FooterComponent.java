package com.uniyaz.ui.component.layout;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;

/**
 * @author HAKAN DERELİ
 * @since 5.xxx.x
 */
public class FooterComponent extends HorizontalLayout {
    private HorizontalLayout mainLayout;
    private ContentComponent contentComponent;
    private Label footerLabel;

    public FooterComponent() {
        setSizeFull();

        buildFooterLayout();
        addComponent(mainLayout);

        setComponentAlignment(mainLayout, Alignment.MIDDLE_CENTER);
    }

    private void buildFooterLayout() {
        mainLayout = new HorizontalLayout();

        footerLabel = new Label();
        footerLabel.setCaption("@COPYRIGHT HAKAN DERELİ");
        footerLabel.setId("footerField");
        mainLayout.addComponents(footerLabel);
    }
}

