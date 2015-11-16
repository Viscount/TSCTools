package util;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Jaric Liao on 2015/11/16.
 */
public class NoiseWiper {

    private static Set<String> punctuation = new HashSet<String>(Arrays.asList(",",".","!","?","/",":",";",
                                                                            "，","。","！","？","/","：","；"));

    public static String replace( String rawContent ){
        for ( String s : punctuation ){
            rawContent = rawContent.replace(s," ");
        }
        return rawContent;
    }
}
