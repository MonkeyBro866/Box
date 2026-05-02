package com.lookingpet.www.gjx.util;

import java.util.Map; 
import com.lookingpet.www.gjx.util.parser.SuperParse;
public class Proxy {

    public static Object[] proxy(Map<String, String> params) {
        try {
            String what = params.get("go");
            assert what != null;
            if (what.equals("SuperParse")) {
                return SuperParse.loadHtml(params.get("flag"), params.get("url"));
            }

        } catch (Throwable ignored) {

        }
        return null;
    }
}
