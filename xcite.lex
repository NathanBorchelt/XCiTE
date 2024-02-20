%option noyywrap

%{
#include <stdio.h>
%}

int     INT
bool    BOOL
float   FLOAT
char    CHAR
string  STRING
array   ARRAY
while   WHILE
if      IF
else    ELSE
true    TRUE
false   FALSE
not     NOT
and     AND
or      OR
print   PRINT

%%

{int} printf("%s:\tINT keyword", yytext);
{int} printf("%s:\tINT keyword", yytext);
{int} printf("%s:\tINT keyword", yytext);
{int} printf("%s:\tINT keyword", yytext);
{int} printf("%s:\tINT keyword", yytext);
{int} printf("%s:\tINT keyword", yytext);
{int} printf("%s:\tINT keyword", yytext);
{int} printf("%s:\tINT keyword", yytext);
{else} printf("%s:\tELSE keyword", yytext);
{true} printf("%s:\tTRUE keyword", yytext);
{false} printf("%s:\tFALSE keyword", yytext);
{not} printf("%s:\tNOT keyword", yytext);
{and} printf("%s:\tAND keyword", yytext);
{or} printf("%s:\tOR keyword", yytext);
{print} printf("%s:\tPRINT keyword", yytext);


{line}  printf("Matched line of text: %s", yytext);

%%

int main(int argc, char* argv[]){
    printf("Scanning %s...\n", argv[1]);

    extern FILE *yyin;
    yyin = fopen(argv[1], "r");
    yylex();

    return 0;
}
