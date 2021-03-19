package com.uniyaz.ui;

import com.uniyaz.ui.component.layout.ContentComponent;
import com.uniyaz.ui.component.layout.FooterComponent;
import com.uniyaz.ui.component.layout.HeaderComponent;
import com.uniyaz.ui.component.layout.MenuComponent;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Widgetset;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

/**
 * This UI is the application entry point. A UI may either represent a browser window
 * (or tab) or some part of a html page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@Theme("mytheme")
@Widgetset("com.uniyaz.MyAppWidgetset")
public class MySiteUI extends UI {
    private VerticalLayout mainLayout;
    private ContentComponent contentComponent;

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        buildMainLayout();
        setContent(mainLayout);
    }

    private void buildMainLayout() {
        mainLayout = new VerticalLayout();
        mainLayout.setSizeFull();

        contentComponent = new ContentComponent();

        HeaderComponent headerComponent = new HeaderComponent();
        mainLayout.addComponent(headerComponent);

        MenuComponent menuComponent = new MenuComponent();
        mainLayout.addComponent(menuComponent);

        mainLayout.addComponent(contentComponent);

        FooterComponent footerComponent = new FooterComponent();
        mainLayout.addComponent(footerComponent);

        mainLayout.setExpandRatio(headerComponent, 1.8f);
        mainLayout.setExpandRatio(menuComponent, 0.5f);
        mainLayout.setExpandRatio(contentComponent, 7f);
        mainLayout.setExpandRatio(footerComponent, 0.5f);

    }

    public ContentComponent getContentComponent() {
        return contentComponent;
    }

    public void setContentComponent(ContentComponent contentComponent) {
        this.contentComponent = contentComponent;
    }
}
