package Practica2;

import java.util.LinkedList;
import java.util.List;

public class TableWithLabels extends Table {

    public TableWithLabels(){
        super();
    }

    @Override
    public int getNumeroColumnas(){
        if( numeroFilas==0){
            return 0;
        }
        return getCabecera().size();
    }
    @Override
    public RowWithLabel getRowAt(int i){
        return (RowWithLabel) getRows().get(i);
    }
    @Override
    public void addRow(List<Double> data){// que sea ArrayList
        RowWithLabel datos = new RowWithLabel(data);
        getRows().add(datos);
        numeroFilas += 1;

    }
    @Override
    public List<Double> getColumAt(int i){
        List<Double> columna = new LinkedList<>();
        for (Row row: getRows()){
            columna.add(row.getData().get(i));
        }
        return columna;
    }


}
