import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class KNNController implements InterfaceControlador{
    private InterfaceVista vista;
    private InterfaceModelo modelo;

    public KNNController(InterfaceVista vista, InterfaceModelo modelo)
    {
        this.vista = vista;
        this.modelo = modelo;
    }
    public void set(InterfaceVista vista, InterfaceModelo modelo)
    {
        this.vista = vista;
        this.modelo = modelo;
    }
    @Override
    public void loadData(String filename) throws IOException {
        modelo.loadData(filename);
        //vista.anulaCarga();
    }

    @Override
    public HashMap<String, List<List<Double>>> getListas() {
       return modelo.getListas();
    }

    @Override
    public List<String> getCabezera()  {
        return modelo.getCabezera();
    }

    @Override
    public List<String> loadLabels(){
        return modelo.getLabels();
    }
    @Override
    public HashMap<String,List<List<Double>>> loadGrupos(){
        return modelo.getListas();
    }
    @Override
    public String getGroupM(){
        return modelo.getGroupM();
    }
    @Override
    public String getGroupE(){
        return modelo.getGroupE();
    }
    @Override
    public void estimateParams() {

    }
    @Override
    public void crearPunto(String punto){
        modelo.crearPunto(punto);
    }
}
