package Practica2;

public interface Algorithm<T,R,L> {//T(Table,TableWithLabels) R(Double,List<Double>) L(Double,String)
     void train(T tabla);
     L estimate(R datos);
}
