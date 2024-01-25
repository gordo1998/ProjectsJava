package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;

import java.awt.Choice;
import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JTextField;

import controller.ControlAutores;
import controller.ControlLibros;
import model.Autor;

import javax.swing.JPanel;
import javax.swing.JDesktopPane;
import java.awt.Label;
import java.awt.Color;
import java.awt.Button;
import java.awt.Font;
import java.awt.Image;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JSeparator;
import java.awt.Cursor;
import javax.swing.border.CompoundBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.UIManager;

public class Principal implements ActionListener, KeyListener, MouseListener{
	private JFrame frame;
	private JPanel panelInicio;
	private JButton btn_InsertarAutores;
	private JLabel lbl_Biblioteca;
	private JLabel lbl_Autores;
	private JLabel lbl_Libros;
	private JButton btn_eliminarAutores;
	private JButton btn_editarAutores;
	private JButton btn_insertarLibros;
	private JButton btn_eliminarLibros;
	private JButton btn_editarLibros;
	private JButton btn_mostrarLibros;
	private InsertAutor insertarAutor;
	private ControlLibros gestionLibros = new ControlLibros();
	private ControlAutores gestionAutores = new ControlAutores();
	private ArrayList<Autor> autores;
	private ArrayList<ImageIcon> imagenesIconos;
	private ArrayList<ImageIcon> iconosNuevos;
	
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal window = new Principal();
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
	public Principal() throws SQLException{
		iniciarComponentes();
		this.autores = this.gestionAutores.crearObjetosAutores();
		this.gestionLibros.crearObjetosLibros();
	}
	
	public void visualize() {
		this.frame.setVisible(true);
	}
	
	public void convertirIconos() {
		/*
		 * Vamos a insertar los iconos del siguiente orden:
		 * 
		 * - [0]Nuevo autor
		 * - [1]Eliminar autor
		 * - [2]Editar autor
		 * - [3]Nuevo libro
		 * - [4]Eliminar libro
		 * - [5]Editar libro
		 * - [6]Mostrar libros
		 * 
		 * 
		 */
		
		int alto = 40;
		int ancho = 40;
		//Primerro añadimos la lista de imagenes que nos serviran de icono
		this.imagenesIconos = new ArrayList<>();
		this.iconosNuevos = new ArrayList<>();
		
		this.imagenesIconos.add(new ImageIcon("C:\\Users\\Usuario\\eclipse-workspace_tres\\PracticaFinalDam1\\Iconos\\InitInsertUserOrigin.png"));
		this.imagenesIconos.add(new ImageIcon("C:\\Users\\Usuario\\eclipse-workspace_tres\\PracticaFinalDam1\\Iconos\\InitDeleteOrigin.png"));
		this.imagenesIconos.add(new ImageIcon("C:\\Users\\Usuario\\eclipse-workspace_tres\\PracticaFinalDam1\\Iconos\\InitEditOrigin.png"));
		this.imagenesIconos.add(new ImageIcon("C:\\Users\\Usuario\\eclipse-workspace_tres\\PracticaFinalDam1\\Iconos\\InitInsertLibroOrigin.png"));
		this.imagenesIconos.add(new ImageIcon("C:\\Users\\Usuario\\eclipse-workspace_tres\\PracticaFinalDam1\\Iconos\\InitDeleteOrigin.png"));
		this.imagenesIconos.add(new ImageIcon("C:\\Users\\Usuario\\eclipse-workspace_tres\\PracticaFinalDam1\\Iconos\\InitEditOrigin.png"));
		this.imagenesIconos.add(new ImageIcon("C:\\Users\\Usuario\\eclipse-workspace_tres\\PracticaFinalDam1\\Iconos\\InitViewOrigin.png"));
		
		//Ahora vamos a añadir elementos de iconos azules 
		ArrayList<ImageIcon> iconoAzul = new ArrayList<>();
		iconoAzul.add(new ImageIcon("C:\\Users\\Usuario\\eclipse-workspace_tres\\PracticaFinalDam1\\Iconos\\InitInsertUserMod.png"));
		iconoAzul.add(new ImageIcon("C:\\Users\\Usuario\\eclipse-workspace_tres\\PracticaFinalDam1\\Iconos\\InitDeleteMod.png"));
		iconoAzul.add(new ImageIcon("C:\\Users\\Usuario\\eclipse-workspace_tres\\PracticaFinalDam1\\Iconos\\InitEditMod.png"));
		iconoAzul.add(new ImageIcon("C:\\Users\\Usuario\\eclipse-workspace_tres\\PracticaFinalDam1\\Iconos\\InitInsertLibroMod.png"));
		iconoAzul.add(new ImageIcon("C:\\Users\\Usuario\\eclipse-workspace_tres\\PracticaFinalDam1\\Iconos\\InitDeleteMod.png"));
		iconoAzul.add(new ImageIcon("C:\\Users\\Usuario\\eclipse-workspace_tres\\PracticaFinalDam1\\Iconos\\InitEditMod.png"));
		iconoAzul.add(new ImageIcon("C:\\Users\\Usuario\\eclipse-workspace_tres\\PracticaFinalDam1\\Iconos\\InitViewMod.png"));
		
		
		//creamos una array de la clase Image que tendrá el mismo tamaño que el arrayList
		Image[] imagenes = new Image[iconoAzul.size()];
		//Recorremos el ArrayList y por cada iteración llamamos al método getImage() de ImageIcon y lo guardamos en la posicion actual 
		//del array. Esto lo hacemos así porque nos devuelve un elemento de la clase Image.
		for (int i = 0; i < iconoAzul.size(); i++) {
			imagenes[i] = iconoAzul.get(i).getImage();
		}
		
		//Una vez tenemos el array de Image, vamos a cambiar el tamaño de la imagen
		for (int i = 0; i < imagenes.length; i++) {
			imagenes[i] = imagenes[i].getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
		}
		
		//Ahora vamos a pasar los elementos del array al ArrayList de los nuevosIconos. Esta parte la hacemos ya que para poder insertar
		// en un botón un icono, este solo permite un objeto de la clase ImageIcon
		for (int i = 0; i < imagenes.length; i++) {
			this.iconosNuevos.add(new ImageIcon(imagenes[i]));
		}
	}
	
	

