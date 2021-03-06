
package internetspeed.datausage.spedwatche.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import internetspeed.datausage.spedwatche.utils.DataInfo;
import internetspeed.datausage.spedwatche.R;

public class GraphAdapter extends RecyclerView.Adapter<GraphAdapter.DataViewHolder> {

    public List<DataInfo> dataList;

    public GraphAdapter(List<DataInfo> dataList) {
        this.dataList = dataList;


    }

    public class DataViewHolder extends RecyclerView.ViewHolder {
        public TextView vDate;
        public  TextView vWifi;
        public  TextView vMobile;
        public  TextView vTotal;

        public DataViewHolder(View itemView) {
            super(itemView);

            vDate = (TextView) itemView.findViewById(R.id.id_date);
            vWifi = (TextView) itemView.findViewById(R.id.id_wifi);
            vMobile = (TextView) itemView.findViewById(R.id.mobile);
            vTotal = (TextView) itemView.findViewById(R.id.total);
        }
    }

    @Override
    public DataViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.card_layout, parent, false);

        return new DataViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(DataViewHolder holder, int position) {

        DataInfo di = dataList.get(position);

        holder.vDate.setText(di.date);
        holder.vWifi.setText(di.wifi);
        holder.vMobile.setText(di.mobile);
        holder.vTotal.setText(di.total);

    }

    @Override
    public int getItemCount() {

        return dataList.size();
    }

}
