//CSC 415 Programming Languages - Assignment 4
// Nathan Borchelt, Mark Payne, and John Irvin

import java.util.Set;

public class Parser2 {

    public static void main(String[] args) {
        Parser2 parser = new Parser2("test1.txt");
        //Parser2 parser = new Parser2(args[0]);
    }

    private Lexer lexer;
    private Token current;

    // Set Declarations
    // From: https://stackoverflow.com/a/1128899
    private final Set<String> dataTypes = Set.of("INT keyword", "FLOAT keyword", "CHAR keyword", "STRING keyword");

    private final Set<String> expr = Set.of("subtraction", "addition");
    private final Set<String> term = Set.of("multiplication", "division", "modulus");

    public Parser2(String file) {
        try {
            lexer = new Lexer(file);
            current = lexer.lex();
            program();
        }

        catch (Exception e) {
            System.out.println(e.getMessage());
            System.exit(0);
        }
    }

    private void match(String expected) {

        if (current == null && expected.equals("EOF")) {
            System.out.println("<EOF>");
            System.exit(0);
        } else if (compare(expected)) {
            System.out.println("Matched: " + current.getLexeme());
            current = lexer.lex();
            if (current != null)
                ;
        }

        else {
            error(expected);
        }

    }

    private void error(String... expected) {
        System.err.println();
        System.err.println("ERROR!");
        System.err.print("Expected: ");
        for (int i = 0; i < expected.length - 1; i++) {
            System.err.print(expected[i] + ", ");
        }
        System.err.println(expected[expected.length - 1]);
        System.err.println("Encountered: " + current);
        System.exit(0);
    }

    private boolean compare(String... against) {
        for (String string : against) {
            if (current.getDescription().equals(string))
                return true;
        }
        return false;
    }

    private void begin(String rule) {
        // System.out.println(current);
        System.out.println("Begin: <" + rule + ">");
    }

    // <program> -> <stmt_list>
    private void program() {
        begin("program");

        stmt_list();

        match("EOF");
    }

    // <stmt_list> -> <stmt> { <stmt> }
    private void stmt_list() {
        begin("stmt_lst");
        stmt();

        while (current != null
                && compare("INT keyword", "FLOAT keyword", "CHAR keyword", "STRING keyword", "ARRAY keyword",
                        "WHILE keyword",
                        "Identifier", "PRINT keyword", "IF keyword", "Comment"))
            stmt();
    }

    // <stmt> -> <declaration> | <loop> | <assignment> | <print_stmt> | <if_stmt> |
    // ?<comment>?
    private void stmt() {
        begin("stmt");

        if (dataTypes.contains(current.getDescription()) || compare("ARRAY keyword"))
            declaration();
        else if (compare("WHILE keyword"))
            loop();
        else if (compare("Identifier"))
            assignment();
        else if (compare("PRINT keyword"))
            print_stmt();
        else if (compare("IF keyword"))
            if_stmt();
        else if (compare("Comment")) {
            System.out.println("Comment: " + current.getLexeme());
            current = lexer.lex();
        }

        else
            error("INT keyword", "FLOAT keyword", "CHAR keyword", "STRING keyword", "ARRAY keyword", "WHILE keyword",
                    "Identifier", "PRINT keyword", "IF keyword");
    }

    // <declaration> -> (<var_decl> | <array_decl>) !
    private void declaration() {
        begin("declaration");
        if (dataTypes.contains(current.getDescription()))
            var_decl();

        else
            array_decl();

        match("exclamation");
    }

    // <var_decl> -> <type_keyword> <identifier> [ = <arith_expr> ]
    private void var_decl() {
        begin("var_decl");
        type_keyword();

        identifier();

        if (compare("assignment")) {
            match("assignment");

            arith_expr();
        }
    }

