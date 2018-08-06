package com.example.zeeshankhalid.nytimesapiconsumption.views;

import android.content.Context;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.ProgressBar;

import com.example.zeeshankhalid.nytimesapiconsumption.R;
import com.example.zeeshankhalid.nytimesapiconsumption.controllers.adapters.NYTimesResultsRecyclerViewAdapter;
import com.example.zeeshankhalid.nytimesapiconsumption.controllers.interfaces.LeftMenuItemClickListener;
import com.example.zeeshankhalid.nytimesapiconsumption.controllers.interfaces.MenuClickedListener;
import com.example.zeeshankhalid.nytimesapiconsumption.controllers.interfaces.OnResultClickListener;
import com.example.zeeshankhalid.nytimesapiconsumption.models.Result;

import java.util.List;

public class BindingViews {

    private static BindingViews bindingViews;
    private static Context mContext;
    private LayoutInflater layoutInflater;

    //views for main activity
    public LeftMenu leftMenu;
    public Toolbar toolbar;
    public DrawerLayout drawer;

    //views for main fragment
    public RecyclerView rvResults;
    public ProgressBar pbResults;
    public NYTimesResultsRecyclerViewAdapter nyTimesResultsRecyclerViewAdapter;


    public static BindingViews getInstance(Context context) {
        mContext = context;
        if (bindingViews == null)
            bindingViews = new BindingViews();
        return bindingViews;
    }

    private BindingViews() {
        layoutInflater = LayoutInflater.from(mContext);
    }

    public View bindViewsForMainActivity(int layout, final LeftMenuItemClickListener leftMenuItemClickListener, final MenuClickedListener menuClickedListener) {
        View mainActivityView = layoutInflater.inflate(layout, null);
        leftMenu = (LeftMenu) mainActivityView.findViewById(R.id.leftMenu);
        toolbar = mainActivityView.findViewById(R.id.toolbar);

        drawer = mainActivityView.findViewById(R.id.drawer);
        leftMenu.setOnLeftMenuItemClickListener(new LeftMenuItemClickListener() {
            @Override
            public void onLeftMenuItemClicked(int viewId) {
                leftMenuItemClickListener.onLeftMenuItemClicked(viewId);
            }
        });
        toolbar.setOnMenuClickedListener(new MenuClickedListener() {
            @Override
            public void onMenuClicked() {
                menuClickedListener.onMenuClicked();
            }
        });
        return mainActivityView;
    }

    public View bindingViewsForMainFragment(int layout, ViewGroup container) {
        View fragmentView = layoutInflater.inflate(layout, container, false);
        pbResults = (ProgressBar) fragmentView.findViewById(R.id.pbResults);
        rvResults = (RecyclerView) fragmentView.findViewById(R.id.rvResults);
        rvResults.setLayoutManager(new LinearLayoutManager(mContext));
        return fragmentView;
    }

    public void setAdapterForMainFragment(List<Result> results, final OnResultClickListener onResultClickListener)
    {
        nyTimesResultsRecyclerViewAdapter = new NYTimesResultsRecyclerViewAdapter(mContext, results);
        nyTimesResultsRecyclerViewAdapter.setOnResultClickListener(new OnResultClickListener() {
            @Override
            public void onResultClicked(int position) {
                onResultClickListener.onResultClicked(position);
            }
        });
        rvResults.setAdapter(nyTimesResultsRecyclerViewAdapter);
    }

    public void showSubMenuInMainActivity(View v, final PopupMenu.OnMenuItemClickListener onMenuItemClickListener) {
        PopupMenu popup = new PopupMenu(mContext, v);
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                onMenuItemClickListener.onMenuItemClick(menuItem);
                return true;
            }
        });
        popup.inflate(R.menu.sub_menu);
        popup.show();
    }
}
