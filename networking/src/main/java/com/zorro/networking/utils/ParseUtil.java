package com.zorro.networking.utils;

import com.zorro.networking.gsonparserfactory.GsonParserFactory;
import com.zorro.networking.interfaces.Parser;
import com.google.gson.Gson;

/**
 * Package:   com.zorro.networking.utils
 * ClassName: ParseUtil
 * Created by Zorro on 2020/5/6 19:51
 * 备注： 解析器工具
 */
public class ParseUtil {

    private static Parser.Factory mParserFactory;

    public static void setParserFactory(Parser.Factory parserFactory) {
        mParserFactory = parserFactory;
    }

    public static Parser.Factory getParserFactory() {
        if (mParserFactory == null) {
            mParserFactory = new GsonParserFactory(new Gson());
        }
        return mParserFactory;
    }

    public static void shutDown() {
        mParserFactory = null;
    }

}
