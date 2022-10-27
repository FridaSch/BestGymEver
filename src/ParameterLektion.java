import java.io.*;

public class ParameterLektion {

    public static void main(String[] args){

        ParameterLektion  pl = new ParameterLektion();
        pl.mainProgram();

    }

    public void mainProgram() {
        //anropa makeMyStringNice och spara resultatet i variablen fixedString
        String fixedString = makeMyStringNice("tjoSan");
        System.out.println(fixedString);

    }

    public String makeMyStringNice(String whatToFix)
    {
        //ta emot variabeln whatToFix, gör nåt nmed den och returnera resultatet
        String nicerString = whatToFix.toUpperCase();
        return nicerString;

        //enklare
        //return whatToFix.toUpperCase();

    }
}
