package Screens;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.example.app.R;

public class Payment_Acvt extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        Toast.makeText(getApplicationContext(), "call razorpay here in payment acvt", Toast.LENGTH_SHORT).show();

    }
}