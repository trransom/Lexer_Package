package lexer;

import java.util.regex.Pattern;


/**
 * Enumerates the types of tokens
 *
 * @author trran
 */
public enum SType {
    /*
    ER: error
    KW: keyword
    TY: type/type declaration
    ID: object identifier
    CI: integer constant
    CF: float constant
    CB: boolean constant
    CC: character constant
    CS: string constant
    OP: operator
    PC: punctuation
     */
    ER, KW, TY, ID, CI, CF, CB, CC, CS, OP, PC;

    @Override
    public String toString() {
        return getSTypeName(this);
    }
    //CHANGED FROM STATIC TO NON STATIC
    public String getSTypeName(SType s) {
        return stname[s.ordinal()];
    }

    public SType getType(String token) {
        if (isKeyword(token)) {
            return KW;
        }
        if (isType(token)) {
            return TY;
        }
        if (isBool(token)) {
            return CB;
        }
        if (isIdentifier(token)) {
            return ID;
        }
        if (isInt(token)) {
            return CI;
        }
        if (isFloat(token)) {
            return CF;
        }
        if (isChar(token)) {
            return CC;
        }
        if (isString(token)) {
            return CS;
        }
        if (isOperator(token)) {
            return OP;
        }
        if (isPunct(token)) {
            return PC;
        }
        return ER;
    }

    public boolean isKeyword(String token) {
        for (String s : keywords) {
            if (s.equals(token)) {
                return true;
            }
        }
        return false;
    }

    public boolean isType(String token) {
        for (String s : types) {
            if (s.equals(token)) {
                return true;
            }
        }
        return false;
    }

    public boolean isIdentifier(String token) {
        return idPattern.matcher(token).matches();
    }

    public boolean isInt(String token) {
        return ciPattern.matcher(token).matches();
    }

    public boolean isFloat(String token) {
        return cfPattern.matcher(token).matches();
    }

    public boolean isChar(String token) {
        return token.startsWith("'") && token.endsWith("'");
    }

    public boolean isBool(String token) {
        return token.equals("false") || token.equals("true");
    }

    public boolean isString(String token) {
        return token.startsWith("\"") && token.endsWith("\"");
    }

    public boolean isLiteral(String token) {
        return isInt(token) || isChar(token)
                || isBool(token) || isString(token); //                || isFloat(token)
                
    }

    public boolean isEquOp(String token) {
        for (String s : equOps) {
            if (s.equals(token)) {
                return true;
            }
        }
        return false;
    }

    public boolean isRelOp(String token) {
        for (String s : relOps) {
            if (s.equals(token)) {
                return true;
            }
        }
        return false;
    }

    public boolean isAddOp(String token) {
        for (String s : addOps) {
            if (s.equals(token)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isMulOp(String token) {
        for (String s : mulOps) {
            if (s.equals(token)) {
                return true;
            }
        }
        return false;
    }

    public boolean isLogOp(String token) {
        for (String s : logOps) {
            if (s.equals(token)) {
                return true;
            }
        }
        return false;
    }

    public boolean isOthOp(String token) {
        for (String s : othOps) {
            if (s.equals(token)) {
                return true;
            }
        }
        return false;
    }

    public boolean isUniOp(String token) {
        for (String s : uniOps) {
            if (s.equals(token)) {
                return true;
            }
        }
        return false;
    }

    public boolean isOperator(String token) {
        return (isEquOp(token) || isRelOp(token) || isAddOp(token)
                || isMulOp(token) || isLogOp(token) || isOthOp(token));
    }

    public static boolean isDop(String token) {
        for (String s : dops) {
            if (s.equals(token)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isPunct(String token) {
        for (String s : puncts) {
            if (s.equals(token)) {
                return true;
            }
        }
        return false;
    }
    //
    // private variables and constants
    //
    private final String[] stname = {
        "Error", "Keyword", "Type", "Identifier", "Integer Constant",
        "Float Constant", "Boolean Constant", "Character Constant",
        "String Constant", "Operator", "Punctuation"};
    private static final String[] keywords = {"do", "break", "continue","case", "enum", 
        "extern", "default", "for", "if", "goto", "else", "main",
        "return", "sizeof", "static", "struct", "switch", "while",};
    private final String[] types = {"bool", "char", "float", "int", "long", "unsigned", "void",};
    private final String idPatternRE = "[a-zA-Z][a-zA-Z0-9_]*";
    private final Pattern idPattern = Pattern.compile(idPatternRE);
    private final String ciPatternRE = "[0-9]+";
    private final Pattern ciPattern = Pattern.compile(ciPatternRE);
    private final String cfPatternRE = "[0-9]+[.][0-9]+";
    private final Pattern cfPattern = Pattern.compile(cfPatternRE);
    private final String[] equOps = {"==", "!="};
    private final String[] relOps = {">", "<", ">=", "<="};
    private final String[] addOps = {"+", "-"};
    private static final String[] mulOps = {"*", "/", "%"};
    private final String[] logOps = {"!", "&&", "||"};
    private final String[] othOps = {"=", "*", "&", "++", "--","*=","/=","%=","+=","-="};
    private final String[] uniOps = {"!", "&", "*", "-", "++", "--", "~"};
    private static final String[] dops = {"=", "<", ">", "!", "+", "-",};
    private static final String[] puncts = {",", ";", "[", "]",
        "{", "}", "(", ")", "#","?",":",".","->"};
}
