package com.uniyaz.ui.page.editpage;

import com.uniyaz.core.domain.Anket;
import com.uniyaz.core.domain.Panel;
import com.uniyaz.core.service.PanelService;
import com.uniyaz.ui.component.button.MySaveButton;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.fieldgroup.PropertyId;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.*;

/**
 * @author HAKAN DERELİ
 * @since 5.xxx.x
 */
public class PanelPage extends VerticalLayout {


    @PropertyId("id")
    private TextField idField;

    @PropertyId("baslik")
    private TextField baslikField;

    //    @PropertyId("anket")
    private Anket anket;

    private FormLayout mainLayout;

    private BeanItem<Panel> panelBeanItem;
    private FieldGroup binder;
    private MySaveButton mySaveButton;
    private Window myAddOrUpdateWindow;

    public PanelPage(Anket anket) {
        this.anket = anket;
        Panel panel = new Panel();
        panel.setAnket(anket);
        fillViewByPanel(panel);
    }

    public PanelPage(Panel panel) {
//        setSizeFull();
        fillViewByPanel(panel);
    }

    private void fillViewByPanel(Panel panel) {
        buildMainLayout();
        addComponent(mainLayout);
        setComponentAlignment(mainLayout, Alignment.MIDDLE_CENTER);

        panelBeanItem = new BeanItem<Panel>(panel);
        binder = new FieldGroup(panelBeanItem);
        binder.bindMemberFields(this);
    }

    private void buildMainLayout() {
        mainLayout = new FormLayout();
        mainLayout.setSizeUndefined();

        idField = new TextField();
        idField.setCaption("ID");
        idField.setEnabled(false);
        mainLayout.addComponent(idField);

        baslikField = new TextField();
        baslikField.setCaption("AD");
        mainLayout.addComponent(baslikField);

        mySaveButton = new MySaveButton();
        mySaveButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                try {
                    binder.commit();

                    Panel panel = panelBeanItem.getBean();
                    PanelService panelService = new PanelService();
                    panelService.savePanel(panel);
                    Notification.show(panel.getBaslik() + " Adlı Panel Kaydedildi !");

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

    public Anket getAnket() {
        return anket;
    }

    public void setAnket(Anket anket) {
        this.anket = anket;
    }
}
