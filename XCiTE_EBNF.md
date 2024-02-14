# XC!TE EBNF

```bnf
<program> -> <stmt_list>
<stmt_list> -> <stmt> { <stmt> }
<stmt> -> <delaration> | <loop> | <assignment> | <print_stmt> | <if_stmt>

<print_stmt> -> PRINT \( <expr> \)!
<assignment> -> <l_value> = <expr>!
<loop> -> WHILE \( <cond_expr> \) \{ <stmt_list> \}
<if_stmt> -> IF \( <cond_expr> \) \{ <stmt_list> \} [ELSE \{ <stmt_list> \}]
<declaration> -> <var_decl> | <array_decl>

<var_decl> -> <type_keyword> <identifier> [ = <expr> ] !

```
