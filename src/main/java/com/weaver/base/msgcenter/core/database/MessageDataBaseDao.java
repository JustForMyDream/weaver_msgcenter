package com.weaver.base.msgcenter.core.database;

import com.weaver.base.msgcenter.entity.MessageBean;

import java.util.List;

/**
 * @ Author     ：wyl
 * @ Date       ：Created in 12:26 2018/9/7
 * @ Description：数据库通道业务接口
 * @ Modified By：
 * @ Version：    1.0
 */
public interface MessageDataBaseDao {

    /**
     * 保存消息
     * @param messageBean
     * @return
     */
    public boolean save(MessageBean messageBean);

    /**
     * 删除单条消息
     * @param messageBean
     * @return
     */
    public boolean delete(MessageBean messageBean);

    /**
     * 更新消息
     * @param messageBean
     * @param params 更新条件
     * @return
     */
    public boolean update(MessageBean messageBean, String... params);


    /**
     * 获取消息
     * @param userId
     * @return
     */
    public List<MessageBean> getList(String userId);


}
