package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.UIManager;

import controller.ControlLibros;

import javax.swing.JComboBox;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Image;
import javax.swing.JSeparator;

public class EditDosBook extends JFrame implements ActionListener, MouseListener{

	private JFrame frame;
	private JLabel lblTituloVista;
	private JLabel lblTituloLibro;
	private JLabel lblAutorNombre;
	private JLabel lblAutorApellido;
	private JLabel lblAutorSegundoApellido;
	private JLabel lblNumPag;
	private JLabel lblGenero;
	private JTextField txtTitulo;
	private JTextField txtAutorNombre;
	private JTextField txtAutorApellido;
	private JTextField txtAutorSegundoApellido;
	private JSpinner txtPaginas;
	private JComboBox txtGenero;
	private JButton btnVolver;
	private JButton btnInsertar;
	private String titulo;
	private String nombreAutor;
	private String apellidoAutor;
	private String segundoApellidoAutor;
	private String ISBNActual;
	private int numPaginas;
	private String genero;
	private Map<String, Object> valoresForm; 
	private ControlLibros gestionLibros = new ControlLibros();
	private ArrayList<ImageIcon> iconosOriginales;
	private ArrayList<ImageIcon> iconosNuevos;
	private JSeparator separator;
	private JSeparator separator_1;
	private JSeparator separator_2;
	private JSeparator separator_3;
	
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditDosBook window = new EditDosBook();
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
	public EditDosBook() throws SQLException{
		this.nombreAutor = "";
		this.apellidoAutor = "";
		this.segundoApellidoAutor = "";
		this.titulo = "";
		this.numPaginas = 0;
		this.genero = "";
		this.valoresForm = new HashMap<>();
		iniciarComponentes();
		
	}
	
