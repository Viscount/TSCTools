package main.analysis;

import main.entity.Danmaku;
import main.entity.TimeWindow;
import main.util.FileUtil;
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

    public static void main(String[] args){
        List<TimeWindow> timeWindowClipList = new ArrayList<TimeWindow>();
        for ( int i = 0; i< EP_NUM; i++ ) {
            Document xml = XMLUtil.readXML(PATH + FileUtil.generateAnimationFileName(ANIMATION_NAME, i));
            List<Danmaku> danmakuList = XMLUtil.extractFromFile(xml);
            Collections.sort(danmakuList);

        }
    }

}
