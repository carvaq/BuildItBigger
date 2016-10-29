package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.joker.backend.jokeApi.JokeApi;

import java.io.IOException;

import cvv.udacity.jokelayout.JokeActivity;

class EndpointAsyncTask extends AsyncTask<Void, Void, String> {
    private static JokeApi sApiService = null;
    private Context context;

    public EndpointAsyncTask(Context context) {
        this.context = context;
    }

    @Override
    protected String doInBackground(Void... params) {
        return fetchJoke();
    }

    private String fetchJoke() {
        if(sApiService == null) {  // Only do this once
              JokeApi.Builder builder = new JokeApi.Builder(AndroidHttp.newCompatibleTransport(),
                      new AndroidJsonFactory(), null)
                      // options for running against local devappserver
                      // - 10.0.2.2 is localhost's IP address in Android emulator
                      // - turn off compression when running against local devappserver
                      .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                      .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                          @Override
                          public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                              abstractGoogleClientRequest.setDisableGZipContent(true);
                          }
                      });
              // end options for devappserver

              sApiService = builder.build();
          }
        try {
            return sApiService.joke().execute().getData();
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String result) {
        context.startActivity(new Intent(context, JokeActivity.class)
                .putExtra(JokeActivity.EXTRA_JOKE, result));
    }
}
