import java.io.File;
import java.util.*;

public class GestionTwitter {
	private static Map<String, UsuarioTwitter> usuariosPorId = new HashMap<>();
	private static Map<String, UsuarioTwitter> usuariosPorNick = new HashMap<>();

	public static void main(String[] args) {
		try {
			String fileName = "/Users/erikeguskiza/Desktop/data.csv";
			//String fileName = "data2.csv";
			CSV.processCSV(new File(fileName));
		} catch (Exception e) {
			e.printStackTrace();
		}
		analizarAmigosUsuarios();
		analizarAmigosUsuarios2();
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

	public static void analizarAmigosUsuarios() {
		// Usaremos un TreeMap para ordenar automáticamente los usuarios por nick
		TreeMap<String, UsuarioTwitter> ordenadoPorNick = new TreeMap<>(usuariosPorNick);

		int totalUsuariosConAmigosDentro = 0;

		for (UsuarioTwitter usuario : ordenadoPorNick.values()) {
			List<String> amigosIds = usuario.getFriends();
			int amigosDentro = 0;
			int amigosFuera = 0;

			for (String amigoId : amigosIds) {
				if (usuariosPorId.containsKey(amigoId)) {
					amigosDentro++;
				} else {
					amigosFuera++;
				}
			}

			// Solo mostramos los usuarios que tienen amigos dentro del sistema
			if (amigosDentro > 0) {
				System.out.println("Usuario " + usuario.getScreenName() + " tiene " +
						amigosFuera + " amigos fuera de nuestro sistema y " +
						amigosDentro + " dentro.");
				totalUsuariosConAmigosDentro++;
			}
		}

		System.out.println("Hay " + totalUsuariosConAmigosDentro + " con algunos amigos dentro de nuestro sistema.");
	}


	public static void analizarAmigosUsuarios2() {
		// Creamos un TreeSet con un Comparator personalizado
		Comparator<UsuarioTwitter> comparador = new Comparator<UsuarioTwitter>() {
			@Override
			public int compare(UsuarioTwitter u1, UsuarioTwitter u2) {
				int amigosDentroU1 = contarAmigosDentro(u1);
				int amigosDentroU2 = contarAmigosDentro(u2);

				// Primero comparamos por la cantidad de amigos dentro del sistema, en orden descendente
				if (amigosDentroU1 != amigosDentroU2) {
					return Integer.compare(amigosDentroU2, amigosDentroU1);
				}

				// Si tienen la misma cantidad de amigos dentro, comparamos por nick
				return u1.getScreenName().compareTo(u2.getScreenName());
			}

			// Función auxiliar para contar los amigos dentro del sistema
			private int contarAmigosDentro(UsuarioTwitter usuario) {
				int amigosDentro = 0;
				for (String amigoId : usuario.getFriends()) {
					if (usuariosPorId.containsKey(amigoId)) {
						amigosDentro++;
					}
				}
				return amigosDentro;
			}
		};

		Set<UsuarioTwitter> ordenadoPorAmigosYNick = new TreeSet<>(comparador);

		for (UsuarioTwitter usuario : usuariosPorNick.values()) {
			ordenadoPorAmigosYNick.add(usuario);
		}

		// Imprimir los usuarios del TreeSet
		for (UsuarioTwitter usuario : ordenadoPorAmigosYNick) {
			System.out.println(usuario.getScreenName() + " - " +
					contarAmigosDentro(usuario) + " amigos.");
		}
	}

	// Función auxiliar para contar los amigos dentro del sistema
	private static int contarAmigosDentro(UsuarioTwitter usuario) {
		int amigosDentro = 0;
		for (String amigoId : usuario.getFriends()) {
			if (usuariosPorId.containsKey(amigoId)) {
				amigosDentro++;
			}
		}
		return amigosDentro;
	}


}
