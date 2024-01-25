package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Image;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;

import controller.ControlLibros;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JSeparator;

public class DeleteBook extends JPanel implements ActionListener, MouseListener{

	private JFrame frame;
	private JLabel lblTituloVista;
	private JLabel lblISBN;
	private JTextField txtISBN;
	private String ISBN;
	private JButton btnDelete;
	private JButton btnVolver;
	private ControlLibros gestionLibros = new ControlLibros();
	private JTextField textField;
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
					DeleteBook window = new DeleteBook();
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
	public DeleteBook() throws SQLException{
		iniciarComponentes();
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
		this.frame.getContentPane().setLayout(null);
		frame.getContentPane().setBackground(new Color(42, 157, 143));

		
		this.lblTituloVista = new JLabel("Eliminar Libro");
		lblTituloVista.setFont(new Font("Yu Gothic UI Semilight", Font.BOLD, 25));
		this.lblTituloVista.setBounds(171, 42, 188, 46);

		
		this.lblISBN = new JLabel("ISBN:");
		this.lblISBN.setBounds(137, 141, 44, 14);
		
		this.txtISBN = new JTextField();
		this.txtISBN.setBounds(188, 138, 205, 20);
		this.txtISBN.setColumns(10);
		this.txtISBN.setBackground(new Color(42, 157, 143));
		this.txtISBN.setBorder(null);
		
		this.btnVolver = new JButton("");
		btnVolver.setIcon(new ImageIcon("C:\\Users\\Usuario\\eclipse-workspace_tres\\PracticaFinalDam1\\Iconos\\atrasOrigin.png"));
		this.btnVolver.setBounds(131, 228, 86, 32);
		this.btnVolver.setBackground(new Color(233, 196, 106));
		this.btnVolver.setBorder(UIManager.getBorder("List.noFocusBorder"));
		
		this.btnDelete = new JButton("");
		btnDelete.setIcon(new ImageIcon("C:\\Users\\Usuario\\eclipse-workspace_tres\\PracticaFinalDam1\\Iconos\\deleteOrigin.png"));
		this.btnDelete.setBounds(307, 228, 86, 32);
		this.btnDelete.setBackground(new Color(233, 196, 106));
		this.btnDelete.setBorder(UIManager.getBorder("List.noFocusBorder"));
	}
	
	public void iniciarComponentes() {
		initialize();
		convertirIconos();
		this.frame.getContentPane().add(this.lblTituloVista);
		this.frame.getContentPane().add(this.lblISBN);
		this.frame.getContentPane().add(this.txtISBN);
		this.frame.getContentPane().add(this.btnVolver);
		this.frame.getContentPane().add(this.btnDelete);
		
		separator = new JSeparator();
		separator.setBackground(new Color(0, 0, 0));
		separator.setBounds(188, 160, 205, 20);
		frame.getContentPane().add(separator);
		
		this.btnDelete.addActionListener(this);
		this.btnVolver.addActionListener(this);
		this.btnVolver.addMouseListener(this);
		this.btnDelete.addMouseListener(this);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		//Si se pulsa el boton delete
		if(e.getSource() == this.btnDelete) {
			//Guardamos el contenido del ISBN en la variable ISBN como se muestra a continuación
			this.ISBN = this.txtISBN.getText();
			//Inicializamos una variable entera a 0. Esta variable nos servirá para el control de estado de la respuesta de las consultas
			int resultado = 0;
			//Inicializamos una variable entera que contendrá la respuesta del usuario de la confirmación
			int confirmacion = JOptionPane.showConfirmDialog(this.frame, "Estas seguro de eliminar este libro?", "Confirmacion", JOptionPane.YES_NO_OPTION);
			//Si dice que sí
			if(confirmacion == 0) {
				//Evaluará si el ISBN esta vacion o no, en caso de que no este vacio...
				if (!this.ISBN.equals("")) {
					try {
						//Comprobará que exista el libro. En caso de que exista llamamos a la funcion que elimina el libro y la guardaremos en
						//la variable de estado. En caso de que el la respuesta sea igual a 1, asignaremos a la variable el numero 1, igual con el numero -1
						if(this.gestionLibros.comprobarExistRegistrosISBN(ISBN) == 0) {
							try {
								
								resultado = this.gestionLibros.eliminarLibro(this.ISBN);
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
								resultado = -1;
							}
						}else if (this.gestionLibros.comprobarExistRegistrosISBN(ISBN) == 1) {
							resultado = 1;
						}else if(this.gestionLibros.comprobarExistRegistrosISBN(ISBN) == -1) {
							resultado = -1;
						}
					} catch (SQLException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
						resultado = -1;
					}	
				//Si el usuario no introduce el ISBN se asignará este valor a la variable resultado
				}else {
					resultado = 2;
				}
				//Se ejecutará un JOptioPane con un mensaje distinto dependiendo de la respuesta
				if(resultado == 0) {
					JOptionPane.showMessageDialog(this.frame, "Se ha eliminado el libro!", "confirmacion", JOptionPane.INFORMATION_MESSAGE);
				}else if(resultado == 1) {
					JOptionPane.showMessageDialog(this.frame, "El libro con el ISBN indicado no existe!", "Error", JOptionPane.ERROR_MESSAGE);
				}else if (resultado == 2) {
					JOptionPane.showMessageDialog(this.frame, "Debe completar el campo ISBN!", "Error", JOptionPane.ERROR_MESSAGE);
				}else if (resultado == -1) {
					JOptionPane.showMessageDialog(this.frame, "Ha ocurrido un error inesperado!", "Error", JOptionPane.ERROR_MESSAGE);
				}
				
			}
		//Si le da al boton de volver
		}else if(e.getSource() == this.btnVolver) {
			//Hacemos invisible el frame y hacemos visible el frame principal
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
