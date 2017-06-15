package local.johnson.finalutils.panel;

import java.awt.FlowLayout;
import java.awt.LayoutManager;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import local.johnson.finalutils.event.datamerge.Choose;

public class DataMerge extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public JButton chooseButton = new JButton();

	public JButton processButton = new JButton();

	public JTextField input = new JTextField(15);

	public JLabel label = new JLabel("File:");

	public JFileChooser chooser = new JFileChooser();

	public JTextPane msg = new JTextPane();

	public DataMerge() {
		chooseButton.setText("choose");
		chooseButton.addActionListener(new Choose(this));
		processButton.setText("process");
		//processButton.addActionListener(new Process(this));
		add(label);
		add(input);
		add(chooseButton);
		add(processButton);
		setLayout(new FlowLayout());
	}

	public DataMerge(LayoutManager layout) {
		super(layout);
		// TODO Auto-generated constructor stub
	}

	public DataMerge(boolean isDoubleBuffered) {
		super(isDoubleBuffered);
		// TODO Auto-generated constructor stub
	}

	public DataMerge(LayoutManager layout, boolean isDoubleBuffered) {
		super(layout, isDoubleBuffered);
		// TODO Auto-generated constructor stub
	}

}
