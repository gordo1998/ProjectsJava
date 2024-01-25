package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;import javax.swing.UIManager;

import controller.ControlAutores;

import javax.swing.JComboBox;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JSeparator;

public class DeleteAutor extends JFrame implements ActionListener, MouseListener {

	private JFrame frame;
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JTextField txtSegundoApellido;
	private JLabel lblDelete;
	private JLabel lblNombre;
	private JLabel lblApellido;
	private JLabel lblSegundoApellido;
	private JButton btnVolver;
	private JButton btnDelete;
	private String nombre;
	private String apellido;
	private String segundoApellido;
	private ControlAutores gestionAutores = new ControlAutores();
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
					DeleteAutor window = new DeleteAutor();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public DeleteAutor() throws SQLException {
		initialize();
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
		iconosOriginales.add(new ImageIcon("C:\\Users\\Usuario\\eclipse-workspace_tres\\PracticaFinalDam1\\Iconos\\deleteOrigin.png"));
		
		ArrayList<ImageIcon> iconoAzul = new ArrayList<>();
		iconoAzul.add(new ImageIcon("C:\\Users\\Usuario\\eclipse-workspace_tres\\PracticaFinalDam1\\Iconos\\atrasMod.png"));
		iconoAzul.add(new ImageIcon("C:\\Users\\Usuario\\eclipse-workspace_tres\\PracticaFinalDam1\\Iconos\\deleteMod.png"));
		
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
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(400, 160, 550, 400);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setBackground(new Color(42, 157, 143));
		frame.getContentPane().setLayout(null);
		
		this.lblDelete = new JLabel("Eliminar Autor");
		lblDelete.setFont(new Font("Yu Gothic UI Semilight", Font.BOLD, 25));
		this.lblDelete.setBounds(173, 27, 220, 46);
		
		this.txtNombre = new JTextField();
		this.txtNombre.setBounds(195, 116, 242, 20);
		this.txtNombre.setColumns(10);
		this.txtNombre.setBackground(new Color(42, 157, 143));
		this.txtNombre.setBorder(null);
		
		this.txtApellido = new JTextField();
		this.txtApellido.setColumns(10);
		this.txtApellido.setBounds(195, 147, 242, 20);
		this.txtApellido.setBackground(new Color(42, 157, 143));
		this.txtApellido.setBorder(null);
		
		this.txtSegundoApellido = new JTextField();
		this.txtSegundoApellido.setColumns(10);
		this.txtSegundoApellido.setBounds(195, 178, 242, 20);
		this.txtSegundoApellido.setBackground(new Color(42, 157, 143));
		this.txtSegundoApellido.setBorder(null);
		
		this.lblNombre = new JLabel("Nombre:");
		this.lblNombre.setBounds(84, 119, 93, 14);
		
		this.lblApellido = new JLabel("Apellido:");
		this.lblApellido.setBounds(84, 150, 93, 14);
		
		this.lblSegundoApellido = new JLabel("Segundo Apellido:");
		this.lblSegundoApellido.setBounds(84, 181, 101, 14);
		
		this.btnVolver = new JButton("");
		this.btnVolver.setBounds(139, 247, 86, 32);
		this.btnVolver.setIcon(new ImageIcon("C:\\Users\\Usuario\\eclipse-workspace_tres\\PracticaFinalDam1\\Iconos\\atrasOrigin.png"));
		this.btnVolver.setBackground(new Color(233, 196, 106));
		this.btnVolver.setBorder(UIManager.getBorder("List.noFocusBorder"));
		
		this.btnDelete = new JButton("");
		this.btnDelete.setBounds(278, 247, 86, 32);
		this.btnDelete.setIcon(new ImageIcon("C:\\Users\\Usuario\\eclipse-workspace_tres\\PracticaFinalDam1\\Iconos\\deleteOrigin.png"));
		this.btnDelete.setBackground(new Color(233, 196, 106));
		this.btnDelete.setBorder(UIManager.getBorder("List.noFocusBorder"));
	
		iniciarComponentes();
	}
	
