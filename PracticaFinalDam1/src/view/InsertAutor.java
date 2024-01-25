package view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.ControlAutores;

import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JSeparator;
import java.awt.Cursor;
import javax.swing.ImageIcon;
import javax.swing.UIManager;

public class InsertAutor extends JPanel implements ActionListener, FocusListener, MouseListener {

	private JFrame frame;
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JTextField txtApellidoDos;
	private JLabel lblTitulo;
	private JComboBox txtPais;
	private JLabel lblNombre;
	private JLabel lblApellido;
	private JLabel lblSegundoApelllido;
	private JLabel lblPais;
	private JButton btnInsertar;
	private JButton btnVolver;
	private String nombre;
	private String apellido;
	private String segundoApellido;
	private String pais;
	private ControlAutores gestionAutores = new ControlAutores();
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
					InsertAutor window = new InsertAutor();
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
	public InsertAutor() throws SQLException {
		iniciarComponentes();
		this.nombre = "";
		this.apellido = "";
		this.segundoApellido = "";
		this.pais = "";		
	}
	
	public void visualize() {
		this.frame.setVisible(true);
	}
	
	public void convertirIconos() {
		/*
		 * Podremos los iconos en este orden:
		 * - Volver
		 * - Añadir
		 */
		iconosNuevos = new ArrayList<>();
		iconosOriginales = new ArrayList<>();
		int width = 28;
		int heigth = 28;
		
		iconosOriginales.add(new ImageIcon("C:\\Users\\Usuario\\eclipse-workspace_tres\\PracticaFinalDam1\\Iconos\\atrasOrigin.png"));
		iconosOriginales.add(new ImageIcon("C:\\Users\\Usuario\\eclipse-workspace_tres\\PracticaFinalDam1\\Iconos\\InsertOriginal.png"));
		
		ArrayList<ImageIcon> iconoAzul = new ArrayList<>();
		iconoAzul.add(new ImageIcon("C:\\Users\\Usuario\\eclipse-workspace_tres\\PracticaFinalDam1\\Iconos\\atrasMod.png"));
		iconoAzul.add(new ImageIcon("C:\\Users\\Usuario\\eclipse-workspace_tres\\PracticaFinalDam1\\Iconos\\InsertMod.png"));
		
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
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(42, 157, 143));
		frame.setBounds(400, 160, 550, 400);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		
		this.lblTitulo = new JLabel("Añadir Autor");
		lblTitulo.setFont(new Font("Yu Gothic UI Semilight", Font.BOLD, 25));
		lblTitulo.setBounds(184, 21, 224, 45);
		
		this.txtNombre = new JTextField();
		txtNombre.setBackground(new Color(42, 157, 143));
		txtNombre.setBorder(null);
		this.txtNombre.setColumns(10);
		this.txtNombre.setBounds(206, 93, 224, 20);
		
		this.txtApellido = new JTextField();
		txtApellido.setBackground(new Color(42, 157, 143));
		txtApellido.setBorder(null);
		this.txtApellido.setColumns(10);
		this.txtApellido.setBounds(206, 124, 224, 20);
		
		this.txtApellidoDos = new JTextField();
		txtApellidoDos.setBackground(new Color(42, 157, 143));
		txtApellidoDos.setBorder(null);
		this.txtApellidoDos.setColumns(10);
		this.txtApellidoDos.setBounds(206, 155, 224, 20);
		
		
		ArrayList<String> paises = this.gestionAutores.listadoPaises();
		paises.add(0, "");
		String [] paisesArray = paises.toArray(new String[0]);
		this.txtPais = new JComboBox(paisesArray);
		txtPais.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		txtPais.setBackground(new Color(233, 196, 106));
		this.txtPais.setBounds(206, 189, 119, 22);
		
		this.lblNombre = new JLabel("Nombre Autor:");
		this.lblNombre.setBounds(94, 96, 102, 14);
		
		this.lblApellido = new JLabel("Apellido Autor:");
		this.lblApellido.setBounds(94, 127, 102, 14);
		
		this.lblSegundoApelllido = new JLabel("Segundo Ape:");
		this.lblSegundoApelllido.setBounds(94, 158, 102, 14);
		
		this.lblPais = new JLabel("Pais:");
		this.lblPais.setBounds(138, 193, 34, 14);
		
		this.btnInsertar = new JButton("");
		btnInsertar.setBorder(UIManager.getBorder("List.noFocusBorder"));
		btnInsertar.setIcon(new ImageIcon("C:\\Users\\Usuario\\eclipse-workspace_tres\\PracticaFinalDam1\\Iconos\\InsertOriginal.png"));
		btnInsertar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnInsertar.setBackground(new Color(233, 196, 106));
		this.btnInsertar.setBounds(273, 263, 86, 32);
		
