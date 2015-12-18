package main.analysis;

import main.entity.Danmaku;
import main.entity.Matrix;
import main.entity.TimeWindow;
import main.util.FileUtil;
import main.util.NoiseWiper;
import main.util.WindowBuilder;
import main.util.XMLUtil;
import org.dom4j.Document;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by TongjiSSE on 2015/12/17.
 */
public class AnimationAnalysis {

    private static String PATH = ".\\data\\animation\\FateUBW";
    private static String ANIMATION_NAME = "FateUBW";
    private static int EP_NUM = 13;

    private static double WINDOW_SIZE;
    private static double WINDOW_SLIDE_STEP;

    public static void main(String[] args){
        List<TimeWindow> timeWindowClipList = new ArrayList<TimeWindow>();
        for ( int i = 0; i< EP_NUM; i++ ) {
            List<TimeWindow> episodeTimeWindowClips = new ArrayList<TimeWindow>();
            Document xml = XMLUtil.readXML(PATH + FileUtil.generateAnimationFileName(ANIMATION_NAME, i));
            List<Danmaku> danmakuList = XMLUtil.extractFromFile(xml);
            Collections.sort(danmakuList);
            NoiseWiper.dict_init();

            double latest = danmakuList.get(danmakuList.size()-1).getVideoSecond();
            WINDOW_SIZE = latest / 3;
            WINDOW_SLIDE_STEP = WINDOW_SIZE;
            WindowBuilder windowBuilder = new WindowBuilder(WINDOW_SLIDE_STEP,WINDOW_SLIDE_STEP);
            episodeTimeWindowClips = windowBuilder.buildWindows(danmakuList);
            timeWindowClipList.addAll(episodeTimeWindowClips);
        }
        WindowBuilder windowBuilder = new WindowBuilder(3);
        List<TimeWindow> timeWindows = windowBuilder.buildWindowsFromClip(timeWindowClipList);
        int matrixID = 0;
        for ( TimeWindow timeWindow : timeWindows ){
            Matrix matrix = new Matrix(timeWindow);
            matrix.output(matrixID," ");
            matrixID++;
        }
    }

}
