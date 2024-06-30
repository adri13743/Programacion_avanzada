package Practica3;

import java.util.List;

public class Knn implements Algorithm<TableWithLabels,List<Double>,String>,DistanceClient{
    Distance distan;
    TableWithLabels tabla;
    public Knn(Distance distancia){
        tabla = null;
        distan = distancia;
    }
    @Override//Interface Algorithm
    public void train(TableWithLabels data){
        tabla = data;
    }
    @Override//Interface Algorithm
    public String estimate(List<Double> sample){
        Double menor = 1000000.0;;
        String clasMenor = null;
        for(int j = 0; j<tabla.getRows().size(); j++){
            List<Double> row = tabla.getRowAt(j).getData();
            if (distan.calculateDistance(sample,row)< menor){
                menor = distan.calculateDistance(sample,row);
                clasMenor = tabla.getRowAt(j).getLabel();
            }
        }
        return clasMenor;
    }
    @Override
    public void setDistance(Distance distanciaa){
        distan = distanciaa;
    }
    public
    Distance getDistance(){
        return this.distan;
    }
}
