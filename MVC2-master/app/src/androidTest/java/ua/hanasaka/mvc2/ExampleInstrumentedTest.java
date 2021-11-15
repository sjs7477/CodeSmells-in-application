package ua.hanasaka.mvc2;

import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    String TAG = "myLogs";
    @Test
    public void useOkHttp() throws Exception {
        System.out.println("YYY");
        Log.d(TAG, "Start!");
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("http://publicobject.com/helloworld.txt")
                .build();
        client.newCall(request).enqueue(new Callback() {

            @Override
            public void onFailure(Request request, IOException e) {
                System.out.println("onFailure!!!");
                Log.d(TAG, e.getClass()+" mess= "+e.getMessage());
            }

            @Override
            public void onResponse(Response response) throws IOException {
                Log.d(TAG, "onResponse");
                if (!response.isSuccessful())
                    throw new IOException("Unexpected code " + response);
                Log.d(TAG, response.body().string());
            }
        });
    }
}
