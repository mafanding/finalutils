package local.johnson.finalutils.panel.tinytool;

import java.awt.FlowLayout;
import java.awt.LayoutManager;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.border.LineBorder;
import local.johnson.finalutils.event.tinytool.unixtime.Process;

public class Unixtime extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public JTextArea sourceText = null;

	public JTextArea destText = null;

	public JTextPane directionText = null;

	public JButton processButton = null;

	public Unixtime() {
		sourceText = new JTextArea(3, 8);
		destText = new JTextArea(3, 8);
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

	public Unixtime(LayoutManager layout) {
		super(layout);
		// TODO Auto-generated constructor stub
	}

	public Unixtime(boolean isDoubleBuffered) {
		super(isDoubleBuffered);
		// TODO Auto-generated constructor stub
	}

	public Unixtime(LayoutManager layout, boolean isDoubleBuffered) {
		super(layout, isDoubleBuffered);
		// TODO Auto-generated constructor stub
	}

}
