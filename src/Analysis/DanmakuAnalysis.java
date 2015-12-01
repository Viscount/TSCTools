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
        Document xml = XMLUtil.readXML(".\\data\\movie\\3431021.xml");
        List<Danmaku> danmakuList = XMLUtil.extractFromFile(xml);
        Collections.sort(danmakuList);
        Global.init();
        System.setProperty("sun.net.client.defaultConnectTimeout", String
                .valueOf(10000));// （单位：毫秒）
        System.setProperty("sun.net.client.defaultReadTimeout", String
                .valueOf(10000)); // （单位：毫秒）
//        ExtractUtil extractUtil = new ExtractUtil(danmakuList);
//        extractUtil.persistParsedWords();

        Global.userID = new ExtractUtil(danmakuList).extractUser();
        NoiseWiper.dict_init();
        List<TimeWindow> timeWindowList = new WindowBuilder(WINDOW_SIZE,WINDOW_SLIDE_STEP).buildWindows(danmakuList);
        List<Matrix> matrixList = new ArrayList<Matrix>();
        int matrixId = 0;
        for ( TimeWindow timeWindow : timeWindowList ){
            Matrix matrix = new Matrix(timeWindow);
            matrix.output(matrixId," ");
            matrixId++;
        }
    }
}
