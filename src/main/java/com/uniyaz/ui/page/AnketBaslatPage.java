package com.uniyaz.ui.page;

import com.uniyaz.ui.MySiteUI;
import com.uniyaz.ui.component.button.MyAddButton;
import com.uniyaz.ui.component.button.MySaveButton;
import com.uniyaz.ui.component.combobox.AnketTipiComboBox;
import com.uniyaz.ui.component.layout.ContentComponent;
import com.vaadin.ui.*;

/**
 * @author HAKAN DERELİ
 * @since 5.xxx.x
 */
public class AnketBaslatPage extends VerticalLayout {

    private TextField katilimciMailField;

    private AnketTipiComboBox anketTipiComboBox;

    private FormLayout mainLayout;

    private MyAddButton myAddButton;

    private ContentComponent contentComponent;

    public AnketBaslatPage() {
        buildMainLayout();
        addComponent(mainLayout);
        setComponentAlignment(mainLayout, Alignment.MIDDLE_CENTER);
    }

    private void buildMainLayout() {
        mainLayout = new FormLayout();
        mainLayout.setSizeUndefined();

        katilimciMailField = new TextField();
        katilimciMailField.setCaption("KATILIMCI MAİL :");
        mainLayout.addComponent(katilimciMailField);

        anketTipiComboBox = new AnketTipiComboBox();
        anketTipiComboBox.setCaption("ANKET : ");
        mainLayout.addComponent(anketTipiComboBox);

        myAddButton = new MyAddButton();
        myAddButton.setCaption("Anket Baslat");
        myAddButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                MySiteUI mySiteUI = (MySiteUI) UI.getCurrent();
                contentComponent = mySiteUI.getContentComponent();

                AnketDoldurPage anketDoldurPage = new AnketDoldurPage();
                contentComponent.setContent(anketDoldurPage);
            }
        });
        mainLayout.addComponent(myAddButton);
    }
}
