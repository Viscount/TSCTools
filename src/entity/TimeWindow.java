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
    private int userAlive;
    private int numOfDanmaku;
    private double averageLength;
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

    public Map<String, Vector> getUserFeature() {
        return userFeature;
    }

    public int getUserAlive() {
        return userAlive;
    }

    public int getNumOfDanmaku() {
        return numOfDanmaku;
    }

    public double getAverageLength() {
        return averageLength;
    }

    public void buildFromDanmaku(List<Danmaku> danmakuList){
        ExtractUtil extractUtil = new ExtractUtil(danmakuList);
        List<String> userIDList = extractUtil.extractUser();
        userAlive = userIDList.size();
        numOfDanmaku = danmakuList.size();
        averageLength = 0;
        for ( Danmaku danmaku : danmakuList ) averageLength += danmaku.getContent().length();
        averageLength = averageLength / numOfDanmaku;
        for ( String s : userIDList ){
            Map<String,Long> userWords = extractUtil.extractWords(s);
            if ( userWords == null ) continue;
            Vector vector = new Vector(userWords);
            userFeature.put(s,vector);
        }
    }
}
