package williammordohay.localisationapp.apiConnection;

/**
 * Created by William on 22/11/2017.
 */

import android.os.AsyncTask;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


/**
 * Created by william.mordohay on 15/05/2017.
 */

public class WebServiceGetter extends AsyncTask<String,String,String>{


    @Override
    protected String doInBackground(String... params) {
        if(android.os.Debug.isDebuggerConnected())
            android.os.Debug.waitForDebugger();

        HttpURLConnection connexionService=null;
        String reponse="";
        BufferedReader lecteur=null;
        InputStream fluxEntree;
        StringBuffer tampon;
        try
        {
            URL url = new URL(params[0]);


            connexionService = (HttpURLConnection) url.openConnection();

            connexionService.setRequestMethod("GET");
            //URL connection
            connexionService.setConnectTimeout(1500); //set timeout to 1.5 seconds

            connexionService.connect();

            if (connexionService.getResponseCode() == HttpURLConnection.HTTP_OK)
            {
                fluxEntree = new BufferedInputStream(connexionService.getInputStream());

                //retourner le flux
                lecteur = new BufferedReader(new InputStreamReader(fluxEntree));

                tampon = new StringBuffer();
                String line = "";
                while ((line = lecteur.readLine()) != null) {
                    tampon.append(line);
                }
                reponse = tampon.toString();
            }

        }
        catch (MalformedURLException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            if (connexionService != null)
            {
                connexionService.disconnect();
            }
            try
            {
                lecteur.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }

        }

        return reponse;
    }



}

