package com.example.app;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import RecyclerViewRelatedAll.feed_item_model;


public class denFragment extends Fragment {
    private Button put_deal_on_table;
    static int c = 0;
    View parent;
    EditText amount , tenure , interest_rate;
    TextView credit_score;
    DatabaseReference root_ref , orders_history;
    Random rd ;
    public denFragment() {
        // Required empty public constructor
    }
    void init(){
        amount = parent.findViewById(R.id.amount_den);
        tenure = parent.findViewById(R.id.tenure_den);
        credit_score = parent.findViewById(R.id.credit_score_den);
        interest_rate = parent.findViewById(R.id.interest_rate_den);
        root_ref = FirebaseDatabase.getInstance().getReference();

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

         parent =  inflater.inflate(R.layout.fragment_den, container, false);
        put_deal_on_table = parent.findViewById(R.id.usr_lend_btn);
        init();
            DatabaseReference ref = root_ref.child("portfolio").child(FirebaseAuth.getInstance().getUid())
.child("borrowed");
            orders_history = root_ref.child("orders").child(FirebaseAuth.getInstance().getUid());
        put_deal_on_table.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String val = amount.getText().toString();
                    Toast.makeText(getContext(), amount.getText().toString(), Toast.LENGTH_SHORT).show();
                    rd = new Random();
                    
//                    den_ref.child(FirebaseAuth.getInstance().getUid()+ "-" + rd.nextInt()).updateChildren(len_info);
                    feed_item_model model = new feed_item_model(credit_score.getText().toString(),  amount.getText().toString()
                            ,  tenure.getText().toString(), interest_rate.getText().toString());
                    try{
                        ref.setValue(amount.getText().toString());
                    }catch (Exception e){
                        Log.d("uploading den class to firebase" , e.getMessage());
                        Toast.makeText(getContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
                    }
                    amount.setText("");
                    tenure.setText("");
                    interest_rate.setText("");

                    // add order to list here
                    orders_history.child(""+new Random().nextInt()).child("Borrowed: ").setValue(val);
                }
            });

        return parent;
    }
}