package com.example.zeeshankhalid.nytimesapiconsumption.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.zeeshankhalid.nytimesapiconsumption.R;

public class AnotherFragment extends BaseFragment {
    View fragmentView;

    @Override public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable @Override public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragmentView = inflater.inflate(R.layout.layout_another_fragment, container, false);
        return fragmentView;

    }

    @Override public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override protected String setTitle() {
        getMainActivity().bindingViews.leftMenu.selectLabel2();
        return "Another layout";

    }
}
