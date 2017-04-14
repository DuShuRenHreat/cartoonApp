package com.test.cartoonapp.utils;

import android.app.Activity;

import java.lang.reflect.Field;

/**
 * Created by PC-2 on 2017/4/11.
 */

public class InitView {
    public static void load(Activity context){
        Class<? extends Activity> clazz = context.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for(Field field : fields){
            InitID init = field.getAnnotation(InitID.class);
            if(init != null ){
                int id = init.value();
                if(id != -1){
                    try {
                        Object obj = context.findViewById(id);
                        field.setAccessible(true);
                        field.set(context,obj);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
