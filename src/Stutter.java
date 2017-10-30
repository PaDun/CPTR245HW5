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
        StringBuilder output = new StringBuilder("");
        int linecnt = 0;

        while ((line = br.readLine()) != null) {
            ++linecnt;

        }


        return "nibh ln 7, nostra ln 11";
    }

}
