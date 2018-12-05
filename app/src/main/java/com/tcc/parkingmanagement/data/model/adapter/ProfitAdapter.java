package com.tcc.parkingmanagement.data.model.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tcc.parkingmanagement.R;
import com.tcc.parkingmanagement.data.model.bean.ProfitBean;

import java.util.List;

/**
 * 项目名称：    com.tcc.parkingmanagement.data.model.adapter
 * 类描述
 * 创建人：      139061759@qq.com
 * 创建时间：    2018/8/22
 * 修改人：      1392061759@qq.com
 * 修改时间：    2018/8/22
 * 修改备注：
 */
public class ProfitAdapter extends RecyclerView.Adapter<ProfitAdapter.ViewHolder> {

    private Context context;

    private LayoutInflater inflater;

    private List<ProfitBean> list;

    public ProfitAdapter(Context context, List<ProfitBean> list) {
        this.context = context;
        this.list = list;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.item_profit,null));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tv_time.setText(list.get(position).getTime());
        holder.tv_money.setText(list.get(position).getTotalAmount());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        TextView tv_time;
        TextView tv_money;

        public ViewHolder(View itemView) {
            super(itemView);
            tv_time = (TextView) itemView.findViewById(R.id.time);
            tv_money = (TextView) itemView.findViewById(R.id.tv_money);
        }
    }
}
