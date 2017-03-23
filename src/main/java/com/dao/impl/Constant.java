package com.dao.impl;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhangying8 on 17/1/24.
 */
public class Constant {

    public static Map<String, String> logMap = null;

    static {
        logMap = new HashMap<String, String>();

        logMap.put("1", "恭喜您，可以去领棉花糖了！");

        logMap.put("-1001", "用户名不能为空！");
        logMap.put("-1002", "手机号码不能为空！");
        logMap.put("-1003", "请填写正确的手机号码！");
        logMap.put("-1004", "不好意思，操作失败，您可能已经领过棉花糖了！");
    }

    public static String getMessage(String key){
        return logMap.get(key);
    }
}
