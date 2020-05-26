package com.equipo.dev;

import com.equipo.interfaces.ICustomFile;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.File;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.PrintWriter;
import java.io.IOException;

public class CustomFile implements ICustomFile {
    private final File archivo;

    public CustomFile(String ruta) {
        archivo = new File(ruta);
    }

    @Override
    public String read() {
        FileReader miFileReader;
        try {
            miFileReader = new FileReader(archivo);
            BufferedReader br = new BufferedReader(miFileReader);

            String textoCompleto = "";
            String textoPorLinea = "";

            while (true) {
                textoPorLinea = br.readLine();
                if (textoPorLinea != null) {
                    textoCompleto += textoPorLinea+"\n";
                } else {
                    break;
                }
            }
            br.close();
            miFileReader.close();
            return textoCompleto;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void write(String texto) {
        FileWriter escritura;
        try {
            escritura = new FileWriter(archivo);
            BufferedWriter bw = new BufferedWriter(escritura);
            PrintWriter salida = new PrintWriter(bw);

            salida.write(texto+"\n");

            salida.close();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
