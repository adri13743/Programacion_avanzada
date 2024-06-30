package Practica3;

public enum DistanceType {
    EUCLIDEAN, MANHATTAN;

    public static DistanceType enteroATipo(int posicion){
        return values()[posicion];
    }
}
