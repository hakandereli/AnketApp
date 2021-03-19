package com.uniyaz.ui.page.listepage;

import com.uniyaz.core.domain.Anket;
import com.uniyaz.core.service.AnketService;
import com.uniyaz.ui.component.button.MyAddButton;
import com.uniyaz.ui.component.button.MyDeleteButton;
import com.uniyaz.ui.component.button.MyEditButton;
import com.uniyaz.ui.page.editpage.AnketPage;
import com.vaadin.data.Container;
import com.vaadin.data.Item;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.ui.*;

import java.util.List;

/**
 * @author HAKAN DERELİ
 * @since 5.xxx.x
 */
public class AnketListePage extends VerticalLayout {
    private VerticalLayout mainLayout;
    private Table table;
    private Container container;
    private MyAddButton myAddButton;

    public AnketListePage() {
        setSizeFull();

        buildMainLayout();
        addComponent(mainLayout);

        setComponentAlignment(mainLayout, Alignment.MIDDLE_CENTER);

        fillTable();
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
        myAddButton.setCaption("Anket Ekle");
        myAddButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                AnketPage anketPage = new AnketPage();
                anketAddOrUpdateWindow(anketPage);
            }
        });
    }

    private void builTable() {
        table = new Table();

        buildContainer();
        table.setContainerDataSource(container);
        table.setSelectable(true);
        table.setColumnHeaders("ID", "AD", "", "");
        table.setPageLength(0);
    }

    private void buildContainer() {

        container = new IndexedContainer();
        container.addContainerProperty("id", Long.class, null);
        container.addContainerProperty("ad", String.class, null);
        container.addContainerProperty("guncelle", Button.class, null);
        container.addContainerProperty("sil", Button.class, null);
    }

    private void fillTable() {
        AnketService anketService = new AnketService();
        List<Anket> anketList = anketService.findAllHql();

        container.removeAllItems();
        for (Anket anket : anketList) {
            Item item = container.addItem(anket);
            item.getItemProperty("id").setValue(anket.getId());
            item.getItemProperty("ad").setValue(anket.getAd());

            MyEditButton guncelle = builEditButton(anket);
            item.getItemProperty("guncelle").setValue(guncelle);

            MyDeleteButton sil = buildDeleteButton(anket);
            item.getItemProperty("sil").setValue(sil);
        }

    }

    private MyEditButton builEditButton(Anket anket) {
        MyEditButton myEditButton = new MyEditButton();
        myEditButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                AnketPage anketPage = new AnketPage(anket);
                anketAddOrUpdateWindow(anketPage);
            }
        });
        return myEditButton;
    }

    private void anketAddOrUpdateWindow(AnketPage anketPage) {
        Window addOrUpdateWindow = new Window();

        if (anketPage.getIdField().getValue() != null) {
            addOrUpdateWindow.setCaption("Anket Güncelleme");
        } else {
            addOrUpdateWindow.setCaption("Anket Ekleme");
        }

        anketPage.setMyAddOrUpdateWindow(addOrUpdateWindow);
        anketPage.setMargin(true);

        addOrUpdateWindow.setContent(anketPage);
        addOrUpdateWindow.center();
        addOrUpdateWindow.addCloseListener(new Window.CloseListener() {
            @Override
            public void windowClose(Window.CloseEvent closeEvent) {
                fillTable();
            }
        });
        UI.getCurrent().addWindow(addOrUpdateWindow);
    }

    private MyDeleteButton buildDeleteButton(Anket anket) {
        MyDeleteButton myDeleteButton = new MyDeleteButton();
        myDeleteButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                try {
                    AnketService anketService = new AnketService();
                    anketService.deleteAnket(anket);
                    Notification.show("Silme Başarılı");
                    // TODO: 19.03.2021 Anket Silindiğinde Container Page deki Anket, Panel Ve Soru Ya Null Verilmeli
                } catch (Exception e) {
                    Notification.show(e.getMessage());
                }

                fillTable();
            }
        });
        return myDeleteButton;
    }

    public Table getTable() {
        return table;
    }
}
