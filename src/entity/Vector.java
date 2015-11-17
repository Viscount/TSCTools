package entity;

import java.util.*;

/**
 * Created by TongjiSSE on 2015/11/16.
 */
public class Vector {
    private Map<String,Long> detail;

    public Vector(Map<String, Long> detail) {
        this.detail = detail;
    }

    public Map<String, Long> getDetail() {
        return detail;
    }

    public long getSize(){
        long size = 0;
        for (Iterator it = detail.entrySet().iterator(); it.hasNext();) {
            Map.Entry entry = (Map.Entry) it.next();
            String word = (String) entry.getKey();
            long count = (long) entry.getValue();
            size += count;
        }
        return size;
    }

    public static double simularity(Vector v1, Vector v2){
        long common = 0;
        Map<String,Long> wordCount = new HashMap<String,Long>();
        Map<String,Long> holder = v1.getDetail();
        wordCount.putAll(holder);
        holder = v2.getDetail();
        for (Iterator it = holder.entrySet().iterator(); it.hasNext();){
            Map.Entry entry = (Map.Entry)it.next();
            String word = (String)entry.getKey();
            long count = (long)entry.getValue();
            if ( wordCount.containsKey(word) ){
                long alreadyCount = wordCount.get(word);
                wordCount.put(word,alreadyCount+count);
                common += alreadyCount + count;
            }
            else wordCount.put(word,count);
        }
        return common*1.0/(v1.getSize()+v2.getSize());
    }
}
