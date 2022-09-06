import net.clouza.mcq.env.Configuration;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Clouza (Siwa)
 * @link https://github.com/clouza
 */
class countDownTest {
    String[] samples = {"A", "B", "C", "D", "E", "F", "", ""}; // 8
    int start = 2;

    @Test
    void countDown() {
        for (int i = start; i < samples.length; i++) {
            if(!samples[i].equals("")) {
                System.out.println(samples[i]);
            }
        }
    }

    @Test
    void lastSampleIndex() {
        assertEquals("f", samples[samples.length - 3].toLowerCase());
    }
}
