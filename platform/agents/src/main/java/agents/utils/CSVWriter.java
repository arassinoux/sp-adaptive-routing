package agents.utils;

import agents.algorithms.BanditAlgorithm;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

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

    public void writeArray(double[] array, BanditAlgorithm banditAlgorithm) {

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

    public void dispose()
    {
        pw.close();
        file = null;
    }
}
