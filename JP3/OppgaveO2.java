package JP3;

import javax.swing.*;

public class OppgaveO2 {

    private static final int MAX_INPUTS = 10;

    private static boolean inRange(long x, long min, long max) {
        return x >= min && x <= max;
    }

    private static boolean gradeStudent() {
        var pointsStr = JOptionPane.showInputDialog("Poeng: ");
        int points = 0;

        try {
            points = Integer.parseInt(pointsStr);
        } catch(NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Ugyldig verdi!");
            return false;
        }

        char grade = 'X';

        if (inRange(points, 90, 100)) {
            grade = 'A';
        } else if (inRange(points, 80, 89)) {
            grade = 'B';
        } else if (inRange(points, 60, 79)) {
            grade = 'C';
        } else if (inRange(points, 50, 59)) {
            grade = 'D';
        } else if (inRange(points, 40, 49)) {
            grade = 'E';
        } else if (inRange(points, 0, 39)) {
            grade = 'F';
        }
        if (grade == 'X') {
            JOptionPane.showMessageDialog(null, "Poeng må være mellom [0, 100]!");
            return false;
        }

        JOptionPane.showMessageDialog(null, "Karakter: " + grade);

        return true;
    }

    public static void main(String[] args) {
        for (int i = 0; i < MAX_INPUTS; ++i) {
            while (!gradeStudent());
        }
    }    
}
