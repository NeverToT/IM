package im.wlf.exception;

public class LoginException extends RuntimeException {

    public LoginException(String message) {
        super(message);
    }
    public static class IPAccessLimitException extends LoginException {
        public IPAccessLimitException() {
            super("请勿频繁登陆");
        }
    }

    public static class CaptchaLimitException extends LoginException {
        public CaptchaLimitException () {
            super("频繁发送验证码");
        }
    }

    public static class ParamNullException extends LoginException {
        public ParamNullException(String msg) {
            super(msg);
        }
    }

    public static class TokenException extends LoginException {
        public TokenException() {
            super("参数错误");
        }
    }


}
