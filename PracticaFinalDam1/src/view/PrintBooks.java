package view;

import java.awt.EventQueue;
import javax.swing.plaf.basic.BasicScrollBarUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.UIManager;

import controller.ControlAutores;
import controller.ControlLibros;
import model.Autor;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Image;

public class PrintBooks extends JFrame implements ActionListener, MouseListener{

	private JFrame frame;
	private JLabel lblTituloVista;
	private JLabel lblAutor;
	private JLabel lblGenero;
	private JComboBox autoresDesp;
	private JComboBox generoDesp;
	private ControlLibros gestionLibros = new ControlLibros();
	private ControlAutores gestionAutores = new ControlAutores();
	private JScrollPane scrollLibros;
	private String strGenero;
	private ArrayList<Autor> autores;
	private JButton btnVolver;
	private String autor;
	private String nombre;
	private String apellido;
	private String segundoApellido;
	private String genero;
	private JButton btnBuscar;
	private ArrayList<ImageIcon> iconosOriginales;
	private ArrayList<ImageIcon> iconosNuevos;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PrintBooks window = new PrintBooks();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public PrintBooks() throws SQLException{
		iniciarComponentes();
		this.genero = "";
		this.autor = "";
	}
	
	/**
	 * Create the application.
	 * @throws SQLException 
	 */
	public PrintBooks(ArrayList<Autor> autores) throws SQLException {
		this.autores = autores;
		iniciarComponentes();
		this.genero = "";
		this.autor = "";

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
		iconosOriginales.add(new ImageIcon("C:\\Users\\Usuario\\eclipse-workspace_tres\\PracticaFinalDam1\\Iconos\\verOrigin.png"));
		
		ArrayList<ImageIcon> iconoAzul = new ArrayList<>();
		iconoAzul.add(new ImageIcon("C:\\Users\\Usuario\\eclipse-workspace_tres\\PracticaFinalDam1\\Iconos\\atrasMod.png"));
		iconoAzul.add(new ImageIcon("C:\\Users\\Usuario\\eclipse-workspace_tres\\PracticaFinalDam1\\Iconos\\verMod.png"));
		
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

		
		this.lblTituloVista = new JLabel("Ver libros");
		lblTituloVista.setFont(new Font("Yu Gothic UI Semilight", Font.BOLD, 25));
		this.lblTituloVista.setBounds(215, 23, 118, 32);
		
		this.lblGenero = new JLabel("Genero:");
		this.lblGenero.setBounds(68, 87, 46, 14);
		
		this.lblAutor = new JLabel("Autor:");
		this.lblAutor.setBounds(245, 87, 46, 14);
		
		
		
		ArrayList<String> generoLista = this.gestionLibros.generos();
		generoLista.add(0, "");
		String[] generos = generoLista.toArray(new String[0]);
		//El de genero
		this.generoDesp = new JComboBox(generos);
		this.generoDesp.setBounds(124, 83, 76, 22);
		this.generoDesp.setBackground(new Color(233, 196, 106));
		ArrayList<String> autoresArray = new ArrayList<>();			
		int i = 0;
		int y = 0;

		for (Autor autor: this.autores) {
			String str = "";
			str += autor.getNombre() + " ";
			str += autor.getApellido() + " ";
			str += autor.getSegundoApellido();
			autoresArray.add(str);
		}
		autoresArray.add(0, "");
		String[] autores = autoresArray.toArray(new String[0]);
		
		
		//El de autores
		this.autoresDesp = new JComboBox(autores);
		this.autoresDesp.setBounds(301, 83, 165, 22);
		this.autoresDesp.setBackground(new Color(233, 196, 106));
		
		this.btnVolver = new JButton("");
		this.btnVolver.setBounds(142, 289, 86, 32);
		this.btnVolver.setBackground(new Color(233, 196, 106));
		this.btnVolver.setBorder(UIManager.getBorder("List.noFocusBorder"));
		this.btnVolver.setIcon(new ImageIcon("C:\\Users\\Usuario\\eclipse-workspace_tres\\PracticaFinalDam1\\Iconos\\atrasOrigin.png"));
		
		this.btnBuscar = new JButton("");
		this.btnBuscar.setBounds(280, 289, 86, 32);
		this.btnBuscar.setBackground(new Color(233, 196, 106));
		this.btnBuscar.setBorder(UIManager.getBorder("List.noFocusBorder"));
		this.btnBuscar.setIcon(new ImageIcon("C:\\Users\\Usuario\\eclipse-workspace_tres\\PracticaFinalDam1\\Iconos\\verOrigin.png"));
		
		this.scrollLibros = new JScrollPane();
		this.scrollLibros.setBounds(112, 127, 298, 112);
		
		this.scrollLibros.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
		    @Override
		    protected void configureScrollBarColors() {
		        this.thumbColor = new Color(233, 196, 106);
		    }
		});
	}
	
	public void iniciarComponentes() {
		initialize();
		this.frame.getContentPane().add(this.lblTituloVista);
		this.frame.getContentPane().add(this.lblGenero);
		this.frame.getContentPane().add(this.lblAutor);
		this.frame.getContentPane().add(this.generoDesp);
		this.frame.getContentPane().add(this.autoresDesp);
		this.frame.getContentPane().add(this.btnVolver);
		this.frame.getContentPane().add(this.scrollLibros);

		
		
		this.frame.getContentPane().add(this.btnBuscar);
		this.btnVolver.addActionListener(this);
		this.btnBuscar.addActionListener(this);
		this.generoDesp.addActionListener(this);
		this.autoresDesp.addActionListener(this);
		this.btnVolver.addMouseListener(this);
		this.btnBuscar.addMouseListener(this);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == this.btnVolver) {
			this.frame.setVisible(false);
			try {
				Principal principal = new Principal();
				principal.visualize();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}else if (e.getSource() == this.autoresDesp) {
			this.autor = (String) this.autoresDesp.getSelectedItem();
			String [] autoresSeparados = this.autor.split(" ");
			this.nombre = autoresSeparados[0];
			this.apellido = autoresSeparados[1];
			this.segundoApellido = autoresSeparados[2];
			
			
		}else if (e.getSource() == this.generoDesp) {
			this.genero =(String) this.generoDesp.getSelectedItem();
		}else if (e.getSource() == this.btnBuscar) {
			if (!this.autor.equals("") && !this.genero.equals("")) {
				ArrayList<String> busquedaTodo = this.gestionLibros.seleccionarLibroAutorGenero(this.gestionLibros.convertirGeneroNumero(this.genero), nombre, apellido, segundoApellido);
				String[] array = busquedaTodo.toArray(new String[0]);
				JList<String> lista = new JList<>(array);
				this.scrollLibros.setViewportView(lista);
				this.scrollLibros.getViewport().setBackground(new Color(233, 196, 106));;
				System.out.println("holaaaa" + this.autor + " " + this.genero);
				System.out.println(busquedaTodo);
				this.autor = "";
				this.genero = "";

			}else if (!this.autor.equals("")) {
				ArrayList<String> busquedaAutores = this.gestionLibros.seleccionarLibroAutor(nombre, apellido, segundoApellido);
				String[] array = busquedaAutores.toArray(new String[0]);
				JList<String> lista = new JList<>(array);
				this.scrollLibros.setViewportView(lista);
				this.scrollLibros.getViewport().setBackground(new Color(233, 196, 106));;
				this.autor = "";
			}else if (!this.genero.equals("")) {
				ArrayList<String> busquedaGenero = this.gestionLibros.seleccionarLibroGenero(this.gestionLibros.convertirGeneroNumero(this.genero));
				String[] array = busquedaGenero.toArray(new String[0]);
				JList<String> lista = new JList<>(array);
				this.scrollLibros.setViewportView(lista);
				this.scrollLibros.getViewport().setBackground(new Color(233, 196, 106));;
				this.genero = "";
			}else {
				ArrayList<String> busquedaTodo = this.gestionLibros.seleccionarTodo();
				String[] array = busquedaTodo.toArray(new String[0]);
				JList<String> lista = new JList<>(array);
				this.scrollLibros.getViewport().setBackground(new Color(233, 196, 106));
				this.scrollLibros.setViewportView(lista);
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
		}else if (e.getSource() == this.btnBuscar) {
			this.btnBuscar.setIcon(this.iconosNuevos.get(1));
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == this.btnVolver) {
			this.btnVolver.setIcon(this.iconosOriginales.get(0));
		}else if (e.getSource() == this.btnBuscar) {
			this.btnBuscar.setIcon(this.iconosOriginales.get(1));
		}
	}
}
