package fahrenheitCelisius;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class Principal extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField celsius;
	private JTextField fahrenheit;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Principal dialog = new Principal();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Principal() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				guardar();
				setVisible(false);
			}
		});
		setResizable(false);
		setTitle("Convertidor temperatura");
		setBounds(100, 100, 345, 226);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		celsius = new JTextField();
		celsius.setText("0");
		celsius.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				aFahrenheit();
			}
		});
		celsius.setBounds(29, 47, 86, 20);
		contentPanel.add(celsius);
		celsius.setColumns(10);

		fahrenheit = new JTextField();
		fahrenheit.setText("32");
		fahrenheit.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				aCelsius();
			}
		});
		fahrenheit.setBounds(178, 47, 86, 20);
		contentPanel.add(fahrenheit);
		fahrenheit.setColumns(10);

		JLabel lblCelsius = new JLabel("celsius");
		lblCelsius.setBounds(37, 27, 46, 14);
		contentPanel.add(lblCelsius);

		JLabel lblFahrenheit = new JLabel("fahrenheit");
		lblFahrenheit.setBounds(190, 27, 74, 14);
		contentPanel.add(lblFahrenheit);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Reset");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						reset();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Salir");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						salir();
					}

				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);

			}

		}
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenuItem mntmGuardar = new JMenuItem("guardar");
		mntmGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				guardar();
			}
		});
		menuBar.add(mntmGuardar);
		abrir();
	}

	protected void reset() {
		celsius.setText("0");
		fahrenheit.setText("32");
		
	}

	protected void aCelsius() {
	
		try {

			celsius.setText(Double.toString(Temperatura.aCelsius(Double
					.parseDouble(fahrenheit.getText()))));

		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(this,
					"fahrenheit debe de ser numericos", "error en los fahrenheit",
					JOptionPane.ERROR_MESSAGE);
		} catch (NullPointerException e) {
			JOptionPane.showMessageDialog(this,
					"no has introducido los fahrenheit", "error en los fahrenheit",
					JOptionPane.ERROR_MESSAGE);
		}

	}

	protected void aFahrenheit() {
		try {

			fahrenheit.setText(Double.toString(Temperatura.aFahrenheit(Double
					.parseDouble(celsius.getText()))));

		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(this,
					"celcius debe de ser numericos", "error en los celsius",
					JOptionPane.ERROR_MESSAGE);
		} catch (NullPointerException e) {
			JOptionPane.showMessageDialog(this,
					"no has introducido los celsius", "error en los celsius",
					JOptionPane.ERROR_MESSAGE);
		}

	}

	private void salir() {
		guardar();
		System.exit(0);

	}

	private void guardar() {
		try {
			PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("celcius.tem")));
			out.write(celsius.getText());
			JOptionPane.showMessageDialog(this, "guardado");
		} catch (IOException e) {
			JOptionPane.showMessageDialog(this, "No se ha podido guardar", "Error al guardar", JOptionPane.ERROR_MESSAGE);
		}

	}

	private void abrir()  {
		try {
			BufferedReader in = new BufferedReader(new FileReader("celcius.tem"));
			celsius.setText(in.readLine());
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(this, "no tienes ningun archivo", "error al abrir", JOptionPane.ERROR_MESSAGE);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(this, "No se ha podido abrir", "Error al abrir", JOptionPane.ERROR_MESSAGE);
		}
	}
}
