package JP3;

import javax.swing.*;

public class OppgaveO3 {
    private static long factorial(long n) {
        if (n == 0) {
            return 1;
        }
        // Rekursive metoder er mer passende for rekursive funksjoner som f.eks fakultet.
        return n * factorial(n - 1);
    }
    public static void main(String[] args) {
        var nStr = JOptionPane.showInputDialog("Antall iterasjoner: (n)");
        long n = 0;

        try {
            n = Long.parseLong(nStr);
        } catch(NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Ugyldig verdi!");
            return;
        }

        if (n < 0) {
            JOptionPane.showMessageDialog(null, "Tall må være > 0!");
            return;
        }
        
        JOptionPane.showMessageDialog(null, "n!: " + factorial(n));
    }
}