package com.weaver.base.msgcenter.channel.impl;


import com.weaver.base.msgcenter.channel.IMessageChannel;
import com.weaver.base.msgcenter.entity.MessageBean;

import java.util.List;

public class DBChannel implements IMessageChannel {

    private static DBChannel dbChannel = null;

    private DBChannel(){}

    public static DBChannel getInstance(){
        if(null == dbChannel){
            synchronized (DBChannel.class){
                if(null == dbChannel){
                    dbChannel = new DBChannel();
                }
            }
        }
        return dbChannel;
    }


    public boolean send(MessageBean message) throws Exception {
        return false;
    }

    public boolean delete(MessageBean message) throws Exception {
        return false;
    }

    public boolean deleteList(List<MessageBean> list) throws Exception {
        return false;
    }

    public boolean deleteListByUserId(String userId) throws Exception {
        return false;
    }

    public List<MessageBean> getList(String userId) throws Exception {
        return null;
    }

    public List<MessageBean> getList(String moduleId, String userId) throws Exception {
        return null;
    }

    public Integer getCount(String userId) throws Exception {
        return null;
    }

    public Integer getCount(String moduleId, String userId) throws Exception {
        return null;
    }

    public Integer sendList(List<MessageBean> list) throws Exception {
        return null;
    }

    public boolean updateList(List<MessageBean> list) throws Exception {
        return false;
    }

    public boolean update(MessageBean message) throws Exception {
        return false;
    }
}

