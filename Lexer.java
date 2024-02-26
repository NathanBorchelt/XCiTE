//CSC 415 Assignment 4
//Matthew Tennyson

import java.io.File;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Lexer
{
    private static final String[][] TOKENS =
    {
        {"INT", "\\bINT\\b", "INT keyword"},
        {"FLOAT", "\\bFLOAT\\b", "FLOAT keyword"},
        {"CHAR", "\\bCHAR\\b", "CHAR keyword"},
        {"STRING", "\\bSTRING\\b", "STRING keyword"},
        {"ARRAY", "\\bARRAY\\b", "ARRAY keyword"},
        {"WHILE", "\\bWHILE\\b", "WHILE keyword"},
        {"IF", "\\bIF\\b", "IF keyword"},
        {"ELSE", "\\bELSE\\b", "ELSE keyword"},
        {"TRUE", "\\bTRUE\\b", "TRUE keyword"},
        {"FALSE", "\\bFALSE\\b", "FALSE keyword"},
        {"NOT", "\\bNOT\\b", "NOT keyword"},
        {"AND", "\\bAND\\b", "AND keyword"},
        {"OR", "\\bOR\\b", "OR keyword"},
        {"PRINT", "\\bPRINT\\b", "PRINT keyword"},
        {"string", "\\\"[^\\\n\\\"]*\\\"", "String Literal"},
        {"character", "\\'[^\\']\\'", "Character Literal"},
        {"float", "(0|[1-9][0-9]*)\\.[0-9]+", "Float Literal"},
        {"integer", "0|[1-9][0-9]*", "Integer Literal"},
        {"identifier", "@[a-z_]+", "Identifier"},
        {"comment", "\\$[^\\\n]*\\\n", "Comment"},
        {"bracketopen", "\\[", "open square bracket"},
        {"bracketclose", "\\]", "close square bracket"},
        {"comma", "\\,", "comma"},
        {"exclamation", "\\!", "exclamation"},
        {"greaterequal", "\\>\\=", "greater than or equal to"},
        {"greater", "\\>", "greater than"},
        {"lessequal", "\\<\\=", "less than or equal to"},
        {"less", "\\<", "less than"},
        {"equalto", "\\=\\=", "is equal to"},
        {"assignment", "\\=", "assignment"},
        {"parenopen", "\\(", "open parenthesis"},
        {"parenclose", "\\)", "close parenthesis"},
        {"braceopen", "\\{", "open curly brace"},
        {"braceclose", "\\}", "close curly brace"},
        {"add", "\\+", "addition"},
        {"subtract", "\\-", "subtraction"},
        {"multiply", "\\*", "multiplication"},
        {"divide", "\\/", "division"},
        {"modulus", "\\%", "modulus"},
        {"error", "[^\\s]+", "error"}
    };

    private Scanner scanner;
    private Pattern pattern;

    public Lexer(String file)
    {
        String regex = "";
        for(int i=0; i<TOKENS.length; i++)
            regex += "(?<" + TOKENS[i][0] + ">" + TOKENS[i][1] + ")|";
        regex = regex.substring(0, regex.length()-1);

        try
        {
            scanner = new Scanner(new File(file));
            pattern = Pattern.compile(regex);
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
            System.exit(0);
        }
    }

    public Token lex()
    {
        String lexeme = scanner.findWithinHorizon(pattern, 0);

        if(lexeme!=null)
        {
            Matcher matcher = pattern.matcher(lexeme);
            matcher.matches();

            for(int i=0; i<TOKENS.length; i++)
                if(matcher.group(TOKENS[i][0])!=null)
                    return new Token(lexeme.trim(), TOKENS[i][2]);
        }

        return null;
    }

    public static void main(String[] args)
    {
        Lexer lex = new Lexer(args[0]);
        Token current = lex.lex();

        int count=0;
        while(current!=null)
        {
            System.out.println((++count) + ".\t" + current);
            current = lex.lex();
        }
    }
}
