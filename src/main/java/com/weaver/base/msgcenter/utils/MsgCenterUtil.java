package com.weaver.base.msgcenter.utils;

import com.alibaba.fastjson.JSON;
import com.weaver.base.msgcenter.entity.EcologyMessageInfoEntity;
import com.weaver.base.msgcenter.entity.MessageBean;
import com.weaver.base.msgcenter.utils.messagequeue.listener.ActiveListener;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

/**
 * @ Author     ：wyl
 * @ Date       ：Created in 14:01 2018/9/7
 * @ Description：消息中心的工具类
 * @ Modified By：
 * @ Version：    1.0
 */
public class MsgCenterUtil {

    private static final Log logger = LogFactory.getLog(MsgCenterUtil.class);
    /**
     * 对象转字符串
     * @param s
     * @return
     */
    public static String null2String(Object s){
        return s == null ? "" : s.toString();
    }

    /**
     * 字符串转int
     * @param v
     * @return
     */
    public static int getIntValue(String v) {
        return getIntValue(v, -1);
    }

    public static int getIntValue(String v, int def) {
        try {
            return Integer.parseInt(v);
        } catch (Exception ex) {
            return def;
        }
    }


    /**
     * 序列化
     * @param object
     * @return
     */
    public static byte[] serialize(Object object) {
        if (object == null) {
            return null;
        } else {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            try {
                ObjectOutputStream oos = new ObjectOutputStream(baos);
                oos.writeObject(object);
                oos.flush();
            } catch (IOException var3) {
                throw new IllegalArgumentException("Failed to serialize object of type: " + object.getClass(), var3);
            }

            return baos.toByteArray();
        }
    }

    /**
     * 反序列化
     * @param bytes
     * @return
     */
    public static Object deserialize(byte[] bytes) {
        if (bytes == null) {
            return null;
        } else {
            try {
                ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(bytes));
                return ois.readObject();
            } catch (IOException var2) {
                throw new IllegalArgumentException("Failed to deserialize object", var2);
            } catch (ClassNotFoundException var3) {
                throw new IllegalStateException("Failed to deserialize object type", var3);
            }
        }
    }


    /**
     * 加载property文件到io流里面
     * @param propertyFile
     * @return
     */
    public static Properties loadProperties(String propertyFile){

        Properties properties = new Properties();

        try{
            InputStream is = MsgCenterUtil.class.getClassLoader().getResourceAsStream(propertyFile);

            if(null == is){
                is = MsgCenterUtil.class.getClassLoader().getResourceAsStream("properties/" + propertyFile);
            }

            properties.load(is);
        }catch (IOException e){
            e.printStackTrace();
        }

        return properties;
    }

    /**
     * 根据key值取得对应的value值
     * @param propertyFile
     * @param key
     * @return
     */
    public static String getValue(String propertyFile,String key){
        Properties properties = loadProperties(propertyFile);

        return properties.getProperty(key);
    }

    /**
     * 消息中心实体类转化
     * @param messageBean
     * @return
     */
    public static EcologyMessageInfoEntity objectConvert(MessageBean messageBean){
        EcologyMessageInfoEntity ecologyMessageInfoEntity = new EcologyMessageInfoEntity();
        ecologyMessageInfoEntity.setMessageId(Integer.parseInt(messageBean.getMessageId()));
        ecologyMessageInfoEntity.setMessageType(messageBean.getMessageType().getCode());
        ecologyMessageInfoEntity.setUserId(messageBean.getUserId());
        ecologyMessageInfoEntity.setTargetId(messageBean.getTargetId());
        ecologyMessageInfoEntity.setTargetName(messageBean.getTargetName());
        ecologyMessageInfoEntity.setTitle(messageBean.getTitle());
        ecologyMessageInfoEntity.setContext(messageBean.getContext());
        ecologyMessageInfoEntity.setCreater(messageBean.getCreater());
        ecologyMessageInfoEntity.setCreateDate(messageBean.getDate());
        ecologyMessageInfoEntity.setCreateTime(messageBean.getTime());
        ecologyMessageInfoEntity.setClientIp(messageBean.getClientIp());
        ecologyMessageInfoEntity.setDescription(messageBean.getDesc());
        ecologyMessageInfoEntity.setLinkUrl(messageBean.getLinkUrl());
        ecologyMessageInfoEntity.setParams(JSON.toJSONString(messageBean.getParams()));
        return ecologyMessageInfoEntity;
    }

}
