package com.weaver.base.msgcenter.entity;

public enum MessageType {

	TW_WMENTION_ME(1, 2118),	//提到我的
	TW_COMMUNICATION(2, 33636),	//相关交流
    WF_NEW_ARRIVAL(3, 179),		//新到达流程
    WF_COMPLETED(4, 179),		//已完成流程
    WF_TIMEOUT(5, 582),			//超时流程
    WF_RETURN(6, 33637),		//退回流程
    WF_FORWARD(7, 2115),		//转发流程
    WF_COPY(8, 33638),			//抄送流程
    WF_INQUIRY(9, 34076),		//意见征询流程
    DOC_NEW_DOC(10, 34076),  	//新文档阅读提醒
    WKP_SCHEDULE(11, 83995),	//日程提醒
    WKP_REMIND(12, 83995),		//协作提醒
    TASK_ARRIVAL(13,  16066),	//新到达任务
    MEETTING_REMIND(14, 84641),	//会议提醒
    HR_BIRTHDAY_REMIND(15, 32269), //生日提醒
    HR_PASSWORD_CHANGE(16, 31953),	//密码变更提醒
    STOCK_ACCEPTANCE_REMIND(17, 27618),//入库验收提醒
    STOCK_LOW_WARNING(18, 27618),		 //低库存预警
    STOCK_TOP_WARNING(19,16686), //库存上限预警
    STOCK_DOWN_WARNING(20,16686),//库存下限预警
    STOCK_WARNING(21,16686),     //库存呆滞预警
    SYS_PUBLIC_CHANGE(22,16686),	 //公共组调整
    SYS_MAIL_REMIND(23,16686),		 //邮件提醒
    OTHER(24,16686),			 //异构系统新到达流程
    SYS_BROADCAST(25,16686),		 //广播
    SYS_ERROR(99,16686),		 //错误信息
	;

    /**
     * code
     */
    protected int code;
    
    protected int parentCode;

    protected int labelId;

    public int getCode() {
        return code;
    }
    
    public int getParentCode() {
        return parentCode;
    }

    public int getLableId() {
        return labelId;
    }

    MessageType(int code, int labelId) {
        this.code = code;
        this.labelId = labelId;
    }

}
