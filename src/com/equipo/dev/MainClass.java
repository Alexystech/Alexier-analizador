package com.equipo.dev;

import java.util.LinkedList;

public class MainClass {
    public static void main(String[] args) {
        CustomFile myCustomFile = new CustomFile("ArchivoTexto.txt");
        Utilidad myUtilities = new Utilidad();

        String textFile = myCustomFile.read();
        String editText = myUtilities.agregarEspacios(textFile);

        LinkedList<String> tokens = myUtilities.tokens(editText);

        System.out.println(editText);
        System.out.println("------------------------------");
        String tokensTemporal = "";
        for (String token : tokens) {
            System.out.println(token);
            tokensTemporal += token;
            tokensTemporal += "\n";
        }

    }
}
