package tt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.concurrent.*;

/**
 * @Auther: 薛
 * @Date: 2020/6/28 14:37
 * @Description:
 */
@SpringBootApplication
public class AppRunLocation {
    public static void main(String[] args) {
        SpringApplication.run(AppRunLocation.class,args);
    }
    @Bean
    public ExecutorService executorService(){
        return   new ThreadPoolExecutor(5,15,60,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(10),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.DiscardPolicy());
    }
}
