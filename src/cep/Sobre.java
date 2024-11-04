package cep;

import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.EventQueue;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.net.URI;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Sobre extends JDialog {

	private static final long serialVersionUID = 1L;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Sobre dialog = new Sobre();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the dialog.
	 */
	public Sobre() {
		setModal(true);
		setTitle("Sobre");
		setIconImage(Toolkit.getDefaultToolkit().getImage(Sobre.class.getResource("/img/home.png")));
		setResizable(false);
		setBounds(150, 150, 450, 300);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Buscar CEP 1.0");
		lblNewLabel.setBounds(33, 46, 149, 14);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("@Autor Geovane Maia");
		lblNewLabel_1.setBounds(33, 82, 149, 14);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Web Service");
		lblNewLabel_2.setBounds(33, 107, 72, 14);
		getContentPane().add(lblNewLabel_2);
		
		JLabel lblWebService = new JLabel("republicavirtual.com.br");
		lblWebService.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				link("https://republicavirtual.com.br");
			}
		});
		lblWebService.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblWebService.setForeground(SystemColor.textHighlight);
		lblWebService.setBounds(109, 107, 156, 14);
		getContentPane().add(lblWebService);
		
		JButton btnGithub = new JButton("");
		btnGithub.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				link("https://github.com/Geovane-Maia");
			}
		});
		btnGithub.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnGithub.setBorder(null);
		btnGithub.setForeground(SystemColor.control);
		btnGithub.setBackground(SystemColor.control);
		btnGithub.setToolTipText("Github");
		btnGithub.setIcon(new ImageIcon(Sobre.class.getResource("/img/github.png")));
		btnGithub.setBounds(28, 172, 48, 48);
		getContentPane().add(btnGithub);
		
		JButton btnYouTube = new JButton("");
		btnYouTube.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				link("https://www.youtube.com/@shomwman");
			}
		});
		btnYouTube.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnYouTube.setToolTipText("Youtube");
		btnYouTube.setIcon(new ImageIcon(Sobre.class.getResource("/img/youtube.png")));
		btnYouTube.setBorder(null);
		btnYouTube.setBackground(SystemColor.control);
		btnYouTube.setBounds(98, 172, 48, 48);
		getContentPane().add(btnYouTube);

	}
  private void link(String site) {
	  Desktop desktop = Desktop.getDesktop();
	  try {
		  URI uri = new URI(site);
		  desktop.browse(uri);
		  
	  }catch(Exception e){
		  
	  }
  }
}
