package williammordohay.localisationapp.Activities;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import williammordohay.localisationapp.Stops.Stop;
import williammordohay.localisationapp.R;
import williammordohay.localisationapp.ApiConnection.UrlConstructor;
import williammordohay.localisationapp.Stops.StopAdapter;

public class StopActivity extends CommunicationActivity {

    private String stopUrl="";
    private ListView vueListe;
    private StopAdapter myStopAdapter;
    private List<Stop> stopList = new ArrayList<>();
    private String positionLat,positionLong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stop);

        //On récupère la valeur
        Bundle extras = getIntent().getExtras();
        positionLat = extras.getString("myLatitude");
        positionLong = extras.getString("myLongitude");

        vueListe = (ListView) findViewById(R.id.stopListView);
        gson = new Gson();

        populateStopList(vueListe);

    }

    public void populateStopList(ListView myListView){
        //populate the StopListView
        stopList = getStops();
        myStopAdapter = new StopAdapter(this,stopList);

        myListView.setAdapter(myStopAdapter);
        //adapteurProduction=new AdapteurProduction(this,listeProduction);
        //vueListe.setAdapter(adapteurProduction);

    }
    public List<Stop> getStops(){
        // "5.7180759" -- "45.1927837"
        stopUrl = UrlConstructor.getStopUrl(positionLong,positionLat,"500");
        inputData = recupereDonnees(stopUrl);

        return (gson.fromJson(inputData, new TypeToken<List<Stop>>(){}.getType()));
    }

    public void quitActivity(View v)
    {
        StopActivity.this.finish();
    }
}
