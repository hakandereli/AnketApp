package com.uniyaz.ui;

import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinServlet;

import javax.servlet.annotation.WebServlet;

/**
 * @author HAKAN DERELİ
 * @since 5.xxx.x
 */
@WebServlet(urlPatterns = "/*", name = "MySiteUIServlet", asyncSupported = true)
@VaadinServletConfiguration(ui = MySiteUI.class, productionMode = false)
public class MySiteUIServlet extends VaadinServlet {
}