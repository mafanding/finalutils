package local.johnson.finalutils.panel.tinytool;

import java.awt.LayoutManager;

import javax.swing.JPanel;
import javax.swing.JTextPane;

public class Default extends JPanel {
	
	public JTextPane msgPane = null;

	public Default() {
		msgPane.setText("wait for develop");
		add(msgPane);
	}

	public Default(LayoutManager layout) {
		super(layout);
		// TODO Auto-generated constructor stub
	}

	public Default(boolean isDoubleBuffered) {
		super(isDoubleBuffered);
		// TODO Auto-generated constructor stub
	}

	public Default(LayoutManager layout, boolean isDoubleBuffered) {
		super(layout, isDoubleBuffered);
		// TODO Auto-generated constructor stub
	}

}
