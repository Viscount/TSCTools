package main.util;

import java.util.*;

/**
 * Created by Jaric Liao on 2015/12/12.
 */
public class ArrayUtil {

    public static List selectTop(List dataList, double percentage){
        List result = new ArrayList<Integer>();
        List sortedData = new ArrayList(dataList);
        Map<Integer, Object> clone = new TreeMap<Integer,Object>();
        for ( int i=0; i<dataList.size(); i++) clone.put(i,dataList.get(i));
        Collections.sort(sortedData);
        int lastPos = (int)Math.ceil(sortedData.size() * percentage);
        int index = sortedData.size();
        for ( int i=0; i<lastPos; i++ ){
            index--;
            if (index < 0) break;
            int key = -1;
            for ( Map.Entry<Integer,Object> entry : clone.entrySet() ){
                if ( entry.getValue().equals(sortedData.get(index)) ){
                    key = entry.getKey();
                    break;
                }
            }
            result.add(key);
            clone.remove(key);
        }
        Collections.sort(result);
        return result;
    }
}
