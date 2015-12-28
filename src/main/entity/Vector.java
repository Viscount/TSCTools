package main.entity;

import java.util.*;

/**
 * Created by TongjiSSE on 2015/11/16.
 */
public class Vector {
    private Map<String,Object> detail;

    public Vector(Map<String, Object> detail) {
        this.detail = detail;
    }

    public Map<String, Object> getDetail() {
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

    public static double similarity(Vector v1, Vector v2){
        long common = 0;
        Map<String,Long> wordCount = new HashMap<String,Long>();
        Map holder = v1.getDetail();
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
        return common * 1.0 / (v1.getSize()+v2.getSize());
    }

    public static double topicSimilarity(Vector v1,Vector v2){
        double minCount = 0;
        double maxCount = 0;
        Set<String> totalTopic = new HashSet<String>();
        for ( Map.Entry<String,Object> entry : v1.getDetail().entrySet() ) {
            String topicName = entry.getKey();
            totalTopic.add(topicName);
        }
        for ( Map.Entry<String,Object> entry : v2.getDetail().entrySet() ) {
            String topicName = entry.getKey();
            totalTopic.add(topicName);
        }
        for ( String topicName : totalTopic ){
            double possibility = 0;
            if (v1.getDetail().containsKey(topicName)) possibility = (double)v1.getDetail().get(topicName);
            double pob = 0;
            if(v2.getDetail().containsKey(topicName)) pob = (double)v2.getDetail().get(topicName);
            if ( pob > possibility ){
                minCount += possibility;
                maxCount += pob;
            }
            else {
                minCount += pob;
                maxCount += possibility;
            }
        }
        if ( maxCount > 0) return minCount/maxCount;
        else return 0;
    }

    public static Vector merge( Vector v1, Vector v2){
        if ( v1 == null ) return v2;
        if ( v2 == null ) return v1;
        Map vectorCount = new HashMap<String,Long>();
        vectorCount.putAll(v1.getDetail());
        for ( Map.Entry<String,Object> entry : v2.getDetail().entrySet() ){
            String word = entry.getKey();
            Long count = (Long)entry.getValue();
            if ( vectorCount.containsKey(word) ){
                Long oldCount = (Long)vectorCount.get(word);
                vectorCount.put(word,oldCount+count);
            }
            else vectorCount.put(word,count);
        }
        Vector vector = new Vector(vectorCount);
        return vector;
    }
}
