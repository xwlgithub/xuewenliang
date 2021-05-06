import com.xwl.utils.JwtUtils;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author xueWenLiang
 * @date 2021/5/6 15:23
 * @Description 描述信息
 */
@SpringBootTest(classes = Demo.class)
public class Demo {

    @Test
    public  void mains() {
        String tokenByParams = JwtUtils.createTokenByParams("李四", "湖南省");
        System.out.println(tokenByParams);
    }


}
