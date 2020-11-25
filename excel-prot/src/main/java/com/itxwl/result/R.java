package com.itxwl.result;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

/**
 * @Auther: 薛
 * @Date: 2020/11/25 13:46
 * @Description:
 */
@Data
@NoArgsConstructor
public class R<T>  implements Serializable {
    private T data;
    private String message;

    public static  <T> R<T> ok(T data){
        R<T> result = new R<>();
        result.setData(data);
        result.setMessage("成功");
        return result;
    }

    public static <T> R<T> error(String message){
        R<T> result = new R<>();
        result.setData(null);
        result.setMessage(message);
        return result;
    }

//    public static <T> List<Object> getDataList(R datas) {
//        List<Object> data=null;
//
//        try {
//             data = (List<Object>)datas.getData();
//        }catch (Exception e){
//
//        }
//        return data;
//    }
}
