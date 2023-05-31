package im.wlf.interceptor;


import im.wlf.entity.User;

/**
 * 工具类：实现向ThreadLocal存储数据的方法
 */
public class UserHolder {

    private static ThreadLocal<User> t1 = new ThreadLocal<>();

    // 将当前用户对象，存入ThreadLocal
    public static void set(User user) {
        t1.set(user);
    }

    // 从当前线程，获取用户对象
    public static User get() {
        return t1.get();
    }

    // 从当前线程，获取用户对象的id
    public static String getUserId() {
        return t1.get().getId();
    }

    // 从当前线程，获取用户对象的手机号码
    public static String getPhone() {
        return t1.get().getPhone();
    }


    // 清空当前线程ThreadLocal
    public static void remove() {
        t1.remove();
    }
}
