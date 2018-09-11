package com.weaver.base.msgcenter.channel.impl;

import com.cloudstore.dev.api.bean.MessageBean;
import com.weaver.base.msgcenter.channel.IMessageChannel;
import com.weaver.base.msgcenter.constant.MessageCenterConstant;

import java.util.List;

public class RestfulChannel implements IMessageChannel {
    private static RestfulChannel restfulChannel = null;

    private RestfulChannel(){}

    public static RestfulChannel getInstance(){
        if(null == restfulChannel){
            synchronized (RestfulChannel.class){
                if(null == restfulChannel){
                    restfulChannel = new RestfulChannel();
                }
            }
        }
        return restfulChannel;
    }

    @Override
    public boolean send(MessageBean message) throws Exception {
        return false;
    }

    @Override
    public boolean delete(MessageBean message) throws Exception {
        return false;
    }

    @Override
    public boolean deleteList(List<MessageBean> list) throws Exception {
        return false;
    }

    @Override
    public boolean deleteListByUserId(String userId) throws Exception {
        return false;
    }

    @Override
    public boolean update(MessageBean message) throws Exception {
        return false;
    }

    @Override
    public boolean updateList(List<MessageBean> list) throws Exception {
        return false;
    }

    @Override
    public List<MessageBean> getList(String userId) throws Exception {
        return null;
    }

    @Override
    public List<MessageBean> getList(String moduleId, String userId) throws Exception {
        return null;
    }

    @Override
    public Integer sendList(List<MessageBean> list) throws Exception {
        return null;
    }

    @Override
    public Integer getCount(String userId) throws Exception {
        return null;
    }

    @Override
    public Integer getCount(String moduleId, String userId) throws Exception {
        return null;
    }
}
