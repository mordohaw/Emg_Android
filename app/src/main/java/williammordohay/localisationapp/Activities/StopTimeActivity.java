package williammordohay.localisationapp.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

import williammordohay.localisationapp.ApiConnection.UrlConstructor;
import williammordohay.localisationapp.R;
import williammordohay.localisationapp.Timetables.TimeTable;

public class StopTimeActivity extends CommunicationActivity {

    private String timeUrl,stopId;
    private List<TimeTable> timesList;
    private ListView vueListe;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stop_time);

        gson = new Gson();

        //get stop's id
        Bundle paquetEntrant = getIntent().getExtras();
        stopId = paquetEntrant.getString("myId");
        vueListe = (ListView) findViewById(R.id.timesListView);

        timesList = getTimeTables();
    }

    public List<TimeTable> getTimeTables(){
        // "5.7180759" -- "45.1927837--500"     positionLong   positionLat
        timeUrl = UrlConstructor.getStopTimeUrl(stopId);
        inputData = recupereDonnees(timeUrl);

        return (gson.fromJson(inputData, new TypeToken<List<TimeTable>>(){}.getType()));
    }
}
