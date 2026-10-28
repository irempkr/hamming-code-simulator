package proje;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.*;

public class MainFrame extends JFrame {

    private JTextField txtData;
    private JTextField txtErrorBit;
    private JTextArea txtResult;
   

    private JButton btnGenerate;
    private JButton btnError;
    private JButton btnCorrect;

    private JComboBox<String> cmbLength;

    private Memory memory;

    private String generatedCode = "";
    private String corruptedCode = "";

    public MainFrame() {

        memory = new Memory();

        setTitle("BLM230 - Hamming Hata Düzeltme Kodu Simülatörü");
        setSize(950, 700);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        getContentPane().setBackground(
                new Color(220, 240, 220));

        setLayout(null);

        initializeComponents();
        setupEvents();

        setVisible(true);
    }

    private void initializeComponents() {

        JLabel title = new JLabel(
                "HAMMING HATA DÜZELTME KODU SİMÜLATÖRÜ",
                SwingConstants.CENTER);

        title.setFont(
                new Font("Arial",
                        Font.BOLD,
                        28));

        title.setBounds(
                120,
                30,
                700,
                40);

        add(title);

        JLabel lblLength =
                new JLabel("Veri Uzunluğu:");

        lblLength.setBounds(
                70,
                120,
                100,
                25);

        add(lblLength);

        cmbLength =
                new JComboBox<>();

        cmbLength.addItem("8 Bit");
        cmbLength.addItem("16 Bit");
        cmbLength.addItem("32 Bit");

        cmbLength.setBounds(
                170,
                120,
                90,
                25);

        add(cmbLength);

        JLabel lblData =
                new JLabel("Veri Giriniz:");

        lblData.setBounds(
                320,
                120,
                100,
                25);

        add(lblData);

        txtData =
                new JTextField();

        txtData.setBounds(
                410,
                120,
                260,
                25);

        add(txtData);

        JLabel lblError =
                new JLabel("Hata Pozisyonu:");

        lblError.setBounds(
                690,
                120,
                100,
                25);

        add(lblError);
        
        JLabel flow =
        		new JLabel(
        		"Data In → Hamming → Memory → Error → Syndrome → Correct");

        		flow.setBounds(
        		        180,
        		        170,
        		        600,
        		        20);

        		add(flow);
        		
        		JLabel info = new JLabel(
        			    "BLM230 Bilgisayar Mimarisi - Hamming Error Correcting Code Simulator",
        			    SwingConstants.CENTER);

        			info.setBounds(
        			        120,
        			        70,
        			        700,
        			        20);

        			add(info);
        			
        			

        txtErrorBit =
                new JTextField();

        txtErrorBit.setBounds(
                800,
                120,
                80,
                25);

        add(txtErrorBit);

        btnGenerate =
                new JButton(
                        "Hamming Kodu Oluştur");

        btnGenerate.setBounds(
                250,
                200,
                180,
                40);

        add(btnGenerate);

        btnError =
                new JButton(
                        "Hata Ekle");

        btnError.setBounds(
                450,
                200,
                120,
                40);

        add(btnError);

        btnCorrect =
                new JButton(
                        "Hata Tespit Et & Düzelt");

        btnCorrect.setBounds(
                590,
                200,
                220,
                40);

        add(btnCorrect);
        
        btnGenerate.setBackground(new Color(100, 180, 100));
        btnGenerate.setForeground(Color.BLACK);

        btnError.setBackground(new Color(255, 200, 100));
        btnError.setForeground(Color.BLACK);

        btnCorrect.setBackground(new Color(120, 180, 255));
        btnCorrect.setForeground(Color.BLACK);
        

        JLabel lblResult =
                new JLabel(
                        "Simülasyon Sonuçları");

        lblResult.setFont(
                new Font("Arial",
                        Font.BOLD,
                        14));

        lblResult.setBounds(
                180,
                250,
                200,
                20);

        add(lblResult);
     


        txtResult =
                new JTextArea();

        txtResult.setEditable(false);

        txtResult.setFont(
                new Font("Monospaced",
                        Font.PLAIN,
                        16));
        
        txtResult.setBackground(
                new Color(245,245,245));

        txtResult.setBorder(
                BorderFactory.createLineBorder(
                        Color.GRAY,2));

        JScrollPane scroll =
                new JScrollPane(txtResult);

        scroll.setBounds(
                180,
                280,
                600,
                320);

        add(scroll);
    }

