// Ultimate SRB2 Launcher
// Has more features than SSNTails's The Ultimate SRB2 Launcher!
// =============================================================
// Built with NetBeans 8.2
// © Rex "ThatAwesomeGuy173" James, 2017.
// =============================================================

package Interface;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Properties;
import java.awt.Desktop;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.net.URI;
import java.net.URISyntaxException;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.text.*;
import javax.swing.filechooser.FileNameExtensionFilter;


public class MainWindow extends javax.swing.JFrame {
    String versionNumber = "v1.2 dev"; // version number! change this every release, kthx
    
    public MainWindow() {
        initComponents();
        
        // Save & load functionality
        Properties prop = new Properties();
    	InputStream input = null;
        
        try {
            // Load the config
            input = new FileInputStream("ultimatesrb2launcher.cfg");
            prop.load(input);
            
            // Set the defaults for the first-time run
            // Also useful when upgrading from a previous version, else this won't open
            if (prop.getProperty("misc.executable") == null) prop.setProperty("misc.executable", "srb2win.exe");
            if (prop.getProperty("misc.parameters") == null) prop.setProperty("misc.parameters", "");
            if (prop.getProperty("player.name") == null) prop.setProperty("player.name", "Sonic");
            if (prop.getProperty("player.color") == null) prop.setProperty("player.color", "Blue");
            if (prop.getProperty("player.skin") == null) prop.setProperty("player.skin", "Sonic");
            if (prop.getProperty("video.renderer") == null) prop.setProperty("video.renderer", "Software");
            if (prop.getProperty("sound.digital") == null) prop.setProperty("sound.digital", "true");
            if (prop.getProperty("sound.midi") == null) prop.setProperty("sound.midi", "true");
            if (prop.getProperty("sound.sfx") == null) prop.setProperty("sound.sfx", "true");

            // Grab all the saved properties here
            txtExecutable.setText(prop.getProperty("misc.executable"));
            txtParameters.setText(prop.getProperty("misc.parameters"));
            txtName.setText(prop.getProperty("player.name"));
            comColor.setSelectedItem(prop.getProperty("player.color"));
            comSkin.setSelectedItem(prop.getProperty("player.skin"));
            if (prop.getProperty("video.renderer").equals("OpenGL")) radOpenGL.setSelected(true); else radSoftware.setSelected(true);
            if (prop.getProperty("sound.digital").equals("false")) chkDigital.setSelected(false); else chkDigital.setSelected(true);
            if (prop.getProperty("sound.midi").equals("false")) chkMIDI.setSelected(false); else chkMIDI.setSelected(true);
            if (prop.getProperty("sound.sfx").equals("false")) chkSFX.setSelected(false); else chkSFX.setSelected(true);
                       
	} 
        catch (IOException | NullPointerException ex) {
            Logger.getLogger(MainWindow.class.getName()).log(Level.WARNING, "Some properties were missing, resetting to defaults", ex);
        } 
        finally {
            if (input != null) {
                try {
                    input.close();
		} 
                catch (IOException ex) {}
            }
	}
        
        // Save everything on exit
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            OutputStream output = null;
            try {
                // Output everything to ultimatesrb2launcher.cfg
                output = new FileOutputStream("ultimatesrb2launcher.cfg");
                
                prop.setProperty("misc.executable", txtExecutable.getText());
                prop.setProperty("misc.parameters", txtParameters.getText());
                prop.setProperty("player.name", txtName.getText());
                prop.setProperty("player.color", comColor.getSelectedItem().toString());
                prop.setProperty("player.skin", comSkin.getSelectedItem().toString());
                if (chkDigital.isSelected()) prop.setProperty("sound.digital", "true"); else prop.setProperty("sound.digital", "false");
                if (chkMIDI.isSelected()) prop.setProperty("sound.midi", "true"); else prop.setProperty("sound.midi", "false");
                if (chkSFX.isSelected()) prop.setProperty("sound.sfx", "true"); else prop.setProperty("sound.sfx", "false");
                if (radOpenGL.isSelected()) prop.setProperty("video.renderer", "OpenGL"); else prop.setProperty("video.renderer", "Software");
                
                prop.store(output, null);
            }
            catch (IOException ex) {
                JOptionPane.showMessageDialog(null,
                "An error occurred while saving the configuration.",
                "Error",
                JOptionPane.ERROR_MESSAGE);
            }
            finally {
                if (output != null) {
                    try {
                        output.close();
                    }
                    catch (IOException ex) {}
                }
            }
        }));
        
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        grpRenderer = new javax.swing.ButtonGroup();
        grpIcon = new javax.swing.ButtonGroup();
        tabpanelMain = new javax.swing.JTabbedPane();
        tabMain = new javax.swing.JPanel();
        panSound = new javax.swing.JPanel();
        chkDigital = new javax.swing.JCheckBox();
        chkSFX = new javax.swing.JCheckBox();
        chkMIDI = new javax.swing.JCheckBox();
        sepSound = new javax.swing.JSeparator();
        panPlayer = new javax.swing.JPanel();
        txtName = new javax.swing.JTextField();
        lblName = new javax.swing.JLabel();
        lblColor = new javax.swing.JLabel();
        lblSkin = new javax.swing.JLabel();
        comColor = new javax.swing.JComboBox<>();
        comSkin = new javax.swing.JComboBox<>();
        panMisc = new javax.swing.JPanel();
        lblCommandline = new javax.swing.JLabel();
        lblExecutable = new javax.swing.JLabel();
        txtParameters = new javax.swing.JTextField();
        txtExecutable = new javax.swing.JTextField();
        btnParametersHelp = new javax.swing.JButton();
        btnExecutableSelect = new javax.swing.JButton();
        panRenderer = new javax.swing.JPanel();
        radSoftware = new javax.swing.JRadioButton();
        radOpenGL = new javax.swing.JRadioButton();
        tabLauncher = new javax.swing.JPanel();
        panTheme = new javax.swing.JPanel();
        lblIcon = new javax.swing.JLabel();
        lblLauncherName = new javax.swing.JLabel();
        txtCustomIcon = new javax.swing.JTextField();
        txtLauncherName = new javax.swing.JTextField();
        radPreset = new javax.swing.JRadioButton();
        radCustom = new javax.swing.JRadioButton();
        cboIcon = new javax.swing.JComboBox<>();
        chkShowVersion = new javax.swing.JCheckBox();
        btnCustomIcon = new javax.swing.JButton();
        btnApply = new javax.swing.JButton();
        sepTheme = new javax.swing.JSeparator();
        sepBottom = new javax.swing.JSeparator();
        btnStart = new javax.swing.JButton();
        tbrMain = new javax.swing.JMenuBar();
        mnuFile = new javax.swing.JMenu();
        itmNew = new javax.swing.JMenuItem();
        itmOpen = new javax.swing.JMenuItem();
        itmSave = new javax.swing.JMenuItem();
        itmSaveAs = new javax.swing.JMenuItem();
        sepFile = new javax.swing.JPopupMenu.Separator();
        itmOpenFolder = new javax.swing.JMenuItem();
        mnuEdit = new javax.swing.JMenu();
        itmAbout = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Ultimate SRB2 Launcher "+ versionNumber);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setName("launcherwindow"); // NOI18N
        setResizable(false);

        tabpanelMain.setBackground(new java.awt.Color(255, 255, 255));

        tabMain.setBackground(new java.awt.Color(255, 255, 255));

        panSound.setBackground(new java.awt.Color(255, 255, 255));
        panSound.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Sound"));

        chkDigital.setBackground(new java.awt.Color(255, 255, 255));
        chkDigital.setSelected(true);
        chkDigital.setText("Digital");

        chkSFX.setBackground(new java.awt.Color(255, 255, 255));
        chkSFX.setSelected(true);
        chkSFX.setText("SFX");

        chkMIDI.setBackground(new java.awt.Color(255, 255, 255));
        chkMIDI.setSelected(true);
        chkMIDI.setText("MIDI");

        javax.swing.GroupLayout panSoundLayout = new javax.swing.GroupLayout(panSound);
        panSound.setLayout(panSoundLayout);
        panSoundLayout.setHorizontalGroup(
            panSoundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panSoundLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panSoundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(sepSound)
                    .addGroup(panSoundLayout.createSequentialGroup()
                        .addGroup(panSoundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(chkMIDI)
                            .addComponent(chkDigital)
                            .addComponent(chkSFX))
                        .addGap(0, 6, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panSoundLayout.setVerticalGroup(
            panSoundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panSoundLayout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addComponent(chkDigital)
                .addGap(2, 2, 2)
                .addComponent(chkMIDI, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(sepSound, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(chkSFX)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        panPlayer.setBackground(new java.awt.Color(255, 255, 255));
        panPlayer.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Player"));

        txtName.setText("Sonic");

        lblName.setText("Name");

        lblColor.setText("Color");

        lblSkin.setText("Skin");

        comColor.setMaximumRowCount(14);
        comColor.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "White", "Siver", "Grey", "Black", "Cyan", "Teal", "Steel Blue", "Blue", "Peach", "Tan", "Pink", "Lavender", "Purple", "Orange", "Rosewood", "Beige", "Brown", "Red", "Dark Red", "Neon Green", "Green", "Zim", "Olive", "Yellow", "Gold" }));
        comColor.setSelectedItem("Blue");

        comSkin.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Sonic", "Tails", "Knuckles" }));

        javax.swing.GroupLayout panPlayerLayout = new javax.swing.GroupLayout(panPlayer);
        panPlayer.setLayout(panPlayerLayout);
        panPlayerLayout.setHorizontalGroup(
            panPlayerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panPlayerLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panPlayerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblName)
                    .addComponent(lblColor))
                .addGap(18, 18, 18)
                .addGroup(panPlayerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panPlayerLayout.createSequentialGroup()
                        .addComponent(comColor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                        .addComponent(lblSkin)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(comSkin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtName))
                .addContainerGap())
        );
        panPlayerLayout.setVerticalGroup(
            panPlayerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panPlayerLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panPlayerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblName)
                    .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panPlayerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblColor)
                    .addComponent(comColor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comSkin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblSkin))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panMisc.setBackground(new java.awt.Color(255, 255, 255));
        panMisc.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Misc."));
        panMisc.setName(""); // NOI18N

        lblCommandline.setText("Command-line parameters");

        lblExecutable.setText("Executable");

        txtExecutable.setText("srb2win.exe");

        btnParametersHelp.setText("?");
        btnParametersHelp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnParametersHelpActionPerformed(evt);
            }
        });

        btnExecutableSelect.setText("...");
        btnExecutableSelect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExecutableSelectActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panMiscLayout = new javax.swing.GroupLayout(panMisc);
        panMisc.setLayout(panMiscLayout);
        panMiscLayout.setHorizontalGroup(
            panMiscLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panMiscLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panMiscLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblCommandline)
                    .addComponent(lblExecutable)
                    .addGroup(panMiscLayout.createSequentialGroup()
                        .addGroup(panMiscLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtParameters, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtExecutable, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panMiscLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnParametersHelp)
                            .addComponent(btnExecutableSelect, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panMiscLayout.setVerticalGroup(
            panMiscLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panMiscLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(lblCommandline)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panMiscLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtParameters, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnParametersHelp))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblExecutable)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panMiscLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtExecutable, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnExecutableSelect))
                .addGap(0, 12, Short.MAX_VALUE))
        );

        panRenderer.setBackground(new java.awt.Color(255, 255, 255));
        panRenderer.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Renderer"));

        radSoftware.setBackground(new java.awt.Color(255, 255, 255));
        grpRenderer.add(radSoftware);
        radSoftware.setSelected(true);
        radSoftware.setText("Software");

        radOpenGL.setBackground(new java.awt.Color(255, 255, 255));
        grpRenderer.add(radOpenGL);
        radOpenGL.setText("OpenGL");

        javax.swing.GroupLayout panRendererLayout = new javax.swing.GroupLayout(panRenderer);
        panRenderer.setLayout(panRendererLayout);
        panRendererLayout.setHorizontalGroup(
            panRendererLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panRendererLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panRendererLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(radOpenGL)
                    .addComponent(radSoftware))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panRendererLayout.setVerticalGroup(
            panRendererLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panRendererLayout.createSequentialGroup()
                .addContainerGap(9, Short.MAX_VALUE)
                .addComponent(radSoftware, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(radOpenGL)
                .addGap(15, 15, 15))
        );

        javax.swing.GroupLayout tabMainLayout = new javax.swing.GroupLayout(tabMain);
        tabMain.setLayout(tabMainLayout);
        tabMainLayout.setHorizontalGroup(
            tabMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabMainLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tabMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(tabMainLayout.createSequentialGroup()
                        .addComponent(panPlayer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panRenderer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(tabMainLayout.createSequentialGroup()
                        .addComponent(panMisc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panSound, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        tabMainLayout.setVerticalGroup(
            tabMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabMainLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tabMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panRenderer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panPlayer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(tabMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panMisc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panSound, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tabpanelMain.addTab("Main", tabMain);

        tabLauncher.setBackground(new java.awt.Color(255, 255, 255));

        panTheme.setBackground(new java.awt.Color(255, 255, 255));
        panTheme.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Theme"));

        lblIcon.setText("Icon");

        lblLauncherName.setText("Launcher Name");

        txtCustomIcon.setEnabled(false);

        txtLauncherName.setText("Ultimate SRB2 Launcher");

        radPreset.setBackground(new java.awt.Color(255, 255, 255));
        grpIcon.add(radPreset);
        radPreset.setSelected(true);
        radPreset.setText("Use Preset");
        radPreset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radPresetActionPerformed(evt);
            }
        });

        radCustom.setBackground(new java.awt.Color(255, 255, 255));
        grpIcon.add(radCustom);
        radCustom.setText("Custom");
        radCustom.setEnabled(false);
        radCustom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radCustomActionPerformed(evt);
            }
        });

        cboIcon.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Sonic", "Tails", "Knuckles" }));

        chkShowVersion.setBackground(new java.awt.Color(255, 255, 255));
        chkShowVersion.setSelected(true);
        chkShowVersion.setText("Show version number");

        btnCustomIcon.setText("...");
        btnCustomIcon.setEnabled(false);
        btnCustomIcon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCustomIconActionPerformed(evt);
            }
        });

        btnApply.setText("Apply");
        btnApply.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnApplyActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panThemeLayout = new javax.swing.GroupLayout(panTheme);
        panTheme.setLayout(panThemeLayout);
        panThemeLayout.setHorizontalGroup(
            panThemeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panThemeLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panThemeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(sepTheme)
                    .addGroup(panThemeLayout.createSequentialGroup()
                        .addComponent(radCustom)
                        .addGap(22, 22, 22)
                        .addComponent(txtCustomIcon)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCustomIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panThemeLayout.createSequentialGroup()
                        .addGroup(panThemeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblIcon)
                            .addGroup(panThemeLayout.createSequentialGroup()
                                .addComponent(radPreset)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cboIcon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lblLauncherName))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panThemeLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnApply))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panThemeLayout.createSequentialGroup()
                        .addComponent(txtLauncherName, javax.swing.GroupLayout.DEFAULT_SIZE, 211, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(chkShowVersion)))
                .addContainerGap())
        );
        panThemeLayout.setVerticalGroup(
            panThemeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panThemeLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblIcon)
                .addGap(10, 10, 10)
                .addGroup(panThemeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(radPreset)
                    .addComponent(cboIcon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panThemeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(radCustom)
                    .addComponent(txtCustomIcon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCustomIcon))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(sepTheme, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblLauncherName)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panThemeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtLauncherName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chkShowVersion))
                .addGap(18, 18, 18)
                .addComponent(btnApply)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout tabLauncherLayout = new javax.swing.GroupLayout(tabLauncher);
        tabLauncher.setLayout(tabLauncherLayout);
        tabLauncherLayout.setHorizontalGroup(
            tabLauncherLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabLauncherLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panTheme, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        tabLauncherLayout.setVerticalGroup(
            tabLauncherLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabLauncherLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panTheme, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(12, Short.MAX_VALUE))
        );

        tabpanelMain.addTab("Launcher", tabLauncher);

        btnStart.setMnemonic('r');
        btnStart.setText("Launch");
        btnStart.setToolTipText("");
        btnStart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStartActionPerformed(evt);
            }
        });

        mnuFile.setText("File");

        itmNew.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));
        itmNew.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/new.png"))); // NOI18N
        itmNew.setText("New");
        itmNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itmNewActionPerformed(evt);
            }
        });
        mnuFile.add(itmNew);

        itmOpen.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
        itmOpen.setText("Open...");
        itmOpen.setEnabled(false);
        mnuFile.add(itmOpen);

        itmSave.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        itmSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/save.png"))); // NOI18N
        itmSave.setText("Save");
        itmSave.setEnabled(false);
        mnuFile.add(itmSave);

        itmSaveAs.setText("Save As...");
        itmSaveAs.setEnabled(false);
        mnuFile.add(itmSaveAs);
        mnuFile.add(sepFile);

        itmOpenFolder.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.ALT_MASK));
        itmOpenFolder.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/openfolder.png"))); // NOI18N
        itmOpenFolder.setText("Open SRB2 Folder...");
        itmOpenFolder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itmOpenFolderActionPerformed(evt);
            }
        });
        mnuFile.add(itmOpenFolder);

        tbrMain.add(mnuFile);

        mnuEdit.setText("Help");

        itmAbout.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F1, 0));
        itmAbout.setText("About");
        itmAbout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itmAboutActionPerformed(evt);
            }
        });
        mnuEdit.add(itmAbout);

        tbrMain.add(mnuEdit);

        setJMenuBar(tbrMain);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnStart)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(tabpanelMain, javax.swing.GroupLayout.PREFERRED_SIZE, 403, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(sepBottom, javax.swing.GroupLayout.PREFERRED_SIZE, 403, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tabpanelMain, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sepBottom, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnStart)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnStartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStartActionPerformed
        // Start by 
        String exec = txtExecutable.getText();
        String path = System.getProperty("user.dir") +"\\"+ exec;
        String args = " "+txtParameters.getText();
        String name = " +name " + txtName.getText();
        String skin = " +skin \"" + comSkin.getSelectedItem().toString() + "\"";
        
        // Transform colors with spaces into underscores
        String color = comColor.getSelectedItem().toString();
        switch (color) {
            case "Steel Blue": color = " +color steel_blue"; break;
            case "Dark Red": color = " +color dark_red"; break;
            case "Neon Green": color = " +color neon_green"; break;
            // Obtain the name from the combobox for every other color
            default: color = " +color " + comColor.getSelectedItem().toString(); break;
        }
        
        // Determine whether digital/midi audio or sound effects should be used
        String dig;
        String mid;
        String sfx;
        if (chkDigital.isSelected()) dig = ""; else dig = " -nodigmusic";
        if (chkMIDI.isSelected()) mid = ""; else mid = " -nomidimusic";
        if (chkSFX.isSelected()) sfx = ""; else sfx = " -nosound";

        // Determine whether OpenGL should be used
        String ogl;
        if (radOpenGL.isSelected()) ogl = " -opengl"; else ogl = "";
        
        // Combine all of these parameters into one big mess
        String finalargs = 
                path + args + 
                name + skin + color + 
                dig + mid + sfx + 
                ogl; 
                
        // Launch the damn thing
        try {
            System.out.println("[DEBUG] Arguments passed: "+finalargs);
            Process p = Runtime.getRuntime().exec(finalargs);
        } 
        catch (IOException ex) { // Show an error in case the exe isn't found
            Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, "Couldn't find executable", ex);
            // Show a special error message if there's nothing in the Executable field
            if (exec.equals("")) {
                JOptionPane.showMessageDialog(null,
                "The Executable field cannot be empty.",
                "Invalid path",
                JOptionPane.ERROR_MESSAGE);
            }   
            // If wasn't that, show that whatever they input wasn't found
            else {
                JOptionPane.showMessageDialog(null,
                exec+" was not found in the launcher's directory.",
                "File not found",
                JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnStartActionPerformed

    private void btnParametersHelpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnParametersHelpActionPerformed
        // Button that opens the wiki for command-line parameters
        if (Desktop.isDesktopSupported()) {
            try { 
                Desktop.getDesktop().browse(new URI("https://wiki.srb2.org/wiki/Command_line_parameters"));
            } 
            catch (URISyntaxException | IOException ex) { // This shouldn't EVER happen
                Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, "Somehow, the Help button fucked up", ex);
            }
        }
    }//GEN-LAST:event_btnParametersHelpActionPerformed

    private void itmAboutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itmAboutActionPerformed
        // About dialog box
        Icon AboutIcon = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Resources/ico_sonic.png")));
        JOptionPane.showMessageDialog(null,
        "Ultimate SRB2 Launcher "+versionNumber+"\n"
        +"Copyright © Rex \"ThatAwesomeGuy173\" James, 2017.\n"
        +"All other assets are property of Sonic Team Jr.\n"
        +"https://www.srb2.org/",
        "About",
        JOptionPane.INFORMATION_MESSAGE,
        AboutIcon);
    }//GEN-LAST:event_itmAboutActionPerformed

    private void btnExecutableSelectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExecutableSelectActionPerformed
        // Choose Your File
        JFileChooser fc = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("EXE files (*.exe)", "exe"); // Allow only exe files
        
        fc.setFileFilter((javax.swing.filechooser.FileFilter) filter); // Set the filter
        fc.setCurrentDirectory(new java.io.File(System.getProperty("user.dir"))); // Open this launcher's current directory
        fc.setDialogTitle("Select an executable");
        
        if (fc.showOpenDialog(btnExecutableSelect) == JFileChooser.APPROVE_OPTION){
            txtExecutable.setText(fc.getSelectedFile().getName()); // Set the executable's name ONLY if the user presses OK
        }
    }//GEN-LAST:event_btnExecutableSelectActionPerformed

    private void itmOpenFolderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itmOpenFolderActionPerformed
        // Opens the SRB2 directory
        try {
            Runtime.getRuntime().exec("explorer \"" + System.getProperty("user.dir") + "\"");
        } 
        catch (IOException ex) {
            Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, "Somehow, the main folder couldn't be opened?", ex);
        }
    }//GEN-LAST:event_itmOpenFolderActionPerformed

    private void itmNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itmNewActionPerformed
        // Reset everything to default
        int reply = JOptionPane.showConfirmDialog(null, 
            "This will reset all fields to their defaults!\n"
            +"Are you sure you want to continue?\n",
            "",
            JOptionPane.YES_NO_OPTION);
        if (reply == JOptionPane.YES_OPTION){
            txtExecutable.setText("srb2win.exe");
            txtParameters.setText("");
            txtName.setText("Sonic");
            comColor.setSelectedItem("Blue");
            comSkin.setSelectedItem("Sonic");
            radSoftware.setSelected(true);
            chkDigital.setSelected(true);
            chkMIDI.setSelected(true);
            chkSFX.setSelected(true);
        }
    }//GEN-LAST:event_itmNewActionPerformed

    private void btnApplyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnApplyActionPerformed
        /* CUSTOM ICON */        
        // Create a variable that will change depending on what was selected        
        String programIcon = null;

        // Check whether Preset or Use Custom is selected, and act accordingly
        if (radPreset.isSelected()) { 
            programIcon = cboIcon.getSelectedItem().toString();
            switch (programIcon) {
            case "Sonic": programIcon = "/Resources/ico_sonic.png"; break;
            case "Tails": programIcon = "/Resources/ico_tails.png"; break;
            case "Knuckles": programIcon = "/Resources/ico_knuckles.png"; break;
            default: programIcon = "/Resources/ico_sonic.png"; break;
            }
            setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource(programIcon)));
        }
        /*
        else if (radCustom.isSelected()) {
            programIcon = "\""+txtCustomIcon.getText()+"\""; 
            programIcon = programIcon.replace("\\", "/");
            BufferedImage img;
        
            try {
                System.out.println("[DEBUG] Arguments passed: "+programIcon);
                img = ImageIO.read(new File(programIcon));
                this.setIconImage(img);
            } 
            catch (IOException e) {}
        }
        */

        /* CUSTOM TITLE */
        String launcherName = txtLauncherName.getText();
        
        // Determine if version number should be shown 
        if (chkShowVersion.isSelected()) { setTitle(launcherName +" "+ versionNumber); }
        else { setTitle(launcherName); }
    }//GEN-LAST:event_btnApplyActionPerformed

    private void radPresetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radPresetActionPerformed
        // Disables the custom icon textbox and button in the only feasible way I can think of
        cboIcon.setEnabled(true);
        txtCustomIcon.setEnabled(false);
        btnCustomIcon.setEnabled(false);
    }//GEN-LAST:event_radPresetActionPerformed

    private void radCustomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radCustomActionPerformed
        // ...and vice-versa
        cboIcon.setEnabled(false);
        txtCustomIcon.setEnabled(true);
        btnCustomIcon.setEnabled(true);
    }//GEN-LAST:event_radCustomActionPerformed

    private void btnCustomIconActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCustomIconActionPerformed
        JFileChooser fc = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
        "All supported types (*.jpg, *.jpeg, *.png)", "jpg", "jpeg", "png");
        
        // Set the filter, use this launcher's directory and set the title
        fc.setFileFilter((javax.swing.filechooser.FileFilter) filter);
        fc.setCurrentDirectory(new java.io.File(System.getProperty("user.dir")));
        fc.setDialogTitle("Select an image");
        
        // Set the icon image ONLY if the user presses OK
        if (fc.showOpenDialog(btnCustomIcon) == JFileChooser.APPROVE_OPTION){
            txtCustomIcon.setText(fc.getSelectedFile().getAbsolutePath()); 
        }
    }//GEN-LAST:event_btnCustomIconActionPerformed

    public static void main(String args[]) {
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        try {
            UIManager.setLookAndFeel(
            UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        java.awt.EventQueue.invokeLater(() -> {
            new MainWindow().setVisible(true);
        });
        
    }
    // Text field limiter (used for the Name text box)
    // https://stackoverflow.com/a/24473097
    public class LimitDocumentFilter extends DocumentFilter {

        private int limit;

        public LimitDocumentFilter(int limit) {
            if (limit <= 0) {
                throw new IllegalArgumentException("Limit can not be <= 0");
            }
            this.limit = limit;
        }

        @Override
        public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
            int currentLength = fb.getDocument().getLength();
            int overLimit = (currentLength + text.length()) - limit - length;
            if (overLimit > 0) {
                text = text.substring(0, text.length() - overLimit);
            }
            if (text.length() > 0) {
                super.replace(fb, offset, length, text, attrs); 
            }
        }
                

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnApply;
    private javax.swing.JButton btnCustomIcon;
    private javax.swing.JButton btnExecutableSelect;
    private javax.swing.JButton btnParametersHelp;
    private javax.swing.JButton btnStart;
    private javax.swing.JComboBox<String> cboIcon;
    private javax.swing.JCheckBox chkDigital;
    private javax.swing.JCheckBox chkMIDI;
    private javax.swing.JCheckBox chkSFX;
    private javax.swing.JCheckBox chkShowVersion;
    private javax.swing.JComboBox<String> comColor;
    private javax.swing.JComboBox<String> comSkin;
    private javax.swing.ButtonGroup grpIcon;
    private javax.swing.ButtonGroup grpRenderer;
    private javax.swing.JMenuItem itmAbout;
    private javax.swing.JMenuItem itmNew;
    private javax.swing.JMenuItem itmOpen;
    private javax.swing.JMenuItem itmOpenFolder;
    private javax.swing.JMenuItem itmSave;
    private javax.swing.JMenuItem itmSaveAs;
    private javax.swing.JLabel lblColor;
    private javax.swing.JLabel lblCommandline;
    private javax.swing.JLabel lblExecutable;
    private javax.swing.JLabel lblIcon;
    private javax.swing.JLabel lblLauncherName;
    private javax.swing.JLabel lblName;
    private javax.swing.JLabel lblSkin;
    private javax.swing.JMenu mnuEdit;
    private javax.swing.JMenu mnuFile;
    private javax.swing.JPanel panMisc;
    private javax.swing.JPanel panPlayer;
    private javax.swing.JPanel panRenderer;
    private javax.swing.JPanel panSound;
    private javax.swing.JPanel panTheme;
    private javax.swing.JRadioButton radCustom;
    private javax.swing.JRadioButton radOpenGL;
    private javax.swing.JRadioButton radPreset;
    private javax.swing.JRadioButton radSoftware;
    private javax.swing.JSeparator sepBottom;
    private javax.swing.JPopupMenu.Separator sepFile;
    private javax.swing.JSeparator sepSound;
    private javax.swing.JSeparator sepTheme;
    private javax.swing.JPanel tabLauncher;
    private javax.swing.JPanel tabMain;
    private javax.swing.JTabbedPane tabpanelMain;
    private javax.swing.JMenuBar tbrMain;
    private javax.swing.JTextField txtCustomIcon;
    private javax.swing.JTextField txtExecutable;
    private javax.swing.JTextField txtLauncherName;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtParameters;
    // End of variables declaration//GEN-END:variables
}