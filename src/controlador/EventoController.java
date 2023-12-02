package controlador;
import java.sql.Connection;
import java.util.ArrayList;

import controlador.conexionBD;
import modelo.Evento;

public class EventoController {
	private Connection conexionactiva;
	
	public EventoController() {
		conexionBD baseDatos = new conexionBD();
		conexionactiva = baseDatos.abrirBaseDatos();
	}
	
	public boolean guardarEvento(String nombre, String tipoEvento, String fecha, String horaInicio,
			String horaFinal, String lugar, String boleteria, String estado) {
		boolean respuestaControlador = true;
		//para realizar la conexion a la BD
        conexionBD baseDatos = new conexionBD();
        Connection conexionactiva = baseDatos.abrirBaseDatos();

        if(conexionactiva!=null) {
            Evento miEvento = new Evento(nombre,tipoEvento,fecha,horaInicio,
                     horaFinal,  lugar,  boleteria,  estado);
            boolean respuestaModelo = miEvento.agregarEvento(conexionactiva);
            if (respuestaModelo==true) {
                return respuestaControlador;
            }else {
                respuestaControlador = false;
                return respuestaControlador;
            }
        }
        else {
            respuestaControlador = false;
            return respuestaControlador;
        }
    }
	
	public boolean actualizarEvento(int codigoEvento, String nombre, String tipoEvento, String fecha, String horaInicio,
			String horaFinal, String lugar, String boleteria, String estado) {
		
		boolean respuestaControlador = true;
		
		conexionBD baseDatos = new conexionBD();
		Connection conexionactiva = baseDatos.abrirBaseDatos();
		
		if(conexionactiva!=null) {
			Evento miEvento = new Evento(codigoEvento, nombre,tipoEvento,fecha,horaInicio,
					 horaFinal,  lugar,  boleteria,  estado);
			boolean respuestaModelo = miEvento.actualizarEvento(conexionactiva);
			if (respuestaModelo==true) {
				return respuestaControlador;
			}else {
				respuestaControlador = false;
				return respuestaControlador;
			}
		}
		else {
			respuestaControlador = false;
			return respuestaControlador;			
		}
	}
	public void EliminarEvento(int codigoEVista) {
		//¿¿¿¿¿¿¿¿¿¿¿¿¿¿¿¿¿PORQUE LO PRIMERO ES LA CONEXION A LA BASE DE DATOS ¿¿¿HAY QUE ENVIARSELA AL MODELO SIEMPRE???????????????????????????????
		conexionBD baseDatos = new conexionBD();
		Connection conexionactiva = baseDatos.abrirBaseDatos();
		Evento miEvento = new Evento();
		miEvento.EliminarEvento(codigoEVista,conexionactiva);
	}
	
	public ArrayList mostrarEventos() {
		//SE DEBE PONER EN EL CONSTRUCTOR DEL CONTROLADOR
		conexionBD baseDatos = new conexionBD();
		Connection conexionactiva = baseDatos.abrirBaseDatos();
		/*CREO OBJETO DE TIPO EVENTO PARA PODER LLAMAR EL METODO 
		  mostrarEvento de la clase Evento EL CUAL EJECUTA LA CONSULTA SELECT * FROM A LA BD*/
		Evento miEvento = new Evento();
		/* EL METODO mostrarEvento EXIGE QUE SE LE ENVIE DESDE EL CONTROLADOR 
		   LA CONEXION A BD Y RETORNA, LA INFORMACIÓN TRANSFORMADA DE SQL á JAVA*/
		return miEvento.mostrarEvento(conexionactiva);
	}
}