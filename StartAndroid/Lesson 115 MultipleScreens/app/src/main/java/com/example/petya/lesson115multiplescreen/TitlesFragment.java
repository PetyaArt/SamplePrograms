package com.example.petya.lesson115multiplescreen;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class TitlesFragment extends ListFragment {

    public interface onItemClickListener {
        public void itemClick(int position);
    }

    onItemClickListener mListener;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                getActivity(), android.R.layout.simple_list_item_1, getResources()
                .getStringArray(R.array.headers));

        setListAdapter(arrayAdapter);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mListener = (onItemClickListener) context;
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        mListener.itemClick(position);
    }
}
