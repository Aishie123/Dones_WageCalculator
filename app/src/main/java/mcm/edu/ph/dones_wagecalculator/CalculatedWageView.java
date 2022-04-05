package mcm.edu.ph.dones_wagecalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.text.DecimalFormat;

public class CalculatedWageView extends AppCompatActivity {

    private TextView txtWage, txtWageOT, txtWageTotal, txtHoursTotal, txtHoursOT, txtTitle, txtEmployee;
    private String name;
    private String TAG = "calculated wage";
    private int employee;
    private double hoursTotal, hoursOT, wage, wageTotal, wageOT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculated_wage_view);
        initUI();
        wageResult();
    }

    public void initUI() {
        txtWage = findViewById(R.id.txtWage);
        txtWageOT = findViewById(R.id.txtWageOT);
        txtWageTotal = findViewById(R.id.txtWageTotal);
        txtHoursTotal = findViewById(R.id.txtHoursTotal);
        txtHoursOT = findViewById(R.id.txtHoursOT);
        txtTitle = findViewById(R.id.txtTitle);
        txtEmployee = findViewById(R.id.txtEmployee);
    }

    @SuppressLint("SetTextI18n")
    public void wageResult(){
        
        Bundle bundle = getIntent().getExtras();
        name = bundle.getString("name");
        employee = bundle.getInt("employee");
        hoursTotal = bundle.getDouble("hours");
        Log.d(TAG, "name: "+ name );
        Log.d(TAG, "employee type: "+ employee );
        Log.d(TAG, "hoursTotal: "+ hoursTotal );

        wageCalculate();

        txtTitle.setText(name + "'s Calculated Wage");
        txtWage.setText(String.valueOf(wage));
        txtWageOT.setText(String.valueOf(wageOT));
        txtWageTotal.setText(String.valueOf(wageTotal));
        txtHoursTotal.setText(String.valueOf(hoursTotal));
        txtHoursOT.setText(String.valueOf(hoursOT));

        switch (employee) {
            case 1: txtEmployee.setText("Regular"); break;
            case 2: txtEmployee.setText("Probationary"); break;
            case 3: txtEmployee.setText("Part-Time"); break;
        }

    }


    double roundTwoDecimals(double d)
    {
        DecimalFormat twoDForm = new DecimalFormat("#.##");
        return Double.parseDouble(twoDForm.format(d));
    }

    public void wageCalculate() {
        switch (employee){
            case 1:
                if (hoursTotal <= 8 ){
                    wageTotal = wage = roundTwoDecimals(100 * hoursTotal);
                    wageOT = 0;
                }
                else {
                    Log.d(TAG, "OVERTIME!");
                    wage = (100 * 8);
                    hoursOvertime();
                    wageOT = roundTwoDecimals(hoursOT * 115);
                    wageOvertime();
                }
                break;
            case 2:
                if (hoursTotal <= 8 ){
                    wageTotal = wage = roundTwoDecimals(90 * hoursTotal);
                    wageOT = 0;
                }
                else {
                    Log.d(TAG, "OVERTIME!");
                    wage = (90 * 8);
                    hoursOvertime();
                    wageOT = roundTwoDecimals(hoursOT * 100);
                    wageOvertime();
                }
                break;
            case 3:
                if (hoursTotal <= 8 ){
                    wageTotal = wage = roundTwoDecimals(75 * hoursTotal);
                    wageOT = 0;
                }
                else {
                    Log.d(TAG, "OVERTIME!");
                    wage = (75 * 8);
                    hoursOvertime();
                    wageOT = roundTwoDecimals(hoursOT * 90);
                    wageOvertime();
                }
                break;
        }
    }

    public void hoursOvertime(){ hoursOT = hoursTotal - 8; }
    public void wageOvertime(){ wageTotal = wage + wageOT; }

    public void wageRestart(View v){
        finish();
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

}