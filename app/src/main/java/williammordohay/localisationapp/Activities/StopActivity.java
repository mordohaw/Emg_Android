package williammordohay.localisationapp.Activities;

import android.content.Context;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
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
    private SwipeRefreshLayout refreshView;
    private static final int refreshTime = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stop);

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        //On récupère la valeur
        //Bundle extras = getIntent().getExtras();
        updatePosition();

        vueListe = (ListView) findViewById(R.id.stopListView);
        gson = new Gson();

        populateStopList(vueListe);

        refreshView = (SwipeRefreshLayout)findViewById(R.id.refreshList);
        refreshList();

    }

    public void updatePosition(){
        //if (checkLocation())
        getPosition();
        positionLat = String.valueOf(latitudeGPS);//extras.getString("myLatitude");
        positionLong = String.valueOf(longitudeGPS); //extras.getString("myLongitude");
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

    private void refreshList()
    {


        //rafraichirListe long-time task in background thread
        new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                while(!isDestroyed())
                {
                    try
                    {
                        //dummy delay for "tpsRafraichissement" second
                        Thread.sleep(refreshTime*1000);

                        //update ui on UI thread
                        runOnUiThread(new Runnable()
                        {
                            @Override
                            public void run()
                            {

                                //set the rafraichirListe
                                vueListe.invalidateViews();
                                refreshView.setRefreshing(true);
                                //set the action on up dating

                                updatePosition();
                                populateStopList(vueListe);


                                //Update the list
                                vueListe.invalidateViews();
                                refreshView.setRefreshing(false);
                            }
                        });
                    } catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }

                }



            }
        }).start();


    }
    public void quitActivity(View v)
    {
        StopActivity.this.finish();
    }
}
