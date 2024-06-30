package Practica2;
import java.util.*;

public class Kmeans implements Algorithm<Table,Row, String>{
    private ArrayList<Integer> centroid;
    private TableWithLabels tabla;
    private ArrayList<Integer> lista;
    private int numberClusters;
    private int iterations;
    private long seed;
    public Kmeans(int numberClusters, int iterations, long seed){
        this.numberClusters = numberClusters;
        this.iterations = iterations;
        this.seed = seed;

    }
    @Override//Interface Algorithm
    public void train(Table tabla) {
        this.tabla = (TableWithLabels) tabla;
        ArrayList<Integer> representantes = Representantes(tabla);
        //Descomenta(representantes a mano) o comenta(aleatorio) la linea 22 para repasar tests(solucion para hacer test controlado) para mas pruebas de numeros aleatorios cambiar la seed en cvtest
        representantes = this.centroid;
        ArrayList<Integer> lista = asignacion(representantes,tabla);
        for (int i = 0; i < iterations;i++) {
            representantes = centroide(lista,representantes,tabla);
            this.centroid = representantes;
            lista = asignacion(representantes, tabla);
        }
        this.lista = lista;
    }
    public void setCentroides(ArrayList<Integer> centroid){
        this.centroid = centroid;
    }

    @Override//Interface Algorithm
    public String estimate(Row r) {//
        this.tabla.addRow(r.getData());
        train(tabla);
        this.tabla.getRowAt(tabla.size()-1).setLabel(tabla.getRowAt(lista.get(tabla.size()-1)).getLabel());
        return tabla.getRowAt(tabla.size()-1).getLabel();
    }


    public ArrayList<Integer> Representantes(Table tabla){
        ArrayList<Integer> lista = new ArrayList<Integer>();
        ArrayList<Integer> representantes = new ArrayList<Integer>();
        for(int i = 0;i<tabla.size();i++){
            lista.add(i);
        }
        Random random = new Random(this.seed);
        Collections.shuffle(lista,random);
        for (int i =0;i<numberClusters;i++){
            representantes.add(lista.get(i));
        }
        return representantes;
    }

    public ArrayList<Integer> asignacion(ArrayList<Integer> representantes,Table tabla){
        ArrayList<Integer> lista_grupo = new ArrayList<Integer>(tabla.size());
        for(int i =0;i<tabla.size();i++){
            if(!representantes.contains(i)){
                int representante_prox = representantes.get(0);
                for (int x = 1;x<numberClusters;x++){

                    if(kmean(tabla,i,representantes.get(x))<kmean(tabla,i,representante_prox)){
                        representante_prox = representantes.get(x);
                    }
                }
                lista_grupo.add(i,representante_prox);
            }else{
                lista_grupo.add(i,i);
            }
        }
        return lista_grupo;
    }

    public Double kmean(Table tabla, int pos_i,int pos_x){//se usa en asignacion para saber cual esta mas cerca del comparado
        List<Double> lista_i = tabla.getRowAt(pos_i).getData();
        List<Double> lista_x = tabla.getRowAt(pos_x).getData();
        double media = 0;
        for (int a = 0;a<lista_i.size();a++){
            double resta = lista_i.get(a) - lista_x.get(a);
            if (resta < 0){resta*=-1;}
            media+=resta;
        }
        return media;
    }

    public Double kmean2(Table tabla ,ArrayList<Double> list,int representante){//se usa en asignacion para saber cual esta mas cerca del comparado
        double media = 0;
        List<Double> lista_i = tabla.getRowAt(representante).getData();
        for (int a = 0;a<list.size();a++){
            double resta = lista_i.get(a) - list.get(a);
            if (resta < 0){resta*=-1;}
            media+=resta;
        }
        return media;
    }

    public ArrayList<Integer> centroide(ArrayList<Integer> lista,ArrayList<Integer> representante,Table tabla){
        ArrayList<Integer> nuevos_repres = new ArrayList<Integer>();
        for(int i = 0; i<representante.size();i++){
            ArrayList<Double> centro = new ArrayList<Double>();
            int m = 0;
            ArrayList<Integer> list = new ArrayList<Integer>();
            for (int a = 0; a < tabla.getNumeroColumnas()-1;a++){
                list.clear();
                double suma = 0;
                int n = 0;
                for (int x = 0; x < lista.size();x++){
                    if (representante.get(i)==lista.get(x)){
                        suma += tabla.getRowAt(x).getData().get(a);
                        n++;
                        list.add(x);
                    }
                }
                m = n;
                suma/=n;
                centro.add(a,suma);
            }//centroide ya calculado
            int representante_prox = list.get(0);
            for (int x = 0;x<m;x++){//se calcula punto mas cercano al centroide
                if(kmean2(tabla,centro,list.get(x))<kmean2(tabla,centro,representante_prox)){
                    representante_prox = list.get(x);
                }
            }
            nuevos_repres.add(representante_prox);
        }
        return nuevos_repres;
    }
    public ArrayList<Integer> getCentroid(){
        return centroid;
    }
}
