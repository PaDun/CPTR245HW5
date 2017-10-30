import java.io.*;

public class Stutter {
    public Stutter() {
    }


    public static String stut(String filePath) throws IOException {
        FileReader fr = new FileReader(filePath);
        BufferedReader br = new BufferedReader(fr);
        String line;
        String output = "";
        String [] lineArray;
        int linecnt = 0;
        String prevW;

        while ((line = br.readLine()) != null) {
            ++linecnt;
            lineArray = line.split("\\W+");

            prevW =lineArray[0];
            for (int i = 1; i < lineArray.length; i++) {
                if(lineArray[i].equals(prevW)) {
                    output = output + String.format("%s ln %d, ", lineArray[i], linecnt);
                }
                else {
                    prevW = lineArray[i];
                }
            }
        }


        return output;
        //return "nibh ln 7, nostra ln 11";
    }

 }
