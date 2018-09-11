package com.weaver.base.msgcenter.utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ Author     ：wyl
 * @ Date       ：Created in 16:26 2018/9/10
 * @ Description：数据库操作工具类
 * @ Modified By：
 * @ Version：    1.0
 */
public class DBUtils {
    private static final Log logger = LogFactory.getLog(MsgCenterUtil.class);
    /**
     * 根据属性名获取属性值
     * @param fieldName
     * @param o
     * @return
     */
    public static Object getFieldValueByName(String fieldName,String type,Object o){
        try {


            String firstLetter = fieldName.substring(0,1).toUpperCase();
            String getter = "";
            if(type.equals("boolean")){
                getter = "is" + firstLetter + fieldName.substring(1);
            } else {
                getter = "get" + firstLetter + fieldName.substring(1);
            }

            Method method =o.getClass().getMethod(getter,new Class[]{});
            Object value = method.invoke(o,new Object[]{});
            return value;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage(),e);
            return null;
        }
    }

    public static Object getFieldValueByName(String fieldName,Object o){
        return getFieldValueByName(fieldName,"",o);
    }


    /**
     * 获取属性类型(type)，属性名(name)，属性值(value)的map组成的list
     * @param o
     * @return
     */
    public static List<Map<String,Object>> getFiledInfo(Object o){
        List<Map<String,Object>> list = new ArrayList();



        Field[]fields = o.getClass().getDeclaredFields();

        for(int i = 0;i < fields.length;i ++){
            Map<String,Object> map = new HashMap();
            map.put("FiledType",fields[i].getType());
            map.put("FiledName",fields[i].getName());
            map.put("FiledValue",getFieldValueByName(fields[i].getName(), String.valueOf(fields[i].getType()),o));
            list.add(map);
        }
        return list;
    }

    public static Object getValueByMap(Map<String,Object> map){
        Object value = map.get("FiledValue");
        String type = String.valueOf(map.get("FiledType"));
        if(null == value || "null".equals(value)){
            if("int".equals(type)){
                value = 0;

            }else{
                value = "''";
            }
        }else {
            if("int".equals(type)){
                value = map.get("FiledValue");

            }else{
                value = "'"+map.get("FiledValue")+"'";
            }
        }
        return value;
    }


    /**
     * 对象转数据库语句（数据库只支持String，int两种类型，对象可以随便填写类型，但是要求int型与数据库对应）。
     * @param object
     * @param dbName
     * @return
     */
    public static String getInsertSqlString(Object object,String dbName){
        List<Map<String,Object>> messageBeanPropertiesList = DBUtils.getFiledInfo(object);

        String sql = "insert into " + dbName + " ( ";

        for(int i = 0;i < messageBeanPropertiesList.size();i ++){
            Map<String,Object> map = messageBeanPropertiesList.get(i);
            if(i == messageBeanPropertiesList.size() - 1){
                sql += map.get("FiledName").toString().toUpperCase() + " ) values ( ";
            }else {
                sql += map.get("FiledName").toString().toUpperCase() + " , ";
            }
        }

        for(int i = 0;i < messageBeanPropertiesList.size();i ++){
            Map<String,Object> map = messageBeanPropertiesList.get(i);

            Object value = getValueByMap(map);

            if(i == messageBeanPropertiesList.size() - 1){
                sql += value + " );";
            }else {
                sql += value + " , ";
            }
        }

        return sql;
    }

    /**
     * 数据库对象更新语句
     * @param object
     * @param dbName
     * @return
     */
    public static String getUpdateSqlString(Object object,String dbName,String ...params){
        List<Map<String,Object>> messageBeanPropertiesList = DBUtils.getFiledInfo(object);
        String sql = "update "+dbName+" set ";
        for(int i = 0;i < messageBeanPropertiesList.size();i ++){
            Map<String,Object> map = messageBeanPropertiesList.get(i);
            if(i == messageBeanPropertiesList.size() - 1){
                sql += map.get("FiledName").toString().toUpperCase() + " = " +  getValueByMap(map) + " where 1 = 1 and ";
                for(String param : params){
                    sql += param +" = " + getFieldValueByName(param,object) + " and ";
                }

            }else {
                sql += map.get("FiledName").toString().toUpperCase() + " = " +  getValueByMap(map) + " , ";
            }
        }

        sql = sql.substring(0,sql.length() - 5) + ";";

        return sql;
    }
}
