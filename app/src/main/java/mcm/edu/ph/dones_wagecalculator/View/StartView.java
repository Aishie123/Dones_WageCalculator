package mcm.edu.ph.dones_wagecalculator.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import mcm.edu.ph.dones_wagecalculator.R;

@SuppressWarnings("FieldCanBeLocal")
public class StartView extends AppCompatActivity {

    private EditText txtHours, txtName;
    private final String TAG = "start";
    private int employeeType;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide(); //hide the action bar
        setContentView(R.layout.activity_start_view);
        initUI();
    }

    // initializing
    private void initUI() {
        txtHours = findViewById(R.id.txtHours);
        txtName = findViewById(R.id.txtName);
    }

    // employee types
    public void regular(View v) { employeeType = 1; }
    public void probationary(View v) { employeeType = 2; }
    public void partTime(View v) { employeeType = 3; }

    // go to calculated wage activity
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
            finish();
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        }

        else{
            Toast.makeText(getApplicationContext(),"Please enter complete details.",Toast.LENGTH_LONG).show();
        }

    }

}