package com.zorro.networking.interfaces;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;

/**
 * Package:   com.zorro.networking.interfaces
 * ClassName: Parser
 * Created by Zorro on 2020/5/6 19:43
 * 备注：  解析器
 */
public interface Parser<F, T> {

    T convert(F value) throws IOException;

    abstract class Factory {

        public Parser<ResponseBody, ?> responseBodyParser(Type type) {
            return null;
        }

        public Parser<?, RequestBody> requestBodyParser(Type type) {
            return null;
        }

        public Object getObject(String string, Type type) {
            return null;
        }

        public String getString(Object object) {
            return null;
        }

        public HashMap<String, String> getStringMap(Object object) {
            return null;
        }

    }

}