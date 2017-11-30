package com.example.yudhisthira.quandoo.utils;

import android.content.Context;

import com.example.yudhisthira.quandoo.R;

import java.io.InputStream;

import okhttp3.mockwebserver.MockResponse;

/**
 * Created by yudhisthira
 */

public class TestUtils {
    public static void waitUntill(long duration) {

        try {
            Thread.sleep(duration);
        }
        catch (Exception e) {

        }
    }

    public static MockResponse getMockGenericError() {
        return new MockResponse().setResponseCode(500);
    }

    public static MockResponse geMockGenericCustomerSuccess(Context context) {
        MockResponse mockResponse = new MockResponse();

        mockResponse.setResponseCode(200).addHeader("Content-Type", "application/json").setBody(readRawFile(context, R.raw.customerlist));

        return mockResponse;
    }

    public static String readRawFile(Context context, int rawID) {
        String body = "";

        try {
            InputStream inputStream = context.getResources().openRawResource(rawID);
            byte[] buffer = new byte[inputStream.available()];
            inputStream.read(buffer);
            body = new String(buffer);
        } catch (Exception e) {

        }

        return body;
    }
}
