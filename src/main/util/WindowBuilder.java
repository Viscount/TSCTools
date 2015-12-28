package main.util;

import main.entity.Danmaku;
import main.entity.TimeWindow;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by TongjiSSE on 2015/11/17.
 */
public class WindowBuilder {

    private double windowSize;
    private double windSlideStep;
    private int clipSize;
    private int clipSlideStep;

    public WindowBuilder(double window_size, double window_slide_step){
        this.windowSize = window_size;
        this.windSlideStep = window_slide_step;
    }

    public WindowBuilder(int clipSize){
        this.clipSize = clipSize;
    }

    public void setClipParam(int n,int slide){
        this.clipSize = n;
        this.clipSlideStep = slide;
    }

    public List<TimeWindow> buildWindowsFromFile(String folderPath){
        List<TimeWindow> result = new ArrayList<TimeWindow>();
        try {
            File folder = new File(folderPath);
            for (File file : folder.listFiles()) {
                BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
                String content = "";
                String line;
                while ((line = br.readLine()) != null) content += line;
                br.close();
                TimeWindow timeWindow = JsonUtil.toObject(content, TimeWindow.class);
                result.add(timeWindow);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    public List<TimeWindow> buildWindowsFromClip(List<TimeWindow> timeWindowsClip){
        long idCount = 0;
        List<TimeWindow> result = new ArrayList<TimeWindow>();
        for ( int i=0; i<timeWindowsClip.size(); i+= clipSlideStep ){
            List<TimeWindow> candidateClip = new ArrayList<TimeWindow>();
            for (int j=0; j<clipSize; j++){
                if( i+j < timeWindowsClip.size() ) candidateClip.add(timeWindowsClip.get(i+j));
            }
            TimeWindow timeWindow = new TimeWindow(idCount,candidateClip);
            result.add(timeWindow);
            idCount++;
        }
        return result;
    }

    public List<TimeWindow> buildWindows(List<Danmaku> sortedList){
        long idCount = 0;
        double currentWindowStart = 0;
        double currentWindowEnd = Math.ceil(currentWindowStart + windowSize);
        List<TimeWindow> result = new ArrayList<TimeWindow>();
        while( sortedList.size() > 0 ){
            List<Danmaku> currentList = new ArrayList<Danmaku>();
            for ( Danmaku danmaku : sortedList ){
                if ( danmaku.getVideoSecond() < currentWindowEnd ){
                    currentList.add(danmaku);
                }
                else break;
            }

            System.out.println("Building window " + idCount + "...");

            TimeWindow timeWindow = new TimeWindow(idCount,currentWindowStart,currentWindowEnd);
            idCount++;
            timeWindow.buildFromDanmaku(currentList);
            result.add(timeWindow);

            currentWindowStart = Math.ceil(currentWindowStart + windSlideStep);
            currentWindowEnd = Math.ceil(currentWindowStart + windowSize);
            for ( Danmaku danmaku : currentList ){
                if ( danmaku.getVideoSecond() < currentWindowStart ) sortedList.remove(danmaku);
                else break;
            }
        }
        return result;
    }
}
