package com.company;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        String expression;
        System.out.println("Enter an expression");
        expression = sc.nextLine();

        Tokenizer tokenizer = new Tokenizer(expression);
        ArrayList<Lexeme> result = tokenizer.getTokens();
		System.out.println("Tokens generated are..");
        for(int i=0 ; i<result.size();i++){
            System.out.println(String.format("Token[%s] = %s (%s).", i, result.get(i).token,result.get(i).type));
        }
        AssemblyGenerator assemblyGenerator = new AssemblyGenerator(result);
		System.out.println("Assembly Code generated is..");
        System.out.println(assemblyGenerator.generateAssemblyCode());


    }
}
