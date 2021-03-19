package com.uniyaz.ui.page.listepage;

import com.uniyaz.core.domain.Panel;
import com.uniyaz.core.domain.Soru;
import com.uniyaz.core.enums.EnumSoruTipi;
import com.uniyaz.core.service.SoruService;
import com.uniyaz.ui.component.button.MyAddButton;
import com.uniyaz.ui.component.button.MyDeleteButton;
import com.uniyaz.ui.component.button.MyEditButton;
import com.uniyaz.ui.page.editpage.SoruPage;
import com.vaadin.data.Container;
import com.vaadin.data.Item;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.ui.*;

import java.util.List;

/**
 * @author HAKAN DERELİ
 * @since 5.xxx.x
 */
public class SoruListePage extends VerticalLayout {
    private VerticalLayout mainLayout;
    private Table table;
    private Container container;
    private MyAddButton myAddButton;
    private Panel panel;

    public SoruListePage() {
    }

    public SoruListePage(Panel panel) {
        this.panel = panel;

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
        myAddButton.setCaption("Soru Ekle");
        myAddButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                SoruPage soruPage = new SoruPage(panel);
                soruAddOrUpdateWindow(soruPage);
            }

        });
    }

    private void builTable() {
        table = new Table();

        buildContainer();
        table.setContainerDataSource(container);
        table.setSelectable(true);
        table.setColumnHeaders("ID", "AD", "SORU TIPI", "PANEL", "", "");
        table.setPageLength(0);
    }

    private void buildContainer() {

        container = new IndexedContainer();
        container.addContainerProperty("id", Long.class, null);
        container.addContainerProperty("ad", String.class, null);
        container.addContainerProperty("enumSoruTipi", EnumSoruTipi.class, null);
        container.addContainerProperty("panel", Long.class, null);
        container.addContainerProperty("guncelle", Button.class, null);
        container.addContainerProperty("sil", Button.class, null);
    }

    public void fillTable(Panel panel) {
        SoruService soruService = new SoruService();
        List<Soru> soruList = soruService.findByPanelId(panel.getId());
        container.removeAllItems();
        for (Soru soru : soruList) {
            Item item = container.addItem(soru);
            item.getItemProperty("id").setValue(soru.getId());
            item.getItemProperty("ad").setValue(soru.getAd());
            item.getItemProperty("enumSoruTipi").setValue(soru.getEnumSoruTipi());
            item.getItemProperty("panel").setValue(soru.getPanel().getId());

            MyEditButton guncelle = builEditButton(soru);
            item.getItemProperty("guncelle").setValue(guncelle);

            MyDeleteButton sil = buildDeleteButton(soru);
            item.getItemProperty("sil").setValue(sil);
        }
    }

    private MyEditButton builEditButton(Soru soru) {
        MyEditButton myEditButton = new MyEditButton();
        myEditButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                soru.setPanel(panel);
                SoruPage soruPage = new SoruPage(soru);
                soruAddOrUpdateWindow(soruPage);
            }
        });
        return myEditButton;
    }

    private void soruAddOrUpdateWindow(SoruPage soruPage) {
        Window addOrUpdateWindow = new Window();

        if (soruPage.getIdField().getValue() != null) {
            addOrUpdateWindow.setCaption("Soru Güncelleme");
        } else {
            addOrUpdateWindow.setCaption("Soru Ekleme");
        }

        soruPage.setMyAddOrUpdateWindow(addOrUpdateWindow);
        soruPage.setMargin(true);

        addOrUpdateWindow.setContent(soruPage);
        addOrUpdateWindow.center();
        addOrUpdateWindow.addCloseListener(new Window.CloseListener() {
            @Override
            public void windowClose(Window.CloseEvent closeEvent) {
                fillTable(panel);
            }
        });
        UI.getCurrent().addWindow(addOrUpdateWindow);
    }

    private MyDeleteButton buildDeleteButton(Soru soru) {
        MyDeleteButton myDeleteButton = new MyDeleteButton();
        myDeleteButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                try {
                    SoruService soruService = new SoruService();
                    soru.setPanel(panel);
                    soruService.deleteSoru(soru);
                    Notification.show("Silme Başarılı");
                    // TODO: 19.03.2021 Soru Silindiğinde Container Page deki Soru Ya Null Verilmeli
                } catch (Exception e) {
                    Notification.show(e.getMessage());
                }

                fillTable(panel);
            }
        });
        return myDeleteButton;
    }

    public Panel getPanel() {
        return panel;
    }

    public void setPanel(Panel panel) {
        this.panel = panel;
    }

    public Table getTable() {
        return table;
    }

    public void setTable(Table table) {
        this.table = table;
    }
}
