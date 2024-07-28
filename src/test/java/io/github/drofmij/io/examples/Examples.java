package io.github.drofmij.io.examples;

import io.github.drofmij.io.Nibbler;

import java.io.IOException;

public class Examples {

    public static void main(String args[]) {

        // instantiates {@link Nibbler} in a try with resources block and implements {@link Nibbler.handleLine()} in an
        // inline anonymous descending class, then runs nibbler in the try block with {@link Nibbler.nibble()}
        try (Nibbler nibbler = new Nibbler("your-file-to-read") {
            @Override
            public void handleLine(String line) {
               // perform your custom action on the lines here.
            }
        }) {
            nibbler.nibble();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
