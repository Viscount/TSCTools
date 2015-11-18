package util;

import entity.Danmaku;

import java.util.*;

/**
 * Created by Jaric Liao on 2015/11/16.
 */
public class NoiseWiper {

    private static Set<String> punctuation = new HashSet<String>(Arrays.asList(",",".","!","?","/","\\",":",";","...","=","-","←","#","@","$","%","`","*","&",
                                                                            "，","。","！","？","/","：","；","。。。","……"));

    public static String replace( String rawContent ){
        for ( String s : punctuation ){
            rawContent = rawContent.replace(s," ");
        }
        return rawContent;
    }

    public static List<Danmaku> removeDuplicate( List<Danmaku> danmakuList ){
        List<Danmaku> noDupList = new ArrayList<Danmaku>();
        Danmaku lastDanmaku = null;
        for ( Danmaku danmaku : danmakuList ){
            if (( lastDanmaku == null ) || (!lastDanmaku.getSenderId().equals(danmaku.getSenderId())) || (!lastDanmaku.getContent().equals(danmaku.getContent()))) {
                noDupList.add(danmaku);
                lastDanmaku = danmaku;
            }
        }
        return noDupList;
    }
}