	/**
	 * Initialize the contents of the frame.
	 */
	public void initialize() {
		this.frame = new JFrame();
		frame.getContentPane().setBackground(new Color(42, 157, 143));
		this.frame.setBounds(400, 160, 550, 400);
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		JButton buton = new JButton("");
		buton.setBorder(null);
		
		
		
	}
	
	public void iniciarComponentes() {
		initialize();
		convertirIconos();
		this.btn_InsertarAutores = new JButton("");
		btn_InsertarAutores.setBorder(UIManager.getBorder("List.noFocusBorder"));
		btn_InsertarAutores.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btn_InsertarAutores.setIcon(new ImageIcon("C:\\Users\\Usuario\\eclipse-workspace_tres\\PracticaFinalDam1\\Iconos\\InitInsertUserOrigin.png"));
		btn_InsertarAutores.setForeground(new Color(0, 0, 0));
		btn_InsertarAutores.setBackground(new Color(233, 196, 106));
		this.btn_InsertarAutores.setBounds(73, 99, 89, 80);
		frame.getContentPane().add(this.btn_InsertarAutores);
		
		this.lbl_Biblioteca = new JLabel("Bienvenido a la biblioteca");
		lbl_Biblioteca.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
		lbl_Biblioteca.setFont(new Font("Yu Gothic Medium", Font.BOLD, 38));
		this.lbl_Biblioteca.setBounds(30, 11, 494, 61);
		this.frame.getContentPane().add(this.lbl_Biblioteca);
		
		this.lbl_Autores = new JLabel("Autores");
		lbl_Autores.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
		this.lbl_Autores.setBounds(94, 74, 46, 14);
		frame.getContentPane().add(this.lbl_Autores);
		
		this.lbl_Libros = new JLabel("Libros");
		lbl_Libros.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
		this.lbl_Libros.setBounds(380, 74, 46, 14);
		this.frame.getContentPane().add(this.lbl_Libros);
		
		this.btn_eliminarAutores = new JButton("");
		btn_eliminarAutores.setBorder(UIManager.getBorder("List.noFocusBorder"));
		btn_eliminarAutores.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btn_eliminarAutores.setBackground(new Color(233, 196, 106));
		btn_eliminarAutores.setIcon(new ImageIcon("C:\\Users\\Usuario\\eclipse-workspace_tres\\PracticaFinalDam1\\Iconos\\InitDeleteOrigin.png"));
		this.btn_eliminarAutores.setBounds(73, 177, 89, 80);
		this.frame.getContentPane().add(this.btn_eliminarAutores);
		
		this.btn_editarAutores = new JButton("");
		btn_editarAutores.setBorder(UIManager.getBorder("List.noFocusBorder"));
		btn_editarAutores.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btn_editarAutores.setBackground(new Color(233, 196, 106));
		btn_editarAutores.setIcon(new ImageIcon("C:\\Users\\Usuario\\eclipse-workspace_tres\\PracticaFinalDam1\\Iconos\\InitEditOrigin.png"));
		this.btn_editarAutores.setBounds(73, 255, 89, 80);
		this.frame.getContentPane().add(this.btn_editarAutores);
		
		this.btn_insertarLibros = new JButton("");
		btn_insertarLibros.setBorder(UIManager.getBorder("List.noFocusBorder"));
		btn_insertarLibros.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btn_insertarLibros.setBackground(new Color(233, 196, 106));
		btn_insertarLibros.setIcon(new ImageIcon("C:\\Users\\Usuario\\eclipse-workspace_tres\\PracticaFinalDam1\\Iconos\\InitInsertLibroOrigin.png"));
		this.btn_insertarLibros.setBounds(303, 99, 89, 80);
		this.frame.getContentPane().add(this.btn_insertarLibros);
		
		this.btn_eliminarLibros = new JButton("");
		btn_eliminarLibros.setBorder(UIManager.getBorder("List.noFocusBorder"));
		btn_eliminarLibros.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btn_eliminarLibros.setBackground(new Color(233, 196, 106));
		btn_eliminarLibros.setIcon(new ImageIcon("C:\\Users\\Usuario\\eclipse-workspace_tres\\PracticaFinalDam1\\Iconos\\InitDeleteOrigin.png"));
		this.btn_eliminarLibros.setBounds(390, 99, 89, 80);
		this.frame.getContentPane().add(this.btn_eliminarLibros);
		
		this.btn_editarLibros = new JButton("");
		btn_editarLibros.setBorder(UIManager.getBorder("List.noFocusBorder"));
		btn_editarLibros.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btn_editarLibros.setBackground(new Color(233, 196, 106));
		btn_editarLibros.setIcon(new ImageIcon("C:\\Users\\Usuario\\eclipse-workspace_tres\\PracticaFinalDam1\\Iconos\\InitEditOrigin.png"));
		this.btn_editarLibros.setBounds(303, 177, 89, 80);
		this.frame.getContentPane().add(this.btn_editarLibros);
		
		this.btn_mostrarLibros = new JButton("");
		btn_mostrarLibros.setBorder(UIManager.getBorder("List.noFocusBorder"));
		btn_mostrarLibros.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btn_mostrarLibros.setBackground(new Color(233, 196, 106));
		btn_mostrarLibros.setIcon(new ImageIcon("C:\\Users\\Usuario\\eclipse-workspace_tres\\PracticaFinalDam1\\Iconos\\InitViewOrigin.png"));
		this.btn_mostrarLibros.setBounds(390, 177, 89, 80);
		this.frame.getContentPane().add(this.btn_mostrarLibros);
		this.btn_InsertarAutores.addActionListener(this);
		this.btn_insertarLibros.addActionListener(this);
		this.btn_eliminarAutores.addActionListener(this);
		this.btn_eliminarLibros.addActionListener(this);
		this.btn_editarAutores.addActionListener(this);
		this.btn_editarLibros.addActionListener(this);
		this.btn_mostrarLibros.addActionListener(this);
		//Aqui van los mouselisteners
		this.btn_InsertarAutores.addMouseListener(this);
		this.btn_eliminarAutores.addMouseListener(this);
		this.btn_editarAutores.addMouseListener(this);
		this.btn_insertarLibros.addMouseListener(this);
		this.btn_eliminarLibros.addMouseListener(this);
		this.btn_editarLibros.addMouseListener(this);
		this.btn_mostrarLibros.addMouseListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == this.btn_InsertarAutores) {
			try {
				this.insertarAutor = new InsertAutor();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			this.insertarAutor.visualize();
			this.frame.setVisible(false);
		}else if (e.getSource() == this.btn_insertarLibros) {
			try {
				InsertBook insercionLibro = new InsertBook();
				insercionLibro.visualize();
				this.frame.setVisible(false);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
//			insercionLibro.visualize();
		}else if (e.getSource() == this.btn_editarAutores) {
			EditAutor edicionAutor = null;
			try {
				edicionAutor = new EditAutor();
				edicionAutor.visualize();
				this.frame.setVisible(false);
				
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}else if (e.getSource() == this.btn_editarLibros) {
			EditBook edicionLibro;
			edicionLibro = null;
			try {
				edicionLibro = new EditBook();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			edicionLibro.visualize();
			this.frame.setVisible(false);
		}else if (e.getSource() == this.btn_eliminarAutores) {
			DeleteAutor deleteAutor = null;
			try {
				deleteAutor = new DeleteAutor();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			deleteAutor.visualize();
			this.frame.setVisible(false);
		}else if (e.getSource() == this.btn_eliminarLibros) {
			DeleteBook deletBook;
			try {
				deletBook = new DeleteBook();
				deletBook.visualize();
				this.frame.setVisible(false);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}else if (e.getSource() == this.btn_mostrarLibros) {
			PrintBooks imprimir;
			try {
				imprimir = new PrintBooks(this.autores);
				imprimir.visualize();
				this.frame.setVisible(false);
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
		if (e.getSource() == this.btn_InsertarAutores) {
			this.btn_InsertarAutores.setIcon(this.iconosNuevos.get(0));
		}else if(e.getSource() == this.btn_eliminarAutores) {
			this.btn_eliminarAutores.setIcon(this.iconosNuevos.get(1));
		}else if(e.getSource() == this.btn_editarAutores) {
			this.btn_editarAutores.setIcon(this.iconosNuevos.get(2));
		}else if(e.getSource() == this.btn_insertarLibros) {
			this.btn_insertarLibros.setIcon(this.iconosNuevos.get(3));
		}else if(e.getSource() == this.btn_eliminarLibros) {
			this.btn_eliminarLibros.setIcon(this.iconosNuevos.get(4));
		}else if(e.getSource() == this.btn_editarLibros) {
			this.btn_editarLibros.setIcon(this.iconosNuevos.get(5));
		}else if(e.getSource() == this.btn_mostrarLibros) {
			this.btn_mostrarLibros.setIcon(this.iconosNuevos.get(6));
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == this.btn_InsertarAutores) {
			this.btn_InsertarAutores.setIcon(this.imagenesIconos.get(0));
		}else if(e.getSource() == this.btn_eliminarAutores) {
			this.btn_eliminarAutores.setIcon(this.imagenesIconos.get(1));
		}else if(e.getSource() == this.btn_editarAutores) {
			this.btn_editarAutores.setIcon(this.imagenesIconos.get(2));
		}else if(e.getSource() == this.btn_insertarLibros) {
			this.btn_insertarLibros.setIcon(this.imagenesIconos.get(3));
		}else if(e.getSource() == this.btn_eliminarLibros) {
			this.btn_eliminarLibros.setIcon(this.imagenesIconos.get(4));
		}else if(e.getSource() == this.btn_editarLibros) {
			this.btn_editarLibros.setIcon(this.imagenesIconos.get(5));
		}else if(e.getSource() == this.btn_mostrarLibros) {
			this.btn_mostrarLibros.setIcon(this.imagenesIconos.get(6));
		}
	}
}
