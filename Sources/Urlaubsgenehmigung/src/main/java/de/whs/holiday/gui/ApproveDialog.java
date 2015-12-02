package de.whs.holiday.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import de.whs.holiday.console.Console;

@SuppressWarnings("serial")
public class ApproveDialog extends JDialog implements ActionListener{

	private final JPanel contentPanel = new JPanel();
	private  ActionListener callback = null;

	/**
	 * Launch the application.
	 */
	public static JDialog start(String title, String text, ActionListener callback) {
		ApproveDialog dialog = new ApproveDialog(title,text);
		dialog.callback = callback;
		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		dialog.setVisible(true);
		return dialog;
	}
	
	@Override
	public void dispose() {
		Console.writeLine("disposed dialog");
		super.dispose();
	};

	/**
	 * Create the dialog.
	 */
	public ApproveDialog(String title, String text) {
		setTitle(title);
		setBounds(100, 100, 300, 200);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			JLabel lblText = new JLabel(text);
			contentPanel.add(lblText);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton approveButton = new JButton("Annehmen");
				approveButton.addActionListener(this);
				approveButton.setActionCommand("approved");
				buttonPane.add(approveButton);
				getRootPane().setDefaultButton(approveButton);
			}
			{
				JButton denyButton = new JButton("Ablehnen");
				denyButton.addActionListener(this);
				denyButton.setActionCommand("denied");
				buttonPane.add(denyButton);
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		callback.actionPerformed(e);
		this.setVisible(false);		
		dispose();		
	}
}
