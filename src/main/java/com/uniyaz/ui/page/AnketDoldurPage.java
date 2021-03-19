package com.uniyaz.ui.page;

import com.uniyaz.ui.MySiteUI;
import com.uniyaz.ui.component.button.MyAddButton;
import com.uniyaz.ui.component.combobox.AnketTipiComboBox;
import com.uniyaz.ui.component.layout.ContentComponent;
import com.vaadin.ui.*;

/**
 * @author HAKAN DERELİ
 * @since 5.xxx.x
 */
public class AnketDoldurPage extends VerticalLayout {

    private Panel anketPanel;
    private VerticalLayout mainLayout;

    private MyAddButton myAddButton;

    private ContentComponent contentComponent;

    public AnketDoldurPage() {
        buildMainLayout();
        addComponent(mainLayout);
        setComponentAlignment(mainLayout, Alignment.MIDDLE_CENTER);
    }

    private void buildMainLayout() {
        mainLayout = new VerticalLayout();
        mainLayout.setSizeUndefined();

        for (int i=0;i<=5;i++){
            anketPanel = new Panel("TEST PANEL");
            anketPanel.setSizeFull();
            anketPanel.setContent(new Label("TEST LABEL"));
            mainLayout.addComponent(anketPanel);
        }

        myAddButton = new MyAddButton();
        myAddButton.setCaption("Anket Kaydet");
        myAddButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                MySiteUI mySiteUI = (MySiteUI) UI.getCurrent();
                contentComponent = mySiteUI.getContentComponent();

                contentComponent.setContent(new Label("Anket Goruntuleme"));
            }
        });
        mainLayout.addComponent(myAddButton);
    }
}
