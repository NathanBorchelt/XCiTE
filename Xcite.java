//CSC 415: Assignment 3
//Nathan Borchelt and Mark Payne
public class Xcite {

    public static void main(String[] args)
    {
        Lexer lex = new Lexer(args[0]);
        Token current;
        while((current = lex.lex())!=null)
        {
            System.out.println(current);
        }
    }

}
