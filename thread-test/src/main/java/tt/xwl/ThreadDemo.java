package tt.xwl;

/**
 * @Auther: 薛
 * @Date: 2020/7/16 16:34
 * @Description:
 */
public class ThreadDemo  extends Thread {


    @Override
    public synchronized void start() {
        System.out.println("得");
    }


    public static void main(String[] args) {
        new ThreadDemo().start();
    }
}
