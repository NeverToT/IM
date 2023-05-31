package im.common.utils;

public class StatusCode {
    // 1xx 信息性状态码
    // ...

    // 2xx 成功状态码
    public static final int OK = 200; // 请求成功
    public static final int CREATED = 201; // 已创建
    public static final int ACCEPTED = 202; // 已接受
    // ...

    // 3xx 重定向状态码
    // ...

    // 4xx 客户端错误状态码
    public static final int BAD_REQUEST = 400; // 错误的请求
    public static final int UNAUTHORIZED = 401; // 未授权
    public static final int FORBIDDEN = 403; // 禁止访问
    // ...

    // 5xx 服务器错误状态码
    public static final int INTERNAL_SERVER_ERROR = 500; // 服务器内部错误
    public static final int NOT_IMPLEMENTED = 501; // 未实现
    public static final int BAD_GATEWAY = 502; // 错误的网关
    // ...

    // 自定义状态码
    public static final int SUCCESS = 1000; // 成功
    public static final int FAILURE = 1001; // 失败
    public static final int INVALID_INPUT = 1002; // 无效的输入



    // 私有构造函数防止实例化
    private StatusCode() {

    }
}
