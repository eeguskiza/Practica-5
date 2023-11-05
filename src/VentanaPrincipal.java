import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class VentanaPrincipal extends JFrame {
    private JTextArea textArea1;
    private JFileChooser fileChooser;
    private JButton cargarButton;
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
        cargarButton = new JButton("Cargar Archivo");
        cargarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fileChooser = new JFileChooser();
                fileChooser.setCurrentDirectory(new java.io.File(properties.getProperty("DEFAULT_PATH")));
                fileChooser.setDialogTitle("Seleccione el archivo a cargar");
                fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

                int result = fileChooser.showOpenDialog(VentanaPrincipal.this);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    if (selectedFile != null) {
                        try {
                            CSV.processCSV(selectedFile);
                            textArea1.setText("Archivo cargado correctamente:\n" + selectedFile.getAbsolutePath());
                        } catch (Exception ex) {
                            ex.printStackTrace();
                            textArea1.setText("Error al cargar el archivo: " + ex.getMessage());
                        }
                    }
                }
            }
        });

        // Configuración Ventana
        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);
        add(cargarButton, BorderLayout.SOUTH);

        // Mostrar Ventana
        setVisible(true);
    }

    private void cargarPropiedades() {
        try {
            properties.load(new FileInputStream(PROPERTIES_FILE));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                VentanaPrincipal ventana = new VentanaPrincipal();
            }
        });
    }
}
