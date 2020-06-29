package tt.runable;

/**
 * @Auther: 薛
 * @Date: 2020/6/29 15:41
 * @Description:
 */
public class MyRunable implements Runnable {
    private static int counts=100;
    @Override
    public  void run() {
        while (true) {
            if (counts <=0) {
                break;
            }
            System.out.println("抢到了"+counts);
            counts--;
        }
    }

    public static void main(String[] args) {
        MyRunable myRunable = new MyRunable();
        new Thread(myRunable).start();
        new Thread(myRunable).start();
        new Thread(myRunable).start();
        new Thread(myRunable).start();
        new Thread(myRunable).start();
    }
}

