package williammordohay.localisationapp.Activities;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;

import com.google.gson.Gson;

import java.util.concurrent.ExecutionException;

import williammordohay.localisationapp.ApiConnection.WebServiceGetter;

/**
 * Created by William on 22/11/2017.
 */

public abstract class CommunicationActivity extends AppCompatActivity{


    protected Gson gson;
    protected String inputData="";
    public String recupereDonnees(String urlRequete)
    {
        //réception de données provenants du service Web
        String donneesEntrantes="";
        try
        {

            AsyncTask monAsyncTask = new WebServiceGetter().execute(urlRequete);
            donneesEntrantes = (String) monAsyncTask.get();
            monAsyncTask.cancel(true);
        }
        catch (InterruptedException e)
        {
            //debugActivite();
            e.printStackTrace();
        }
        catch (ExecutionException e)
        {
            //debugActivite();
            e.printStackTrace();
        }
        return donneesEntrantes;
    }

}
