import java.io.*;

public class Stutter {
    public Stutter() {
    }

    private static char delimits[] =
            {'	', ' ', ',', '.', '!', '-', '+', '=', ';', ':',
                    '?', '&', '{', '}', '\\'};


    public static String stut(String filePath) {
        FileReader fr = new FileReader(filePath);
        BufferedReader br = new BufferedReader(fr);
        String line = null;
    //    StringBuilder output = new StringBuilder("");
        String output = "";
        char currentChar;
        int linecnt = 0;

        while ((line = br.readLine()) != null) {
            ++linecnt;
            for (int i = )
                line.split("\t|\s|\,|\.|\!|\-|\+|\=|\;|\:|\?|\&|\{|\}|\\");
        }


        return "nibh ln 7, nostra ln 11";
    }

}
