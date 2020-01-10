package view;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;

public class GamerData extends AbstractTableModel {

    List<Gamer> gamers = new ArrayList<>();

	@Override
	public int getRowCount() {
		return gamers.size();
	}

	@Override
	public int getColumnCount() {
		return 3;
	}

	@Override
	public Object getValueAt(int i, int i1) {
		Gamer gamer = gamers.get(i);
		switch(i1) {
			case 0: return gamer.getGameVersion();
			case 1: return gamer.getGamerName();
			default: return gamer.getScorePoints();
		}
	}

	@Override
	public void addTableModelListener(TableModelListener tl) {
		super.addTableModelListener(tl);
	}
	
	@Override
	public String getColumnName(int i)
	{
		switch(i)
		{
			case 0: return "GameVersion";
			case 1: return "GamerName";
			default: return "ScorePoints";
		}
	}
    
	@Override
	public Class<?> getColumnClass(int i)
	{
		Gamer s =gamers.get(0);
		switch(i)
		{
			case 0: return String.class;
			case 1: return String.class;
			default: return int.class;
		}
	}

	public void addGamer(String gameVersion, String gamerName, int scorePoints)
	{
		gamers.add(new Gamer(gameVersion, gamerName, scorePoints));
		this.fireTableDataChanged();
		
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("gamers.dat"));
			oos.writeObject(gamers);
			oos.close();
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}

}
