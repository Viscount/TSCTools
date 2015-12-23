package main.analysis;

import main.collection.Global;
import main.entity.Danmaku;
import main.entity.Matrix;
import main.entity.TimeWindow;
import main.util.*;
import org.dom4j.Document;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Jaric Liao on 2015/12/14.
 */
public class DanmakuAnalysisOld {

    private static double WINDOW_SIZE = 30;
    private static double WINDOW_SLIDE_STEP = 10;

    public static void main(String[] args){
        Document xml = XMLUtil.readXML(".\\data\\movie\\2065063.xml");
        List<Danmaku> danmakuList = XMLUtil.extractFromFile(xml);
        Collections.sort(danmakuList);
        Global.init();
        Global.userID = new ExtractUtil(danmakuList).extractUser();
        NoiseWiper.dict_init();
        WindowBuilder windowBuilder = new WindowBuilder(WINDOW_SIZE,WINDOW_SLIDE_STEP);
        List<TimeWindow> timeWindowClipList = windowBuilder.buildWindows(danmakuList);
        List userAlive = new ArrayList<Integer>();
        List numOfDanmaku = new ArrayList<Integer>();
        List averageLength = new ArrayList<Double>();
        int matrixID = 0;
        for ( TimeWindow timeWindow : timeWindowClipList ){
            userAlive.add(timeWindow.getUserAlive());
            numOfDanmaku.add(timeWindow.getNumOfDanmaku());
            averageLength.add(timeWindow.getAverageLength());
//            Matrix matrix = new Matrix(timeWindow);
//            matrix.output(matrixID, " ");
//            matrixID++;
            timeWindow.outputMerge();
        }
        Global.output();
        FileUtil.output2File(numOfDanmaku,"numOfDanmaku10.txt");
        FileUtil.output2File(userAlive,"userAlive10.txt");
    }
}
