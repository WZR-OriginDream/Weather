package com.example.weathercloud.adapter;

import android.annotation.SuppressLint;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.weathercloud.R;
import com.example.weathercloud.bean.CityBean;
import com.example.weathercloud.bean.CityBeanList;
import com.example.weathercloud.utils.ContentUtil;
import com.example.weathercloud.utils.SpUtils;
import com.example.weathercloud.view.activity.ControlCityActivity;

import java.util.List;

public class ControlCityAdapter extends RecyclerView.Adapter<ControlCityAdapter.MyViewHolder> {

    private List<CityBean> datas;
    private ControlCityActivity activity;

    public ControlCityAdapter(ControlCityActivity controlCityActivity, List<CityBean> datas) {
        activity = controlCityActivity;
        this.datas = datas;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_follow_city, viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, @SuppressLint("RecyclerView") final int i) {
        myViewHolder.tvItemCity.setText(datas.get(i).getCityName());
        myViewHolder.ivDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CityBeanList favorCity = SpUtils.getBean(activity, "cityBean", CityBeanList.class);
                CityBeanList favorCityEn = SpUtils.getBean(activity, "cityBeanEn", CityBeanList.class);
                List<CityBean> cityBeans = favorCity.getCityBeans();
                List<CityBean> cityBeansEn = favorCityEn.getCityBeans();
                for (int x = 0; x < cityBeans.size(); x++) {
                    if (cityBeans.get(x).getCityId().equals(datas.get(i).getCityId())) {
                        cityBeans.remove(x);
                    }
                }
                for (int x = 0; x < cityBeansEn.size(); x++) {
                    if (cityBeansEn.get(x).getCityId().equals(datas.get(i).getCityId())) {
                        cityBeansEn.remove(x);
                    }
                }

                datas.remove(i);

                CityBeanList cityBeanList = new CityBeanList();
                cityBeanList.setCityBeans(cityBeans);
                CityBeanList cityBeanListEn = new CityBeanList();
                cityBeanListEn.setCityBeans(cityBeansEn);
                SpUtils.saveBean(activity, "cityBeanEn", cityBeanListEn);
                SpUtils.saveBean(activity, "cityBean", cityBeanList);
                //????????????
                notifyItemRemoved(i);
                notifyDataSetChanged();
                ContentUtil.CITY_CHANGE = true;
//                DataUtil.deleteId(i);
            }
        });
    }


    @Override
    public int getItemCount() {
        return datas.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder {

        private final ImageView ivDelete;
        private final TextView tvItemCity;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ivDelete = itemView.findViewById(R.id.iv_item_delete);
            tvItemCity = itemView.findViewById(R.id.tv_item_city);

        }
    }
}
