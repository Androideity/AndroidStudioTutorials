package com.androideity.recyclerviewexample.utils;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Condesa on 05/02/16.
 */
public class Utility {

    private static final String TAG = Utility.class.getSimpleName();

    public static JSONObject loadJSONObjectFromAsset(Context context, String file) {
        String filePath = String.format("json/%s.json", file);
        AssetManager assetManager = context.getAssets();
        try {
            InputStream is = assetManager.open(filePath);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            String jsonContent = new String(buffer, "UTF-8");

            return convertToJSONObject(jsonContent);
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public static JSONArray loadJSONArrayFromAsset(Context context, String file) {
        String filePath = String.format("json/%s.json", file);
        AssetManager assetManager = context.getAssets();
        try {
            InputStream is = assetManager.open(filePath);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            String jsonContent = new String(buffer, "UTF-8");
            return convertToJSONArray(jsonContent);
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public static JSONArray convertToJSONArray(String jsonContent) {
        JSONArray jsonArray = null;
        try {
            jsonArray = new JSONArray(jsonContent);
        } catch (JSONException e) {
            Log.e(TAG, "It couldn't parse to JSONArray");
            e.printStackTrace();
        }
        return jsonArray;
    }

    public static JSONObject convertToJSONObject(String jsonContent) {
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(jsonContent);
        } catch (JSONException e) {
            Log.e(TAG, "It couldn't parse to JSONObject");
            e.printStackTrace();
        }
        return jsonObject;
    }


}
