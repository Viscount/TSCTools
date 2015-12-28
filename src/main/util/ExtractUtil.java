package main.util;

import main.entity.Danmaku;
import main.entity.Word;

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

    public void persistParsedWords(){
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
    }

    public Map<String,Object> extractWords( String userID ){
        String content = "";
        String danmakuPersistID = "";
        Map<String,Object> wordsCount = new HashMap<String,Object>();
        for ( Danmaku danmaku : danmakuList ){
            if( danmaku.getSenderId().equals(userID) ){
                content = content + " " +danmaku.getContent();
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
            if ( !word.getCont().equals(NoiseWiper.merge(word)))
                word.setCont(NoiseWiper.merge(word));
            if ( NoiseWiper.wipeWordType(word) ) continue;
            if ( !wordsCount.containsKey(word.getCont()) ) wordsCount.put(word.getCont(),1L);
            else {
                long count = (long)wordsCount.get(word.getCont());
                wordsCount.put(word.getCont(),count+1);
            }
        }
        return wordsCount;
    }

    public Map<String,Long> extractUserWords( String userID ){
        if (!NoiseWiper.filterUser(userID)) return null;
        Map<String,Long> wordsCount = new HashMap<String,Long>();
        for ( Danmaku danmaku : danmakuList ){
            if ( danmaku.getSenderId().equals(userID)){
                String content = danmaku.getContent();
                String danmakuPersistID = Long.toString(danmaku.getId());

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
                    if ( !word.getCont().equals(NoiseWiper.merge(word)))
                        word.setCont(NoiseWiper.merge(word));
                    if ( NoiseWiper.wipeWordType(word) ) continue;
                    if ( !wordsCount.containsKey(word.getCont()) ) wordsCount.put(word.getCont(),1L);
                    else {
                        long count = wordsCount.get(word.getCont());
                        wordsCount.put(word.getCont(),count+1);
                    }
                }
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
