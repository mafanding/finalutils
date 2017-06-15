package local.johnson.finalutils.panel;

import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import org.apache.commons.configuration2.XMLConfiguration;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.ex.ConfigurationException;

public class Background extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected JTabbedPane tabPane;
	
	protected final String PACKAGE_NAME = getClass().getPackage().getName().toLowerCase();
	
	protected final String PACKAGE_DOT = ".";
	
	protected final String CONFIG_PATH = "finalutils.xml";

	public Background() {
		tabPane = new JTabbedPane();
		List<String> tabList;
		Configurations configs = new Configurations();
		try {
			XMLConfiguration config = configs.xml(CONFIG_PATH);
			tabList = config.getList(String.class, "tabs.tab");
			for (String tab : tabList) {
				Class<?> reflectClass = Class.forName(PACKAGE_NAME + PACKAGE_DOT + tab);
				JPanel panelInstance = (JPanel) reflectClass.newInstance();
				tabPane.addTab(tab, panelInstance);
			}
			setLayout(new GridLayout(1, 1));
			add(tabPane);
		} catch (ConfigurationException | InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public Background(LayoutManager layout) {
		super(layout);
		// TODO Auto-generated constructor stub
	}

	public Background(boolean isDoubleBuffered) {
		super(isDoubleBuffered);
		// TODO Auto-generated constructor stub
	}

	public Background(LayoutManager layout, boolean isDoubleBuffered) {
		super(layout, isDoubleBuffered);
		// TODO Auto-generated constructor stub
	}

}
