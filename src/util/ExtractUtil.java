package util;

import entity.Danmaku;
import entity.Word;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by TongjiSSE on 2015/11/17.
 */
public class ExtractUtil {

    private List<String> userID;
    private List<Danmaku> danmakuList;

    public List<String> extractUser(){
        for ( Danmaku danmaku : danmakuList ){
            if ( !userID.contains(danmaku.getSenderId()) ) userID.add(danmaku.getSenderId());
        }
        return userID;
    }

    public Map<String,Long> extractWords( String userID){
        String content = "";
        Map<String,Long> wordsCount = new HashMap<String,Long>();
        for ( Danmaku danmaku : danmakuList ){
            if( danmaku.getSenderId().equals(userID) ) content += danmaku.getContent();
        }
        List<Word> wordList = new LtpCloudUtil().parseText(content);
        for ( Word word : wordList ){
            if ( !wordsCount.containsKey(word.getCont()) ) wordsCount.put(word.getCont(),1L);
            else {
                long count = wordsCount.get(word.getCont());
                wordsCount.put(word.getCont(),count+1);
            }
        }
        return wordsCount;
    }

    public void setDanmakuList(List<Danmaku> danmakuList) {
        this.danmakuList = danmakuList;
    }

    public List<String> getUserID() {
        return userID;
    }
}
