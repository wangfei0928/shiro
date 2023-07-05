package com.wf.client;

import com.wf.tools.DigestsUtil;
import com.wf.tools.EncodesUtils;
import org.junit.Test;

import java.util.Map;

public class ClientTest {

    @Test
    public void testHex(){
        String val = "hello";
        String flag = EncodesUtils.encodeHex(val.getBytes());
        String valHandler = new String(EncodesUtils.decodeHex(flag));
        System.out.println("比较结果:" + val.equals(valHandler));
    }


    @Test
    public void testBase64(){
        String val = "hello";
        String flag = EncodesUtils.encode64(val.getBytes());
        String valHandler = new String(EncodesUtils.decodeBase64(flag));
        System.out.println("比较结果:" + val.equals(valHandler));
    }


    @Test
    public void testDigestsUtils(){
        Map<String, String> map = DigestsUtil.entryptPassword("123");
        System.out.println(map.toString());
    }
}
