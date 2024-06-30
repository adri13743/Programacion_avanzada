package Practica3;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CSV {
    private List<String> cabecera = new ArrayList<String>();
    private List<Double> datos = new ArrayList<Double>();
    public Table readTable(String n_fichero) throws IOException {
        cabecera = new ArrayList<String>();
        String separador = ",";
        String linea;
        TableWithLabels tabla = new TableWithLabels();
        BufferedReader br = new BufferedReader(new FileReader(new File(n_fichero)));
        linea = br.readLine();
        String[] separado = linea.split(separador);
        cabecera.addAll(Arrays.asList(separado));
        tabla.setHeader(cabecera);
        int rowss = -1;
        while((linea = br.readLine()) != null)
        {
            separado = linea.split(separador);
            for (String cabe:separado){
                if( cabe.matches("[+-]?\\d*(\\.\\d+)?")){
                    Double num = Double.valueOf(cabe);
                    datos.add(num);
                }else{
                    rowss++;
                    tabla.addRow(datos);
                    tabla.getRowAt(rowss).addRowLabel(cabe);
                }
            }
            if (rowss==-1){tabla.addRow(datos);}
            datos = new ArrayList<Double>();
        }
        return tabla;
    }
}
