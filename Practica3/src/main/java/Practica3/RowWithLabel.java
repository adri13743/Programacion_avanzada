package Practica3;

import java.util.List;

public class RowWithLabel extends Row{
    private String label;

    public RowWithLabel(List<Double> data){
        super(data);
        this.label = null;
    }
    public void addRowLabel(String label){
        this.label = label;
    }
    public String getLabel(){
        return this.label;
    }
    public void setLabel(String label){
        this.label = label;
    }

}
