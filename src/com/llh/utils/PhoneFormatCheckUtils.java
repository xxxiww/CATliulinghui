package com.llh.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class PhoneFormatCheckUtils {
    private static final Pattern CHINA_PATTERN = Pattern.compile("^((13[0-9])|(14[0,1,4-9])|(15[0-3,5-9])|(16[2,5,6,7])|(17[0-8])|(18[0-9])|(19[0-3,5-9]))\\d{8}$");
    //校验手机号码工具类
    public static boolean isChinaPhoneLegal(String str) throws PatternSyntaxException
    {
        Matcher m = CHINA_PATTERN.matcher(str);
        return m.matches();
    }
}
