package com.example.weathercloud.bean;

import java.util.List;

public class ForecastBean {
    private List<SmartForecastBean> beans;

    public List<SmartForecastBean> getBeans() {
        return beans;
    }

    public void setBeans(List<SmartForecastBean> beans) {
        this.beans = beans;
    }
}
