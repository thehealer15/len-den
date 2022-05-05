package Screens;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.example.app.R;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseUser;

import RecyclerViewRelatedAll.LenAdapter;
import RecyclerViewRelatedAll.feed_item_model;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import java.util.ArrayList;

/*
* my features :==>
    Basically i can keep 2 types of Instruments;
    1. Investment
        - mid to long term
    2. Short Term
        - will be sold to business / buyers within 3 years

*  LOCK IN *

    Allocation Strategy  :
    * BUY SELL should be allowed to both :
    <== short term / actionable ==>
        1. every year premium of 13%
    <== Investment ==>
        1. 3 years LOCK IN period

        redeem option:
        *
 at backend :
 * Table 1
    there will be java class as Investment with parameters:
        * price now , type , date, units , price at that time;
* Table 2
    mapping of ( uuid <=> profile info completed )

*
** PROGRESS 2.0 **
now add realtime data base of firebase
* maintain 3 tables
* mapping (profile completed uuid - bool )
* information
* portfolio
*   - short term
*   - long term
* * * */
public class feed_fragment extends Fragment {
    private View parentHolder;
    private Context context;
    RecyclerView lendingView;
    DatabaseReference root_ref , den_ref;
//    ArrayList<feed_item_model> data = new ArrayList<>();
    int index = 0;
    feed_item_model data[] = new feed_item_model[]{
//    public feed_item_model(String credit_score, String amount, String tenure, String interest_rate)1 {
            new feed_item_model("435" , "100 inr" , "2 years" , "5.678"),
            new feed_item_model("451" , "100 inr" , "2 years" , "5.678"),
            new feed_item_model("435" , "100 inr" , "2 years" , "5.678"),
            new feed_item_model("232" , "100 inr" , "2 years" , "12.678"),
            new feed_item_model("435" , "100 inr" , "2 years" , "7.678")
    };

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
    }

    public feed_fragment() { }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        parentHolder =  inflater.inflate(R.layout.feed_fragment, container, false);
        lendingView = parentHolder.findViewById(R.id.lendgin_view);
        FirebaseApp.initializeApp(context);
        LenAdapter adapter = new LenAdapter(data);


        try{
            root_ref = FirebaseDatabase.getInstance().getReference();
            den_ref = root_ref.child("den");
//        den_ref.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
//                    for(DataSnapshot temp : dataSnapshot.getChildren()){
//                        for(DataSnapshot x : temp.getChildren()){
//                            feed_item_model model2 = x.getValue(feed_item_model.class);
//                            data[index++] = (model2);
//                        }
//                    }
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });

            lendingView.setHasFixedSize(true);
            lendingView.setLayoutManager(new LinearLayoutManager(context));
            lendingView.setAdapter(adapter);
        }catch (Exception e){
            Log.d("errmsg" , e.getMessage());
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();
        }


        return parentHolder;
 }


    private void showSnackBar(String text){
        Snackbar.make(getActivity().findViewById(android.R.id.content), text, Snackbar.LENGTH_LONG)
                .show();
    }
    private void showToast(String text){
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
    }
}
/*
Alertdialog box code dump

 new AlertDialog.Builder(context)
                    .setTitle("Investment Done!")
                    .setMessage("Congratulations you have invested in most recession proof asset class")
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // Continue with delete operation
                        }
                    })

                    // A null listener allows the button to dismiss the dialog and take no further action.
                    .setNegativeButton(android.R.string.no, null)
                    .setIcon(android.R.drawable.star_on)
                    .show();
* */