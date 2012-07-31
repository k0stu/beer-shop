package ss.bshop.extraviews;

import android.content.Context;
import android.widget.TableLayout;
import android.widget.TableRow;

public class Table extends TableLayout {

	private TableRow selected = null;
	public Table(Context context) {
		super(context);
	}

	public void setSelectedRow(TableRow tr) {
		this.selected = tr;
	}

	public TableRow getSelectedRow() {
		return selected;
	}
}
