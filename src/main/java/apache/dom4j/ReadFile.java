package apache.dom4j;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ReadFile {


    private static Document load(String fileName) {
        SAXReader reader = new SAXReader();
        Document document = null;
        try {
            document = reader.read(new File(fileName));
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return document;
    }

    public static Map<String, String> read(String fileName) {
        Map<String, String> properties = new HashMap<>();
        Document document = load(fileName);
        Element root = document.getRootElement();
        for (Iterator i = root.elementIterator();i.hasNext();) {
            Element element = (Element) i.next();
            properties.put(element.getName(), element.getText().trim());
        }
        return properties;
    }
}
