import com.weaver.base.msgcenter.entity.MessageBean;
import com.weaver.base.msgcenter.utils.Util_ActiveMQ;
import com.weaver.base.msgcenter.utils.Util_RabbitMQ;
import org.junit.Test;

public class TestRabbitMQ {

    @Test
    public void test(){
        Util_RabbitMQ util_rabbitMQ = Util_RabbitMQ.getInstance();

        MessageBean messageBean = new MessageBean();
        messageBean.setUserId(20180904);
        messageBean.setContext("hhhhshhshshh");

        try {
            util_rabbitMQ.sendMessage(messageBean);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }


    public static void main(String []args){
        Util_RabbitMQ util_rabbitMQ = Util_RabbitMQ.getInstance();
        try {
            util_rabbitMQ.receiveMessage("20180904");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
