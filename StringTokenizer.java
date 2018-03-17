package lexer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Takes a string and returns an enumeration of tokens.
 *
 * @author trran
 */
public class StringTokenizer implements Iterator {

    String[] tk;
    private String[] del;
    public int column = 1;
    int current = 0;
    int ptr = 0;
    int i = 0;
    String hold = "";
    int z;
    public final ArrayList<Token> token = new ArrayList<>();
    

    public StringTokenizer(String s, Integer x) {
        z = x;
        this.del = s.split("");

        while (ptr <= del.length - 1) {
            if ("".equals(del[0])) {
                break;
            }
            
            //Test for whitespace
            if (" ".equals(del[current])) {
                current += 1;
                ptr += 1;
            } //Test for keyword, types, boolean,
            else if (Character.isLetter(del[current].charAt(0))) {
                while (Character.isLetter(del[ptr].charAt(0))) {
                    //During this process, if what you've found is a character, pass it into hold. Also, set ptr to the current index.
                    this.hold = hold + del[ptr];
                    ptr++;
                    if (ptr > del.length - 1) {
                        break;
                    }
                }
                resolve();
            } //Test for integer
            else if ((Character.isDigit(del[current].charAt(0)))) {
                while (Character.isDigit(del[ptr].charAt(0)) || (".".equals(del[ptr]))) {
                    this.hold = hold + del[ptr]; 
                    ptr++;
                    if (ptr > del.length - 1) {
                        break;
                    }
                }
                resolve();
            } //Test for char. TODO: Determine whether more than one character
            //can be between the apostrophes
            else if (("'".equals(del[current]))) {
                this.hold = hold + del[ptr];
                ptr++;
                while (!("'".equals(del[ptr]))) {
                    this.hold = hold + del[ptr]; 
                    ptr++;
                    if (ptr > del.length - 1) {
                        break;
                    }
                }
                this.hold = hold + del[ptr];
                ptr++;
                resolve();
            } //Test for string.
            else if (("\"".equals(del[current]))) {
                this.hold = hold + del[ptr];
                ptr++;
                while (!("\"".equals(del[ptr]))) {
                    this.hold = hold + del[ptr];
                    ptr++;
                    if (ptr > del.length - 1) {
                        break;
                    }
                }
                this.hold = hold + del[ptr];
                ptr++;
                resolve();
            } //Check for punctuation other than '->'
            else if (SType.isPunct(del[current])) {
                this.hold = hold + del[ptr];
                ptr++;
                resolve();
            } //Check for dOps
            else if (SType.isDop(del[current])) {
                this.hold = hold + del[current];
                if (current == del.length - 1) {
                    ptr++;
                    resolve();
                } else if ((del[ptr + 1].equals("=")) || (del[ptr + 1].equals("+"))
                        || (del[ptr + 1].equals("-")) || (del[ptr + 1].equals(">"))) {
                    ptr++;
                    this.hold = hold + del[ptr];
                    ptr++;
                    resolve();
                } else {
                    ptr++;
                    resolve();
                }
            } //Check for mulOps
            else if (SType.isMulOp(del[current])) {
                this.hold = hold + del[current];
                if (current == del.length - 1) {
                    ptr++;
                    resolve();
                } else if (del[ptr + 1].equals("=")) {
                    ptr++;
                    this.hold = hold + del[ptr];
                    ptr++;
                    resolve();
                } else {
                    ptr++;
                    resolve();
                }
            } //Check for uniOps and othOps
            else if ("&".equals(del[current]) || "|".equals(del[current])) {
                this.hold = hold + del[current];
                if (current == del.length - 1) {
                    ptr++;
                    resolve();
                } else if ("&".equals(del[current])) {
                    if (del[ptr + 1].equals("&")) {
                        ptr++;
                        this.hold = hold + del[ptr];
                        ptr++;
                        resolve();
                    } else {
                        ptr++;
                        resolve();
                    }
                } else if ("|".equals(del[current])) {
                    if (del[ptr + 1].equals("|")) {
                        ptr++;
                        this.hold = hold + del[ptr];
                        ptr++;
                        resolve();
                    } else {
                        ptr++;
                        resolve();
                    }
                }

            }

        }

    }
    
    private void resolve() {
        SType c = SType.ER;
                SType d = c.getType(hold);
                Integer m = 0 + column;
                Token t = new Token(hold, d, z, m);
                this.token.add(t);
                current = ptr;
                this.hold = "";
                column++;
                i++;
    }
    

    @Override
    public boolean hasNext() {
        return current < tk.length - 1;
    }

    @Override
    public String next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        } else {
            return tk[current + 1];
        }
    }

    public String toString(Token t) {
        if (null != t.type) {
            switch (t.type) {
                case ER:
                    return "Error< " + t.symbol + " >, row: " + t.getLine() + ", column: " + t.getColumn();
                case KW:
                    return "Keyword< " + t.symbol + " >, row: " + t.getLine() + ", column: " + t.getColumn();
                case TY:
                    return "Type Declaration< " + t.symbol + " >, row: " + t.getLine() + ", column: " + t.getColumn();
                case ID:
                    return "Object Identifier< " + t.symbol + " >, row: " + t.getLine() + ", column: " + t.getColumn();
                case CI:
                    return "Integer Constant< " + t.symbol + " >, row: " + t.getLine() + ", column: " + t.getColumn();
                case CF:
                    return "Float Constant< " + t.symbol + " >, row: " + t.getLine() + ", column: " + t.getColumn();
                case CB:
                    return "Boolean Operator< " + t.symbol + " >, row: " + t.getLine() + ", column: " + t.getColumn();
                case CC:
                    return "Character Constant< " + t.symbol + " >, row: " + t.getLine() + ", column: " + t.getColumn();
                case CS:
                    return "String Constant< " + t.symbol + " >, row: " + t.getLine() + ", column: " + t.getColumn();
                case OP:
                    return "Operator< " + t.symbol + " >, row: " + t.getLine() + ", column: " + t.getColumn();
                case PC:
                    return "Punctuation< " + t.symbol + " >, row: " + t.getLine() + ", column: " + t.getColumn();
                default:
                    break;
            }
        }
        //Should never print
        return "UNKNOWN";
    }

}
