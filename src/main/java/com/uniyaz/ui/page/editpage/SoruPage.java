package com.uniyaz.ui.page.editpage;

import com.uniyaz.core.domain.Panel;
import com.uniyaz.core.domain.Soru;
import com.uniyaz.core.service.SoruService;
import com.uniyaz.ui.component.button.MySaveButton;
import com.uniyaz.ui.component.combobox.SoruTipiComboBox;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.fieldgroup.PropertyId;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.*;

/**
 * @author HAKAN DERELİ
 * @since 5.xxx.x
 */
public class SoruPage extends VerticalLayout {

    @PropertyId("id")
    private TextField idField;

    @PropertyId("ad")
    private TextField adField;

    @PropertyId("enumSoruTipi")
    private SoruTipiComboBox soruTipiComboBox;

    //    @PropertyId("panel")
    private Panel panel;

    private FormLayout mainLayout;

    private BeanItem<Soru> soruBeanItem;
    private FieldGroup binder;
    private MySaveButton mySaveButton;
    private Window myAddOrUpdateWindow;

    public SoruPage(Panel panel) {
        this.panel = panel;
        Soru soru = new Soru();
        soru.setPanel(panel);
        fillViewBySoru(soru);
    }

    public SoruPage(Soru soru) {
        fillViewBySoru(soru);
    }

    private void fillViewBySoru(Soru soru) {
        buildMainLayout();
        addComponent(mainLayout);
        setComponentAlignment(mainLayout, Alignment.MIDDLE_CENTER);

        soruBeanItem = new BeanItem<Soru>(soru);
        binder = new FieldGroup(soruBeanItem);
        binder.bindMemberFields(this);
    }

    private void buildMainLayout() {
        mainLayout = new FormLayout();
        mainLayout.setSizeUndefined();

        idField = new TextField();
        idField.setCaption("ID");
        idField.setEnabled(false);
        mainLayout.addComponent(idField);

        adField = new TextField();
        adField.setCaption("AD");
        mainLayout.addComponent(adField);

        soruTipiComboBox = new SoruTipiComboBox();
        soruTipiComboBox.setCaption("Soru Tipi");
        mainLayout.addComponent(soruTipiComboBox);

        mySaveButton = new MySaveButton();
        mySaveButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                try {
                    binder.commit();

                    Soru soru = soruBeanItem.getBean();
                    SoruService soruService = new SoruService();
                    soruService.saveSoru(soru);
                    Notification.show(soru.getAd() + " Adlı Soru Kaydedildi !");

                    UI.getCurrent().getUI().removeWindow(getMyAddOrUpdateWindow());

                } catch (FieldGroup.CommitException e) {
                    Notification.show("Alanlar nesne ile uyumlu değil", Notification.Type.ERROR_MESSAGE);
                }

            }
        });
        mainLayout.addComponent(mySaveButton);
    }

    public Window getMyAddOrUpdateWindow() {
        return myAddOrUpdateWindow;
    }

    public void setMyAddOrUpdateWindow(Window myAddOrUpdateWindow) {
        this.myAddOrUpdateWindow = myAddOrUpdateWindow;
    }

    public TextField getIdField() {
        return idField;
    }

    public void setIdField(TextField idField) {
        this.idField = idField;
    }

    public Panel getPanel() {
        return panel;
    }

    public void setPanel(Panel panel) {
        this.panel = panel;
    }
}
