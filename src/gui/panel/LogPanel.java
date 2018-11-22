package gui.panel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import gui.RiskFrame;

public class LogPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JTextPane area;
	private JScrollPane pane;
	private Style docStyle;
	
	public LogPanel(RiskFrame frame) {
		area = new JTextPane();
		Dimension d = new Dimension(200,
				frame.getHeight());
		area.setMinimumSize(d);
		pane = new JScrollPane(area);
		pane.setSize(d);
		pane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		area.setEditable(false);
		docStyle = area.addStyle("Log Style", null);
		this.setLayout(new BorderLayout());
		this.add(pane, BorderLayout.CENTER);
		this.setPreferredSize(d);
	}
	
	void add_to_log(String s, Color color) {
		StyleConstants.setForeground(docStyle, color);
		try {
			area.getStyledDocument().insertString(0, s, docStyle);
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
	}
	
	void clear_log() {
		area.setText("");
	}
}
