import javax.swing.JFrame;

public class VentanaPrincipal extends JFrame{
    public VentanaPrincipal(){
        super("Ventana Principal");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
    public static void main(String[] args) {
        VentanaPrincipal ventana = new VentanaPrincipal();
    }
}