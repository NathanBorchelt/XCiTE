public class Test {
    public static void main(String[] args) {
        Lexer lexer = new Lexer("test1.txt");
        Token token = lexer.lex();

        while(token != null){
            System.out.println(token);
            token = lexer.lex();
        }
    }
}
