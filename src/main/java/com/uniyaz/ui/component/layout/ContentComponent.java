package com.uniyaz.ui.component.layout;

import com.vaadin.ui.Component;
import com.vaadin.ui.VerticalLayout;

/**
 * @author HAKAN DERELİ
 * @since 5.xxx.x
 */
public class ContentComponent extends VerticalLayout {
    public ContentComponent() {
        setSizeFull();
        buildContentLayout();
    }

    private void buildContentLayout() {
    }

    public void setContent(Component component) {
        this.removeAllComponents();
        addComponent(component);
    }
}
