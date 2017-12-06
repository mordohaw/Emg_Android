package williammordohay.localisationapp.Activities;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.PorterDuff;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

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
        communicationIntent = new Intent(MainActivity.this, StopActivity.class);
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
