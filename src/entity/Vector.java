package entity;

import java.util.List;
import java.util.Map;

/**
 * Created by TongjiSSE on 2015/11/16.
 */
public class Vector {
    private Map<String,Long> detail;

    public Vector(Map<String, Long> detail) {
        this.detail = detail;
    }

    public Map<String, Long> getDetail() {
        return detail;
    }

    public static double simularity(Vector v1, Vector v2){
        return 0;
    }
}
