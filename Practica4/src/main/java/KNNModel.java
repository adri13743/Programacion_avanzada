import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class KNNModel implements InterfaceModelo{
    private InterfaceVista vista;
    private TableWithLabels data;
    public  KNNModel(InterfaceVista vista){
    this.vista = vista;
    }
    public void set(InterfaceVista vista) {
        this.vista = vista;

    }
    public void loadData(String filename) throws IOException {
        CSV csv = new CSV();
        data = csv.readTablew(filename);
        vista.datosDisponibles();//comunica con vista
    }

    public int getNumRows(){
        return data.size();
    }

    @Override
    public List<Double> getRow(int i) {
        return data.getRowAt(i).getData();
    }

    @Override
    public List<String> getLabels() {
        List<String> lista = new ArrayList<String>();
        lista.add(data.getRowAt(0).getLabel());
        for (int i = 1;i<data.size();i++){
            if (!lista.contains(data.getRowAt(i).getLabel())){
                lista.add(data.getRowAt(i).getLabel());
            }
        }
        return lista;
    }
    @Override
    public List<String> getCabezera() {return data.getCabecera();}

    public HashMap<String, List<List<Double>>> getListas() {
        HashMap<String,List<List<Double>>> gruposLabels = new HashMap<String,List<List<Double>>>();
        List<String> listaLabels = getLabels();
        for(int i = 0;i<listaLabels.size();i++){
            List<List<Double>> lista = new ArrayList<List<Double>>();
            for(int j = 0;j<data.size();j++){
                if(Objects.equals(data.getRowAt(j).getLabel(), listaLabels.get(i))){
                    lista.add(data.getRowAt(j).getData());
                }
            }
            gruposLabels.put(listaLabels.get(i),lista);
        }
        return gruposLabels;
    }
    @Override
    public String getGroupM(){
        Knn k = new Knn(new ManhattanDistance());
        k.train(data);
        return k.estimate(data.getRowAt(data.size()-1).getData());
    }
    @Override
    public String getGroupE(){
        Knn k = new Knn(new EuclideanDistance());
        k.train(data);
        return k.estimate(data.getRowAt(data.size()-1).getData());
    }

    @Override
    public void crearPunto(String punto){
        List<Double> numeros = new ArrayList<>();
        char[] text = punto.toCharArray();
        StringBuilder num = new StringBuilder();
        for (int i = 0;i<text.length;i++){
            if (text[i]==','){
                numeros.add(Double.parseDouble(num.toString()));
                num = new StringBuilder();
            }else{
                num.append(text[i]);
            }
        }
        numeros.add(Double.parseDouble(num.toString()));
        data.addRow(numeros);
    }

}
