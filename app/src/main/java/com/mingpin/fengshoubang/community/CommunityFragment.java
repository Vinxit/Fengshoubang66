package com.mingpin.fengshoubang.community;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.mingpin.fengshoubang.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class CommunityFragment extends Fragment {
    private String FILE_NAME = "http://1.192.225.18/sports.tc.qq.com/m0022ect1qs.mp4?vkey=8D84B1F1D837C9566DB6EA985413322F557C8CDC6189B78BF7D118665FC3EDD40974E38FCB0FBBD0B9CB25D12FB1137B4C8AB7D562B0CBDD88E19E04CECE46B065B06F923261DA2F49330F98545B35BAEDA4D42BE9233FBA";
    private String FILE_NAME1 = "/storage/emulated/0/05.mp4";
    private WebView myWebView;
    public CommunityFragment() {
        // Required empty public constructor
    }

    public static CommunityFragment newInstance(String content) {
        Bundle args = new Bundle();
        args.putString("ARGS", content);
        CommunityFragment fragment = new CommunityFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_community, container, false);

    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
/*        WebSettings settings = myWebView.getSettings();
        settings.setLoadWithOverviewMode(true);
        settings.setUseWideViewPort(true);*/
        myWebView = (WebView) getActivity().findViewById(R.id.wv_com);
        myWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url)
            {
                view.loadUrl(url);
                return true;
            }
        });
        myWebView.loadUrl("http://www.enongzi.com/");
    }
}
