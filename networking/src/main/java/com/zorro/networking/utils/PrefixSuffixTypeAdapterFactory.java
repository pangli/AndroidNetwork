package com.zorro.networking.utils;

import static com.google.gson.stream.JsonToken.BEGIN_OBJECT;


import androidx.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.util.Map;

/**
 * @Author PangLi
 * @Date 2024/7/19 14:55
 * @Description PrefixSuffixTypeAdapterFactory
 *
 * <p>As with type adapters, factories must be <i>registered</i> with {@link
 * com.google.gson.GsonBuilder} and {@link retrofit2.converter.gson.GsonConverterFactory} for them to take effect:
 * <pre>
 * {@code
 * GsonBuilder builder = new GsonBuilder();
 * builder.registerTypeAdapterFactory(new PrefixSuffixTypeAdapterFactory("Zorro_","_Zorro"));
 * Gson gson = builder.create();
 * GsonConverterFactory gsonFactory = GsonConverterFactory.create(gson);
 * }
 *  </pre>
 */
public class PrefixSuffixTypeAdapterFactory implements TypeAdapterFactory {
    private final String prefix;
    private final String suffix;

    public PrefixSuffixTypeAdapterFactory(String prefix, String suffix) {
        this.prefix = prefix;
        this.suffix = suffix;
    }

    @Override
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
        final TypeAdapter<T> delegate = gson.getDelegateAdapter(this, type);
        final TypeAdapter<JsonElement> elementAdapter = gson.getAdapter(JsonElement.class);
        return newPrefixSuffixTypeAdapter(delegate, elementAdapter);
    }

    private <T> @NonNull TypeAdapter<T> newPrefixSuffixTypeAdapter(TypeAdapter<T> delegate, TypeAdapter<JsonElement> elementAdapter) {
        return new TypeAdapter<T>() {
            @Override
            public T read(JsonReader in) throws IOException {
                JsonToken token = in.peek();
                if (BEGIN_OBJECT == token) {
                    JsonObject targetJson = new JsonObject();
                    in.beginObject();
                    while (in.hasNext()) {
                        targetJson.add(prefix + in.nextName() + suffix, elementAdapter.read(in));
                    }
                    in.endObject();
                    return delegate.fromJsonTree(targetJson);
                } else {
                    return delegate.read(in);
                }

            }

            @Override
            public void write(JsonWriter out, T value) throws IOException {
                JsonElement jsonElement = delegate.toJsonTree(value);
                if (!jsonElement.isJsonObject()) {
                    delegate.write(out, value);
                    return;
                }
                elementAdapter.write(out, newJsonObject(jsonElement, new JsonObject()));
            }
        };
    }


    private JsonObject newJsonObject(JsonElement jsonElement, JsonObject targetJson) {
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        for (Map.Entry<String, JsonElement> entry : jsonObject.entrySet()) {
            targetJson.add(prefix + entry.getKey() + suffix, entry.getValue());
        }
        return targetJson;
    }
}