    // <type_keyword> -> INT | FLOAT | CHAR | STRING
    private void type_keyword() {
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

    // <arith_expr> -> <arith_term> { (+|-) <arith_term> }
    private void arith_expr() {
        begin("arith_expr");
        arith_term();
        while (expr.contains(current.getDescription())) {
            if (compare("addition"))
                match("addition");

            else if (compare("subtraction"))
                match("subtraction");

            arith_term();
        }
    }

    // <arith_term> -> <arith_factor> { (*|/|%) <arith_factor> }
    private void arith_term() {
        begin("arith_term");
        arith_factor();

        while (term.contains(current.getDescription())) {
            if (compare("multiplication"))
                match("multiplication");

            else if (compare("division"))
                match("division");

            else if (compare("modulus"))
                match("modulus");

            arith_factor();
        }

    }

    // <arith_factor> -> [ \- ] ( \( <arith_expr> \) | <l_value> | <literal> )
    private void arith_factor() {
        begin("arith_factor");
        if (compare("subtraction"))
            match("subtraction");

        if (compare("open parenthesis")) {
            match("open parenthesis");
            arith_expr();
            match("close parenthesis");
        } else if (compare("Identifier"))
            l_value();
        else if (current.getDescription().contains("Literal"))
            literal();
        else
            error("open parenthesis", "Identifier", "Literal");
    }

    // <l_value> -> <identifier> [ \[ <identifier> | <int_literal> \] ]
    private void l_value() {
        begin("l_value");
        identifier();
        if (compare("open square bracket")) {
            match("open square bracket");
            if (compare("Identifier"))
                identifier();
            else
                int_literal();
            match("close square bracket");

        }
    }

    // <int_literal> -> <lexer_regex>
    private void int_literal() {
        begin("int_literal");
        match("Integer Literal");
    }

    // <literal> -> <string_literal> | <char_literal> | <int_literal> |
    // <float_literal>

    private void literal() {
        begin("literal");
        if (compare("String Literal"))
            string_literal();
        else if (compare("Character Literal"))
            char_literal();
        else if (compare("Integer Literal"))
            int_literal();
        else if (compare("Float Literal"))
            float_literal();

    }

    private void string_literal() {
        begin("String Literal");
        match("String Literal");
    }

    private void char_literal() {
        begin("Character Literal");
        match("Character Literal");
    }

    private void float_literal() {
        begin("Float Literal");
        match("Float Literal");
    }

    // <identifier> -> <lexer_regex>
    private void identifier() {
        begin("identifier");
        match("Identifier");
    }

    // <array_decl> -> ARRAY <type_keyword> <identifier> = \[ <literal_list> \]
    private void array_decl() {
        begin("array_decl");
        match("ARRAY keyword");
        type_keyword();
        identifier();
        match("assignment");
        match("open square bracket");
        literal_list();
        match("close square bracket");
    }

    // <literal_list> -> <literal> { \, <literal> }
    private void literal_list() {
        begin("literal_list");
        literal();
        while (compare("comma")) {
            match("comma");
            literal();
        }
    }

    // <loop> -> WHILE \( <cond_expr> \) \{ <stmt_list> \}
    private void loop() {
        begin("loop");
        match("WHILE keyword");
        match("open parenthesis");
        cond_expr();
        match("close parenthesis");
        match("open curly brace");
        stmt_list();
        match("close curly brace");
    }

    // <cond_expr> -> <cond_term> { OR <cond_term> }
    private void cond_expr() {
        begin("cond_expr");
        cond_term();
        while (compare("OR keyword")) {
            match("OR keyword");
            cond_term();
        }
    }

    // <cond_term> -> <cond_factor> { AND <cond_factor> }
    private void cond_term() {
        begin("cond_term");
        cond_factor();
        while (compare("AND keyword")) {
            match("AND keyword");
            cond_factor();
        }
    }

    // <cond_factor> -> [ NOT ] ( \( <cond_expr> \) | TRUE | FALSE | <comparison> )
    private void cond_factor() {
        begin("cond_factor");
        if (compare("NOT keyword")) {
            match("NOT keyword");
        }

        if (compare("open parenthesis")) {
            match("open parenthesis");
            cond_expr();
            match("close parenthesis");
        } else if (compare("TRUE keyword"))
            match("TRUE keyword");
        else if (compare("FALSE keyword"))
            match("FALSE keyword");
        else
            comparison();

    }

    // <comparison> -> <arith_expr> (>|<|>=|<=|==) <arith_expr>
    private void comparison() {
        begin("comparison");
        arith_expr();

        if (compare("greater than"))
            match("greater than");
        else if (compare("greater than or equal to"))
            match("greater than or equal to");
        else if (compare("is equal to"))
            match("is equal to");
        else if (compare("less than or equal to"))
            match("less than or equal to");
        else if (compare("less than"))
            match("less than");
        else
            error("greater than or equal to", "greater than", "less than or equal to",
                    "less than", "is equal to");

        arith_expr();

    }

    // <assignment> -> <l_value> = <arith_expr> !
    private void assignment() {
        begin("assignment");
        l_value();
        match("assignment");
        arith_expr();
    }

    // <print_stmt> -> PRINT \( <arith_expr> \) !
    private void print_stmt() {
        begin("print_stmt");
        match("PRINT keyword");
        match("open parenthesis");
        arith_expr();
        match("close parenthesis");
        match("exclamation");
    }

    // IF \( <cond_expr> \) \{ <stmt_list> \} [ ELSE \{ <stmt_list> \} ]
    private void if_stmt() {
        begin("if_stmt");
        match("IF keyword");
        match("open parenthesis");
        cond_expr();
        match("close parenthesis");
        match("open curly brace");
        stmt_list();
        match("close curly brace");
        if (compare("ELSE keyword")) {
            match("ELSE keyword");
            match("open curly brace");
            stmt_list();
            match("close curly brace");
        }
    }
}
