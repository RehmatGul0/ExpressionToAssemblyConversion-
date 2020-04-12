package com.company;

import java.io.IOException;
import java.util.ArrayList;

public class AssemblyGenerator {
    private ArrayList<Lexeme> lexemes;
    private int index;

    AssemblyGenerator(ArrayList<Lexeme> lexemes) {
        this.lexemes = lexemes;
        index = 0;
    }

    String Expr() {
        String term = Term();
        String expBar = ExprBar();
        return term + expBar;
    }

    String ExprBar() {
        if(checkLexemeEnd())return "";
        if (lexemes.get(this.index).token.equals("+")) {
            this.index++;
            String term = Term();
            String expBar = ExprBar();
            String codeForAdd = "POP AX\nPOP BX\nADD AX,BX\nPUSH AX\n";
            return term + expBar + codeForAdd;
        } else if (lexemes.get(this.index).token.equals("-")) {
            this.index++;
            String term = Term();
            String expBar = ExprBar();
            String codeForSub = "POP AX\nPOP BX\nSUB AX,BX\nPUSH AX\n";
            return term + expBar + codeForSub;
        } else {
            return "";
        }
    }

    String Term() {
        String factor = Factor();
        String termBar = TermBar();
        return factor + termBar;
    }

    String TermBar() {
        if(checkLexemeEnd())return "";
        if (lexemes.get(this.index).token.equals("*")) {
            this.index++;
            String factor = Factor();
            String termBar = TermBar();
            String codeForMul = "POP AX\nPOP BX\nMUL BX\nPUSH AX\n";
            return factor + termBar + codeForMul;
        } else if (lexemes.get(this.index).token.equals("/")) {
            this.index++;
            String factor = Factor();
            String termBar = TermBar();
            String codeForDiv = "POP AX\nPOP BX\nMOV DX,0\nDIV BX\nPUSH AX\n";
            return factor + termBar + codeForDiv;
        }else if (lexemes.get(this.index).token.equals("%")) {
            this.index++;
            String factor = Factor();
            String termBar = TermBar();
            String codeForDiv = "POP AX\nPOP BX\nMOV DX,0\nDIV BX\nPUSH DX\n";
            return factor + termBar + codeForDiv;
        } else {
            return "";
        }
    }

    String Factor() {
        if(checkLexemeEnd())return "";
        if (lexemes.get(this.index).token.equals("(")) {
            this.index++;
            String expr = Expr();
            this.index++;
            return expr;
        } else {
            int number = Integer.parseInt(lexemes.get(this.index).token);
            this.index++;
            return "MOV AX," + number + "\nPUSH AX\n";
        }
    }

    String generateAssemblyCode() throws IOException {
        String assemblyCode="INCLUDE Irvine32.inc\n.code\nmain PROC\n";
        assemblyCode += Expr();
        assemblyCode+="call WriteDec\nexit\nmain ENDP\nEND main\n";
        FileWriter writer = new FileWriter(assemblyCode);
        writer.writeToFile();
        return assemblyCode;
    }

    boolean checkLexemeEnd(){
        return (lexemes.size()==this.index);
    }

}
