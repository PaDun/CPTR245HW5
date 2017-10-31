import java.io.*;

public class Stutter {
    public Stutter() {
    }


    public static String stut(String filePath) throws IOException {
        try {
            FileReader fr = new FileReader(filePath);
            BufferedReader br = new BufferedReader(fr);

            String line;
            String output = "";
            String[] lineArray;
            int linecnt = 0;
            String prevW;

            while ((line = br.readLine()) != null) {
                ++linecnt;
                lineArray = line.split("\\W+");

                prevW = lineArray[0];
                for (int i = 1; i < lineArray.length; i++) {
                    if (lineArray[i].equals(prevW)) {
                        output = output + String.format("%s ln %d, ", lineArray[i], linecnt);
                    } else {
                        prevW = lineArray[i];
                    }
                }
            }

            if(output.length() == 0){
                return "No Duplicates";
            }
            else {
                output = output.substring(0, output.length() - 2);
            }

            return output;

        } catch (FileNotFoundException ex) {
            return String.format("File %s not found", filePath);
        }
    }
 }
