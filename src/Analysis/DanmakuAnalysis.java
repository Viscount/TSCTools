package analysis;

import collection.Global;
import entity.Danmaku;
import entity.Matrix;
import entity.TimeWindow;
import entity.Word;
import org.dom4j.Document;
import util.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by TongjiSSE on 2015/11/12.
 */
public class DanmakuAnalysis {

    private static double WINDOW_SIZE = 30;
    private static double WINDOW_SLIDE_STEP = 10;

    public static void main(String[] args){
        Document xml = XMLUtil.readXML(".\\data\\movie\\2065063.xml");
        List<Danmaku> danmakuList = XMLUtil.extractFromFile(xml);
        Collections.sort(danmakuList);
        Global.init();
        Global.userID = new ExtractUtil(danmakuList).extractUser();
        NoiseWiper.dict_init();
        List<TimeWindow> timeWindowList = new WindowBuilder(WINDOW_SIZE,WINDOW_SLIDE_STEP).buildWindows(danmakuList);
        List<Matrix> matrixList = new ArrayList<Matrix>();
        List userAlive = new ArrayList();
        List numOfDanmaku = new ArrayList();
        List averageLength = new ArrayList();
        int matrixId = 0;
        for ( TimeWindow timeWindow : timeWindowList ){
            timeWindow.output();
            Matrix matrix = new Matrix(timeWindow);
            matrix.output(matrixId," ");
            matrixId++;
            userAlive.add(timeWindow.getUserAlive());
            numOfDanmaku.add(timeWindow.getNumOfDanmaku());
            averageLength.add(timeWindow.getAverageLength());
        }
        FileUtil.output2File(userAlive,"userAlive.txt");
        FileUtil.output2File(numOfDanmaku,"numOfDanmaku.txt");
        FileUtil.output2File(averageLength,"averageLength.txt");
    }
}
