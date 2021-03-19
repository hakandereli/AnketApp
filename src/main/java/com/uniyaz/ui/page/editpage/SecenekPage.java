package com.uniyaz.ui.page.editpage;

import com.uniyaz.core.domain.Secenek;
import com.uniyaz.core.domain.Soru;
import com.uniyaz.core.service.SecenekService;
import com.uniyaz.ui.component.button.MySaveButton;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.fieldgroup.PropertyId;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.*;

/**
 * @author HAKAN DERELİ
 * @since 5.xxx.x
 */
public class SecenekPage extends VerticalLayout {

    @PropertyId("id")
    private TextField idField;

    @PropertyId("ad")
    private TextField adField;

    //    @PropertyId("soru")
    private Soru soru;

    private FormLayout mainLayout;

    private BeanItem<Secenek> secenekBeanItem;
    private FieldGroup binder;
    private MySaveButton mySaveButton;
    private Window myAddOrUpdateWindow;

    public SecenekPage(Soru soru) {
        this.soru = soru;
        Secenek secenek = new Secenek();
        secenek.setSoru(soru);
        fillViewBySecenek(secenek);
    }

    public SecenekPage(Secenek secenek) {
        fillViewBySecenek(secenek);
    }

    private void fillViewBySecenek(Secenek secenek) {
        buildMainLayout();
        addComponent(mainLayout);
        setComponentAlignment(mainLayout, Alignment.MIDDLE_CENTER);

        secenekBeanItem = new BeanItem<Secenek>(secenek);
        binder = new FieldGroup(secenekBeanItem);
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

        mySaveButton = new MySaveButton();
        mySaveButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                try {
                    binder.commit();

                    Secenek secenek = secenekBeanItem.getBean();
                    SecenekService secenekService = new SecenekService();
                    secenekService.saveSecenek(secenek);
                    Notification.show(secenek.getAd() + " Adlı Secenek Kaydedildi !");

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

    public Soru getSoru() {
        return soru;
    }

    public void setSoru(Soru soru) {
        this.soru = soru;
    }
}
