package vista;

import java.awt.EventQueue;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import controlador.EventoController;
import modelo.Evento;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import javax.swing.JRadioButton;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class formEventos extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private DefaultTableModel tabla;
	private JTextField txtNombre;
	private JTextField txtFecha;
	private JTextField txtLugar;
	private JRadioButton rdbtnSi;
	private JRadioButton rdbtnNo;
	private JButton btnNuevo;
	private JButton btnGuardar;
	private JButton btnCancelar;
	private JButton btnEliminar;
	private JComboBox cmbHoraInicio;
	private JComboBox cmbAMPMinicio;
	private JComboBox cmbHoraFinal;
	private JComboBox cmbAMPMfinal;
	private JComboBox cmbEstado;
	private JComboBox cmbTipo;
	private JRadioButton rdbtnBoleteriaNo;
	private JRadioButton rdbtnBoleteriaSi;
	private int codigoEventoSeleccionado;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					formEventos frame = new formEventos();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public formEventos() {
		

		
		tabla = new DefaultTableModel(
                new String [][] {
                    {"Nombre", 
                        "Tipo", 
                        "Fecha", 
                        "Hora inicial", 
                        "Hora final",
                        "Lugar",
                        "Boletería",
                        "Estado"},
                },
                new String [] {
                        "Nombre", 
                        "Tipo", 
                        "Fecha", 
                        "Hora inicial", 
                        "Hora final",
                        "Lugar",
                        "Boletería",
                        "Estado",
                            }) 
                {
                    @Override
                    public boolean isCellEditable(int row, int column) {
                    return false; // Las celdas no serán editables
                                    }
                };
        limpiarDatosTabla();
        cargarDatosTablaMYSQL();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 610, 716);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblLogo = new JLabel("New label");
		lblLogo.setBounds(151, 12, 277, 99);
		contentPane.add(lblLogo);
		//cargamos el logo
		Icon nuevoLogo = new ImageIcon(getClass().getResource("/imagenes/Logo.png"));
		lblLogo.setIcon(nuevoLogo);
		
		//DATOS DE LA TABLA
		table = new JTable(tabla);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int i = table.getSelectedRow();
				codigoEventoSeleccionado = (int) tabla.getValueAt(i, 0);
				
				btnGuardar.setText("Actualizar");
				btnCancelar.setVisible(true);
				btnEliminar.setVisible(true);
				
				txtNombre.setText(tabla.getValueAt(i, 1).toString());
				txtFecha.setText(tabla.getValueAt(i, 3).toString());
				txtLugar.setText(tabla.getValueAt(i, 6).toString());
				txtNombre.requestFocus();
				
				cambiarEstadoObjetos(true);
			}
		});
		table.setBorder(new LineBorder(new Color(0, 0, 0)));
		table.setBounds(12, 126, 580, 182);
		contentPane.add(table);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(12, 320, 70, 15);
		contentPane.add(lblNombre);
		
		JLabel lblTIpo = new JLabel("Tipo");
		lblTIpo.setBounds(12, 344, 70, 15);
		contentPane.add(lblTIpo);
		
		JLabel lblFecha = new JLabel("Fecha");
		lblFecha.setBounds(12, 370, 70, 15);
		contentPane.add(lblFecha);
		
		JLabel lblHoraInicio = new JLabel("Hora Inicio");
		lblHoraInicio.setBounds(12, 397, 75, 15);
		contentPane.add(lblHoraInicio);
		
		JLabel lblHoraFinal = new JLabel("Hora Final");
		lblHoraFinal.setBounds(12, 424, 75, 15);
		contentPane.add(lblHoraFinal);
		
		JLabel lblLugar = new JLabel("Lugar");
		lblLugar.setBounds(12, 451, 70, 15);
		contentPane.add(lblLugar);
		
		JLabel lblBoleteria = new JLabel("Boleteria");
		lblBoleteria.setBounds(12, 493, 70, 15);
		contentPane.add(lblBoleteria);
		
		JLabel lblEstado = new JLabel("Estado");
		lblEstado.setBounds(12, 537, 70, 15);
		contentPane.add(lblEstado);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(97, 319, 495, 19);
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);
		
		cmbTipo = new JComboBox();
		cmbTipo.setEnabled(false);
		cmbTipo.setModel(new DefaultComboBoxModel(new String[] {"C: Concierto", "D: Desfile"}));
		cmbTipo.setBounds(97, 339, 495, 24);
		contentPane.add(cmbTipo);
		
		txtFecha = new JTextField();
		txtFecha.setBounds(97, 369, 495, 19);
		contentPane.add(txtFecha);
		txtFecha.setColumns(10);
		
		cmbHoraInicio = new JComboBox();
		cmbHoraInicio.setEnabled(false);
		cmbHoraInicio.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"}));
		cmbHoraInicio.setBounds(97, 392, 371, 24);
		contentPane.add(cmbHoraInicio);
		
		cmbAMPMinicio = new JComboBox();
		cmbAMPMinicio.setEnabled(false);
		cmbAMPMinicio.setModel(new DefaultComboBoxModel(new String[] {"am", "pm"}));
		cmbAMPMinicio.setBounds(506, 387, 86, 24);
		contentPane.add(cmbAMPMinicio);
		
		cmbHoraFinal = new JComboBox();
		cmbHoraFinal.setEnabled(false);
		cmbHoraFinal.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"}));
		cmbHoraFinal.setBounds(98, 420, 370, 24);
		contentPane.add(cmbHoraFinal);
		
		cmbAMPMfinal = new JComboBox();
		cmbAMPMfinal.setEnabled(false);
		cmbAMPMfinal.setModel(new DefaultComboBoxModel(new String[] {"am", "pm"}));
		cmbAMPMfinal.setBounds(507, 415, 84, 24);
		contentPane.add(cmbAMPMfinal);
		
		txtLugar = new JTextField();
		txtLugar.setBounds(97, 452, 495, 19);
		contentPane.add(txtLugar);
		txtLugar.setColumns(10);
		
		rdbtnBoleteriaSi = new JRadioButton("Si");
		rdbtnBoleteriaSi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				rdbtnBoleteriaSi.setSelected(true);
				rdbtnBoleteriaNo.setSelected(false);
			}
		});
		rdbtnBoleteriaSi.setBackground(new Color(255, 255, 255));
		rdbtnBoleteriaSi.setBounds(97, 477, 149, 23);
		contentPane.add(rdbtnBoleteriaSi);
		
		rdbtnBoleteriaNo = new JRadioButton("No");
		rdbtnBoleteriaNo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				rdbtnBoleteriaSi.setSelected(false);
				rdbtnBoleteriaNo.setSelected(true);
			}
		});
		rdbtnBoleteriaNo.setBackground(new Color(255, 255, 255));
		rdbtnBoleteriaNo.setBounds(98, 506, 149, 23);
		contentPane.add(rdbtnBoleteriaNo);
		
		cmbEstado = new JComboBox();
		cmbEstado.setEnabled(false);
		cmbEstado.setModel(new DefaultComboBoxModel(new String[] {"Programado", "Ejecutado"}));
		cmbEstado.setBounds(100, 534, 492, 24);
		contentPane.add(cmbEstado);
		
		//ACA INICIA EL PROCESO DE GUARDADO DE DATOS QUE INGRESA EL USUARIO
		btnGuardar = new JButton("Nuevo");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(btnGuardar.getText() == "Nuevo") {
					cambiarEstadoObjetos(true);
					btnGuardar.setText("Guardar");
					btnCancelar.setEnabled(true);
				}else if(btnGuardar.getText()=="Guardar") {
					//GUARDO TIPO DE EVENTO QUE SE SELECIONO
					String tipoEvento = "";
					if(cmbTipo.getSelectedIndex()==0) {
						tipoEvento = "C";
					}else {
						tipoEvento = "D";
					}
					//GUARDO DATOS DE LA HORA EN VARIABLES String PARA ENVIARLAS AL CONTROLADOR
					String horaInicio = cmbHoraInicio.getSelectedItem().toString() + cmbAMPMinicio.getSelectedItem();
					String horaFinal = cmbHoraFinal.getSelectedItem().toString() + cmbAMPMfinal.getSelectedItem().toString();
					//GUARDO BOLETA QUE SE SELECCIONO
					String boleteria = "";
					if(rdbtnBoleteriaSi.isSelected()) {
						boleteria = "Si";
					}else {
						boleteria = "No";
					}
					System.out.println("empieza el proceso de guardado");
					//INSTANCIA DE EventoController para poder llamar el metodo GuardarEvento
					EventoController miNuevoEvento = new EventoController();
					//ACA OBTENGO LA INFORMACION QUE EL USUARIO INGRESO
					boolean respuestaFinal = miNuevoEvento.guardarEvento(txtNombre.getText(), 
																	     tipoEvento,
																	     txtFecha.getText(),
																	     horaInicio,
																	     horaFinal,
																	     txtLugar.getText(),
																	     boleteria, 
																	     cmbEstado.getSelectedItem().toString());
					if (respuestaFinal) {
						JOptionPane.showMessageDialog(null, "Datos guardados correctamente");
					}else {
						JOptionPane.showMessageDialog(null, "Error interno no se guardaron los datos correctamente");
					}
					borronyCuentaNueva();
					//¿¿¿¿¿¿¿¿¿¿¿¿¿¿¿¿DUDA?????????????????????????????????????????
					limpiarDatosTabla();
					//ESTO ES PARA MOSTRAR LOS DATOS DE SQL EN LA TABLA DE EL FORMULARIO
					cargarDatosTablaMYSQL();
				}else if(btnGuardar.getText() == "Actualizar") {
					String tipoEvento = "";
					if (cmbTipo.getSelectedIndex()==0) {
						tipoEvento = "C";
					}else {
						tipoEvento = "D";
					}
					String horaInicio = cmbHoraInicio.getSelectedItem().toString() + cmbAMPMinicio.getSelectedItem().toString();
					String horaFinal = cmbHoraFinal.getSelectedItem().toString() + cmbAMPMfinal.getSelectedItem().toString();
					String boleteria = "";
					if(rdbtnBoleteriaSi.isSelected()) {
						boleteria = "Si"	;
					}else {
						boleteria = "No"	;
					}
					System.out.println("Empieza el proceso de actualizar");
					EventoController miNuevoEvento = new EventoController();
					boolean respuestaFinal = miNuevoEvento.actualizarEvento(
																			codigoEventoSeleccionado,
																			txtNombre.getText(),
																			tipoEvento,
																			txtFecha.getText(),
																			horaInicio,
																			horaFinal,
																			txtLugar.getText(),
																			boleteria,
																			cmbEstado.getSelectedItem().toString()																			
																		);
					if (respuestaFinal) {
						JOptionPane.showMessageDialog(null, "Datos actualizados correctamente");
					}else {
						JOptionPane.showMessageDialog(null, "Error interno");	
					}
					
					borronyCuentaNueva();
					limpiarDatosTabla();
					//¿¿¿¿NO ME QUEDA DEL TODO CLARO??????????????
					cargarDatosTablaMYSQL();
				}
			}
		});
		
		
		
		
		btnGuardar.setBounds(97, 613, 117, 25);
		contentPane.add(btnGuardar);
		
		//BOTON ELMININAR
		btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				EventoController borrarEvento = new EventoController();
				borrarEvento.EliminarEvento(codigoEventoSeleccionado);
				limpiarDatosTabla();
				cargarDatosTablaMYSQL();
				btnEliminar.setEnabled(false);
				borronyCuentaNueva();
			}
		});
		btnEliminar.setVisible(false);
		btnEliminar.setBounds(266, 613, 117, 25);
		contentPane.add(btnEliminar);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setEnabled(false);
		btnCancelar.setBounds(444, 613, 117, 25);
		contentPane.add(btnCancelar);
	}
	
	private void cambiarEstadoObjetos(boolean estado) {
        txtFecha.setEnabled(estado);
        txtLugar.setEnabled(estado);
        txtNombre.setEnabled(estado);
        cmbHoraInicio.setEnabled(estado);
        cmbAMPMinicio.setEnabled(estado);
        cmbHoraFinal.setEnabled(estado);
        cmbAMPMfinal.setEnabled(estado);
        cmbEstado.setEnabled(estado);
        cmbTipo.setEnabled(estado);
        rdbtnBoleteriaNo.setEnabled(estado);
        rdbtnBoleteriaSi.setEnabled(estado);
    }
	
	private void borronyCuentaNueva() {
        cambiarEstadoObjetos(false);
        btnGuardar.setText("Nuevo");
        btnCancelar.setVisible(false);
        txtFecha.setText("");
        txtLugar.setText("");
        txtNombre.setText("");
    }
	
	//¿DUDA CON ESTA PARTE DEL CODIGO?
    private void limpiarDatosTabla() {
        tabla.setRowCount(1);
    }
    
    //ESTO ES PARA PASAR LOS DATOS DE SQL A JAVA Y QUE SE PUEDAN INTERPRETAR
	private void cargarDatosTablaMYSQL() {
		//INSTANCIA PARA CARGAR CADA EVENTO EN LA TABLA
		EventoController ListaEventos = new EventoController();
		ArrayList<Evento> info = ListaEventos.mostrarEventos();
		//¿NO COMPRENDO BIEN PRQUE EL VECTOR OBJECT?
		for (Evento datos : info) {
			Object[] fila = new Object[9];
			fila[0]=datos.getCodigoEvento();
			fila[1]=datos.getNombre();
			fila[2]=datos.getTipoEvento();
			fila[3]=datos.getFecha();
			fila[4]=datos.getHoraInicio();
			fila[5]=datos.getHoraFinal();
			fila[6]=datos.getLugar();
			fila[7]=datos.getBoleteria();
			fila[8]=datos.getEstado();
			tabla.addRow(fila);
		}
	}
}
