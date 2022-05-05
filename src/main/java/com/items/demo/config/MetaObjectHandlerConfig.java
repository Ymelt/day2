package com.items.demo.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Slf4j
public class MetaObjectHandlerConfig implements MetaObjectHandler {

    private static final String createName = "admin";
    private static final String updatedName = "admin";

    @Override
    public void insertFill(MetaObject metaObject) {
//        this.strictInsertFill(metaObject, "createdTime", Long.class, System.currentTimeMillis() / 1000);
//        this.strictInsertFill(metaObject, "createName", String.class, createName);
        log.info("start insert field....");
        this.setFieldValByName("createTime", System.currentTimeMillis() / 1000,metaObject);
        this.setFieldValByName("updateTime", System.currentTimeMillis() / 1000,metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
//        this.strictUpdateFill(metaObject, "updatedTime",  Long.class, System.currentTimeMillis() / 1000);
//        this.strictUpdateFill(metaObject, "updatedName", String.class, updatedName);
        this.setFieldValByName("updateTime", System.currentTimeMillis() / 1000,metaObject);

    }
}