package dw.fdb.com.fdbapp.fragments;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import dw.fdb.com.fdbapp.R;

public class BrandDialog extends DialogFragment {

    ListView listView;

    public BrandDialog() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_list, container);
        listView = (ListView) view.findViewById(R.id.filter_list);
        getDialog().setTitle("Hello");
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //CustomListAdapter adapter = new CustomListAdapter(getActivity(), items)
        //listView.setAdapter(adapter);
    }

}