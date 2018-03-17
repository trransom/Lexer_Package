package lexer;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Takes a source file and breaks it into tokens.
 *
 * @author trran
 */
public class Lexer {

    private String filename;
    private BufferedReader reader;
    private String line;
    private Integer linecount;
    private StringTokenizer sTokenizer;

    public Lexer(String filename) throws IOException {
        this.filename = filename;
        if (!this.filename.endsWith(".c")) {
            this.filename += ".c";
        }
        reader = null;
        try {
            reader = new BufferedReader(new FileReader(filename));
            line = reader.readLine();
            linecount = 1;
            while (line != null) {
                sTokenizer = new StringTokenizer(line, linecount);
                int i = 0;
                while (i <= sTokenizer.token.size() - 1) {
                    System.out.println(sTokenizer.toString(sTokenizer.token.get(i)));
                    i++;
                }
                line = reader.readLine();
                linecount++;
            }
            reader.close();
        } catch (FileNotFoundException ex) {
            System.err.println(ex.getMessage());
            System.exit(1);
        }
    }

}
