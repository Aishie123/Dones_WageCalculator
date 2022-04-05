package mcm.edu.ph.dones_wagecalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class StartView extends AppCompatActivity {

    EditText txtHours, txtName;
    int employeeType;
    String TAG = "start";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_view);
        initUI();
    }

    // initializing
    public void initUI() {
        txtHours = findViewById(R.id.txtHours);
        txtName = findViewById(R.id.txtName);
    }

    // employee types
    public void regular(View v) { employeeType = 1; }
    public void probationary(View v) { employeeType = 2; }
    public void partTime(View v) { employeeType = 3; }

    // go to calculated wage activity
    @SuppressWarnings("ConstantConditions")
    public void startCalculate(View v) {

        if (employeeType != 0 && txtName != null && txtHours != null) {

            String name = txtName.getText().toString();
            double hours = Double.parseDouble(txtHours.getText().toString());
            Log.d(TAG,  "Hours:" + hours);

            Intent i = new Intent(StartView.this, CalculatedWageView.class);
            i.putExtra("employee", employeeType);
            i.putExtra("name", name);
            i.putExtra("hours", hours);
            startActivity(i);
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);

        }

        else{
            Toast.makeText(getApplicationContext(),"Please enter complete details.",Toast.LENGTH_LONG).show();
        }

    }

}