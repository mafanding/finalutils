package local.johnson.finalutils.panel.tinytool;

import java.awt.FlowLayout;
import java.awt.LayoutManager;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.LineBorder;
import local.johnson.finalutils.event.tinytool.replace.Process;

public class Replace extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public JTextArea sourceText = null;

	public JTextArea destText = null;

	public JTextField search = null;

	public JTextField replace = null;

	public JTextPane directionText = null;

	public JButton processButton = null;

	public Replace() {
		sourceText = new JTextArea(12, 5);
		destText = new JTextArea(12, 5);
		directionText = new JTextPane();
		processButton = new JButton();
		search = new JTextField(1);
		replace = new JTextField(1);
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
		add(search);
		add(directionText);
		add(replace);
		add(destText);
		add(processButton);
	}

	public Replace(LayoutManager layout) {
		super(layout);
		// TODO Auto-generated constructor stub
	}

	public Replace(boolean isDoubleBuffered) {
		super(isDoubleBuffered);
		// TODO Auto-generated constructor stub
	}

	public Replace(LayoutManager layout, boolean isDoubleBuffered) {
		super(layout, isDoubleBuffered);
		// TODO Auto-generated constructor stub
	}

}
