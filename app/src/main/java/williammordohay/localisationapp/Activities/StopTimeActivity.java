package williammordohay.localisationapp.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

import williammordohay.localisationapp.ApiConnection.UrlConstructor;
import williammordohay.localisationapp.R;
import williammordohay.localisationapp.Timetables.TimeTable;
import williammordohay.localisationapp.Timetables.TimeTableAdapter;

public class StopTimeActivity extends CommunicationActivity {

    private String timeUrl,stopId;
    private List<TimeTable> timesList;
    private ListView vueListe;
    private TimeTableAdapter myTimesAdapter;

    public void quitActivity(View v)
    {
        StopTimeActivity.this.finish();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stop_time);

        gson = new Gson();

        //get stop's id
        Bundle paquetEntrant = getIntent().getExtras();
        stopId = paquetEntrant.getString("myId");
        vueListe = (ListView) findViewById(R.id.timesListView);


        populateStopList(vueListe);
    }


    public List<TimeTable> getTimeTables(){
        timeUrl = UrlConstructor.getStopTimeUrl(stopId);
        inputData = recupereDonnees(timeUrl);

        return (gson.fromJson(inputData, new TypeToken<List<TimeTable>>(){}.getType()));
    }

    public void populateStopList(ListView myListView) {
        //populate the TimeListView
        try{
            timesList = getTimeTables();
            if(timesList.size() != 0){
                myTimesAdapter = new TimeTableAdapter(this, timesList);
                myListView.setAdapter(myTimesAdapter);

            }else{
                Toast.makeText(this, R.string.times_unavailable, Toast.LENGTH_SHORT).show();
            }
        }catch(Exception e){
            Toast.makeText(this, R.string.times_unavailable, Toast.LENGTH_SHORT).show();

        }


    }
}
