import javax.swing.*;
import java.io.*;
import java.time.LocalDate;
import java.time.Period;
import java.util.Scanner;

public class Gymprogram {
    public static void main(String[] args) {

        Gymprogram gmp =new Gymprogram();
        gmp.mainProgram();
    }
    public void mainProgram()
    {
        boolean personFound = false;
        boolean yearCardValid = false;
        String name ="";
        String pnr = "";

        try{
            BufferedReader bufferedReader = new BufferedReader(new FileReader("src/Personuppgifter"));
            Scanner scan = new Scanner(bufferedReader);

            String input = JOptionPane.showInputDialog("Skriv in ditt för- och efternamn eller ditt personnummer: ");

            while(scan.hasNextLine()) {
                String nameAndNumber = scan.nextLine();
                String paidDate = scan.nextLine();

                String[] personInfo = nameAndNumber.split(", ");
                name = personInfo[1];
                pnr = personInfo[0];

                if(input.equals(name) || input.equals(pnr))
                    personFound = true;

                if(personFound)
                {
                    LocalDate now = LocalDate.now();
                    LocalDate date = LocalDate.parse(paidDate);
                    Period period = Period.between(date, now);
                    int year = period.getYears();
                    if(year == 0)
                        yearCardValid = true;
                }

                if(personFound)
                    break;
            }
        }
        catch (FileNotFoundException e){
            jOptionHelper("Något blev fel.");
            System.exit(0);
        }

        if(personFound && yearCardValid)
        {
            try{
                PrintWriter printWriter = new PrintWriter(new BufferedWriter
                        (new FileWriter("src/PT-fil",true)));

                printWriter.println(name);
                printWriter.println(pnr);
                printWriter.println(LocalDate.now());
                printWriter.println("");
                printWriter.close();
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }

        if(!personFound)
            jOptionHelper("Du finns inte i registret!");
        if(personFound && !yearCardValid)
            jOptionHelper("Ditt årskort har gått ut.");
        if(personFound && yearCardValid)
            jOptionHelper("Välkommen att träna!");
    }

    public void jOptionHelper(String message)
    {
        JOptionPane.showMessageDialog(null,message,
                "Best Gym Ever", JOptionPane.PLAIN_MESSAGE);
    }
}