    private void setupEvents() {

        btnGenerate.addActionListener(
                new ActionListener() {

                    @Override
                    public void actionPerformed(
                            ActionEvent e) {

                        String data =
                                txtData.getText().trim();
                        
                        if(data.isEmpty()) {

                            JOptionPane.showMessageDialog(
                                    null,
                                    "Lütfen veri giriniz!");

                            return;
                        }

                        if (!HammingCode.isBinary(data)) {

                            JOptionPane.showMessageDialog(
                                    null,
                                    "Sadece 0 ve 1 giriniz!");

                            return;
                        }

                        int selectedLength;

                        if (cmbLength
                                .getSelectedItem()
                                .toString()
                                .startsWith("8")) {

                            selectedLength = 8;

                        } else if (cmbLength
                                .getSelectedItem()
                                .toString()
                                .startsWith("16")) {

                            selectedLength = 16;

                        } else {

                            selectedLength = 32;
                        }

                        if (data.length()
                                != selectedLength) {

                            JOptionPane.showMessageDialog(
                                    null,
                                    "Seçilen uzunluk ile veri uzunluğu eşleşmiyor!");

                            return;
                        }

                        generatedCode =
                                HammingCode.encode(data);

                        memory.write(generatedCode);
                        
             

                        txtResult.setText(

                                "=== BELLEK DURUMU ===\n\n"

                                + "Bellekteki Kod:\n"
                                + memory.read()

                                + "\n\n-------------------------\n\n"

                                + "Girilen Veri:\n"
                                + data

                                + "\n\nOluşturulan Hamming Kodu:\n"
                                + generatedCode

                                + "\n\nBelleğe Yazıldı."
                        );
                    }
                });

        btnError.addActionListener(
                new ActionListener() {

                    @Override
                    public void actionPerformed(
                            ActionEvent e) {

                        if (memory.isEmpty()) {

                            JOptionPane.showMessageDialog(
                                    null,
                                    "Önce Hamming kodu oluştur!");

                            return;
                        }

                        try {

                            int position =
                                    Integer.parseInt(
                                            txtErrorBit.getText());

                            corruptedCode =
                                    HammingCode.createError(
                                            memory.read(),
                                            position);
                            
                          

                            int syndrome = 0;
							String corrected = null;
							
							txtResult.setText(

                                    "=== BELLEK DURUMU ===\n\n"

                                    + "Bellekteki Kod:\n"
                                    + memory.read()

                                    + "\n\n-------------------------\n\n"

                                    + "Bozulmuş (Hatalı) Kod:\n"
                                    + corruptedCode
                            );

                        } catch (Exception ex) {

                            JOptionPane.showMessageDialog(
                                    null,
                                    "Geçersiz bit pozisyonu!");
                        }
                    }
                });

        btnCorrect.addActionListener(
                new ActionListener() {

                    @Override
                    public void actionPerformed(
                            ActionEvent e) {

                        if (corruptedCode.isEmpty()) {

                            JOptionPane.showMessageDialog(
                                    null,
                                    "Önce hata oluştur!");

                            return;
                        }

                        int syndrome =
                                HammingCode.detectError(
                                        corruptedCode);

                        String corrected =
                                HammingCode.correctError(
                                        corruptedCode,
                                        syndrome);

                        txtResult.setText(

                                "Bozulmuş (Hatalı) Kod:\n"
                                        + corruptedCode

                                        + "\n\nSendrom (Binary): "
                                        		+ Integer.toBinaryString(
                                        				syndrome)

                                        				+ "\nSendrom (Decimal): "
                                        				+ syndrome

                                        + "\n\nTespit Edilen Hata Pozisyonu: "
                                        + syndrome

                                        + "\n\nDüzeltilmiş Kod:\n"
                                        + corrected

                        );
                    }
                });
    }
}