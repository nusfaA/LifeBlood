package com.graise.ansafn.lifeblood;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.graise.ansafn.lifeblood.Donor.Donor;
import com.graise.ansafn.lifeblood.R;

import java.util.List;

/**
 * Created by ansaf.n on 2/24/2018.
 */

public class CustomAdapter extends BaseAdapter {

    private Context mContext;
    private List<Donor> lstDonors;

    public CustomAdapter(Context mContext, List<Donor> lstDonors) {
        this.mContext = mContext;
        this.lstDonors = lstDonors;
    }

    @Override
    public int getCount() {
        return lstDonors.size();
    }

    @Override
    public Object getItem(int position) {
        return lstDonors.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view1 = inflater.inflate(R.layout.row,null);

        TextView txtUser = (TextView)view1.findViewById(R.id.txtUser);
        txtUser.setText(lstDonors.get(position).getUsername());

        return view1;

    }
}
