package tt.callable;

import java.util.concurrent.*;

/**
 * @Auther: 薛
 * @Date: 2020/6/29 16:20
 * @Description:
 */
public class MyCallable implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        Thread.sleep(6000);
        return 15;
    }

    public static void main(String[] args) throws Exception{
        FutureTask<Integer> integerFutureTask = new FutureTask<>(new MyCallable());
        new Thread(integerFutureTask).start();
        Integer integer = null;
        try {
            integer = integerFutureTask.get(7, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
        System.out.println(integer);
    }
}
