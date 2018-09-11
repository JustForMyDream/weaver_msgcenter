package com.weaver.base.msgcenter.channel.impl;

import com.weaver.base.msgcenter.channel.IMessageChannel;
import com.weaver.base.msgcenter.entity.MessageBean;
import com.weaver.base.msgcenter.utils.messagequeue.Util_RabbitMQ;


import java.util.List;

public class RabbitMQChannel implements IMessageChannel {

    private static RabbitMQChannel rabbitMQChannel = null;

    private RabbitMQChannel(){}

    public static RabbitMQChannel getInstance(){
        if(null == rabbitMQChannel){
            synchronized (RabbitMQChannel.class){
                if(null == rabbitMQChannel){
                    rabbitMQChannel = new RabbitMQChannel();
                }
            }
        }
        return rabbitMQChannel;
    }

    private Util_RabbitMQ util_rabbitMQ = Util_RabbitMQ.getInstance();


    public boolean send(MessageBean message) throws Exception {
        try{
            util_rabbitMQ.sendMessage(message);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
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

    public boolean update(MessageBean message) throws Exception {
        return false;
    }

    public boolean updateList(List<MessageBean> list) throws Exception {
        return false;
    }

    public List<MessageBean> getList(String userId) throws Exception {
        return null;
    }

    public List<MessageBean> getList(String moduleId, String userId) throws Exception {
        return null;
    }

    public Integer sendList(List<MessageBean> list) throws Exception {
        return null;
    }

    public Integer getCount(String userId) throws Exception {
        return null;
    }

    public Integer getCount(String moduleId, String userId) throws Exception {
        return null;
    }
}
