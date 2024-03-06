import java.util.Set;

public class Parser2 {

    public static void main(String[] args) {
        Parser2 parser = new Parser2("test1.txt");
    }

    private Lexer lexer;
    private Token current;

    //Set Declarations
    //From: https://stackoverflow.com/a/1128899
    private final Set<String> dataTypes = Set.of("INT", "FLOAT", "CHAR", "STRING");

    private final Set<String> expr = Set.of("-","+");
    private final Set<String> term = Set.of("*","//","%" );


    public Parser2(String file){
        try{
            lexer = new Lexer(file);
            current = lexer.lex();
            program();
        }

        catch (Exception e){
            System.out.println(e.getMessage());
            System.exit(0);
        }
    }

    private void match(String expected){
        if(current.getDescription().equals(expected)){
            System.out.println("Matched: "+ current.getLexeme());
            current = lexer.lex();
            if(current != null);

            else{
                System.out.println("EOF");
                System.exit(0);
            }
        }

        else{
            System.out.println();
            System.out.println("ERROR!");
            System.out.println("Expected: " + expected);
            System.out.println("Encountered: " + current);
            System.exit(0);
        }

    }

    private boolean compare( String against){
        return current.getDescription().equals(against);
    }

    private void begin(String rule){
        //System.out.println(current);
        System.out.println("Begin: <" + rule + ">");
    }

    //<program> -> <stmt_list>
    private void program(){
        begin("program");

        stmt_list();
    }

    //<stmt_list> -> <stmt> { <stmt> }
    private void stmt_list(){
        begin("stmt_lst");
        stmt();

        while(current != null) stmt();
    }

    //<stmt> -> <declaration> | <loop> | <assignment> | <print_stmt> | <if_stmt>
    private void stmt(){
        begin("stmt");

        if(dataTypes.contains(current.getLexeme()) || compare("ARRAY")) declaration();
        else if(compare("WHILE")) loop();
        else if(compare("Identifier")) assignment();
        else if(compare("PRINT")) print_stmt();
        else if(compare("IF")) if_stmt();
    }

    //<declaration> -> (<var_decl> | <array_decl>) !
    private void declaration(){
        begin("declaration");
        if(dataTypes.contains(current.getLexeme())) var_decl();

        else array_decl();

        match("exclamation");
    }

    //<var_decl> -> <type_keyword> <identifier> [ = <arith_expr> ]
    private void var_decl(){
        begin("var_decl");
        type_keyword();

        identifier();

        if(compare("assignment")){
            match("assignment");

            arith_expr();
        }
    }

    //<type_keyword> -> INT | FLOAT | CHAR | STRING
    private void type_keyword(){
        begin("type_keyword");

        switch (current.getLexeme()) {
            case "INT":
                match("INT keyword");
                break;

            case "FLOAT":
                match("FLOAT keyword");
                break;

            case "CHAR":
                match("CHAR keyword");
                break;

            case "STRING":
                match("STRING keyword");
                break;

            default:
                break;
        }
    }

    private void arith_expr() {
        arith_term();
        while(expr.contains(current.getLexeme())){
            if(compare("addition")) match("addition");

            else if(compare("subtraction")) match("subtraction");

            arith_term();
        }
    }

    private void arith_term(){
        arith_factor();

        while(term.contains(current.getLexeme())){
            if(compare("multiplication")) match("multiplication");

            else if(compare("division")) match("division");

            else if(compare("modulus")) match("modulus");

            arith_factor();
        }


    }

    //<identifier> -> <lexer_regex>
    private void identifier() {
        begin("identifier");
        match("Identifier");
    }

    //<arith_expr> -> <arith_term> { (+|-) <arith_term> }
    private void array_decl() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'array_decl'");
    }

    private void loop() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'loop'");
    }

    private void assignment() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'assignment'");
    }

    private void print_stmt() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'print_stmt'");
    }

    private void if_stmt() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'if_stmt'");
    }
}
