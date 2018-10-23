package arjierdagames.gameloop;

import android.annotation.SuppressLint;
import android.graphics.Canvas;

import java.util.Calendar;

public class CicloJuego extends Thread{

    static final long FPS = 30;
    private VistaJuego view;
    private int lastSecond;
    private boolean running = false;

    public CicloJuego(VistaJuego view)
    {
        this.view = view;
    }

    public void setRunning(boolean run)
    {
        running = run;
    }

    private boolean CheckTime()	//regresa true si cada segundo que pasa
    {
        Calendar c= Calendar.getInstance();
        int seconds= c.get(Calendar.SECOND);
        if (lastSecond!=seconds)
        {
            lastSecond=seconds;
            return true;
        }
        return false;
    }

    @Override
    @SuppressLint("WrongCall")
    public void run()
    {
        //Declaramos para controlar FPS
        long ticksPS = 1000 / FPS;
        long startTime;
        long sleepTime;
        while (running)
        {
            //Obtenemos reloj
            Canvas c = null;
            startTime = System.currentTimeMillis();

            try
            {
                //Obtenemos canvas y sincronizamos
                c = view.getHolder().lockCanvas();
                synchronized (view.getHolder())
                {
                    view.onDraw(c);
                }
            }
            finally
            {
                //Liberamos //Permitir que se dibuje
                if (c != null)
                {
                    view.getHolder().unlockCanvasAndPost(c);
                }
            }

            //Calculamos tiempo
            sleepTime = ticksPS-(System.currentTimeMillis() - startTime);
            //Forzamos que solo corra a los FPS asignados
            try
            {
                if (sleepTime > 0)
                    sleep(sleepTime);
                else
                    sleep(10);
            } catch (Exception e) {}

        }
    }
}