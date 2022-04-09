package Screens.profile;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.app.R;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;


public class AccountDetailsFragament extends Fragment {
    private View parentHolder;
    private Button editBtn , save_infoBtn;
    private TextInputLayout name, email , bankAccountNo, pan , mobile_no, nominee ;
    private void init(){
        name = parentHolder.findViewById(R.id.user_name);
        email = parentHolder .findViewById(R.id.user_email);
        bankAccountNo = parentHolder.findViewById(R.id.user_bank_ac);
        pan = parentHolder.findViewById(R.id.user_pan_card);
        mobile_no = parentHolder.findViewById(R.id.user_mobile_no);
        nominee = parentHolder.findViewById(R.id.user_Nominee);
        editBtn = parentHolder.findViewById(R.id.edit_info);
        save_infoBtn = parentHolder.findViewById(R.id.save_info);
    }

    public AccountDetailsFragament() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        parentHolder =  inflater.inflate(R.layout.account_details_fragament, container, false);
        init();
        controlEditText(false);
        loadData();

        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                controlEditText(true);
            }
        });

        save_infoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Map<String, Object> map = new HashMap<>();
                map.put("name" ,getModi(name));
                map.put("email", getModi(email));
                map.put("bankAccountNo", getModi(bankAccountNo));
                map.put("pan", getModi(pan));
                map.put("mobile_no" , getModi(mobile_no));
                map.put("nominee" , getModi(nominee));

                try{
                    FirebaseDatabase.getInstance().getReference().child("info").child(FirebaseAuth.getInstance().getUid()).updateChildren(map);
                    Toast.makeText(getActivity(), "Updated Successfully", Toast.LENGTH_SHORT).show();
                }catch (Exception e){
                    Toast.makeText(getActivity(), "Some error happened while updating to database", Toast.LENGTH_LONG).show();
                }
            }
        });
        return parentHolder;
    }
    private String getModi(TextInputLayout v){
        return v.getEditText().getText().toString();
    }
    private void putEditTextName(TextInputLayout t , String s){
        t.getEditText().setText(s);
    }
    private  void controlEditText(boolean val){
        name.getEditText().setFocusableInTouchMode(val);
        email.getEditText().setFocusableInTouchMode(val);
        bankAccountNo.getEditText().setFocusableInTouchMode(val);
        pan.getEditText().setFocusableInTouchMode(val);
        mobile_no.getEditText().setFocusableInTouchMode(val);
        nominee.getEditText().setFocusableInTouchMode(val);
    }

    private void showSnackBar(String text){
        Snackbar.make(getActivity().findViewById(android.R.id.content), text, Snackbar.LENGTH_LONG)
                .show();
    }
    private void loadData(){
        DatabaseReference mFirebaseDatabaseReference = FirebaseDatabase.getInstance().getReference();
        Query query = mFirebaseDatabaseReference.child("info").child(FirebaseAuth.getInstance().getUid()).orderByChild("title").equalTo("#Yahoo");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                try {
                    Map map ;
                    map = snapshot.getValue(Map.class);
                    if(map == null) return;
                    putEditTextName(name, map.get("name").toString());
                    putEditTextName(email, map.get("email").toString());
                    putEditTextName(bankAccountNo, map.get("bankAccountNo").toString());
                    putEditTextName(pan, map.get("pan").toString());
                    putEditTextName(mobile_no, map.get("mobile_no").toString());
                    putEditTextName(nominee, map.get("nominee").toString());
                }catch (Exception e){
                    Toast.makeText(getActivity(), "can't load data", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}