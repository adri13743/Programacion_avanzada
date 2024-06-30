package Practica1;
import java.util.List;

public class Row {
    private List<Double> data;
    public int getNumDatos() {
        return data.size();
    }

    public Row(List<Double> data){
        this.data = data;
    }
    public List<Double> getData(){
        return data;
    }

}