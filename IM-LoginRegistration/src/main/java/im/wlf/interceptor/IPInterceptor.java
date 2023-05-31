package im.wlf.interceptor;

import im.wlf.exception.LoginException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.TimeUnit;

public class IPInterceptor implements HandlerInterceptor {

    private final StringRedisTemplate redisTemplate;

    public IPInterceptor(StringRedisTemplate redisTemplate) {
        this.redisTemplate =redisTemplate;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String ipAddress = request.getRemoteAddr();
        String ipVisitCountStr = redisTemplate.opsForValue().get(ipAddress);
        int ipVisitCount = 0;
        if (ipVisitCountStr != null) {
            redisTemplate.opsForValue().increment(ipAddress);
            ipVisitCount = Integer.parseInt(ipVisitCountStr) + 1;
        } else {
            redisTemplate.opsForValue().set(ipAddress, "1", 10, TimeUnit.SECONDS);
        }
        if (ipVisitCount >= 3) {
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
