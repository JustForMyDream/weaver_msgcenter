import com.weaver.base.msgcenter.entity.EcologyMessageInfoEntity;
import com.weaver.base.msgcenter.entity.MessageBean;
import com.weaver.base.msgcenter.entity.MessageType;
import com.weaver.base.msgcenter.utils.MsgCenterUtil;
import com.weaver.base.msgcenter.utils.Util_ActiveMQ;
import org.junit.Test;

import javax.security.auth.callback.TextOutputCallback;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TestActiveMQ {

    @Test
    public void test(){
       // Util_ActiveMQ util_activeMQ = Util_ActiveMQ.getInstance();
        EcologyMessageInfoEntity ecologyMessageInfoEntity = new EcologyMessageInfoEntity();

        ecologyMessageInfoEntity.setMessageId(1);
        ecologyMessageInfoEntity.setCallBackClass("class");
        ecologyMessageInfoEntity.setLinkUrl("www.baidu.com");

        MessageType messageType = new MessageType();
        messageType.setId(1);
        messageType.setFlag(true);
        messageType.setContext("kkkkkkkkkkk");


        String sql = MsgCenterUtil.getUpdateSqlString(messageType,"message_type","id");


        System.out.println(sql);


    }


    public static void main(String []a){
        Util_ActiveMQ util_activeMQ = Util_ActiveMQ.getInstance();
        try {
            util_activeMQ.receiveMessage("message_center_queue_one");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }




}
