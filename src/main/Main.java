package main;

import javax.swing.SwingUtilities;

import gui.RiskFrame;

public class Main {
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new RiskFrame();
            }
        });
	}
}
