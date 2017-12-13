package threadcount.nugget;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class WeatherItemAdapter extends ArrayAdapter<WeatherItem> {
    private static class ViewHolder {
        TextView date;
        TextView max;
        TextView min;
        TextView cond;
    }

    public WeatherItemAdapter(Context context, ArrayList<WeatherItem> items) {
        super(context, R.layout.weatheritem, items);
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        WeatherItem wi = getItem(position);
        ViewHolder vh;
        if (view == null) {
            vh = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            view = inflater.inflate(R.layout.weatheritem, parent, false);
            vh.date = (TextView) view.findViewById(R.id.date);
            vh.max = (TextView) view.findViewById(R.id.max_temp);
            vh.min = (TextView) view.findViewById(R.id.min_temp);
            vh.cond = (TextView) view.findViewById(R.id.condition);
            view.setTag(vh);
        } else {
            vh = (ViewHolder) view.getTag();
        }
        vh.date.setText(wi.date);
        vh.max.setText(Double.toString(wi.max));
        vh.min.setText(Double.toString(wi.min));
        vh.cond.setText(wi.date);
        return view;
    }
}
