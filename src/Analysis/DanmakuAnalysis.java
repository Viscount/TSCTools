package analysis;

import collection.Global;
import entity.Danmaku;
import entity.Word;
import org.dom4j.Document;
import util.JsonUtil;
import util.LtpCloudUtil;
import util.XMLUtil;

import java.util.Collections;
import java.util.List;

/**
 * Created by TongjiSSE on 2015/11/12.
 */
public class DanmakuAnalysis {

    private static double WINDOW_SIZE = 30;
    private static double WINDOW_SLIDE_STEP = 10;

    public static void extractCollection( List<Danmaku> danmakuList ){
        Global.init();
        for ( Danmaku danmaku : danmakuList ){
            if ( !Global.userID.contains(danmaku.getSenderId()) ) Global.userID.add(danmaku.getSenderId());
            List<Word> contentWords = LtpCloudUtil.parseText(danmaku.getContent());
            for ( Word word : contentWords ){
                if ( !Global.words.containsKey(word.getCont()) ) Global.words.put(word.getCont(),1L);
                else {
                    long count = Global.words.get(word.getCont());
                    Global.words.put(word.getCont(),count+1);
                }
            }
        }
        Global.output();
    }

    public static void main(String[] args){
        Document xml = XMLUtil.readXML("D:\\Develop\\workspace\\TSCTools\\data\\movie\\2065063.xml");
        List<Danmaku> danmakuList = XMLUtil.extractFromFile(xml);
        Collections.sort(danmakuList);
        extractCollection(danmakuList);
    }
}
