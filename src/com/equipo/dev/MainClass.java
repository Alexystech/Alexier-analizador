package com.equipo.dev;

import javax.swing.*;
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

        System.out.println("*--------------------------------------*");
        LinkedList<String>lineasCodigo = myUtilities.lineasCodigo(textFile);
        LinkedList<Integer>keyList = new LinkedList<>();
        boolean tieneError = false;
        int contLineas = 0;

        for (String linea : lineasCodigo) {
            contLineas++;
            System.out.println(contLineas+" | "+linea);
            keyList = myUtilities.keyList(linea);

            tieneError = myUtilities.veredictoFinal(keyList);

            if (tieneError) {
                JOptionPane.showMessageDialog(null,"error en la linea "+
                        contLineas);
            }
        }

        System.out.println("+-------------------------------------------+");
        for (int x : keyList) {
            System.out.println(x);
        }

    }
}
