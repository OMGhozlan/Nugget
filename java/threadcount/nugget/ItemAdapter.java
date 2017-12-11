package threadcount.nugget;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ItemAdapter extends ArrayAdapter<Item> {
    private static class ViewHolder {
        TextView name;
        TextView value;
    }

    public ItemAdapter(Context context, ArrayList<Item> items) {
        super(context, R.layout.item, items);
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        Item i = getItem(position);
        ViewHolder vh;
        if (view == null) {
            vh = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            view = inflater.inflate(R.layout.item, parent, false);
            vh.name = (TextView) view.findViewById(R.id.item_name);
            vh.value = (TextView) view.findViewById(R.id.item_value);
            view.setTag(vh);
        } else {
            vh = (ViewHolder) view.getTag();
        }
        vh.name.setText(i.name);
        vh.value.setText(Double.toString(i.value));
        return view;
    }
}
