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
intLit     [-]?([1-9][0-9]*|0)
floatLit    [+-]?([1-9][0-9]*|0)(\.[0-9]*[1-9])?|0\.0
charLit     '\''.'\''

identifier "@"[a-z_]+
comment "$"[[:alnum:]]*

osBracket       "["
csBracket       "]"
assignment      "="
comma           ","
exclamation     "!"
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

space           [[:blank:]]*

%%

{int} printf("%s:\tINT keyword\n", yytext);
{bool} printf("%s:\tBOOL keyword\n", yytext);
{float} printf("%s:\tFLOAT keyword\n", yytext);
{char} printf("%s:\tCHAR keyword\n", yytext);
{string} printf("%s:\tSTRING keyword\n", yytext);
{array} printf("%s:\tARRAY keyword\n", yytext);
{while} printf("%s:\tWHILE keyword\n", yytext);
{if} printf("%s:\tIF keyword\n", yytext);
{else} printf("%s:\tELSE keyword\n", yytext);
{true} printf("%s:\tTRUE keyword\n", yytext);
{false} printf("%s:\tFALSE keyword\n", yytext);
{not} printf("%s:\tNOT keyword\n", yytext);
{and} printf("%s:\tAND keyword\n", yytext);
{or} printf("%s:\tOR keyword\n", yytext);
{print} printf("%s:\tPRINT keyword\n", yytext);

{strLit} printf("%s:\tString Literal\n", yytext);
{intLit} printf("%s:\tInteger Literal\n", yytext);
{floatLit} printf("%s:\tFloat Literal\n", yytext);
{charLit} printf("%s:\tCharacter Literal\n", yytext);

{identifier} printf("%s:\tIdentifier\n", yytext);

{comment} printf("%s:\tComment\n", yytext);

{osBracket} printf("%s:\tOpen Square Bracket\n", yytext);
{csBracket} printf("%s:\tClose Square Bracket\n", yytext);
{assignment} printf("%s:\tAssignment\n", yytext);
{comma} printf("%s:\tComma\n", yytext);
{exclamation} printf("%s:\tExclamation\n", yytext);
{greaterThan} printf("%s:\tGreater Than\n", yytext);
{lessThan} printf("%s:\tLess Than\n", yytext);
{gtEqual} printf("%s:\tGreater Than or Equal To\n", yytext);
{ltEqual} printf("%s:\tLess Than or Equal To\n", yytext);
{isEqual} printf("%s:\tIs Equal To\n", yytext);
{openParenthesis} printf("%s:\tOpen Parenthesis\n", yytext);
{closeParenthesis} printf("%s:\tClose Parenthesis\n", yytext);
{opencBrace} printf("%s:\tOpen Curly Brace\n", yytext);
{closecBrace} printf("%s:\tClose Curly Brace\n", yytext);
{addition} printf("%s:\tAddition\n", yytext);
{subtraction} printf("%s:\tSubtraction\n", yytext);
{division} printf("%s:\tDivision\n", yytext);
{multiplication} printf("%s:\tMultiplication\n", yytext);
{modulus} printf("%s:\tModulus\n", yytext);

{space} printf("");

. printf("%s:\tError\n", yytext);

%%

int main(int argc, char* argv[]){
    printf("Scanning %s...\n", argv[1]);

    extern FILE *yyin;
    yyin = fopen(argv[1], "r");
    yylex();

    printf("Done Scanning %s...\n", argv[1]);
    
    return 0;
}
