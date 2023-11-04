import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class GestionTwitter {
	private static Map<String, UsuarioTwitter> usuariosMap = new HashMap<>();

	public static void main(String[] args) {
		try {
			String fileName = "/Users/erikeguskiza/data.csv";
			CSV.processCSV( new File( fileName ) );
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static boolean agregarUsuarioSiNoExiste(UsuarioTwitter usuario) {
		if (usuariosMap.containsKey(usuario.getId())) {
			System.err.println("Usuario duplicado con id: " + usuario.getId());
			return false;
		} else {
			usuariosMap.put(usuario.getId(), usuario);
			return true;
		}
	}

}
