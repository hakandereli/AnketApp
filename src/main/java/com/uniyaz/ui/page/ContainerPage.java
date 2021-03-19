package com.uniyaz.ui.page;

import com.uniyaz.core.domain.Anket;
import com.uniyaz.core.domain.Panel;
import com.uniyaz.core.domain.Soru;
import com.uniyaz.ui.page.listepage.AnketListePage;
import com.uniyaz.ui.page.listepage.PanelListePage;
import com.uniyaz.ui.page.listepage.SecenekListePage;
import com.uniyaz.ui.page.listepage.SoruListePage;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;

/**
 * @author HAKAN DERELİ
 * @since 5.xxx.x
 */
public class ContainerPage extends VerticalLayout {
    private VerticalLayout mainLayout;

    private TabSheet tabSheet;

    private AnketListePage anketListePage;
    private PanelListePage panelListePage;
    private SoruListePage soruListePage;
    private SecenekListePage secenekListePage;

    private TabSheet.Tab anketTab;
    private TabSheet.Tab panelTab;
    private TabSheet.Tab soruTab;
    private TabSheet.Tab secenekTab;

    private Anket anket;
    private Panel panel;
    private Soru soru;


    public ContainerPage() {
        buildMainLayout();

        addComponent(mainLayout);
    }

    private void buildMainLayout() {
        addStyleName("tabMenu");

        mainLayout = new VerticalLayout();
        mainLayout.setSizeFull();

        buildTabSheet();
        mainLayout.addComponent(tabSheet);
    }

    private void buildTabSheet() {
        tabSheet = new TabSheet();

        tabSheet.addSelectedTabChangeListener(new TabSheet.SelectedTabChangeListener() {
            @Override
            public void selectedTabChange(TabSheet.SelectedTabChangeEvent selectedTabChangeEvent) {
                //Panel Tabı Seçildiğinde Anketi Seçili Olmalı
                if (selectedTabChangeEvent.getTabSheet().getSelectedTab() instanceof PanelListePage) {
                    if (anket == null) {
                        Notification.show("Anket Seçmelisiniz ! ", Notification.Type.WARNING_MESSAGE);
                        tabSheet.setSelectedTab(anketTab);
                    }
                    if (anket != null) {
                        panelListePage.setAnket(anket);
                        tabSheet.setSelectedTab(panelTab);
                        panelListePage.fillTable(anket);
                    }
                }

                //Soru Tabı Seçildiğinde Paneli Seçili Olmalı
                if (selectedTabChangeEvent.getTabSheet().getSelectedTab() instanceof SoruListePage) {
                    if (panel == null) {
                        Notification.show("Panel Seçmelisiniz ! ", Notification.Type.WARNING_MESSAGE);
                        tabSheet.setSelectedTab(panelTab);
                    }
                    if (panel != null) {
                        soruListePage.setPanel(panel);
                        tabSheet.setSelectedTab(soruTab);
                        soruListePage.fillTable(panel);
                    }
                }

                //Seçenek Tabı Seçildiğinde Sorusu Seçili Olmalı
                if (selectedTabChangeEvent.getTabSheet().getSelectedTab() instanceof SecenekListePage) {
                    if (soru == null) {
                        Notification.show("Soru Seçmelisiniz ! ", Notification.Type.WARNING_MESSAGE);
                        tabSheet.setSelectedTab(soruTab);
                    }
                    if (soru != null) {
                        secenekListePage.setSoru(soru);
                        tabSheet.setSelectedTab(secenekTab);
                        secenekListePage.fillTable(soru);
                    }
                }
            }
        });

        buildTabs();
    }

    private void buildTabs() {
        buildSecenekTab();
    }

    private void buildSecenekTab() {
        secenekListePage = new SecenekListePage(soru);
        buildSoruTab();
        secenekTab = tabSheet.addTab(secenekListePage);
        secenekTab.setIcon(FontAwesome.CREATIVE_COMMONS);
        secenekTab.setCaption("Secenek");
    }

    private void buildSoruTab() {
        soruListePage = new SoruListePage(panel);
        buildPanelTab();
        soruListePage.getTable().addItemClickListener(new ItemClickEvent.ItemClickListener() {
            @Override
            public void itemClick(ItemClickEvent itemClickEvent) {
                soru = (Soru) itemClickEvent.getItemId();
            }
        });
        soruTab = tabSheet.addTab(soruListePage);
        soruTab.setIcon(FontAwesome.QUESTION);
        soruTab.setCaption("Soru");
    }

    private void buildPanelTab() {
        panelListePage = new PanelListePage(anket);
        buildAnketTab();
        panelListePage.getTable().addItemClickListener(new ItemClickEvent.ItemClickListener() {
            @Override
            public void itemClick(ItemClickEvent itemClickEvent) {
                panel = (Panel) itemClickEvent.getItemId();
            }
        });
        panelTab = tabSheet.addTab(panelListePage);
        panelTab.setIcon(FontAwesome.DASHBOARD);
        panelTab.setCaption("Panel");
    }

    private void buildAnketTab() {
        anketListePage = new AnketListePage();
        anketTab = tabSheet.addTab(anketListePage);
        anketListePage.getTable().addItemClickListener(new ItemClickEvent.ItemClickListener() {
            @Override
            public void itemClick(ItemClickEvent itemClickEvent) {
                anket = (Anket) itemClickEvent.getItemId();
            }
        });
        anketTab.setIcon(FontAwesome.CLIPBOARD);
        anketTab.setCaption("Anket");
    }
}
