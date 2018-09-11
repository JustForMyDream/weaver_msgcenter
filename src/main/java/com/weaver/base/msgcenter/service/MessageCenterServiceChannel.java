package com.weaver.base.msgcenter.service;

import com.weaver.base.msgcenter.channel.IMessageChannel;
import com.weaver.base.msgcenter.channel.impl.*;
import com.weaver.base.msgcenter.constant.MessageCenterConstant;
import com.weaver.base.msgcenter.entity.MessageBean;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;

/**
 * @ Author     ：wyl
 * @ Date       ：Created in 11:33 2018/9/7
 * @ Description：消息通道统一发送类
 * @ Modified By：
 * @ Version：    1.0
 */
public class MessageCenterServiceChannel{

    private static final Log logger = LogFactory.getLog(MessageCenterServiceChannel.class);
    private boolean cache_enable = true;
    private boolean activemq_enable = false;
    private boolean rabbitmq_enable = false;
    private boolean db_enable = false;
    private boolean restful_enable = false;

    public boolean isCache_enable() {
        return cache_enable;
    }

    public boolean isActivemq_enable() {
        return activemq_enable;
    }

    public boolean isRabbitmq_enable() {
        return rabbitmq_enable;
    }

    public boolean isDb_enable() {
        return db_enable;
    }

    public boolean isRestful_enable() {
        return restful_enable;
    }

    public static IMessageChannel iMessageChannel;

    /**
     * 根据开关启用不同的通道
     */
    public MessageCenterServiceChannel(){
//        BaseBean baseBean = new BaseBean();
//
//        cache_enable = Boolean.parseBoolean(baseBean.getPropValue("weaver_message_center","cache_enable"));
//        activemq_enable = Boolean.parseBoolean(baseBean.getPropValue("weaver_message_center","activemq_enable"));
//        rabbitmq_enable = Boolean.parseBoolean(baseBean.getPropValue("weaver_message_center","rabbitmq_enable"));
//        db_enable = Boolean.parseBoolean(baseBean.getPropValue("weaver_message_center","db_enable"));
//        restful_enable = Boolean.parseBoolean(baseBean.getPropValue("weaver_message_center","restful_enable"));

        int openNumber = 0;



        if(activemq_enable){
            iMessageChannel = ActiveMQChannel.getInstance();
            logger.info("====================================>创建的通道为ActiveMQChannel");
            openNumber ++;
        }
        if(rabbitmq_enable){
            iMessageChannel = RabbitMQChannel.getInstance();
            logger.info("====================================>创建的通道为RabbitMQChannel");
            openNumber ++;
        }
        if(cache_enable){
            iMessageChannel = CacheChannel.getInstance();
            logger.info("====================================>创建的通道为CacheChannel");
            openNumber ++;
        }
        if(db_enable){
            iMessageChannel = DBChannel.getInstance();
            logger.info("====================================>创建的通道为DBChannel");
            openNumber ++;
        }
        if(restful_enable){
            iMessageChannel = RestfulChannel.getInstance();
            logger.info("====================================>创建的通道为RestfulChannel");
            openNumber ++;
        }

        if(openNumber > 1){
            iMessageChannel = CacheChannel.getInstance();
            logger.info("====================================>创建的通道为CacheChannel");
        }

    }


    /**
     * 发送消息
     * @param message
     * @return
     * @throws Exception
     */
    public boolean send(MessageBean message) throws Exception {
        return iMessageChannel.send(message);

    }

    /**
     * 删除单个消息
     * @param message
     * @return
     * @throws Exception
     */
    public boolean delete(MessageBean message) throws Exception {
        return iMessageChannel.delete(message);
    }


    public boolean deleteList(List<MessageBean> list) throws Exception {
        return iMessageChannel.deleteList(list);
    }

    /**
     * 通过用户id删除全部消息
     * @param userId
     * @return
     * @throws Exception
     */
    public boolean deleteListByUserId(String userId) throws Exception {
        return iMessageChannel.deleteListByUserId(userId);
    }


    public boolean update(MessageBean message) throws Exception {
        return iMessageChannel.update(message);

    }


    public boolean updateList(List<MessageBean> list) throws Exception {
        return iMessageChannel.updateList(list);

    }

    public List<MessageBean> getList(String userId) throws Exception {
        return iMessageChannel.getList(userId);
    }

    public List<MessageBean> getList(String moduleId, String userId) throws Exception {
        return iMessageChannel.getList(moduleId,userId);
    }


    public Integer sendList(List<MessageBean> list) throws Exception {
        return iMessageChannel.sendList(list);
    }

    /**
     * 获取用户消息的数量
     * @param userId
     * @return
     * @throws Exception
     */
    public Integer getCount(String userId) throws Exception {
        return iMessageChannel.getCount(userId);
    }

    public Integer getCount(String moduleId, String userId) throws Exception {
        return iMessageChannel.getCount(moduleId,userId);
    }
}
