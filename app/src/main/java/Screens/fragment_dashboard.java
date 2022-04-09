package Screens;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.app.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class fragment_dashboard extends Fragment {

    private View parentHolder;
    private Context context;
    private DatabaseReference root , user_portfolio;
    TextView short_term_portfolio_value , long_term_portfolio_value;
    Button call_support_btn;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
    }
    public fragment_dashboard() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
         parentHolder =  inflater.inflate(R.layout.fragment_dashboard, container, false);
         call_support_btn = parentHolder.findViewById(R.id.call_support_btn);
         short_term_portfolio_value = parentHolder.findViewById(R.id.short_term_portfolio);
         long_term_portfolio_value= parentHolder.findViewById(R.id.long_term_portfolio);

         root = FirebaseDatabase.getInstance().getReference().child("portfolio");
        user_portfolio = root.child(FirebaseAuth.getInstance().getUid());
        update_port_folio();

        call_support_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(context)
                        .setTitle("Request Put")
                        .setMessage("You will receive call from executive")
                        .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // Continue with delete operation
                            }
                        })
                        .setIcon(android.R.drawable.ic_menu_call)
                        .show();
            }
        });

        return parentHolder;
    }
    private  void update_port_folio() {
        try {
            user_portfolio.child("short_term").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                   Integer value = snapshot.getValue(Integer.class);
                    short_term_portfolio_value.setText("\t\t" + value);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(getActivity(), "Failed to get data.", Toast.LENGTH_LONG).show();
                }
            });

            user_portfolio.child("long_term").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    Integer value = snapshot.getValue(Integer.class);
                    long_term_portfolio_value.setText("\t\t" + value);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(getActivity(), "Failed to get data.", Toast.LENGTH_LONG).show();
                }
            });
        } catch (Exception e) {
            Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}