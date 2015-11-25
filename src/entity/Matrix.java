package entity;

import collection.Global;

import java.io.File;
import java.io.FileWriter;
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
                int rowID = Global.getUserIndex(userID1);
                int lineID = Global.getUserIndex(userID2);
                double simularity = Vector.simularity(vector1,vector2)*100;
                detail[rowID][lineID] = simularity;
                detail[lineID][rowID] = detail[rowID][lineID];
            }
        }
    }

    public void output(int matrixId, String split){
        try {
            int count = 0;
            File file = new File(".\\matrix\\matrixExample"+matrixId+".txt");
            FileWriter fileWriter = new FileWriter(file);
            for ( int i=0; i< Global.userID.size(); i++ ){
                for ( int j=0; j< Global.userID.size(); j++){
                    if ( detail[i][j] > 0 ) {
                        count++;
                        fileWriter.write(Double.toString(detail[i][j])+split);
                    }
                    else fileWriter.write("0"+split);
                }
                fileWriter.write("\r\n");
            }
            fileWriter.close();
        } catch ( Exception e ){
            e.printStackTrace();
        }

    }
}
