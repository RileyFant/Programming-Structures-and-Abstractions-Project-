import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;


public class RegionPanel extends JPanel {
	
	private static final long serialVersionUID = 6L;
	private JLabel title;
	private DefaultListModel<String> geoModel;
	protected JList<String> jlGeoList;
	protected JScrollPane spGeos;
	private JButton jbtAdd;
	private JButton jbtEdit;
	private JButton jbtDelete;
	
	public RegionPanel() {
		
	}
	
	public RegionPanel(String title) {
		/*This will the the title of the panel and the type of Geo in geos*/
		this.title = new JLabel(title);
		/*This panel will display the names of the geos*/
		this.geoModel = new DefaultListModel<String>();
		this.jlGeoList = new JList<String>(geoModel);
		this.spGeos = new JScrollPane(jlGeoList);
		JPanel centerPanel = new JPanel();
		centerPanel.add(spGeos);
		/*This panel will contain the 3 buttons to interact with the 3*/
		jbtAdd = new JButton("Add");
		jbtEdit = new JButton("Edit");
		jbtDelete = new JButton("Delete");
		jbtAdd.setEnabled(false);
		jbtEdit.setEnabled(false);
		jbtDelete.setEnabled(false);
		JPanel buttonPanel = new JPanel(new GridLayout(1,3));
		buttonPanel.add(jbtAdd);
		buttonPanel.add(jbtEdit);
		buttonPanel.add(jbtDelete);
		/*Add the 3 panels to the RegionPanel*/
		this.add(this.title, BorderLayout.NORTH);
		this.add(centerPanel, BorderLayout.CENTER);
		this.add(buttonPanel, BorderLayout.SOUTH);
	}
	
	public RegionPanel(ArrayList<? extends GeographicRegion> geos, String title) {
		this.setLayout(new GridLayout(3,1));
		/*This will the the title of the panel and the type of Geo in geos*/
		this.title = new JLabel(title);
		
		/*This panel will display the names of the geos*/
		this.geoModel = new DefaultListModel<String>();
		this.jlGeoList = new JList<String>(geoModel);
		this.spGeos = new JScrollPane(jlGeoList);
		spGeos.setSize(new Dimension(100,200));
		JPanel centerPanel = new JPanel();
		centerPanel.add(spGeos);
		setGeoModel(geos);
		
		/*This panel will contain the 3 buttons to interact with the 3*/
		jbtAdd = new JButton("Add");
		jbtEdit = new JButton("Edit");
		jbtDelete = new JButton("Delete");
		jbtAdd.setEnabled(false);
		jbtEdit.setEnabled(false);
		jbtDelete.setEnabled(false);
		JPanel buttonPanel = new JPanel(new GridLayout(1,3));
		buttonPanel.add(jbtAdd);
		buttonPanel.add(jbtEdit);
		buttonPanel.add(jbtDelete);
		
		/*Add the 3 panels to the RegionPanel*/
		this.add(this.title);
		this.add(centerPanel);
		this.add(buttonPanel);
	}
	
	public void setGeoModel(ArrayList<? extends GeographicRegion> geos) {
		geoModel.clear();
		for (GeographicRegion geo : geos) {
			geoModel.addElement(geo.getName());
		}
	}
	
	public DefaultListModel<String> getListModel() {
		return geoModel;
	}
	
	public JButton addButton() {
		return jbtAdd;
	}
	
	public JButton editButton() {
		return jbtEdit;
	}
	
	public JButton deleteButton() {
		return jbtDelete;
	}
	
	public void enableButtons() {
		jbtAdd.setEnabled(true);
		jbtEdit.setEnabled(true);
		jbtDelete.setEnabled(true);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
	}
}
