package since.linked_call;

import org.junit.Test;

public class UserMain {

    /**
     * 同为Sting 参数， 客户端调用具有不确定性
     * 当参数位置颠倒不会导致程序出错，却会导致结果出错
     */
    @Test
    public void build2Constructor() {
        User user = new User("firstName", "lastName", "fullName");
    }

    /**
     * 可以解决上一个问题，但：
     * 在多线程情况下，无法确定对象在能执行完所有的setter
     * 需要同步以确保对象状态一致
     */
    @Test
    public void build2Setter() {
        User user = new User();
        user.setFirstName("firstName");
        user.setLastName("lastName");
        user.setFullName("fullName");
    }

    /**
     * 链式调用
     */
    @Test
    public void build2Linked() {
        User user = new User().
                setFirstName("firstName").setLastName("lastName").setFullName("fullName");

        System.out.println(user);
    }

}
