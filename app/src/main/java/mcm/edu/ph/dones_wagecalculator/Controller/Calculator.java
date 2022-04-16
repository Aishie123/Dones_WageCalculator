package mcm.edu.ph.dones_wagecalculator.Controller;

import android.util.Log;

import java.text.DecimalFormat;

public class Calculator {

    private double wage, wageOT;

    public double wage(int employee, double hoursTotal) {
        if ( hoursTotal > 8 ){ hoursTotal = 8; }
        switch (employee){
            case 1: wage = 100 * hoursTotal; break;
            case 2: wage = 90 * hoursTotal; break;
            case 3: wage = 75 * hoursTotal; break;
        }
        return roundTwoDecimals(wage);
    }

    public double overtimeWage(int employee, double hoursOT) {
        switch (employee){
            case 1: wageOT = 115 * hoursOT; break;
            case 2: wageOT = 100 * hoursOT; break;
            case 3: wageOT = 90 * hoursOT; break;
        }
        return roundTwoDecimals(wageOT);
    }

    public double hoursOT(double hoursTotal){ return hoursTotal - 8; }
    public double totalWage(double wage, double wageOT){ return wage + wageOT; }

    public double roundTwoDecimals(double d) {
        DecimalFormat twoDForm = new DecimalFormat("#.##");
        return Double.parseDouble(twoDForm.format(d));
    }

}
