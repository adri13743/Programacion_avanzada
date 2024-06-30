package Practica3;

import java.util.List;
public class EuclideanDistance implements Distance {
    public double calculateDistance(List<Double> p, List<Double> q) {
        double media = 0;
        double num = 0;
        for (int a = 0;a<p.size();a++){
            num = p.get(a)-q.get(a);
            num = Math.pow(num,2);
            media += num;
        }
        return Math.sqrt(media);
    }
}
