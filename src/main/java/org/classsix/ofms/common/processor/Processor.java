package org.classsix.ofms.common.processor;


import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created by Jiang on 2018/6/27.
 * Coding
 */
public class Processor {
    public static Map<String,Object> processSteam(int min , int max){
        Map<String,Object> res = new HashMap<>();
        String[] strings = new String[24];
        int[] ints = new int[24];
        Random random = new Random();
        for (int i = 0 ; i < 24; i++) {
            strings[i] = i < 10 ? "0" + i + ":00" : i + ":00";
            ints[i] = random.nextInt(min) + (max - min);
        }
        res.put("category",strings);
        res.put("series",ints);
        return res;
    }
}
