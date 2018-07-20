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
public class DeleteFragment extends Fragment {

    private EditText etContactId;
    private Button btnDelete;


    public DeleteFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_delete, container, false);

        etContactId = view.findViewById(R.id.etContactId);
        btnDelete = view.findViewById(R.id.btnDelete);

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteContact();
            }
        });

        return view;
    }

    private void deleteContact(){
        ContactDbHelper contactDbHelper = new ContactDbHelper(getActivity());
        SQLiteDatabase database = contactDbHelper.getWritableDatabase();

        int id = Integer.parseInt(etContactId.getText().toString());

        contactDbHelper.deleteContact(id,database);
        contactDbHelper.close();

        Toast.makeText(getActivity(), "Delete Successful", Toast.LENGTH_SHORT).show();

        etContactId.setText("");

    }

}
