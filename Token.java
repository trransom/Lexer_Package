package lexer;

/**
 * Creates a 'Token' object
 * @author trran
 */
public final class Token {
    
     public String symbol;
     public SType type;
     public SType yo;
     public Integer line;
     public Integer column;
    
    public Token(String t, SType tk, int l, int c){
        this.symbol = t;
        this.type = tk;
        this.line = l;
        this.column = c;
    }
    
    public Token(String t){
        symbol = t;
    }
    
    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public SType getType() {
        return type;
    }

    public void setType(SType type) {
        this.type = type;
    }

    public Integer getLine() {
        return line;
    }

    public void setLine(Integer line) {
        this.line = line;
    }

    public Integer getColumn() {
        return column;
    }

    public void setColumn(Integer column) {
        this.column = column;
    }

}
