package view;

import model.Destination;
import model.VacationPackage;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class UserMainPage extends AppFrame {

    private JButton exitButton;
    private JButton availableVacationsButton;
    private JButton bookedVacationsButton;
    private JButton bookButton;
    private JButton filterButton;

    private JTextField minPriceTF;
    private JTextField maxPriceTF;
    private JTextField startDateTF;
    private JTextField endDateTF;

    private JScrollPane scrollPane;
    private JScrollPane scrollPane2;
    private JComboBox<String> destinationsComboBox;

    private int[] selectedRows;

    public UserMainPage() {
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
        scrollPane2 = new JScrollPane();
        destinationsComboBox = new JComboBox<>();
    }

    private void initializeForm(JPanel panel) {

        JLabel ordersLabel = new JLabel("FILTERS");
        ordersLabel.setBounds(320,20,200,30);
        ordersLabel.setFont(new Font("Calibri", Font.BOLD, 14));
        ordersLabel.setForeground(new Color(151, 85, 13));
        panel.add(ordersLabel);

        minPriceTF = new JTextField();
        minPriceTF.setBounds(400, 60, 100, 30);
        minPriceTF.setBackground(new Color(245, 230, 156));
        minPriceTF.setFont(new Font("Calibri", Font.PLAIN, 14));
        minPriceTF.setForeground(new Color(151, 85, 13));
        panel.add(minPriceTF);

        maxPriceTF = new JTextField();
        maxPriceTF.setBounds(550, 60, 100, 30);
        maxPriceTF.setBackground(new Color(245, 230, 156));
        maxPriceTF.setFont(new Font("Calibri", Font.PLAIN, 14));
        maxPriceTF.setForeground(new Color(151, 85, 13));
        panel.add(maxPriceTF);

        JLabel priceLabel = new JLabel("Price:    Min                                          Max");
        priceLabel.setBounds(320,60,400,30);
        priceLabel.setFont(new Font("Calibri", Font.PLAIN, 14));
        priceLabel.setForeground(new Color(151, 85, 13));
        panel.add(priceLabel);

        JLabel destinationLabel = new JLabel("Destination: ");
        destinationLabel.setBounds(320,100,400,30);
        destinationLabel.setFont(new Font("Calibri", Font.PLAIN, 14));
        destinationLabel.setForeground(new Color(151, 85, 13));
        panel.add(destinationLabel);

        JLabel periodLabel = new JLabel("Period:  Start                                        End");
        periodLabel.setBounds(320,140,400,30);
        periodLabel.setFont(new Font("Calibri", Font.PLAIN, 14));
        periodLabel.setForeground(new Color(151, 85, 13));
        panel.add(periodLabel);

        startDateTF = new JTextField();
        startDateTF.setBounds(400, 140, 100, 30);
        startDateTF.setBackground(new Color(245, 230, 156));
        startDateTF.setFont(new Font("Calibri", Font.PLAIN, 14));
        startDateTF.setForeground(new Color(151, 85, 13));
        panel.add(startDateTF);

        endDateTF = new JTextField();
        endDateTF.setBounds(550, 140, 100, 30);
        endDateTF.setBackground(new Color(245, 230, 156));
        endDateTF.setFont(new Font("Calibri", Font.PLAIN, 14));
        endDateTF.setForeground(new Color(151, 85, 13));
        panel.add(endDateTF);

        availableVacationsButton = new JButton("Available Vacations");
        availableVacationsButton.setBounds(40, 60, 200, 30);
        availableVacationsButton.setFont(new Font("Calibri", Font.PLAIN, 14));
        availableVacationsButton.setForeground(new Color(151, 85, 13));
        panel.add(availableVacationsButton);

        bookedVacationsButton = new JButton("My Vacations");
        bookedVacationsButton.setBounds(40, 100, 200, 30);
        bookedVacationsButton.setFont(new Font("Calibri", Font.PLAIN, 14));
        bookedVacationsButton.setForeground(new Color(151, 85, 13));
        panel.add(bookedVacationsButton);

        bookButton = new JButton("Book Vacation");
        bookButton.setBounds(40, 140, 200, 30);
        bookButton.setFont(new Font("Calibri", Font.PLAIN, 14));
        bookButton.setForeground(new Color(151, 85, 13));
        panel.add(bookButton);

        filterButton = new JButton("Filter");
        filterButton.setBounds(720, 140, 200, 30);
        filterButton.setFont(new Font("Calibri", Font.PLAIN, 14));
        filterButton.setForeground(new Color(151, 85, 13));
        panel.add(filterButton);

        exitButton = new JButton("Exit");
        exitButton.setBounds(420, 720, 140, 30);
        exitButton.setFont(new Font("Calibri", Font.PLAIN, 14));
        exitButton.setForeground(new Color(151, 85, 13));
        panel.add(exitButton);
    }

    public void addTable(JTable availableVacationsTable) {
        scrollPane.setBounds(40, 220, 900, 220);
        scrollPane.setBackground(new Color(245, 230, 156));
        scrollPane.setForeground(new Color(245, 230, 156));
        availableVacationsTable.setVisible(true);
        availableVacationsTable.setEnabled(true);
        scrollPane.setViewportView(availableVacationsTable);
        this.getContentPane().add(scrollPane);
    }

    public void addTable2(JTable bookedVacationsTable) {
        scrollPane2.setBounds(40, 470, 900, 220);
        scrollPane2.setBackground(new Color(245, 230, 156));
        scrollPane2.setForeground(new Color(245, 230, 156));
        bookedVacationsTable.setVisible(true);
        bookedVacationsTable.setEnabled(true);
        scrollPane2.setViewportView(bookedVacationsTable);
        this.getContentPane().add(scrollPane2);
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
                    return false;
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
        destinationsComboBox.setBounds(400, 100, 250, 30);
        destinationsComboBox.setForeground(new Color(151, 85, 13));
        destinationsComboBox.setBackground(new Color(245, 230, 156));
        for(Destination d : destinations){
            destinationsComboBox.addItem(d.getName());
        }
        this.getContentPane().add(destinationsComboBox);
    }

    public JButton getExitButton() {
        return exitButton;
    }

    public void setExitButton(JButton exitButton) {
        this.exitButton = exitButton;
    }

    public JButton getAvailableVacationsButton() {
        return availableVacationsButton;
    }

    public void setAvailableVacationsButton(JButton availableVacationsButton) {
        this.availableVacationsButton = availableVacationsButton;
    }

    public JButton getBookedVacationsButton() {
        return bookedVacationsButton;
    }

    public void setBookedVacationsButton(JButton bookedVacationsButton) {
        this.bookedVacationsButton = bookedVacationsButton;
    }

    public JButton getBookButton() {
        return bookButton;
    }

    public void setBookButton(JButton bookButton) {
        this.bookButton = bookButton;
    }

    public JButton getFilterButton() {
        return filterButton;
    }

    public void setFilterButton(JButton filterButton) {
        this.filterButton = filterButton;
    }

    public JTextField getMinPriceTF() {
        return minPriceTF;
    }

    public void setMinPriceTF(JTextField minPriceTF) {
        this.minPriceTF = minPriceTF;
    }

    public JTextField getMaxPriceTF() {
        return maxPriceTF;
    }

    public void setMaxPriceTF(JTextField maxPriceTF) {
        this.maxPriceTF = maxPriceTF;
    }

    public JTextField getStartDateTF() {
        return startDateTF;
    }

    public void setStartDateTF(JTextField startDateTF) {
        this.startDateTF = startDateTF;
    }

    public JTextField getEndDateTF() {
        return endDateTF;
    }

    public void setEndDateTF(JTextField endDateTF) {
        this.endDateTF = endDateTF;
    }

    public JScrollPane getScrollPane() {
        return scrollPane;
    }

    public void setScrollPane(JScrollPane scrollPane) {
        this.scrollPane = scrollPane;
    }

    public JScrollPane getScrollPane2() {
        return scrollPane2;
    }

    public void setScrollPane2(JScrollPane scrollPane2) {
        this.scrollPane2 = scrollPane2;
    }

    public JComboBox<String> getDestinationsComboBox() {
        return destinationsComboBox;
    }

    public void setDestinationsComboBox(JComboBox<String> destinationsComboBox) {
        this.destinationsComboBox = destinationsComboBox;
    }

    public int[] getSelectedRows() {
        return selectedRows;
    }

    public void setSelectedRows(int[] selectedRows) {
        this.selectedRows = selectedRows;
    }
}
