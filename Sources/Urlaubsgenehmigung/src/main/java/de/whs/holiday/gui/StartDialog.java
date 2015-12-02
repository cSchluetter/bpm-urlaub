package de.whs.holiday.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import de.whs.holiday.console.Console;
import de.whs.holiday.data.Application;

@SuppressWarnings("serial")
public class StartDialog extends JDialog implements ActionListener{

	private final JPanel contentPanel = new JPanel();
	private  ApplicationActionListener callback = null;
	private JTextField txtName;
	private JTextField txtDays;
	private JComboBox comboBox;

	/**
	 * Launch the application.
	 */
	public static void start(ApplicationActionListener callback) {
		try {
			StartDialog dialog = new StartDialog();
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
	public StartDialog() {
		setTitle("Urlaub beantragen");
		setBounds(100, 100, 250, 170);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new GridLayout(0, 2, 0, 10));
		{
			JLabel lblName = new JLabel("Name:");
			contentPanel.add(lblName);
		}
		{
			txtName = new JTextField();
			contentPanel.add(txtName);
			txtName.setColumns(10);
		}
		{
			JLabel lblAnzahlTage = new JLabel("Anzahl Tage:");
			contentPanel.add(lblAnzahlTage);
		}
		{
			txtDays = new JTextField();
			contentPanel.add(txtDays);
			txtDays.setColumns(10);
		}
		{
			JLabel lblUrlaubstyp = new JLabel("Urlaubstyp:");
			contentPanel.add(lblUrlaubstyp);
		}
		{
			comboBox = new JComboBox();
			comboBox.setModel(new DefaultComboBoxModel(new String[] {"Normal", "Umzug", "Geburt- oder Todesfall", "Bonus"}));
			comboBox.setSelectedIndex(0);
			contentPanel.add(comboBox);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton cancelButton = new JButton("Abbrechen");
				cancelButton.addActionListener(this);
				cancelButton.setActionCommand("cancel");
				buttonPane.add(cancelButton);
			}
			{
				JButton sendButton = new JButton("Abschicken");
				sendButton.addActionListener(this);
				sendButton.setActionCommand("send");
				buttonPane.add(sendButton);
				getRootPane().setDefaultButton(sendButton);
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		this.setVisible(false);
		String action = e.getActionCommand();
		switch (action) {
		case "cancel":
			Console.writeLine("application form cancelled");
			break;
			
		case "send":
			Application app = new Application();
			app.setApplicant(txtName.getText());
			app.setDays(Integer.parseInt(txtDays.getText()));
			app.setHolidaytype(comboboxItemsToString(comboBox.getSelectedIndex()));
			callback.actionPerformed(app);
			break;		
		}
		dispose();
	}
	
	private String comboboxItemsToString(int index){
		switch (index) {
		case 0:
			return "normal";
		case 1:
			return "umzug";
		case 2:
			return "geburtTodesfall";
		case 3:
			return "bonus";
		default:
			return "Holidaytype selection error";
		}
	}
}
