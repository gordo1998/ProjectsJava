package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;

import controller.ControlAutores;
import java.awt.Font;
import javax.swing.JSeparator;


public class EditAutor extends JFrame implements ActionListener, KeyListener, MouseListener {
	private JFrame frame;
	private JComboBox<String> desplegable;
	private JLabel labelTitulo;
	private JButton btn_enviar;
	private Object seleccion;
	private JTextField textApellido;
	private JTextField textNombre;
	private ControlAutores gestionAutores;
	private String nombre = new String();
	private String apellido = new String();
	private String segundoApellido = new String();
	private JTextField textApellidoDos;
	private JLabel pruebaa;
	EditDosAutor actualizarAutor;
	private JButton btn_cancelar;
	private ArrayList<ImageIcon> iconosOriginales;
	private ArrayList<ImageIcon> iconosNuevos;
	private JSeparator separator;
	private JSeparator separator_1;
	private JSeparator separator_2;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditAutor window = new EditAutor();
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
	public EditAutor() throws SQLException {
		inicializarComponentes();
	}
	/**
	 * Visualiza el frame
	 */
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
		iconosOriginales.add(new ImageIcon("C:\\Users\\Usuario\\eclipse-workspace_tres\\PracticaFinalDam1\\Iconos\\adelanteOrigin.png"));
		
		ArrayList<ImageIcon> iconoAzul = new ArrayList<>();
		iconoAzul.add(new ImageIcon("C:\\Users\\Usuario\\eclipse-workspace_tres\\PracticaFinalDam1\\Iconos\\atrasMod.png"));
		iconoAzul.add(new ImageIcon("C:\\Users\\Usuario\\eclipse-workspace_tres\\PracticaFinalDam1\\Iconos\\adelanteMod.png"));
		
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
		this.gestionAutores = new ControlAutores();
		this.frame = new JFrame("Hola");
		this.frame.setVisible(true);
		frame.getContentPane().setBackground(new Color(42, 157, 143));
		this.frame.setBounds(400, 160, 550, 400);
		this.frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		this.frame.getContentPane().setLayout(null);
		//Creamos un JComboBox y almacenamos dentro los valores de un ArrayList que proviene de la funcion gestionAutores que recupera los paises de la base de datos		
		this.labelTitulo = new JLabel("Buscar autor");
		labelTitulo.setFont(new Font("Yu Gothic UI Semilight", Font.BOLD, 25));
		this.labelTitulo.setSize(160, 34);
		this.labelTitulo.setLocation(196, 36);
		
		this.btn_enviar = new JButton("");
		btn_enviar.setIcon(new ImageIcon("C:\\Users\\Usuario\\eclipse-workspace_tres\\PracticaFinalDam1\\Iconos\\adelanteOrigin.png"));
		this.btn_enviar.setBounds(new Rectangle(286, 253, 86, 32));
		this.btn_enviar.setBackground(Color.BLUE);
		this.btn_enviar.setBorder(UIManager.getBorder("List.noFocusBorder"));
		this.btn_enviar.setBackground(new Color(233, 196, 106));
		
		this.textApellido = new JTextField();
		this.textApellido.setBounds(209, 152, 245, 20);
		this.textApellido.setColumns(10);
		this.textApellido.setBackground(new Color(42, 157, 143));
		this.textApellido.setBorder(null);
		
