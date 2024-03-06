import java.util.Set;

public class Parser2 {

    private Lexer lexer;
    private Token current;

    //Set Declarations
    //From: https://stackoverflow.com/a/1128899
    private final Set<String> dataTypes = Set.of("INT", "FLOAT", "CHAR", "STRING");

    public Parser2(String file){
        try{
            lexer = new Lexer(file);
            current = lexer.lex();
        }

        catch (Exception e){
            System.out.println(e.getMessage());
            System.exit(0);
        }
    }

    public void match(String expected){
        if(current.getDescription().equals(expected)){
            System.out.println("Matched: "+ current);

            if(lexer.lex() != null) current = lexer.lex();

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

    public void begin(String rule){
        System.out.println("Begin: <" + rule + ">");
    }

    //<program> -> <stmt_list>
    public void program(){
        begin("program");

        stmt_list();
    }

    //<stmt_list> -> <stmt> { <stmt> }
    public void stmt_list(){
        stmt();

        while(true) stmt();
    }

    //<stmt> -> <declaration> | <loop> | <assignment> | <print_stmt> | <if_stmt>
    public void stmt(){

        if(dataTypes.contains(current.getLexeme())) declaration();
        else if(current.getLexeme().equals("WHILE")) loop();
        else if(current.getDescription().equals("Identifier")) assignment();
        else if(current.getLexeme().equals("PRINT")) print_stmt();
        else if(current.getLexeme().equals("IF")) if_stmt()
    }
}
