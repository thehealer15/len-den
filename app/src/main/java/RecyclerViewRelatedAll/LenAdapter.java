package RecyclerViewRelatedAll;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Random;

import Screens.Payment_Acvt;

public class LenAdapter extends RecyclerView.Adapter<LenAdapter.ViewHolder>{
    private feed_item_model[] listdata;
    DatabaseReference r , dbref;
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
        r = FirebaseDatabase.getInstance().getReference().child("portfolio");
        dbref = FirebaseDatabase.getInstance().getReference().child("orders").child(FirebaseAuth.getInstance().getUid());
        holder.amount.setText(myListData.getAmount());
        holder.tenure.setText(myListData.getTenure());
        holder.credit_score.setText(myListData.getCredit_score());
        holder.interest_rate.setText(myListData.getInterest_rate());

        holder.deal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // set up deal here only ! ; take confirmation
                new AlertDialog.Builder(view.getContext())
                        .setTitle("Invest?")
                        .setMessage("You wanna invest in this ?")
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog, int whichButton) {
                                Toast.makeText(view.getContext(), "Done!", Toast.LENGTH_SHORT).show();
                               FirebaseDatabase.getInstance().getReference("portfolio").child(FirebaseAuth.getInstance().getUid()).child("lent").setValue(holder.amount.getText().toString());
                                dbref.child(""+ new Random().nextInt()).child("Lent: ").setValue(holder.amount.getText().toString());
                            }})
                        .setNegativeButton(android.R.string.no, null).show();

            }
        });
    }
    String[] getLenValue(){
        DatabaseReference portfolio = FirebaseDatabase.getInstance().getReference().child("portfolio").child(FirebaseAuth.getInstance().getUid());
        final String[] ret_val = new String[1];
        portfolio.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ret_val[0] = snapshot.getValue(String.class);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
return  ret_val;
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