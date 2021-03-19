package com.uniyaz.core.service;

import com.uniyaz.core.data.dao.SecenekDao;
import com.uniyaz.core.domain.Secenek;

import java.util.List;

/**
 * @author HAKAN DERELİ
 * @since 5.xxx.x
 */
public class SecenekService {
    SecenekDao secenekDao = new SecenekDao();

    public void saveSecenek(Secenek secenek) {
        secenekDao.saveSecenek(secenek);
    }

    public List<Secenek> findAllHql() {
        return secenekDao.findAllHql();
    }

    public void deleteSecenek(Secenek secenek) {
        secenekDao.deleteSecenek(secenek);
    }

    public List<Secenek> findBySoruId(Long soruId) {
        return secenekDao.findBySoruId(soruId);
    }
}
