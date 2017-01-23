import android.os.AsyncTask;
import android.test.InstrumentationTestCase;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.udacity.gradle.builditbigger.IConstants;
import com.udacity.joketeller.application.backend.jokeTellerApi.JokeTellerApi;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * Created by arbalan on 1/22/17.
 */

public class JokeTellerAsyncTaskTests extends InstrumentationTestCase {

    private static JokeTellerApi myApiService = null;

    public void testAsyncJoke() throws Throwable {
        // create  a signal to let us know when our task is done.
        final CountDownLatch signal = new CountDownLatch(1);

        final AsyncTask<Void, Void, String> myTask = new AsyncTask<Void, Void, String>() {

            @Override
            protected String doInBackground(Void... params) {
                if (myApiService == null) {  // Only do this once
                    JokeTellerApi.Builder builder = new JokeTellerApi.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null)
                            .setRootUrl(IConstants.ROOT_URL + "/_ah/api/");
                    myApiService = builder.build();
                }

                try {
                    return myApiService.tellJoke().execute().getData();
                } catch (IOException e) {
                    return e.getMessage();
                }
            }

            @Override
            protected void onPostExecute(String result) {
                assertNotNull(result);
                assertTrue(result.length() > 0);
                assertEquals("This is totally a funny joke", result);
                signal.countDown();
            }
        };

        runTestOnUiThread(new Runnable() {

            @Override
            public void run() {
                myTask.execute();
            }
        });

        signal.await(30, TimeUnit.SECONDS);
    }
}