package com.sipc.clockin.utils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * 数据解析工具类
 * json字符串解析成对象
 */

public class DataParseUtils {
    private static final ObjectMapper objectMapper = new ObjectMapper();


    // 将JSON字符串解析为特定类型的Java对象
    public static <T> T parseJson(Object json, Class<T> clazz) {
        try {
            return objectMapper.readValue((JsonParser) json, clazz);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    // 将JSON字符串解析为Map<String, Object>
    public static Map<String, Object> parseJsonToMap(String json) {
        try {
            return objectMapper.readValue(json, Map.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    public static <T> List<T> parseJsonToList(String json,Class<T> clazz)  {
        try {
            return objectMapper.readValue(json, objectMapper.getTypeFactory().constructCollectionType(List.class, clazz));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static String parseMapToJson(Map<String,Object> map) {
        try {

            return objectMapper.writeValueAsString(map);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;

    }
    public static <T> String parseListToJson(List<T> list) {
        try {
            return objectMapper.writeValueAsString(list);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }
}