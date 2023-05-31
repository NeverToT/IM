package im.wlf.utils;

import im.wlf.exception.LoginException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.Map;

public class JwtUtils {

    // TOKEN的有效期1小时（S）
    private static final int TOKEN_TIME_OUT = 20;

    // 加密使用的密钥，解析token数据时要填写此密钥
    private static final String TOKEN_SECRET = "zglbcvip";

    /**
     * 生成token
     * @param params 有效载荷，token携带的供当前请求使用的信息
     * @return 生成的token
     */
    public static String getToken(Map<String,Object> params) {
        long currentTime = System.currentTimeMillis();
        return Jwts.builder()
                .signWith(SignatureAlgorithm.HS512, TOKEN_SECRET) // 加密方式
//                .setExpiration(new Date(currentTime + TOKEN_TIME_OUT * 1000)) // 过期时间戳
                .addClaims(params) // token携带的信息
                .compact();
    }

    /**
     * 获取Token中的claims携带的信息
     * @param token 要解析的token
     * @return 解析后的token携带的信息
     */
    public static Claims getClaims(String token) {
        return Jwts.parser()
                .setSigningKey(TOKEN_SECRET)
                .parseClaimsJws(token).getBody();
    }

    /**
     * 校验token是否有效，是否有效 true-有效，false-失效
     * @param token 需要校验的token
     * @return 该token是否有效
     */
    public static boolean verifyToken(String token) {
        System.out.println(token);
        if (StringUtils.isEmpty(token)) { // token为空，无效
            return false;
        }

        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(TOKEN_SECRET) // 创建token时使用的密钥
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) { // 解析token时抛出异常，token无效
            throw new LoginException.TokenException();
        }

        return true;
    }
}
