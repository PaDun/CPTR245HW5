import java.io.*;

public class Stutter {
    public Stutter() {
    }


    public static String stut(String filePath) {
        if(!isTextFile(filePath)) {
            return String.format("%s is not a txt file", filePath);
        }

        try {
            FileReader fr = new FileReader(filePath);
            BufferedReader br = new BufferedReader(fr);

            String line;
            String output = "";
            String[] lineArray;
            int linecnt = 0;

            while ((line = br.readLine()) != null) {
                ++linecnt;
                lineArray = getWords(line);

                if (noWords(lineArray)) {
                    output = getDuplicates(output, lineArray, linecnt);
                }
            }

            if(outputEmpty(output)) {
                output = "No Duplicates";
            }
            else {
                output = cleanUpOutput(output);
            }

            return output;

        } catch (FileNotFoundException ex) {
            return String.format("File %s not found", filePath);
        } catch (IOException ex) {
            return String.format("Error reading file: %s", filePath);
        }
    }

    private static String cleanUpOutput(String output) {
        output = output.substring(0, output.length() - 2);
        return output;
    }

    private static boolean noWords(String[] lineArray) {
        return lineArray.length != 0;
    }

    private static boolean isTextFile(String filePath) {
        return filePath.substring(filePath.length()-4,filePath.length()).equals(".txt");
    }

    private static boolean outputEmpty(String output) {
        return output.length() == 0;
    }

    private static String[] getWords(String line) {
        String[] lineArray;
        lineArray = line.split("\\W+");
        return lineArray;
    }

    private static String getDuplicates(String output, String[] lineArray, int linecnt) {
        String prevW = lineArray[0];

        for (int i = 1; i < lineArray.length; i++) {
            if (lineArray[i].equals(prevW)) {
                output = output + String.format("%s ln %d, ", lineArray[i], linecnt);
                prevW = lineArray[i];
            } else {
                prevW = lineArray[i];
            }
        }
        return output;
    }
}
