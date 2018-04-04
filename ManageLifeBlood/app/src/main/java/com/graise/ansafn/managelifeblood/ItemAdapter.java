package com.graise.ansafn.managelifeblood;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.graise.ansafn.managelifeblood.DonationTypeQty.DonationTypeQty;

import java.util.List;

/**
 * Created by Uthpala on 4/2/2018.
 */

/*Converter class for DonationTypeQty to display as a list*/

public class ItemAdapter extends BaseAdapter {

    private Context mContext;
    private List<DonationTypeQty> lstItems;


    public ItemAdapter(Context mContext, List<DonationTypeQty> items) {
        this.mContext = mContext;
        this.lstItems = items;
    }

    @Override
    public int getCount() {
        return lstItems.size();
    }

    @Override
    public Object getItem(int position) {
        return lstItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //get the layout
        View view1 = inflater.inflate(R.layout.row_stockmgt,null);

        TextView txtUser = (TextView)view1.findViewById(R.id.txtItemName);
        txtUser.setText(lstItems.get(position).getItemName());

        TextView txtValue = (TextView)view1.findViewById(R.id.txtQty);
        txtValue.setText(String.valueOf(lstItems.get(position).getQty()));

        TextView txtPrioity = (TextView)view1.findViewById(R.id.txtItemId);
        txtPrioity.setText(lstItems.get(position).getId());


        return view1;

    }
}
