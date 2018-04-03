package com.graise.ansafn.managelifeblood;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.graise.ansafn.managelifeblood.Donor.Donor;

import java.util.List;

/**
 * Created by ansaf.n on 4/3/2018.
 */

public class DonorAdapter extends BaseAdapter {
    private Context mContext;
    private List<Donor> lstDonors;

    public DonorAdapter(Context mContext, List<Donor> lstDonors) {
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
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view1 = inflater.inflate(R.layout.dnr_row, null);

        TextView txtDnrName = (TextView) view1.findViewById(R.id.txtDnrName);
        TextView txtDnrLoc = (TextView) view1.findViewById(R.id.txtDnrLoc);
        TextView txtDnrScore = (TextView) view1.findViewById(R.id.txtDnrScore);

        txtDnrName.setText(lstDonors.get(position).getUserName());
        txtDnrLoc.setText(lstDonors.get(position).getLocCode());
        txtDnrScore.setText(String.valueOf(lstDonors.get(position).getScore()));

        return view1;

    }
}
