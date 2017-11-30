package williammordohay.localisationapp.Stops;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import williammordohay.localisationapp.R;

/**
 * Created by William on 30/11/2017.
 */

public class StopAdapter extends ArrayAdapter<Stop> {

    public Map<View,Stop> mapStops = new HashMap<>();

    public StopAdapter(Context context, List<Stop> stops)
    {
        super(context,0,stops);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {


        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.stop_element,parent, false);
        }
        StopView viewHolder = (StopView) convertView.getTag();
        if(viewHolder==null)
        {
            // Associate View with object viewHolder
            viewHolder = new StopView();
            viewHolder.name=(TextView) convertView.findViewById(R.id.name_value);
            viewHolder.longitude=(TextView) convertView.findViewById(R.id.longi_value);
            viewHolder.latitude=(TextView) convertView.findViewById(R.id.lati_value);
            viewHolder.lines=(TextView) convertView.findViewById(R.id.line_value);
            convertView.setTag(viewHolder);
        }
        else
        {
            //increase performance
            viewHolder = (StopView) convertView.getTag();
        }

        Stop currentStop = getItem(position);
        viewHolder.name.setText(currentStop.getName());
        //viewHolder.longitude.setText((int) currentStop.getLon());
        //viewHolder.latitude.setText((int) currentStop.getLat());

        /*for(int i=0;i<currentStop.getLines().length;i++){
            viewHolder.lines.setText(currentStop.getLines()[i]);
        }*/


        mapStops.put(convertView, currentStop);
        return convertView;

    }

    public class StopView
    {
        public TextView name;
        public TextView longitude;
        public TextView latitude;
        public TextView lines;
    }
}
