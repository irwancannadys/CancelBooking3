package tukang.id.cancelbooking3;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by irwan on 6/24/16.
 */
public class CustomListAdapter extends BaseAdapter {
    String[] names;
    Context context;
    LayoutInflater inflter;
    String value;
    Button btnCancel;

    public CustomListAdapter(Context context, String[] names) {
        this.context = context;
        this.names = names;
        inflter = (LayoutInflater.from(context));

    }

    @Override
    public int getCount() {
        return names.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View view, ViewGroup parent) {
        view = inflter.inflate(R.layout.item_list, null);

        View v = view;

        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(context);
            v = vi.inflate(R.layout.item_list, null);
        }


//        btnCancel = (Button) view.findViewById(R.id.btn_cancel);
//
        final CheckedTextView simpleCheckedTextView = (CheckedTextView) view.findViewById(R.id.simpleCheckedTextView);
        simpleCheckedTextView.setText(names[position]);
//// perform on Click Event Listener on CheckedTextView
        simpleCheckedTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnCancel = (Button) v.findViewById(R.id.btn_cancel);
                String posisi = names[position];

                if (simpleCheckedTextView.isChecked()) {
// set cheek mark drawable and set checked property to false
                    value = "un-Checked";
                    simpleCheckedTextView.setCheckMarkDrawable(0);
                    simpleCheckedTextView.setChecked(false);
                } else {
// set cheek mark drawable and set checked property to true
                    value = "Checked";
                    simpleCheckedTextView.setCheckMarkDrawable(R.drawable.ic_check);
                    simpleCheckedTextView.setChecked(true);
                }

            }
        });
        return view;
    }
}