package com.llh.utils;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class UserNameLenghtLimitedDmt extends PlainDocument {
    private int limit;

    public UserNameLenghtLimitedDmt (int limit){
        super();
        this.limit = limit;
    }
    public void insertString(int offset, String str, AttributeSet attr)throws BadLocationException {
        if (str ==null){
            return;
        }
        if ((getLength() +str.length()) <= limit) {

            char[] upper =str.toCharArray();
            int length=0;
            for (int i = 0; i< upper.length; i++){
                if(upper[i]!=' ' ){
                    upper[length++] = upper[i];
                }
            }
            super.insertString(offset, new String(upper,0,length),attr);
        }
    }
}
