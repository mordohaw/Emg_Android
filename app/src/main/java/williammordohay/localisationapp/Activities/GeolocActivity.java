package williammordohay.localisationapp.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;


import com.google.gson.Gson;

import williammordohay.localisationapp.ApiConnection.UrlConstructor;
import williammordohay.localisationapp.GoogleMap.Place;
import williammordohay.localisationapp.R;

public class GeolocActivity extends CommunicationActivity {

    private Spinner placeSpinner;
    private EditText adressContent;
    private Button nextButton;
    private String manualMapUrl;
    private Place myMapPlace;
    private Gson myGson;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_geoloc);

        myGson = new Gson();
        placeSpinner = (Spinner) findViewById(R.id.placesSpinner);
        nextButton = (Button) findViewById(R.id.next_button);
        adressContent = (EditText) findViewById(R.id.placeContent);
        populateSpinner();
        effetAuClic(nextButton);
    }

    public Place getPlace(){
        // "5.7180759" -- "45.1927837--500"     positionLong   positionLat
        manualMapUrl = UrlConstructor.getGeoCoordonates(placeSpinner.getSelectedItem().toString(),adressContent.getText().toString());

        inputData = recupereDonnees(manualMapUrl);

        try{
            return (myGson.fromJson(inputData, Place.class));
        }catch(Exception e){
            return null;
        }

    }


    public void populateSpinner(){
        String placesTab[] = {"Place", "Rue", "Impasse", "Avenue"};



        //populate the spinner
        ArrayAdapter<String> lineNumberAdapter = new ArrayAdapter<>(this, R.layout.element_spinner, placesTab);
        placeSpinner.setAdapter(lineNumberAdapter);
        //set default selected value
        placeSpinner.setSelection(1);
    }

    public void passBoolean(Intent communication, String[] lines)
    {
        Bundle paquetSortant = new Bundle();

        paquetSortant.putStringArray("myLines", lines);
        communication.putExtras(paquetSortant);
    }


    public void passManualAdress(Intent communication)
    {
        Bundle paquetSortant = new Bundle();



        double myLat = myMapPlace.getResults().getGeometry().getBounds().getPositionLat();
        double myLong = myMapPlace.getResults().getGeometry().getBounds().getPositionLong();
        paquetSortant.putString("manualLong", String.valueOf(myLong));
        paquetSortant.putString("manualLat", String.valueOf(myLat));
        communication.putExtras(paquetSortant);
    }

    public void passIsAutomatic(Intent communication)
    {
        Bundle paquetSortant = new Bundle();
        Boolean isAutomatic = false;

        paquetSortant.putBoolean("isAutomatic", isAutomatic);
        communication.putExtras(paquetSortant);
    }


    public void gotoStopListActivity(View v)
    {
        myMapPlace = getPlace();

        if(myMapPlace != null){
            Intent manualIntent = new Intent(GeolocActivity.this, StopListActivity.class);
            passIsAutomatic(manualIntent);
            passManualAdress(manualIntent);
            // 04/12 passPosition(communicationIntent);
            //effetAuClic(v);

            startActivity(manualIntent);
        }else{
            Toast.makeText(this, R.string.web_unavailable, Toast.LENGTH_SHORT).show();
        }

    }


}
