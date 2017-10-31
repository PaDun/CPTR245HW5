# Homework 5
### CPTR 245
### Patrick Dunphy & Charles Lambert

Our initial happy path tests sets up the main funcitonality of the program.
Which in this case is to read a file and check for repeated words.
Once repeated words are found it returns the words and lines.

```java
  @Test
    public void testNormalBehavior() throws IOException {
        assertEquals("Not expected return","nibh ln 7, nostra ln 11, ",Stutter.stut("/home/charlie/School/Fall_2017/Software_Testing/HW/CPTR245HW5/tests/blah.txt"));
    }
```

As one would expect we get an error because the implementation does not exist.
```java
public class Stutter {
}
```

So lets make a basic implementation that fakes the result.
```java
public class Stutter {
    public Stutter() {
    }
    public static String stut (String filePath){

        return "nibh ln 7, nostra ln 11";
    }
}
```

Now obviously this implementation does not capture the real functionality we are looking for, so lets make another test.
```java
 @Test
    public void testNewBehavior() {
        assertEquals("Not expected return", "amet ln 1, tortor ln 2", Stutter.stut("blah2.txt"));
    }
```

Oh we have broken the function tests again so lets make a real implementation.
First we need to open a file and read from it. In java we use a FileReader and a BufferReader.
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

So now we can open the file and read a line but our test will still fail so lets parse the line.
To do so we will use the string split method and a regular expression.
```java
public static String stut(String filePath) throws IOException {
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
In this case split takes a string and splits it based on a delimiter, which we used a regular expression to catch all non word groupings.
And unfortunatly our tests still don't pass. Now we need check for duplicates in the line.

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
        return output;
    }
```
And finally our happy path test is functional, but we missed a few things along the way.

