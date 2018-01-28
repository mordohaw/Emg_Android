package williammordohay.localisationapp.Activities;


import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;



import williammordohay.localisationapp.R;

public class MainActivity extends CommunicationActivity {

    //ma clé : AIzaSyDeDG09ZXIlnEfkHNkA1zHqHyCb2t7SZks
    //private double longitudeGPS, latitudeGPS;

    //private static final int REQUEST_LOCATION=1;
    //LocationManager locationManager;
    EditText positionValue;
    Button buttonAuto,buttonManual;

    private Intent autoIntent,communicationIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonAuto = ((Button)findViewById(R.id.auto_btn));
        buttonManual = ((Button)findViewById(R.id.manual_btn));

        effetAuClic(buttonAuto);
        effetAuClic(buttonManual);
        //getPosition();

    }



    public void passIsAutomatic(Intent communication)
    {
        Bundle paquetSortant = new Bundle();
        Boolean isAutomatic = true;

        paquetSortant.putBoolean("isAutomatic", isAutomatic);
        communication.putExtras(paquetSortant);
    }


    public void gotoGeolocActivity(View v)
    {
        communicationIntent = new Intent(MainActivity.this, GeolocActivity.class);
        // 04/12 passPosition(communicationIntent);
        //effetAuClic(v);

        startActivity(communicationIntent);
    }

    public void gotoStopActivity(View v)
    {
        autoIntent = new Intent(MainActivity.this, StopListActivity.class);
        // 04/12 passPosition(communicationIntent);
        //effetAuClic(v);
        passIsAutomatic(autoIntent);

        startActivity(autoIntent);
    }




}
