package Practica1;

import java.util.LinkedList;
import java.util.List;

public class TableWithLabels extends Table {
    private List<RowWithLabel> rowsWithLabel;
    public TableWithLabels(){
        super();
        rowsWithLabel = new LinkedList<>();
    }
    public List<RowWithLabel> getRowsWithLabel(){
        return rowsWithLabel;
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
        return this.rowsWithLabel.get(i);
    }
    @Override
    public void addRow(List<Double> data){// que sea ArrayList
        RowWithLabel datos = new RowWithLabel(data);
        rowsWithLabel.add(datos);
        numeroFilas += 1;

    }
    @Override
    public List<Double> getColumAt(int i){
        List<Double> columna = new LinkedList<>();
        for (RowWithLabel row: rowsWithLabel){
            columna.add(row.getData().get(i));
        }
        return columna;
    }


}
