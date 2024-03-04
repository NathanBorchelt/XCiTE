//CSC 415 Programming Languages
//Matthew Tennyson
//
// This file contains a (partial) implementation of a recursive-descent parser.
// The grammar in Extended BNF that the parser recognizes is defined as:
//
//     <program> -> <simple> { + <simple> }
//     <simple> -> a <color> [c] d
//     <color> -> red | green | blue | <simple>
//
// Note that the parser expects that all lexemes are separated by whitespace.
// Also note that implementation is not entirely complete. Not all potential
// syntax errors will be recognized. For example, an "end of file reached
// while parsing" error will not be properly recognized.

import java.util.Scanner;
import java.io.File;

public class Parser
{
    private Lexer scanner;
    private Token current;

    public Parser(String file)
    {
        try
        {
            scanner = new Lexer(file);
            current = scanner.lex();
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
            System.exit(0);
        }
    }

    public void match(String expected)
    {
        if(current.equals(expected))
        {
            System.out.println("Matched " + current);

            if(scanner.lex() != null)
                current = scanner.lex();
            else
            {
                System.out.println("Reached the end of file.");
                System.exit(0);
            }
        }
        else
        {
            System.out.println();
            System.out.println("ERROR!");
            System.out.println("Expected: " + expected);
            System.out.println("Encountered: " + current);
            System.exit(0);
        }
    }

    //<color> -> red | green | blue | <simple>
    void color()
    {
        System.out.println("Begin <color>");

        switch(current)
        {
            case "red":
                match("red");
                break;

            case "green":
                match("green");
                break;

            case "blue":
                match("blue");
                break;

            case "a":
                simple();
                break;

            default:
                System.out.println();
                System.out.println("ERROR!");
                System.out.println("Expected: red, green, or blue");
                System.out.println("Encountered: " + current);
                System.exit(0);
        }
    }

    //<simple> -> a <color> [c] d
    void simple()
    {
        System.out.println("Begin <simple>");

        match("a");
        color();
        if(current.equals("c"))
            match("c");
        match("d");
    }

    //<program> -> <simple> { + <simple> }
    void program()
    {
        System.out.println("Begin <program>");

        simple();
        while(current.equals("+"))
        {
            match("+");
            simple();
        }
    }

    public static void main(String[] args)
    {
        Parser p = new Parser(args[0]);
        p.program();
    }
}
