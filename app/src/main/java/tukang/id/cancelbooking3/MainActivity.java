package tukang.id.cancelbooking3;

import android.app.Activity;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity{


    String value;
    String[] keluhan = {
            "Mau mengganti alamat",
            "Saya menunggu terlalu lama",
            "Sudah pesan ditempat lain",
            "Tidak mendapat konfirmasi sama sekali",
            "aplikasi ini membingungkan",
            "Lainnya"
    };

    Button btnCancel;

    AppCompatActivity appCompatActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        appCompatActivity = this;

        final ListView listView = (ListView) findViewById(R.id.list_view);
        // set the adapter to fill the data in ListView
        btnCancel = (Button) findViewById(R.id.btn_cancel);
        final CustomListAdapter customAdapter = new CustomListAdapter(getApplicationContext(), keluhan);
        listView.setAdapter(customAdapter);

       listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
               final int itemPosition = position;
               final String itemValue = (String) listView.getItemAtPosition(position);

//               String selectedItem = ((TextView) view).getText().toString();
//               if (pilihAlasan.contains(selectedItem)) {
//                   pilihAlasan.remove(selectedItem); //unchecked item
//               } else {
//                   pilihAlasan.add(selectedItem);
//               }

               for(int i=0; i< customAdapter.getCount(); i++){

                   View v = getViewByPosition(i,listView);
                   CheckedTextView checkedTextViewRow = (CheckedTextView) v.findViewById(R.id.simpleCheckedTextView);
                   checkedTextViewRow.setCheckMarkDrawable(R.drawable.ic_check_blank);
                   checkedTextViewRow.setChecked(false);
               }


                   final CheckedTextView checkedTextView = (CheckedTextView) view.findViewById(R.id.simpleCheckedTextView);

               if(checkedTextView.isChecked()) {
                   checkedTextView.setCheckMarkDrawable(R.drawable.ic_check_blank);
                   checkedTextView.setChecked(false);
               }else{
                   checkedTextView.setCheckMarkDrawable(R.drawable.ic_check);
                   checkedTextView.setChecked(true);
               }
           }
       });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listView.getSelectedItemPosition() == 5 ){

                    LinearLayout linearLayout = new LinearLayout(getApplicationContext());
                    linearLayout.setOrientation(LinearLayout.VERTICAL);
                    final EditText txtLainnya = new EditText(getApplicationContext());
                    txtLainnya.setHint("isikan pendapat");
                    linearLayout.addView(txtLainnya);

                    final AlertDialog.Builder dialogMasukan = new AlertDialog.Builder(appCompatActivity);
                    dialogMasukan.setTitle("Silahkan isi pendapat");
                    dialogMasukan.setView(linearLayout);
                    dialogMasukan.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    });
                    dialogMasukan.show();

                } else {
//                    Toast.makeText(getContext(), "ini yang lain" , Toast.LENGTH_SHORT).show();
                    final AlertDialog.Builder feedBack = new AlertDialog.Builder(appCompatActivity);
                    feedBack.setTitle("Pesanan dibatalkan");
                    feedBack.setMessage("Terima kasih atas feedback anda");
                    feedBack.setPositiveButton("Selesai", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            feedBack.setCancelable(true);
                        }
                    });
                    feedBack.show();
                }
            }
        });
    }

    public View getViewByPosition(int pos, ListView listView) {
        final int firstListItemPosition = listView.getFirstVisiblePosition();
        final int lastListItemPosition = firstListItemPosition + listView.getChildCount() - 1;
        if (pos < firstListItemPosition || pos > lastListItemPosition ) {
            return listView.getAdapter().getView(pos, null, listView);
        } else {
            final int childIndex = pos - firstListItemPosition; return listView.getChildAt(childIndex);

        }
    }

//    @Override
//    public void onClick(View view) {
//        String items = "";
//        for (String aa : pilihAlasan){
//            items += "-" + aa ;
//        }
//        Toast.makeText(getApplicationContext(), "anda memilih " +  items , Toast.LENGTH_SHORT).show();
//    }


}
