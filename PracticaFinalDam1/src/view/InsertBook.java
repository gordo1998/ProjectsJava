package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.UIManager;

import controller.ControlLibros;

import javax.swing.JComboBox;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JSeparator;

public class InsertBook extends JPanel implements ActionListener, MouseListener{

	private JFrame frame;
	private JLabel lblISBN;
	private JLabel lblTituloVista;
	private JLabel lblTitulo;
	private JLabel lblNombreAutor;
	private JLabel lblApellidoAutor;
	private JLabel lblSegundoApellidoAutor;
	private JLabel lblNumPag;
	private JLabel lblgenero;
	private JTextField txtISBN;
	private JTextField txtTitulo;
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JTextField txtSegundoApellido;
	private JSpinner txtNumeroPags;
	private JComboBox txtGenero;
	private String ISBN;
	private String titulo;
	private String nombre;
	private String apellido;
	private String segundoApellido;
	private int numPaginas;
	private String genero;
	private JButton btnVolver;
	private JButton btnInsertar;
	private ControlLibros gestionLibros = new ControlLibros();
	private JTextField textField;
	private JSeparator separator;
	private JSeparator separator_1;
	private JSeparator separator_2;
	private JSeparator separator_3;
	private JSeparator separator_4;
	private ArrayList<ImageIcon> iconosOriginales;
	private ArrayList<ImageIcon> iconosNuevos;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InsertBook window = new InsertBook();
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
	public InsertBook() throws SQLException{
		iniciarComponentes();
		this.ISBN = "";
		this.titulo = "";
		this.nombre = "";
		this.apellido = "";
		this.segundoApellido = "";
		this.numPaginas = 0;
		this.genero = "";
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
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(400, 160, 550, 400);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.getContentPane().setBackground(new Color(42, 157, 143));

		
		this.lblTituloVista = new JLabel("Insertar Libro");
		lblTituloVista.setFont(new Font("Yu Gothic UI Semilight", Font.BOLD, 25));
		this.lblTituloVista.setBounds(191, 11, 202, 38);
		
		this.lblISBN = new JLabel("ISBN:");
		this.lblISBN.setBounds(110, 89, 84, 14);
		this.frame.getContentPane().add(this.lblISBN);
		
		this.lblTitulo = new JLabel("Titulo:");
		this.lblTitulo.setBounds(110, 114, 84, 14);
		
		this.lblNombreAutor = new JLabel("Nombre Aut:");
		this.lblNombreAutor.setBounds(110, 139, 84, 14);
		
		this.lblApellidoAutor = new JLabel("Apellido Auto:");
		this.lblApellidoAutor.setBounds(110, 164, 84, 14);
		
		this.lblSegundoApellidoAutor = new JLabel("Segundo Ape:");
		this.lblSegundoApellidoAutor.setBounds(110, 189, 84, 14);
		
		this.lblgenero = new JLabel("Género:");
		this.lblgenero.setBounds(110, 214, 84, 14);
		
		this.lblNumPag = new JLabel("Cantidad pág.:");
		this.lblNumPag.setBounds(110, 239, 84, 14);
		
		this.txtISBN = new JTextField();
		this.txtISBN.setColumns(10);
		this.txtISBN.setBounds(207, 86, 226, 20);
		this.txtISBN.setBackground(new Color(42, 157, 143));
		this.txtISBN.setBorder(null);
		
		this.txtTitulo = new JTextField();
		this.txtTitulo.setBounds(207, 111, 226, 20);
		this.txtTitulo.setColumns(10);
		this.txtTitulo.setBackground(new Color(42, 157, 143));
		this.txtTitulo.setBorder(null);

		
		this.txtNombre = new JTextField();
		this.txtNombre.setColumns(10);
		this.txtNombre.setBounds(207, 136, 226, 20);
		this.txtNombre.setBackground(new Color(42, 157, 143));
		this.txtNombre.setBorder(null);
		
		this.txtApellido = new JTextField();
		this.txtApellido.setColumns(10);
		this.txtApellido.setBounds(207, 161, 226, 20);
		this.txtApellido.setBackground(new Color(42, 157, 143));
		this.txtApellido.setBorder(null);
		
		this.txtSegundoApellido = new JTextField();
		this.txtSegundoApellido.setColumns(10);
		this.txtSegundoApellido.setBounds(207, 186, 226, 20);
		this.txtSegundoApellido.setBackground(new Color(42, 157, 143));
		this.txtSegundoApellido.setBorder(null);
		
		this.txtNumeroPags = new JSpinner();
		this.txtNumeroPags.setBounds(207, 236, 84, 20);
		this.txtNumeroPags.setBackground(new Color(0, 64, 0));
		
		ArrayList<String> generosList = this.gestionLibros.generos();
		generosList.add(0, "");
		String[] generos = generosList.toArray(new String[0]);
		this.txtGenero  = new JComboBox(generos);
		this.txtGenero.setBounds(207, 211, 86, 22);
		this.txtGenero.setBackground(new Color(233, 196, 106));
		
		this.btnVolver = new JButton("");
		this.btnVolver.setBounds(158, 280, 86, 32);
		this.btnVolver.setBorder(UIManager.getBorder("List.noFocusBorder"));
		this.btnVolver.setBackground(new Color(233, 196, 106));
		this.btnVolver.setIcon(new ImageIcon("C:\\Users\\Usuario\\eclipse-workspace_tres\\PracticaFinalDam1\\Iconos\\atrasOrigin.png"));
		
		this.btnInsertar = new JButton("");
		this.btnInsertar.setBounds(307, 280, 86, 32);
		this.btnInsertar.setBorder(UIManager.getBorder("List.noFocusBorder"));
		this.btnInsertar.setBackground(new Color(233, 196, 106));
		this.btnInsertar.setIcon(new ImageIcon("C:\\Users\\Usuario\\eclipse-workspace_tres\\PracticaFinalDam1\\Iconos\\InsertOriginal.png"));
	}
	
