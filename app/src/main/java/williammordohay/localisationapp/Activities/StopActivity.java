package williammordohay.localisationapp.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import williammordohay.localisationapp.R;
import williammordohay.localisationapp.apiConnection.UrlConstructor;

public class StopActivity extends CommunicationActivity {

    private String stopUrl="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stop);
        stopUrl = UrlConstructor.getStopUrl("5.7180759","45.1927837","500");
    }
}
