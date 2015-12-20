package main.util;

import main.entity.Word;

import java.io.*;
import java.util.List;

/**
 * Created by Jaric Liao on 2015/11/18.
 */
public class PersistenceUtil {

    private static String PARSE_FILE_PATH = ".\\data\\parse\\";

    public static void persist(String danmakuID, String json){
        try {
            File file = new File(PARSE_FILE_PATH + danmakuID);
            FileOutputStream fos = new FileOutputStream(file);
            OutputStreamWriter osw = new OutputStreamWriter(fos,"UTF-8");
            osw.write(json);
            osw.flush();
            osw.close();
            fos.close();
        } catch ( Exception e ){
            e.printStackTrace();
        }
    }

    public static List<Word> read(String danmakuID){
        try {
            File file = new File(PARSE_FILE_PATH + danmakuID + ".parse");
            FileInputStream inputStream = new FileInputStream(file);
            InputStreamReader inputReader = new InputStreamReader(inputStream,"UTF-8");
            BufferedReader bufferedReader = new BufferedReader(inputReader);
            String output;
            String json = "";
            while ((output = bufferedReader.readLine()) != null) {
                json = json + output;
            }
            bufferedReader.close();
            inputReader.close();
            inputStream.close();
            List<Word> result = JsonUtil.toObjectList(json,Word.class);
            return result;
        } catch ( Exception e ){
            e.printStackTrace();
        }
        return null;
    }

    public static boolean checkExist(String danmakuID){
        File file = new File(PARSE_FILE_PATH + danmakuID + ".parse");
        return file.exists();
    }
}
