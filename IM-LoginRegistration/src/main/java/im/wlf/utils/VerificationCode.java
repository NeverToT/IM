package im.wlf.utils;

import java.util.Random;

/**
 * 验证码生成
 */
public class VerificationCode {
    public static String generateVerificationCode(int codeLength){
        Random random = new Random();
        int min = (int) Math.pow(10,codeLength - 1);
        int max = (int) Math.pow(10,codeLength) - 1;
        return String.valueOf(random.nextInt(max - min + 1) + min);
    }
}
