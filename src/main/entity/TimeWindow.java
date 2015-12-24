package main.entity;

import main.util.JsonUtil;
import main.util.ExtractUtil;

import java.io.File;
import java.io.FileWriter;
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

    public TimeWindow(long id, double startTime, double endTime) {
        this.id = id;
        this.endTime = endTime;
        this.startTime = startTime;
        userFeature = new HashMap<String, Vector>();
    }

    public TimeWindow(long id, List<TimeWindow> timeWindowList){
        this.id = id;
        Map<String,Vector> userFeatureMerge = new HashMap<String,Vector>();
        for ( TimeWindow timeWindow : timeWindowList ){
            Map<String, Vector> userFeatureOld = timeWindow.getUserFeature();
            for ( Map.Entry<String, Vector> entry : userFeatureOld.entrySet() ){
                String userID = entry.getKey();
                Vector userVec = entry.getValue();
                if ( userFeatureMerge.containsKey(userID) ){
                    Vector oldUserVec = userFeatureMerge.get(userID);
                    userFeatureMerge.put(userID,Vector.merge(oldUserVec,userVec));
                }
                else userFeatureMerge.put(userID,userVec);
            }
        }
        this.userFeature = userFeatureMerge;
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
        for ( String s : userIDList ){
            Map<String,Long> userWords = extractUtil.extractWords(s);
            if ( userWords == null ) continue;
            Vector vector = new Vector(userWords);
            userFeature.put(s,vector);
            averageLength += vector.getSize();
        }
        this.averageLength = averageLength/numOfDanmaku;
    }

    public Vector getAllMerge(){
        Vector result = null;
        for (  Map.Entry<String, Vector> entry : userFeature.entrySet() ){
            Vector current = entry.getValue();
            result = Vector.merge(result,current);
        }
        return result;
    }

    public void output(){
        try {
            File file = new File(".\\window\\Window" + id + ".txt");
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(JsonUtil.toJson(this));
            fileWriter.close();
        } catch ( Exception e){
            e.printStackTrace();
        }
    }

    public void outputMerge(){
        try {
            File file = new File(".\\window\\WindowMerge" + id + ".txt");
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(JsonUtil.toJson(this.getAllMerge()));
            fileWriter.close();
        } catch ( Exception e){
            e.printStackTrace();
        }
    }
}