		this.btnVolver = new JButton("");
		btnVolver.setBorder(UIManager.getBorder("List.noFocusBorder"));
		btnVolver.setIcon(new ImageIcon("C:\\Users\\Usuario\\eclipse-workspace_tres\\PracticaFinalDam1\\Iconos\\atrasOrigin.png"));
		btnVolver.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnVolver.setBackground(new Color(233, 196, 106));
		this.btnVolver.setBounds(145, 263, 86, 32);
	}
	
	public void iniciarComponentes() throws SQLException {
		initialize();
		convertirIconos();
		this.frame.getContentPane().add(this.lblTitulo);
		this.frame.getContentPane().add(this.txtNombre);
		this.frame.getContentPane().add(this.txtApellido);
		this.frame.getContentPane().add(this.txtApellidoDos);
		this.frame.getContentPane().add(this.txtPais);
		this.frame.getContentPane().add(this.lblNombre);
		this.frame.getContentPane().add(this.lblApellido);
		this.frame.getContentPane().add(this.lblSegundoApelllido);
		this.frame.getContentPane().add(this.lblPais);
		this.frame.getContentPane().add(btnInsertar);
		this.frame.getContentPane().add(btnVolver);
		
		JSeparator separator = new JSeparator();
		separator.setBackground(new Color(0, 0, 0));
		separator.setBounds(206, 147, 224, 20);
		frame.getContentPane().add(separator);
		
		separator_1 = new JSeparator();
		separator_1.setBackground(Color.BLACK);
		separator_1.setBounds(206, 116, 224, 20);
		frame.getContentPane().add(separator_1);
		
		separator_2 = new JSeparator();
		separator_2.setBackground(Color.BLACK);
		separator_2.setBounds(206, 178, 224, 20);
		frame.getContentPane().add(separator_2);
		this.btnInsertar.addActionListener(this);
		this.btnVolver.addActionListener(this);
		this.txtPais.addActionListener(this);
		this.txtNombre.addActionListener(this);
		this.txtApellido.addActionListener(this);
		this.txtApellidoDos.addActionListener(this);
		this.btnInsertar.addMouseListener(this);
		this.btnVolver.addMouseListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == this.btnInsertar) {
			this.nombre = this.txtNombre.getText();
			this.apellido = this.txtApellido.getText();
			this.segundoApellido = this.txtApellidoDos.getText();
			int resultado = -1;
			if (!this.nombre.equals("") && !this.apellido.equals("") && !this.segundoApellido.equals("")) {
				int confirmacion = JOptionPane.showConfirmDialog(this.frame, "Quieres insertar a " + this.nombre + " " + this.apellido + " " + this.segundoApellido + "?" , "Cofirmacion", JOptionPane.OK_OPTION);
				if (confirmacion == 0) {
					try {
						resultado = this.gestionAutores.insercionAutor(nombre, apellido, segundoApellido, pais);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
						resultado = 1;
					}
				}else if(confirmacion == 1) {
					resultado = -2;
				}
			}else if (!this.nombre.equals("") && !this.apellido.equals("") && !this.segundoApellido.equals("") && !this.pais.equals("")) {
				int confirmacion = JOptionPane.showConfirmDialog(this.frame, "Quieres insertar a " + this.nombre + " " + this.apellido + " " + this.segundoApellido + "?" , "Cofirmacion", JOptionPane.OK_OPTION);
				if (confirmacion == 0) {
					try {
						resultado = this.gestionAutores.insercionAutor(nombre, apellido, segundoApellido, pais);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
						resultado = 1;
					}
				}else if(confirmacion == 1){
					resultado = -2;
				}
			}
			
			else {
				resultado = -1;
			}
			
			if (resultado == 0) {
				JOptionPane.showMessageDialog(this.frame, "Se ha añadido el autor!", "exito", JOptionPane.INFORMATION_MESSAGE);
			}else if (resultado == 1) {
				JOptionPane.showMessageDialog(this.frame, "El autor ya existe!", "Error", JOptionPane.ERROR_MESSAGE);
			}else if(resultado == -1){
				JOptionPane.showMessageDialog(this.frame, "No puede dejar los campos en blanco!", "Error", JOptionPane.ERROR_MESSAGE);
			}
			
		}else if(e.getSource() == this.btnVolver) {
			this.frame.setVisible(false);
			try {
				Principal principal = new Principal();
				principal.visualize();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}else if(e.getSource() == this.txtPais) {
			this.pais = (String) this.txtPais.getSelectedItem();
		}else if (e.getSource() == this.txtNombre) {
			this.txtNombre.setText("");
		}else if(e.getSource() == this.txtApellido){
			this.txtApellido.setText("");
		}else if(e.getSource() == this.txtApellidoDos) {
			this.txtApellidoDos.setText("");
		}
	}

	@Override
	public void focusGained(FocusEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == this.txtNombre) {
			frame.getContentPane().setBackground(new Color(247, 100, 134));
			this.txtNombre.setText("");
		}
	}

	@Override
	public void focusLost(FocusEvent e) {
		// TODO Auto-generated method stub
		
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
		if (e.getSource() == this.btnVolver) {
			this.btnVolver.setIcon(iconosNuevos.get(0));

		}else if (e.getSource() == this.btnInsertar) {
			this.btnInsertar.setIcon(this.iconosNuevos.get(1));
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == this.btnVolver) {
			this.btnVolver.setIcon(iconosOriginales.get(0));
		}else if (e.getSource() == this.btnInsertar) {
			this.btnInsertar.setIcon(iconosOriginales.get(1));
		}
	}
}
