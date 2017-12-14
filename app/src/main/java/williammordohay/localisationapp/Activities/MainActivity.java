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
    Button buttonStop;

    private Intent communicationIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        positionValue = ((EditText) findViewById(R.id.myPositionText));
        buttonStop = ((Button)findViewById(R.id.tram_stop_btn));
        effetAuClic(buttonStop);
        //getPosition();

    }






    public void gotoStopActivity(View v)
    {
        communicationIntent = new Intent(MainActivity.this, StopListActivity.class);
        // 04/12 passPosition(communicationIntent);
        //effetAuClic(v);

        startActivity(communicationIntent);
    }

    public void passPosition(Intent communication)
    {
        getPosition();
        Bundle paquetSortant = new Bundle();

        paquetSortant.putString("myLatitude", String.valueOf(latitudeGPS));
        paquetSortant.putString("myLongitude", String.valueOf(longitudeGPS));
        communication.putExtras(paquetSortant);
    }



}
