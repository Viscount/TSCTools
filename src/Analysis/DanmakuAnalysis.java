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

//    public static class Extractor extends Thread{
//        private List<Word> parseResult;
//        private String text;
//
//        public Extractor(String text){
//            this.text = text;
//        }
//        public void run(){
//            LtpCloudUtil ltpCloudUtil = new LtpCloudUtil();
//            parseResult = ltpCloudUtil.parseText(text);
//        }
//        public List<Word> getParseResult(){
//            return parseResult;
//        }
//    }

//    public static void extractCollection( List<Danmaku> danmakuList ){
//        Global.init();
//        List<Danmaku> noDupDanmakuList = NoiseWiper.removeDuplicate(danmakuList);
//        for ( Danmaku danmaku : noDupDanmakuList ){
//            if ( !Global.userID.contains(danmaku.getSenderId()) ) Global.userID.add(danmaku.getSenderId());
//            System.out.println("Danmaku ID : "+danmaku.getId() + " start parsing...");
//
//            String modifiedContent = NoiseWiper.replace(danmaku.getContent());
//            modifiedContent = modifiedContent.trim();
//            if (( modifiedContent == null ) ||  ( modifiedContent.length() <= 0)) continue;
//
//            Extractor extractor = new Extractor(modifiedContent);
//            extractor.run();
//            List<Word> contentWords = extractor.getParseResult();
//
//            for ( Word word : contentWords ){
//                if ( !Global.words.containsKey(word.getCont()) ) Global.words.put(word.getCont(),1L);
//                else {
//                    long count = Global.words.get(word.getCont());
//                    Global.words.put(word.getCont(),count+1);
//                }
//            }
//        }
//        Global.output();
//    }

    public static void main(String[] args){
        Document xml = XMLUtil.readXML(".\\data\\movie\\2065063.xml");
        List<Danmaku> danmakuList = XMLUtil.extractFromFile(xml);
        Collections.sort(danmakuList);
        Global.init();
        Global.userID = new ExtractUtil(danmakuList).extractUser();
        List<TimeWindow> timeWindowList = new WindowBuilder(WINDOW_SIZE,WINDOW_SLIDE_STEP).buildWindows(danmakuList);
        List<Matrix> matrixList = new ArrayList<Matrix>();
        for ( TimeWindow timeWindow : timeWindowList ){
            Matrix matrix = new Matrix(timeWindow);
            matrix.output();
        }
    }
}
