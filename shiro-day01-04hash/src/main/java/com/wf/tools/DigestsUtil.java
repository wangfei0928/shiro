package com.wf.tools;

import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;

import java.util.HashMap;
import java.util.Map;

public class DigestsUtil {

    private static final String SHA1 = "SHA-1";
    private static final Integer ITERATIONS = 512;

    /**
     * sha1摘要算法
     *
     * @param input 明文字符串
     * @param salt  干扰数据
     * @return
     */
    public static String sha1(String input, String salt) {
        return new SimpleHash(SHA1, input, salt, ITERATIONS).toString();
    }


    /**
     * 随机生成salt
     *
     * @return hex编码的salt
     */
    public static String generateSalt() {
        SecureRandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();
        return randomNumberGenerator.nextBytes().toHex();
    }

    /**
     *   生成密码和salt的密文
     * @param passwordPlan
     * @return map->salt和密文密码
     */
    public static Map<String,String> entryptPassword(String passwordPlan){
        HashMap<String, String> map = new HashMap<>();
        String salt = generateSalt();
        String password = sha1(passwordPlan,salt);
        map.put("salt",salt);
        map.put("password",password);
        return map;
    }
}
