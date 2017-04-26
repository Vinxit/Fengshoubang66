package com.mingpin.fengshoubang.product;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.mingpin.fengshoubang.LoginActivity;
import com.mingpin.fengshoubang.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProductFragment extends Fragment {

    private TextView tv;
    private Button bt_image;
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
        tv = (TextView) getActivity().findViewById(R.id.tv_product);
        bt_image = (Button) getActivity().findViewById(R.id.bt_image);
        bt_image.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
/*
                ImageGalleryActivity.show(getContext(),"http://www.fengshoubang.cn/attached/image/20170309/1489052696101464.jpg");
*/
                Intent intent = new Intent(getContext(), LoginActivity.class);
/*                intent.putExtra("image","http://www.fengshoubang.cn/attached/image/20170309/1489052696101464.jpg");*/
                startActivity(intent);
            }
        });
        tv.setText(getArguments().getString("ARGS"));
    }

}
