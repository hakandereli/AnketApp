package com.uniyaz.core.service;

import com.uniyaz.core.data.dao.PanelDao;
import com.uniyaz.core.domain.Panel;

import java.util.List;

/**
 * @author HAKAN DERELİ
 * @since 5.xxx.x
 */
public class PanelService {
    PanelDao panelDao = new PanelDao();

    public void savePanel(Panel panel) {
        panelDao.savePanel(panel);
    }

    public List<Panel> findAllHql() {
        return panelDao.findAllHql();
    }

    public void deletePanel(Panel panel) {
        panelDao.deletePanel(panel);
    }

    public List<Panel> findByAnketId(Long anketId) {
        return panelDao.findByAnketId(anketId);
    }
}
