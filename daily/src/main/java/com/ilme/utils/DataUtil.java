package com.ilme.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.UnsupportedEncodingException;
import java.util.Random;

/**
 * 数据生成工具
 *
 * @author yuwenkai
 * @date 2019-09-26 22:54
 **/
public class DataUtil {

    /**
     * 生成int类型的数组
     * @param length 长度
     */
    public static int[] getIntArr(int length) {
        int[] arr = new int[length];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = 1 + (int) (Math.random() * 100);
        }
        return arr;
    }

    /**
     * 将对象转换成格式化后的json
     *
     * @param o 对象
     */
    public static String toFormatJson(Object o) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        try {
            return objectMapper.writeValueAsString(o);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 随机一个汉字
     */
    private static char getRandomChar() {
        String str = "";
        int hightPos;
        int lowPos;

        Random random = new Random();

        hightPos = (176 + Math.abs(random.nextInt(39)));
        lowPos = (161 + Math.abs(random.nextInt(93)));

        byte[] b = new byte[2];
        b[0] = (Integer.valueOf(hightPos)).byteValue();
        b[1] = (Integer.valueOf(lowPos)).byteValue();

        try {
            str = new String(b, "GBK");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return str.charAt(0);
    }

    /**
     * 随机生成一组自定义数量的用户数据
     *
     * @param length 数量
     */
    public static User[] getUsers(int length) {
        User[] users = new User[length];
        for (int i = 0; i < users.length; i++) {
            long id = (long) (Math.random() * 1000000);
            int age = (int) (Math.random() * 100);
            String name = new String(new char[]{getRandomChar(), getRandomChar()});
            users[i] = new User(id, name, age);
        }
        return users;
    }

    @Getter
    @Setter
    @ToString
    @AllArgsConstructor
    @Accessors(chain = true)
    public static class User {
        long id;
        String name;
        int age;
    }
}
