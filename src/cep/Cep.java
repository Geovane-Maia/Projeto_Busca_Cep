package cep;

import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.Iterator;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import Atxy2k.CustomTextField.RestrictedTextField;

public class Cep extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtCep;
	private JTextField txtEndereco;
	private JTextField txtBairro;
	private JTextField txtCidade;
	private JLabel lcboUF;
	private JComboBox cboUf;
	private JLabel lblStatus;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Cep frame = new Cep();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Cep() {
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(Cep.class.getResource("/img/home.png")));
		setTitle("Buscar Cep");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 486, 300);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.controlHighlight);
		contentPane.setBorder(null);

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("CEP");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel.setBounds(27, 37, 46, 14);
		contentPane.add(lblNewLabel);

		txtCep = new JTextField();
		txtCep.setBounds(60, 34, 144, 20);
		contentPane.add(txtCep);
		txtCep.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Endereço");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1.setBounds(27, 77, 70, 14);
		contentPane.add(lblNewLabel_1);

		txtEndereco = new JTextField();
		txtEndereco.setBounds(94, 74, 267, 20);
		contentPane.add(txtEndereco);
		txtEndereco.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("Bairro");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_2.setBounds(27, 118, 46, 14);
		contentPane.add(lblNewLabel_2);

		txtBairro = new JTextField();
		txtBairro.setBounds(94, 115, 267, 20);
		contentPane.add(txtBairro);
		txtBairro.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("Cidade");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_3.setBounds(27, 159, 46, 14);
		contentPane.add(lblNewLabel_3);

		txtCidade = new JTextField();
		txtCidade.setBounds(94, 156, 183, 20);
		contentPane.add(txtCidade);
		txtCidade.setColumns(10);

		lcboUF = new JLabel("UF");
		lcboUF.setFont(new Font("Tahoma", Font.BOLD, 11));
		lcboUF.setBounds(293, 159, 32, 14);
		contentPane.add(lcboUF);

		cboUf = new JComboBox();
		cboUf.setModel(new DefaultComboBoxModel(
				new String[] { "", "AC", "AL", "AP", "AM", "BA", "CE", "ES", "GO", "MA", "MT", "MS", "MG", "PA", "PB",
						"PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO", "DF" }));
		cboUf.setBounds(317, 155, 46, 22);
		contentPane.add(cboUf);

		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpar();
			}
		});
		btnLimpar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnLimpar.setBounds(27, 203, 89, 23);
		contentPane.add(btnLimpar);

		JButton btnCep = new JButton("Buscar");
		btnCep.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txtCep.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Informe o Cep !");
					txtCep.requestFocus();
				} else {
					buscarCep();
				}
			}
		});
		btnCep.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnCep.setBounds(250, 33, 89, 23);
		contentPane.add(btnCep);

		JButton btnSobre = new JButton("");
		btnSobre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Sobre sobre = new Sobre();
				sobre.setVisible(true);

			}
		});
		btnSobre.setOpaque(false);
		btnSobre.setBorder(null);
		btnSobre.setToolTipText("Sobre");
		btnSobre.setBackground(SystemColor.control);
		btnSobre.setIcon(new ImageIcon(Cep.class.getResource("/img/interrogacao1.png")));
		btnSobre.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnSobre.setBounds(387, 22, 48, 48);
		contentPane.add(btnSobre);
		/* Uso da biblioteca Atxy2k para validação do campo txtCep */
		RestrictedTextField validar = new RestrictedTextField(txtCep);
		
		lblStatus = new JLabel("");
		lblStatus.setBounds(214, 34, 20, 20);
		contentPane.add(lblStatus);
		validar.setOnlyNums(true);
		validar.setLimit(8);

	}

	private void buscarCep() {
		String logradouro = "";
		String tipoLogradouro = "";
		String resultado = null;
		String cep = txtCep.getText();
		try {
			URL url = new URL("http://cep.republicavirtual.com.br/web_cep.php?cep=" + cep + "&formato=xml");
			SAXReader xml = new SAXReader();
			Document documento = xml.read(url);
			Element root = documento.getRootElement();

			for (Iterator<Element> it = root.elementIterator(); it.hasNext();) {
				Element element = it.next();

				if (element.getQualifiedName().equals("cidade")) {
					txtCidade.setText(element.getText());
				}
				
				if (element.getQualifiedName().equals("bairro")) {
					txtBairro.setText(element.getText());
					}
				if (element.getQualifiedName().equals("uf")) {
					cboUf.setSelectedItem(element.getText());
					}
				if (element.getQualifiedName().equals("tipo_logradouro")) {
					tipoLogradouro = element.getText();
					}
				if (element.getQualifiedName().equals("logradouro")) {
					logradouro = element.getText();
					}
				if(element.getQualifiedName().equals("resultado")) {
					resultado = element.getText();
					if(resultado.equals("1")) {
						lblStatus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/check-mark.png")));
					}else {
						JOptionPane.showMessageDialog(null, " CEP Não encontrado ! ");
					}
				}
				/*  Setr o campo endereço */
				txtEndereco.setText(tipoLogradouro + " " + logradouro);
			}

		} catch (Exception e) {
			System.out.println(e);

		}
	}
	private void limpar() {
		txtCep.setText(null);
		txtEndereco.setText(null);
		txtBairro.setText(null);
		txtCidade.setText(null);
		cboUf.setSelectedItem(null);
		txtCep.requestFocus();
		lblStatus.setIcon(null);
	}
}
