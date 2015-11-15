package util;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

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

    public static String getClassName(String json) throws Exception{
        JsonParser jsonParser = new JsonParser();
        JsonObject jsonObject = jsonParser.parse(json).getAsJsonObject();
        JsonElement element = jsonObject.get("_class");
        if ( element!= null) return element.getAsString();
        return null;
    }

    public static <T> T toObject(String json,Class<T> clazz) {
        return gson.fromJson(json,clazz);
    }

    public static <T> List<T> toObjectList(String json, Class<T> clazz){
        List<T> objectList = gson.fromJson(json, new TypeToken<List<T>>(){}.getType());
        return objectList;
    }
}
