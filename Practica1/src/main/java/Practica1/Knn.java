package Practica1;

import java.util.List;

public class Knn {
    TableWithLabels tabla;
    public Knn(){
        tabla = null;
    }
    public void train(TableWithLabels data){
        tabla = data;
    }
    public String estimate(List<Double> sample){
        Double menor = 1000000.0;
        String clasMenor = null;
        for(int j = 0; j<tabla.getRowsWithLabel().size(); j++) {
            Double a = 0.0;
            List<Double> row = tabla.getRowAt(j).getData();
            for (int i = 0; i < row.size(); i++) {
                a += Math.pow(sample.get(i) - row.get(i), 2);
            }
            Double d = Math.sqrt(a);
            if (menor > d) {
                menor = d;
                clasMenor = tabla.getRowAt(j).getLabel();
            }
        }
        return clasMenor;
    }
}
