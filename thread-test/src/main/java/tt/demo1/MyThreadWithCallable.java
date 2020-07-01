package tt.demo1;

import org.springframework.stereotype.Component;

import java.util.concurrent.Callable;

/**
 * @Auther: 薛
 * @Date: 2020/6/29 10:33
 * @Description:
 */
@Component
public class MyThreadWithCallable  implements Callable<String>{
    /**
     * 同步代码块或添加同步方法
     * @return
     * @throws Exception
     */
        @Override
        public   String call() throws Exception {
            synchronized (this) {
                return String.valueOf((int) (Math.random() * 10 + 1));
            }
        }
}
