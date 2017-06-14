package local.johnson.finalutils.panel;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import local.johnson.finalutils.event.downloadpicture.Choose;
import local.johnson.finalutils.event.downloadpicture.Process;

public class DownloadPicture extends JPanel {
	
	public JButton chooseButton = new JButton();

	public JButton processButton = new JButton();

	public JTextField input = new JTextField(15);

	public JLabel label = new JLabel("File:");

	public JFileChooser chooser = new JFileChooser();

	public FileNameExtensionFilter filter = new FileNameExtensionFilter("excel files", "csv", "xls", "xlsx");

	public JTextPane msg = new JTextPane();

	public DownloadPicture() {
		chooseButton.setText("choose");
		chooseButton.addActionListener(new Choose(this));
		processButton.setText("process");
		processButton.addActionListener(new Process(this));
		add(label);
		add(input);
		add(chooseButton);
		add(processButton);
		setLayout(new FlowLayout());
	}

	public DownloadPicture(LayoutManager layout) {
		super(layout);
		// TODO Auto-generated constructor stub
	}

	public DownloadPicture(boolean isDoubleBuffered) {
		super(isDoubleBuffered);
		// TODO Auto-generated constructor stub
	}

	public DownloadPicture(LayoutManager layout, boolean isDoubleBuffered) {
		super(layout, isDoubleBuffered);
		// TODO Auto-generated constructor stub
	}

}
