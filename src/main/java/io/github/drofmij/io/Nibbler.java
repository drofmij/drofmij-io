package io.github.drofmij.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.Closeable;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Nibbler handles reading a file or input stream with a buffered reader one
 * line at a time
 *
 * @author drofmij
 */
public abstract class Nibbler implements Closeable {

    /**
     * name of the file to nibble
     */
    private String filename;

    /**
     * input stream to read from
     */
    private InputStream inputstream;

    /**
     * default constructor
     */
    public Nibbler() {
    }

    /**
     * constructor, sets file name to read from
     *
     * @param filename the string of the file name we will be reading from
     */
    public Nibbler(String filename) {
        this.filename = filename;
    }

    /**
     * constructor, sets inputstream to read from
     *
     * @param inputstream input stream object
     */
    public Nibbler(InputStream inputstream) {
        this.inputstream = inputstream;
    }

    /**
     * convenience method for setting input stream
     *
     * @param inputstream the input stream object to read
     * @return this Nibbler object is returned.
     */
    public Nibbler withInputStream(InputStream inputstream) {
        this.inputstream = inputstream;
        return this;
    }

    /**
     * convenience method for setting filename
     *
     * @param filename the string of the file name we will be reading from
     * @return this Nibbler object is returned.
     */
    public Nibbler withFilename(String filename) {
        this.filename = filename;
        return this;
    }

    /**
     * read file line by line, if filename is not null, create FileInputStream to read the file, if inputstream is not
     * null, read directly from it
     *
     * @throws IOException throws IO exception if we run into an issue
     */
    public void nibble() throws IOException {
        if (filename != null) {
            File file = new File(filename);
            if (file.exists()) {
                nibble(new FileInputStream(file));
            }
        } else if (inputstream != null) {
            nibble(inputstream);
        } else {
            throw new IOException("Error: No filename or inputstream is set");
        }
    }

    /**
     * read the given InputStreamReader via a BufferedReader, each line will be passed to handleLine() as implemented in
     * the desecending class.
     *
     * @param inputStream the input stream object to read
     * @throws IOException throws IO exception if we run into an issue
     */
    private void nibble(InputStream inputStream) throws IOException {
        try (InputStreamReader isr = new InputStreamReader(inputStream);
             BufferedReader br = new BufferedReader(isr);) {
            String line;
            while ((line = br.readLine()) != null) {
                handleLine(line);
            }
        }
    }

    /**
     * override close(), closes the input stream
     *
     * @throws IOException throws IO exception if we run into an issue
     */
    @Override
    public void close() throws IOException {
        if (inputstream != null) {
            inputstream.close();
        }
    }

    /**
     * descending class will implement this method and handle each line as appropriate, can be anonymously implemented
     * if appropriate.
     *
     * @param line string value of the line to handle.
     */
    public abstract void handleLine(String line);
}
