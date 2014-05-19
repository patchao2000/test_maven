package com.videostar.security.impl;

import com.videostar.security.api.UrlSourceFetcher;

import org.springframework.beans.factory.InitializingBean;

public class DatabaseUrlSourceFetcher extends AbstractDatabaseSourceFetcher
        implements UrlSourceFetcher, InitializingBean {
    public void afterPropertiesSet() throws Exception {
        if (getQuery() != null) {
            return;
        }

        String sql = "select ac.value as access,p.code as perm"
                + " from AUTH_ACCESS ac,AUTH_PERM p"
                + " where ac.perm_id=p.id and ac.type='URL'"
                + " order by ac.priority";
        this.setQuery(sql);
    }
}
