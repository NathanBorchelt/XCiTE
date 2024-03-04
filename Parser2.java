public class Parser2 {

    private Lexer lexer;
    private Token current;

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
        if(current.)
    }
}