	public EditDosBook(String ISBNActual) throws SQLException{
		this.nombreAutor = "";
		this.apellidoAutor = "";
		this.segundoApellidoAutor = "";
		this.titulo = "";
		this.numPaginas = 0;
		this.genero = "";
		this.ISBNActual = ISBNActual;
		this.valoresForm = new HashMap<>();
		iniciarComponentes();
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
	 */
	private void initialize() {
		convertirIconos();
		frame = new JFrame();
		frame.setBounds(400, 160, 550, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.getContentPane().setBackground(new Color(42, 157, 143));

		
		this.lblTituloVista = new JLabel("Añadir libro:");
		lblTituloVista.setFont(new Font("Yu Gothic UI Semilight", Font.BOLD, 25));
		this.lblTituloVista.setBounds(190, 28, 152, 31);
		
		this.lblTituloLibro = new JLabel("Titulo:");
		this.lblTituloLibro.setBounds(64, 89, 92, 14);
		
		this.lblAutorNombre = new JLabel("Nombre Autor:");
		this.lblAutorNombre.setBounds(64, 120, 92, 14);
		
		this.lblAutorApellido = new JLabel("Apellido Autor:");
		this.lblAutorApellido.setBounds(64, 151, 92, 14);
		
		this.lblAutorSegundoApellido = new JLabel("Segundo Apellido Autor:");
		this.lblAutorSegundoApellido.setBounds(64, 182, 127, 14);
		
		this.lblNumPag = new JLabel("Numero paginas:");
		this.lblNumPag.setBounds(64, 213, 104, 14);
		
		this.lblGenero = new JLabel("Genero:");
		this.lblGenero.setBounds(64, 244, 92, 14);
		
		this.txtAutorSegundoApellido = new JTextField();
		this.txtAutorSegundoApellido.setBounds(201, 179, 249, 20);
		this.txtAutorSegundoApellido.setColumns(10);
		this.txtAutorSegundoApellido.setBackground(new Color(42, 157, 143));
		this.txtAutorSegundoApellido.setBorder(null);
		
		this.txtAutorApellido = new JTextField();
		this.txtAutorApellido.setColumns(10);
		this.txtAutorApellido.setBounds(201, 148, 249, 20);
		this.txtAutorApellido.setBorder(null);
		this.txtAutorApellido.setBackground(new Color(42, 157, 143));
		
		this.txtAutorNombre = new JTextField();
		this.txtAutorNombre.setColumns(10);
		this.txtAutorNombre.setBounds(201, 117, 249, 20);
		this.txtAutorNombre.setBackground(new Color(42, 157, 143));
		this.txtAutorNombre.setBorder(null);
		
		this.txtTitulo = new JTextField();
		this.txtTitulo.setColumns(10);
		this.txtTitulo.setBounds(201, 86, 249, 20);
		this.txtTitulo.setBackground(new Color(42, 157, 143));
		this.txtTitulo.setBorder(null);
		
		SpinnerNumberModel numberModel = new SpinnerNumberModel(0, 0, Integer.MAX_VALUE, 1); // Valores mínimo, máximo e incremento

		this.txtPaginas = new JSpinner(numberModel);
		this.txtPaginas.setBounds(201, 210, 98, 20);
		
		ArrayList<String> generosList = this.gestionLibros.generos();
		generosList.add(0, "");
		String[] generos = generosList.toArray(new String[0]);
		this.txtGenero = new JComboBox(generos);
		this.txtGenero.setBounds(201, 240, 98, 22);
		this.txtGenero.setBackground(new Color(233, 196, 106	));
		
		this.btnVolver = new JButton("");
		btnVolver.setIcon(new ImageIcon("C:\\Users\\Usuario\\eclipse-workspace_tres\\PracticaFinalDam1\\Iconos\\atrasOrigin.png"));
		this.btnVolver.setBounds(137, 295, 86, 32);
		this.btnVolver.setBackground(new Color(233, 196, 106));
		this.btnVolver.setBorder(UIManager.getBorder("List.noFocusBorder"));
		
		this.btnInsertar = new JButton("");
		btnInsertar.setIcon(new ImageIcon("C:\\Users\\Usuario\\eclipse-workspace_tres\\PracticaFinalDam1\\Iconos\\editOrigin.png"));
		this.btnInsertar.setBounds(297, 295, 86, 32);
		this.btnInsertar.setBackground(new Color(233, 196, 106));
		this.btnInsertar.setBorder(UIManager.getBorder("List.noFocusBorder"));
	}
	
	public void iniciarComponentes() {
		initialize();
		frame.getContentPane().add(this.lblTituloVista);
		frame.getContentPane().add(this.lblTituloLibro);
		frame.getContentPane().add(this.lblAutorNombre);
		frame.getContentPane().add(this.lblAutorApellido);
		frame.getContentPane().add(this.lblAutorSegundoApellido);
		frame.getContentPane().add(this.lblNumPag);
		frame.getContentPane().add(this.lblGenero);
		frame.getContentPane().add(this.txtAutorSegundoApellido);
		frame.getContentPane().add(this.txtAutorApellido);
		frame.getContentPane().add(this.txtAutorNombre);
		frame.getContentPane().add(this.txtTitulo);
		frame.getContentPane().add(this.txtPaginas);
		frame.getContentPane().add(this.txtGenero);
		frame.getContentPane().add(this.btnVolver);
		frame.getContentPane().add(this.btnInsertar);
		
		separator = new JSeparator();
		separator.setBackground(new Color(0, 0, 0));
		separator.setBounds(200, 106, 250, 14);
		frame.getContentPane().add(separator);
		
		separator_1 = new JSeparator();
		separator_1.setBackground(Color.BLACK);
		separator_1.setBounds(201, 139, 250, 14);
		frame.getContentPane().add(separator_1);
		
		separator_2 = new JSeparator();
		separator_2.setBackground(Color.BLACK);
		separator_2.setBounds(200, 168, 250, 14);
		frame.getContentPane().add(separator_2);
		
		separator_3 = new JSeparator();
		separator_3.setBackground(Color.BLACK);
		separator_3.setBounds(200, 198, 250, 14);
		frame.getContentPane().add(separator_3);
		
		this.btnInsertar.addActionListener(this);
		this.btnVolver.addActionListener(this);
		this.txtGenero.addActionListener(this);
		this.btnVolver.addMouseListener(this);
		this.btnInsertar.addMouseListener(this);
		

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == this.btnInsertar) {
			this.titulo = this.txtTitulo.getText();
			this.nombreAutor = this.txtAutorNombre.getText();
			this.apellidoAutor = this.txtAutorApellido.getText();
			this.segundoApellidoAutor = this.txtAutorSegundoApellido.getText();
			this.numPaginas =(int) this.txtPaginas.getValue();
			int resultado = 0;
			int confirmacion = JOptionPane.showConfirmDialog(this.frame, "Seguro que quiere añadir el libro?", "Confirmacion", JOptionPane.YES_NO_OPTION);
			if(confirmacion == 0) {
				if (!this.titulo.equals("") || !this.nombreAutor.equals("") || !this.apellidoAutor.equals("") || !this.segundoApellidoAutor.equals("") || !this.genero.equals("") || this.numPaginas > 0) {
				    // Realizar acciones si al menos una de las variables no está vacía

				    // Aquí puedes incluir las acciones que deseas realizar para cada combinación
				    if (!this.titulo.equals("")) {
				        // Acción cuando el título no está vacío
				        this.valoresForm.put("tituloLibro", this.titulo);
				    }

				    if (!this.nombreAutor.equals("")) {
				        // Acción cuando el nombre del autor no está vacío
				        this.valoresForm.put("AutorNombre", this.nombreAutor);
				    }

				    if (!this.apellidoAutor.equals("")) {
				        // Acción cuando el apellido del autor no está vacío
				        this.valoresForm.put("AutorApellido", this.apellidoAutor);
				    }

				    if (!this.segundoApellidoAutor.equals("")) {
				        // Acción cuando el segundo apellido del autor no está vacío
				        this.valoresForm.put("AutorApellidoDos", this.segundoApellidoAutor);
				    }
				    
				    if(this.numPaginas > 0) {
				    	this.valoresForm.put("numeroPaginas", this.numPaginas);
				    }

				    if (!this.genero.equals("")) {
				        // Acción cuando el género no está vacío
				        this.valoresForm.put("Genero", this.gestionLibros.convertirGeneroNumero(this.genero));
				    }
				    try {
				    	resultado = this.gestionLibros.actualizacionLibro(this.ISBNActual, this.valoresForm);
				    }catch(IndexOutOfBoundsException e1) {
					    
				    }
				    
				}else {
					resultado = 2;
				}
				
				if(resultado == 0) {
					JOptionPane.showMessageDialog(this.frame, "Se ha actualizado el libro", "Confirmacion", JOptionPane.INFORMATION_MESSAGE);
				}else if(resultado == 1) {
					JOptionPane.showMessageDialog(this.frame, "No se ha actualizado el libro!", "Error", JOptionPane.ERROR_MESSAGE);
				}else if (resultado == 2) {
					JOptionPane.showMessageDialog(this.frame, "Debe completar al menos un campo!", "Error", JOptionPane.ERROR_MESSAGE);

				}
			}
		}else if (e.getSource() == this.txtGenero) {
			this.genero =(String) this.txtGenero.getSelectedItem();
		}else if(e.getSource() == this.btnVolver) {
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
		if (e.getSource() == this.btnVolver) {
			this.btnVolver.setIcon(this.iconosNuevos.get(0));
		}else if(e.getSource() == this.btnInsertar) {
			this.btnInsertar.setIcon(this.iconosNuevos.get(1));
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == this.btnVolver) {
			this.btnVolver.setIcon(this.iconosOriginales.get(0));
		}else if(e.getSource() == this.btnInsertar) {
			this.btnInsertar.setIcon(this.iconosOriginales.get(1));
		}
	}
}
