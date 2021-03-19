package com.uniyaz.core.service;

import com.uniyaz.core.data.dao.SoruDao;
import com.uniyaz.core.domain.Soru;

import java.util.List;

/**
 * @author HAKAN DERELİ
 * @since 5.xxx.x
 */
public class SoruService {
    SoruDao soruDao = new SoruDao();

    public void saveSoru(Soru soru) {
        soruDao.saveSoru(soru);
    }

    public List<Soru> findAllHql() {
        return soruDao.findAllHql();
    }

    public void deleteSoru(Soru soru) {
        soruDao.deleteSoru(soru);
    }

    public List<Soru> findByPanelId(Long panelId) {
        return soruDao.findByPanelId(panelId);
    }
}
