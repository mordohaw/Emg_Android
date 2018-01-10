package williammordohay.localisationapp.Timetables;

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
 * Created by William on 10/01/2018.
 */

public class TimeTableAdapter extends ArrayAdapter<TimeTable> {
    public Map<View,TimeTable> mapStops = new HashMap<>();

    public TimeTableAdapter(Context context, List<TimeTable> timeTables)
    {
        super(context,0,timeTables);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {


        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.timetable_element,parent, false);
        }
        TimeTableView viewHolder = (TimeTableView) convertView.getTag();
        if(viewHolder==null)
        {
            // Associate View with object viewHolder
            viewHolder = new TimeTableView();
            viewHolder.destinationName=(TextView) convertView.findViewById(R.id.dest_value);
            viewHolder.times=(TextView) convertView.findViewById(R.id.times_value);
            convertView.setTag(viewHolder);
        }
        else
        {
            //increase performance
            viewHolder = (TimeTableView) convertView.getTag();
        }

        TimeTable currentTimeTable = getItem(position);
        viewHolder.destinationName.setText(currentTimeTable.getPattern().getShortDesc());

        String hoursString="";
        for(int i=0;i<currentTimeTable.getTimes().length;i++){
            hoursString += currentTimeTable.getTimes()[i].getRealtimeDeparture();

        }

        viewHolder.times.setText(hoursString);

        mapStops.put(convertView, currentTimeTable);
        return convertView;

    }

    public class TimeTableView
    {
        public TextView destinationName;
        public TextView times;
    }
}
