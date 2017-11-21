package com.tekizma.coreUtils;

import java.util.List;

public class CommonUtils{
    
    public static boolean isEmpty(String string){
        boolean isEmpty = true;
        
        if(string!=null && !string.equals("")){
            isEmpty = false;
        }
        return isEmpty;
    }
    
    public static boolean isEmpty(List<String> list){
        boolean isEmpty = true;
        
        if(list!=null && !list.isEmpty() && list.size()>0){
            isEmpty = false;
        }
        return isEmpty;
    }
}