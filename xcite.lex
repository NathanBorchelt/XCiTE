%option noyywrap

%{
#include <stdio.h>
%}

int         "INT"
bool        "BOOL"
float       "FLOAT"
char        "CHAR"
string      "STRING"
array       "ARRAY"
while       "WHILE"
if          "IF"
else        "ELSE"
true        "TRUE"
false       "FALSE"
not         "NOT"
and         "AND"
or          "OR"
print       "PRINT"

strLit      "\""[^\"]*"\""
intLit      "0" | ["-"?][1-9][[:digit:]]*
floatLit    ["-"?]"0."[[:digit:]]+ - ["-"?][0{2,}]"."[0{2,}] | ["-"?][1-9][[:digit:]]*"."[[:digit:]]+
charLit     "'"."'"

osBracket       "["
csBracket       "]"
assignment      "="
comma           ","
exclamaiton     "!"
greaterThan     ">"
lessThan        "<"
gtEqual         ">="
ltEqual         "<="
isEqual         "=="
openParenthesis "("
closeParenthesis ")"
opencBrace      "{"
closecBrace     "}"
addition        "+"
subtraction     "-"
division        "/"
multiplication  "*"
modulus         "%"

other .

%%

{int} printf("%s:\tINT keyword", yytext);
{bool} printf("%s:\tBOOL keyword", yytext);
{float} printf("%s:\tFLOAT keyword", yytext);
{char} printf("%s:\tCHAR keyword", yytext);
{string} printf("%s:\tSTRING keyword", yytext);
{array} printf("%s:\tARRAY keyword", yytext);
{while} printf("%s:\tWHILE keyword", yytext);
{if} printf("%s:\tIF keyword", yytext);
{else} printf("%s:\tELSE keyword", yytext);
{true} printf("%s:\tTRUE keyword", yytext);
{false} printf("%s:\tFALSE keyword", yytext);
{not} printf("%s:\tNOT keyword", yytext);
{and} printf("%s:\tAND keyword", yytext);
{or} printf("%s:\tOR keyword", yytext);
{print} printf("%s:\tPRINT keyword", yytext);


{line}  printf("Matched line of text: %s", yytext);

{other} printf("%s:\tError", yytext);

%%

int main(int argc, char* argv[]){
    printf("Scanning %s...\n", argv[1]);

    extern FILE *yyin;
    yyin = fopen(argv[1], "r");
    yylex();

    return 0;
}
