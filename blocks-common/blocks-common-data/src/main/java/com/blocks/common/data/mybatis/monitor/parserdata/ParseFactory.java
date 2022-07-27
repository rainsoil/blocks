package com.blocks.common.data.mybatis.monitor.parserdata;

import java.util.HashMap;
import java.util.Map;

/**
 * 解析工厂
 *
 * @author luyanan
 * @since 2022/7/27
 **/
public class ParseFactory {
    private ParseFactory() {
    }

    private static ParseFactory factory = new ParseFactory();
    private static Map<String, ParseData> parseMap = new HashMap<>();


    static {
        parseMap.put("UPDATE", new ParseUpdateData());
    }

    public static ParseFactory getInstance() {
        return factory;
    }

    public ParseData creator(String commandName) {
        return parseMap.get(commandName);
    }
}
