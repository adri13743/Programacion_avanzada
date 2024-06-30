package Practica3;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Table{
    private List<String> cabecera;//que sea array list
    private List<Row> rows;
    protected int numeroFilas = 0;
    public Table(){
        this.cabecera = new ArrayList<>();
        this.rows = new LinkedList<>();
    }

    public int size(){
        return rows.size();
    }

    public void setHeader(List<String> cabecera){
        this.cabecera = cabecera;
    }

    public void addRow(List<Double> data){// que sea ArrayList
        Row datos = new Row(data);
        rows.add(datos);
        numeroFilas+=1;
    }

    public int getNumeroColumnas(){
        if( numeroFilas==0){
            return 0;
        }
        return rows.get(0).getNumDatos();
    }
    public int getNumeroFilas(){return numeroFilas;}
    public Row getRowAt(int i){
        return rows.get(i);
    }

    public List<Double> getColumAt(int i){
        List<Double> columna = new LinkedList<>();
        for (Row row: rows){
            columna.add(row.getData().get(i));
        }
        return columna;
    }
    public List<String> getCabecera(){
        return cabecera;
    }

    public List<Row> getRows(){return rows;}
}