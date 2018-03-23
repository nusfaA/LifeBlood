package com.graise.ansafn.managelifeblood;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.graise.ansafn.managelifeblood.EligibilityCriteria.EligibilityCriteria;
import com.graise.ansafn.managelifeblood.R;

import java.util.List;

/**
 * Created by ansaf.n on 2/25/2018.
 */

/*Converter class for EligibilityCriteria to display as a list*/

public class CustomAdapter extends BaseAdapter {

    private Context mContext;
    private List<EligibilityCriteria> lstCriterias;

    public CustomAdapter(Context mContext, List<EligibilityCriteria> lstCriterias) {
        this.mContext = mContext;
        this.lstCriterias = lstCriterias;
    }

    @Override
    public int getCount() {
        return lstCriterias.size();
    }

    @Override
    public Object getItem(int position) {
        return lstCriterias.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view1 = inflater.inflate(R.layout.row,null);

        TextView txtUser = (TextView)view1.findViewById(R.id.txtCriteria);
        txtUser.setText(lstCriterias.get(position).getCriteria());

        TextView txtValue = (TextView)view1.findViewById(R.id.txtValue);
        txtValue.setText(lstCriterias.get(position).getValue());

        TextView txtPrioity = (TextView)view1.findViewById(R.id.txtPriority);
        txtPrioity.setText(lstCriterias.get(position).getPriority());


        return view1;

    }
}
