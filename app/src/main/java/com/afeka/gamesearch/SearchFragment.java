package com.afeka.gamesearch;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.afeka.gamesearch.Controller.FILTER_BY;

public class SearchFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    private OnFragmentInteractionListener mListener;
    private Button searchButton;
    private EditText[] textInputs;
    private TextView[] textLabels;
    private Spinner spinner;
    private int currentPosition;

    public SearchFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        assignViewById(view);
        ArrayAdapter<CharSequence> filterAdapter = ArrayAdapter.createFromResource(this.getContext(),R.array.FilterBy,android.R.layout.simple_spinner_item);
        filterAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(filterAdapter);
        spinner.setOnItemSelectedListener(this);
        currentPosition = 0;
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    String text = "";
                    if (currentPosition!= 0) {
                        text = textInputs[currentPosition].getText().toString();
                    }
                    mListener.onFragmentInteraction(text,FILTER_BY.values()[0]);
                }
            }
        });

        return view;


    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        currentPosition = spinner.getSelectedItemPosition();
        Log.e("search:","outside loop, position is " + position);
        for (int i=1 ; i<5 ; i++){
            if (currentPosition != i){
                textLabels[i].setTypeface(null, Typeface.NORMAL);
                textInputs[i].setText("");
                textInputs[i].setEnabled(false);
            }
            else if (currentPosition == i){
                textLabels[i].setTypeface(null, Typeface.BOLD);
                textInputs[i].setEnabled(true);
            }
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(String text, FILTER_BY filter);
    }



    private void assignViewById(View view){
        searchButton = view.findViewById(R.id.buttonPerformSearch);
        //0.reserve 1. Name; 2.Genre; 3.Year; 4.Company
        textInputs = new EditText[5];
        textLabels = new TextView[5];
        textLabels[1] = view.findViewById(R.id.fragmentNameTextView);
        textLabels[2] = view.findViewById(R.id.fragmentGenreTextView);
        textLabels[3] = view.findViewById(R.id.fragmentYearTextView);
        textLabels[4] = view.findViewById(R.id.fragmentCompanyTextView);
        textInputs[1] = view.findViewById(R.id.editTextName);
        textInputs[2] = view.findViewById(R.id.editTextGenre);
        textInputs[3] = view.findViewById(R.id.editTextYear);
        textInputs[4] = view.findViewById(R.id.editTextCompany);
        spinner = view.findViewById(R.id.spinner);

    }
}