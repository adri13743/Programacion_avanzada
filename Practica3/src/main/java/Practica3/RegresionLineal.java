package Practica3;

import java.util.List;

public class RegresionLineal implements Algorithm<Table,Double,Double>{
    private Double alpha;
    private Double beta;

    public RegresionLineal(){
        alpha = null;
        beta = null;
    }
    @Override//Interface Algorithm
    public void train(Table tabla){
        List<Double> listaX = tabla.getColumAt(0);
        List<Double> listaY = tabla.getColumAt(1);
        Double mediaX = mediaColum(tabla, 0);
        Double mediaY = mediaColum(tabla, 1);
        Double a = 0.0, b = 0.0;
        for(int i=0; i<tabla.getNumeroFilas(); i++){
            a += (listaX.get(i)-mediaX)*(listaY.get(i)-mediaY);
            b += (listaX.get(i)-mediaX)*(listaX.get(i)-mediaX);
        }
        alpha = a/b;
        beta = mediaY - alpha*mediaX;
    }
    @Override//Interface Algorithm
    public Double estimate(Double sample){
        Double y = alpha * sample + beta;
        return y;
    }
    private Double mediaColum(Table tabla, int indice){
        Double media = 0.0;
        for(Double dato: tabla.getColumAt(indice)){
            media+=dato;
        }
        media = media/tabla.getNumeroFilas();
        return media;
    }
    public Double getAlpha(){
        return alpha;
    }
    public Double getBeta(){
        return beta;
    }



}

