package Screens;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.app.R;
import com.google.firebase.auth.FirebaseAuth;

import Screens.authentication.WelcomeActivity;
import Screens.profile.AccountDetailsFragament;


public class fragment_profile extends Fragment {
    private View parentHolder;
    private Context context;
    private CardView account;
    private Button allOrders, helpNSupport ,log_out;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
    }

    public fragment_profile() {
        // Required empty public constructor
    }


//    private void disableEditText(EditText editText) {
//        editText.setFocusable(false);
//        editText.setEnabled(false);
//        editText.setCursorVisible(false);
////        editText.setKeyListener(null);
////        editText.setBackgroundColor(Color.TRANSPARENT);
//    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        parentHolder =  inflater.inflate(R.layout.fragment_profile, container, false);
        account = parentHolder.findViewById(R.id.cardView);
        allOrders = parentHolder.findViewById(R.id.all_orders_btn);
        helpNSupport = parentHolder.findViewById(R.id.help_n_support_btn);
        log_out = parentHolder.findViewById(R.id.log_out);

        if(FirebaseAuth.getInstance().getCurrentUser() == null){
            log_out.setVisibility(View.GONE);
        }

        account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadFragment(new AccountDetailsFragament());
            }
        });
        helpNSupport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(context)
                        .setTitle("Request Put")
                        .setMessage("You will recieve call from executive")
                        .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // Continue with delete operation
                            }
                        })
                        .setIcon(android.R.drawable.ic_menu_call)
                        .show();
            }
        });

        log_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getActivity() , WelcomeActivity.class));
            }
        });

        return parentHolder;
    }
    private boolean loadFragment(Fragment fragment) {
        //switching fragment
        if (fragment != null) {
            FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
            transaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right);
            transaction.replace(R.id.fragment_container, fragment);
            transaction.addToBackStack(null);
            transaction.commit();
            return true;
        }
        return false;
    }

}