package main.util;

import main.entity.DictionaryItem;
import main.entity.Word;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Jaric Liao on 2015/11/16.
 */
public class NoiseWiper {

    private static Set<String> punctuation = new HashSet<String>(Arrays.asList(",",".","!","?","/","\\",":",";","...","=","-","←","#","@","$","%","`","*","&","(",")","\"","+",
                                                                            "，","。","！","？","/","：","；","。。。","……","（","）","“","”","‘","’"));
    private static Set<String> wordType = new HashSet(Arrays.asList("c","e","g","h","p","q","u","wp","x"));
    private static String DICTIONARY_PATH = ".//data//dict.json";
    private static List<DictionaryItem> dictionary;

    public static String replace( String rawContent ){
        for ( String s : punctuation ){
            rawContent = rawContent.replace(s," ");
        }
        return rawContent;
    }

    public static void dict_init() {
        try {
            File file = new File(DICTIONARY_PATH);
            FileInputStream inputStream = new FileInputStream(file);
            InputStreamReader inputReader = new InputStreamReader(inputStream, "UTF-8");
            BufferedReader bufferedReader = new BufferedReader(inputReader);
            String output;
            String json = "";
            while ((output = bufferedReader.readLine()) != null) {
                json = json + output;
            }
            bufferedReader.close();
            inputReader.close();
            inputStream.close();
            dictionary = JsonUtil.toObjectList(json,DictionaryItem.class);
        } catch ( Exception e ){
            e.printStackTrace();
        }
    }

    public static String merge(Word word){
        String content = word.getCont();
        for ( DictionaryItem dictionaryItem : dictionary ){
            if ( dictionaryItem.getMode().equals("WL") ){
                List<String> wordlist = dictionaryItem.getWordsList();
                if ( wordlist.contains(word) ) return dictionaryItem.getMergeTo();
                else return content;
            }
            else if ( dictionaryItem.getMode().equals("RE")){
                Pattern pattern = Pattern.compile(dictionaryItem.getPattern());
                Matcher matcher = pattern.matcher(content);
                if ( matcher.matches() ) return dictionaryItem.getMergeTo();
                else return content;
            }
        }
        return content;
    }

    public static boolean wipeWordType(Word word){
        if ( wordType.contains(word.getPos())) return true;
        else return false;
    }
}
