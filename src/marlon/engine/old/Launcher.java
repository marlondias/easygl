package marlon.engine.old;

public class Launcher {
	public static GameLoop GL;

	public static void main(String[] args) {
		System.out.println("Iniciando loop de jogo..");
		GL = new GameLoop();
        GL.start();
	}

}
