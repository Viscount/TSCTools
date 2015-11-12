package Analysis;

import entity.Danmaku;
import org.dom4j.Document;
import util.JsonUtil;
import util.XMLUtil;

import java.util.List;

/**
 * Created by TongjiSSE on 2015/11/12.
 */
public class DanmakuAnalysis {
    public static void main(String[] args){
        Document xml = XMLUtil.readXML("D:\\Develop\\workspace\\TSCTools\\data\\movie\\2065063.xml");
        List<Danmaku> danmakuList = XMLUtil.extractFromFile(xml);
        for ( Danmaku danmaku : danmakuList ){
            System.out.print(JsonUtil.toJson(danmaku)+"\n");
        }
    }
}
