package williammordohay.localisationapp.Activities;

import android.os.Bundle;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import williammordohay.localisationapp.EntryData.Stop;
import williammordohay.localisationapp.R;
import williammordohay.localisationapp.ApiConnection.UrlConstructor;

public class StopActivity extends CommunicationActivity {

    private String stopUrl="";
    private List<Stop> stopList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stop);
        gson = new Gson();

        stopList = getStops();
    }

    public List<Stop> getStops(){

        stopUrl = UrlConstructor.getStopUrl("5.7180759","45.1927837","500");
        inputData = recupereDonnees(stopUrl);

        return (gson.fromJson(inputData, new TypeToken<List<Stop>>(){}.getType()));
    }
}
