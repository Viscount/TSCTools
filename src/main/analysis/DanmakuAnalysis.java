package main.analysis;

import main.collection.Global;
import main.entity.Danmaku;
import main.entity.Matrix;
import main.entity.TimeWindow;
import main.util.ExtractUtil;
import main.util.NoiseWiper;
import main.util.WindowBuilder;
import main.util.XMLUtil;
import org.dom4j.Document;
import main.util.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by TongjiSSE on 2015/11/12.
 */
public class DanmakuAnalysis {

    private static int WINDOW_SIZE = 3;
    private static double CLIP_SIZE = 30;
    private static int WINDOW_SLIDE_SIZE = 1;

    public static void main(String[] args){
        Document xml = XMLUtil.readXML(".\\data\\movie\\2065063.xml");
        List<Danmaku> danmakuList = XMLUtil.extractFromFile(xml);
        Collections.sort(danmakuList);
        Global.init();
        Global.userID = new ExtractUtil(danmakuList).extractUser();
        NoiseWiper.dict_init();
        WindowBuilder windowBuilder = new WindowBuilder(CLIP_SIZE,CLIP_SIZE);
        List<TimeWindow> timeWindowClipList = windowBuilder.buildWindows(danmakuList);
        List userAlive = new ArrayList<Integer>();
        List numOfDanmaku = new ArrayList<Integer>();
        List averageLength = new ArrayList<Double>();
        for ( TimeWindow timeWindow : timeWindowClipList ){
            userAlive.add(timeWindow.getUserAlive());
            numOfDanmaku.add(timeWindow.getNumOfDanmaku());
            averageLength.add(timeWindow.getAverageLength());
        }
//        List<Integer> indexList = ArrayUtil.selectTop(numOfDanmaku,0.2);
//        List candidateClips = new ArrayList<TimeWindow>();
//        for ( Integer index : indexList ) candidateClips.add(timeWindowClipList.get(index));
        windowBuilder.setClipParam(WINDOW_SIZE, WINDOW_SLIDE_SIZE);
        List<TimeWindow> timeWindows = windowBuilder.buildWindowsFromClip(timeWindowClipList);
        int matrixID = 0;
        for ( TimeWindow timeWindow : timeWindows ){
            Matrix matrix = new Matrix(timeWindow);
            matrix.output(matrixID," ");
            matrixID++;
            timeWindow.output();
        }
        Global.output();
        FileUtil.output2File(numOfDanmaku,"numOfDanmaku.txt");
    }
}
