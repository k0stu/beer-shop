package ss.bshop.extraviews;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TableLayout;
import android.widget.TableRow;

public class Table extends TableLayout {

	private TableRow selected = null;
	public Table(Context context) {
		super(context);
	}
	public Table(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public void setSelectedRow(TableRow tr) {
		this.selected = tr;
	}

	public TableRow getSelectedRow() {
		return selected;
	}
}