		this.textNombre = new JTextField();
		this.textNombre.setColumns(10);
		this.textNombre.setBounds(209, 121, 245, 20);
		this.textNombre.setBackground(new Color(42, 157, 143));
		this.textNombre.setBorder(null);
		
		
		this.btn_cancelar = new JButton("");
		btn_cancelar.setIcon(new ImageIcon("C:\\Users\\Usuario\\eclipse-workspace_tres\\PracticaFinalDam1\\Iconos\\atrasOrigin.png"));
		this.btn_cancelar.setBounds(149, 252, 86, 32);
		this.btn_cancelar.setBorder(UIManager.getBorder("List.noFocusBorder"));
		this.btn_cancelar.setBackground(new Color(233, 196, 106));
		
	}
	
	public void inicializarComponentes() throws SQLException {
		initialize();
		convertirIconos();
		this.frame.getContentPane().add(this.labelTitulo);
		this.frame.getContentPane().add(this.btn_enviar);
		this.frame.getContentPane().add(textApellido);
		this.frame.getContentPane().add(textNombre);
		
		textApellidoDos = new JTextField();
		textApellidoDos.setBounds(209, 183, 245, 20);
		frame.getContentPane().add(textApellidoDos);
		textApellidoDos.setColumns(10);
		textApellidoDos.setBackground(new Color(42, 157, 143));
		textApellidoDos.setBorder(null);
		
		JLabel lbl_nombre = new JLabel("Nombre:");
		lbl_nombre.setBounds(80, 124, 69, 14);
		frame.getContentPane().add(lbl_nombre);
		
		JLabel lblApellido = new JLabel("Apellido:");
		lblApellido.setBounds(80, 155, 69, 14);
		frame.getContentPane().add(lblApellido);
		
		JLabel lblApellidoDos = new JLabel("Segundo Apellido:");
		lblApellidoDos.setBounds(80, 186, 100, 14);
		frame.getContentPane().add(lblApellidoDos);
		
		pruebaa = new JLabel("");
		pruebaa.setBounds(220, 11, 46, 14);
		frame.getContentPane().add(pruebaa);
		
		
		this.frame.getContentPane().add(this.btn_cancelar);
		
		separator = new JSeparator();
		separator.setBackground(new Color(0, 0, 0));
		separator.setBounds(209, 140, 248, 20);
		frame.getContentPane().add(separator);
		
		separator_1 = new JSeparator();
		separator_1.setBackground(Color.BLACK);
		separator_1.setBounds(209, 171, 248, 20);
		frame.getContentPane().add(separator_1);
		
		separator_2 = new JSeparator();
		separator_2.setBackground(Color.BLACK);
		separator_2.setBounds(209, 202, 248, 20);
		frame.getContentPane().add(separator_2);
		this.btn_cancelar.addActionListener(this);
		this.btn_enviar.addActionListener(this);
		this.btn_enviar.addMouseListener(this);
		this.btn_cancelar.addMouseListener(this);
		this.textNombre.addKeyListener(this);
		
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		//si el autor le da a enviar
		if(e.getSource() == this.btn_enviar) {
			//Se guardan los valores en las respectivas variables
			this.nombre = this.textNombre.getText().trim().replaceAll("\\s+", " ");
			this.apellido = this.textApellido.getText().trim().replaceAll("\\s+", " ");
			this.segundoApellido = this.textApellidoDos.getText().trim().replaceAll("\\s+", " ");
			//Declaramos una variable de la clase ArrayList
			ArrayList<String> autores;
			//Si todas las variables estan llenas
			if (!this.nombre.equals("") && !this.apellido.equals("") && !this.nombre.equals("")) {
				//Inicializamos la variable a null
				autores = null;
				try {
					//Recuperamos el autor de la base de datos
					autores = this.gestionAutores.imprimirAutorNomApellPrimSegun(this.nombre, this.apellido, this.segundoApellido);
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				//Si el autor no existe dará un error
				if(autores == null) {
					JOptionPane.showMessageDialog(null, "Ocurrio un error", "Error", JOptionPane.ERROR_MESSAGE);
				}else {
					//Si es mayor a 0
					if(autores.size() > 0) {
						//Mandamos mensaje de confirmación
						int numero = JOptionPane.showConfirmDialog(this.frame, "Estás seguro que quieres editar este autor?", "Confirmación", JOptionPane.YES_NO_CANCEL_OPTION);
						//Si responde afirmativamente inicializamos un objeto de la clase EditDosAutor y pasamos por parámetro las variables en el constructor
						if (numero == 0) {
							EditDosAutor actualizar = null;
							try {
								actualizar = new EditDosAutor(this.nombre, this.apellido, this.segundoApellido);
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							actualizar.visualize();
							
						}
					}else {
						JOptionPane.showMessageDialog(null, "No existe este autor en la base de datos!!", "Error", JOptionPane.ERROR_MESSAGE);
					}
				}
			//En caso de que el usuario no introduzca todos los datos
			}else if(this.nombre.equals("") || this.apellido.equals("") || this.segundoApellido.equals("")){
				JOptionPane.showMessageDialog(null, "No puedes dejar un campo en blanco!!", "Error", JOptionPane.ERROR_MESSAGE);
			}
			
						
		
			
		}else if (e.getSource() == this.btn_cancelar) {
			this.frame.setVisible(false);
			try {
				Principal principal = new Principal();
				principal.visualize();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
//		
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
		if(e.getSource() == this.btn_cancelar) {
			this.btn_cancelar.setIcon(this.iconosNuevos.get(0));
		}else if(e.getSource() == this.btn_enviar) {
			this.btn_enviar.setIcon(this.iconosNuevos.get(1));
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == this.btn_cancelar) {
			this.btn_cancelar.setIcon(this.iconosOriginales.get(0));
		}else if(e.getSource() == this.btn_enviar) {
			this.btn_enviar.setIcon(this.iconosOriginales.get(1));
		}
	}
}
