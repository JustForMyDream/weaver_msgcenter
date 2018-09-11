import com.weaver.base.msgcenter.channel.IMessageChannel;
import com.weaver.base.msgcenter.channel.Impl.ActiveMQChannel;
import com.weaver.base.msgcenter.entity.MessageBean;
import org.junit.Test;

public class TestActiveChannel {

    @Test
    public void test(){

        IMessageChannel iMessageChannel = new ActiveMQChannel();
        MessageBean messageBean = new MessageBean();
        messageBean.setUserId(20180904);
        messageBean.setContext("hhhhshhshshh");
        try {
            System.out.println(iMessageChannel.send(messageBean));;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
