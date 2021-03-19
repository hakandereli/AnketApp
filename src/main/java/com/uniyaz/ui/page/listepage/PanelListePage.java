package com.uniyaz.ui.page.listepage;

import com.uniyaz.core.domain.Anket;
import com.uniyaz.core.domain.Panel;
import com.uniyaz.core.service.PanelService;
import com.uniyaz.ui.component.button.MyAddButton;
import com.uniyaz.ui.component.button.MyDeleteButton;
import com.uniyaz.ui.component.button.MyEditButton;
import com.uniyaz.ui.page.editpage.PanelPage;
import com.vaadin.data.Container;
import com.vaadin.data.Item;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.ui.*;

import java.util.List;

/**
 * @author HAKAN DERELİ
 * @since 5.xxx.x
 */
public class PanelListePage extends VerticalLayout {
    private VerticalLayout mainLayout;
    private Table table;
    private Container container;
    private MyAddButton myAddButton;
    private Anket anket;

    public PanelListePage() {
    }

    public PanelListePage(Anket anket) {
        this.anket = anket;

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
        myAddButton.setCaption("Panel Ekle");
        myAddButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                PanelPage panelPage = new PanelPage(anket);
                panelAddOrUpdateWindow(panelPage);
            }

        });
    }

    private void builTable() {
        table = new Table();

        buildContainer();
        table.setContainerDataSource(container);
        table.setSelectable(true);
        table.setColumnHeaders("ID", "BASLIK", "ANKET", "", "");
        table.setPageLength(0);
    }

    private void buildContainer() {

        container = new IndexedContainer();
        container.addContainerProperty("id", Long.class, null);
        container.addContainerProperty("baslik", String.class, null);
        container.addContainerProperty("anket", Long.class, null);
        container.addContainerProperty("guncelle", Button.class, null);
        container.addContainerProperty("sil", Button.class, null);
    }

    public void fillTable(Anket anket) {
        PanelService panelService = new PanelService();
        List<Panel> panelList = panelService.findByAnketId(anket.getId());
        container.removeAllItems();
        for (Panel panel : panelList) {
            Item item = container.addItem(panel);
            item.getItemProperty("id").setValue(panel.getId());
            item.getItemProperty("baslik").setValue(panel.getBaslik());
            item.getItemProperty("anket").setValue(panel.getAnket().getId());

            MyEditButton guncelle = builEditButton(panel);
            item.getItemProperty("guncelle").setValue(guncelle);

            MyDeleteButton sil = buildDeleteButton(panel);
            item.getItemProperty("sil").setValue(sil);
        }
    }

    private MyEditButton builEditButton(Panel panel) {
        MyEditButton myEditButton = new MyEditButton();
        myEditButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                panel.setAnket(anket);
                PanelPage panelPage = new PanelPage(panel);
                panelAddOrUpdateWindow(panelPage);
            }
        });
        return myEditButton;
    }

    private void panelAddOrUpdateWindow(PanelPage panelPage) {
        Window addOrUpdateWindow = new Window();

        if (panelPage.getIdField().getValue() != null) {
            addOrUpdateWindow.setCaption("Panel Güncelleme");
        } else {
            addOrUpdateWindow.setCaption("Panel Ekleme");
        }

        panelPage.setMyAddOrUpdateWindow(addOrUpdateWindow);
        panelPage.setMargin(true);

        addOrUpdateWindow.setContent(panelPage);
        addOrUpdateWindow.center();
        addOrUpdateWindow.addCloseListener(new Window.CloseListener() {
            @Override
            public void windowClose(Window.CloseEvent closeEvent) {
                fillTable(anket);
            }
        });
        UI.getCurrent().addWindow(addOrUpdateWindow);
    }

    private MyDeleteButton buildDeleteButton(Panel panel) {
        MyDeleteButton myDeleteButton = new MyDeleteButton();
        myDeleteButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                try {
                    PanelService panelService = new PanelService();
                    panel.setAnket(anket);
                    panelService.deletePanel(panel);
                    Notification.show("Silme Başarılı");
                    // TODO: 19.03.2021 Panel Silindiğinde Container Page deki Panel ve Soru Ya Null Verilmeli
                } catch (Exception e) {
                    Notification.show(e.getMessage());
                }

                fillTable(anket);
            }
        });
        return myDeleteButton;
    }

    public Table getTable() {
        return table;
    }

    public Anket getAnket() {
        return anket;
    }

    public void setAnket(Anket anket) {
        this.anket = anket;
    }
}
