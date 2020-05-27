package com.equipo.interfaces;

import java.util.LinkedList;

public interface IUtilidad {
    String agregarEspacios(String textoArchivo);
    LinkedList<String>tokens(String editText);
    LinkedList<String>subLineTokens(String tokenText);
    LinkedList<String>lineasCodigo(String textoArchivo);
    LinkedList<Integer> keyList(String textoArchivo);
}
