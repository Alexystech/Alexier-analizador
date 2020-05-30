package com.equipo.dev;

import java.util.LinkedList;
import java.util.NoSuchElementException;

import com.equipo.constantes.TextConstant;
import com.equipo.constantes.Key;
import com.equipo.constantes.Instrucciones;
import com.equipo.interfaces.IUtilidad;

public class Utilidad implements IUtilidad {

    private final TextConstant myTextConstants = new TextConstant();
    private final Instrucciones myInstructions = new Instrucciones();
    private final Key myKeys = new Key();

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
            } else if (token.equals(myInstructions.STDIO)) {
                keys.add(myKeys.KEY_STDIO);
            } else if (token.equals(myInstructions.INCLUDE)) {
                keys.add(myKeys.KEY_INCLUDE);
            } else if (token.equals(myInstructions.STDLIB)) {
                keys.add(myKeys.KEY_STDLIB);
            } else if (token.equals(myInstructions.INT_MAIN)) {
                keys.add(myKeys.KEY_INT_MAIN);
            } else if (token.equals(myInstructions.RETURN)) {
                keys.add(myKeys.KEY_RETURN);
            } else {
                int valorAleatorio = (int) (Math.random() * 200) + 61;
                keys.add(valorAleatorio);
            }
        }
        return  keys;
    }

    public boolean veredictoFinal(LinkedList<Integer>keyList) {
        boolean tieneError = false;

        try{
            if (keyList.getFirst().equals(myKeys.KEY_IMPRIMIR)) {
                tieneError = evaluarImprimir(keyList);
            } else if (keyList.getFirst().equals(myKeys.KEY_HASHTAG) &&
                        keyList.get(keyList.size()-2).equals(myKeys.KEY_STDIO)) {
                tieneError = evaluarLibreriaStdio(keyList);
            } else if (keyList.getFirst().equals(myKeys.KEY_HASHTAG) &&
                        keyList.get(keyList.size()-2).equals(myKeys.KEY_STDLIB)) {
                tieneError = evaluarLibreriaStdlib(keyList);
            } else if (keyList.getFirst().equals(myKeys.KEY_INT_MAIN)) {
                tieneError = evaluarMetodoPrincipal(keyList);
            } else if (keyList.getFirst().equals(myKeys.KEY_RETURN)) {
                tieneError = evaluarReturn(keyList);
            } else if (keyList.getFirst().equals(myKeys.KEY_LLAVE)) {
                tieneError = evaluarLlaveApertura(keyList);
            } else if (keyList.getFirst().equals(myKeys.KEY_LLAVE_CIERRE)) {
                tieneError = evaluarLlaveCierre(keyList);
            } else {
                tieneError = true;
            }
        } catch (NoSuchElementException e) {
            tieneError = false;
        }

        return tieneError;
    }

    public boolean evaluarLlaveCierre(LinkedList<Integer>keyLis) {
        if (keyLis.size() > 1) {
            return true;
        } else {
            return false;
        }
    }

    public boolean evaluarLlaveApertura(LinkedList<Integer>keyList) {
        if (keyList.size() > 1) {
            return true;
        } else {
            return false;
        }
    }

    public boolean evaluarReturn(LinkedList<Integer>keyList) {
        if (keyList.size() < 2) {
            return true;
        } else {
            if (keyList.getFirst().equals(myKeys.KEY_RETURN) &&
                keyList.get(1).equals(myKeys.KEY_PUNTO_COMA)) {
                return false;
            } else {
                return true;
            }
        }
    }

    public boolean evaluarMetodoPrincipal(LinkedList<Integer>keyList) {
        if (keyList.size() < 3) {
            return true;
        } else {
            if (keyList.getFirst().equals(myKeys.KEY_INT_MAIN) &&
                keyList.get(1).equals(myKeys.KEY_PARENTESIS) &&
                    ((keyList.getLast().equals(myKeys.KEY_LLAVE) &&
                            keyList.get(keyList.size()-2)
                                    .equals(myKeys.KEY_PARENTESIS_CIERRE)) ||
                            keyList.getLast().equals(myKeys.KEY_PARENTESIS_CIERRE)
                    )) {
                return false;
            } else {
                return true;
            }
        }
    }

    public boolean evaluarLibreriaStdlib(LinkedList<Integer>keyList) {
        if (keyList.size() != 5) {
            return true;
        } else {
            if (keyList.getFirst().equals(myKeys.KEY_HASHTAG) &&
                keyList.get(1).equals(myKeys.KEY_INCLUDE) &&
                keyList.get(2).equals(myKeys.KEY_MENOR_QUE) &&
                keyList.get(keyList.size()-2).equals(myKeys.KEY_STDLIB) &&
                keyList.getLast().equals(myKeys.KEY_MAYOR_QUE)) {
                return false;
            } else {
                return true;
            }
        }
    }

    public boolean evaluarLibreriaStdio(LinkedList<Integer>keyList) {
        if (keyList.size() != 5) {
            return true;
        } else {
            if (keyList.getFirst().equals(myKeys.KEY_HASHTAG) &&
                keyList.get(1).equals(myKeys.KEY_INCLUDE) &&
                keyList.get(2).equals(myKeys.KEY_MENOR_QUE) &&
                keyList.get(keyList.size()-2).equals(myKeys.KEY_STDIO) &&
                keyList.getLast().equals(myKeys.KEY_MAYOR_QUE)) {
                return false;
            } else {
                return true;
            }
        }
    }

    public boolean evaluarImprimir(LinkedList<Integer>keyList) {
        if (keyList.size() < 4) {
            return true;
        } else {
            if (keyList.getFirst().equals(myKeys.KEY_IMPRIMIR) &&
                    keyList.get(1).equals(myKeys.KEY_PARENTESIS) &&
                    keyList.getLast().equals(myKeys.KEY_PUNTO_COMA) &&
                    keyList.get(keyList.size()-2)
                    .equals(myKeys.KEY_PARENTESIS_CIERRE)) {
                return false;
            } else {
                return true;
            }
        }
    }

}
