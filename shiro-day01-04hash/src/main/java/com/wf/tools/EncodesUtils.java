package com.wf.tools;

import org.apache.shiro.codec.Base64;
import org.apache.shiro.codec.Hex;

public class EncodesUtils {


    /**
     * HEX-String-byte[]
     * @param input 输入数字
     * @return  字符串
     */
    public static String encodeHex(byte[] input){
        return Hex.encodeToString(input);
    }

    /**
     *  HEX - byte[] -String
     * @param input 输入字符串
     * @return byte数字
     */
    public static byte[] decodeHex(String input){
        return Hex.decode(input);
    }

    /**
     * Base64-String-byte[]
     * @param input 输入数字
     * @return  字符串
     */
    public static String encode64(byte[] input){
        return Base64.encodeToString(input);
    }


    /**
     *  Base64 - byte[] -String
     * @param input 输入字符串
     * @return byte数字
     */
    public static byte[] decodeBase64(String input){
        return Base64.decode(input);
    }

}
