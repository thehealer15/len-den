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

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


public class denFragment extends Fragment {
    private Button put_deal_on_table;
    static int c = 0;
    View parent;
    EditText amount , tenure , interest_rate;
    TextView credit_score;
    DatabaseReference root_ref;
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
            DatabaseReference den_ref = root_ref.child("den");
            put_deal_on_table.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Map<String, Object> len_info = new HashMap<>();
                    len_info.put("credit_score", credit_score.getText().toString());
                    len_info.put("amount", amount.getText().toString());
                    len_info.put("tenure", tenure.getText().toString());
                    len_info.put("interest_rate", interest_rate.getText().toString());

                    den_ref.child(""+c++).updateChildren(len_info);
                }
            });

        return parent;
    }
}