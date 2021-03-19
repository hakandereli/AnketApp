package com.uniyaz.core.service;

import com.uniyaz.core.data.dao.AnketDao;
import com.uniyaz.core.domain.Anket;

import java.util.List;

/**
 * @author HAKAN DERELİ
 * @since 5.xxx.x
 */
public class AnketService {
    AnketDao anketDao = new AnketDao();

    public void saveAnket(Anket anket) {
        anketDao.saveAnket(anket);
    }

    public List<Anket> findAllHql() {
        return anketDao.findAllHql();
    }

    public void deleteAnket(Anket anket) {
        anketDao.deleteAnket(anket);
    }
}
