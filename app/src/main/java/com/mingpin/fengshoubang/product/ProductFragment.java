package com.mingpin.fengshoubang.product;


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
public class ProductFragment extends Fragment {


    public ProductFragment() {
        // Required empty public constructor
    }
    public static ProductFragment newInstance(String content) {
        Bundle args = new Bundle();
        args.putString("ARGS", content);
        ProductFragment fragment = new ProductFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_product, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        TextView tv = (TextView) getActivity().findViewById(R.id.tv_product);
        tv.setText(getArguments().getString("ARGS"));
    }
}
