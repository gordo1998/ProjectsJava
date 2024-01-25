package view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;

import java.awt.Color;
import java.sql.SQLException;
import java.util.ArrayList;

import controller.ControlAutores;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JSeparator;



public class EditDosAutor extends JFrame implements ActionListener, MouseListener{

	private JFrame frame;
	private JLabel lblTitulo;
	private JLabel lblNombre;
	private JLabel lblApellido;
	private JLabel lblSegundoApellido;
	private JLabel lblPais;
	private JTextField textNombre;
	private JTextField textApellido;
	private JTextField textSegundoApellido;
	private JComboBox textPais;
	private JButton enviar;
	private JButton volver;
	private String nombreAntiguo;
	private String apellidoAntiguo;
	private String segundoApellidoAntiguo;
	private String nombreNuevo;
	private String apellidoNuevo;
	private String segundoApellidoNuevo;
	private String pais;
	private ControlAutores gestionAutores;
	private JSeparator separator;
	private JSeparator separator_1;
	private JSeparator separator_2;
	private ArrayList<ImageIcon> iconosOriginales;
	private ArrayList<ImageIcon> iconosNuevos;

	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditDosAutor window = new EditDosAutor();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws SQLException 
	 */
	public EditDosAutor(String nombre, String apellido, String segundoApellido) throws SQLException {
		this.nombreAntiguo = nombre;
		this.apellidoAntiguo = apellido;
		this.segundoApellidoAntiguo = segundoApellido;
		this.pais = "";
		this.gestionAutores = new ControlAutores();
		initialize();
	}
	
	public EditDosAutor() throws SQLException {
		this.gestionAutores = new ControlAutores();
		initialize();
	}
	
	public void visualize() {
		this.frame.setVisible(true);
	}
	
	public void convertirIconos() {
		this.iconosOriginales = new ArrayList<>();
		this.iconosNuevos = new ArrayList<>();
		
		/*
		 * Podremos los iconos en este orden:
		 * - Volver
		 * - Añadir
		 */
		int width = 28;
		int heigth = 28;
		
		iconosOriginales.add(new ImageIcon("C:\\Users\\Usuario\\eclipse-workspace_tres\\PracticaFinalDam1\\Iconos\\atrasOrigin.png"));
		iconosOriginales.add(new ImageIcon("C:\\Users\\Usuario\\eclipse-workspace_tres\\PracticaFinalDam1\\Iconos\\editOrigin.png"));
		
		ArrayList<ImageIcon> iconoAzul = new ArrayList<>();
		iconoAzul.add(new ImageIcon("C:\\Users\\Usuario\\eclipse-workspace_tres\\PracticaFinalDam1\\Iconos\\atrasMod.png"));
		iconoAzul.add(new ImageIcon("C:\\Users\\Usuario\\eclipse-workspace_tres\\PracticaFinalDam1\\Iconos\\editMod.png"));
		
		Image[] imagenes = new Image[iconosOriginales.size()];
		
		for (int i = 0; i < iconoAzul.size(); i++) {
			imagenes[i] = iconoAzul.get(i).getImage();
		}
		
		for (int i = 0; i < imagenes.length; i++) {
			imagenes[i] = imagenes[i].getScaledInstance(width, heigth, Image.SCALE_SMOOTH);
		}
		
		for (int i = 0; i < imagenes.length; i++) {
			this.iconosNuevos.add(new ImageIcon(imagenes[i]));
		}
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws SQLException 
	 */
	private void initialize() throws SQLException {
		convertirIconos();
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(42, 157, 143));
		frame.setBounds(400, 160, 550, 400);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		this.lblTitulo = new JLabel("Edite el autor:");
		lblTitulo.setFont(new Font("Yu Gothic UI Semilight", Font.BOLD, 25));
		this.lblTitulo.setBounds(182, 30, 169, 34);
		
		this.lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(91, 103, 70, 20);
		
		this.lblApellido = new JLabel("Apellido:");
		lblApellido.setBounds(91, 134, 70, 20);
		
		this.lblSegundoApellido = new JLabel("Segundo Apellido:");
		lblSegundoApellido.setBounds(91, 165, 117, 20);
		
		this.lblPais = new JLabel("Pais:");
		lblPais.setBounds(91, 196, 70, 20);
		
		this.textNombre = new JTextField();
		textNombre.setBounds(218, 103, 221, 20);
		this.textNombre.setBackground(new Color(42, 157, 143));
		this.textNombre.setBorder(null);
		
		this.textApellido = new JTextField();
		textApellido.setBounds(218, 134, 221, 20);
		this.textApellido.setBackground(new Color(42, 157, 143));
		this.textApellido.setBorder(null);
		
		
		this.textSegundoApellido = new JTextField();
		textSegundoApellido.setBounds(218, 165, 221, 20);
		this.textSegundoApellido.setBackground(new Color(42, 157, 143));
		this.textSegundoApellido.setBorder(null);
		
		this.enviar = new JButton("");
		enviar.setIcon(new ImageIcon("C:\\Users\\Usuario\\eclipse-workspace_tres\\PracticaFinalDam1\\Iconos\\editOrigin.png"));
		this.enviar.setBounds(305, 277, 86, 32);
		this.enviar.setBackground(new Color(233, 196, 106));
		this.enviar.setBorder(UIManager.getBorder("List.noFocusBorder"));
		
		this.volver = new JButton("");
		volver.setIcon(new ImageIcon("C:\\Users\\Usuario\\eclipse-workspace_tres\\PracticaFinalDam1\\Iconos\\atrasOrigin.png"));
		this.volver.setBounds(144, 277, 86, 32);
		this.volver.setBackground(new Color(233, 196, 106));
		this.volver.setBorder(UIManager.getBorder("List.noFocusBorder"));
		
		//Guardamos en una variable de la clase ArrayList el resultado de todos los paises
		ArrayList<String> paises = this.gestionAutores.listadoPaises();
		paises.add(0, "");
		
		//Luego lo guardamos en una array estática
		String[] paisesComboBox = paises.toArray(new String[0]);		
		//Inicializamos el JComboBox y le introducimos el array estático
		this.textPais = new JComboBox(paisesComboBox);
		textPais.setSize(97, 20);
		textPais.setLocation(218, 196);
		this.textPais.setBackground(new Color(233, 196, 106));
		
		iniciarComponentes();
	}
	
