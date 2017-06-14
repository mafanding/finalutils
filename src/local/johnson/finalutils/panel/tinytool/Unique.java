package local.johnson.finalutils.panel.tinytool;

import java.awt.GridLayout;
import java.awt.LayoutManager;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.border.LineBorder;


public class Unique extends JPanel {
	
	public JTextArea sourceText = null;

	public JTextArea destText = null;

	public JTextPane directionText = null;

	public JButton processButton = null;

	public Unique() {
		sourceText = new JTextArea(15, 5);
		destText = new JTextArea(15, 5);
		directionText = new JTextPane();
		processButton = new JButton();
		processButton.setText("process");
		//processButton.addActionListener(new ProcessEvent(this));
		sourceText.setBorder(LineBorder.createBlackLineBorder());
		destText.setBorder(LineBorder.createBlackLineBorder());
		sourceText.setLineWrap(true);
		destText.setLineWrap(true);
		directionText.setText("=>");
		directionText.setEditable(false);
		setLayout(new GridLayout(1, 4));
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
