package local.johnson.finalutils.panel.tinytool;

import java.awt.FlowLayout;
import java.awt.LayoutManager;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.border.LineBorder;
import local.johnson.finalutils.event.tinytool.unique.Process;


public class Unique extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public JTextArea sourceText = null;

	public JTextArea destText = null;

	public JTextPane directionText = null;

	public JButton processButton = null;

	public Unique() {
		sourceText = new JTextArea(12, 5);
		destText = new JTextArea(12, 5);
		directionText = new JTextPane();
		processButton = new JButton();
		processButton.setText("process");
		processButton.addActionListener(new Process(this));
		sourceText.setBorder(LineBorder.createBlackLineBorder());
		destText.setBorder(LineBorder.createBlackLineBorder());
		sourceText.setLineWrap(true);
		destText.setLineWrap(true);
		directionText.setText("=>");
		directionText.setEditable(false);
		setLayout(new FlowLayout());
		add(sourceText);
		add(directionText);
		add(destText);
		add(processButton);
	}

	public Unique(LayoutManager layout) {
		super(layout);
		// TODO Auto-generated constructor stub
	}

	public Unique(boolean isDoubleBuffered) {
		super(isDoubleBuffered);
		// TODO Auto-generated constructor stub
	}

	public Unique(LayoutManager layout, boolean isDoubleBuffered) {
		super(layout, isDoubleBuffered);
		// TODO Auto-generated constructor stub
	}

}
