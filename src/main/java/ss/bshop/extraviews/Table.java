package ss.bshop.extraviews;

import android.content.Context;
import android.widget.TableLayout;
import android.widget.TableRow;

public class Table extends TableLayout {

	private TableRow selected = null;
	public Table(Context context) {
		super(context);
	}

	public void setSelected(TableRow tr) {
		this.selected = tr;
	}

	public TableRow getSelected() {
		return selected;
	}
}
