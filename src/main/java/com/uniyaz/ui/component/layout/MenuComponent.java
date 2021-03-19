package com.uniyaz.ui.component.layout;

import com.uniyaz.ui.MySiteUI;
import com.uniyaz.ui.page.AnketBaslatPage;
import com.uniyaz.ui.page.ContainerPage;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.UI;

/**
 * @author HAKAN DERELİ
 * @since 5.xxx.x
 */
public class MenuComponent extends MenuBar {
    private ContentComponent contentComponent;

    public MenuComponent() {
        setSizeFull();
        addStyleName("MenuComponent");

        MySiteUI mySiteUI = (MySiteUI) UI.getCurrent();
        contentComponent = mySiteUI.getContentComponent();

        buildAnketTanimMenuItem();

        buildAnketBaslatMenuItem();
    }

    private void buildAnketTanimMenuItem() {
        MenuItem anketTanimlaMenuItem = addItem("ANKET TANIMLA", new Command() {
            @Override
            public void menuSelected(MenuItem menuItem) {
                ContainerPage containerPage = new ContainerPage();
                contentComponent.setContent(containerPage);
            }
        });
    }


    private void buildAnketBaslatMenuItem() {
        MenuItem anketBaslatMenuItem = addItem("ANKET BASLAT", new Command() {
            @Override
            public void menuSelected(MenuItem menuItem) {
                AnketBaslatPage anketBaslatPage = new AnketBaslatPage();
                contentComponent.setContent(anketBaslatPage);
            }
        });

//        menuIslemleriMenuItem.addItem("Müşteri Ekle", FontAwesome.PLUS, new Command() {
//            @Override
//            public void menuSelected(MenuItem menuItem) {
//                MusteriPage musteriPage = new MusteriPage();
//                contentComponent.setContent(musteriPage);
//            }
//        });
    }
}
