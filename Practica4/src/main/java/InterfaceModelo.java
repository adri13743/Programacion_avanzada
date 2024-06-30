import javafx.scene.chart.ScatterChart;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public interface InterfaceModelo {
    void loadData(String filename) throws IOException;
    int getNumRows();
    List<Double> getRow(int i);
    List<String> getLabels();

    List<String> getCabezera();

    HashMap<String, List<List<Double>>> getListas();

    String getGroupM();

    String getGroupE();

    void crearPunto(String punto);
}
