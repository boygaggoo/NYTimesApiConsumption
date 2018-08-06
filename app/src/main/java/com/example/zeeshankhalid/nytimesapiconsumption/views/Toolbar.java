package com.example.zeeshankhalid.nytimesapiconsumption.views;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.zeeshankhalid.nytimesapiconsumption.R;
import com.example.zeeshankhalid.nytimesapiconsumption.controllers.interfaces.MenuClickedListener;


/**
 * Created by khalidz on 10/20/2017.
 */

public class Toolbar extends RelativeLayout {
    public Toolbar(Context context) {
        super(context);
        initViews(context);
    }

    public Toolbar(Context context, AttributeSet attrs) {
        super(context, attrs);
        initViews(context);
    }

    public Toolbar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initViews(context);
    }

    private void initViews(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        try {
            inflater.inflate(R.layout.layout_toolbar, this);
        } catch (Exception e) {
            Log.e("view-error", e.toString());
        }

        bindViews();
    }

    public ImageView ivMenu, btnSearch, btnSubMenu;
    public EditText etSearch;
    public TextView tvTitle;
    MenuClickedListener menuClickedListener;

    public void setOnMenuClickedListener(MenuClickedListener menuClickedListener) {
        this.menuClickedListener = menuClickedListener;
    }

    public void setTitle(String title, boolean isMenuButtonVisible) {
        tvTitle.setText(title);
        if (isMenuButtonVisible)
            ivMenu.setVisibility(VISIBLE);
        else
            ivMenu.setVisibility(INVISIBLE);
    }

    private void bindViews() {
        ivMenu = (ImageView) findViewById(R.id.ivMenu);
        btnSearch = (ImageView) findViewById(R.id.btnSearch);
        btnSubMenu = (ImageView) findViewById(R.id.btnSubMenu);
        ivMenu = (ImageView) findViewById(R.id.ivMenu);
        tvTitle = (TextView) findViewById(R.id.tvTitle);

        etSearch = (EditText) findViewById(R.id.etSearch);

        ivMenu.setOnClickListener(new OnClickListener() {
            @Override public void onClick(View view) {
                if (menuClickedListener != null)
                    menuClickedListener.onMenuClicked();
            }
        });
    }

    public void showSearchButton(final TextView.OnEditorActionListener onEditorActionListener) {
        btnSearch.setVisibility(VISIBLE);
        btnSearch.setOnClickListener(new OnClickListener() {
            @Override public void onClick(View view) {
                if (etSearch.getVisibility() == GONE) {
                    etSearch.setVisibility(VISIBLE);
                    tvTitle.setVisibility(GONE);
                } else {
                    etSearch.setVisibility(GONE);
                    tvTitle.setVisibility(VISIBLE);
                }
            }
        });

        etSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                onEditorActionListener.onEditorAction(textView, i, keyEvent);
                return true;
            }
        });
    }

    public void showSubMenuButton(final View.OnClickListener onClickListener) {
        btnSubMenu.setVisibility(VISIBLE);
        btnSubMenu.setOnClickListener(new OnClickListener() {
            @Override public void onClick(View view) {
                onClickListener.onClick(btnSearch);
            }
        });
    }


}
