package RecyclerViewRelatedAll;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.app.R;

import Screens.Payment_Acvt;

public class LenAdapter extends RecyclerView.Adapter<LenAdapter.ViewHolder>{
    private feed_item_model[] listdata;

    // RecyclerView recyclerView;
    public LenAdapter(feed_item_model[] listdata) {
        this.listdata = listdata;
    }
    @Override
    public LenAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.item_feed_card, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final feed_item_model myListData = listdata[position];

        holder.amount.setText(myListData.getAmount());
        holder.tenure.setText(myListData.getTenure());
        holder.credit_score.setText(myListData.getCredit_score());
        holder.interest_rate.setText(myListData.getInterest_rate());

        holder.deal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    Intent i = new Intent(view.getContext(), Payment_Acvt.class);
                    view.getContext().startActivity(i);
                }catch (Exception e){
                    Log.d("lenAdapter_onclick" , e.getMessage());
                    Toast.makeText(view.getContext(), "something went wrong!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return listdata.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
       public Button deal;
        public TextView amount, credit_score, interest_rate , tenure;
        public ViewHolder(View itemView) {
            super(itemView);
            this.amount = itemView.findViewById(R.id.amount_cardview);
            // credit score, interest rate, tenure, btn_deal
            this.deal =  itemView.findViewById(R.id.deal_btn);
            this.interest_rate = itemView.findViewById(R.id.interest_rate_cardview);
            this.credit_score =  itemView.findViewById(R.id.credit_score_cardview);
            this.tenure =  itemView.findViewById(R.id.tenure_cardview);
        }
    }
}