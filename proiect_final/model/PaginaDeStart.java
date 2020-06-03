package model;

/**
 *
 * @author Windows10
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//import model.Test;

import model.Campaign;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;
import java.util.*;
import java.lang.*;
import java.io.*;
import java.time.*;
import java.time.format.*;
import java.awt.*;
import java.awt.event.*;
import model.VMS;

public class PaginaDeStart extends JFrame implements ActionListener{
    
    JLabel titleLabel, userLabel, passwordLabel, outputLabel, adminLabel, campaignTitleLabel, voucherTitleLabel, addCampaignVouchersDictionaryField;
    JButton fileLoaderButton, outputGeneratorButton, loginButton, campaignsButton, vouchersButton, addCampaignButton, addVoucherButton, searchVoucherButton, addCampaignAllButton, cancelCampaignButton, backToMenuButton, addVoucherAllButton;
    JTextField userField, passwordField, searchField, addCampaignIDField, addCampaignNameField, addCampaignDescriptionField, addCampaignStartDateField, addCampaignEndDateField, addCampaignBudgetField, addCampaignStrategyField, addCampaignAvailableVouchersField, addVoucherValueField;
    JTable campaignsTable, vouchersTable;
    JScrollPane sp, vsp;
    boolean isAdmin = false;
    String[][] data;
    String[][] vData;
    JFrame newFrame, voucherFrame;
    String[] columnNames = {"ID", "Nume", "Data inceperii", "Data incheierii", "Buget", "Strategie", "Status"};
    String[] vColumnNames = {"ID", "Cod", "Status", "Data Utilizarii","Email", "Campanie", "Tip"};
    int cnt = -1, vcnt = -1;
    JComboBox addVoucherCampaignIDBox, addVoucherEmailBox, addVoucherTypeBox;
    
    public void showLoadFiles()
    {
        titleLabel.setBounds(50, 50, 700, 50);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        
        
        fileLoaderButton.setBounds(250, 200, 300, 50);
        
        outputGeneratorButton.setBounds(250, 270, 300, 50);
        
        this.getContentPane().add(titleLabel);
        getContentPane().add(fileLoaderButton);
        getContentPane().add(outputGeneratorButton);
        
        this.pack();  
    }
    
    public void showLogin()
    {
        titleLabel.setBounds(50, 50, 700, 50);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setText("Introduceti datele de logare");
        
        userLabel.setBounds(250, 150, 100, 30);
        userLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        passwordLabel.setBounds(250, 200, 100, 30);
        passwordLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        
        userField.setBounds(380, 150, 170, 30);
        passwordField.setBounds(380, 200, 170, 30);
        
        loginButton.setBounds(250, 250, 300, 50);
        
        this.getContentPane().add(titleLabel);
        this.getContentPane().add(userLabel);
        this.getContentPane().add(passwordLabel);
        this.getContentPane().add(userField);
        this.getContentPane().add(passwordField);
        this.getContentPane().add(loginButton); 
    }
    
    public void showCampaignsAndVouchers()
    {
        this.getContentPane().removeAll();
        this.repaint();
        
        campaignsButton.setBounds(250, 100, 300, 50);
        
        vouchersButton.setBounds(250, 250, 300, 50);
        
        this.getContentPane().add(campaignsButton);
        this.getContentPane().add(vouchersButton);
    }
    
    public void showCampaigns()
    {
        cnt = -1;
        
        this.setLayout(new FlowLayout());
        
       
        VMS vms = VMS.getInstance();
        Set<Campaign> campaignsSet = vms.getCampaigns();
        
        data = new String[campaignsSet.size()][];
        
        Iterator it = campaignsSet.iterator();
        while(it.hasNext())
        {
            Campaign i = (Campaign)it.next();
            String[] campaignList = new String[7];
            campaignList[0] = Integer.toString(i.campaignID);
            campaignList[1] = i.campaignName;
            campaignList[2] = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm").format(i.campaignStartDate);
            campaignList[3] = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm").format(i.campaignEndDate);
            campaignList[4] = Integer.toString(i.campaignTotalGiftsNumber);
            campaignList[5] = String.valueOf(i.campaignStrategyType);
            if(i.campaignStatus == Campaign.CampaignStatusType.NEW)
            {
                campaignList[6] = "NEW";
            }
            else if(i.campaignStatus == Campaign.CampaignStatusType.STARTED)
            {
                campaignList[6] = "STARTED";
            }
            else if(i.campaignStatus == Campaign.CampaignStatusType.EXPIRED)
            {
                campaignList[6] = "EXPIRED";
            }
            else
            {
                campaignList[6] = "CANCELLED";
            }
            
            data[++cnt] = campaignList;
        }
        
        campaignsTable = new JTable();
        DefaultTableModel defTableModel = new DefaultTableModel(data, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        
        
        campaignsTable.setModel(defTableModel);
        
        campaignsTable.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
        @Override
        public void valueChanged(ListSelectionEvent event) {
            if(! event.getValueIsAdjusting())
            {
                newFrame.getContentPane().removeAll();
                newFrame.repaint();
        
                newFrame.setPreferredSize(new Dimension(800,600));
                newFrame.setLayout(null);

                campaignTitleLabel.setBounds(50, 50, 700, 50);
                campaignTitleLabel.setHorizontalAlignment(SwingConstants.CENTER);
                newFrame.getContentPane().add(campaignTitleLabel);

                JLabel addCampaignIDLabel = new JLabel("ID");
                JLabel addCampaignNameLabel = new JLabel("Nume");
                JLabel addCampaignDescriptionLabel = new JLabel("Descriere");
                JLabel addCampaignStartDateLabel = new JLabel("Data inceperii");
                JLabel addCampaignEndDateLabel = new JLabel("Data incheierii");
                JLabel addCampaignBudgetLabel = new JLabel("Buget");
                JLabel addCampaignStrategyLabel = new JLabel("Strategie");
                JLabel addCampaignAvailableVouchersLabel = new JLabel("Vouchere Disponibile");
                JLabel addCampaignVouchersDictionaryLabel = new JLabel("Dictionar Vouchere");

                addCampaignIDLabel.setBounds(20, 120, 150, 50);
                addCampaignNameLabel.setBounds(20, 170, 150, 50);
                addCampaignDescriptionLabel.setBounds(20, 220, 150, 50);
                addCampaignStartDateLabel.setBounds(20, 270, 150, 50);
                addCampaignEndDateLabel.setBounds(20, 320, 150, 50);
                addCampaignBudgetLabel.setBounds(20, 370, 150, 50);
                addCampaignStrategyLabel.setBounds(20, 420, 150, 50);
                
                addCampaignAvailableVouchersLabel.setBounds(410, 120, 150, 50);
                addCampaignVouchersDictionaryLabel.setBounds(410, 170, 150, 50);

                newFrame.getContentPane().add(addCampaignIDLabel);
                newFrame.getContentPane().add(addCampaignNameLabel);
                newFrame.getContentPane().add(addCampaignDescriptionLabel);
                newFrame.getContentPane().add(addCampaignStartDateLabel);
                newFrame.getContentPane().add(addCampaignEndDateLabel);
                newFrame.getContentPane().add(addCampaignBudgetLabel);
                newFrame.getContentPane().add(addCampaignStrategyLabel);
                newFrame.getContentPane().add(addCampaignAvailableVouchersLabel);
                newFrame.getContentPane().add(addCampaignVouchersDictionaryLabel);

                addCampaignIDField.setBounds(170, 120, 220, 50);
                addCampaignNameField.setBounds(170, 170, 220, 50);
                addCampaignDescriptionField.setBounds(170, 220, 220, 50);
                addCampaignStartDateField.setBounds(170, 270, 220, 50);
                addCampaignEndDateField.setBounds(170, 320, 220, 50);
                addCampaignBudgetField.setBounds(170, 370, 220, 50);
                addCampaignStrategyField.setBounds(170, 420, 220, 50);
                
                addCampaignAvailableVouchersField.setBounds(560, 120, 220, 50);
                addCampaignVouchersDictionaryField.setBounds(560, 170, 220, 300);

                Campaign campaign = vms.getCampaign(Integer.parseInt(campaignsTable.getValueAt(campaignsTable.getSelectedRow(), 0).toString()));
                
                addCampaignIDField.setText(campaign.campaignID + "");
                addCampaignNameField.setText(campaign.campaignName);
                addCampaignDescriptionField.setText(campaign.campaignDescription);
                addCampaignStartDateField.setText(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm").format(campaign.campaignStartDate));
                addCampaignEndDateField.setText(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm").format(campaign.campaignEndDate));
                addCampaignBudgetField.setText(campaign.campaignTotalGiftsNumber + "");
                addCampaignStrategyField.setText(campaign.campaignStrategyType + "");
                addCampaignAvailableVouchersField.setText(campaign.campaignAvailableGiftsNumber + "");
                
                StringBuffer str = new StringBuffer();
                Iterator it = campaign.campaignGiftsDictionary.entrySet().iterator();
                while(it.hasNext())
                {
                    Map.Entry entry = (Map.Entry)it.next();
                    String email = (String)entry.getKey();
                    ArrayList<NintendoGift> vouchers = (ArrayList<NintendoGift>)entry.getValue();
                    str.append(email + " : ");
                    Iterator it2 = vouchers.iterator();
                    while(it2.hasNext())
                    {
                        NintendoGift i = (NintendoGift)it2.next();
                        str.append(i.giftID + " ");
                    }
                    str.append('\n');
                }
                
                addCampaignVouchersDictionaryField.setText("<html>" + str.toString().replaceAll("<","&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br/>") + "</html>");
                
                if(campaignsTable.getValueAt(campaignsTable.getSelectedRow(), 6).equals("NEW"))
                {
                    if(isAdmin)
                        campaignTitleLabel.setText("Campanie Noua: Puteti Edita Sau Anula Campania");
                    else
                        campaignTitleLabel.setText("Campanie Noua");
                    
                    campaignTitleLabel.setForeground(Color.BLUE);
                    
                    addCampaignNameLabel.setForeground(Color.blue);
                    addCampaignDescriptionLabel.setForeground(Color.blue);
                    addCampaignStartDateLabel.setForeground(Color.blue);
                    addCampaignEndDateLabel.setForeground(Color.blue);
                    addCampaignBudgetLabel.setForeground(Color.blue);
                    
                    addCampaignNameField.setEditable(true);
                    addCampaignDescriptionField.setEditable(true);
                    addCampaignStartDateField.setEditable(true);
                    addCampaignEndDateField.setEditable(true);
                    addCampaignBudgetField.setEditable(true);
                    
                    addCampaignAllButton.setEnabled(true);
                    cancelCampaignButton.setEnabled(true);
                }
                else if(campaignsTable.getValueAt(campaignsTable.getSelectedRow(), 6).equals("STARTED"))
                {
                    if(isAdmin)
                        campaignTitleLabel.setText("Campanie Inceputa: Puteti Edita Sau Anula Campania");
                    else
                        campaignTitleLabel.setText("Campanie Inceputa");
                    
                    campaignTitleLabel.setForeground(Color.BLUE);
                    
                    addCampaignDescriptionField.setEditable(false);
                    addCampaignStartDateField.setEditable(false);
                    
                    addCampaignNameField.setEditable(true);
                    addCampaignEndDateField.setEditable(true);
                    addCampaignBudgetField.setEditable(true);
                    
                    addCampaignNameLabel.setForeground(Color.blue);
                    addCampaignEndDateLabel.setForeground(Color.blue);
                    addCampaignBudgetLabel.setForeground(Color.blue);
                    
                    addCampaignAllButton.setEnabled(true);
                    cancelCampaignButton.setEnabled(true);
                }
                else
                {
                    addCampaignNameField.setEditable(false);
                    addCampaignDescriptionField.setEditable(false);
                    addCampaignStartDateField.setEditable(false);
                    addCampaignEndDateField.setEditable(false);
                    addCampaignBudgetField.setEditable(false); 
                    
                    if(campaignsTable.getValueAt(campaignsTable.getSelectedRow(), 6).equals("EXPIRED"))
                    {
                        campaignTitleLabel.setText("Detalii Campanie: Campanie Expirata");
                        campaignTitleLabel.setForeground(Color.red);
                    }
                    else
                    {
                        campaignTitleLabel.setText("Detalii Campanie: Campanie Anulata");
                        campaignTitleLabel.setForeground(Color.red);
                    }
                    
                    addCampaignAllButton.setEnabled(false);
                    cancelCampaignButton.setEnabled(false);
                }
                
                if(! isAdmin)
                {
                    addCampaignAllButton.setEnabled(false);
                    cancelCampaignButton.setEnabled(false);
                }
                
                addCampaignIDField.setEditable(false);
                addCampaignStrategyField.setEditable(false);
                addCampaignAvailableVouchersField.setEditable(false);
                addCampaignVouchersDictionaryField.setBackground(Color.WHITE);
                addCampaignVouchersDictionaryField.setOpaque(true);
                addCampaignVouchersDictionaryField.setVerticalAlignment(SwingConstants.TOP);
   
                newFrame.getContentPane().add(addCampaignIDField);
                newFrame.getContentPane().add(addCampaignNameField);
                newFrame.getContentPane().add(addCampaignDescriptionField);
                newFrame.getContentPane().add(addCampaignStartDateField);
                newFrame.getContentPane().add(addCampaignEndDateField);
                newFrame.getContentPane().add(addCampaignBudgetField);
                newFrame.getContentPane().add(addCampaignStrategyField);
                newFrame.getContentPane().add(addCampaignAvailableVouchersField);
                newFrame.getContentPane().add(addCampaignVouchersDictionaryField);

                addCampaignAllButton.setBounds(170, 470, 220, 50);
                addCampaignAllButton.setText("Editeaza Campania");
                addCampaignAllButton.setForeground(Color.MAGENTA);

                newFrame.getContentPane().add(addCampaignAllButton);
                
                cancelCampaignButton.setBounds(560, 470, 220, 50);
                cancelCampaignButton.setForeground(Color.MAGENTA);
                
                newFrame.getContentPane().add(cancelCampaignButton);

                newFrame.setVisible(true);
                newFrame.pack();
                
            }
        }
    });
        
        campaignsTable.setAutoCreateRowSorter(true);
        
        String admin = new String();
        if(this.isAdmin)
        {
            admin = "ADMIN";
        }
        else
        {
            admin = "GUEST";
        }
        this.adminLabel = new JLabel(admin);
        
        this.adminLabel.setBounds(0, 0, 130, 50);
        
        this.addCampaignButton.setEnabled(this.isAdmin);
        
        this.getContentPane().add(this.adminLabel);
        
        this.addCampaignButton.setBounds(360, 0, 120, 50);
        
        this.getContentPane().add(this.addCampaignButton);
        
        this.backToMenuButton.setBounds(490, 0, 120, 50);
        this.getContentPane().add(this.backToMenuButton);
        
        TableColumnModel columnModel = campaignsTable.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(50);
        columnModel.getColumn(1).setPreferredWidth(140);
        columnModel.getColumn(2).setPreferredWidth(180);
        columnModel.getColumn(3).setPreferredWidth(180);
        columnModel.getColumn(4).setPreferredWidth(70);
        columnModel.getColumn(5).setPreferredWidth(70);
        columnModel.getColumn(6).setPreferredWidth(90);
        
         sp = new JScrollPane(campaignsTable,
          ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
          ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
         sp.setPreferredSize(new Dimension(780, 500));
         
        this.getContentPane().add(sp); 
        this.setVisible(true);
       
    }
    
    public void showVouchers()
    {
        vcnt = -1;
        
        this.setLayout(new FlowLayout());
        
       
        VMS vms = VMS.getInstance();
        Set<Campaign> campaignsSet = vms.getCampaigns();
        
        model.HashSet<NintendoGift> vouchersSet = new HashSet<NintendoGift>();
        
        Iterator iterator = campaignsSet.iterator();
        while(iterator.hasNext())
        {
            Campaign campaign = (Campaign)iterator.next();
            model.HashSet<NintendoGift> campaignVouchers = campaign.getVouchers();
            //vouchersSet.addAll(campaignVouchers);
        }
        
       // vData = new String[vouchersSet.size()][];
        
        Iterator it = vouchersSet.iterator();
        while(it.hasNext())
        {
           NintendoGift i = (NintendoGift)it.next();
            String[] voucherList = new String[7];
            voucherList[0] = Integer.toString(i.giftID);
            voucherList[1] = i.giftCode;
            voucherList[2] = i.giftStatus.toString();
            if(i.giftUsingDate != null)
                voucherList[3] = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm").format(i.giftUsingDate);
            else
                voucherList[3] = "-";
            voucherList[4] = i.giftUserEmail;
            voucherList[5] = String.valueOf(i.giftCampaignID);
            if(i instanceof NintendoVoucher)
                voucherList[6] = "GiftVoucher";
            else 
                voucherList[6] = "LoyaltyVoucher";
            
            vData[++vcnt] = voucherList;
        }
        
        vouchersTable = new JTable();
        DefaultTableModel defTableModel = new DefaultTableModel(vData, vColumnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        
        vouchersTable.setModel(defTableModel);
        
        vouchersTable.setAutoCreateRowSorter(true);
        
        String admin = new String();
        if(this.isAdmin)
        {
            admin = "ADMIN";
        }
        else
        {
            admin = "GUEST";
        }
        this.adminLabel = new JLabel(admin);
        this.adminLabel.setBounds(0, 0, 130, 50);
        this.getContentPane().add(this.adminLabel);
        
       
        this.searchField.setBounds(140, 0, 100, 50);
        this.getContentPane().add(this.searchField);
        
        this.searchVoucherButton.setBounds(250, 0, 100, 50);
        this.searchVoucherButton.setEnabled(this.isAdmin);
        this.getContentPane().add(this.searchVoucherButton);
        
        this.addVoucherButton.setEnabled(this.isAdmin);
        this.addVoucherButton.setBounds(360, 0, 120, 50);
        this.getContentPane().add(this.addVoucherButton);
        
        this.backToMenuButton.setBounds(490, 0, 120, 50);
        this.getContentPane().add(this.backToMenuButton);
        
        TableColumnModel columnModel = vouchersTable.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(50);
        columnModel.getColumn(1).setPreferredWidth(90);
        columnModel.getColumn(2).setPreferredWidth(130);
        columnModel.getColumn(3).setPreferredWidth(180);
        columnModel.getColumn(4).setPreferredWidth(150);
        columnModel.getColumn(5).setPreferredWidth(70);
        columnModel.getColumn(6).setPreferredWidth(110);
        
         vsp = new JScrollPane(vouchersTable,
          ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
          ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
         vsp.setPreferredSize(new Dimension(780, 500));
         
        this.getContentPane().add(vsp); 
        this.setVisible(true);
    }
    
    public PaginaDeStart()
    {
        this.setPreferredSize(new Dimension(800,600));
        this.setLayout(null);
        titleLabel = new JLabel("Voucher Management System");
        
        
        fileLoaderButton = new JButton("Incarcati Fisierele / Interfata Grafica");
        
        outputGeneratorButton = new JButton("Rulati Testul / output.txt");
        outputLabel = new JLabel();
        
        this.showLoadFiles();
       
        
        userLabel = new JLabel("Utilizator");
        passwordLabel = new JLabel("Parola");
        
        userField = new JTextField();
        passwordField = new JTextField();
        
        loginButton = new JButton("Logare");
        
        campaignsButton = new JButton("Afisati Campaniile");
        vouchersButton = new JButton("Afisati Voucherele");
        
        
        this.adminLabel = new JLabel();
        
        this.addCampaignButton = new JButton("Adauga Campanie");
        
        this.addVoucherButton = new JButton("Genereaza Voucher");
        this.searchVoucherButton = new JButton("Marcheaza");
        
        this.searchField = new JTextField(4);
        
        newFrame = new JFrame();
        
        addCampaignAllButton = new JButton("Adauga Campania");
        
        addCampaignIDField = new JTextField();
        addCampaignNameField = new JTextField();
        addCampaignDescriptionField = new JTextField();
        addCampaignStartDateField = new JTextField();
        addCampaignEndDateField = new JTextField();
        addCampaignBudgetField = new JTextField();
        addCampaignStrategyField = new JTextField();
        addCampaignAvailableVouchersField = new JTextField();
        addCampaignVouchersDictionaryField = new JLabel();
        
        this.campaignTitleLabel = new JLabel("Introduceti datele campaniei");
        
        cancelCampaignButton = new JButton("Anuleaza Campania");
        
        backToMenuButton = new JButton("Inapoi La Meniu");
        
        voucherFrame = new JFrame();
        
        this.voucherTitleLabel = new JLabel("Introduceti datele voucherului");
        
        this.addVoucherValueField = new JTextField();
        this.addVoucherCampaignIDBox = new JComboBox();
        this.addVoucherEmailBox = new JComboBox();
        this.addVoucherTypeBox = new JComboBox();
        this.addVoucherAllButton = new JButton();
        
        this.addCampaignAllButton.addActionListener(this);
        this.addVoucherAllButton.addActionListener(this);
        fileLoaderButton.addActionListener(this);
        outputGeneratorButton.addActionListener(this);
        loginButton.addActionListener(this);
        campaignsButton.addActionListener(this);
        vouchersButton.addActionListener(this);
        addCampaignAllButton.addActionListener(PaginaDeStart.this);
        cancelCampaignButton.addActionListener(PaginaDeStart.this);
        this.addCampaignButton.addActionListener(this);
        this.backToMenuButton.addActionListener(this);
        this.searchVoucherButton.addActionListener(this);
        this.addVoucherButton.addActionListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) 
    {
        if(e.getSource() == outputGeneratorButton)
        {
            this.getContentPane().removeAll();
            this.repaint();
            
            Test obiect = new Test();
            obiect.runEvents(9);
            
            VMS x = VMS.getInstance();
            String output = x.outputString.toString();
            this.setLayout(new FlowLayout());
            outputLabel.setText("<html>" + output.replaceAll("<","&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br/>") + "</html>");
            
            this.outputLabel.setBounds(0, 0, 500, 400);
            
            sp = new JScrollPane(outputLabel,
          ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
          ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
         sp.setPreferredSize(new Dimension(500, 400));
         
            this.getContentPane().add(sp); 
            this.setVisible(true);
        
        }
        
        
        if(e.getSource() == fileLoaderButton)
        {
            
            VMS vms = VMS.getInstance();
            
            this.getContentPane().removeAll();
            this.repaint();
            
            this.showLogin();
        } 
        
        
        else if(e.getSource() == loginButton)
        {
            VMS vms = VMS.getInstance();
            
            String user = userField.getText().trim();
            String password = passwordField.getText().trim();
            
            if(user.equals("") || password.equals(""))
            {
                titleLabel.setText("Atentie! Date de logare incomplete!");
                titleLabel.setForeground(Color.red);
                
            }
            else
            {
                Test obiect = new Test();
                this.isAdmin = obiect.checkAdmin(user);

                if(obiect.checkLogin(user, password) == false)
                {
                    titleLabel.setText("Atentie! Date de logare incorecte!");
                    titleLabel.setForeground(Color.red);
                }
                else
                {
                    
                    this.showCampaignsAndVouchers();
                }
            }
            
        }
        
        
        
        else if(e.getSource() == campaignsButton)
        {
            this.getContentPane().removeAll();
            this.repaint();
            
            this.showCampaigns();
        }
        
        
        else if(e.getSource() == vouchersButton)
        {
            this.getContentPane().removeAll();
            this.repaint();
            
            this.showVouchers();
        }
        
        
        
        else if(e.getSource() == this.addCampaignButton)
        {
            
            
            newFrame.getContentPane().removeAll();
            newFrame.repaint();
            
            
            newFrame.setPreferredSize(new Dimension(800,600));
            newFrame.setLayout(null);
            
            this.campaignTitleLabel.setBounds(50, 50, 700, 50);
            this.campaignTitleLabel.setHorizontalAlignment(SwingConstants.CENTER);
            newFrame.getContentPane().add(this.campaignTitleLabel);
            
            JLabel addCampaignIDLabel = new JLabel("ID");
            JLabel addCampaignNameLabel = new JLabel("Nume");
            JLabel addCampaignDescriptionLabel = new JLabel("Descriere");
            JLabel addCampaignStartDateLabel = new JLabel("Data inceperii");
            JLabel addCampaignEndDateLabel = new JLabel("Data incheierii");
            JLabel addCampaignBudgetLabel = new JLabel("Buget");
            JLabel addCampaignStrategyLabel = new JLabel("Strategie");
            
            addCampaignIDLabel.setBounds(170, 120, 220, 50);
            addCampaignNameLabel.setBounds(170, 170, 220, 50);
            addCampaignDescriptionLabel.setBounds(170, 220, 220, 50);
            addCampaignStartDateLabel.setBounds(170, 270, 220, 50);
            addCampaignEndDateLabel.setBounds(170, 320, 220, 50);
            addCampaignBudgetLabel.setBounds(170, 370, 220, 50);
            addCampaignStrategyLabel.setBounds(170, 420, 220, 50);
           
            newFrame.getContentPane().add(addCampaignIDLabel);
            newFrame.getContentPane().add(addCampaignNameLabel);
            newFrame.getContentPane().add(addCampaignDescriptionLabel);
            newFrame.getContentPane().add(addCampaignStartDateLabel);
            newFrame.getContentPane().add(addCampaignEndDateLabel);
            newFrame.getContentPane().add(addCampaignBudgetLabel);
            newFrame.getContentPane().add(addCampaignStrategyLabel);
             
            
            addCampaignIDField.setBounds(410, 120, 220, 50);
            addCampaignNameField.setBounds(410, 170, 220, 50);
            addCampaignDescriptionField.setBounds(410, 220, 220, 50);
            addCampaignStartDateField.setBounds(410, 270, 220, 50);
            addCampaignEndDateField.setBounds(410, 320, 220, 50);
            addCampaignBudgetField.setBounds(410, 370, 220, 50);
            addCampaignStrategyField.setBounds(410, 420, 220, 50);
            
            addCampaignIDField.setText("");
            addCampaignNameField.setText("");
            addCampaignDescriptionField.setText("");
            addCampaignStartDateField.setText("yyyy-MM-dd HH:mm");
            addCampaignEndDateField.setText("yyyy-MM-dd HH:mm");
            addCampaignBudgetField.setText("");
            addCampaignStrategyField.setText("");
            
            addCampaignIDField.setEditable(true);
            addCampaignNameField.setEditable(true);
            addCampaignDescriptionField.setEditable(true);
            addCampaignStartDateField.setEditable(true);
            addCampaignEndDateField.setEditable(true);
            addCampaignBudgetField.setEditable(true);
            addCampaignStrategyField.setEditable(true);
            
            
            campaignTitleLabel.setText("Introduceti datele campaniei");
            addCampaignAllButton.setText("Adauga Campania");
            addCampaignAllButton.setForeground(Color.MAGENTA);
            
            newFrame.getContentPane().add(addCampaignIDField);
            newFrame.getContentPane().add(addCampaignNameField);
            newFrame.getContentPane().add(addCampaignDescriptionField);
            newFrame.getContentPane().add(addCampaignStartDateField);
            newFrame.getContentPane().add(addCampaignEndDateField);
            newFrame.getContentPane().add(addCampaignBudgetField);
            newFrame.getContentPane().add(addCampaignStrategyField);
            
            this.addCampaignAllButton.setBounds(290, 470, 220, 50);
            
            newFrame.getContentPane().add(addCampaignAllButton);
            
            newFrame.setVisible(true);
            newFrame.pack();
        }
        
        
        
        else if(e.getSource() == this.addCampaignAllButton)
        {
            if(this.addCampaignAllButton.getText().equals("Adauga Campania"))
            {

                 DefaultTableModel tableModel = (DefaultTableModel)this.campaignsTable.getModel();

                 String idString = this.addCampaignIDField.getText().trim();
                 String nameString = this.addCampaignNameField.getText().trim();
                 String descriptionString = this.addCampaignDescriptionField.getText().trim();
                 String startDateString = this.addCampaignStartDateField.getText().trim();
                 String endDateString = this.addCampaignEndDateField.getText().trim();
                 String budgetString = this.addCampaignBudgetField.getText().trim();
                 String strategyString = this.addCampaignStrategyField.getText().trim();

                 VMS vms = VMS.getInstance();

                 if(idString.equals("") || descriptionString.equals("") || startDateString.equals("") || endDateString.equals("") || budgetString.equals("") || strategyString.equals(""))
                 {
                     this.campaignTitleLabel.setText("Toate campurile sunt obligatorii!!!");
                     this.campaignTitleLabel.setForeground(Color.red);
                 }
                 else
                 {
                     if(vms.getCampaign(Integer.parseInt(idString)) != null)
                     {
                         this.campaignTitleLabel.setText("Campania exista deja!!!");
                         this.campaignTitleLabel.setForeground(Color.red);
                     }
                     else
                     {
                         int id = Integer.parseInt(idString);
                         String name = nameString;
                         String description = descriptionString;
                         DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                         LocalDateTime start_date = LocalDateTime.parse(startDateString, formatter);
                         LocalDateTime end_date = LocalDateTime.parse(endDateString, formatter);
                         int budget = Integer.parseInt(budgetString);
                         char strategy = strategyString.charAt(0);
                         int becStatus = 0;

                         LocalDateTime currentDate = LocalDateTime.now();
                         if(currentDate.isBefore(start_date))
                             becStatus = 0;
                         else if(currentDate.isAfter(start_date) && currentDate.isBefore(end_date))
                             becStatus = 1;
                         else if(currentDate.isAfter(end_date))
                             becStatus = 2;

                         Campaign newCampaign = new Campaign(id,name,description,start_date,end_date,budget,strategy,becStatus);

                         vms.addCampaign(newCampaign);



                         String[] campaignList = new String[7];
                         campaignList[0] = idString;
                         campaignList[1] = name;
                         campaignList[2] = startDateString;
                         campaignList[3] = endDateString;
                         campaignList[4] = budgetString;
                         campaignList[5] = strategyString;
                         if(becStatus == 0)
                             campaignList[6] = "NEW";
                         else if(becStatus == 1)
                             campaignList[6] = "STARTED";
                         else
                             campaignList[6] = "EXPIRED";

                         tableModel.addRow(campaignList);


                         this.newFrame.setVisible(false);
                         this.newFrame.dispose();

                     }
                 }
            
            }
            
            else if(this.addCampaignAllButton.getText().equals("Editeaza Campania"))
            {
               
                
                String idString = this.addCampaignIDField.getText().trim();
                String nameString = this.addCampaignNameField.getText().trim();
                String descriptionString = this.addCampaignDescriptionField.getText().trim();
                String startDateString = this.addCampaignStartDateField.getText().trim();
                String endDateString = this.addCampaignEndDateField.getText().trim();
                String budgetString = this.addCampaignBudgetField.getText().trim();
                String strategyString = this.addCampaignStrategyField.getText().trim();
                
                int id = Integer.parseInt(idString);
                String name = nameString;
                String description = descriptionString;
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                LocalDateTime start_date = LocalDateTime.parse(startDateString, formatter);
                LocalDateTime end_date = LocalDateTime.parse(endDateString, formatter);
                int budget = Integer.parseInt(budgetString);
                char strategy = strategyString.charAt(0);
                int becStatus = 0;

                LocalDateTime currentDate = LocalDateTime.now();
                if(currentDate.isBefore(start_date))
                    becStatus = 0;
                else if(currentDate.isAfter(start_date) && currentDate.isBefore(end_date))
                    becStatus = 1;
                else if(currentDate.isAfter(end_date))
                    becStatus = 2;

                Campaign newCampaign = new Campaign(id,name,description,start_date,end_date,budget,strategy,becStatus);
                
                VMS vms = VMS.getInstance();
                
                vms.updateCampaign(id, newCampaign);
    
                DefaultTableModel tableModel = (DefaultTableModel)this.campaignsTable.getModel();
                
                tableModel.setValueAt(idString,campaignsTable.getSelectedRow(),0);
                tableModel.setValueAt(nameString,campaignsTable.getSelectedRow(),1);
                tableModel.setValueAt(startDateString,campaignsTable.getSelectedRow(),2);
                tableModel.setValueAt(endDateString,campaignsTable.getSelectedRow(),3);
                tableModel.setValueAt(budgetString,campaignsTable.getSelectedRow(),4);
                tableModel.setValueAt(strategyString,campaignsTable.getSelectedRow(),5);
          
                
                if(becStatus == 0)
                    tableModel.setValueAt("NEW",campaignsTable.getSelectedRow(),6);
                else if(becStatus == 1)
                    tableModel.setValueAt("STARTED",campaignsTable.getSelectedRow(),6);
                else
                    tableModel.setValueAt("EXPIRED",campaignsTable.getSelectedRow(),6);
                
                if(end_date.isBefore(LocalDateTime.now()))
                    tableModel.setValueAt("EXPIRED",campaignsTable.getSelectedRow(),6);
                
                this.newFrame.setVisible(false);
                this.newFrame.dispose();
            }
            
        }
        
        
        
        else if(e.getSource() == cancelCampaignButton)
        {
            VMS vms = VMS.getInstance();
            
            int id = Integer.parseInt(addCampaignIDField.getText());
            
            vms.cancelCampaign(id);
            
            DefaultTableModel tableModel = (DefaultTableModel)this.campaignsTable.getModel();
            tableModel.setValueAt("CANCELLED",campaignsTable.getSelectedRow(),6);
            
            this.newFrame.setVisible(false);
            this.newFrame.dispose();
            
        }
        
        
        
        else if(e.getSource() == backToMenuButton)
        {
            this.showCampaignsAndVouchers();
        }
     
        
        
        
        else if(e.getSource() == this.addVoucherButton)
        {
            voucherFrame.getContentPane().removeAll();
            voucherFrame.repaint();
            
            
            voucherFrame.setPreferredSize(new Dimension(800,600));
            voucherFrame.setLayout(null);
            
            this.addVoucherCampaignIDBox.removeAllItems();
            this.addVoucherEmailBox.removeAllItems();
            this.addVoucherTypeBox.removeAllItems();
            
            this.voucherTitleLabel.setBounds(50, 50, 700, 50);
            this.voucherTitleLabel.setHorizontalAlignment(SwingConstants.CENTER);
            voucherFrame.getContentPane().add(this.voucherTitleLabel);
            
            JLabel addVoucherIDLabel = new JLabel("ID Campanie");
            JLabel addVoucherEmailLabel = new JLabel("Email Utilizator");
            JLabel addVoucherTypeLabel = new JLabel("Tip");
            JLabel addVoucherValueLabel = new JLabel("Valoare");
            
            addVoucherIDLabel.setBounds(170, 120, 220, 50);
            addVoucherEmailLabel.setBounds(170, 170, 220, 50);
            addVoucherTypeLabel.setBounds(170, 220, 220, 50);
            addVoucherValueLabel.setBounds(170, 270, 220, 50);
            
            voucherFrame.getContentPane().add(addVoucherIDLabel);
            voucherFrame.getContentPane().add(addVoucherEmailLabel);
            voucherFrame.getContentPane().add(addVoucherTypeLabel);
            voucherFrame.getContentPane().add(addVoucherValueLabel);
            
            
            this.addVoucherCampaignIDBox.setBounds(410, 120, 220, 50);
            this.addVoucherEmailBox.setBounds(410, 170, 220, 50);
            this.addVoucherTypeBox.setBounds(410, 220, 220, 50);
            this.addVoucherValueField.setBounds(410, 270, 220, 50);
            
            VMS vms = VMS.getInstance();
            Set<Campaign> campaigns = vms.getCampaigns();
            Iterator it = campaigns.iterator();
            while(it.hasNext())
            {
                Campaign i = (Campaign)it.next();
                if(i.campaignStatus != i.campaignStatus.EXPIRED && i.campaignStatus != i.campaignStatus.CANCELLED)
                    this.addVoucherCampaignIDBox.addItem(i.campaignID);
            }
            
            Set<User> users = vms.getUsers();
            Iterator it2 = users.iterator();
            while(it2.hasNext())
            {
                User i = (User)it2.next();
                this.addVoucherEmailBox.addItem(i.userEmail);
            }
            
            this.addVoucherTypeBox.addItem("GiftVoucher");
            this.addVoucherTypeBox.addItem("LoyaltyVoucher");
            
            this.addVoucherValueField.setEditable(true);
            this.addVoucherValueField.setText("");
            
            
            addVoucherAllButton.setText("Adauga Voucherul");
            addVoucherAllButton.setForeground(Color.MAGENTA);
            
            voucherFrame.getContentPane().add(this.addVoucherCampaignIDBox);
            voucherFrame.getContentPane().add(this.addVoucherEmailBox);
            voucherFrame.getContentPane().add(this.addVoucherTypeBox);
            voucherFrame.getContentPane().add(this.addVoucherValueField);
            
            this.addVoucherAllButton.setBounds(290, 470, 220, 50);
            //this.addVoucherAllButton.addActionListener(this);
            
            voucherFrame.getContentPane().add(addVoucherAllButton);
            
            voucherFrame.setVisible(true);
            voucherFrame.pack();
        }
        
        
        
        else if(e.getSource() == this.addVoucherAllButton)
        {
        
            DefaultTableModel tableModel = (DefaultTableModel)this.vouchersTable.getModel();

                 String idString = this.addVoucherCampaignIDBox.getSelectedItem().toString();
                 String emailString = this.addVoucherEmailBox.getSelectedItem().toString();
                 String typeString = this.addVoucherTypeBox.getSelectedItem().toString();
                 String valueString = this.addVoucherValueField.getText().trim();
                 
                 VMS vms = VMS.getInstance();

                 if(valueString.equals(""))
                 {
                     this.voucherTitleLabel.setText("Toate campurile sunt obligatorii!!!");
                     this.voucherTitleLabel.setForeground(Color.red);
                 }
                 else
                 {                        
                        int id = Integer.parseInt(idString);
                        Campaign campaign = vms.getCampaign(id); 
                        String email = emailString;
                        String voucherType = typeString;
                        float value = Float.parseFloat(valueString);
                        
                        campaign.generateVoucher(email, voucherType, value);
                         
                     
                        String[] voucherList = new String[7];
                        
                        
                        int generatedVoucherID =  campaign.campaignTotalGiftsNumber - campaign.campaignAvailableGiftsNumber;
                        String code  = generatedVoucherID + "";
                        
                        voucherList[0] = generatedVoucherID + "";
                        voucherList[1] = code;
                        voucherList[2] = "UNUSED";
                        voucherList[3] = "";
                        voucherList[4] = email;
                        voucherList[5] = id + "";
                        voucherList[6] = voucherType;

                        tableModel.addRow(voucherList);

                         this.voucherFrame.setVisible(false);
                         this.voucherFrame.dispose();

                     
                 }
              
        }
        
        
        
        else if(e.getSource() == this.searchVoucherButton)
        {
            DefaultTableModel tableModel = (DefaultTableModel)this.vouchersTable.getModel();
            
            String voucherCodeInput = this.searchField.getText().trim();
            
            int nrRows = this.vouchersTable.getRowCount();
            int campaignId = -1;
            int i;
            for(i=0;i<nrRows;i++)
            {
                if(this.vouchersTable.getValueAt(i, 1).equals(voucherCodeInput))
                {
                    campaignId = Integer.parseInt(this.vouchersTable.getValueAt(i, 5).toString());
                    break;
                }
            }
            if(campaignId != -1)
            {
                LocalDateTime nowTime = LocalDateTime.now();
                VMS vms = VMS.getInstance();
                Campaign campaign = vms.getCampaign(campaignId);
                campaign.redeemVoucher(voucherCodeInput, nowTime);
                tableModel.setValueAt("USED", i, 2);
                
                tableModel.setValueAt(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm").format(nowTime), i, 3);
            }
    
        }
    }
    
}

