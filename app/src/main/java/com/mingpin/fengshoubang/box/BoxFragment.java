package com.mingpin.fengshoubang.box;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mingpin.fengshoubang.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class BoxFragment extends Fragment {


    public BoxFragment() {
        // Required empty public constructor
    }
    public static BoxFragment newInstance(String content) {
        Bundle args = new Bundle();
        args.putString("ARGS", content);
        BoxFragment fragment = new BoxFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_box, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        TextView tv = (TextView) getActivity().findViewById(R.id.tv_box);
        tv.setText(getArguments().getString("ARGS"));
    }
}
