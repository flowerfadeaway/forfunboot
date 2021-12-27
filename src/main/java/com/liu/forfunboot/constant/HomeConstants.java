package com.liu.forfunboot.constant;


import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class HomeConstants implements InitializingBean {
    @Value("${dir.root}")
    private String root;
    @Value("${dir.xuanxuan}")
    private String xuanxuan;
    @Value("${dir.ruirui}")
    private String ruirui;



    public Map<String, String> idToProfilePath = new HashMap<>();


    @Override
    public void afterPropertiesSet() throws Exception {
        idToProfilePath.put("root", root);
        idToProfilePath.put("xuanxuan", xuanxuan);
        idToProfilePath.put("ruirui", ruirui);
    }
}
