import org.hamcrest.CoreMatchers;
import org.junit.Test;

import static org.junit.Assert.assertThat;

public class TestTest {

    @Test
    public void TestTestHi(){
        assertThat("Hello", CoreMatchers.containsString("Hello"));

    }

}
