package com.equipo.dev;

import java.util.LinkedList;
import java.util.NoSuchElementException;

import com.equipo.constantes.TextConstant;
import com.equipo.constantes.Key;
import com.equipo.constantes.Instrucciones;
import com.equipo.interfaces.IUtilidad;

public class Utilidad implements IUtilidad {

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
     * este metodo sirve para sacar las llaves
     * de una linea de codigo que retorna una
     * LinkedList con los valores numericos
     * correspondientes para poder sacar el
     * veredicto final de la linea
     * */
    public LinkedList<Integer> keyList(String lineaCodigo) {
        TextConstant myTextConstants = new TextConstant();
        Instrucciones myInstructions = new Instrucciones();
        Key myKeys = new Key();

        LinkedList<String>tokensTemporal = subLineTokens(lineaCodigo);
        LinkedList<Integer>keys = new LinkedList<Integer>();

        for (String token : tokensTemporal) {
            if (token.equals(myTextConstants.TEXT_PARENTESIS)) {
                keys.add(myKeys.KEY_PARENTESIS);
            } else if (token.equals(myTextConstants.TEXT_PARENTESIS_CIERRE)) {
                keys.add(myKeys.KEY_PARENTESIS_CIERRE);
            } else if (token.equals(myTextConstants.TEXT_MENOR_QUE)) {
                keys.add(myKeys.KEY_MENOR_QUE);
            } else if (token.equals(myTextConstants.TEXT_MAYOR_QUE)) {
                keys.add(myKeys.KEY_MAYOR_QUE);
            } else if (token.equals(myTextConstants.TEXT_IGUAL)) {
                keys.add(myKeys.KEY_IGUAL);
            } else if (token.equals(myTextConstants.TEXT_PUNTO_COMA)) {
                keys.add(myKeys.KEY_PUNTO_COMA);
            } else if (token.equals(myTextConstants.TEXT_COMA)) {
                keys.add(myKeys.KEY_COMA);
            } else if (token.equals(myTextConstants.TEXT_LLAVE)) {
                keys.add(myKeys.KEY_LLAVE);
            } else if (token.equals(myTextConstants.TEXT_LLAVE_CIERRE)) {
                keys.add(myKeys.KEY_LLAVE_CIERRE);
            } else if (token.equals(myTextConstants.TEXT_SUMA)) {
                keys.add(myKeys.KEY_SUMA);
            } else if (token.equals(myTextConstants.TEXT_RESTA)) {
                keys.add(myKeys.KEY_RESTA);
            } else if (token.equals(myTextConstants.TEXT_MULTIPLICACION)) {
                keys.add(myKeys.KEY_MULTIPLICACION);
            } else if (token.equals(myTextConstants.TEXT_DIVISION)) {
                keys.add(myKeys.KEY_DIVISION);
            } else if (token.equals(myTextConstants.TEXT_HASHTAG)) {
                keys.add(myKeys.KEY_HASHTAG);
            } else if (token.equals(myInstructions.IMPRIMIR)) {
                keys.add(myKeys.KEY_IMPRIMIR);
            } else {
                int valorAleatorio = (int) (Math.random() * 80) + 21;
                keys.add(valorAleatorio);
            }
        }
        return  keys;
    }

    public boolean veredictoFinal(LinkedList<Integer>keyList) {
        boolean tieneError = false;

        try{
            if (keyList.getFirst().equals(6)) {
                tieneError = evaluarImprimir(keyList);
            }
        } catch (NoSuchElementException e) {
            tieneError = false;
        }

        return tieneError;
    }

    public boolean evaluarImprimir(LinkedList<Integer>keyList) {
        if (keyList.size() < 4) {
            return true;
        } else {
            if (keyList.getFirst().equals(6) && keyList.get(1).equals(10)
                && keyList.getLast().equals(15) && keyList.get(keyList.size()-2)
                    .equals(11)) {
                return false;
            } else {
                return true;
            }
        }
    }

}
