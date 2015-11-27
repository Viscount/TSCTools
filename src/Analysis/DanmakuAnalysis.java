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
        Document xml = XMLUtil.readXML(".\\data\\movie\\zimu.xml");
        List<Danmaku> danmakuList = XMLUtil.extractFromFile(xml);
        Collections.sort(danmakuList);
        Global.init();
        for ( Danmaku danmaku : danmakuList ){
            String content = danmaku.getContent();
            String danmakuPersistID = Long.toString(danmaku.getId());
            String modifiedContent = NoiseWiper.replace(content);
            modifiedContent = modifiedContent.trim();
            if (modifiedContent.length()<=0) continue;
            if ( !PersistenceUtil.checkExist(danmakuPersistID) ) {
                List<Word> wordList = new LtpCloudUtil().parseText(modifiedContent);
                PersistenceUtil.persist(danmakuPersistID, JsonUtil.toJson(wordList));
            }
        }

//        Global.userID = new ExtractUtil(danmakuList).extractUser();
//        NoiseWiper.dict_init();
//        List<TimeWindow> timeWindowList = new WindowBuilder(WINDOW_SIZE,WINDOW_SLIDE_STEP).buildWindows(danmakuList);
//        List<Matrix> matrixList = new ArrayList<Matrix>();
//        int matrixId = 0;
//        for ( TimeWindow timeWindow : timeWindowList ){
//            Matrix matrix = new Matrix(timeWindow);
//            matrix.output(matrixId," ");
//            matrixId++;
//        }
    }
}