	public void iniciarComponentes() {
		convertirIconos();
		//Añadimos al Panel principal todos los elementos a la vista
		this.frame.getContentPane().add(this.lblDelete);
		this.frame.getContentPane().add(this.txtNombre);
		this.frame.getContentPane().add(this.txtApellido);
		this.frame.getContentPane().add(this.txtSegundoApellido);
		this.frame.getContentPane().add(this.lblNombre);
		this.frame.getContentPane().add(this.lblApellido);
		this.frame.getContentPane().add(this.lblSegundoApellido);
		this.frame.getContentPane().add(this.btnVolver);
		this.frame.getContentPane().add(this.btnDelete);
		
		separator = new JSeparator();
		separator.setBackground(new Color(0, 0, 0));
		separator.setBounds(195, 135, 242, 20);
		frame.getContentPane().add(separator);
		
		separator_1 = new JSeparator();
		separator_1.setBackground(new Color(0, 0, 0));
		separator_1.setBounds(195, 166, 242, 20);
		frame.getContentPane().add(separator_1);
		
		separator_2 = new JSeparator();
		separator_2.setBackground(new Color(0, 0, 0));
		separator_2.setBounds(195, 197, 242, 20);
		frame.getContentPane().add(separator_2);
		//Añadimos dinamismo a los botones
		this.btnDelete.addActionListener(this);
		this.btnVolver.addActionListener(this);
		this.btnVolver.addMouseListener(this);
		this.btnDelete.addMouseListener(this);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		//Si se apreta el boton eliminar
		if(e.getSource() == this.btnDelete) {
			//Rellenamos todas las variables con el contenido de los campos
			this.nombre = this.txtNombre.getText();
			this.apellido = this.txtApellido.getText();
			this.segundoApellido = this.txtSegundoApellido.getText();
			//Inicializamos una variable entera para guardar el resultado de las consultas
			int respuesta = 0;
			//Inicializamos otra variable entera para guardar la respuesta del usuario
			int confirmacion = JOptionPane.showConfirmDialog(this.frame, "Estas seguro que quieres eliminar este autor?", "Advertencia", JOptionPane.OK_CANCEL_OPTION);
			//Si respide que sí
			if (confirmacion == 0) {
				try {
					//Miramos si el usuario existe. Si existe devolverá un 0, si no existe le asignaremos un 2
					if(this.gestionAutores.comprobarExistRegistrosAutor(nombre, apellido, segundoApellido)) {
						respuesta = this.gestionAutores.eliminarAutor(nombre, apellido, segundoApellido);
					}else {
						respuesta = 2;
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			//Mostraremos un mensaje dependiendo del resultado de la consulta
			if (respuesta == 0) {
				JOptionPane.showMessageDialog(this.frame, "Autor eliminado!", "eliminado", JOptionPane.INFORMATION_MESSAGE);
			}else if (respuesta == 1) {
				JOptionPane.showMessageDialog(this.frame, "El autor no se ha podido eliminar!", "Error", JOptionPane.ERROR_MESSAGE);
			}else if (respuesta == 2) {
				JOptionPane.showMessageDialog(this.frame, "No existe el autor que quieres eliminar", "Error", JOptionPane.ERROR_MESSAGE);
			}
		//Si se apreta el boton de Volver
		}else if (e.getSource() == this.btnVolver) {
			//Cerramos la ventana actual y abrimos la ventana principal
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
		if(e.getSource() == this.btnVolver) {
			this.btnVolver.setIcon(this.iconosNuevos.get(0));
		}else if (e.getSource() == this.btnDelete) {
			this.btnDelete.setIcon(this.iconosNuevos.get(1));
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == this.btnVolver) {
			this.btnVolver.setIcon(this.iconosOriginales.get(0));
		}else if (e.getSource() == this.btnDelete) {
			this.btnDelete.setIcon(this.iconosOriginales.get(1));
		}
	}
}
