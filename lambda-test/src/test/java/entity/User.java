package entity;

import lombok.Data;

/**
 * @Auther: 薛
 * @Date: 2020/4/21 13:25
 * @Description:
 */
@Data
public class User {
    private String id;
    private String name;
    private Double money;
    private String dd;

    public User(String id, String name, Double money,String dd) {
        this.id = id;
        this.name = name;
        this.money = money;
        this.dd = dd;
    }

    public User() {
    }

    public static void main(String[] args) {

    }
}
