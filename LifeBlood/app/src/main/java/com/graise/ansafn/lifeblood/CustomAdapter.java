package com.graise.ansafn.lifeblood;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.graise.ansafn.lifeblood.DonationRequest.DonationRequest;

import java.util.List;

/**
 * Created by ansaf.n on 2/24/2018.
 */

public class CustomAdapter extends BaseAdapter {

    private Context mContext;
    private List<DonationRequest> lstDonReq;

    public CustomAdapter(Context mContext, List<DonationRequest> lstDonors) {
        this.mContext = mContext;
        this.lstDonReq = lstDonors;
    }

    @Override
    public int getCount() {
        return lstDonReq.size();
    }

    @Override
    public Object getItem(int position) {
        return lstDonReq.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view1 = inflater.inflate(R.layout.req_row, null);

        TextView txtBloodGroup = (TextView) view1.findViewById(R.id.txtBlodGroup);
        TextView txtQuant = (TextView) view1.findViewById(R.id.txtQuantity);
        TextView txtDate = (TextView) view1.findViewById(R.id.txtDate);

        String txt1 = lstDonReq.get(position).getGroup();
        String txt2 = lstDonReq.get(position).getQuantity() + " Donors Required";
        String txt3 = "Before " + lstDonReq.get(position).getEndDate();

        txtBloodGroup.setText(txt1);
        txtQuant.setText(txt2);
        txtDate.setText(txt3);

        return view1;

    }
}