	public void iniciarComponentes() {
		frame.getContentPane().setLayout(null);
		this.frame.getContentPane().add(this.lblTitulo);
		this.frame.getContentPane().add(this.lblNombre);
		this.frame.getContentPane().add(this.lblApellido);
		this.frame.getContentPane().add(this.lblSegundoApellido);
		this.frame.getContentPane().add(this.lblPais);
		this.frame.getContentPane().add(this.textNombre);
		this.frame.getContentPane().add(this.textApellido);
		this.frame.getContentPane().add(this.textSegundoApellido);
		this.frame.getContentPane().add(this.textPais);
		this.frame.getContentPane().add(this.enviar);
		this.frame.getContentPane().add(this.volver);
		
		separator = new JSeparator();
		separator.setBackground(new Color(0, 0, 0));
		separator.setBounds(218, 122, 221, 20);
		frame.getContentPane().add(separator);
		
		separator_1 = new JSeparator();
		separator_1.setBackground(new Color(0, 0, 0));
		separator_1.setBounds(218, 153, 221, 20);
		frame.getContentPane().add(separator_1);
		
		separator_2 = new JSeparator();
		separator_2.setBackground(new Color(0, 0, 0));
		separator_2.setBounds(218, 184, 221, 20);
		frame.getContentPane().add(separator_2);
		this.volver.addActionListener(this);
		this.enviar.addActionListener(this);
		this.textPais.addActionListener(this);
		this.enviar.addMouseListener(this);
		this.volver.addMouseListener(this);


	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == this.enviar) {
			this.nombreNuevo = this.textNombre.getText();
			this.apellidoNuevo = this.textApellido.getText();
			this.segundoApellidoNuevo = this.textSegundoApellido.getText();
			int respuesta = 0;
			int confirmacion = JOptionPane.showConfirmDialog(this.frame, "Seguro que quieres actualizar estos datos?", "Confirmacion", JOptionPane.YES_NO_OPTION);
			if(confirmacion == 0) {
				if(!this.nombreNuevo.equals("") && !this.apellidoNuevo.equals("") && !this.segundoApellidoNuevo.equals("") && !this.pais.equals("")) {
					//0: Si, 1: No
					try {
						respuesta = this.gestionAutores.actualizarTodo(nombreNuevo, apellidoNuevo, segundoApellidoNuevo, pais, nombreAntiguo, apellidoAntiguo, segundoApellidoAntiguo);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					//TODO MENOS EL PAÍS
				}else if(!this.nombreNuevo.equals("") && !this.apellidoNuevo.equals("") && !this.segundoApellidoNuevo.equals("")) {
					try {
						respuesta = this.gestionAutores.actualizarTodoSinPais(nombreNuevo, apellidoNuevo, segundoApellidoNuevo, nombreAntiguo, apellidoAntiguo, segundoApellidoAntiguo);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					//TODO MENOS EL SEGUNDO APELLIDO
				}else if(!this.nombreNuevo.equals("") && !this.apellidoNuevo.equals("") && !this.pais.equals("")) {
					try {
						respuesta = this.gestionAutores.actualizarTodoSinSegApellido(nombreNuevo, apellidoNuevo, pais, nombreAntiguo, apellidoAntiguo, segundoApellidoAntiguo);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					//TODO MENOS EL APELLIDO
				}else if(!this.nombreNuevo.equals("") && !this.segundoApellidoNuevo.equals("") && !this.pais.equals("")){
					try {
						respuesta = this.gestionAutores.actualizarTodoSinApellido(nombreNuevo, segundoApellidoNuevo, pais, nombreAntiguo, apellidoAntiguo, segundoApellidoAntiguo);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					//Todo menos el nombre
				}else if(!this.apellidoNuevo.equals("") && !this.segundoApellidoNuevo.equals("") && !this.pais.equals("")){
					try {
						respuesta = this.gestionAutores.actualizarTodoSinNombre(apellidoNuevo, segundoApellidoNuevo, pais, nombreAntiguo, apellidoAntiguo, segundoApellidoAntiguo);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					//Solo el nombre y apellido
				}else if(!this.nombreNuevo.equals("") && !this.apellidoNuevo.equals("")){
					try {
						respuesta = this.gestionAutores.actualizarNombreApellido(nombreNuevo, apellidoNuevo, nombreAntiguo, apellidoAntiguo, segundoApellidoAntiguo);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					//Solo el nombre y segundo apellido
				}else if(!this.nombreNuevo.equals("") && !this.segundoApellidoNuevo.equals("")){
					try {
						respuesta = this.gestionAutores.actualizarNombreSegApellido(nombreNuevo, segundoApellidoNuevo, nombreAntiguo, apellidoAntiguo, segundoApellidoAntiguo);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					//Solo el nombre y el pais
				}else if(!this.nombreNuevo.equals("") && !this.pais.equals("")){
					try {
						respuesta = this.gestionAutores.actualizarNombrePais(nombreNuevo, pais, nombreAntiguo, apellidoAntiguo, segundoApellidoAntiguo);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					//Solo Apellidos
				}else if(!this.apellidoNuevo.equals("") && !this.segundoApellidoNuevo.equals("")){
					try {
						respuesta = this.gestionAutores.actualizarApellidos(apellidoNuevo, segundoApellidoNuevo, nombreAntiguo, apellidoAntiguo, segundoApellidoAntiguo);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					//Apellido y pais
				}else if(!this.apellidoNuevo.equals("") && !this.pais.equals("")){
					try {
						respuesta = this.gestionAutores.actualizarApellidoPais(apellidoNuevo, pais, nombreAntiguo, apellidoAntiguo, segundoApellidoAntiguo);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					//Segundo Apellido y pais
				}else if(!this.segundoApellidoNuevo.equals("") && !this.pais.equals("")){
					try {
						respuesta = this.gestionAutores.actualizarSegApellidoPais(segundoApellidoNuevo, pais, nombreAntiguo, apellidoAntiguo, segundoApellidoAntiguo);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					//nombre
				}else if(!this.nombreNuevo.equals("")){
					try {
						respuesta = this.gestionAutores.actualizarAutorNombre(nombreNuevo, nombreAntiguo, apellidoAntiguo, segundoApellidoAntiguo);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					//Apellido
				}else if(!this.apellidoNuevo.equals("")){
					try {
						respuesta = this.gestionAutores.actualizarAutorApellido(apellidoNuevo, nombreAntiguo, apellidoAntiguo, segundoApellidoAntiguo);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					//Segundo apellido
				}else if(!this.segundoApellidoNuevo.equals("")){
					try {
						respuesta = this.gestionAutores.actualizarAutorApellidoDos(segundoApellidoNuevo, nombreAntiguo, apellidoAntiguo, segundoApellidoAntiguo);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					//Pais
				}else if(!this.pais.equals("")){
					try {
						respuesta = this.gestionAutores.actualizarPais(pais, nombreAntiguo, apellidoAntiguo, segundoApellidoAntiguo);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}else {
					respuesta = 2;
					JOptionPane.showMessageDialog(this.frame, "Debe introducir algun dato!", "Advertencia", JOptionPane.INFORMATION_MESSAGE);
					
				}
				//MIRAMOS LA RESPUESTA
				if (respuesta == 0) {
					JOptionPane.showMessageDialog(this.frame, "Se ha actualizado correctamente!", "Exito", JOptionPane.INFORMATION_MESSAGE);
					this.frame.setVisible(false);
				}else if(respuesta == 1){
					JOptionPane.showMessageDialog(this.frame, "Este usuario ya existe!!", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		}else if(e.getSource() == this.textPais) {
			this.pais =(String) this.textPais.getSelectedItem();
		}else if(e.getSource() == this.volver) {
			this.frame.setVisible(false);
			Principal principal = null;
			try {
				principal = new Principal();
				principal.visualize();
			}catch(SQLException e1) {
				e1.printStackTrace();
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == this.volver) {
			this.volver.setIcon(this.iconosNuevos.get(0));
		}else if(e.getSource() == this.enviar) {
			this.enviar.setIcon(this.iconosNuevos.get(1));
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == this.volver) {
			this.volver.setIcon(this.iconosOriginales.get(0));
		}else if(e.getSource() == this.enviar) {
			this.enviar.setIcon(this.iconosOriginales.get(1));
		}
	}
}
