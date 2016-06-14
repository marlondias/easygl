package marlon.engine.old;

public class Launcher {
	private static GameLoop game = new GameLoop();

	public static void main(String[] args) {
		System.out.println("Iniciando loop de jogo..");
		game.start();
	}

}
