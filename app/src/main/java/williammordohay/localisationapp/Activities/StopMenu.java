package williammordohay.localisationapp.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import williammordohay.localisationapp.ApiConnection.UrlConstructor;
import williammordohay.localisationapp.R;

public class StopMenu extends CommunicationActivity {


    private String inputData="", myId, lineInfosUrl, toto;
    private String[] myLines;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stop_menu);


        //On récupère la valeur
        Bundle paquetEntrant = getIntent().getExtras();

        myId = paquetEntrant.getString("myId");
        myLines = paquetEntrant.getStringArray("myLines");


        for(int i=0;i<myLines.length;i++){
            lineInfosUrl = UrlConstructor.getLineInfoUrl(myLines[i]);
            inputData += recupereDonnees(lineInfosUrl);
        }

        toto=inputData;

    }
}
