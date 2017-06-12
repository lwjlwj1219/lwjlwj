package com.connection.common.uitls;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Tools {
	 /**
     * ������ʽУ������
     * @param emaile ��ƥ�������
     * @return ƥ��ɹ�����true ���򷵻�false;
     */
    public static boolean checkEmaile(String emaile){
        String RULE_EMAIL = "^\\w+((-\\w+)|(\\.\\w+))*\\@[A-Za-z0-9]+((\\.|-)[A-Za-z0-9]+)*\\.[A-Za-z0-9]+$";
        //������ʽ��ģʽ
        Pattern p = Pattern.compile(RULE_EMAIL);
        //������ʽ��ƥ����
        Matcher m = p.matcher(emaile);
        //��������ƥ��
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
        for (String str : list2) {//����list1
            if (list1.contains(str)) {//������������
                result.add(str);//�Ž�һ��list���棬���list���ǽ���
            }
        }
        return result;
    }
}
