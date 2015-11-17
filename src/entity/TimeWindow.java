package entity;

import util.ExtractUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by TongjiSSE on 2015/11/13.
 */
public class TimeWindow {
    private long id;
    private double startTime, endTime;
    private Map<String,Vector> userFeature;

    public TimeWindow(long id, double endTime, double startTime) {
        this.id = id;
        this.endTime = endTime;
        this.startTime = startTime;
        userFeature = new HashMap<String, Vector>();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getStartTime() {
        return startTime;
    }

    public void setStartTime(double startTime) {
        this.startTime = startTime;
    }

    public double getEndTime() {
        return endTime;
    }

    public void setEndTime(double endTime) {
        this.endTime = endTime;
    }

    public void buildFromDanmaku(List<Danmaku> danmakuList){
        ExtractUtil extractUtil = new ExtractUtil();
        extractUtil.setDanmakuList(danmakuList);
        List<String> userIDList = extractUtil.extractUser();
        for ( String s : userIDList ){
            Map<String,Long> userWords = extractUtil.extractWords(s);
            Vector vector = new Vector(userWords);
            userFeature.put(s,vector);
        }
    }
}
