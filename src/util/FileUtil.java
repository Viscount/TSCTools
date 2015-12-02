package util;

import java.io.File;
import java.io.FileWriter;
import java.util.List;

/**
 * Created by Jaric Liao on 2015/12/2.
 */
public class FileUtil {

    public static void output2File(List collection, String fileName){
        try {
            File file = new File(fileName);
            FileWriter fileWriter = new FileWriter(file);
            for (Object d : collection) fileWriter.write(d + ", ");
            fileWriter.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
