package JP3;

import javax.swing.*;

public class OppgaveO1 {

    private static boolean inRange(long x, long min, long max) {
        return x >= min && x <= max;
    }
    
    public static void main(String[] args) {
        var incomeStr = JOptionPane.showInputDialog("Bruttolønn: ");
        long income = 0;

        try {
            income = Long.parseLong(incomeStr);
        } catch(NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Ugyldig verdi!");
            return;
        }

        double taxPercent = 0.0;

        if (inRange(income, 164101, 230950)) {
            taxPercent = 0.93;
        } else if (inRange(income, 230951, 580650)) {
            taxPercent = 2.41;
        } else if (inRange(income, 580651, 934050)) {
            taxPercent = 11.52;
        } else if (income >= 934051) {
            taxPercent = 14.52;
        }

        double toPayInTaxes = (double)income * (taxPercent / 100);
        double leftAfterTaxes = income - toPayInTaxes;

        var output = "Sats: " + taxPercent
            + "\nÅ betale i skatt: " + toPayInTaxes
            + "\nNettolønn: " + leftAfterTaxes;

        JOptionPane.showMessageDialog(null, output);
    }
}
