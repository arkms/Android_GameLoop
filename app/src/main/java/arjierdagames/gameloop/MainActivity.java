package arjierdagames.gameloop;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Window;

public class MainActivity extends AppCompatActivity {

    VistaJuego vistaJuego; //JUego
    AlertDialog alertDialog; //Creamos alerta al salir

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Full screen
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        vistaJuego=new VistaJuego(this);	//Inicializamos
        setContentView(vistaJuego);  //mostramos
    }

    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        //Para evitar salir de app sin querer con back, preguntamos si desea salir
        if (keyCode==KeyEvent.KEYCODE_BACK)
        {
            //PAUSAR JUEGO SI ES NECESARIO

            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
            //Mensaje de la alerta
            alertDialogBuilder.setMessage("¿Deseas salir del juego?");
            //Primero boton, texto y accion
            alertDialogBuilder.setPositiveButton("Si",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface arg0, int arg1) {
                            alertDialog.hide();
                            vistaJuego.LiberarJuego();
                            finish();
                        }
                    });
            //Indicamos accion del siguiente boton y texto
            alertDialogBuilder.setNegativeButton("No",new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //Despausar
                }
            });
            //Desactivamos que se pueda cancelar el dialogo (dar touch fuera del dialogo)
            alertDialogBuilder.setCancelable(false);
            //Creamos y mostramos
            alertDialog = alertDialogBuilder.create();
            alertDialog.show();

            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
