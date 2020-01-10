package view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableRowSorter;


public class ScoreBoard extends JFrame {

    private GamerData data;
    /*private String gameVersion;
    private String gamerName;
    private int scorePoints;*/
    
    public void addGamerData(String gameVersion, String gamerName, int scorePoints) {
		data.addGamer(gameVersion,gamerName,scorePoints); 
    }
    
    private void initComponents() {
        this.setLayout(new BorderLayout());
        
        ///Táblázat///
		JTable jt=new JTable(data);
		jt.setFillsViewportHeight(rootPaneCheckingEnabled);
		jt.setRowSorter(new TableRowSorter<GamerData>(data));
		

		jt.updateUI();
		this.add(new JScrollPane(jt),BorderLayout.CENTER);
    }


    @SuppressWarnings("unchecked")
    public ScoreBoard() {
   
        //super("ScoreBoard");
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        
        //Adatok betöltése induláskor
        try {
            data = new GamerData();
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("gamers.dat"));
            data.gamers = (List<Gamer>)ois.readObject();
            ois.close();
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        
        ///WINDOW SETTINGS///
        setMinimumSize(new Dimension(500, 200));
        initComponents();
    }

    /*
     * A program belépési pontja.
     * 
     * NE MÓDOSÍTSD!
     */
    public void startShowScoreBoard() {
        // Megjelenítjük az ablakot
    	/*ScoreBoard sb = new ScoreBoard(String gameVersion, String gamerName, int scorePoints);
    	sb.setVisible(true);*/
    }

	private class GamerTableCellRenderer implements TableCellRenderer {
		private final TableCellRenderer renderer;
		public GamerTableCellRenderer(TableCellRenderer defRenderer) 
		{
			this.renderer = defRenderer;
		}
		
		@Override
		public Component getTableCellRendererComponent(JTable table,
					Object value, boolean isSelected, boolean hasFocus,
					int row, int column) 
		{
			Component component = renderer.getTableCellRendererComponent(
						table, value, isSelected, hasFocus, row, column);
			return component;
		}
	
	}
}