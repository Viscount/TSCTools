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
 * Created by Jaric Liao on 2015/12/29.
 */
public class DanmakuTopicAnalysis {

    private static double WINDOW_SIZE = 30;
    private static double WINDOW_SLIDE_STEP = 10;
    private static String path = ".\\window";

    public static void main(String[] args){
        Document xml = XMLUtil.readXML(".\\data\\movie\\2065063.xml");
        List<Danmaku> danmakuList = XMLUtil.extractFromFile(xml);
        Collections.sort(danmakuList);
        Global.init();
        Global.userID = new ExtractUtil(danmakuList).extractUser();
        NoiseWiper.dict_init();
        WindowBuilder windowBuilder = new WindowBuilder(WINDOW_SIZE,WINDOW_SLIDE_STEP);
        List<TimeWindow> timeWindowClipList = windowBuilder.buildWindowsFromFile(path);
        List userAlive = new ArrayList<Integer>();
        List numOfDanmaku = new ArrayList<Integer>();
        List averageLength = new ArrayList<Double>();
        Collections.sort(timeWindowClipList);
        for ( TimeWindow timeWindow : timeWindowClipList ){
            userAlive.add(timeWindow.getUserAlive());
            numOfDanmaku.add(timeWindow.getNumOfDanmaku());
            averageLength.add(timeWindow.getAverageLength());
            Matrix matrix = new Matrix(timeWindow);
            int matrixID = Integer.parseInt(Long.toString(timeWindow.getId()));
            matrix.output(matrixID, " ");
        }
        Global.output();
        FileUtil.output2File(numOfDanmaku, "numOfDanmaku.txt");
        FileUtil.output2File(userAlive,"userAlive.txt");
        FileUtil.output2File(averageLength,"averageLength.txt");
    }
}
