package view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.UIManager;

import controller.ControlLibros;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JComboBox;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JSeparator;

public class EditBook extends JFrame implements ActionListener, MouseListener{

	private JFrame frame;
	private JLabel lblTitulo;
	private JLabel lblTituloLibro;
	private JLabel lblISBN;
	private JLabel lblOpcion;
	private JTextField txtTitulo;
	private JTextField txtISBN;
	private JButton btnvolver;
	private JButton btnEdicion;
	private JComboBox desplegable;
	private String opcionDesplegable;
	private String tituloLibro;
	private String ISBNLibro;
	private ControlLibros gestionLibros = new ControlLibros();
	private ArrayList<ImageIcon> iconosOriginales;
	private ArrayList<ImageIcon> iconosNuevos;
	private JSeparator separator;

	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditBook window = new EditBook();
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
	public EditBook() throws SQLException {
		this.tituloLibro = "";
		this.ISBNLibro = "";
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
	 */
	private void initialize() {
		convertirIconos();
		frame = new JFrame();
		frame.setBounds(400, 160, 550, 400);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.frame.getContentPane().setLayout(null);
		frame.getContentPane().setBackground(new Color(42, 157, 143));

		
		this.txtTitulo = new JTextField();
		this.txtTitulo.setBounds(188, 161, 203, 20);
		this.txtTitulo.setColumns(10);
		this.txtTitulo.setVisible(false);
		this.txtTitulo.setBackground(new Color(42, 157, 143));
		this.txtTitulo.setBorder(null);
		
		this.lblTitulo = new JLabel("Escoger autor:");
		lblTitulo.setFont(new Font("Yu Gothic UI Semilight", Font.BOLD, 25));
		this.lblTitulo.setBounds(188, 23, 203, 31);
		
		this.lblTituloLibro = new JLabel("Titulo:");
		this.lblTituloLibro.setBounds(115, 164, 46, 14);
		this.lblTituloLibro.setVisible(false);
		
		this.lblISBN = new JLabel("ISBN:");
		this.lblISBN.setBounds(115, 164, 46, 14);
		this.lblISBN.setVisible(false);
		
		this.txtISBN = new JTextField();
		this.txtISBN.setColumns(10);
		this.txtISBN.setBounds(188, 161, 203, 20);
		this.txtISBN.setVisible(false);
		this.txtISBN.setBackground(new Color(42, 157, 143));
		this.txtISBN.setBorder(null);
		
		this.btnvolver = new JButton("");
		btnvolver.setIcon(new ImageIcon("C:\\Users\\Usuario\\eclipse-workspace_tres\\PracticaFinalDam1\\Iconos\\atrasOrigin.png"));
		this.btnvolver.setBounds(146, 254, 86, 32);
		this.btnvolver.setBackground(new Color(233, 196, 106));
		this.btnvolver.setBorder(UIManager.getBorder("List.noFocusBorder"));
		
		this.btnEdicion = new JButton("");
		btnEdicion.setIcon(new ImageIcon("C:\\Users\\Usuario\\eclipse-workspace_tres\\PracticaFinalDam1\\Iconos\\adelanteOrigin.png"));
		this.btnEdicion.setBounds(283, 254, 86, 32);
		this.btnEdicion.setBackground(new Color(233, 196, 106));
		this.btnEdicion.setBorder(UIManager.getBorder("List.noFocusBorder"));
		
		String[] desplegableArray = {"", "ISBN", "Título"};
		this.desplegable = new JComboBox(desplegableArray);
		this.desplegable.setBounds(306, 86, 90, 20);
		this.desplegable.setBackground(new Color(233, 196, 106));
		
		iniciarComponentes();

	}
	
	public void iniciarComponentes() {
		this.frame.getContentPane().add(this.txtTitulo);
		this.frame.getContentPane().add(this.lblTitulo);
		this.frame.getContentPane().add(this.lblTituloLibro);
	 	this.frame.getContentPane().add(this.lblISBN);
		this.frame.getContentPane().add(this.txtISBN);
		this.frame.getContentPane().add(this.btnvolver);
		this.frame.getContentPane().add(this.btnEdicion);
		this.frame.getContentPane().add(this.desplegable);
		
		JLabel lblNewLabel = new JLabel("Elija como quiere buscar el libro:");
		lblNewLabel.setBounds(109, 89, 162, 14);
		frame.getContentPane().add(lblNewLabel);
		
		separator = new JSeparator();
		separator.setBackground(new Color(0, 0, 0));
		separator.setBounds(188, 181, 203, 14);
		frame.getContentPane().add(separator);
		this.separator.setVisible(false);
		
		this.btnEdicion.addActionListener(this);
		this.btnvolver.addActionListener(this);
		this.desplegable.addActionListener(this);
		this.btnEdicion.addMouseListener(this);
		this.btnvolver.addMouseListener(this);
		

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		//Si se le da click al desplegable y se selecciona un elemento
		if(e.getSource() == this.desplegable) {
			//Guardamos el elementon en la variable
			this.opcionDesplegable = (String) this.desplegable.getSelectedItem();
			//Si el elemento es igual a ISBN entonces desaparece el campo de texto de titulo de libro y el label y aparecerá el de ISBN
			if(this.opcionDesplegable.equals("ISBN")) {
				this.lblISBN.setVisible(true);
				this.txtISBN.setVisible(true);
				this.lblTituloLibro.setVisible(false);
				this.txtTitulo.setVisible(false);
				this.separator.setVisible(true);
			//Si el elemento es igual a titulo libro entonces desaparece el campo de texto de ISN y el label y aparece el de titulo
			}else if(this.opcionDesplegable.equals("Título")) {
				this.lblTituloLibro.setVisible(true);
				this.txtTitulo.setVisible(true);
				this.lblISBN.setVisible(false);
				this.txtISBN.setVisible(false);
				this.separator.setVisible(true);
			}
		//Si pulsa el boton de editar
		}else if(e.getSource() == this.btnEdicion) {
			//guardamos los valores en las variables correspondientes
			this.tituloLibro = this.txtTitulo.getText();
			this.ISBNLibro = this.txtISBN.getText();
			//Creamos una variable de estado. 0: Correcto, 1: El libro no existe, 2: No puedes dejar un campo en blanco
			int respuesta = 0;
			int confirmacion = JOptionPane.showConfirmDialog(this.frame, "Estas seguro que quieres actualizar este libro", "confirmacion", JOptionPane.YES_NO_OPTION);
			if (confirmacion == 0) {
				//Una vez confirmado se 
				if (!this.tituloLibro.equals("")) {
					try {
						respuesta = this.gestionLibros.comprobarExistRegistrosTitulo(tituloLibro);
						this.ISBNLibro = this.gestionLibros.comprobarExistTituloLibro(tituloLibro);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}else if (!this.ISBNLibro.equals("")) {
					try {
						respuesta = this.gestionLibros.comprobarExistRegistrosISBN(ISBNLibro);
					}catch(SQLException e1) {
						e1.printStackTrace();
					}
				}else {
					respuesta = 2;
				}
			}
			
			if (respuesta == 0) {
				EditDosBook editDosLibro = null;
				try {
					editDosLibro = new EditDosBook(this.ISBNLibro);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				editDosLibro.visualize();
			}else if (respuesta == 1) {
				JOptionPane.showMessageDialog(this.frame, "El libro no existe!", "Error", JOptionPane.ERROR_MESSAGE);
			}else if (respuesta == 2) {
				JOptionPane.showMessageDialog(this.frame, "Debe introducir un campo!", "Error", JOptionPane.ERROR_MESSAGE);
			}
			
		}else if (e.getSource() == this.btnvolver) {
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
		if (e.getSource() == this.btnvolver) {
			this.btnvolver.setIcon(this.iconosNuevos.get(0));
		}else if (e.getSource() == this.btnEdicion) {
			this.btnEdicion.setIcon(this.iconosNuevos.get(1));
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == this.btnvolver) {
			this.btnvolver.setIcon(this.iconosOriginales.get(0));
		}else if(e.getSource() == this.btnEdicion) {
			this.btnEdicion.setIcon(this.iconosOriginales.get(1));
		}
	}
}
