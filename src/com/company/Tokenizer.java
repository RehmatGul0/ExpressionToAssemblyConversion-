package com.company;

import java.util.ArrayList;

public class Tokenizer {
    private char[] expression;
    private ArrayList<Lexeme> lexemes;
    Tokenizer(String expression) {
        this.expression = expression.toCharArray();
        this.lexemes= new ArrayList<Lexeme>();
    }
    ArrayList<Lexeme> getTokens() {
        for(int i=0 ; i<this.expression.length ; i++){
            if(this.expression[i]=='+'||this.expression[i]=='-'||this.expression[i]=='*'
                    ||this.expression[i]=='/'||this.expression[i]==')'||this.expression[i]=='('){
                lexemes.add(new Lexeme(Types.OPERATOR,Character.toString(this.expression[i])));
            }
            else if(checkDigit(this.expression[i])){
                StringBuilder token = new StringBuilder();
                do{
                    token.append(this.expression[i]);
                    i++;
                }while(i<expression.length&&checkDigit(this.expression[i]));
                i--;
                lexemes.add(new Lexeme(Types.NUMBER,token.toString()));
            }
        }
        return this.lexemes;
    }
    boolean checkDigit(char toCheck){
        return Character.isDigit(toCheck);
    }
}