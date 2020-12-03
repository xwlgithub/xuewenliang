package itxwl.email;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @Auther: 薛
 * @Date: 2020/12/1 13:13
 * @Description:
 */
public class Ddemo {

    public static void main(String[] args)throws Exception {
        SimpleDateFormat st = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateTimes="2020-12-01 13:13:00";
        long bi=st.parse(dateTimes).getTime();
        long notTime = new Date().getTime();
        long l = bi + 3600000;
        String format = st.format(new Date(l));
        System.out.println(format);
        if (notTime>l){
            System.out.println("离线");
        }else {
            System.out.println("正常运行");
        }
    }
}
