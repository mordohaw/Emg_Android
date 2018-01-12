package williammordohay.localisationapp.Activities;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import williammordohay.localisationapp.ApiConnection.UrlConstructor;
import williammordohay.localisationapp.Lines.Line;
import williammordohay.localisationapp.Lines.LineAdapter;
import williammordohay.localisationapp.R;

public class StopLineActivity extends CommunicationActivity {

    private String[] myLines;
    private Spinner lineSpinner;
    private ListView vueListe;
    private LineAdapter myLinesAdapter;
    private List<Line> lineList = new ArrayList<>();
    private String lineUrl;
    private static final int refreshTime=5;
    private SwipeRefreshLayout refreshView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stop_line);
        gson = new Gson();
        lineSpinner = (Spinner) findViewById(R.id.lineSpinner);

        Bundle paquetEntrant = getIntent().getExtras();

        myLines = paquetEntrant.getStringArray("myLines");
        populateSpinner();

        vueListe = (ListView) findViewById(R.id.lineListView);
        populateLineList(vueListe);

        refreshView = (SwipeRefreshLayout)findViewById(R.id.refreshLineList);
        refreshList();

    }

    public void quitActivity(View v)
    {
        StopLineActivity.this.finish();
    }

    public void populateSpinner(){
        //populate the spinner
        ArrayAdapter<String> lineNumberAdapter = new ArrayAdapter<>(this, R.layout.element_spinner, myLines);
        lineSpinner.setAdapter(lineNumberAdapter);
        //set default selected value
        lineSpinner.setSelection(0);

    }

    public void populateLineList(ListView myListView) {
        //populate the StopListView
        lineList = getLines();
        myLinesAdapter = new LineAdapter(this, lineList);

        myListView.setAdapter(myLinesAdapter);
    }

    public List<Line> getLines(){
        // "5.7180759" -- "45.1927837--500"     positionLong   positionLat
        lineUrl = UrlConstructor.getLineInfoUrl(lineSpinner.getSelectedItem().toString());
        inputData = recupereDonnees(lineUrl);

        return (gson.fromJson(inputData, new TypeToken<List<Line>>(){}.getType()));
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

                                populateLineList(vueListe);


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
}
