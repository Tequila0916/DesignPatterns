package org.whisky.strategy_pattern;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.whisky.strategy_pattern.impl.MT1011;
import org.whisky.strategy_pattern.impl.MT2101;
import org.whisky.strategy_pattern.impl.MT4101;
import org.whisky.strategy_pattern.impl.MT8104;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName ReceiptHandleStrategyFactory
 * @Description TODO
 * @Author GT-R
 * @Date 2024/7/23下午4:47
 * @Version 1.0
 */
public class ReceiptHandleStrategyFactory {
    private static Map<String, ReceiptHandleStrategy> strategies;
    public static void init(){
        strategies = new HashMap<>();
//        strategies.put("MT1011",new MT1011());
//        strategies.put("MT2101",new MT2101());
//        strategies.put("MT4101",new MT4101());
//        strategies.put("MT8104",new MT8104());
        SAXReader saxReader = new SAXReader();
        String file  = "src/main/resources/config.xml";
        try {
            Document document = saxReader.read(file);
            Node node = document.selectSingleNode("/config/className");
            String text = node.getText();
            Class<?> aClass = Class.forName(text);
            ReceiptHandleStrategy strategy = (ReceiptHandleStrategy) aClass.newInstance();
            strategies.put("MT1011",strategy);

        } catch (DocumentException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
    public static ReceiptHandleStrategy getStrategy(String type) {
        return strategies.get(type);
    }
}
