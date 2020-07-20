package tt.threads;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Auther: 薛
 * @Date: 2020/6/29 14:33
 * @Description:
 */
@Data
public class baoZi {
     String name="/";
     Boolean isHave=false;//最开始默认没有值
}
@Data
@NoArgsConstructor
class Pu extends Thread{
 private  baoZi bz;
 public Pu(baoZi bb){
     this.bz=bb;
    }
    @Override
    public void run() {
     while (true) {
         synchronized (bz) {
             if (bz.isHave) {
                 try {
                     bz.wait();
                 } catch (InterruptedException e) {
                     e.printStackTrace();
                 }
             }
             System.out.println("开始生产~" + bz.getName());
             //生产完毕修改状态有值
             try {
                 Thread.sleep(3000);
             } catch (InterruptedException e) {
                 e.printStackTrace();
             }
             bz.isHave = true;
             bz.notify();//唤醒当前线程
         }
     }
    }
}
@Data
@NoArgsConstructor
class BC extends Thread{
 private  baoZi bz;
 public BC(baoZi bb){
     this.bz=bb;
    }
    @Override
    public void run() {
     while (true) {
         synchronized (bz) {
             if (bz.isHave== false) {
                 try {
                     bz.wait();
                 } catch (InterruptedException e) {
                     e.printStackTrace();
                 }
             }
             System.out.println("开始吃~" + bz.getName());
             //生产完毕修改状态有值
             bz.isHave=false;
             bz.notify();//唤醒当前线程
         }
     }
    }
}
class Demo {
    public static void main(String[] args) {
        baoZi bbb = new baoZi();
        new Pu(bbb).start();
        new BC(bbb).start();
    }
}
