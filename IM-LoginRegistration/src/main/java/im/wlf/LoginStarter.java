package im.wlf;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.data.redis.core.StringRedisTemplate;

@SpringBootApplication
@MapperScan("im/wlf/mapper")
public class LoginStarter {

    public static void main(String[] args) {
        SpringApplication.run(LoginStarter.class);
    }
}
