package com.example.SimpleSqliteExample;


import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class UpdateFragment extends Fragment {

    private EditText update_id,update_name,update_email;
    private Button btnUpdate;


    public UpdateFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_update, container, false);

        update_id = view.findViewById(R.id.et_contact_id_update);
        update_name = view.findViewById(R.id.et_name_update);
        update_email = view.findViewById(R.id.et_email_update);

        btnUpdate = view.findViewById(R.id.btn_update);
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateContact();
            }
        });


        return view;

    }

    private void updateContact(){

        int id = Integer.parseInt(update_id.getText().toString());
        String name = update_name.getText().toString();
        String email = update_email.getText().toString();

        ContactDbHelper contactDbHelper = new ContactDbHelper(getActivity());
        SQLiteDatabase database = contactDbHelper.getWritableDatabase();

        contactDbHelper.updateContacts(id,name,email,database);

        contactDbHelper.close();

        Toast.makeText(getActivity(), "Update Successful", Toast.LENGTH_SHORT).show();

        update_id.setText("");
        update_name.setText("");
        update_email.setText("");

    }

}
