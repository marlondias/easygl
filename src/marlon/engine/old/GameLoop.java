package marlon.engine.old;

import marlon.engine.GLFWWindow_Wrapper;

public class GameLoop implements Runnable {
    private boolean running = false; //condição para o gameloop
    
	private GLFWWindow_Wrapper janela1 = new GLFWWindow_Wrapper(800, 600, "Aqui estoy!", true);

    
    
    public GameLoop(){
    	
    }
    
    
    public void init(){
    	janela1.setPosition(10, 10);
    	janela1.setVisible(true);
    }
    
    public synchronized void start(){
        running = true; //permite o inicio do gameloop
        new Thread(this).start(); //chama o run() do objeto da thread, nesse caso o run() abaixo
    }
    
    public synchronized void stop(){
        running = false; //termina o gameloop
    }
    
    public void run() {
        final int TARGET_FPS = 60; //fps desejado
        final double NS_PER_UPDATE = (double)(1000000000 / TARGET_FPS);
        int ups = 0; //updates per second
        int fps = 0; //frames per second
        double delta = 0; //diferença entre o tempo atual e a ultima atualização
        long time_lastLoop = System.nanoTime(); //marca quando ocorreu o ultimo loop do WHILE abaixo
        long time_lastSecond = System.nanoTime(); //usado para exibir as informações a cada 1 segundo
        
        init(); //chama a titleScreen
        
        while (running){
            long time_now = System.nanoTime();
            time_lastLoop = time_now; //atualiza o instante do ultimo loop (este)
            
            delta += (time_now - time_lastLoop) / NS_PER_UPDATE; //acumula a diferença de tempo (divisão??)
            
            boolean renderNow = true; //indica se deve atualizar a tela (true = ultrapassa fps desejado)
            while(delta >= 1){
                delta -= 1;
                ups++;
                GameLogic.update();
                renderNow = true;
            }
            
            //diminui a performance de propósito (retirar depois dos testes)
            try {Thread.sleep(3);} catch (InterruptedException e) {e.printStackTrace();}
            
            if (renderNow){
                fps++;
                GameGraphics.render();
            }
            
            if (System.nanoTime() - time_lastSecond >= 1000000000){
                //Passou 1 segundo
            	time_lastSecond += 1000000000; //soma mais 1 segundo na variavel
            	//TODO: trocar
            	//this.frame.setTitle("UPS: " + ups + " FPS: " + fps);
                ups = 0; //zera tudo pra usar no proximo segundo
                fps = 0;
            }    
        }
    }

}
