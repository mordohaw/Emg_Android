package williammordohay.localisationapp.Lines;

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
 * Created by William on 13/12/2017.
 */

public class LineAdapter extends ArrayAdapter<Line> {
    public Map<View,Line> mapLine = new HashMap<>();

    public LineAdapter(Context context, List<Line> lines)
    {
        super(context,0,lines);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.line_element,parent, false);
        }
        LineView viewHolder = (LineView) convertView.getTag();
        if(viewHolder==null)
        {
            // Associate View with object viewHolder
            viewHolder = new LineView();
            viewHolder.name=(TextView) convertView.findViewById(R.id.name_value);
            viewHolder.mode=(TextView) convertView.findViewById(R.id.mode_value);
            viewHolder.type=(TextView) convertView.findViewById(R.id.type_value);
            convertView.setTag(viewHolder);
        }
        else
        {
            //increase performance
            viewHolder = (LineView) convertView.getTag();
        }

        Line currentLine = getItem(position);
        viewHolder.name.setText(currentLine.getLongName());
        viewHolder.mode.setText(String.valueOf(currentLine.getMode()));
        viewHolder.type.setText(String.valueOf(currentLine.getType()));

        //convertView.setBackgroundColor(Integer.getInteger(currentLine.getColor()));

        mapLine.put(convertView, currentLine);
        return convertView;
    }

    public class LineView
    {
        public TextView name;
        public TextView mode;
        public TextView type;
    }
}
