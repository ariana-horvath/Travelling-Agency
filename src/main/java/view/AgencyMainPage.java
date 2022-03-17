package view;

import model.Destination;
import model.VacationPackage;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class AgencyMainPage extends AppFrame {

    private JButton exitButton;
    private JButton addPackageButton;
    private JButton editPackageButton;
    private JButton deletePackageButton;
    private JButton viewPackagesButton;
    private JButton addDestinationButton;
    private JButton deleteDestinationButton;

    private JTextField nameTextField;
    private JTextField priceTextField;
    private JTextField nbPlacesTextField;
    private JTextField startTextField;
    private JTextField endTextField;
    private JScrollPane scrollPane;
    private JTextArea extraDetailsTextArea;
    private JTextArea descriptionTextArea;
    private JScrollPane scrollPane2;
    private JTextField nameDestTextField;
    private JComboBox<String> destinationsComboBox;
    private int[] selectedRows;

    public AgencyMainPage() {
        this.setTitle("Your Perfect Vacation");
        this.setSize(1000, 800);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        this.setLocationRelativeTo(null);
        initializeForm(panel);
        this.setContentPane(panel);
        this.setVisible(true);
        this.getContentPane().setBackground(new Color(250, 206, 97));
        scrollPane = new JScrollPane();
        destinationsComboBox = new JComboBox<>();
    }

    private void initializeForm(JPanel panel) {
        JLabel baseProduct = new JLabel("VACATION PACKAGES ");
        baseProduct.setBounds(10,30,200,30);
        baseProduct.setFont(new Font("Calibri", Font.BOLD, 16));
        baseProduct.setForeground(new Color(151, 85, 13));
        panel.add(baseProduct);

        JLabel ordersLabel = new JLabel("DESTINATIONS ");
        ordersLabel.setBounds(600,30,200,30);
        ordersLabel.setFont(new Font("Calibri", Font.BOLD, 16));
        ordersLabel.setForeground(new Color(151, 85, 13));
        panel.add(ordersLabel);

        JLabel clientLabel = new JLabel("Name: ");
        clientLabel.setBounds(620,60,200,30);
        clientLabel.setFont(new Font("Calibri", Font.PLAIN, 14));
        clientLabel.setForeground(new Color(151, 85, 13));
        panel.add(clientLabel);

        nameDestTextField = new JTextField();
        nameDestTextField.setBounds(710, 60, 240, 30);
        nameDestTextField.setBackground(new Color(245, 230, 156));
        nameDestTextField.setFont(new Font("Calibri", Font.PLAIN, 14));
        nameDestTextField.setForeground(new Color(151, 85, 13));
        panel.add(nameDestTextField);

        JLabel productsLabel = new JLabel("Description: ");
        productsLabel.setBounds(620,100,200,30);
        productsLabel.setFont(new Font("Calibri", Font.PLAIN, 14));
        productsLabel.setForeground(new Color(151, 85, 13));
        panel.add(productsLabel);

        descriptionTextArea = new JTextArea();
        descriptionTextArea.setEditable(true);
        descriptionTextArea.setBackground(new Color(245, 230, 156));
        descriptionTextArea.setFont(new Font("Calibri", Font.PLAIN, 14));
        descriptionTextArea.setForeground(new Color(151, 85, 13));
        scrollPane2 = new JScrollPane(descriptionTextArea);
        scrollPane2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane2.setBounds(710, 100, 240, 190);
        scrollPane2.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        panel.add(scrollPane2);

        addDestinationButton = new JButton("Add Destination");
        addDestinationButton.setBounds(710, 330, 240, 30);
        addDestinationButton.setFont(new Font("Calibri", Font.PLAIN, 14));
        addDestinationButton.setForeground(new Color(151, 85, 13));
        panel.add(addDestinationButton);

        deleteDestinationButton = new JButton("Delete Destination");
        deleteDestinationButton.setBounds(710, 370, 240, 30);
        deleteDestinationButton.setFont(new Font("Calibri", Font.PLAIN, 14));
        deleteDestinationButton.setForeground(new Color(151, 85, 13));
        panel.add(deleteDestinationButton);

        JLabel nameLabel = new JLabel("Name: ");
        nameLabel.setBounds(30,60,70,30);
        nameLabel.setFont(new Font("Calibri", Font.PLAIN, 14));
        nameLabel.setForeground(new Color(151, 85, 13));
        panel.add(nameLabel);

        nameTextField = new JTextField();
        nameTextField.setBounds(150, 60, 240, 30);
        nameTextField.setBackground(new Color(245, 230, 156));
        nameTextField.setFont(new Font("Calibri", Font.PLAIN, 14));
        nameTextField.setForeground(new Color(151, 85, 13));
        panel.add(nameTextField);

        JLabel destinationLabel = new JLabel("Destination name: ");
        destinationLabel.setBounds(30,100,200,30);
        destinationLabel.setFont(new Font("Calibri", Font.PLAIN, 14));
        destinationLabel.setForeground(new Color(151, 85, 13));
        panel.add(destinationLabel);

        JLabel priceLabel = new JLabel("Price: ");
        priceLabel.setBounds(30,140,70,30);
        priceLabel.setFont(new Font("Calibri", Font.PLAIN, 14));
        priceLabel.setForeground(new Color(151, 85, 13));
        panel.add(priceLabel);

        priceTextField = new JTextField();
        priceTextField.setBounds(150, 140, 240, 30);
        priceTextField.setBackground(new Color(245, 230, 156));
        priceTextField.setFont(new Font("Calibri", Font.PLAIN, 14));
        priceTextField.setForeground(new Color(151, 85, 13));
        panel.add(priceTextField);

        JLabel startLabel = new JLabel("Start date: ");
        startLabel.setBounds(30,180,70,30);
        startLabel.setFont(new Font("Calibri", Font.PLAIN, 14));
        startLabel.setForeground(new Color(151, 85, 13));
        panel.add(startLabel);

        JLabel dateLabel = new JLabel("(YYYY-MM-DD)");
        dateLabel.setBounds(25,192,200,30);
        dateLabel.setFont(new Font("Calibri", Font.PLAIN, 12));
        dateLabel.setForeground(new Color(151, 85, 13));
        panel.add(dateLabel);

        startTextField = new JTextField();
        startTextField.setBounds(150, 180, 240, 30);
        startTextField.setBackground(new Color(245, 230, 156));
        startTextField.setFont(new Font("Calibri", Font.PLAIN, 14));
        startTextField.setForeground(new Color(151, 85, 13));
        panel.add(startTextField);

        JLabel endLabel = new JLabel("End date: ");
        endLabel.setBounds(30,220,70,30);
        endLabel.setFont(new Font("Calibri", Font.PLAIN, 14));
        endLabel.setForeground(new Color(151, 85, 13));
        panel.add(endLabel);

        JLabel dateLabel2 = new JLabel("(YYYY-MM-DD)");
        dateLabel2.setBounds(25,232,200,30);
        dateLabel2.setFont(new Font("Calibri", Font.PLAIN, 12));
        dateLabel2.setForeground(new Color(151, 85, 13));
        panel.add(dateLabel2);

        endTextField = new JTextField();
        endTextField.setBounds(150, 220, 240, 30);
        endTextField.setBackground(new Color(245, 230, 156));
        endTextField.setFont(new Font("Calibri", Font.PLAIN, 14));
        endTextField.setForeground(new Color(151, 85, 13));
        panel.add(endTextField);

        JLabel nbPlacesLabel = new JLabel("Number of places: ");
        nbPlacesLabel.setBounds(30,260,150,30);
        nbPlacesLabel.setFont(new Font("Calibri", Font.PLAIN, 14));
        nbPlacesLabel.setForeground(new Color(151, 85, 13));
        panel.add(nbPlacesLabel);

        nbPlacesTextField = new JTextField();
        nbPlacesTextField.setBounds(150, 260, 240, 30);
        nbPlacesTextField.setBackground(new Color(245, 230, 156));
        nbPlacesTextField.setFont(new Font("Calibri", Font.PLAIN, 14));
        nbPlacesTextField.setForeground(new Color(151, 85, 13));
        panel.add(nbPlacesTextField);

        JLabel detailsLabel = new JLabel("Extra details: ");
        detailsLabel.setBounds(30,300,120,30);
        detailsLabel.setFont(new Font("Calibri", Font.PLAIN, 14));
        detailsLabel.setForeground(new Color(151, 85, 13));
        panel.add(detailsLabel);

        addPackageButton = new JButton("Add Package");
        addPackageButton.setBounds(420, 60, 140, 30);
        addPackageButton.setFont(new Font("Calibri", Font.PLAIN, 14));
        addPackageButton.setForeground(new Color(151, 85, 13));
        panel.add(addPackageButton);

        editPackageButton = new JButton("Update Package");
        editPackageButton.setBounds(420, 100, 140, 30);
        editPackageButton.setFont(new Font("Calibri", Font.PLAIN, 14));
        editPackageButton.setForeground(new Color(151, 85, 13));
        panel.add(editPackageButton);

        deletePackageButton = new JButton("Delete Package");
        deletePackageButton.setBounds(420, 140, 140, 30);
        deletePackageButton.setFont(new Font("Calibri", Font.PLAIN, 14));
        deletePackageButton.setForeground(new Color(151, 85, 13));
        panel.add(deletePackageButton);

        viewPackagesButton = new JButton("View Packages");
        viewPackagesButton.setBounds(420, 180, 140, 30);
        viewPackagesButton.setFont(new Font("Calibri", Font.PLAIN, 14));
        viewPackagesButton.setForeground(new Color(151, 85, 13));
        panel.add(viewPackagesButton);

        exitButton = new JButton("Exit");
        exitButton.setBounds(420, 720, 140, 30);
        exitButton.setFont(new Font("Calibri", Font.PLAIN, 14));
        exitButton.setForeground(new Color(151, 85, 13));
        panel.add(exitButton);

        extraDetailsTextArea = new JTextArea();
        extraDetailsTextArea.setBackground(new Color(245, 230, 156));
        extraDetailsTextArea.setFont(new Font("Calibri", Font.PLAIN, 14));
        extraDetailsTextArea.setForeground(new Color(151, 85, 13));
        scrollPane2 = new JScrollPane(extraDetailsTextArea);
        scrollPane2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane2.setBounds(150, 300, 410, 100);
        scrollPane2.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        panel.add(scrollPane2);
    }

    public void addTable(JTable vacationPackagesTable) {
        scrollPane.setBounds(40, 440, 900, 265);
        scrollPane.setBackground(new Color(245, 230, 156));
        scrollPane.setForeground(new Color(245, 230, 156));
        vacationPackagesTable.setVisible(true);
        vacationPackagesTable.setEnabled(true);
        scrollPane.setViewportView(vacationPackagesTable);
        this.getContentPane().add(scrollPane);
    }

    public JTable createVacationsTable(List<VacationPackage> list) {
        ArrayList<String> columns = new ArrayList<>();
        columns.add("Name");
        columns.add("Destination");
        columns.add("Price");
        columns.add("Start Date");
        columns.add("End Date");
        columns.add("Places");
        columns.add("Available");
        columns.add("Status");
        columns.add("Details");

        DefaultTableModel model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column != 7 && column != 6;
            }
        };

        model.setColumnIdentifiers(columns.toArray());
        for (VacationPackage pack : list) {
            ArrayList<String> vacations = new ArrayList<>();
            vacations.add(pack.getName());
            vacations.add(pack.getDestination().getName());
            vacations.add(Float.toString(pack.getPrice()));
            vacations.add(pack.getStartDate().toString());
            vacations.add(pack.getEndDate().toString());
            vacations.add(Integer.toString(pack.getNbOfPlaces()));
            vacations.add(Integer.toString(pack.getAvailablePlaces()));
            vacations.add(pack.getStatus().toString());
            vacations.add(pack.getDetails());

            model.addRow(vacations.toArray());
        }
        JTable table = new JTable(model);
        table.getTableHeader().setBackground(new Color(151, 85, 13));
        table.getTableHeader().setFont(new Font("Calibri", Font.PLAIN, 14));
        table.getTableHeader().setForeground(new Color(245, 230, 156));
        table.setRowHeight(20);
        table.setBackground(new Color(245, 230, 156));
        table.setForeground(new Color(151, 85, 13));
        table.setGridColor(new Color(151, 85, 13));
        table.setFont(new Font("Calibri", Font.PLAIN, 14));
        table.getColumnModel().getColumn(0).setPreferredWidth(120);
        table.getColumnModel().getColumn(0).setPreferredWidth(100);
        table.getColumnModel().getColumn(2).setPreferredWidth(30);
        table.getColumnModel().getColumn(3).setPreferredWidth(50);
        table.getColumnModel().getColumn(4).setPreferredWidth(50);
        table.getColumnModel().getColumn(5).setPreferredWidth(30);
        table.getColumnModel().getColumn(6).setPreferredWidth(30);
        return table;
    }

    public void addComboBox(List<Destination> destinations) {
        destinationsComboBox.removeAllItems();
        destinationsComboBox.setBounds(150, 100, 240, 30);
        destinationsComboBox.setForeground(new Color(151, 85, 13));
        destinationsComboBox.setBackground(new Color(245, 230, 156));
        for(Destination d : destinations){
            destinationsComboBox.addItem(d.getName());
        }
        this.getContentPane().add(destinationsComboBox);
    }

    public int[] getSelectedRows() {
        return selectedRows;
    }

    public void setSelectedRows(int[] selectedRows) {
        this.selectedRows = selectedRows;
    }

    public JButton getExitButton() {
        return exitButton;
    }

    public void setExitButton(JButton exitButton) {
        this.exitButton = exitButton;
    }

    public JButton getAddPackageButton() {
        return addPackageButton;
    }

    public void setAddPackageButton(JButton addPackageButton) {
        this.addPackageButton = addPackageButton;
    }

    public JButton getEditPackageButton() {
        return editPackageButton;
    }

    public void setEditPackageButton(JButton editPackageButton) {
        this.editPackageButton = editPackageButton;
    }

    public JButton getDeletePackageButton() {
        return deletePackageButton;
    }

    public void setDeletePackageButton(JButton deletePackageButton) {
        this.deletePackageButton = deletePackageButton;
    }

    public JButton getViewPackagesButton() {
        return viewPackagesButton;
    }

    public void setViewPackagesButton(JButton viewPackagesButton) {
        this.viewPackagesButton = viewPackagesButton;
    }

    public JButton getAddDestinationButton() {
        return addDestinationButton;
    }

    public void setAddDestinationButton(JButton addDestinationButton) {
        this.addDestinationButton = addDestinationButton;
    }

    public JButton getDeleteDestinationButton() {
        return deleteDestinationButton;
    }

    public void setDeleteDestinationButton(JButton deleteDestinationButton) {
        this.deleteDestinationButton = deleteDestinationButton;
    }

    public JTextField getNameTextField() {
        return nameTextField;
    }

    public void setNameTextField(JTextField nameTextField) {
        this.nameTextField = nameTextField;
    }

    public JTextField getPriceTextField() {
        return priceTextField;
    }

    public void setPriceTextField(JTextField priceTextField) {
        this.priceTextField = priceTextField;
    }

    public JTextField getNbPlacesTextField() {
        return nbPlacesTextField;
    }

    public void setNbPlacesTextField(JTextField nbPlacesTextField) {
        this.nbPlacesTextField = nbPlacesTextField;
    }

    public JTextField getStartTextField() {
        return startTextField;
    }

    public void setStartTextField(JTextField startTextField) {
        this.startTextField = startTextField;
    }

    public JTextField getEndTextField() {
        return endTextField;
    }

    public void setEndTextField(JTextField endTextField) {
        this.endTextField = endTextField;
    }

    public JScrollPane getScrollPane() {
        return scrollPane;
    }

    public void setScrollPane(JScrollPane scrollPane) {
        this.scrollPane = scrollPane;
    }

    public JTextArea getExtraDetailsTextArea() {
        return extraDetailsTextArea;
    }

    public void setExtraDetailsTextArea(JTextArea extraDetailsTextArea) {
        this.extraDetailsTextArea = extraDetailsTextArea;
    }

    public JTextArea getDescriptionTextArea() {
        return descriptionTextArea;
    }

    public void setDescriptionTextArea(JTextArea descriptionTextArea) {
        this.descriptionTextArea = descriptionTextArea;
    }

    public JScrollPane getScrollPane2() {
        return scrollPane2;
    }

    public void setScrollPane2(JScrollPane scrollPane2) {
        this.scrollPane2 = scrollPane2;
    }

    public JTextField getNameDestTextField() {
        return nameDestTextField;
    }

    public void setNameDestTextField(JTextField nameDestTextField) {
        this.nameDestTextField = nameDestTextField;
    }

    public JComboBox<String> getDestinationsComboBox() {
        return destinationsComboBox;
    }

    public void setDestinationsComboBox(JComboBox<String> destinationsComboBox) {
        this.destinationsComboBox = destinationsComboBox;
    }
}
