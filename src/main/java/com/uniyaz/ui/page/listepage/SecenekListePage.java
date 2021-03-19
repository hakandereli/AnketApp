package com.uniyaz.ui.page.listepage;

import com.uniyaz.core.domain.Secenek;
import com.uniyaz.core.domain.Soru;
import com.uniyaz.core.service.SecenekService;
import com.uniyaz.ui.component.button.MyAddButton;
import com.uniyaz.ui.component.button.MyDeleteButton;
import com.uniyaz.ui.component.button.MyEditButton;
import com.uniyaz.ui.page.editpage.SecenekPage;
import com.vaadin.data.Container;
import com.vaadin.data.Item;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.ui.*;

import java.util.List;

/**
 * @author HAKAN DERELİ
 * @since 5.xxx.x
 */
public class SecenekListePage extends VerticalLayout {
    private VerticalLayout mainLayout;
    private Table table;
    private Container container;
    private MyAddButton myAddButton;
    private Soru soru;

    public SecenekListePage() {
    }

    public SecenekListePage(Soru soru) {
        this.soru = soru;

        setSizeFull();

        buildMainLayout();
        addComponent(mainLayout);

        setComponentAlignment(mainLayout, Alignment.MIDDLE_CENTER);

    }

    private void buildMainLayout() {
        mainLayout = new VerticalLayout();
        mainLayout.setSizeUndefined();
        mainLayout.setMargin(true);

        buildMyAddButton();
        mainLayout.addComponent(myAddButton);

        builTable();
        mainLayout.addComponent(table);

    }

    private void buildMyAddButton() {
        myAddButton = new MyAddButton();
        myAddButton.setCaption("Secenek Ekle");
        myAddButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                SecenekPage secenekPage = new SecenekPage(soru);
                secenekAddOrUpdateWindow(secenekPage);
            }

        });
    }

    private void builTable() {
        table = new Table();

        buildContainer();
        table.setContainerDataSource(container);
        table.setSelectable(true);
        table.setColumnHeaders("ID", "AD", "SORU", "", "");
        table.setPageLength(0);
    }

    private void buildContainer() {

        container = new IndexedContainer();
        container.addContainerProperty("id", Long.class, null);
        container.addContainerProperty("ad", String.class, null);
        container.addContainerProperty("soru", Long.class, null);
        container.addContainerProperty("guncelle", Button.class, null);
        container.addContainerProperty("sil", Button.class, null);
    }

    public void fillTable(Soru soru) {
        SecenekService secenekService = new SecenekService();
        List<Secenek> secenekList = secenekService.findBySoruId(soru.getId());
        container.removeAllItems();
        for (Secenek secenek : secenekList) {
            Item item = container.addItem(secenek);
            item.getItemProperty("id").setValue(secenek.getId());
            item.getItemProperty("ad").setValue(secenek.getAd());
            item.getItemProperty("soru").setValue(secenek.getSoru().getId());

            MyEditButton guncelle = builEditButton(secenek);
            item.getItemProperty("guncelle").setValue(guncelle);

            MyDeleteButton sil = buildDeleteButton(secenek);
            item.getItemProperty("sil").setValue(sil);
        }
    }

    private MyEditButton builEditButton(Secenek secenek) {
        MyEditButton myEditButton = new MyEditButton();
        myEditButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                secenek.setSoru(soru);
                SecenekPage secenekPage = new SecenekPage(secenek);
                secenekAddOrUpdateWindow(secenekPage);
            }
        });
        return myEditButton;
    }

    private void secenekAddOrUpdateWindow(SecenekPage secenekPage) {
        Window addOrUpdateWindow = new Window();

        if (secenekPage.getIdField().getValue() != null) {
            addOrUpdateWindow.setCaption("Secenek Güncelleme");
        } else {
            addOrUpdateWindow.setCaption("Secenek Ekleme");
        }

        secenekPage.setMyAddOrUpdateWindow(addOrUpdateWindow);
        secenekPage.setMargin(true);

        addOrUpdateWindow.setContent(secenekPage);
        addOrUpdateWindow.center();
        addOrUpdateWindow.addCloseListener(new Window.CloseListener() {
            @Override
            public void windowClose(Window.CloseEvent closeEvent) {
                fillTable(soru);
            }
        });
        UI.getCurrent().addWindow(addOrUpdateWindow);
    }

    private MyDeleteButton buildDeleteButton(Secenek secenek) {
        MyDeleteButton myDeleteButton = new MyDeleteButton();
        myDeleteButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                try {
                    SecenekService secenekService = new SecenekService();
                    secenek.setSoru(soru);
                    secenekService.deleteSecenek(secenek);
                    Notification.show("Silme Başarılı");
                } catch (Exception e) {
                    Notification.show(e.getMessage());
                }

                fillTable(soru);
            }
        });
        return myDeleteButton;
    }

    public Soru getSoru() {
        return soru;
    }

    public void setSoru(Soru soru) {
        this.soru = soru;
    }
}
