# XC!TE EBNF

```bnf
<program> ::= <stmt_list>
<stmt_list> ::= <stmt> { <stmt> }
<stmt> ::= <delaration> | <loop> | <assignment> | <print_stmt> | <if_stmt>

<print_stmt> ::= PRINT \( <expr> \) <terminal>
<assignment> ::= <l_value> = <expr> <terminal>
<loop> ::= WHILE \( <cond_expr> \) \{ <stmt_list> \}
<if_stmt> ::= IF \( <cond_expr> \) \{ <stmt_list> \} [ELSE \{ <stmt_list> \}]
<declaration> ::= <var_decl> | <array_decl>
<terminal> ::= !{!}


<var_decl> ::= <type_keyword> <identifier> [ = <expr> ] <terminal>
<array_decl> ::= ARRAY <type_keyword> <identifier> [ = <array> ]<terminal>

<array> ::= \[ ([<array_item>] | <array_item>{, <array_item>}) \]
<array_item> ::= {( <identifier> | <lit>)}

<type_keyword> ::= "INT" | "BOOL" | "FLOAT" | "CHAR" | "STRING"
<identifier> ::= @<ident_char>{<ident_char>}
<ident_char> ::= "_" | "[a-z]"

<expr> ::= <term> { ( + | \- ) <term> } | <string> | <array>
<term> ::= <number> { ( * | / ) <number> }
<number> ::= <int_num> | <float_num>

<int_num> ::=  "0" | "[\-][1-9]{[0-9]}"
<float_num> ::= "[\-]0.'[0-9]'{['0-9]'}" - "[\-]0.0{0}" | "[\-]'[1-9]'{'[0-9]'}.[0-9]{[0-9]}"

<cond_expr> ::= NOT \(<cond_expr>\) |  <expr> <logic_comp> <expr> { ( AND | OR ) <cond_expr>} | <bool>
<logic_comp> ::= "==" | "<=" | ">=" | ">" | "<"
<bool> ::= TRUE | FALSE

<lit> ::= <number> | <string> | <array> | <char> | <bool>
<string> ::= "\"{'[ -~]'}\""
<char> ::=" \'{'[ -~]'}\'"

```
