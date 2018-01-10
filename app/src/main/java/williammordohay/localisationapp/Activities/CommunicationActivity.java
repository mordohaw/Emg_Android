package williammordohay.localisationapp.Activities;


import android.graphics.PorterDuff;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;

import com.google.gson.Gson;

import java.util.concurrent.ExecutionException;

import williammordohay.localisationapp.ApiConnection.WebServiceGetter;

/**
 * Created by William on 22/11/2017.
 */

public abstract class CommunicationActivity extends AppCompatActivity{

    protected Gson gson;
    protected String inputData="";





    //API CLIENT PART

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







    public static void effetAuClic(View button){
        button.setOnTouchListener(new View.OnTouchListener() {

            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_BUTTON_PRESS:
                        v.getBackground().setColorFilter(0xe040B3E5, PorterDuff.Mode.SRC_ATOP);
                        v.invalidate();
                        break;

                    case MotionEvent.ACTION_DOWN:
                        v.getBackground().setColorFilter(0xe040B3E5, PorterDuff.Mode.SRC_ATOP);
                        v.invalidate();
                        break;

                    case MotionEvent.ACTION_UP:
                        v.getBackground().clearColorFilter();
                        v.invalidate();
                        break;

                }
                return false;
            }
        });
    }

}
