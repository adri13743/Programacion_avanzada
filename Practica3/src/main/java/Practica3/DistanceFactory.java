package Practica3;

public class DistanceFactory implements Factory{

    public Distance getDistance(DistanceType tipo) {
        Distance distancia;
        switch (tipo){
            case EUCLIDEAN:
                distancia = new EuclideanDistance();
                break;
            case MANHATTAN:
                distancia = new ManhattanDistance();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + tipo);
        }
        return distancia;
    }
}
