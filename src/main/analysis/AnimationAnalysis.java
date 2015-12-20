package main.analysis;

import main.entity.Danmaku;
import main.entity.Matrix;
import main.entity.TimeWindow;
import main.util.*;
import org.dom4j.Document;

import java.util.*;

/**
 * Created by TongjiSSE on 2015/12/17.
 */
public class AnimationAnalysis {

    private static String PATH = ".\\data\\animation\\FateUBW\\";
    private static String ANIMATION_NAME = "FateUBW";
    private static int EP_NUM = 13;

    private static double WINDOW_SIZE;
    private static double WINDOW_SLIDE_STEP;

    public static void main(String[] args){
        NoiseWiper.dict_init();
        List<String> userFilter = new ArrayList<>();
        Map<String,Long> userCount = new HashMap<String,Long>();
        for ( int i=0; i<EP_NUM; i++ ){
            Document xml = XMLUtil.readXML(PATH + FileUtil.generateAnimationFileName(ANIMATION_NAME, i));
            List<Danmaku> danmakuList = XMLUtil.extractFromFile(xml);
            List<String> episodeUser = new ExtractUtil(danmakuList).extractUser();
            for ( String userID : episodeUser ){
                if ( userCount.containsKey(userID) ){
                    long count = userCount.get(userID);
                    userCount.put(userID,count+1);
                }
                else userCount.put(userID,1L);
            }
        }
        List<String> allUser = new ArrayList<String>();
        List<Long> allCount = new ArrayList<Long>();
        for ( Map.Entry<String,Long> entry : userCount.entrySet()){
            allUser.add(entry.getKey());
            allCount.add(entry.getValue());
        }
        List<Integer> position = ArrayUtil.selectTop(allCount,0.2);
        userFilter = ArrayUtil.selectFromPositionList(allUser,position);
        NoiseWiper.setUserFilter(userFilter);

        List<TimeWindow> timeWindowClipList = new ArrayList<TimeWindow>();
        for ( int i = 0; i< EP_NUM; i++ ) {
            List<TimeWindow> episodeTimeWindowClips = new ArrayList<TimeWindow>();
            Document xml = XMLUtil.readXML(PATH + FileUtil.generateAnimationFileName(ANIMATION_NAME, i));
            List<Danmaku> danmakuList = XMLUtil.extractFromFile(xml);
            Collections.sort(danmakuList);

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
