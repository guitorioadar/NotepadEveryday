package com.example.SimpleSqliteExample;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements View.OnClickListener {

    private Button btnAddContact,btnView,btnDelete,btnUpdate;

    onDbOperationListener dbOpListener;

    public HomeFragment() {
        // Required empty public constructor
    }


    public interface onDbOperationListener{
        public void dBoperationPerform(int method);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        btnAddContact = view.findViewById(R.id.btnAddContact);
        btnView = view.findViewById(R.id.btnVIewContact);
        btnUpdate = view.findViewById(R.id.btnUpdateContact);
        btnDelete = view.findViewById(R.id.btnDeleteContact);

        btnAddContact.setOnClickListener(this);
        btnView.setOnClickListener(this);
        btnUpdate.setOnClickListener(this);
        btnDelete.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.btnAddContact: {
                dbOpListener.dBoperationPerform(0);
                break;
            }

            case R.id.btnVIewContact: {
                dbOpListener.dBoperationPerform(1);
                break;
            }

            case R.id.btnUpdateContact: {
                dbOpListener.dBoperationPerform(2);
                break;
            }

            case R.id.btnDeleteContact: {
                dbOpListener.dBoperationPerform(3);
                break;
            }
        }

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        Activity activity = (Activity) context;

        try {
            dbOpListener = (onDbOperationListener) activity;
        }catch (ClassCastException e){
            Toast.makeText(activity, e.getMessage(), Toast.LENGTH_SHORT).show();

            throw new ClassCastException(activity.toString()+" must implement the interface method");

        }
    }
}
