package Practica3;
import java.util.*;

public class Kmeans implements Algorithm<Table,Row,String>,DistanceClient{
    Distance distancia;
    private ArrayList<Row>  repres;
    private int numberClusters;
    private int iterations;
    private long seed;
    public Kmeans(int numberClusters, int iterations, long seed,Distance distancia){
        this.numberClusters = numberClusters;
        this.iterations = iterations;
        this.seed = seed;
        this.distancia = distancia;
    }
    @Override//Interface Algorithm
    public void train(Table tabla) {
        repres = obtenerRepresentantes(tabla);
        ArrayList<Row> lista = new ArrayList<Row>();
        for (int i = 0; i < iterations+1;i++) {//(ok)
            lista = asignarRepresentantes(repres,tabla);
            this.repres = obtenerCentroide(lista,repres,tabla);

        }

    }

    @Override//Interface Algorithm
    public String estimate(Row r) {
        Row estimado = repres.get(0);
        int indice = 1;
        for(int i=1; i<repres.size(); i++){
            if(distancia.calculateDistance(r.getData(), repres.get(i).getData()) < distancia.calculateDistance(r.getData(), estimado.getData())){
                estimado = repres.get(i);
                indice = i+1;
            }
        }
        return "cluster-" + indice;
    }


    public ArrayList<Row>  obtenerRepresentantes(Table tabla){//representantes aleatorios(ok)
        ArrayList<Row> lista = new ArrayList<Row>();
        ArrayList<Row> representantes = new ArrayList<Row>();
        for(int i = 0;i<tabla.size();i++){
            lista.add(tabla.getRowAt(i));
        }
        Random random = new Random(this.seed);
        Collections.shuffle(lista,random);
        for (int i =0;i<numberClusters;i++){
            representantes.add(lista.get(i));
        }
        return representantes;
    }

    public ArrayList<Row> asignarRepresentantes(ArrayList<Row> representantes,Table tabla){//(ok)
        ArrayList<Row> lista_grupo = new ArrayList<Row>();
        for(int i =0;i<tabla.size();i++){
            Row representante_proximo = representantes.get(0);
            for (int x = 1;x<numberClusters;x++){//(ok)
                if(distancia.calculateDistance(representantes.get(x).getData(),tabla.getRowAt(i).getData())<distancia.calculateDistance(representante_proximo.getData(),tabla.getRowAt(i).getData())){
                    representante_proximo = representantes.get(x);
                }
            }
            lista_grupo.add(i,representante_proximo);
        }
        return lista_grupo;
    }

    public Double calcularDistancia(Row row_i,Row row_x){//se usa en asignacion para saber cual esta mas cerca del comparado(ok)
        List<Double> lista_i = row_i.getData();
        List<Double> lista_x = row_x.getData();
        double media = 0;
        double num = 0;
        for (int a = 0;a<lista_i.size();a++){
            num = Math.abs(lista_i.get(a)-lista_x.get(a));
            num = Math.pow(2,num);
            media += num;
        }
        return Math.sqrt(media);
    }


    public ArrayList<Row> obtenerCentroide(ArrayList<Row> lista_dato_representante,List<Row> representante,Table tabla){
        ArrayList<Row> Lista_representantes = new ArrayList<Row>();
        for(Row rep1: representante){
            List<Double> centroide = new LinkedList<Double>();
            int i = 0;
            for(int indice_lista_dato = 0; indice_lista_dato<lista_dato_representante.size(); indice_lista_dato++){
                if(lista_dato_representante.get(indice_lista_dato).equals(rep1)) {
                    i++;
                    centroide = lista_dato_representante.get(indice_lista_dato).getData();
                    for (int j = 0; j < tabla.getRowAt(j).getData().size(); j++) {
                        centroide.set(j, centroide.get(j) + tabla.getRowAt(indice_lista_dato).getData().get(j));
                    }
                }
                int o = 0;
                for(Double num: centroide){
                    centroide.set(o,num/i);
                    o++;
                }
            }
            Lista_representantes.add(new Row(centroide));
        }
        return Lista_representantes;
    }
    public
    ArrayList<Row>getCentroid(){
        return this.repres;
    }

    @Override
    public void setDistance(Distance distanciaa){
        distancia = distanciaa;
    }
    public
    Distance getDistance(){
        return this.distancia;
    }

}
