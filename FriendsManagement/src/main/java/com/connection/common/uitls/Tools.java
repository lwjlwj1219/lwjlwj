package com.connection.common.uitls;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Tools {
	 /**
     * 正则表达式校验邮箱
     * @param emaile 待匹配的邮箱
     * @return 匹配成功返回true 否则返回false;
     */
    public static boolean checkEmaile(String emaile){
        String RULE_EMAIL = "^\\w+((-\\w+)|(\\.\\w+))*\\@[A-Za-z0-9]+((\\.|-)[A-Za-z0-9]+)*\\.[A-Za-z0-9]+$";
        //正则表达式的模式
        Pattern p = Pattern.compile(RULE_EMAIL);
        //正则表达式的匹配器
        Matcher m = p.matcher(emaile);
        //进行正则匹配
        return m.matches();
    }
    /**
     * get the same element
     * @param list1
     * @param list2
     * @return
     */
    public static List<String> getIntersection(List<String> list1,
            List<String> list2) {
        List<String> result = new ArrayList<String>();
        for (String str : list2) {//遍历list1
            if (list1.contains(str)) {//如果存在这个数
                result.add(str);//放进一个list里面，这个list就是交集
            }
        }
        return result;
    }
}
