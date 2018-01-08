package williammordohay.localisationapp.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.gson.reflect.TypeToken;

import java.util.List;

import williammordohay.localisationapp.ApiConnection.UrlConstructor;
import williammordohay.localisationapp.R;
import williammordohay.localisationapp.Timetables.TimeTable;

public class StopTimeActivity extends CommunicationActivity {

    private String timeUrl;
    private List<TimeTable> timeList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stop_time);

        timeList = getTimeTables();
    }

    public List<TimeTable> getTimeTables(){
        // "5.7180759" -- "45.1927837--500"     positionLong   positionLat
        timeUrl = UrlConstructor.getStopTimeUrl("SEM:3207");
        inputData = recupereDonnees(timeUrl);

        return (gson.fromJson(inputData, new TypeToken<List<TimeTable>>(){}.getType()));
    }
}
