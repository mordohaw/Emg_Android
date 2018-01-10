package williammordohay.localisationapp.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;


import williammordohay.localisationapp.R;

public class StopMenuActivity extends CommunicationActivity {


    private String inputData="", myId, lineInfosUrl, toto;
    private String[] myLines;
    private Button linestopButton,timestopButton;

    //https://maps.googleapis.com/maps/api/geocode/json?address=5%20Rue%20de%20New-York,%20Grenoble&key=AIzaSyDeDG09ZXIlnEfkHNkA1zHqHyCb2t7SZks
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stop_menu);


        //On récupère la valeur
        Bundle paquetEntrant = getIntent().getExtras();

        myId = paquetEntrant.getString("myId");
        myLines = paquetEntrant.getStringArray("myLines");
        linestopButton = ((Button)findViewById(R.id.lineButton));
        timestopButton = ((Button)findViewById(R.id.timeButton));
        effetAuClic(linestopButton);
        effetAuClic(timestopButton);

        /*myLineTab = new Line[myLines.length];

        for(int i=0;i<myLines.length;i++){
            lineInfosUrl = UrlConstructor.getLineInfoUrl(myLines[i]);
            inputData = recupereDonnees(lineInfosUrl);
            int indice = inputData.indexOf("[");


            myLineTab[i] = gson.fromJson(inputData, Line.class);
        }

        toto=inputData;*/

    }

    public void quitActivity(View v)
    {
        StopMenuActivity.this.finish();
    }

    public void goToLineActivity(View v){
        Intent stopLineIntent = new Intent(StopMenuActivity.this, StopLineActivity.class);

        //pass the infos about the stop
        passLineTable(stopLineIntent, myLines);

        startActivity(stopLineIntent);
    }

    public void passLineTable(Intent communication, String[] lines)
    {
        Bundle paquetSortant = new Bundle();

        paquetSortant.putStringArray("myLines", lines);
        communication.putExtras(paquetSortant);
    }

    public void goToTimeActivity(View v){
        Intent stopTimeIntent = new Intent(StopMenuActivity.this, StopTimeActivity.class);

        //pass the infos about the stop
        passStopId(stopTimeIntent, myId);

        startActivity(stopTimeIntent);
    }

    public void passStopId(Intent communication, String myId)
    {
        Bundle paquetSortant = new Bundle();

        paquetSortant.putString("myId", myId);
        communication.putExtras(paquetSortant);
    }
}
