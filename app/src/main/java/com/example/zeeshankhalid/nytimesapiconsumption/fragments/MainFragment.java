package com.example.zeeshankhalid.nytimesapiconsumption.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.zeeshankhalid.nytimesapiconsumption.Constants;
import com.example.zeeshankhalid.nytimesapiconsumption.R;
import com.example.zeeshankhalid.nytimesapiconsumption.controllers.Utility;
import com.example.zeeshankhalid.nytimesapiconsumption.controllers.interfaces.OnResultClickListener;
import com.example.zeeshankhalid.nytimesapiconsumption.controllers.retrofit.CallingWebServices;
import com.example.zeeshankhalid.nytimesapiconsumption.controllers.retrofit.ServiceResponse;
import com.example.zeeshankhalid.nytimesapiconsumption.models.NYTimesResponseModel;
import com.example.zeeshankhalid.nytimesapiconsumption.models.Result;

import java.util.List;

public class MainFragment extends BaseFragment {

    String type = null;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        type = getArguments().getString(Constants.RESULT_TYPE_KEY);
        if(TextUtils.isEmpty(type))
            type = "mostemailed";
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return getMainActivity().bindingViews.bindingViewsForMainFragment(R.layout.layout_main_fragment, container);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getNews();
    }

    private void getNews() {
        CallingWebServices.getInstance().getNews(type, "all-sections", "7.json", new ServiceResponse() {
            @Override
            public void onSuccess(Object object) {
                NYTimesResponseModel nyTimesResponseModel = (NYTimesResponseModel) object;
                getMainActivity().bindingViews.pbResults.setVisibility(View.GONE);
                if (nyTimesResponseModel.getResults().size() < 1) {
                    Utility.showToast("No result found");
                } else {
                    getMainActivity().bindingViews.rvResults.setVisibility(View.VISIBLE);
                    setAdapter(nyTimesResponseModel.getResults());
                }
            }

            @Override
            public void onFail(Object object) {
                getMainActivity().bindingViews.pbResults.setVisibility(View.GONE);
                getMainActivity().bindingViews.rvResults.setVisibility(View.GONE);
                Utility.showToast(object.toString());

            }

            @Override
            public void onError(Object object) {
                getMainActivity().bindingViews.pbResults.setVisibility(View.GONE);
                getMainActivity().bindingViews.rvResults.setVisibility(View.GONE);
                Utility.showToast(object.toString());
            }
        });
    }

    private void setAdapter(final List<Result> results) {
        getMainActivity().bindingViews.setAdapterForMainFragment(results, new OnResultClickListener() {
            @Override
            public void onResultClicked(int position) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(results.get(position).getUrl()));
                startActivity(browserIntent);
            }
        });
    }

    @Override
    protected String setTitle() {
        getMainActivity().bindingViews.leftMenu.selectLabel1();
        return "NY Times Most Popular";

    }
}
