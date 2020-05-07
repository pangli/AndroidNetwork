package com.zorro.networking.gsonparserfactory;

import com.zorro.networking.interfaces.Parser;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;

import java.io.IOException;

import okhttp3.ResponseBody;

/**
 * Package:   com.zorro.networking.gsonparserfactory
 * ClassName: GsonResponseBodyParser
 * Created by Zorro on 2020/5/6 19:30
 * 备注：Gson 返回数据解析器
 */
final class GsonResponseBodyParser<T> implements Parser<ResponseBody, T> {
    private final Gson gson;
    private final TypeAdapter<T> adapter;

    GsonResponseBodyParser(Gson gson, TypeAdapter<T> adapter) {
        this.gson = gson;
        this.adapter = adapter;
    }

    @Override
    public T convert(ResponseBody value) throws IOException {
        JsonReader jsonReader = gson.newJsonReader(value.charStream());
        try {
            return adapter.read(jsonReader);
        } finally {
            value.close();
        }
    }
}
