package tt.demo1;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @Auther: 薛
 * @Date: 2020/6/29 09:48
 * @Description:
 */
//继承Thread 重写线程开启时执行的run方法并打印当前线程名称
public class MyThread extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName() + "\t" + 0);
        }
    }

    //声明构造方法 外部使用当前bean时直接开启线程即调用run方法~
    public MyThread() {
        this.start();
    }

    public static void main(String[] args) {
        new MyThread();
        new MyThread();
        new MyThread();
    }
}

//实现runnable接口重写run方法
@SuppressWarnings("ALL")
class HAHA {
    static class MyThread2 extends Thread {
        private Object o;
        public MyThread2(Object oo) {
            this.o = oo;
        }
        int x = 10;
        @Override
        public void run() {
            synchronized (o) {
                while (true) {
                    if (x > 0) {
                        System.out.println(System.currentTimeMillis()+"我执行了自减操作" + x--);
                        if (x == 5) {
                            try {
                                //等待
                                System.out.println(System.currentTimeMillis()+"我执行了等待操作");
                                o.wait();
                            } catch (InterruptedException e) {
                            }
                        }
                    }else {
                        break;
                    }
                }
            }
        }

    }
    static class MyThread9 extends Thread {
        private Object o;
        public MyThread9(Object oo) {
            this.o = oo;
        }
        @Override
        public void run() {
            synchronized (o) {
                o.notify();
                System.out.println(System.currentTimeMillis()+"当前值是5,线程等待被唤醒");
                System.out.println("准备休眠两秒再唤醒当前线程");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                }
            }
        }
    }
    public static void main(String[] args) {
        Object o = new Object();
        MyThread2 myThread2 = new MyThread2(o);
        MyThread9 myThread9 = new MyThread9(o);
        new Thread(myThread2).start();
        new Thread(myThread9).start();
    }
}

//实现Callable 泛型类型为String(字符串)
class MyThread3 implements Callable<String> {
    @Override
    public String call() throws Exception {
        //获取随机数
        return String.valueOf((int) (Math.random() * 10 + 1));
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //声明一个默认线程池
        ExecutorService executorService = Executors.newCachedThreadPool();
        //由线程池提交任务执行并返回线程执行完毕之后的结果值
        Future<String> submit = executorService.submit(new MyThread3());
        //调用Future get方法获取结果
        System.out.println(submit.get());
    }
}

class ddd {

    public void D() {
        System.out.println("走你");
    }

    public static void main(String[] args) {

    }
}

class TestMyThread {
    public static void main(String[] args) {
        new Thread() {
            //内置有一个run方式:该方法是线程执行时业务处理~
            @Override
            public void run() {
                System.out.println("匿名内部类线程声明执行" + "线程名称\t" + this.getName());
            }
            //调用start方法,即触发当前线程开始执行
        }.start();
        new Thread();

    }
}
