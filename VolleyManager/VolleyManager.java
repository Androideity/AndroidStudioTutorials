package com.androideity.volleyexample.volley;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Condesa on 21/03/14.
 */
public class VolleyManager implements Response.Listener<String>, Response.ErrorListener {

    private static final int DEFAULT_TIMEOUT = 60000;

    /**
     * Interface
     */
    public interface OnRequestListener {
        void onRequestSuccess(JSONArray responseArray);
        void onRequestSuccess(JSONObject responseObject);
        void onRequestFail(Error error);
    }

    /**
     * Member Variables
     */
    private Context context;
    private OnRequestListener onRequestListener;
    private HashMap<String, String> headers;

    /**
     * Static Variables
     */
    private static RequestQueue sRequestQueue;
    private static final String TAG = VolleyManager.class.getSimpleName();
    private static final String JSON_TYPE = "application/json; charset=utf-8";

    /**
     * Constructors
     */
    private VolleyManager(Context context) {
        this.context = context.getApplicationContext();
        headers = new HashMap<>();
    }

    /**
     * Factory Method
     */
    public static VolleyManager getInstance(Context context) {
        if (sRequestQueue == null) {
            synchronized (VolleyManager.class) {
                sRequestQueue = Volley.newRequestQueue(context.getApplicationContext());
                sRequestQueue.start();
            }
        }
        return new VolleyManager(context);
    }

    /**
     * Getters and Setters
     */
    public OnRequestListener getOnRequestListener() {
        return onRequestListener;
    }

    public void setOnRequestListener(OnRequestListener onRequestListener) {
        this.onRequestListener = onRequestListener;
    }

    public HashMap<String, String> getRequestHeaders() {
        headers.clear();
        headers.put("Content-Type", JSON_TYPE);
        headers.put("Accept", JSON_TYPE);
        return headers;
    }

    // Public Methods
    /**
     *
     * @param params JSONObject
     * @param url String
     */
    public void executePostRequest(JSONObject params, String url){

        JsonRequest<String> request = new JsonRequest<String>(Request.Method.POST, url, params.toString(), this, this) {

            @Override
            public String getBodyContentType() {
                return JSON_TYPE;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                return getRequestHeaders();
            }

            @Override
            protected Response<String> parseNetworkResponse(NetworkResponse response) {
                try {
                    String jsonString =
                            new String(response.data, HttpHeaderParser.parseCharset(response.headers));

                    return Response.success(jsonString, HttpHeaderParser.parseCacheHeaders(response));
                }
                catch (UnsupportedEncodingException e) {
                    return Response.error(new ParseError(e));
                }
            }
        };
        request.setRetryPolicy(new DefaultRetryPolicy(DEFAULT_TIMEOUT, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        sRequestQueue.add(request);
    }

    public void executePostRequest(HashMap<String, Object> params, String url){
        JSONObject jsonParams = new JSONObject(params);
        executePostRequest(jsonParams, url);
    }

    public void executePutRequest(JSONObject params, String url){

        JsonRequest<String> request = new JsonRequest<String>(Request.Method.PUT, url, params.toString(), this, this) {

            @Override
            public String getBodyContentType() {
                return JSON_TYPE;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                return getRequestHeaders();
            }

            @Override
            protected Response<String> parseNetworkResponse(NetworkResponse response) {
                try {
                    String jsonString =
                            new String(response.data, HttpHeaderParser.parseCharset(response.headers));

                    return Response.success(jsonString, HttpHeaderParser.parseCacheHeaders(response));
                }
                catch (UnsupportedEncodingException e) {
                    return Response.error(new ParseError(e));
                }
            }
        };
        request.setRetryPolicy(new DefaultRetryPolicy(DEFAULT_TIMEOUT, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        sRequestQueue.add(request);
    }

    public void executePutRequest(HashMap<String, Object> params, String url){
        JSONObject jsonParams = new JSONObject(params);
        executePostRequest(jsonParams, url);
    }

    public void executeGetRequest(String url) {
        Log.i(TAG, url);
        JsonRequest<String> request = new JsonRequest<String>(Request.Method.GET, url, null, this, this) {

            @Override
            public String getBodyContentType() {
                return JSON_TYPE;
            }

            @Override
            protected Response<String> parseNetworkResponse(NetworkResponse networkResponse) {
                Response<String> response = null;
                try {
                    String jsonString = "{}";

                    if (networkResponse.data.length > 0) {
                        jsonString =
                                new String(networkResponse.data, HttpHeaderParser.parseCharset(networkResponse.headers));
                    }

                    response = Response.success(jsonString, HttpHeaderParser.parseCacheHeaders(networkResponse));
                }
                catch (UnsupportedEncodingException e) {
                    response = Response.error(new ParseError(e));
                }

                return response;
            }
        };
        request.setRetryPolicy(new DefaultRetryPolicy(DEFAULT_TIMEOUT, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        sRequestQueue.add(request);
    }


    // Private Methods
    private JSONArray toJSONArray(String responseObject) {
        JSONArray jsonArray = null;
        try {
            jsonArray = new JSONArray(responseObject);
        } catch (JSONException e) {
            Log.e(TAG, "It couldn't parse to JSONArray");
            e.printStackTrace();
        }

        return jsonArray;
    }

    private JSONObject toJSONObject(String responseObject) {
        JSONObject jsonObject = null;

        try {
            jsonObject = new JSONObject(responseObject);
        } catch (JSONException e) {
            Log.e(TAG, "It couldn't parse to JSONObject");
            e.printStackTrace();
        }

        return jsonObject;
    }

    // Response.Listener
    @Override
    public void onResponse(String responseObject) {
        JSONArray jsonArray = null;
        JSONObject jsonObject = null;

        if((jsonArray = toJSONArray(responseObject)) != null) {
            if (onRequestListener != null) {
                onRequestListener.onRequestSuccess(jsonArray);
            }
        }else if((jsonObject = toJSONObject(responseObject)) != null) {
            if (onRequestListener != null) {
                onRequestListener.onRequestSuccess(jsonObject);
            }
        }else {
            String errorMessage = String.format("%s couldn't be parsed as a valid json object", responseObject);

            Error error = new Error(errorMessage);

            if (onRequestListener != null) {
                onRequestListener.onRequestFail(error);
            }
        }
    }

    // Response.ErrorListener
    @Override
    public void onErrorResponse(VolleyError volleyError) {
        Error error = new Error(volleyError);

        if (onRequestListener != null) {
            onRequestListener.onRequestFail(error);
        }
    }
}
