package com.force.codes.project.app;

/*
 * Created by Force Porquillo on 6/9/20 1:42 PM
 * Copyright (c) 2020.  All rights reserved.
 * FEU Institute of Technology
 */

import android.content.Context;

import androidx.test.core.app.ApplicationProvider;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class UnitTestSampleJava {
    private static final String FAKE_STRING = "HELLO_WORLD";
    private Context context = ApplicationProvider.getApplicationContext();

    @Test
    public void readStringFromContext_LocalizedString() {
        // Given a Context object retrieved from Robolectric...
        ClassUnderTest myObjectUnderTest = new ClassUnderTest(context);

        // ...when the string is returned from the object under test...
        String result = myObjectUnderTest.getHelloWorldString();

        // ...then the result should be the expected one.
        assertEquals(FAKE_STRING, result);
    }

    static class ClassUnderTest{
        Context context;

        public ClassUnderTest(Context context){
            this.context = context;
        }

        public String getHelloWorldString(){
            return "HELLO WORLD";
        }
    }
}
