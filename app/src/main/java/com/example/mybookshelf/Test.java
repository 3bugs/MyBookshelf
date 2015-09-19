package com.example.mybookshelf;

import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mybookshelf.net.BooksHTTP;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.Headers;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Test extends AppCompatActivity {

    private static final String TAG = "Test";

    private final OkHttpClient client = new OkHttpClient();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        //WebView wv = (WebView) findViewById(R.id.webView);
        //wv.loadUrl("http://192.168.56.1/mybookshelf/phpinfo.php");

        try {
            //run();

            HashMap<String, String> params = new HashMap<>();
            params.put("title", "ทดสอบ title");
            params.put("subtitle", "ทดสอบ subtitle");
            params.put("isbn", "ทดสอบ isbn");
            params.put("description", "ทดสอบ description");

            File fileToUpload = new File(getFilesDir(), "/images/code.jpg");

            BooksHTTP http = new BooksHTTP(this);
            http.insertSampleBookData();
/*
            http.doRequestMultipart(
                    "http://192.168.56.1/mybookshelf/insert.php",
                    params,
                    fileToUpload,
                    new Callback() {
                        @Override
                        public void onFailure(Request request, IOException e) {

                        }

                        @Override
                        public void onResponse(Response response) throws IOException {
                            if (!response.isSuccessful())
                                throw new IOException("Unexpected code " + response);

                            final String responseBody = response.body().string();
                            Log.i(TAG, responseBody);

                            new Handler(Looper.getMainLooper()).post(
                                    new Runnable() {
                                        @Override
                                        public void run() {
                                            ((TextView) findViewById(R.id.textView)).setText(responseBody);
                                        }
                                    }
                            );
                        }
                    });
*/
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void run() throws Exception {
        RequestBody formBody = new FormEncodingBuilder()
                .add("name", "Promlert")
                .build();
        Request request = new Request.Builder()
                .url("http://192.168.56.1/mybookshelf/test.php")
                .post(formBody)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Response response) throws IOException {
                if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

                Headers responseHeaders = response.headers();
                for (int i = 0; i < responseHeaders.size(); i++) {
                    Log.i(TAG, responseHeaders.name(i) + ": " + responseHeaders.value(i));
                }

                Log.i(TAG, response.body().string());
            }
        });
    }
}
