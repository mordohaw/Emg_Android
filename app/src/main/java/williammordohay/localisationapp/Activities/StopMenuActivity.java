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
    private Button lineButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stop_menu);


        //On récupère la valeur
        Bundle paquetEntrant = getIntent().getExtras();

        myId = paquetEntrant.getString("myId");
        myLines = paquetEntrant.getStringArray("myLines");
        lineButton = ((Button)findViewById(R.id.lineButton));
        effetAuClic(lineButton);

        /*myLineTab = new Line[myLines.length];

        for(int i=0;i<myLines.length;i++){
            lineInfosUrl = UrlConstructor.getLineInfoUrl(myLines[i]);
            inputData = recupereDonnees(lineInfosUrl);
            int indice = inputData.indexOf("[");


            myLineTab[i] = gson.fromJson(inputData, Line.class);
        }

        toto=inputData;*/

    }

    public void goToLineActivity(View v){
        Intent StopLineIntent = new Intent(StopMenuActivity.this, StopLineActivity.class);

        //pass the infos about the stop
        passLineTable(StopLineIntent, myLines);

        startActivity(StopLineIntent);
    }

    public void passLineTable(Intent communication, String[] lines)
    {
        Bundle paquetSortant = new Bundle();

        paquetSortant.putStringArray("myLines", lines);
        communication.putExtras(paquetSortant);
    }
}
