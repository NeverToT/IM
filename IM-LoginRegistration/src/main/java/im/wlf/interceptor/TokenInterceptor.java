package im.wlf.interceptor;



import im.wlf.entity.User;
import im.wlf.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import org.springframework.web.servlet.HandlerInterceptor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TokenInterceptor implements HandlerInterceptor { // 创建自定义拦截器要实现HandlerInterceptor接口
    /**
     * 处理请求之前执行的拦截方法
     * @param request 此次请求的request参数
     * @param response 此次请求的response参数
     * @param handler
     * @return 返回true放行，返回false拦截本次请求
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 1、获取请求头
        String token = request.getHeader("Token");
        // 2、使用工具类，判断token是否有效
        boolean verifyToken = JwtUtils.verifyToken(token);
        // 3、token无效，返回状态码401，拦截后续请求
        if (!verifyToken) {
            response.setStatus(401);
            return false;
        }
        // 4、如果token正常可用，解析token，获取id和手机号码，放行
        Claims claims = JwtUtils.getClaims(token);
        String phone = (String) claims.get("phone");
        String id = (String) claims.get("userId");
        String email = (String) claims.get("email");
        System.out.println(claims+"claims");
        // 构造User对象，存入ThreadLocal
        User user = new User();
        user.setId(id);
        user.setPhone(phone);
        user.setEmail(email);
        // 将解析token得到的用户信息保存到ThreadLocal对象中
        UserHolder.set(user);
        return true;
    }

    /**
     * 本次请求完成之后执行的拦截器方法，清空当前ThreadLocal内的内容，预防内存泄漏
     * @param request 此次请求的request参数
     * @param response 此次请求的response参数
     * @param handler
     * @param ex
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        UserHolder.remove();
    }
}