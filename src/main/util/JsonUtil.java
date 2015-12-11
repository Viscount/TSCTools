package main.util;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by TongjiSSE on 2015/11/12.
 */
public class JsonUtil {

    private static final Gson gson;

    static {
        gson = new Gson();
    }


    public static String toJson(Object obj) {
        return gson.toJson(obj);
    }

    public static <T> T toObject(String json,Class<T> clazz) {
        return gson.fromJson(json,clazz);
    }

    public static <T> List<T> toObjectList(String json, Class<T> clazz){
        List<T> list = new ArrayList<T>();
//        JsonParser jsonParser = new JsonParser();
        JsonArray array = new JsonParser().parse(json).getAsJsonArray();
        for(final JsonElement elem : array){
            list.add(new Gson().fromJson(elem, clazz));
        }
        return list;
    }
}
