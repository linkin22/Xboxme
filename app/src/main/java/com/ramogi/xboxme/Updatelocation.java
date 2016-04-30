package com.ramogi.xboxme;

import android.content.Context;
import android.os.AsyncTask;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.extensions.android.gms.auth.GoogleAccountCredential;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;

import com.ramogi.xboxme.backend.mylocationApi.MylocationApi;
import com.ramogi.xboxme.backend.mylocationApi.model.Mylocation;

import java.io.IOException;

/**
 * Created by ramogiochola on 4/30/16.
 */
public class Updatelocation  extends AsyncTask<Mylocation, Void, String>{

    private static MylocationApi myApiService = null;
    private Context context;
    private GoogleAccountCredential credential = null;
    private Mylocation mylocation;

    public Updatelocation(Mylocation mylocation, Context context, GoogleAccountCredential credential){

        //this.mylocation = mylocation;
        //this.context = context;
        //this.credential = credential;

        setMylocation(mylocation);
        setContext(context);
        setCredential(credential);
    }

    @Override
    protected String doInBackground(Mylocation... mylocation){

        if(myApiService == null){
            MylocationApi.Builder builder = new MylocationApi.Builder(
                    AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(),getCredential())
                    .setRootUrl("https://" + Constants.PROJECT_ID + ".appspot.com/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);

                        }
                    });

            myApiService = builder.build();

        }


        try{

            getMylocation().setUpdatetime(new com.google.api.client.util.DateTime(
                    new java.util.Date()));

            myApiService.insert(getMylocation()).execute();

        } catch (IOException e){

        }


        return "updated";
    }

    @Override
    protected void onPostExecute(String me){

    }

    public static MylocationApi getMyApiService() {
        return myApiService;
    }

    public static void setMyApiService(MylocationApi myApiService) {
        Updatelocation.myApiService = myApiService;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public GoogleAccountCredential getCredential() {
        return credential;
    }

    public void setCredential(GoogleAccountCredential credential) {
        this.credential = credential;
    }

    public Mylocation getMylocation() {
        return mylocation;
    }

    public void setMylocation(Mylocation mylocation) {
        this.mylocation = mylocation;
    }
}
