package com.example.cp6.pojo;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.nio.ByteBuffer;

/**
 * @description:
 * @author: icecrea
 * @create: 2019-06-21 20:54
 **/
public class PerformTestUserInfo {
    public static void main(String[] args) throws Exception {
        UserInfo info = new UserInfo();
        info.buildUserName("Welcome to Netty").buildUserID(123);
        int loop = 1000000;
        ByteArrayOutputStream bos = null;
        ObjectOutputStream os = null;
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < loop; i++) {
            bos = new ByteArrayOutputStream();
            os = new ObjectOutputStream(bos);
            os.writeObject(info);
            os.flush();
            os.close();
            byte[] b = bos.toByteArray();
            bos.close();
        }
        long endTime = System.currentTimeMillis();
        System.out.println("jdk seria cost time is " + (endTime - startTime) + "ms");

        ByteBuffer buffer = ByteBuffer.allocate(1024);
        startTime = System.currentTimeMillis();
        for (int i = 0; i < loop; i++) {
            byte[] b = info.codeC(buffer);
        }
        endTime = System.currentTimeMillis();
        System.out.println("the byte array seria time is :" + (endTime - startTime));
    }
}