	public void iniciarComponentes() {
		initialize();
		convertirIconos();
		frame.getContentPane().add(this.lblTituloVista);
		frame.getContentPane().add(this.lblTitulo);
		frame.getContentPane().add(this.lblNombreAutor);
		frame.getContentPane().add(this.lblApellidoAutor);
		frame.getContentPane().add(this.lblSegundoApellidoAutor);
		frame.getContentPane().add(this.lblgenero);
		frame.getContentPane().add(this.lblNumPag);
		frame.getContentPane().add(this.txtTitulo);
		frame.getContentPane().add(this.txtNombre);
		frame.getContentPane().add(this.txtApellido);
		frame.getContentPane().add(this.txtSegundoApellido);
		this.frame.getContentPane().add(this.txtNumeroPags);
		frame.getContentPane().add(this.txtGenero);
		frame.getContentPane().add(this.btnVolver);
		frame.getContentPane().add(this.btnInsertar);
		
		
		frame.getContentPane().add(this.txtISBN);
		
		separator = new JSeparator();
		separator.setBackground(new Color(0, 0, 0));
		separator.setBounds(207, 109, 226, 19);
		frame.getContentPane().add(separator);
		
		separator_1 = new JSeparator();
		separator_1.setBackground(new Color(0, 0, 0));
		separator_1.setBounds(207, 134, 226, 19);
		frame.getContentPane().add(separator_1);
		
		separator_2 = new JSeparator();
		separator_2.setBackground(new Color(0, 0, 0));
		separator_2.setBounds(207, 159, 226, 19);
		frame.getContentPane().add(separator_2);
		
		separator_3 = new JSeparator();
		separator_3.setBackground(new Color(0, 0, 0));
		separator_3.setBounds(207, 184, 226, 19);
		frame.getContentPane().add(separator_3);
		
		separator_4 = new JSeparator();
		separator_4.setBackground(Color.BLACK);
		separator_4.setBounds(207, 209, 226, 19);
		frame.getContentPane().add(separator_4);
		
		this.btnInsertar.addActionListener(this);
		this.btnVolver.addActionListener(this);
		this.txtGenero.addActionListener(this);
		this.btnInsertar.addMouseListener(this);
		this.btnVolver.addMouseListener(this);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == this.btnInsertar) {
			this.ISBN = this.txtISBN.getText();
			this.titulo = this.txtTitulo.getText();
			this.nombre = this.txtNombre.getText();
			this.apellido = this.txtApellido.getText();
			this.segundoApellido = this.txtSegundoApellido.getText();
			this.numPaginas = (int)this.txtNumeroPags.getValue();
			int respuesta = 0;
			int confirmacion = JOptionPane.showConfirmDialog(this.frame, "Seguro que quiere añadir este libro?", "Confirmacion", JOptionPane.YES_NO_OPTION);
			if(confirmacion == 0) {
				if(!this.ISBN.equals("") && !this.titulo.equals("") && !this.nombre.equals("") && !this.apellido.equals("") && !this.segundoApellido.equals("") && this.numPaginas > 0 && !this.genero.equals("")) {
					try {
						respuesta = this.gestionLibros.insertarNuevoLibro(ISBN, titulo, nombre, apellido, segundoApellido, numPaginas, genero);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}else {
					respuesta = 3;
				}
				
				if(respuesta == 0) {
					JOptionPane.showMessageDialog(this.frame, "Se ha añadido el libro correctamente!", "confirmacion", JOptionPane.INFORMATION_MESSAGE);
				}else if (respuesta == 1) {
					int autorNo = JOptionPane.showConfirmDialog(this.frame, "El autor no esta en la base de datos! Quieres crearlo?", "Error", JOptionPane.YES_NO_OPTION);
					if (autorNo == 0) {
						try {
							InsertAutor nuevoAutor = new InsertAutor();
							nuevoAutor.visualize();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}else if(respuesta == 2) {
					JOptionPane.showMessageDialog(this.frame, "Ya existe el libro con este ISBN!", "Error", JOptionPane.ERROR_MESSAGE);
				}else if(respuesta == 3) {
					JOptionPane.showMessageDialog(this.frame, "No puede dejar campos en blanco!", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		}else if (e.getSource() == this.txtGenero) {
			this.genero = (String) this.txtGenero.getSelectedItem();
		}else if (e.getSource() == this.btnVolver) {
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
		}else if (e.getSource() == this.btnInsertar) {
			this.btnInsertar.setIcon(this.iconosNuevos.get(1));
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == this.btnVolver) {
			this.btnVolver.setIcon(this.iconosOriginales.get(0));
		}else if (e.getSource() == this.btnInsertar) {
			this.btnInsertar.setIcon(this.iconosOriginales.get(1));
		}
	}
}
