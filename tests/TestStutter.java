import org.junit.Test;
import static org.junit.Assert.*;
public class TestStutter {


    @Test
    public void testNormalBehavior() {
        assertEquals("Not expected return","nibh ln 7, nostra ln 11",
                Stutter.stut("blah.txt"));
    }

    @Test
    public void testNewBehavior() {
        assertEquals("Not expected return", "amet ln 1, tortor ln 2", Stutter.stut("blah2.txt"));
    }
}
