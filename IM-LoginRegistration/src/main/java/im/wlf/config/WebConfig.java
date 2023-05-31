package im.wlf.config;


import im.wlf.interceptor.IPInterceptor;
import im.wlf.interceptor.TokenInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration // 配置类必须添加@Configuration注解
public class WebConfig implements WebMvcConfigurer { // 自定义的SpringMVC的配置类要实现WebMvcConfigurer接口
    @Autowired
    private StringRedisTemplate redisTemplate;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new TokenInterceptor())
                .addPathPatterns("/**") // 拦截所有请求
                .excludePathPatterns("/login/**","/register/**");// 排除对登录注册请求的拦截

        registry.addInterceptor(new IPInterceptor(redisTemplate))
                .addPathPatterns("/**");
    }
}