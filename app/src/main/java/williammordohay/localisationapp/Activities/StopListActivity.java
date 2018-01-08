package williammordohay.localisationapp.Activities;

import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import williammordohay.localisationapp.Stops.Stop;
import williammordohay.localisationapp.R;
import williammordohay.localisationapp.ApiConnection.UrlConstructor;
import williammordohay.localisationapp.Stops.StopAdapter;
import williammordohay.localisationapp.Timetables.TimeTable;

public class StopListActivity extends CommunicationActivity {

    /*

     Liste des lignes de transport : http://data.metromobilite.fr/api/routers/default/index/routes?codes=SEM:12

     Horaires au poteau : https://data.metromobilite.fr/api/routers/default/index/stops/SEM:3207/stoptimes
     */

    private String stopUrl="";
    private ListView vueListe;
    private StopAdapter myStopAdapter;
    private List<Stop> stopList = new ArrayList<>();
    private String positionLat,positionLong;
    private SwipeRefreshLayout refreshView;
    private static int refreshTime = 5;
    private Spinner distanceSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stop_list);

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        distanceSpinner = (Spinner) findViewById(R.id.distanceSpinner);
        populateSpinner();

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
        myListView.setOnItemClickListener( new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //TextView lineTextView = (TextView) findViewById(R.id.line_value);
                //String myStr = lineTextView.toString();
                //StopAdapter.StopView myItem = (StopAdapter.StopView) parent.getItemAtPosition(position).getClass();
                Stop selectedStop = getStops().get(position);
                view.setBackgroundColor(0000);


                goToStopActivity(selectedStop);

            }
        });
        //adapteurProduction=new AdapteurProduction(this,listeProduction);
        //vueListe.setAdapter(adapteurProduction);

    }

    public void goToStopActivity(Stop currentStop){
        String selectedStopId = currentStop.getId();
        String[] selectedStopLine = currentStop.getLines();
        Intent StopIntent = new Intent(StopListActivity.this, StopMenuActivity.class);

        //pass the infos about the stop
        passStopInfo(StopIntent, selectedStopId, selectedStopLine);

        startActivity(StopIntent);
    }

    public void passStopInfo(Intent communication, String id, String[] lines)
    {
        Bundle paquetSortant = new Bundle();

        paquetSortant.putString("myId", id);
        paquetSortant.putStringArray("myLines", lines);
        communication.putExtras(paquetSortant);
    }



    public List<Stop> getStops(){
        // "5.7180759" -- "45.1927837--500"     positionLong   positionLat
        stopUrl = UrlConstructor.getStopUrl("5.7180759","45.1927837",distanceSpinner.getSelectedItem().toString());
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
                        if(distanceSpinner.getSelectedItem().toString()=="1000"){
                            refreshTime=10;
                        }else{
                            refreshTime=5;
                        }
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
        StopListActivity.this.finish();
    }

    public void populateSpinner(){
        String distanceTab[] = {"300", "500", "1000"};



        //populate the spinner
        ArrayAdapter<String> lineNumberAdapter = new ArrayAdapter<>(this, R.layout.element_spinner, distanceTab);
        distanceSpinner.setAdapter(lineNumberAdapter);
        //set default selected value
        distanceSpinner.setSelection(1);
    }
}
