import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;


public class KNNView implements InterfaceVista{
    private InterfaceControlador controlador;
    private InterfaceModelo modelo;
    private Button buscadorCsv = new Button("Cargar dataset");
    private ScatterChart grafica;
    private Stage stage;
    public KNNView( InterfaceControlador controlador, InterfaceModelo modelo) {
        this.controlador = controlador;
        this.modelo = modelo;
    }
    public void set(InterfaceControlador controlador, InterfaceModelo modelo) {
        this.controlador = controlador;
        this.modelo = modelo;
    }

    public void create(Stage primaryStage) {
        this.stage=primaryStage;
        stage.setTitle("Hello World!");
        StackPane root = new StackPane();
        root.getChildren().add(buscadorCsv);
        stage.setScene(new Scene(root, 250, 250));
        stage.show();
        buscadorCsv.setOnAction( e -> {
            try {
                cargarDatos();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
    }


    void cargarDatos() throws IOException {
        FileChooser fs = new FileChooser();
        File file = fs.showOpenDialog(null);
        if (file!=null){
            controlador.loadData(file.getAbsolutePath());
        }

    }

    @Override
    public void datosDisponibles() {
        Label entrada = new Label();
        Text pertenece = new Text("Label");
        List<String> tiposCabezera = controlador.getCabezera();
        this.grafica = getGrafica(0,1);
        tiposCabezera.remove(tiposCabezera.size()-1);
        ObservableList<String> labels = FXCollections.observableList(tiposCabezera);
        List<String> lista = new ArrayList<String>();
        lista.add("Manhattan");
        lista.add("Euclidean");
        ObservableList<String> metodos = FXCollections.observableList(lista);
        Button aceptar = new Button("Aceptar");
        Button fichero = new Button("Nuevo Fichero");
        javafx.scene.control.TextField textField = new javafx.scene.control.TextField();
        textField.setText("Escribe un punto");
        ComboBox combox = new ComboBox<>(labels);
        ComboBox comboy = new ComboBox<>(labels);
        ComboBox metodo= new ComboBox<>(metodos);
        combox.getSelectionModel().select(0);
        comboy.getSelectionModel().select(1);
        metodo.getSelectionModel().select(0);
        actualizarGrafica(combox,comboy,aceptar,textField,fichero,metodo,pertenece);
        stage.show();
        combox.setOnAction(e->{
            this.grafica = getGrafica(combox.getSelectionModel().getSelectedIndex(), comboy.getSelectionModel().getSelectedIndex());
            actualizarGrafica(combox,comboy,aceptar,textField,fichero,metodo,pertenece);
            stage.show();
        });
        comboy.setOnAction(e->{
            this.grafica = getGrafica(combox.getSelectionModel().getSelectedIndex(), comboy.getSelectionModel().getSelectedIndex());
            actualizarGrafica(combox,comboy,aceptar,textField,fichero,metodo,pertenece);
            stage.show();
        });
        aceptar.setOnAction(e->{
            XYChart.Series datosGrafica = new XYChart.Series();
            datosGrafica.getData().add(new XYChart.Data(getNumero(textField.getText(),combox.getSelectionModel().getSelectedIndex()),getNumero(textField.getText(),comboy.getSelectionModel().getSelectedIndex())));
            this.grafica.getData().add(datosGrafica);
            pertenece.setText(controlador.getGroupM());
            modelo.crearPunto(textField.getText());
            stage.show();
        });

        fichero.setOnAction(e->{create(this.stage);});


    }

    public double getNumero(String texto,int eje){
        char[] text = texto.toCharArray();
        StringBuilder num = new StringBuilder();
        int iteraciones = 0;
        for (int i = 0;i<text.length;i++){
            if (text[i]==','){
                if (iteraciones==eje){
                    return Double.parseDouble(num.toString());
                }else{
                     num = new StringBuilder();
                    iteraciones++;
                }
            }else{
                num.append(text[i]);
            }
        }
        return Double.parseDouble(num.toString());
    }

    public ScatterChart getGrafica(int ejex, int ejey){
        NumberAxis xAxis = new NumberAxis();
        NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel(controlador.getCabezera().get(ejex));
        yAxis.setLabel(controlador.getCabezera().get(ejey));
        ScatterChart grafica = new ScatterChart(xAxis,yAxis);
        HashMap<String, List<List<Double>>> gruposLabels = controlador.getListas();
        for (int i = 0; i < controlador.loadLabels().size(); i++) {
            XYChart.Series datosGrafica = new XYChart.Series();
            for (List<Double> lista0 : gruposLabels.get(controlador.loadLabels().get(i))) {
                datosGrafica.getData().add(new XYChart.Data(lista0.get(ejex), lista0.get(ejey)));
            }
            grafica.getData().add(datosGrafica);
        }
        return grafica;
    }

    void actualizarGrafica(ComboBox combox, ComboBox comboy, Button aceptar, javafx.scene.control.TextField textField,Button fichero,ComboBox metodo,Text pertenece){
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(100, 0, 0, 100));
        Text x = new Text("Eje X");
        Text y = new Text("Eje y");
        gridPane.add(x, 0, 3);
        gridPane.add(combox, 1, 3);
        gridPane.add(y, 0, 0);
        gridPane.add(comboy, 1, 0);
        if(textField.getText().matches(".*\\d.*")){
            XYChart.Series datosGrafica = new XYChart.Series();
            datosGrafica.getData().add(new XYChart.Data(getNumero(textField.getText(),combox.getSelectionModel().getSelectedIndex()),getNumero(textField.getText(),comboy.getSelectionModel().getSelectedIndex())));
            this.grafica.getData().add(datosGrafica);
            if (Objects.equals(metodo.getTypeSelector(), "Manhattan")){
                pertenece.setText(controlador.getGroupE());
            }else {
                pertenece.setText(controlador.getGroupM());

            }
        }
        gridPane.add(this.grafica, 2, 0);
        gridPane.add(new javafx.scene.control.Label("Introduce un Punto: "),0,5);
        gridPane.add(textField,1,5);
        gridPane.add(aceptar,2,5);
        gridPane.add(metodo,0,6);
        gridPane.add(pertenece,1,6);
        gridPane.add(fichero,0,7);
        stage.setScene(new Scene(gridPane, 800, 800));
    }
}

