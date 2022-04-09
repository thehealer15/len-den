package Screens.authentication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import android.widget.Toast;

import com.example.app.MainActivity;
import com.example.app.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.AuthResult;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuthEmailException;
import com.google.firebase.auth.FirebaseAuthException;

public class registerFragment extends Fragment {
    View parent;
    Context mContext;
    EditText name , surname , bankAc , pan , email , passwd;
    Button createAc;
//    private ProgressBar progressbar;//
    private FirebaseAuth mAuth;

    private void init(){
        createAc = parent.findViewById(R.id.create_ac_btn);
        name  = parent.findViewById(R.id.name_register);
        surname = parent.findViewById(R.id.surname_register);
        bankAc  = parent.findViewById(R.id.bank_ac_register);
        pan =  parent.findViewById(R.id.pan_register);
        passwd = parent.findViewById(R.id.passwd_register);
        email= parent.findViewById(R.id.email_register);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mContext = context;
    }


    public registerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        parent =  inflater.inflate(R.layout.fragment_register, container, false);
        init();
        mAuth = FirebaseAuth.getInstance();
        createAc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerNewUser();
            }
        });

        return parent;
    }
    private void registerNewUser()
    {
        String email_str = email.getText().toString();
        String password = passwd.getText().toString();
        if(password.length() == 0|| email_str.length() == 0) {
            Toast.makeText(getActivity(), "Gaval", Toast.LENGTH_SHORT).show();
            return;
        }
        // create new user or register new user
        mAuth.createUserWithEmailAndPassword(email_str, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task)
                    {
                        if (task.isSuccessful()) {
                            Toast.makeText(getActivity(),
                                    "Registration successful!",
                                    Toast.LENGTH_LONG)
                                    .show();
//                              progressbar.setVisibility(View.GONE);
//                              try{
//                                Intent intent = new Intent(getActivity(), MainActivity.class);
//                                startActivity(intent);
//                            }catch (Exception e){
//                                Toast.makeText(mContext, e.getMessage(), Toast.LENGTH_SHORT).show();
//                            }
                        }
                        else {
                            // Registration failed
                            Toast.makeText(getActivity(),  "Registration failed!!  Please try again later", Toast.LENGTH_LONG).show();
//                            progressbar.setVisibility(View.GONE);
                        }
                    }
                });
    }
}