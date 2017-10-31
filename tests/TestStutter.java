import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;
public class TestStutter {


    @Test
    public void testNormalBehavior() throws IOException {
        assertEquals("Not expected return","nibh ln 7, nostra ln 11",
                Stutter.stut("/home/charlie/School/Fall_2017/Software_Testing/HW/CPTR245HW5/tests/blah.txt"));
    }

    @Test
    public void testNewBehavior() throws IOException {
        assertEquals("Not expected return", "amet ln 1, tortor ln 2, metus ln 2",
                Stutter.stut("/home/charlie/School/Fall_2017/Software_Testing/HW/CPTR245HW5/tests/blah2.txt"));
    }

    @Test
    public void fileDoesNotExist() throws IOException {
        assertEquals("not expected return", "File junk.txt not found", Stutter.stut("junk.txt"));
    }

    @Test
    public void NoDupes() throws IOException {
        assertEquals("not expected return", "No Duplicates", Stutter.stut("/home/charlie/School/Fall_2017/Software_Testing/HW/CPTR245HW5/tests/blah3.txt"));
    }


    @Test
    public void unreadableFile() throws IOException {
        assertEquals("not expected return", "Error reading file: ", Stutter.stut("/home/charlie/School/Fall_2017/Software_Testing/HW/CPTR245HW5/tests/battery"));
    }
}
