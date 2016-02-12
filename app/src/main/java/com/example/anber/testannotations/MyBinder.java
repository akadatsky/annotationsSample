package com.example.anber.testannotations;

import android.app.Activity;
import android.view.View;

import java.lang.reflect.Field;

public class MyBinder {

    public static void inject(Activity activity) {
        for (Field field : activity.getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(Bind.class)) {
                boolean accessible = field.isAccessible();
                field.setAccessible(true);
                Bind annotationBind = field.getAnnotation(Bind.class);
                View view = activity.findViewById(annotationBind.value()[0]);
                try {
                    field.set(activity, view);
                } catch (IllegalAccessException e) {
                    // do nothing
                }
                field.setAccessible(accessible);
            }
        }
    }

}
