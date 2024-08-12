import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class LabeledField extends JPanel {
	
	JTextField field;
	
	LabeledField(String label) {
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		add(new JLabel(label));
		add(field = new JTextField());
	}
	
	LabeledField(String label, boolean editable) {
		this(label);
		field.setEditable(editable);
	}
	
	public String getText() {
		return field.getText();
	}
	
	public void setText(String s) {
		field.setText(s);
	}
}
