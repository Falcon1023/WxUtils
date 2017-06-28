package me.trierbo.wechat.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NumericUtil {
    /**
     * 判断字符串是否由数字组成
     * @param str
     * @return
     */
    public static boolean isNumeric(String str){
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if( !isNum.matches() ){
            return false;
        }
        return true;
    }
}
