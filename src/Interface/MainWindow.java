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
import java.net.URI;
import java.net.URISyntaxException;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class MainWindow extends javax.swing.JFrame {
    String versionNumber = "v1.1 dev"; // version number! change this every release, kthx
    
    public MainWindow() {
        initComponents();
        
        // Program icon
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Resources/icon.png")));
        
        // Save & load functionality
        Properties prop = new Properties();
    	InputStream input = null;
        
        try {
            // Load the config
            input = new FileInputStream("srb2javalauncher.cfg");
            prop.load(input);
            
            // Set the defaults for the first-time run
            // Also useful when upgrading from a previous version, else this won't open
            if (prop.getProperty("misc.executable") == null) prop.setProperty("executable", "srb2win.exe");
            if (prop.getProperty("misc.parameters") == null) prop.setProperty("parameters", "");
            if (prop.getProperty("player.name") == null) prop.setProperty("name", "Sonic");
            if (prop.getProperty("player.color") == null) prop.setProperty("color", "Blue");
            if (prop.getProperty("player.skin") == null) prop.setProperty("skin", "Sonic");
            if (prop.getProperty("video.renderer") == null) prop.setProperty("renderer", "Software");
            if (prop.getProperty("music.digital") == null) prop.setProperty("digital", "true");
            if (prop.getProperty("music.midi") == null) prop.setProperty("midi", "true");
            if (prop.getProperty("music.sfx") == null) prop.setProperty("sfx", "true");

            // Grab all the saved properties here
            txtExecutable.setText(prop.getProperty("misc.executable"));
            txtParameters.setText(prop.getProperty("misc.parameters"));
            txtName.setText(prop.getProperty("player.name"));
            comColor.setSelectedItem(prop.getProperty("player.color"));
            comSkin.setSelectedItem(prop.getProperty("player.skin"));
            if (prop.getProperty("video.renderer").equals("OpenGL")) radOpenGL.setSelected(true); else radSoftware.setSelected(true);
            if (prop.getProperty("music.digital").equals("false")) chkDigital.setSelected(false); else chkDigital.setSelected(true);
            if (prop.getProperty("music.midi").equals("false")) chkMIDI.setSelected(false); else chkMIDI.setSelected(true);
            if (prop.getProperty("music.sfx").equals("false")) chkSFX.setSelected(false); else chkSFX.setSelected(true);
                       
	} 
        catch (IOException | NullPointerException e) {} 
        finally {
            if (input != null) {
                try {
                    input.close();
		} 
                catch (IOException e) {}
            }
	}
        
        // Save everything on exit
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            OutputStream output = null;
            try {
                // Output everything to srb2javalauncher.cfg
                output = new FileOutputStream("srb2javalauncher.cfg");
                
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
            catch (IOException io) {
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
                    catch (IOException e) {}
                }
            }
        })); 
        
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        grpRenderer = new javax.swing.ButtonGroup();
        panPlayer = new javax.swing.JPanel();
        txtName = new javax.swing.JTextField();
        lblName = new javax.swing.JLabel();
        lblColor = new javax.swing.JLabel();
        lblSkin = new javax.swing.JLabel();
        comColor = new javax.swing.JComboBox<>();
        comSkin = new javax.swing.JComboBox<>();
        panRenderer = new javax.swing.JPanel();
        radSoftware = new javax.swing.JRadioButton();
        radOpenGL = new javax.swing.JRadioButton();
        panSound = new javax.swing.JPanel();
        chkDigital = new javax.swing.JCheckBox();
        chkSFX = new javax.swing.JCheckBox();
        chkMIDI = new javax.swing.JCheckBox();
        jSeparator1 = new javax.swing.JSeparator();
        panMisc = new javax.swing.JPanel();
        lblCommandline = new javax.swing.JLabel();
        lblExecutable = new javax.swing.JLabel();
        txtParameters = new javax.swing.JTextField();
        txtExecutable = new javax.swing.JTextField();
        btnCommandlineHelp = new javax.swing.JButton();
        btnExecutable = new javax.swing.JButton();
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
        setResizable(false);

        panPlayer.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Player"));

        txtName.setText("Sonic");

        lblName.setText("Name");

        lblColor.setText("Color");

        lblSkin.setText("Skin");

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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

        panRenderer.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Renderer"));

        grpRenderer.add(radSoftware);
        radSoftware.setSelected(true);
        radSoftware.setText("Software");

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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(radSoftware, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(radOpenGL)
                .addGap(15, 15, 15))
        );

        panSound.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Sound"));

        chkDigital.setSelected(true);
        chkDigital.setText("Digital");

        chkSFX.setSelected(true);
        chkSFX.setText("SFX");

        chkMIDI.setSelected(true);
        chkMIDI.setText("MIDI");

        javax.swing.GroupLayout panSoundLayout = new javax.swing.GroupLayout(panSound);
        panSound.setLayout(panSoundLayout);
        panSoundLayout.setHorizontalGroup(
            panSoundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panSoundLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panSoundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1)
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
                .addGap(9, 9, 9)
                .addComponent(chkDigital)
                .addGap(2, 2, 2)
                .addComponent(chkMIDI, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(chkSFX)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        panMisc.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Misc."));
        panMisc.setName(""); // NOI18N

        lblCommandline.setText("Command-line parameters");

        lblExecutable.setText("Executable");

        txtExecutable.setText("srb2win.exe");

        btnCommandlineHelp.setText("?");
        btnCommandlineHelp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCommandlineHelpActionPerformed(evt);
            }
        });

        btnExecutable.setText("...");
        btnExecutable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExecutableActionPerformed(evt);
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
                            .addComponent(btnCommandlineHelp)
                            .addComponent(btnExecutable, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))))
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
                    .addComponent(btnCommandlineHelp))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblExecutable)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panMiscLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtExecutable, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnExecutable))
                .addGap(0, 12, Short.MAX_VALUE))
        );

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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnStart))
                    .addComponent(sepBottom)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(panMisc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(panPlayer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(panRenderer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(panSound, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panPlayer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panRenderer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panMisc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panSound, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
        // Declare these so the ProcessBuilder doesn't look too much of a bloody mess
        String exec = txtExecutable.getText();
        String args = txtParameters.getText();
        String name = "+name " + txtName.getText();
        String skin = "+skin \"" + comSkin.getSelectedItem().toString() + "\"";
        
        // Transform colors with spaces into underscores
        String color = comColor.getSelectedItem().toString();
        switch (color) {
            case "Steel Blue": color = "+color steel_blue"; break;
            case "Dark Red": color = "+color dark_red"; break;
            case "Neon Green": color = "+color neon_green"; break;
            // Obtain the name from the combobox for every other color
            default: color = "+color " + comColor.getSelectedItem().toString(); break;
        }
        
        // Determine whether digital/midi audio or sound effects should be used
        String dig;
        String mid;
        String sfx;
        if (chkDigital.isSelected()) dig = ""; else dig = "-nodigmusic";
        if (chkMIDI.isSelected()) mid = ""; else mid = "-nomidimusic";
        if (chkSFX.isSelected()) sfx = ""; else sfx = "-nosound";

        // Determine whether OpenGL should be used
        String ogl;
        if (radOpenGL.isSelected()) ogl = "-opengl"; else ogl = "";
               
        // Launch the damn thing
        try {
            // System.out.println("[DEBUG] Arguments passed: "+exec+" "+args+" "+name+" "+color+" "+skin+" "+dig+" "+mid+" "+sfx+" "+ogl); 
            Process p = new ProcessBuilder(System.getProperty("user.dir") +"\\"+ exec, args, name, color, skin, dig, mid, sfx, ogl).start();
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
                ""+exec +" was not found in this launcher's directory.",
                "Error",
                JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnStartActionPerformed

    private void btnCommandlineHelpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCommandlineHelpActionPerformed
        // Button that opens the wiki for command-line parameters
        if (Desktop.isDesktopSupported()) {
            try { 
                Desktop.getDesktop().browse(new URI("https://wiki.srb2.org/wiki/Command_line_parameters"));
            } 
            catch (URISyntaxException | IOException ex) { // This shouldn't EVER happen
                Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, "Somehow, the Help button fucked up", ex);
            }
        }
    }//GEN-LAST:event_btnCommandlineHelpActionPerformed

    private void itmAboutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itmAboutActionPerformed
        // About dialog box
        Icon AboutIcon = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Resources/icon.png")));
        JOptionPane.showMessageDialog(null,
        "Ultimate SRB2 Launcher "+versionNumber+"\n"
        +"Copyright © Rex \"ThatAwesomeGuy173\" James, 2017.\n"
        +"All other assets are property of Sonic Team Jr.\n"
        +"https://www.srb2.org/",
        "About",
        JOptionPane.INFORMATION_MESSAGE,
        AboutIcon);
    }//GEN-LAST:event_itmAboutActionPerformed

    private void btnExecutableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExecutableActionPerformed
        // Choose Your File
        JFileChooser fc = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("EXE files (*.exe)", "exe"); // Allow only exe files
        
        fc.setFileFilter((javax.swing.filechooser.FileFilter) filter); // Set the filter
        fc.setCurrentDirectory(new java.io.File(System.getProperty("user.dir"))); // Open this launcher's current directory
        fc.setDialogTitle("Select an executable");
        
        if (fc.showOpenDialog(btnExecutable) == JFileChooser.APPROVE_OPTION){
            txtExecutable.setText(fc.getSelectedFile().getName()); // Set the executable's name ONLY if the user presses OK
        }
    }//GEN-LAST:event_btnExecutableActionPerformed

    private void itmOpenFolderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itmOpenFolderActionPerformed
        // Opens the SRB2 directory
        try {
            Runtime.getRuntime().exec("explorer" + System.getProperty("user.dir"));
        } catch (IOException ex) {
            Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, "Somehow, the main folder couldn't be opened?", ex);
        }
    }//GEN-LAST:event_itmOpenFolderActionPerformed

    private void itmNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itmNewActionPerformed
        // Reset everything to default
        txtExecutable.setText("srb2win.exe");
        txtParameters.setText("");
        txtName.setText("Sonic");
        comColor.setSelectedItem("Blue");
        comSkin.setSelectedItem("Sonic");
        radSoftware.setSelected(true);
        chkDigital.setSelected(true);
        chkMIDI.setSelected(true);
        chkSFX.setSelected(true);
    }//GEN-LAST:event_itmNewActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        try {
            UIManager.setLookAndFeel(
            UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainWindow().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCommandlineHelp;
    private javax.swing.JButton btnExecutable;
    private javax.swing.JButton btnStart;
    private javax.swing.JCheckBox chkDigital;
    private javax.swing.JCheckBox chkMIDI;
    private javax.swing.JCheckBox chkSFX;
    private javax.swing.JComboBox<String> comColor;
    private javax.swing.JComboBox<String> comSkin;
    private javax.swing.ButtonGroup grpRenderer;
    private javax.swing.JMenuItem itmAbout;
    private javax.swing.JMenuItem itmNew;
    private javax.swing.JMenuItem itmOpen;
    private javax.swing.JMenuItem itmOpenFolder;
    private javax.swing.JMenuItem itmSave;
    private javax.swing.JMenuItem itmSaveAs;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lblColor;
    private javax.swing.JLabel lblCommandline;
    private javax.swing.JLabel lblExecutable;
    private javax.swing.JLabel lblName;
    private javax.swing.JLabel lblSkin;
    private javax.swing.JMenu mnuEdit;
    private javax.swing.JMenu mnuFile;
    private javax.swing.JPanel panMisc;
    private javax.swing.JPanel panPlayer;
    private javax.swing.JPanel panRenderer;
    private javax.swing.JPanel panSound;
    private javax.swing.JRadioButton radOpenGL;
    private javax.swing.JRadioButton radSoftware;
    private javax.swing.JSeparator sepBottom;
    private javax.swing.JPopupMenu.Separator sepFile;
    private javax.swing.JMenuBar tbrMain;
    private javax.swing.JTextField txtExecutable;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtParameters;
    // End of variables declaration//GEN-END:variables
}