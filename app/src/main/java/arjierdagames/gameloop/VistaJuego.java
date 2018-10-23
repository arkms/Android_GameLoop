package arjierdagames.gameloop;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class VistaJuego extends SurfaceView{

    //Control de canvas
    private SurfaceHolder holder;
    private CicloJuego gameLoop;

    public VistaJuego(Context context)
    {
        super(context);
        setFocusable(true);
        SoundManager.initSounds(context, 0); //0 es numero de sonidos
        gameLoop = new CicloJuego(this); //Creamos hilo
        holder = getHolder(); //Obtenemos holder
        holder.addCallback(new SurfaceHolder.Callback() {
            //Tenemos que liberar y dejar de dibujar en canvas si de destruye (app deja de estar arriba)
            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                boolean retry = true;
                gameLoop.setRunning(false);
                while (retry)
                {
                    try
                    {
                        gameLoop.join();
                        retry = false;
                    }
                    catch (InterruptedException e)
                    {
                    }
                } //fin while (retry)
            }

            //Cada vez que se crea, volvemos a iniciar hio
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                gameLoop.setRunning(true);
                gameLoop.start();
            }

            //Solo lo dejamos aqui
            @Override
            public void surfaceChanged(SurfaceHolder holder, int format,
                                       int width, int height) {
            }
        });

        //CARGAR IMAGENES Y CREAR PLUMAS
    }

    //Touch
    public boolean onTouchEvent(MotionEvent event)
    {
        //(int)event.getX()
        //(int)event.getY()
        //event.getAction() == MotionEvent.ACTION_DOWN

        return super.onTouchEvent(event);
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        //Limpiar pantalla
        canvas.drawColor(Color.WHITE);
        //DIBUJAR

        Paint p = new Paint();
        p.setARGB(255, 0, 255, 0);
        canvas.drawCircle(100, 100, 100, p);
    }

    public void LiberarJuego()
    {
        SoundManager.cleanup();
    }
}