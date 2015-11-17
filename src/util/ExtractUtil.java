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

    public ExtractUtil(List<Danmaku> danmakuList){
        this.danmakuList = danmakuList;
        this.userID = new ArrayList<String>();
    }

    public List<String> extractUser(){
        for ( Danmaku danmaku : danmakuList ){
            if ( !userID.contains(danmaku.getSenderId()) ) userID.add(danmaku.getSenderId());
        }
        return userID;
    }

    public Map<String,Long> extractWords( String userID){
        String content = "";
        String danmakuPersistID = "";
        Map<String,Long> wordsCount = new HashMap<String,Long>();
        for ( Danmaku danmaku : danmakuList ){
            if( danmaku.getSenderId().equals(userID) ){
                content += danmaku.getContent();
                danmakuPersistID += danmaku.getId();
            }
        }

        String modifiedContent = NoiseWiper.replace(content);
        modifiedContent = modifiedContent.trim();
        if (modifiedContent.length()<=0) return null;

        List<Word> wordList;
        if ( PersistenceUtil.checkExist(danmakuPersistID) ) wordList = PersistenceUtil.read(danmakuPersistID);
        else {
            wordList = new LtpCloudUtil().parseText(modifiedContent);
            PersistenceUtil.persist(danmakuPersistID,JsonUtil.toJson(wordList));
        }
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
