package mcm.edu.ph.dones_wagecalculator.View;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.text.DecimalFormat;

import mcm.edu.ph.dones_wagecalculator.Controller.Calculator;
import mcm.edu.ph.dones_wagecalculator.R;

public class CalculatedWageView extends AppCompatActivity {

    private TextView txtWage, txtWageOT, txtWageTotal, txtHoursTotal, txtHoursOT, txtTitle, txtEmployee;
    private final String TAG = "calculated wage";
    private int employeeType;
    private double hoursTotal, hoursOT, wage, wageTotal, wageOT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide(); //hide the action bar
        setContentView(R.layout.activity_calculated_wage_view);
        initUI();
        wageResult();
    }

    // initializing UI
    private void initUI() {
        txtWage = findViewById(R.id.txtWage);
        txtWageOT = findViewById(R.id.txtWageOT);
        txtWageTotal = findViewById(R.id.txtWageTotal);
        txtHoursTotal = findViewById(R.id.txtHoursTotal);
        txtHoursOT = findViewById(R.id.txtHoursOT);
        txtTitle = findViewById(R.id.txtDisplayTitle);
        txtEmployee = findViewById(R.id.txtEmployee);
    }

    @SuppressLint("SetTextI18n")
    private void wageResult(){
        
        Bundle bundle = getIntent().getExtras();
        String name = bundle.getString("name");
        employeeType = bundle.getInt("employee");
        hoursTotal = bundle.getDouble("hours");
        Log.d(TAG, "name: "+ name);
        Log.d(TAG, "employeeType: "+ employeeType);
        Log.d(TAG, "hoursTotal: "+ hoursTotal);

        wageCalculate(); // calculates the wage

        // displaying employee wage and info
        txtTitle.setText(name + "'s Calculated Wage");
        txtWage.setText("₱" + wage);
        txtWageOT.setText("₱" + wageOT);
        txtWageTotal.setText("₱" + wageTotal);
        txtHoursTotal.setText(String.valueOf(hoursTotal));
        txtHoursOT.setText(String.valueOf(hoursOT));

        switch (employeeType) {
            case 1: txtEmployee.setText("Regular"); break;
            case 2: txtEmployee.setText("Probationary"); break;
            case 3: txtEmployee.setText("Part-Time"); break;
        }
    }

    // method to calculate the wage
    private void wageCalculate() {
        Calculator calc = new Calculator();
        wage = calc.wage(employeeType, hoursTotal);
        if (hoursTotal > 8){ // if overtime
            Log.d(TAG, "OVERTIME!");
            hoursOT = calc.hoursOT(hoursTotal);
            wageOT = calc.overtimeWage(employeeType, hoursOT);
        }
        wageTotal = calc.totalWage(wage, wageOT);
    }

    // when "Restart" button is pressed
    public void wageRestart(View v){
        Intent i = new Intent(CalculatedWageView.this, StartView.class);
        startActivity(i);
        finish();
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

}