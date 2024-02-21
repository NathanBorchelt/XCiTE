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
floatLit    [+-]?([1-9][0-9]*|0)(\.[0-9]*[1-9])? | 0\.0
charLit     '\''.'\''

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

{strLit} printf("%s:\tString Literal", yytext);
{intLit} printf("%s:\tInteger Literal", yytext);
{floatLit} printf("%s:\tFloat Literal", yytext);
{charLit} printf("%s:\tCharacter Literal", yytext);

{osBracket} printf("%s:\tOpen Square Bracket", yytext);
{csBracket} printf("%s:\tClose Square Bracket", yytext);
{assignment} printf("%s:\tAssignment", yytext);
{comma} printf("%s:\tComma", yytext);
{exclamation} printf("%s:\tExclamation", yytext);
{greaterThan} printf("%s:\tGreater Than", yytext);
{lessThan} printf("%s:\tLess Than", yytext);
{gtEqual} printf("%s:\tGreater Than or Equal To", yytext);
{ltEqual} printf("%s:\tLess Than or Equal To", yytext);
{isEqual} printf("%s:\tIs Equal To", yytext);
{openParenthesis} printf("%s:\tOpen Parenthesis", yytext);
{closeParenthesis} printf("%s:\tClose Parenthesis", yytext);
{opencBrace} printf("%s:\tOpen Curly Brace", yytext);
{closecBrace} printf("%s:\tClose Curly Brace", yytext);
{addition} printf("%s:\tAddition", yytext);
{subtraction} printf("%s:\tSubtraction", yytext);
{division} printf("%s:\tDivision", yytext);
{multiplication} printf("%s:\tMultiplication", yytext);
{modulus} printf("%s:\tModulus", yytext);

. printf("%s:\tError", yytext);

%%

int main(int argc, char* argv[]){
    printf("Scanning %s...\n", argv[1]);

    extern FILE *yyin;
    yyin = fopen(argv[1], "r");
    yylex();

    return 0;
}
