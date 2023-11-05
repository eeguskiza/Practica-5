import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.*;
import java.util.Properties;

public class VentanaPrincipal extends JFrame {
    private JTextArea textArea1;
    private JFileChooser fileChooser;
    private Properties properties;
    private final String PROPERTIES_FILE = "config.properties";

    public VentanaPrincipal() {
        setTitle("Gestión de Usuarios de Twitter");
        setSize(700, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Carga de propiedades
        properties = new Properties();
        cargarPropiedades();

        // Text Area para mostrar los resultados
        textArea1 = new JTextArea(20, 40);
        textArea1.setEditable(false);

        // ScrollPane para el TextArea
        JScrollPane scrollPane = new JScrollPane(textArea1);

        // Botón para cargar el archivo
        JButton cargarButton = new JButton("Cargar Archivo");
        cargarButton.addActionListener(e -> cargarArchivo());

        // Configuración Ventana
        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);
        add(cargarButton, BorderLayout.SOUTH);

        // Mostrar Ventana
        setVisible(true);
    }

    private void cargarArchivo() {
        fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(properties.getProperty("DEFAULT_PATH", System.getProperty("user.home"))));
        fileChooser.setDialogTitle("Seleccione el archivo a cargar");
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            if (selectedFile != null) {
                SwingWorker<Void, String> worker = new SwingWorker<Void, String>() {
                    @Override
                    protected Void doInBackground() throws Exception {
                        CSV.processCSV(selectedFile);
                        return null;
                    }

                    @Override
                    protected void done() {
                        try {
                            // Actualiza el textArea en el EDT después de que el procesamiento ha finalizado
                            updateTextArea();
                            System.out.println("Archivo cargado correctamente:\n" + selectedFile.getAbsolutePath());

                            // Guardar la ruta del archivo procesado en propiedades
                            properties.setProperty("DEFAULT_PATH", selectedFile.getParent());
                            guardarPropiedades();
                        } catch (Exception e) {
                            textArea1.append("Error al cargar el archivo: " + e.getMessage() + "\n");
                        }
                    }
                };
                worker.execute();
            }
        }
    }


    private void cargarPropiedades() {
        try {
            FileInputStream in = new FileInputStream(PROPERTIES_FILE);
            properties.load(in);
            in.close();
        } catch (IOException e) {
            textArea1.append("Error al cargar el archivo de propiedades: " + e.getMessage() + "\n");
        }
    }

    private void guardarPropiedades() {
        try {
            FileOutputStream out = new FileOutputStream(PROPERTIES_FILE);
            properties.store(out, "Última ruta de archivo usada");
            out.close();
        } catch (IOException e) {
            textArea1.append("Error al guardar el archivo de propiedades: " + e.getMessage() + "\n");
        }
    }

    public void updateTextArea() {
        String data = CSV.getProcessedDataAsString(); // Obtiene los datos del CSV como String
        textArea1.setText(data); // Establece el texto del TextArea
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(VentanaPrincipal::new);
    }
}
