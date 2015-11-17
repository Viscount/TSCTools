package entity;

import collection.Global;

import java.util.Iterator;
import java.util.Map;

/**
 * Created by TongjiSSE on 2015/11/16.
 */
public class Matrix {

    double[][] detail;

    public Matrix(TimeWindow timeWindow){
        detail = new double[Global.userID.size()][Global.userID.size()];
        Map<String, Vector> userFeature = timeWindow.getUserFeature();
        for ( Map.Entry<String, Vector> entry1 : userFeature.entrySet() ){
            for ( Map.Entry<String, Vector> entry2 : userFeature.entrySet() ){
                String userID1 = entry1.getKey();
                String userID2 = entry2.getKey();
                Vector vector1 = entry1.getValue();
                Vector vector2 = entry2.getValue();
                detail[Global.getUserIndex(userID1)][Global.getUserIndex(userID2)] = Vector.simularity(vector1,vector2);
                detail[Global.getUserIndex(userID2)][Global.getUserIndex(userID1)] =  detail[Global.getUserIndex(userID1)][Global.getUserIndex(userID2)];
            }
        }
    }
}
