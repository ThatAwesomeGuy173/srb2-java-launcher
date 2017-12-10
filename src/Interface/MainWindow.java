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
    // Variables accessed throughout the whole program
    String versionNumber = "v1.2"; // version number! change this every release, kthx
    DefaultListModel fileModel; // filelist, useful for listing files
    
    // I/O variables
    Properties prop = new Properties();
    InputStream input = null;
    OutputStream output = null;
    
    public MainWindow() {
        initComponents();
        
        // Set the model for the file list
        fileModel = new DefaultListModel();
        listFiles.setModel(fileModel);
        
        /* SAVE/LOAD FUNTIONALITY */
        // Load the config file on launch
        try {
            // Read from ultimatesrb2launcher.cfg
            input = new FileInputStream("ultimatesrb2launcher.cfg");
            prop.load(input);

            // Set all values from the saved properties
            /* MISCELLANEOUS */
            txtExecutable.setText(prop.getProperty("misc.executable"));
            txtParameters.setText(prop.getProperty("misc.parameters"));
            
            /* PLAYER */
            txtName.setText(prop.getProperty("player.name"));
            comColor.setSelectedItem(prop.getProperty("player.color"));
            comSkin.setSelectedItem(prop.getProperty("player.skin"));
            
            /* VIDEO */
            if (prop.getProperty("video.renderer").equals("OpenGL")) radOpenGL.setSelected(true); else radSoftware.setSelected(true);
            
            /* SOUND */
            if (prop.getProperty("sound.digital").equals("false")) chkDigital.setSelected(false); else chkDigital.setSelected(true);
            if (prop.getProperty("sound.midi").equals("false")) chkMIDI.setSelected(false); else chkMIDI.setSelected(true);
            if (prop.getProperty("sound.sfx").equals("false")) chkSFX.setSelected(false); else chkSFX.setSelected(true);
            
            /* LAUNCHER */
            txtLauncherName.setText(prop.getProperty("launcher.name"));
            comIcon.setSelectedItem(prop.getProperty("launcher.icon"));
            txtCustomIcon.setText(prop.getProperty("launcher.customicon"));
            if (prop.getProperty("launcher.iconselect").equals("Custom")) radCustom.setSelected(true); else radPreset.setSelected(true);
            if (prop.getProperty("launcher.version").equals("false")) chkShowVersion.setSelected(false); else chkShowVersion.setSelected(true);
            
            /* WINDOW - THEME SETTINGS ARE APPLIED ON LAUNCH */
            /* NAME */
            String launcherName = prop.getProperty("launcher.name");
            if (prop.getProperty("launcher.version").equals("true")) { setTitle(launcherName +" "+ versionNumber); }
            else { setTitle(launcherName); }
            
            /* ICON */
            String programIcon;
            
            // Custom icon
            if (prop.getProperty("launcher.iconselect").equals("Custom")) {
                // Enable Custom icon elements
                comIcon.setEnabled(false);
                txtCustomIcon.setEnabled(true);
                btnCustomIcon.setEnabled(true);
                
                // Set the custom icon
                try {
                    programIcon = prop.getProperty("launcher.customicon"); 
                    BufferedImage img;

                    img = ImageIO.read(new File(programIcon));
                    this.setIconImage(img);
                } 
                catch (IOException e) {
                    // Ignore the empty textbox
                    if (txtCustomIcon.getText().equals("")) { /* do nothing */ }
                    else { 
                        JOptionPane.showMessageDialog(null,
                        "Unable to load the custom icon image!\n"
                        +"Was the file moved, deleted or renamed?",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
            
            // Preset icon
            else {
                // Enable the Preset icon elements
                comIcon.setEnabled(true);
                txtCustomIcon.setEnabled(false);
                btnCustomIcon.setEnabled(false);
                
                // Set the Preset icon
                programIcon = prop.getProperty("launcher.icon");
                
                switch (programIcon) {
                case "Sonic": programIcon = "/Resources/ico_sonic.png"; break;
                case "Tails": programIcon = "/Resources/ico_tails.png"; break;
                case "Knuckles": programIcon = "/Resources/ico_knuckles.png"; break;
                default: programIcon = "/Resources/ico_sonic.png"; break;
                }
                
                setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource(programIcon)));
            }   
	} 
        catch (IOException | NullPointerException ex) {
            Logger.getLogger(this.getName()).log(Level.WARNING, "Some properties were missing, resetting to defaults", ex);
        } 
        finally {
            if (input != null) {
                try {
                    input.close();
		} 
                catch (IOException ex) {}
            }
            // Generate the config if it wasn't found (usually this happens at first launch)
            else if (input == null) {
                try {
                    // Output everything to ultimatesrb2launcher.cfg
                    output = new FileOutputStream("ultimatesrb2launcher.cfg");
                    
                    // Save all of these properties as their default values
                    prop.setProperty("misc.executable", "srb2win.exe");
                    prop.setProperty("misc.parameters", "");
                    prop.setProperty("player.name", "Sonic");
                    prop.setProperty("player.color", "Blue");
                    prop.setProperty("player.skin", "Sonic");
                    prop.setProperty("video.renderer", "Software");
                    prop.setProperty("sound.digital", "true");
                    prop.setProperty("sound.midi", "true");
                    prop.setProperty("sound.sfx", "true");
                    prop.setProperty("launcher.name", "Ultimate SRB2 Launcher");
                    prop.setProperty("launcher.version", "true");
                    prop.setProperty("launcher.icon", "Sonic");
                    prop.setProperty("launcher.customicon", "");
                    prop.setProperty("launcher.iconselect", "Preset");
                    
                    // Set the icon too because it'd show the default Java one otherwise
                    setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Resources/ico_sonic.png")));
                    
                    // Save the properties then close the output
                    prop.store(output, null);
                    output.close();
                } 
                catch (FileNotFoundException ex) { } 
                catch (IOException ex) { }
            }
	}
        
        // Save everything on exit
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try {
                // Output everything to ultimatesrb2launcher.cfg
                output = new FileOutputStream("ultimatesrb2launcher.cfg");
                
                /* MISCELLANEOUS */
                prop.setProperty("misc.executable", txtExecutable.getText());
                prop.setProperty("misc.parameters", txtParameters.getText());
                
                /* PLAYER */
                prop.setProperty("player.name", txtName.getText());
                prop.setProperty("player.color", comColor.getSelectedItem().toString());
                prop.setProperty("player.skin", comSkin.getSelectedItem().toString());
                
                /* VIDEO */
                if (radOpenGL.isSelected()) prop.setProperty("video.renderer", "OpenGL"); else prop.setProperty("video.renderer", "Software");
                
                /* SOUND */
                if (chkDigital.isSelected()) prop.setProperty("sound.digital", "true"); else prop.setProperty("sound.digital", "false");
                if (chkMIDI.isSelected()) prop.setProperty("sound.midi", "true"); else prop.setProperty("sound.midi", "false");
                if (chkSFX.isSelected()) prop.setProperty("sound.sfx", "true"); else prop.setProperty("sound.sfx", "false");
                
                if (prop.getProperty("launcher.name") == null) prop.setProperty("launcher.name", "Ultimate SRB2 Launcher");
                if (prop.getProperty("launcher.version") == null) prop.setProperty("launcher.version", "true");
                if (prop.getProperty("launcher.icon") == null) prop.setProperty("launcher.icon", "Sonic");
                if (prop.getProperty("launcher.customicon") == null) prop.setProperty("launcher.customicon", "");
                if (prop.getProperty("launcher.iconselect") == null) prop.setProperty("launcher.iconselect", "Preset");
                
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
        jToggleButton1 = new javax.swing.JToggleButton();
        tabpanelMain = new javax.swing.JTabbedPane();
        tabMain = new javax.swing.JPanel();
        panSound = new javax.swing.JPanel();
        chkDigital = new javax.swing.JCheckBox();
        chkSFX = new javax.swing.JCheckBox();
        chkMIDI = new javax.swing.JCheckBox();
        sepSound = new javax.swing.JSeparator();
        panPlayer = new javax.swing.JPanel();
        txtName = new javax.swing.JTextField();
        ((AbstractDocument)txtName.getDocument()).setDocumentFilter(new LimitDocumentFilter(20));
        lblName = new javax.swing.JLabel();
        lblColor = new javax.swing.JLabel();
        lblSkin = new javax.swing.JLabel();
        comColor = new javax.swing.JComboBox<>();
        comSkin = new javax.swing.JComboBox<>();
        panelImage = new javax.swing.JPanel();
        lblCharacter = new javax.swing.JLabel();
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
        panResolution = new javax.swing.JPanel();
        comResolution = new javax.swing.JComboBox<>();
        chkWindowed = new javax.swing.JCheckBox();
        chkShowNonGreen = new javax.swing.JCheckBox();
        tabFiles = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        listFiles = new javax.swing.JList<>();
        btnAddFile = new javax.swing.JButton();
        btnRemoveFile = new javax.swing.JButton();
        btnClearList = new javax.swing.JButton();
        btnMoveUp = new javax.swing.JButton();
        btnMoveDown = new javax.swing.JButton();
        sepTheme1 = new javax.swing.JSeparator();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        tabLauncher = new javax.swing.JPanel();
        panTheme = new javax.swing.JPanel();
        lblIcon = new javax.swing.JLabel();
        lblLauncherName = new javax.swing.JLabel();
        txtCustomIcon = new javax.swing.JTextField();
        txtLauncherName = new javax.swing.JTextField();
        radPreset = new javax.swing.JRadioButton();
        radCustom = new javax.swing.JRadioButton();
        comIcon = new javax.swing.JComboBox<>();
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

        jToggleButton1.setText("jToggleButton1");

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
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panSoundLayout.setVerticalGroup(
            panSoundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panSoundLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(chkDigital)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chkMIDI, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(sepSound, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chkSFX)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

        panelImage.setBackground(new java.awt.Color(255, 255, 255));

        lblCharacter.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resources/sonic.png"))); // NOI18N

        javax.swing.GroupLayout panelImageLayout = new javax.swing.GroupLayout(panelImage);
        panelImage.setLayout(panelImageLayout);
        panelImageLayout.setHorizontalGroup(
            panelImageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelImageLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblCharacter)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelImageLayout.setVerticalGroup(
            panelImageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelImageLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblCharacter, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout panPlayerLayout = new javax.swing.GroupLayout(panPlayer);
        panPlayer.setLayout(panPlayerLayout);
        panPlayerLayout.setHorizontalGroup(
            panPlayerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panPlayerLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(panelImage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addGroup(panPlayerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblColor, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblName, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panPlayerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panPlayerLayout.createSequentialGroup()
                        .addComponent(comColor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(43, 43, 43)
                        .addComponent(lblSkin)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(comSkin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
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
            .addGroup(panPlayerLayout.createSequentialGroup()
                .addComponent(panelImage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 2, Short.MAX_VALUE))
        );

        panMisc.setBackground(new java.awt.Color(255, 255, 255));
        panMisc.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Misc."));
        panMisc.setName(""); // NOI18N

        lblCommandline.setText("Custom Arguments");

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
                .addGroup(panMiscLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblCommandline)
                    .addComponent(lblExecutable))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panMiscLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtParameters)
                    .addComponent(txtExecutable))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panMiscLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnParametersHelp, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnExecutableSelect, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        panMiscLayout.setVerticalGroup(
            panMiscLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panMiscLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panMiscLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCommandline)
                    .addComponent(txtParameters, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnParametersHelp))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panMiscLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblExecutable)
                    .addComponent(txtExecutable, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnExecutableSelect))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                    .addGroup(panRendererLayout.createSequentialGroup()
                        .addComponent(radOpenGL)
                        .addGap(6, 6, 6))
                    .addComponent(radSoftware, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panRendererLayout.setVerticalGroup(
            panRendererLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panRendererLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(radSoftware, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(radOpenGL)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panResolution.setBackground(new java.awt.Color(255, 255, 255));
        panResolution.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Resolution"));

        comResolution.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1920x1200", "1280x800", "640x400", "320x200" }));
        comResolution.setSelectedIndex(2);

        chkWindowed.setBackground(new java.awt.Color(255, 255, 255));
        chkWindowed.setText("Windowed");

        chkShowNonGreen.setBackground(new java.awt.Color(255, 255, 255));
        chkShowNonGreen.setText("Show non-green resolutions");

        javax.swing.GroupLayout panResolutionLayout = new javax.swing.GroupLayout(panResolution);
        panResolution.setLayout(panResolutionLayout);
        panResolutionLayout.setHorizontalGroup(
            panResolutionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panResolutionLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panResolutionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(chkShowNonGreen)
                    .addComponent(chkWindowed)
                    .addComponent(comResolution, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(28, Short.MAX_VALUE))
        );
        panResolutionLayout.setVerticalGroup(
            panResolutionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panResolutionLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(comResolution, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(chkWindowed)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chkShowNonGreen)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout tabMainLayout = new javax.swing.GroupLayout(tabMain);
        tabMain.setLayout(tabMainLayout);
        tabMainLayout.setHorizontalGroup(
            tabMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabMainLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tabMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panPlayer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panMisc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(tabMainLayout.createSequentialGroup()
                        .addComponent(panSound, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panRenderer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panResolution, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        tabMainLayout.setVerticalGroup(
            tabMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabMainLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panPlayer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(tabMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panSound, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panRenderer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panResolution, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                .addComponent(panMisc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        tabpanelMain.addTab("Main", tabMain);

        tabFiles.setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Files"));

        jScrollPane1.setViewportView(listFiles);

        btnAddFile.setText("Add...");
        btnAddFile.setPreferredSize(new java.awt.Dimension(72, 23));
        btnAddFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddFileActionPerformed(evt);
            }
        });

        btnRemoveFile.setText("Remove");
        btnRemoveFile.setPreferredSize(new java.awt.Dimension(72, 23));
        btnRemoveFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoveFileActionPerformed(evt);
            }
        });

        btnClearList.setText("Clear");
        btnClearList.setPreferredSize(new java.awt.Dimension(72, 23));
        btnClearList.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearListActionPerformed(evt);
            }
        });

        btnMoveUp.setText("↑");
        btnMoveUp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMoveUpActionPerformed(evt);
            }
        });

        btnMoveDown.setText("↓");
        btnMoveDown.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMoveDownActionPerformed(evt);
            }
        });

        jButton1.setText("Import");
        jButton1.setMaximumSize(new java.awt.Dimension(71, 23));
        jButton1.setMinimumSize(new java.awt.Dimension(71, 23));
        jButton1.setPreferredSize(new java.awt.Dimension(71, 23));

        jButton2.setText("Export");
        jButton2.setPreferredSize(new java.awt.Dimension(71, 23));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnMoveUp)
                        .addGap(9, 9, 9)
                        .addComponent(btnMoveDown)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 56, Short.MAX_VALUE)
                        .addComponent(btnClearList, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnRemoveFile, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnAddFile, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(sepTheme1))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 217, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAddFile, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRemoveFile, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnClearList, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnMoveUp)
                    .addComponent(btnMoveDown))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(sepTheme1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout tabFilesLayout = new javax.swing.GroupLayout(tabFiles);
        tabFiles.setLayout(tabFilesLayout);
        tabFilesLayout.setHorizontalGroup(
            tabFilesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabFilesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        tabFilesLayout.setVerticalGroup(
            tabFilesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabFilesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        tabpanelMain.addTab("File List", tabFiles);

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
        radCustom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radCustomActionPerformed(evt);
            }
        });

        comIcon.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Sonic", "Tails", "Knuckles" }));

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

        btnApply.setText("Save & Apply");
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
                                .addComponent(comIcon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lblLauncherName))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panThemeLayout.createSequentialGroup()
                        .addComponent(txtLauncherName, javax.swing.GroupLayout.DEFAULT_SIZE, 234, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(chkShowVersion))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panThemeLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnApply)))
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
                    .addComponent(comIcon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 125, Short.MAX_VALUE)
                .addComponent(btnApply)
                .addContainerGap())
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
                .addComponent(panTheme, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
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
                    .addComponent(sepBottom)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnStart))
                    .addComponent(tabpanelMain, javax.swing.GroupLayout.Alignment.LEADING))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tabpanelMain, javax.swing.GroupLayout.PREFERRED_SIZE, 390, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(sepBottom, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(btnStart)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnStartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStartActionPerformed
        // Get all the arguments and set them to strings
        
        // Executable and its path (these are separate for error dialog purposes)
        String exec = txtExecutable.getText();
        String path = System.getProperty("user.dir") +"\\"+ exec;
        
        String args = " "+txtParameters.getText();
        String file = " -file " + listFiles.getModel().toString()
                      .replaceAll("\\[","").replaceAll("\\]","").replaceAll("[,]","");
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
                path + args + file +
                name + skin + color + 
                dig + mid + sfx + 
                ogl; 
                
        // Launch the damn thing
        try {
            System.out.println("[DEBUG] Arguments passed: "+finalargs);
            Process p = Runtime.getRuntime().exec(finalargs);
        } 
        // Show an error in case the exe isn't found
        catch (IOException ex) { 
            Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, "Couldn't find executable", ex);
            // Show this if there's nothing in the Executable field
            if (exec.equals("")) {
                JOptionPane.showMessageDialog(null,
                "The Executable field cannot be empty.",
                "Invalid path",
                JOptionPane.ERROR_MESSAGE);
            }   
            // Use this error message for everything else
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
        // Executable file chooser
        JFileChooser fc = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
        "EXE files (*.exe)", "exe"); // Allow only exe files
        
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
        // But first, we need to ask if they really want to reset
        int reply = JOptionPane.showConfirmDialog(null, 
            "This will reset all fields to their defaults!\n"
            +"Are you sure you want to continue?\n",
            "",
            JOptionPane.YES_NO_OPTION);
        if (reply == JOptionPane.YES_OPTION) {
            /* MAIN TAB */
            txtExecutable.setText("srb2win.exe");
            txtParameters.setText("");
            txtName.setText("Sonic");
            comColor.setSelectedItem("Blue");
            comSkin.setSelectedItem("Sonic");
            radSoftware.setSelected(true);
            chkDigital.setSelected(true);
            chkMIDI.setSelected(true);
            
            /* FILE LIST TAB */
            fileModel.removeAllElements();
            
            /* LAUNCHER TAB */
            radPreset.setSelected(true);
            txtCustomIcon.setText("");
            txtLauncherName.setText("Ultimate SRB2 Launcher");
            chkShowVersion.setSelected(true);
            comIcon.setSelectedItem("Sonic");
            
            // Reset the window too
            setTitle("Ultimate SRB2 Launcher " + versionNumber);
            setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Resources/ico_sonic.png")));
            
            // Save their properties too while we're at it
            prop.setProperty("launcher.name", "Ultimate SRB2 Launcher");
            prop.setProperty("launcher.icon", "Sonic");
            prop.setProperty("launcher.customicon", "");
            prop.setProperty("launcher.iconselect", "Preset");
            prop.setProperty("launcher.version", "true");
        }
    }//GEN-LAST:event_itmNewActionPerformed

    private void btnApplyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnApplyActionPerformed
        /* CUSTOM ICON */        
        // Create a variable that will change depending on what was selected
        String programIcon;
        
        // Check whether Preset or Use Custom is selected, and act accordingly
        if (radPreset.isSelected()) { 
            programIcon = comIcon.getSelectedItem().toString();
            switch (programIcon) {
            case "Sonic": programIcon = "/Resources/ico_sonic.png"; break;
            case "Tails": programIcon = "/Resources/ico_tails.png"; break;
            case "Knuckles": programIcon = "/Resources/ico_knuckles.png"; break;
            default: programIcon = "/Resources/ico_sonic.png"; break;
            }
            setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource(programIcon)));
        }

        if (radCustom.isSelected()) {
            try {
                programIcon = txtCustomIcon.getText(); 
                BufferedImage img;
                
                // System.out.println("[DEBUG] Arguments passed: "+programIcon);
                img = ImageIO.read(new File(programIcon));
                this.setIconImage(img);
            } 
            catch (IOException e) {
                JOptionPane.showMessageDialog(null,
                "The system cannot find the file specified.",
                "File not found",
                JOptionPane.ERROR_MESSAGE);
            }
        }

        /* CUSTOM TITLE */
        String launcherName = txtLauncherName.getText();
        
        // Determine if version number should be shown 
        if (chkShowVersion.isSelected()) { setTitle(launcherName +" "+ versionNumber); }
        else { setTitle(launcherName); }
                     
        /* SAVE VALUES ON CLICK */
        prop.setProperty("launcher.name", txtLauncherName.getText());
        prop.setProperty("launcher.icon", comIcon.getSelectedItem().toString());
        prop.setProperty("launcher.customicon", txtCustomIcon.getText());
        if (radCustom.isSelected()) prop.setProperty("launcher.iconselect", "Custom"); else prop.setProperty("launcher.iconselect", "Preset");
        if (chkShowVersion.isSelected()) prop.setProperty("launcher.version", "true"); else prop.setProperty("launcher.version", "false");
    }//GEN-LAST:event_btnApplyActionPerformed
	
    private void btnCustomIconActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCustomIconActionPerformed
        // Custom icon file chooser
        JFileChooser fc = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
        "All supported types (*.png, *.jpg, *.gif)", "png", "jpeg", "jpg", "gif");
        
        // Set the filter, use this launcher's directory and set the title
        fc.setFileFilter((javax.swing.filechooser.FileFilter) filter);
        fc.setCurrentDirectory(new java.io.File(System.getProperty("user.dir")));
        fc.setDialogTitle("Select an image");
        
        // Set the path to the image ONLY if the user presses OK
        if (fc.showOpenDialog(btnCustomIcon) == JFileChooser.APPROVE_OPTION){
            txtCustomIcon.setText(fc.getSelectedFile().getAbsolutePath()); 
        }
    }//GEN-LAST:event_btnCustomIconActionPerformed

    private void radPresetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radPresetActionPerformed
        // Disables the custom icon textbox and button in the only feasible way I can think of
        comIcon.setEnabled(true);
        txtCustomIcon.setEnabled(false);
        btnCustomIcon.setEnabled(false);
    }//GEN-LAST:event_radPresetActionPerformed

    private void radCustomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radCustomActionPerformed
        // ...and vice-versa
        comIcon.setEnabled(false);
        txtCustomIcon.setEnabled(true);
        btnCustomIcon.setEnabled(true);
    }//GEN-LAST:event_radCustomActionPerformed

    private void btnAddFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddFileActionPerformed
        // Extra addon file chooser
        JFileChooser fc = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
        "All supported types (*.wad, *.soc, *.lua)", "wad", "soc", "lua");
        
        // Set the filter, use this launcher's directory and set the title
        fc.setFileFilter((javax.swing.filechooser.FileFilter) filter);
        fc.setCurrentDirectory(new java.io.File(System.getProperty("user.dir")));
        fc.setDialogTitle("Select a file");
        
        // Add the file ONLY if the user presses OK
        if (fc.showOpenDialog(btnCustomIcon) == JFileChooser.APPROVE_OPTION){
            fileModel.addElement(fc.getSelectedFile().getName());
        }
    }//GEN-LAST:event_btnAddFileActionPerformed

    private void btnRemoveFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoveFileActionPerformed
        // Delete a selected entry from the file list
        int index = listFiles.getSelectedIndex();
        if (index != -1) {
            fileModel.remove(index);
        }
    }//GEN-LAST:event_btnRemoveFileActionPerformed

    private void btnClearListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearListActionPerformed
        // Remove all file list entires with just one line
        fileModel.removeAllElements();
    }//GEN-LAST:event_btnClearListActionPerformed

    private void btnMoveUpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMoveUpActionPerformed
        // Move a selected entry upways
        String selectedItem = listFiles.getSelectedValue();
        int itemIndex = listFiles.getSelectedIndex();
        
        if(itemIndex > 0){
            fileModel.remove(itemIndex);
            fileModel.add(itemIndex - 1, selectedItem);
            listFiles.setSelectedIndex(itemIndex - 1);
        }
    }//GEN-LAST:event_btnMoveUpActionPerformed

    private void btnMoveDownActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMoveDownActionPerformed
        // Move a selected entry downways
        String selectedItem = listFiles.getSelectedValue();
        int itemIndex = listFiles.getSelectedIndex();
        
        if( itemIndex < fileModel.getSize() -1 ){
            fileModel.remove(itemIndex);
            fileModel.add(itemIndex + 1, selectedItem);
            listFiles.setSelectedIndex(itemIndex + 1);
        }
    }//GEN-LAST:event_btnMoveDownActionPerformed

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
    private javax.swing.JButton btnAddFile;
    private javax.swing.JButton btnApply;
    private javax.swing.JButton btnClearList;
    private javax.swing.JButton btnCustomIcon;
    private javax.swing.JButton btnExecutableSelect;
    private javax.swing.JButton btnMoveDown;
    private javax.swing.JButton btnMoveUp;
    private javax.swing.JButton btnParametersHelp;
    private javax.swing.JButton btnRemoveFile;
    private javax.swing.JButton btnStart;
    private javax.swing.JCheckBox chkDigital;
    private javax.swing.JCheckBox chkMIDI;
    private javax.swing.JCheckBox chkSFX;
    private javax.swing.JCheckBox chkShowNonGreen;
    private javax.swing.JCheckBox chkShowVersion;
    private javax.swing.JCheckBox chkWindowed;
    private javax.swing.JComboBox<String> comColor;
    private javax.swing.JComboBox<String> comIcon;
    private javax.swing.JComboBox<String> comResolution;
    private javax.swing.JComboBox<String> comSkin;
    private javax.swing.ButtonGroup grpIcon;
    private javax.swing.ButtonGroup grpRenderer;
    private javax.swing.JMenuItem itmAbout;
    private javax.swing.JMenuItem itmNew;
    private javax.swing.JMenuItem itmOpen;
    private javax.swing.JMenuItem itmOpenFolder;
    private javax.swing.JMenuItem itmSave;
    private javax.swing.JMenuItem itmSaveAs;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JToggleButton jToggleButton1;
    private javax.swing.JLabel lblCharacter;
    private javax.swing.JLabel lblColor;
    private javax.swing.JLabel lblCommandline;
    private javax.swing.JLabel lblExecutable;
    private javax.swing.JLabel lblIcon;
    private javax.swing.JLabel lblLauncherName;
    private javax.swing.JLabel lblName;
    private javax.swing.JLabel lblSkin;
    private javax.swing.JList<String> listFiles;
    private javax.swing.JMenu mnuEdit;
    private javax.swing.JMenu mnuFile;
    private javax.swing.JPanel panMisc;
    private javax.swing.JPanel panPlayer;
    private javax.swing.JPanel panRenderer;
    private javax.swing.JPanel panResolution;
    private javax.swing.JPanel panSound;
    private javax.swing.JPanel panTheme;
    private javax.swing.JPanel panelImage;
    private javax.swing.JRadioButton radCustom;
    private javax.swing.JRadioButton radOpenGL;
    private javax.swing.JRadioButton radPreset;
    private javax.swing.JRadioButton radSoftware;
    private javax.swing.JSeparator sepBottom;
    private javax.swing.JPopupMenu.Separator sepFile;
    private javax.swing.JSeparator sepSound;
    private javax.swing.JSeparator sepTheme;
    private javax.swing.JSeparator sepTheme1;
    private javax.swing.JPanel tabFiles;
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