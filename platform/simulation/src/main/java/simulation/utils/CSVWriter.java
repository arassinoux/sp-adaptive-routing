package simulation.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class CSVWriter {

    private File file;
    private PrintWriter pw;
    private int arrayCount;


    public CSVWriter(String fileName)
    {
        file = new File(fileName + ".csv");
        arrayCount = 0;

        try {
            pw = new PrintWriter(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void writeArray(double[] array) {

        if(arrayCount > 0)
            pw.print('\n');

        //pw.print(banditAlgorithm.getName() + ", ");

        for (int i = 0; i < array.length; i++) {

            pw.print(String.valueOf(array[i]));
            if(i != array.length - 1)
                pw.print(", ");

        }

        arrayCount++;
    }


    public void writeArray(int agentId, ArrayList<Double> array) {

        if(arrayCount > 0)
            pw.print('\n');

        pw.print(agentId + ", ");

        for (double value : array) {
            pw.print(String.valueOf(value) + ", ");
        }

        arrayCount++;
    }

    public void dispose()
    {
        pw.close();
        file = null;
    }
}
