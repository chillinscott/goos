package goos;

import javax.swing.table.AbstractTableModel;

public class SnipersTableModel extends AbstractTableModel implements SniperListener {
	private static final long serialVersionUID = 1247198670811592368L;
	private static String[] STATUS_TEXT = {
		"Joining", "Bidding", "Winning", "Lost", "Won" 
	};
	private final static SniperSnapshot STARTING_UP = new SniperSnapshot("", 0, 0, SniperState.JOINING);
	private SniperSnapshot snapshot = STARTING_UP;

	public int getColumnCount() {
		return Column.values().length;
	}

	public int getRowCount() {
		return 1;
	}
	
	@Override
	public String getColumnName(int column) {
		return Column.at(column).name;
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		return Column.at(columnIndex).valueIn(snapshot);
	}

	public void sniperStateChanged(SniperSnapshot newSniperState) {
		snapshot = newSniperState;
		fireTableRowsUpdated(0, 0);
	}
	
	public static String textFor(SniperState state) {
		return STATUS_TEXT[state.ordinal()];
	}
}
