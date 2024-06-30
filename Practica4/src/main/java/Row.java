import java.util.List;

public class Row {
    private List<Double> data;
    public Row(List<Double> data){
        this.data = data;
    }
    public int getNumDatos() {
        return data.size();
    }
    public List<Double> getData(){
        return data;
    }

}