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
    String versionNumber = "v1.0"; // version number! change this every release, kthx
    
    public MainWindow() {
        initComponents();
        
        // Program icon
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Resources/icon.png")));
        
        // Set the Renderer options into a group
        ButtonGroup Renderer = new ButtonGroup();
        Renderer.add(radSoftware);
        Renderer.add(radOpenGL);
        radSoftware.setSelected(true);
        
        // Load from srb2javalauncher.cfg
        Properties prop = new Properties();
    	InputStream input = null;
        
    	try {
            // Load the config
            input = new FileInputStream("srb2javalauncher.cfg");
            prop.load(input);

            // Grab all the saved properties here
            txtExecutable.setText(prop.getProperty("executable"));
            txtCommandline.setText(prop.getProperty("parameters"));
	} 
        catch (IOException e) {} 
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
                
                // TODO: Find a way to save the Renderer buttons here
                prop.setProperty("executable", txtExecutable.getText());
                prop.setProperty("parameters", txtCommandline.getText());
                prop.store(output, null);
            }
            catch (IOException io) {}
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

        lblCommandline = new javax.swing.JLabel();
        lblExecutable = new javax.swing.JLabel();
        txtCommandline = new javax.swing.JTextField();
        txtExecutable = new javax.swing.JTextField();
        btnCommandlineHelp = new javax.swing.JButton();
        btnStart = new javax.swing.JButton();
        sepBottom = new javax.swing.JSeparator();
        panRenderer = new javax.swing.JPanel();
        lblRenderer = new javax.swing.JLabel();
        radOpenGL = new javax.swing.JRadioButton();
        radSoftware = new javax.swing.JRadioButton();
        btnExecutable = new javax.swing.JButton();
        tbrMain = new javax.swing.JMenuBar();
        mnuFile = new javax.swing.JMenu();
        itmNew = new javax.swing.JMenuItem();
        mnuEdit = new javax.swing.JMenu();
        itmAbout = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Ultimate SRB2 Launcher "+ versionNumber);
        setResizable(false);

        lblCommandline.setText("Command-line parameters");

        lblExecutable.setText("Executable");

        txtExecutable.setText("srb2win.exe");

        btnCommandlineHelp.setText("?");
        btnCommandlineHelp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCommandlineHelpActionPerformed(evt);
            }
        });

        btnStart.setMnemonic('r');
        btnStart.setText("Launch");
        btnStart.setToolTipText("");
        btnStart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStartActionPerformed(evt);
            }
        });

        panRenderer.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblRenderer.setText("Renderer");

        radOpenGL.setText("OpenGL");

        radSoftware.setText("Software");

        javax.swing.GroupLayout panRendererLayout = new javax.swing.GroupLayout(panRenderer);
        panRenderer.setLayout(panRendererLayout);
        panRendererLayout.setHorizontalGroup(
            panRendererLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panRendererLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblRenderer)
                .addGap(30, 30, 30))
            .addGroup(panRendererLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panRendererLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(radSoftware)
                    .addComponent(radOpenGL))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panRendererLayout.setVerticalGroup(
            panRendererLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panRendererLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblRenderer)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(radSoftware)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(radOpenGL)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnExecutable.setText("...");
        btnExecutable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExecutableActionPerformed(evt);
            }
        });

        mnuFile.setText("File");

        itmNew.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));
        itmNew.setText("New...");
        itmNew.setEnabled(false);
        mnuFile.add(itmNew);

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
                    .addComponent(sepBottom, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblExecutable)
                            .addComponent(lblCommandline)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtCommandline, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnCommandlineHelp))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtExecutable, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnExecutable, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                        .addComponent(panRenderer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnStart)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblCommandline)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtCommandline, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnCommandlineHelp))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblExecutable)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtExecutable, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnExecutable))
                        .addGap(12, 12, 12))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(panRenderer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addComponent(sepBottom, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(btnStart)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnStartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStartActionPerformed
        // Launch the damn thing
        try {
            if (radSoftware.isSelected()) {
                Process p = new ProcessBuilder(System.getProperty("user.dir") +"\\"+ txtExecutable.getText(), txtCommandline.getText()).start();
            }
            else if (radOpenGL.isSelected()) {
                Process p = new ProcessBuilder(System.getProperty("user.dir") +"\\"+ txtExecutable.getText(), txtCommandline.getText(), "-opengl").start();
            }
        } 
        catch (IOException ex) { // Show an error in case the exe isn't found
            Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, "Couldn't find executable", ex);
            // Show a special error message if there's nothing in the Executable field
            if (txtExecutable.getText().equals("")) {
                JOptionPane.showMessageDialog(null,
                "The Executable field cannot be empty.",
                "Error",
                JOptionPane.ERROR_MESSAGE);
            }   
            // If wasn't that, show that whatever they input wasn't found
            else {
                JOptionPane.showMessageDialog(null,
                ""+txtExecutable.getText() +" was not found in this launcher's directory.",
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
    private javax.swing.JMenuItem itmAbout;
    private javax.swing.JMenuItem itmNew;
    private javax.swing.JLabel lblCommandline;
    private javax.swing.JLabel lblExecutable;
    private javax.swing.JLabel lblRenderer;
    private javax.swing.JMenu mnuEdit;
    private javax.swing.JMenu mnuFile;
    private javax.swing.JPanel panRenderer;
    private javax.swing.JRadioButton radOpenGL;
    private javax.swing.JRadioButton radSoftware;
    private javax.swing.JSeparator sepBottom;
    private javax.swing.JMenuBar tbrMain;
    private javax.swing.JTextField txtCommandline;
    private javax.swing.JTextField txtExecutable;
    // End of variables declaration//GEN-END:variables
}