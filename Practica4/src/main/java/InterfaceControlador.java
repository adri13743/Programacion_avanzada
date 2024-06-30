import javafx.scene.chart.ScatterChart;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public interface InterfaceControlador {
    List<String> getCabezera();

    List<String> loadLabels() ;
    HashMap<String,List<List<Double>>> loadGrupos();

    String getGroupM();

    String getGroupE();

    void estimateParams();
    void loadData(String absolutePath) throws IOException;

    HashMap<String, List<List<Double>>> getListas();


    void crearPunto(String punto);
}
