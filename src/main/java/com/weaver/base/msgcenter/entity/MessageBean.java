package com.weaver.base.msgcenter.entity;

import java.io.Serializable;
import java.util.Map;


public class MessageBean implements Serializable{
	/*
	 * 消息编号
	 */
	private String messageId;
	/*
	 * 消息类型
	 */
	private MessageType messageType;
	
	/*
	 * 目标用户ID
	 */
	private int userId;
	
	/*
	 * 目标ID
	 */
	private String targetId;
	/*
	 * 目标名称
	 */
	private String targetName;
	
	/*
	 * 标题
	 */
	private String title;
	/*
	 * 内容
	 */
	private String context;
	/*
	 * 创建人
	 */
	private int creater;
	
	/*
	 * 创建日期
	 */
	private String date;
	/*
	 * 创建时间
	 */
	private String time;
	/*
	 * 用户IP
	 */
	private String clientIp;
	/*
	 * 详细信息
	 */
	private String desc;
	/*
	 * 链接地址
	 */
	private String linkUrl;
	/*
	 * 参数
	 */
	private Map<String, Object> params;
	
	/*
	 * 全局唯一消息编号
	 */
	private String messageUnitId;
	
	public String getMessageId() {
		return messageId;
	}
	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}
	public MessageType getMessageType() {
		return messageType;
	}
	public void setMessageType(MessageType messageType) {
		this.messageType = messageType;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getTargetId() {
		return targetId;
	}
	public void setTargetId(String targetId) {
		this.targetId = targetId;
	}
	public String getTargetName() {
		return targetName;
	}
	public void setTargetName(String targetName) {
		this.targetName = targetName;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContext() {
		return context;
	}
	public void setContext(String context) {
		this.context = context;
	}
	public int getCreater() {
		return creater;
	}
	public void setCreater(int creater) {
		this.creater = creater;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getClientIp() {
		return clientIp;
	}
	public void setClientIp(String clientIp) {
		this.clientIp = clientIp;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getLinkUrl() {
		return linkUrl;
	}
	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}
	public Map<String, Object> getParams() {
		return params;
	}
	public void setParams(Map<String, Object> params) {
		this.params = params;
	}
	public String getMessageUnitId() {
		return messageUnitId;
	}
	public void setMessageUnitId(String messageUnitId) {
		this.messageUnitId = messageUnitId;
	}
}
