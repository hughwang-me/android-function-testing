package com.uwjx.function.util;

import com.google.gson.Gson;

public class GSonUtil {

    public static String toJsonString(Object obj){
        Gson gson = new Gson();
        return gson.toJson(obj);
    }
}
