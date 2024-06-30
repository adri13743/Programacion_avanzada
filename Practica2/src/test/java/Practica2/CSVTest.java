package Practica2;
import org.junit.jupiter.api.BeforeEach;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CSVTest {
    Table tabla0 = new TableWithLabels();
    TableWithLabels tabla = new TableWithLabels();//pruebas especiales para ver el correcto funcionamiento en "tabla"
    TableWithLabels tabla2 = new TableWithLabels();
    TableWithLabels tabla3 = new TableWithLabels();
    RegresionLineal r0;
    RegresionLineal r;
    RegresionLineal r2;
    CSV csv = new CSV();
    Knn k = new Knn();
    Knn k2 = new Knn();
    Kmeans kk = new Kmeans(2,1,1111);
    String separador = System.getProperty("file.separator");//dependiendo del sistema oprativo el separador sera diferente
    ArrayList<Integer> representantes = new ArrayList<>();

    @BeforeEach// BeforeEach para inicar datos de nuevo despues de cada prueba // ALL solo al iniciar las pruebas
    void previo() throws IOException {
        tabla0 = csv.readTable("src"+separador+"main"+separador+"resources"+separador+"miles_dollars.csv");
        tabla = (TableWithLabels) csv.readTable("src"+separador+"main"+separador+"resources"+separador+"miles_dollars.csv");
        tabla2 = (TableWithLabels) csv.readTable("src"+separador+"main"+separador+"resources"+separador+"iris.csv");
        tabla3 = (TableWithLabels) csv.readTable("src"+separador+"main"+separador+"resources"+separador+ "test.csv");
        r0 = new RegresionLineal();
        r0.train(tabla0);
        r = new RegresionLineal();
        r.train(tabla);
        r2 = new RegresionLineal();
        r2.train(tabla2);
        k.train(tabla);
        k2.train(tabla2);
        tabla.getRowAt(0).addRowLabel("Juan");
        tabla.getRowAt(2).addRowLabel("Jovenayo");
        tabla.getRowAt(24).addRowLabel("Adrian");
        representantes.add(0);
        representantes.add(6);
        kk.setCentroides(representantes);
        kk.train(tabla3);


    }
    @org.junit.jupiter.api.Test
    void filasLeidas(){//El número de ejemplares (filas) leído es correcto.
        assertEquals(25,tabla0.getNumeroFilas());
        assertEquals(25,tabla.getNumeroFilas());
        assertEquals(150,tabla2.getNumeroFilas());
    }
    @org.junit.jupiter.api.Test
    void columnasLeidas(){//El número de columnas es correcto.
        assertEquals(2,tabla0.getNumeroColumnas());
        assertEquals(2,tabla.getNumeroColumnas());
        assertEquals(5,tabla2.getNumeroColumnas());
    }
    @org.junit.jupiter.api.Test
    void getLabel(){//Pruebas del primer fichero(miles_dollars)siendo TableWithLabels añadido en previo
        assertEquals("Juan",tabla.getRowAt(0).getLabel());
        assertEquals("Jovenayo",tabla.getRowAt(2).getLabel());
        assertEquals("Adrian",tabla.getRowAt(24).getLabel());
        assertNull(tabla.getRowAt(1).getLabel());
    }
    @org.junit.jupiter.api.Test
    void getRowLabel(){//Pruebas getlabel con el 2 fichero(iris.csv)
        assertEquals("Iris-setosa",tabla2.getRowAt(0).getLabel());
        assertEquals("Iris-setosa",tabla2.getRowAt(49).getLabel());
        tabla2.getRowAt(49).addRowLabel("Juan");//probando el metodo addrowlabel para cambiar el contenido de la row
        assertEquals("Juan",tabla2.getRowAt(49).getLabel());//su prueba
        assertEquals("Iris-versicolor",tabla2.getRowAt(50).getLabel());
        assertEquals("Iris-versicolor",tabla2.getRowAt(99).getLabel());
        assertEquals("Iris-virginica",tabla2.getRowAt(100).getLabel());
    }
    @org.junit.jupiter.api.Test
    void regeresionAlpha(){
        assertEquals(1.255, r.getAlpha(), 0.001);//fichero dollars añadiendo row(TableWithLabels)
        assertEquals(-0.0572, r2.getAlpha(), 0.001);//fichero iris
        assertEquals(1.255, r0.getAlpha(), 0.001);//fichero dollars(table)
    }
    @org.junit.jupiter.api.Test
    void regeresionBeta(){
        assertEquals(274.85, r.getBeta(), 0.001);//fichero dollars añadiendo row(TableWithLabels)
        assertEquals(3.388, r2.getBeta(), 0.001);//fichero iris
        assertEquals(274.85, r0.getBeta(), 0.001);//fichero dollars(table)
    }
    @org.junit.jupiter.api.Test
    void testEstimate(){
        assertEquals(1795.05,r.estimate(1211.0),0.01);//fichero dollars añadiendo row(TableWithLabels)
        assertEquals(-65.963,r2.estimate(1211.0),0.01);//fichero iris
        assertEquals(1795.05,r0.estimate(1211.0),0.01);//fichero dollars(table)

    }
    @org.junit.jupiter.api.Test
    void testKnn(){
        assertEquals("Juan",k.estimate(tabla.getRowAt(0).getData()));//fichero dollars añadiendo row
        assertEquals("Jovenayo",k.estimate(tabla.getRowAt(2).getData()));//fichero dollars añadiendo row
        assertEquals("Iris-setosa",k2.estimate(tabla2.getRowAt(0).getData()));//fichero iris
        assertEquals("Iris-setosa",k2.estimate(tabla2.getRowAt(49).getData()));//fichero iris
        assertEquals("Iris-versicolor",k2.estimate(tabla2.getRowAt(80).getData()));//fichero iris
        assertEquals("Iris-versicolor",k2.estimate(tabla2.getRowAt(99).getData()));//fichero iris
        assertEquals("Iris-virginica",k2.estimate(tabla2.getRowAt(100).getData()));//fichero iris
    }
    @org.junit.jupiter.api.Test
    void getColumn(){
        List<Double> lista = tabla.getColumAt(0);
        Double dato = lista.get(0);
        assertEquals(1211, dato);
    }
    @org.junit.jupiter.api.Test
        //Los dos centroides del fichero test.csv son 2 y 7, por lo tanto se comprueba que dicho metodo obtenga realmente esos numeros (indices de los centroides).
    void testKmean(){
        ArrayList<Integer> centroides = new ArrayList<>();centroides.add(2);centroides.add(7);
        assertEquals(centroides,kk.getCentroid());
    }

    @org.junit.jupiter.api.Test
    void testEstimateKmean(){
        LinkedList<Double> datos = new LinkedList<>();
        datos.add(1.2);
        datos.add(1.2);
        datos.add(1.2);
        datos.add(1.2);
        Row row = new Row(datos);
        System.out.println(kk.estimate(row));
        assertEquals("C5", kk.estimate(row) );
    }


}