package util;

import entity.Danmaku;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by TongjiSSE on 2015/11/12.
 */
public class XMLUtil {

    public static Document readXML(String filepath){
        try {
            SAXReader reader = new SAXReader();
            Document document = reader.read(new File(filepath));
            return document;
        } catch ( Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static List<Danmaku> extractFromFile(Document document){
        List<Danmaku> danmakuList = new ArrayList<Danmaku>();
        List<Element> elementList = document.getRootElement().elements("d");
        for ( Element element : elementList ){
            Danmaku danmaku = new Danmaku();
            String attributes = element.attribute("p").getValue();
            String content = element.getText();
            danmaku = DanmakuMapper.convert(attributes,content);
            danmakuList.add(danmaku);
        }
        return danmakuList;
    }
}
