package util;

import entity.Danmaku;
import entity.TimeWindow;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TongjiSSE on 2015/11/17.
 */
public class WindowBuilder {

    private double windowSize;
    private double windSlideStep;

    public WindowBuilder(double window_size, double window_slide_step){
        this.windowSize = window_size;
        this.windSlideStep = window_slide_step;
    }

    public List<TimeWindow> buildWindows(List<Danmaku> sortedList){
        long idCount = 0;
        double currentWindowStart = 0;
        double currentWindowEnd = currentWindowStart + windowSize;
        List<TimeWindow> result = new ArrayList<TimeWindow>();
        while( sortedList.size() > 0 ){
            List<Danmaku> currentList = new ArrayList<Danmaku>();
            for ( Danmaku danmaku : sortedList ){
                if ( danmaku.getVideoSecond() < currentWindowEnd ){
                    currentList.add(danmaku);
                }
                else break;
            }

            TimeWindow timeWindow = new TimeWindow(idCount,currentWindowStart,currentWindowEnd);
            idCount++;
            timeWindow.buildFromDanmaku(currentList);
            result.add(timeWindow);

            currentWindowStart += windSlideStep;
            currentWindowEnd = currentWindowStart + windowSize;
            for ( Danmaku danmaku : currentList ){
                if ( danmaku.getVideoSecond() < currentWindowStart ) sortedList.remove(danmaku);
                else break;
            }
        }
        return result;
    }
}
