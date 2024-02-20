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
print   PRIN

%%

{int} printf("%s:\tINT keyword", yytext);
{int} printf("%s:\tINT keyword", yytext);
{int} printf("%s:\tINT keyword", yytext);
{int} printf("%s:\tINT keyword", yytext);
{int} printf("%s:\tINT keyword", yytext);
{int} printf("%s:\tINT keyword", yytext);
{int} printf("%s:\tINT keyword", yytext);
{int} printf("%s:\tINT keyword", yytext);
{int} printf("%s:\tINT keyword", yytext);
{int} printf("%s:\tINT keyword", yytext);
{int} printf("%s:\tINT keyword", yytext);
{int} printf("%s:\tINT keyword", yytext);
{int} printf("%s:\tINT keyword", yytext);
{int} printf("%s:\tINT keyword", yytext);
{int} printf("%s:\tINT keyword", yytext);


{line}  printf("Matched line of text: %s", yytext);

%%

int main(int argc, char* argv[]){
    printf("Scanning %s...\n", argv[1]);

    extern FILE *yyin;
    yyin = fopen(argv[1], "r");
    yylex();

    return 0;
}
