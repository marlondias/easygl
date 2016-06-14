package marlon.engine.old;

public class GameLoop implements Runnable {
    private boolean running = false; //Permite a execução do gameloop
    private GameGraphics graphics; //Sub-sistema gráfico
    private GameLogic logic; //Sub-sistema lógico/matemático


    private void init(){
    	//Inicializa os sub-sistemas
    	graphics = new GameGraphics();
    	logic = new GameLogic();
    }

    public void start(){
		running = true;
		new Thread(this, "GameLoop Thread").start();
    }
    
    public void stop(){
        running = false; //termina o gameloop
        graphics.terminate();
    }

    public void run() {
        init();
        
    	final long SECOND_TO_NS = 1000000000; //1 segundo convertido em nanosegundos
        final int TARGET_FPS = 60; //fps desejado
        final double NS_PER_UPDATE = (double)(SECOND_TO_NS / TARGET_FPS);
        int ups = 0; //updates per second
        int fps = 0; //frames per second
        double delta = 0; //diferença entre o tempo atual e a ultima atualização
        long time_lastLoop = System.nanoTime(); //marca quando ocorreu o ultimo loop do WHILE abaixo
        long time_lastSecond = 0L; //usado para exibir as informações a cada 1 segundo
        
        while (running){
            long time_now = System.nanoTime();
            delta += (time_now - time_lastLoop) / NS_PER_UPDATE; //acumula a diferença de tempo para atualizar
            time_lastLoop = time_now; //o tempo atual será usado na proxima iteração
            
            boolean renderNow = false; //indica se deve atualizar a tela (true = ultrapassa fps desejado)
            while(delta >= 1){
                delta -= 1;
                logic.update();
                ups++;
                renderNow = true;
            }
            
            //diminui a performance de propósito (TODO: retirar depois dos testes)
            try {Thread.sleep(7);} catch (InterruptedException e) {e.printStackTrace();}

            if (renderNow){
                graphics.render();
            	fps++;
            }
            
            if (time_lastSecond == 0L) time_lastSecond = System.nanoTime();
            else if (System.nanoTime() - time_lastSecond >= SECOND_TO_NS){
                //Passou-se 1 segundo..
            	graphics.showFPS(ups, fps);
            	time_lastSecond += SECOND_TO_NS;
                ups = 0;
                fps = 0;
            }
            
        	if (graphics.shouldTerminate()) stop();
        }
    }

}
