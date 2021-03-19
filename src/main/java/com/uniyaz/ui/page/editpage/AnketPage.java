package com.uniyaz.ui.page.editpage;

import com.uniyaz.core.domain.Anket;
import com.uniyaz.core.service.AnketService;
import com.uniyaz.ui.component.button.MySaveButton;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.fieldgroup.PropertyId;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.*;

/**
 * @author HAKAN DERELİ
 * @since 5.xxx.x
 */
public class AnketPage extends VerticalLayout {

    @PropertyId("id")
    private TextField idField;

    @PropertyId("ad")
    private TextField adField;

    private FormLayout mainLayout;

    private BeanItem<Anket> anketBeanItem;
    private FieldGroup binder;
    private MySaveButton mySaveButton;
    private Window myAddOrUpdateWindow;

    public AnketPage() {
        this(new Anket());
    }

    public AnketPage(Anket anket) {
        buildMainLayout();
        addComponent(mainLayout);
        setComponentAlignment(mainLayout, Alignment.MIDDLE_CENTER);

        anketBeanItem = new BeanItem<Anket>(anket);
        binder = new FieldGroup(anketBeanItem);
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

                    Anket anket = anketBeanItem.getBean();
                    AnketService anketService = new AnketService();
                    anketService.saveAnket(anket);
                    Notification.show(anket.getAd() + " Adlı Anket Kaydedildi !");

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
}
