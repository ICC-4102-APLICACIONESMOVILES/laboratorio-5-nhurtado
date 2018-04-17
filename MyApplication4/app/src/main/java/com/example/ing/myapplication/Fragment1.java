package com.example.ing.myapplication;

import android.app.FragmentTransaction;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Fragment1.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Fragment1#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment1 extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private FormDatabase formDatabase;
    private static final String DATABASE_NAME = "forms_db";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public Fragment1() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Fragment1.
     */
    // TODO: Rename and change types and number of parameters
    public static Fragment1 newInstance(String param1, String param2) {
        Fragment1 fragment = new Fragment1();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fragment1, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        formDatabase = Room.databaseBuilder(getActivity().getApplicationContext(),
                FormDatabase.class, DATABASE_NAME)
                .build();

        List<String> spinnerArray =  new ArrayList<>();
        spinnerArray.add("Encuesta");
        spinnerArray.add("Inspección");
        spinnerArray.add("Reporte");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, spinnerArray);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner sItems = (Spinner)  getView().findViewById(R.id.spinner);
        sItems.setAdapter(adapter);

        Button button = (Button)getView().findViewById(R.id.button);
        // Register the onClick listener with the implementation above
        button.setOnClickListener(Listener);

    }

    private View.OnClickListener Listener = new View.OnClickListener() {
        public void onClick(View v) {
            new Thread(new Runnable() {
                @Override
                public void run() {
//                    Forms form =new Forms();
//                    String name = ((EditText)getView().findViewById(R.id.editText)).getText().toString();
//                    String date = ((EditText)getView().findViewById(R.id.editText3)).getText().toString();
//                    String cate = ((Spinner)getView().findViewById(R.id.spinner)).getSelectedItem().toString();
//                    String comm = ((EditText)getView().findViewById(R.id.editText2)).getText().toString();
//                    form.setFormName(name);
//                    form.setFormDate(date);
//                    form.setFormCategory(cate);
//                    form.setFormComment(comm);
//
//                    formDatabase.daoAccess().insertOnlySingleForm(form);
                }
            }) .start();
            Toast.makeText(getActivity(),"Formulario creado exitosamente!!",Toast.LENGTH_LONG).show();
            Fragment fragment = new Fragment0();
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.replace(R.id.frame_layout, fragment);
            ft.commit();
        }
    };

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
