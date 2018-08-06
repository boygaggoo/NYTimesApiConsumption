package com.example.zeeshankhalid.nytimesapiconsumption;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.example.zeeshankhalid.nytimesapiconsumption.controllers.Utility;
import com.example.zeeshankhalid.nytimesapiconsumption.controllers.interfaces.LeftMenuItemClickListener;
import com.example.zeeshankhalid.nytimesapiconsumption.controllers.interfaces.MenuClickedListener;
import com.example.zeeshankhalid.nytimesapiconsumption.fragments.AnotherFragment;
import com.example.zeeshankhalid.nytimesapiconsumption.fragments.MainFragment;
import com.example.zeeshankhalid.nytimesapiconsumption.views.BindingViews;
import com.example.zeeshankhalid.nytimesapiconsumption.views.LeftMenu;
import com.example.zeeshankhalid.nytimesapiconsumption.views.Toolbar;

public class MainActivity extends AppCompatActivity implements LeftMenuItemClickListener, MenuClickedListener {

    public BindingViews bindingViews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bindingViews = BindingViews.getInstance(MainActivity.this);
        setContentView(bindingViews.bindViewsForMainActivity(R.layout.activity_main, this, this));
        initViews();
    }

    private void initViews() {
        bindingViews.toolbar.showSearchButton(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == EditorInfo.IME_ACTION_DONE) {
                    Utility.showToast("Done");
                    Utility.hideKeyboard(MainActivity.this, bindingViews.toolbar.etSearch.getWindowToken());
                }
                return false;
            }
        });

        bindingViews.toolbar.showSubMenuButton(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bindingViews.showSubMenuInMainActivity(bindingViews.toolbar.btnSubMenu, new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        switch (menuItem.getItemId()) {
                            case R.id.mostEmailed:
                                startInitialFragment(menuItem.getTitle().toString());
                                return true;
                            case R.id.mostShared:
                                startInitialFragment(menuItem.getTitle().toString());
                                return true;
                            case R.id.mostViewed:
                                startInitialFragment(menuItem.getTitle().toString());
                                return true;
                        }
                        return false;
                    }
                });
            }
        });
        startInitialFragment("mostemailed");
    }

    private void startInitialFragment(String type)
    {
        Bundle bundle = new Bundle();
        bundle.putString(Constants.RESULT_TYPE_KEY, type);
        replaceFragment(new MainFragment(), bundle, false, "MainFragment");
    }

    public void replaceFragment(Fragment fragment, Bundle bundle, boolean isBackwardCompatible, String fragmentTag) {
        //Toast.makeText(MainActivity.this, "Number of fragments iin stack: " + getSupportFragmentManager().getBackStackEntryCount(), Toast.LENGTH_SHORT).show();
        fragment.setArguments(bundle);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
        fragmentTransaction.replace(R.id.frame, fragment, fragmentTag);
        if (isBackwardCompatible)
            fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commitAllowingStateLoss();

    }

    public void clearFragmentStack() {
        FragmentManager fm = getSupportFragmentManager();
        for (int i = 0; i < fm.getBackStackEntryCount(); ++i) {
            fm.popBackStack();
        }
    }

    @Override
    public void onBackPressed() {
        if (bindingViews.drawer.isDrawerOpen(bindingViews.leftMenu))
            bindingViews.drawer.closeDrawer(bindingViews.leftMenu);
        else if (bindingViews.toolbar.etSearch.getVisibility() == View.VISIBLE) {
            bindingViews.toolbar.etSearch.setVisibility(View.GONE);
            bindingViews.toolbar.tvTitle.setVisibility(View.VISIBLE);
        } else
            super.onBackPressed();
    }

    @Override
    public void onLeftMenuItemClicked(int viewId) {
        bindingViews.drawer.closeDrawer(bindingViews.leftMenu);
        Bundle bundle = new Bundle();

        switch (viewId) {
            case R.id.llLabel1:
                clearFragmentStack();
                break;

            case R.id.llLabel2:
                replaceFragment(new AnotherFragment(), null, true, "AboutUs");
                break;
        }
    }

    @Override
    public void onMenuClicked() {
        if (bindingViews.drawer.isDrawerOpen(bindingViews.leftMenu))
            bindingViews.drawer.closeDrawer(bindingViews.leftMenu);
        else
            bindingViews.drawer.openDrawer(bindingViews.leftMenu);

    }
}
