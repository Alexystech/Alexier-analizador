package com.equipo.interfaces;

import java.util.LinkedList;

public interface IUtilidad {
    String agregarEspacios(String textoArchivo);
    LinkedList<String>tokens(String editText);
    LinkedList<String>subLineTokens(String tokenText);
    LinkedList<String>lineasCodigo(String textoArchivo);
    LinkedList<Integer> keyList(String textoArchivo);
    boolean veredictoFinal(LinkedList<Integer>keyList);
    boolean evaluarLibreriaStdlib(LinkedList<Integer>keyList);
    boolean evaluarLibreriaStdio(LinkedList<Integer>keyList);
    boolean evaluarImprimir(LinkedList<Integer>keyList);
    boolean evaluarMetodoPrincipal(LinkedList<Integer>keyList);
    boolean evaluarReturn(LinkedList<Integer>keyList);
    boolean evaluarLlaveCierre(LinkedList<Integer>keyList);
    boolean evaluarLlaveApertura(LinkedList<Integer>keyList);
}
