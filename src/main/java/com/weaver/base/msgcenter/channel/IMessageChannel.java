package com.weaver.base.msgcenter.channel;

import com.weaver.base.msgcenter.entity.MessageBean;

import java.util.List;

/**
 * @ Author     ：wyl
 * @ Date       ：Created in 11:15 2018/9/7
 * @ Description：消息中心消息通道接口
 * @ Modified By：
 * @Version:
 */
public interface IMessageChannel {

    /**
     * 发送消息
     * @param message 消息
     * @return 是否成功
     */
    public boolean
    send(MessageBean message) throws Exception;

    /**
     * 删除消息
     * @param message 键名
     * @return 是否成功
     */
    public boolean delete(MessageBean message) throws Exception;

    /**
     * 删除所有消息
     * @param list
     * @return
     * @throws Exception
     */
    public boolean deleteList(List<MessageBean> list) throws Exception;

    /**
     * 删除用户所有消息
     * @param userId
     * @return
     * @throws Exception
     */
    public boolean deleteListByUserId(String userId) throws Exception;


    /**
     * 更新消息
     * @param message 消息
     * @return 是否成功
     */
    public boolean update(MessageBean message) throws Exception;

    /**
     *
     * @param list
     * @return
     * @throws Exception
     */
    public boolean updateList(List<MessageBean> list) throws Exception;

    /**
     * 获取用户全部消息
     * @param userId 用户Id
     * @return
     * @throws Exception
     */
    public List<MessageBean> getList(String userId) throws Exception;

    /**
     * 获取消息列表
     * @param moduleId 模块名称
     * @param userId 用户ID
     */
    public List<MessageBean> getList(String moduleId, String userId) throws Exception;

    /**
     * 发送消息
     * @param list 消息体
     * @return
     * @throws Exception
     */
    public Integer sendList(List<MessageBean> list) throws Exception;

    /**
     * 获取消息总数
     * @param userId
     * @return
     * @throws Exception
     */
    public Integer getCount(String userId) throws Exception;

    /**
     * 获取模块消息数量
     * @param moduleId
     * @param userId
     * @return
     * @throws Exception
     */
    public Integer getCount(String moduleId, String userId) throws Exception;

}
