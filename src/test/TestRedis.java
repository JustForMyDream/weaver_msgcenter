import com.weaver.base.msgcenter.utils.redis.Util_Redis;
import org.junit.Test;

public class TestRedis {

    @Test
    public void test(){

        Util_Redis util_redis = Util_Redis.getIstance();

        util_redis.set("aaa","111");
        System.out.println(util_redis.get("aaa"));
    }
}
