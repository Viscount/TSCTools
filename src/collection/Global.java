package collection;

import util.JsonUtil;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by TongjiSSE on 2015/11/16.
 */
public class Global {

    public static String USER_FILE_PATH = "user.txt";
    public static String WORD_FILE_PATH = "word.txt";

    public static List<String> userID;
    public static Map<String,Long> words;

    public static void init(){
        userID = new ArrayList<String>();
        words = new HashMap<String,Long>();
    }

    public static int getUserIndex(String userIDString){
        return userID.indexOf(userIDString);
    }

    public static void output(){
        try {
            File userFile = new File(USER_FILE_PATH);
            FileWriter fwriter = new FileWriter(userFile);
            for ( String s : userID ){
                fwriter.write(s+"\r\n");
            }
            fwriter.close();

            File wordFile = new File(WORD_FILE_PATH);
            fwriter = new FileWriter(wordFile);
            fwriter.write(JsonUtil.toJson(words));
            fwriter.close();
        } catch ( Exception e ){
            e.printStackTrace();
        }
    }

}
