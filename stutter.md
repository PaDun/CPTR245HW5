# Homework 5
### CPTR 245
### Patrick Dunphy & Charles Lambert

Our initial happy path tests sets up the main funcitonality of the program.
Which in this case is to read a file and check for repeated words.
Once repeated words are found it returns the words and lines.

```java
  @Test
    public void testNormalBehavior() throws IOException {
        assertEquals("Not expected return","nibh ln 7, nostra ln 11, ",
                Stutter.stut("/home/charlie/School/Fall_2017/Software_Testing/HW/CPTR245HW5/tests/blah.txt"));
    }
```
