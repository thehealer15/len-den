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
import com.google.android.material.textfield.TextInputLayout;import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.AuthResult;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;


public class loginFragment extends Fragment {
    private View parent;
    private Context mContext;
    private EditText email_id ,passwd;
    private Button signIn , go_back_register;
    private FirebaseAuth mAuth;
    private void init(){
        signIn = parent.findViewById(R.id.sign_in_login_page);
        go_back_register = parent.findViewById(R.id.register_login_page);
        email_id = parent.findViewById(R.id.user_email_login_page);
        passwd = parent.findViewById(R.id.user_passwd_login_page);
    }
    
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mContext = context;
    }

    public loginFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        parent =  inflater.inflate(R.layout.fragment_login, container, false);
        init();
        
        go_back_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Going Back", Toast.LENGTH_SHORT).show();
            }
        });
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });

        return parent ;
    }

    private void login(){
        String email_str = email_id.getText().toString();
        String password_str = passwd.getText().toString();

        // validations for input email and password
        if (TextUtils.isEmpty(email_str)) {
            Toast.makeText(getActivity(), "Please enter email!!", Toast.LENGTH_LONG).show();
            return;
        }

        if (TextUtils.isEmpty(password_str)) {
            Toast.makeText(getActivity(),
                    "Please enter password!!",
                    Toast.LENGTH_LONG)
                    .show();
            return;
        }

        // signin existing user
        mAuth.signInWithEmailAndPassword(email_str, password_str)
                .addOnCompleteListener(
                        new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(
                                    @NonNull Task<AuthResult> task)
                            {
                                if (task.isSuccessful()) {
                                    Toast.makeText(getActivity(),
                                            "Login successful!!",
                                            Toast.LENGTH_LONG)
                                            .show();

                                    // hide the progress bar
//                                    progressBar.setVisibility(View.GONE);

                                    // if sign-in is successful
                                    // intent to home activity
//                                    Intent intent
//                                            = new Intent(getActivity(),
//                                            MainActivity.class);
//                                    startActivity(intent);
                                }
                                else {

                                    Toast.makeText(getActivity(),
                                            "Login failed!!",
                                            Toast.LENGTH_LONG)
                                            .show();

                                    // hide the progress bar
//                                    progressbar.setVisibility(View.GONE);
                                }
                            }
                        });
    }
    /*
        Intent i = new Intent(getActivity(), MainActivity.class);
        i.putExtra("path" , "MainActivity");
        startActivity(i);
        **/
}