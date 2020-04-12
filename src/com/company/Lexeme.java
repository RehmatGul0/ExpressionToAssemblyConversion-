package com.company;

class Lexeme {
    Types type;
    String token;

    Lexeme(Types type, String token) {
        this.type = type;
        this.token = token;
    }
}