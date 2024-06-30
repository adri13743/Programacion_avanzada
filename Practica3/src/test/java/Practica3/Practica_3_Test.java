package Practica3;

import org.junit.jupiter.api.BeforeEach;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class KmeansTest {
    Distance dist = new ManhattanDistance();
    Distance dist2 = new EuclideanDistance();
    CSV csv = new CSV();
    TableWithLabels tabla3 = new TableWithLabels();
    TableWithLabels tabla4 = new TableWithLabels();
    Kmeans kk = new Kmeans(2,1,1111,dist);
    Kmeans kk2 = new Kmeans(2,1,11111,dist2);
    String separador = System.getProperty("file.separator");//dependiendo del sistema oprativo el separador sera diferente
    @BeforeEach
    void previo() throws IOException {
        tabla3 = (TableWithLabels) csv.readTable("src"+separador+"main"+separador+"resources"+separador+ "test.csv");
        tabla4 = (TableWithLabels) csv.readTable("src"+separador+"main"+separador+"resources"+separador+ "test.csv");
        kk.train(tabla3);
        kk2.train(tabla4);
    }

    @org.junit.jupiter.api.Test
    void testTrain(){
        ArrayList<Double> centroides1 = new ArrayList<>();centroides1.add(0.0484375);centroides1.add(0.0484375);centroides1.add(0.0484375);centroides1.add(0.0484375);
        ArrayList<Double> centroides2 = new ArrayList<>();centroides2.add(0.0016402435555555555);centroides2.add(0.0016402435555555555);centroides2.add(0.0016402435555555555);centroides2.add(0.0016402435555555555);
        ArrayList<List<Double>> centroides = new ArrayList<List<Double>>() ;
        centroides.add(centroides1);centroides.add(centroides2);
        assertEquals(centroides1,kk.getCentroid().get(0).getData());
        assertEquals(centroides2,kk2.getCentroid().get(0).getData());
    }
    @org.junit.jupiter.api.Test
    void estimateTest(){
        LinkedList<Double> datos1 = new LinkedList<>();
        LinkedList<Double> datos2 = new LinkedList<>();
        LinkedList<Double> datos3 = new LinkedList<>();
        datos1.add(2.2);
        datos1.add(1.1);
        datos2.add(0.2);
        datos2.add(0.1);
        datos3.add(100.2);
        datos3.add(50.1);
        Row aux = new Row(datos3);
        Row centroide1 = new Row(datos1);
        Row centroide2 = new Row(datos2);
        ArrayList<Row> centroides = new ArrayList<>();
        assertEquals(kk.estimate(centroide2), "cluster-1");
        assertEquals(kk.estimate(centroide1), "cluster-2");
        assertEquals(kk.estimate(aux), "cluster-2");
        assertEquals(kk.getDistance(), dist);
        kk.setDistance(dist2);
        assertEquals(kk.getDistance(), dist2);
        kk2.setDistance(dist);
        assertEquals(kk2.getDistance(), dist);

    }
}
class KNNTest {
    TableWithLabels tabla2 = new TableWithLabels();
    CSV csv = new CSV();
    Knn k = new Knn(new ManhattanDistance());
    Knn k2 = new Knn(new EuclideanDistance());
    String separador = System.getProperty("file.separator");
    @BeforeEach
    void previo() throws IOException {
        tabla2 = (TableWithLabels) csv.readTable("src"+separador+"main"+separador+"resources"+separador+"iris.csv");
        k.train(tabla2);
        k2.train(tabla2);
    }
    @org.junit.jupiter.api.Test
    void testKnn(){
        assertEquals("Iris-setosa",k.estimate(tabla2.getRowAt(0).getData()));//fichero iris
        assertEquals("Iris-setosa",k.estimate(tabla2.getRowAt(49).getData()));//fichero iris
        assertEquals("Iris-versicolor",k.estimate(tabla2.getRowAt(80).getData()));//fichero iris
        assertEquals("Iris-versicolor",k.estimate(tabla2.getRowAt(99).getData()));//fichero iris
        assertEquals("Iris-virginica",k.estimate(tabla2.getRowAt(100).getData()));//fichero iris
        assertEquals("Iris-setosa",k2.estimate(tabla2.getRowAt(0).getData()));//fichero iris
        assertEquals("Iris-setosa",k2.estimate(tabla2.getRowAt(49).getData()));//fichero iris
        assertEquals("Iris-versicolor",k2.estimate(tabla2.getRowAt(80).getData()));//fichero iris
        assertEquals("Iris-versicolor",k2.estimate(tabla2.getRowAt(99).getData()));//fichero iris
        assertEquals("Iris-virginica",k2.estimate(tabla2.getRowAt(100).getData()));//fichero iris
    }
}