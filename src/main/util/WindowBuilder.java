package main.util;

import main.entity.Danmaku;
import main.entity.TimeWindow;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TongjiSSE on 2015/11/17.
 */
public class WindowBuilder {

    private double windowSize;
    private double windSlideStep;
    private int clipSize;

    public WindowBuilder(double window_size, double window_slide_step){
        this.windowSize = window_size;
        this.windSlideStep = window_slide_step;
    }

    public WindowBuilder(int clipSize){
        this.clipSize = clipSize;
    }

    public void setClipSize(int n){
        this.clipSize = n;
    }

    public List<TimeWindow> buildWindowsFromClip(List<TimeWindow> timeWindowsClip){
        long idCount = 0;
        List<TimeWindow> result = new ArrayList<TimeWindow>();
        for ( int i=0; i<timeWindowsClip.size()-clipSize; i++){
            List<TimeWindow> candidateClip = new ArrayList<TimeWindow>();
            for (int j=0; j<clipSize; j++){
                candidateClip.add(timeWindowsClip.get(i+j));
            }
            TimeWindow timeWindow = new TimeWindow(idCount,candidateClip);
            result.add(timeWindow);
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
