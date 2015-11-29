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

@SuppressWarnings("serial")
public class ApproveDialog extends JDialog implements ActionListener{

	private final JPanel contentPanel = new JPanel();
	private  ActionListener callback = null;

	/**
	 * Launch the application.
	 */
	public static void run(String text, ActionListener callback) {
		try {
			ApproveDialog dialog = new ApproveDialog(text);
			dialog.callback = callback;
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ApproveDialog(String text) {
		setTitle("Urlaubsantrag");
		setBounds(100, 100, 250, 200);
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
		this.setVisible(false);
		dispose();
		callback.actionPerformed(e);
	}
}
