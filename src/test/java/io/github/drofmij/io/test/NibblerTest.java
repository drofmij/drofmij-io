package io.github.drofmij.io.test;

import io.github.drofmij.io.IOTools;
import io.github.drofmij.io.Nibbler;
import org.junit.jupiter.api.Test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * simple test for Nibbler class.
 *
 * @author drofmij
 */
public class NibblerTest {

    /**
     * test {@link Nibbler} by writing 2 lines to a temporary file, then read them back out with Nibbler.
     */
    @Test
    public void testNibbler() {
        String tempFile = "/tmp/nibbler_test_file" + System.currentTimeMillis();
        String line1 = "test line 1";
        String line2 = "test line 2";
        List<String> lines = new ArrayList<>();
        File testFile = new File(tempFile);
        try(BufferedWriter bw = IOTools.bwrite(tempFile);) {
            bw.append(line1 + "\n");
            bw.append(line2 + "\n");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // instantiates Nibbler in a try with resources block and implements handleLine() in an anonymous descendant
        // class, then runs nibbler in the try block with nibbler.nibble().
        try (Nibbler nibbler = new Nibbler(testFile.getAbsolutePath()) {
            @Override
            public void handleLine(String line) {
                lines.add(line);
            }
        }) {
            nibbler.nibble();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        assertTrue(line1.equals(lines.get(0)));
        assertTrue(line2.equals(lines.get(1)));
        testFile.delete();
    }
}
