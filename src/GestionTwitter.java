import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class GestionTwitter {
	private static Map<String, UsuarioTwitter> usuariosPorId = new HashMap<>();
	private static Map<String, UsuarioTwitter> usuariosPorNick = new HashMap<>();

	public static void main(String[] args) {
		try {
			String fileName = "data2.csv";
			CSV.processCSV(new File(fileName));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static boolean agregarUsuarioSiNoExiste(UsuarioTwitter usuario) {
		if (usuariosPorId.containsKey(usuario.getId())) {
			System.err.println("Usuario duplicado con id: " + usuario.getId());
			return false;
		} else {
			usuariosPorId.put(usuario.getId(), usuario);
			// Aquí también intentamos agregar por nickname
			return agregarUsuarioPorScreenName(usuario);
		}
	}

	public static boolean agregarUsuarioPorScreenName(UsuarioTwitter usuario) {
		if (usuariosPorNick.containsKey(usuario.getScreenName())) {
			System.err.println("Usuario duplicado con nick: " + usuario.getScreenName());
			return false;
		} else {
			usuariosPorNick.put(usuario.getScreenName(), usuario);
			System.out.println("Usuario agregado con nick: " + usuario.getScreenName());
			return true;
		}
	}

	public static UsuarioTwitter buscarUsuarioPorScreenName(String screenName) {
		return usuariosPorNick.get(screenName);
	}

	public static UsuarioTwitter buscarUsuarioPorId(String id) {
		return usuariosPorId.get(id);
	}
}
