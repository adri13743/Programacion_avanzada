package Practica3;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ManhattanDistanceTest {
    @Test
    void calculateDistance() {
        List<Double> x = new ArrayList<Double>();
        x.add(3.0);
        x.add(-4.0);
        ManhattanDistance md = new ManhattanDistance();
        Double d = md.calculateDistance(x,x);
        assertEquals(0.0,d);
    }
}