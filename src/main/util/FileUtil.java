package main.util;

import java.io.File;
import java.io.FileWriter;
import java.util.List;

/**
 * Created by Jaric Liao on 2015/12/2.
 */
public class FileUtil {

    public static String generateAnimationFileName( String name, int episode){
        String result = name;
        String suffix = ".xml";
        if ( episode < 10 ) result = result + "0" + episode;
        else result = result + episode;
        return result + suffix;
    }

    public static void output2File(List collection, String fileName){
        try {
            File file = new File(fileName);
            FileWriter fileWriter = new FileWriter(file);
            for (Object d : collection) fileWriter.write(d + " ");
            fileWriter.write("\n\r");
            fileWriter.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
