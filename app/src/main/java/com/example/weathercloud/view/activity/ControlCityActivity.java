package com.example.weathercloud.view.activity;

import android.os.Bundle;

import android.view.View;
import android.widget.ImageView;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weathercloud.R;
import com.example.weathercloud.adapter.ControlCityAdapter;
import com.example.weathercloud.bean.CityBean;
import com.example.weathercloud.bean.CityBeanList;
import com.example.weathercloud.utils.ContentUtil;
import com.example.weathercloud.utils.SpUtils;

import java.util.ArrayList;
import java.util.List;

public class ControlCityActivity extends BaseActivity {
    private List<CityBean> datas = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_control_city);
        ImageView ivBack = findViewById(R.id.iv_control_back);
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        RecyclerView rcControl = findViewById(R.id.recycle_control);
        CityBeanList cityBeanList = SpUtils.getBean(this, "cityBean", CityBeanList.class);
        if (ContentUtil.APP_SETTING_LANG.equals("en") || ContentUtil.APP_SETTING_LANG.equals("sys") && ContentUtil.SYS_LANG.equals("en")) {
            cityBeanList = SpUtils.getBean(this, "cityBeanEn", CityBeanList.class);
        }
        if (cityBeanList != null) {
            datas = cityBeanList.getCityBeans();
        }
        LinearLayoutManager forecastManager = new LinearLayoutManager(this);
        forecastManager.setOrientation(LinearLayoutManager.VERTICAL);
        ControlCityAdapter followCityAdapter = new ControlCityAdapter(this, datas);

        rcControl.setAdapter(followCityAdapter);
        rcControl.setLayoutManager(forecastManager);
        rcControl.setItemAnimator(new DefaultItemAnimator());
    }
}
