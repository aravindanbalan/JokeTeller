package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.widget.Toast;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.udacity.joketeller.application.backend.jokeTellerApi.JokeTellerApi;
import com.udacity.joketellerlib.JokeTellerDisplayActivity;

import java.io.IOException;

/**
 * Created by arbalan on 1/22/17.
 */

public class JokeTellerAsyncTask extends AsyncTask<Void, Void, String> {
    private JokeTellerApi myApiService = null;
    private Context context;

    public JokeTellerAsyncTask(Context context) {
        this.context = context;
    }

    @Override
    protected String doInBackground(Void... params) {
        if (myApiService == null) {  // Only do this once
            JokeTellerApi.Builder builder = new JokeTellerApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    .setRootUrl(IConstants.ROOT_URL + "/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });
            // end options for devappserver

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
        if (!TextUtils.isEmpty(result)) {
            Intent intent = new Intent(context, JokeTellerDisplayActivity.class);
            intent.putExtra(JokeTellerDisplayActivity.KEY_JOKE, result);
            context.startActivity(intent);
        } else {
            Toast.makeText(context, "Error, Backend server not responding", Toast.LENGTH_SHORT).show();
        }
    }
}