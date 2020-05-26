package com.equipo.dev;

import java.util.LinkedList;

public class Utilidad {

    public Utilidad(){}

    /**
     * esta funcion me sirve para agregar
     * espacios a un String y retornar el
     * texto modificado
     * */
    public String agregarEspacios(String textoArchivo) {
        String textEdit = "";
        for (int x = 0; x < textoArchivo.length(); x++) {
            if (textoArchivo.charAt(x) == '(' || textoArchivo.charAt(x) == ')' ||
                    textoArchivo.charAt(x) == '+' || textoArchivo.charAt(x) == '-' ||
                    textoArchivo.charAt(x) == '*' || textoArchivo.charAt(x) == '/' ||
                    textoArchivo.charAt(x) == '<' || textoArchivo.charAt(x) == '>' ||
                    textoArchivo.charAt(x) == '=' || textoArchivo.charAt(x) == ';' ||
                    textoArchivo.charAt(x) == ',' || textoArchivo.charAt(x) == '{' ||
                    textoArchivo.charAt(x) == '}' || textoArchivo.charAt(x) == '#') {
                textEdit += " ";
                textEdit += textoArchivo.charAt(x);
                textEdit += " ";
            } else {
                textEdit += textoArchivo.charAt(x);
            }
        }
        return textEdit;
    }

    /**
     * esta funcion me sirve para obtener los tokens
     * con espacios y saltos de linea
     * (para imprimir en consola)
     * */
    public LinkedList<String>tokens(String editText) {
        LinkedList<String> tokens = new LinkedList<String>();
        String textoTemporal = "";

        for (int x = 0; x < editText.length(); x++) {
            if (editText.charAt(x) == '(' || editText.charAt(x) == ')' ||
                    editText.charAt(x) == '+' || editText.charAt(x) == '-' ||
                    editText.charAt(x) == '*' || editText.charAt(x) == '/' ||
                    editText.charAt(x) == '<' || editText.charAt(x) == '>' ||
                    editText.charAt(x) == '=' || editText.charAt(x) == ';' ||
                    editText.charAt(x) == ',' || editText.charAt(x) == '{' ||
                    editText.charAt(x) == '}' || editText.charAt(x) == '#') {

                tokens.add(textoTemporal);
                textoTemporal = Character.toString(editText.charAt(x));
                tokens.add(textoTemporal);
                textoTemporal = "";

            } else {
                textoTemporal += Character.toString(editText.charAt(x));
            }
        }
        return tokens;
    }

    /**
     * esta funcion me sirve para obtener
     * los tokens con las condiciones necesarias
     * para calcular las keys y posteriormente
     * poder relacionar
     * */
    public LinkedList<String>subLineTokens(String tokenText) {
        LinkedList<String>newTokens = new LinkedList<String>();
        String textoTemporal = "";
        boolean firstCharacter = true;
        boolean finalInconcluso = false;
        int hitsSaltosLinea = 0;

        for (int x = 0; x < tokenText.length(); x++) {
            if (tokenText.charAt(x) == '(' || tokenText.charAt(x) == ')' ||
                    tokenText.charAt(x) == '+' || tokenText.charAt(x) == '-' ||
                    tokenText.charAt(x) == '*' || tokenText.charAt(x) == '/' ||
                    tokenText.charAt(x) == '<' || tokenText.charAt(x) == '>' ||
                    tokenText.charAt(x) == '=' || tokenText.charAt(x) == ';' ||
                    tokenText.charAt(x) == ',' || tokenText.charAt(x) == '{' ||
                    tokenText.charAt(x) == '}' || tokenText.charAt(x) == '#') {
                hitsSaltosLinea++;
                if (hitsSaltosLinea > 1) {
                    textoTemporal = Character.toString(tokenText.charAt(x));
                    newTokens.add(textoTemporal);
                    textoTemporal = "";
                } else {
                    if (firstCharacter) {
                        textoTemporal = Character.toString(tokenText.charAt(x));
                        newTokens.add(textoTemporal);
                        textoTemporal = "";
                        firstCharacter = false;
                    } else {
                        newTokens.add(textoTemporal);
                        textoTemporal = Character.toString(tokenText.charAt(x));
                        newTokens.add(textoTemporal);
                        textoTemporal = "";
                    }
                }
                finalInconcluso = false;

            } else if (tokenText.charAt(x) == ' ') {

            } else if (tokenText.charAt(x) == '\n') {
                hitsSaltosLinea++;
            } else {
                textoTemporal += Character.toString(tokenText.charAt(x));
                hitsSaltosLinea = 0;
                firstCharacter = false;
                finalInconcluso = true;
            }
        }
        if (finalInconcluso) {
            newTokens.add(textoTemporal);
        }

        return newTokens;
    }

    /**
     * esta funcion me sirve para sacar las
     * lineas de codigo una por una y almacenarlas
     * en una linkedlist (esto con el fin de
     * poder evaluar cada linea y tener un
     * veredicto final para cada una de las
     * lineas)
     * */
    public LinkedList<String>lineasCodigo(String textoArchivo) {
        LinkedList<String>lineasCodigo = new LinkedList<String>();
        String textoTemporal = "";

        for (int x = 0; x < textoArchivo.length(); x++) {
            if (textoArchivo.charAt(x) == '\n') {
                lineasCodigo.add(textoTemporal);
                textoTemporal = "";
            } else {
                textoTemporal += Character.toString(textoArchivo.charAt(x));
            }
        }
        return lineasCodigo;
    }

    /**
     * falta razonar esta funcionalidad (esta mal
     * planeada tienes que ver el contexto y calcular
     * exactamente que necesito despues)
     * */
    public LinkedList<Integer>lineasKey(LinkedList<String>tokens,String textoArchivo) {
        LinkedList<String>lineasCodigo = lineasCodigo(textoArchivo);

        for (String linea : lineasCodigo) {
            LinkedList<String>tokens2 = subLineTokens(linea);
        }
        return null;
    }
}
