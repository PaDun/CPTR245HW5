# Homework 5
### CPTR 245
### Patrick Dunphy & Charles Lambert

The functionality we decided to recreate using TDD was the Stutter program, which takes a file, and returns a string listing duplicate words and the line number they were on (For the sake of simplicity, we're omitting the custom files we made to test with). To start, we made a test checking that we get a string back listing the duplicated word, and the line (ln) that it appears on.

```java
  @Test
    public void testNormalBehavior() {
        //For the sake of both brevity and privacy, the full file paths will be omitted.
        assertEquals("Not expected return","nibh ln 7, nostra ln 11, ",Stutter.stut("blah.txt"));
    }
```

Shockingly, the test fails, as we have no implementation past the header.
```java
public class Stutter {
}
```

So, from there, we faked it. As you do.
```java
public class Stutter {
    public Stutter() {
    }
    public static String stut (String filePath){

        return "nibh ln 7, nostra ln 11";
    }
}
```

Of course, this implementation doesn't quite capture the functionality we're looking for, so let's make another test.
```java
 @Test
    public void testNewBehavior() {
        assertEquals("Not expected return", "amet ln 1, tortor ln 2", Stutter.stut("blah2.txt"));
    }
```

*Gasp!* We have broken the function tests again. Well, guess it's time to move towards a more useful implementation.
So, first we need to open the file we put in, and read from it. Using FileReader and BufferedReader seems like a sensible idea. While we're at it, perhaps it's skipping a bit, but let's add a line counter system as well, since we'll need one anyway.
```java
public static String stut(String filePath) {
        FileReader fr = new FileReader(filePath);
        BufferedReader br = new BufferedReader(fr);
        String line = null;
        int linecnt = 0;

        while ((line = br.readLine()) != null) {
            ++linecnt;
        }
        
        return "nibh ln 7, nostra ln 11";
    }
```

We can now open the file and read a line, but our test will still fail as we haven't yet gotten rid of our hard-coded return. Parsing the string sounds like it could be useful here. To do so, we'll use the string split method and a regular expression.
```java
public static String stut(String filePath) {
        FileReader fr = new FileReader(filePath);
        BufferedReader br = new BufferedReader(fr);
        String line;
        String [] lineArray;
        int linecnt = 0;

        while ((line = br.readLine()) != null) {
            ++linecnt;
            lineArray = line.split("\\W+");
        }
        return "nibh ln 7, nostra ln 11";
    }
```
Here, split takes a string, and splits it based on a delimiter, which the regular expression provides. We still have yet to purge the hard-coded return, though, but now we should be prepared to tackle it.

```java
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
        output = output.substring(0, output.length() - 2); //to get rid of the annoying ", " at the end of the string.
        return output;
    }
```
Hooray, our tests are passing! Unfortunately, we still have some problems to address. For one, suppose the file we feed it doesn't contain any duplicates. First, let's make a test for it.

```java
      @Test
    public void NoDupes() {
        assertEquals("not expected return", "No Duplicates", Stutter.stut("blah3.txt"));
}
```
It doesn't pass, as we'd expect, but it should be a simple implementation. Checking that the length of the output is zero should do well as a check for a lack of duplicates.

```java
            if(output.length() == 0){
                return "No Duplicates";
}
            else {
                //code to cut ", "
                }
                
            return output;
                
```
Alright, back to green. Just out of curiosity, if we feed it a file with nothing but delimiters, what do we get? I'd assume No Duplicates, but let's check anyway.

```java
    @Test
    public void noWordsAndDemlimiters() {
        assertEquals("not expected return", "No Duplicates", Stutter.stut("blah4.txt"));
}
```
It does. Good! While we're on unusual files, though, what if we try feeding it a file that isn't .txt? This calls for a new test!

```java
    @Test
    public void notATextFile() {
        assertEquals("not expected return", "1753.png is not a txt file", Stutter.stut("1753.png"));
}
```
Off the record, we struggled for a long time to figure out how we wanted to implement this. We'll spare you that story, though, and instead give the implementation we decided on, which was probably one of the simpler options we could've come up with.

```java
    public static String stut(String filePath) {
        if(!filePath.substring(filePath.length()-4,filePath.length()).equals(".txt")) {
            return String.format("%s is not a txt file", filePath);
}
```
Green. Just one more check before I think we can call the program functionally adequate: let's feed it a file that doesn't exist.

```java
     @Test
    public void fileDoesNotExist() {
        assertEquals("not expected return", "File junk.txt not found", Stutter.stut("junk.txt"));
}
```
Then, to implement so it passes. 

```java
 try {
 //code for the program
 } catch (FileNotFoundException ex) {
            return String.format("File %s not found", filePath);
 }
 ```
Everything's passing, now for the refactoring step. Let's look at the whole code again.

```java
import java.io.*;

public class Stutter {
    public Stutter() {
    }


    public static String stut(String filePath) {
        if(!filePath.substring(filePath.length()-4,filePath.length()).equals(".txt")) {
            return String.format("%s is not a txt file", filePath);
        }

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

                if (lineArray.length != 0) {
                    prevW = lineArray[0];
                    for (int i = 1; i < lineArray.length; i++) {
                        if (lineArray[i].equals(prevW)) {
                            output = output + String.format("%s ln %d, ", lineArray[i], linecnt);
                            prevW = lineArray[i];
                        } else {
                            prevW = lineArray[i];
                        }
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
        } catch (IOException ex) { //We have this more for good form than anything. We understand it goes against the "you
                                   //ain't gonna need it" mentality, but we couldn't help it.
             return String.format("Error reading file: %s", filePath);
        }
    }
}
```
Not exactly the prettiest code to look at. I feel some parts of this could be consolidated into their own methods. Let's tinker with it a bit. For one, the check for the .txt could probably be its own small method, so let's do that.

```java
         if(!isTextFile(filePath)) {
             return String.format("%s is not a txt file", filePath);
         }
         //code
         private static boolean isTextFile(String filePath) {
         return filePath.substring(filePath.length()-4,filePath.length()).equals(".txt");
   }
   ```
   The while loop parsing the string could probably be broken into a couple methods as well. So, let's try it.
   
   ```java
               while ((line = br.readLine()) != null) {
                 ++linecnt;
                 lineArray = getWords(line);
                 if (noWords(lineArray)) {
                    output = getDuplicates(output, lineArray, linecnt);
                 }
               }
               //code
               private static String[] getWords(String line) {
                 String[] lineArray;
                 lineArray = line.split("\\W+");
                 return lineArray;
               }
               
               private static boolean noWords(String[] lineArray) {
                 return lineArray.length != 0;
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
```
It could just be nitpicking at this point, but let's turn the check for an empty output and the cleaning-up of a proper one into methods as well. 

```java
               if(outputEmpty(output)) {
                 output = "No Duplicates";
               }
               else {
                 output = cleanUpOutput(output);
               }
               //code
               private static boolean outputEmpty(String output) {
                 return output.length() == 0;
               }
               private static String cleanUpOutput(String output) {
                 output = output.substring(0, output.length() - 2);
                 return output;
               }
```
And everything still works! So, we have a newly-cleaned, better-organized program than we did before, and it still works as we'd expect. I'd say, overall, this venture into TDD wasn't so bad. (Looking at the last snippet again, I could realistically see it condensed into a single method, but we've only got so much time, so we'll leave it as is.) Let's take one more look at our newly refactored Stutter code, to reminesce.
```java
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
```
Hope this was a satisfactory description of how we did our TDD. Have a good day!
