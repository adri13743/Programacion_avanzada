package Practica3;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DistanceFactoryTest {
    List<Double> dist1 = new LinkedList<>();
    List<Double> dist2 = new LinkedList<>();

    @org.junit.jupiter.api.Test
    void calcuarDistanciaTest(){
        dist1.add(3.0);
        dist1.add(6.0);
        dist1.add(9.0);
        dist2.add(2.0);
        dist2.add(4.0);
        dist2.add(8.0);

        DistanceType eucli = DistanceType.enteroATipo(0);//Indice uno correspondiente a la distancia euclidea
        Distance dist_eucli = new DistanceFactory().getDistance(eucli);
        assertEquals(dist_eucli.calculateDistance(dist1,  dist2), new EuclideanDistance().calculateDistance(dist1, dist2));
    }
}
