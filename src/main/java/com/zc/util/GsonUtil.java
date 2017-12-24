package com.zc.util;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * 使用Google Gson写日志，要用这个类，避免每次都new Gson()
 *
 * @author dongyongjin
 * @version 1.0
 * @created 2015年7月7日
 */

public class GsonUtil {

    private static final Gson gson = new GsonBuilder().serializeNulls()
            .addSerializationExclusionStrategy(new CampaignsExclusionStrategy())
            .create();

    public static <T> String toJson(T t) {
        try {
            return gson.toJson(t);
        } catch (Exception e) {
        }
        return null;
    }

    public static <T> T toObject(String json, Class<T> type) {
        try {
            return gson.fromJson(json, type);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Google Gson 排除策略
     *
     * @author dongyongjin
     * @version 1.0
     * @created 2015年7月7日
     */
    static class CampaignsExclusionStrategy implements ExclusionStrategy {

        /**
         * 使用Gson写日志时排除意义不大的的占用磁盘空间的列
         */
        @Override
        public boolean shouldSkipField(FieldAttributes field) {
            return "fingerprint".equals(field.getName())
                    || "__isset_bit_vector".equals(field.getName())
                    || "optionals".equals(field.getName());
        }

        /**
         * 使用Gson写日志时需要排除的类
         */
        @Override
        public boolean shouldSkipClass(Class<?> clazz) {
            return false;
        }

    }
}

