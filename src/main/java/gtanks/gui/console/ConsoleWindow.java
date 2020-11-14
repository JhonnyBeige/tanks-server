package gtanks.gui.console;

import javax.swing.*;
import javax.swing.text.AttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import java.awt.*;
import java.awt.TrayIcon.MessageType;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class ConsoleWindow extends JFrame {
    public static final String ICON_STR = "/gtanks/gui/console/settings.png";
    private static final long serialVersionUID = 1L;
    public TrayIcon trayIcon;
    public ConsoleCommandHandler handler;
    private JTextPane txtpnServerGtanksNative = new JTextPane();
    private JScrollPane jsp;
    private JTextField textField;
    private JLabel lblNewLabel;
    private JFrame instance;

    public ConsoleWindow() {
        super("Native Console by SCpacker - GTanks server");
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(ConsoleWindow.class.getResource("/gtanks/gui/console/settings.png")));
        this.instance = this;
        this.configurateWindow("Server", true);
        this.addDefaultListeners();

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException | ClassNotFoundException var2) {
            var2.printStackTrace();
        }

        this.configurateTray();
        this.append(Color.green, "===========================================");
        this.append(Color.GREEN, "==GTanks server console by SCpacker v.0.2==");
        this.append(Color.green, "===========================================");
        this.handler = new ConsoleCommandHandler(this);
    }

    private void configurateTray() {
        if (SystemTray.isSupported()) {
            SystemTray tray = SystemTray.getSystemTray();

            try {
                Image icon = Toolkit.getDefaultToolkit().getImage(ConsoleWindow.class.getResource("/gtanks/gui/console/settings.png"));
                this.trayIcon = new TrayIcon(icon, "Console server logger");
                this.trayIcon.setImageAutoSize(true);
                tray.add(this.trayIcon);
                this.trayIcon.displayMessage("Native Console by SCpacker - GTanks server", "Console started!", MessageType.INFO);
            } catch (AWTException var3) {
                var3.printStackTrace();
            }
        }

    }

    private void configurateWindow(String pcName, boolean isRoot) {
        this.getContentPane().setBackground(Color.BLACK);

        try {
            this.getContentPane().setLayout((LayoutManager) null);
        } catch (Exception var4) {
        }

        this.txtpnServerGtanksNative.setFont(new Font("DejaVu Sans Mono", 1, 14));
        this.txtpnServerGtanksNative.setBackground(Color.BLACK);
        this.txtpnServerGtanksNative.setForeground(Color.WHITE);
        this.txtpnServerGtanksNative.setEditable(false);
        this.txtpnServerGtanksNative.setBounds(0, 0, 811, 523);
        this.jsp = new JScrollPane(this.txtpnServerGtanksNative);
        this.jsp.setBounds(0, 0, 918, 625);
        this.getContentPane().add(this.jsp);
        this.lblNewLabel = new JLabel("$" + pcName + (isRoot ? "@root" : "") + " >");
        this.lblNewLabel.setFont(new Font("SimSun", 1, 13));
        this.lblNewLabel.setForeground(Color.WHITE);
        this.lblNewLabel.setBounds(10, 636, 138, 14);
        this.getContentPane().add(this.lblNewLabel);
        this.textField = new JTextField();
        this.textField.setFont(new Font("Tahoma", 1, 13));
        this.textField.setForeground(Color.WHITE);
        this.textField.setBackground(Color.BLACK);
        this.textField.setBounds(130, 633, 777, 20);
        this.textField.setColumns(10);
        this.getContentPane().add(this.textField);
        this.setSize(936, 693);
        this.setDefaultCloseOperation(3);
        this.setLocationRelativeTo((Component) null);
    }

    private void addDefaultListeners() {
        this.addComponentListener(new ComponentListener() {
            @Override
            public void componentResized(ComponentEvent e) {
                ConsoleWindow.this.jsp.setBounds(ConsoleWindow.this.jsp.getX(), ConsoleWindow.this.jsp.getY(), Math.max(930, ConsoleWindow.this.instance.getWidth() - 17), Math.max(630, ConsoleWindow.this.instance.getHeight() - 65));
                ConsoleWindow.this.textField.setBounds(130, Math.max(633, ConsoleWindow.this.instance.getHeight() - 60), Math.max(777, ConsoleWindow.this.instance.getWidth() - ConsoleWindow.this.lblNewLabel.getWidth() - 10), 20);
                ConsoleWindow.this.lblNewLabel.setBounds(10, ConsoleWindow.this.textField.getY(), 138, 14);
            }

            @Override
            public void componentMoved(ComponentEvent arg0) {
                ConsoleWindow.this.jsp.setBounds(ConsoleWindow.this.jsp.getX(), ConsoleWindow.this.jsp.getY(), Math.max(930, ConsoleWindow.this.instance.getWidth() - 17), Math.max(630, ConsoleWindow.this.instance.getHeight() - 65));
                ConsoleWindow.this.textField.setBounds(130, Math.max(633, ConsoleWindow.this.instance.getHeight() - 60), Math.max(630, ConsoleWindow.this.instance.getWidth() - ConsoleWindow.this.lblNewLabel.getWidth() - 10), 20);
                ConsoleWindow.this.lblNewLabel.setBounds(10, ConsoleWindow.this.textField.getY(), 138, 14);
            }

            @Override
            public void componentShown(ComponentEvent arg0) {
            }

            @Override
            public void componentHidden(ComponentEvent arg0) {
            }
        });
        this.textField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent key) {
                if (key.getKeyCode() == 10) {
                    ConsoleWindow.this.handler.onEnterCommand(ConsoleWindow.this.textField.getText());
                    ConsoleWindow.this.textField.setText("");
                }

            }
        });
    }

    public void append(Color c, String s) {
        if (c == Color.red || c == Color.RED) {
            this.trayIcon.displayMessage("Fatal error!", "Server print error log! Look it!", MessageType.ERROR);
        }

        this.txtpnServerGtanksNative.setEditable(true);
        s = s + "\n";
        StyleContext sc = StyleContext.getDefaultStyleContext();
        AttributeSet aset = sc.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, c);
        int len = this.txtpnServerGtanksNative.getDocument().getLength();
        this.txtpnServerGtanksNative.setCaretPosition(len);
        this.txtpnServerGtanksNative.setCharacterAttributes(aset, false);
        this.txtpnServerGtanksNative.replaceSelection(s);
        this.txtpnServerGtanksNative.setEditable(false);
    }
}
