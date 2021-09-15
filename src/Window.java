import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

//Dont use window, i guess, dont use a GUI, i cant figure out how to update text.

public class Window {
    //look at GUI tutorials and start from there. Threads are cool but
    //stay in your ockat. Just make a simple text based game with a clean
    //GUI element like "A Dark Room." Don't do too much after that. Just
    //Make it simple, using what you know.

    //first start with what you know. Create the array, the objects, the game
    //on an array board. Only then when its created look at how to show that on
    //a GUI.
    //private Map map = new Map();
    private JLabel mapText;
    //private String mapStringForm;

    public Window(Map map) {

        JFrame frame = new JFrame();

        JLabel mapText = new JLabel("Map.toHTML(map.toString()))");

        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.setLayout(new GridLayout(0, 1));
        panel.add(mapText); // center this.

        frame.add(panel, BorderLayout.CENTER);
        frame.setSize(700, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        panel.setBackground(new Color(255, 0, 0));

        //panel.setLayout(null);
        //JLabel label = new JLabel();

        frame.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                int keyCode = e.getKeyCode();
                switch (keyCode) {
                    case KeyEvent.VK_UP:
                        frame.invalidate();
                        frame.validate();
                        frame.repaint();
                        setMapText(new JLabel("AAAAAAA"));
                        System.out.println("UP");
                        break;
                    case KeyEvent.VK_DOWN:
                        //
                        System.out.println("DOWN");
                        break;
                    case KeyEvent.VK_RIGHT:
                        //
                        System.out.println("RIGHT");
                        break;
                    case KeyEvent.VK_LEFT:
                        //
                        System.out.println("LEFTY");
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
    }

    public JLabel getMapText() {
        return mapText;
    }

    public void setMapText(JLabel mapText) {
        this.mapText = mapText;
    }

    public static void main(String[] args) {
        System.out.println("⣿⣿⠿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠿⢛⠩⢤⣶⡤⠙⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿\n" +
                "⣿⡇⠺⠏⢿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡀⠀⠻⣿⠟⠀⢚⣿⣿⣿⣿⣿⠿⠛⢛⣛⣛⠻⢿⣿⣿⣿⣿⣿⣿⣿\n" +
                "⣿⣿⣄⣼⣿⣿⣿⡿⢟⣉⠥⢤⣬⣀⣉⣙⠛⠛⠛⢿⣿⣿⣿⣿⣿⣤⣤⣤⣴⣿⣿⣿⣿⣿⠟⡡⠶⡟⠋⣭⠛⡟⠛⠿⣻⣿⣿⣿⣿⣿\n" +
                "⣿⣿⣿⣿⣿⢟⣡⠆⠁⠀⢠⡟⡝⣩⡍⡛⡟⠙⠲⢄⢙⣿⣿⣿⣿⡟⢡⣤⣽⣿⣿⣿⣿⠃⣠⡀⠀⠹⢦⣤⡴⠃⠀⢔⣻⣿⣿⣿⣿⣿\n" +
                "⣿⣿⣿⣿⣷⣯⡉⠁⠀⠀⠘⣧⡁⠉⠅⣡⠇⠀⠀⢀⣹⣿⣿⣿⣿⣷⣮⢩⣬⣿⣿⣿⣿⣷⣦⣤⣔⡀⠀⠀⠀⠀⣲⣿⣿⣿⣿⠟⣛⡻\n" +
                "⣿⣿⣿⣿⣿⣿⣿⣷⣤⣀⠀⠈⠙⠛⠛⠁⠀⣀⣤⣿⣿⣿⣿⣿⣿⣿⣿⣼⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣆⢀⣿⣿⣿⣿⣿⣵⣮⣥\n" +
                "⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣦⡀⠀⠀⠀⢰⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡿⠋⠉⡁⣀⣈⣩⠝⢉⡻⣿⣿⣿⣾⣿⣿⣿⠿⢿⣿⣿⣿\n" +
                "⡿⡭⢤⣶⣬⠽⣿⣿⣿⣿⣿⣿⣿⡄⠀⠀⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡻⣥⠀⠙⠿⠃⢀⣲⣾⣾⣿⣿⣿⠟⠉⠀⠀⣀⣉⡛⠻\n" +
                "⣿⣄⣀⡛⠁⢀⣽⣿⣿⣿⣿⣿⣿⣷⠀⢠⣿⣿⣿⣿⡿⠿⠿⠛⠛⠿⣿⣿⣿⣿⣾⣷⡀⢀⣤⣿⣿⣿⣿⣿⡿⢋⡀⠐⠀⡴⢋⡉⢿⡁\n" +
                "⣿⣿⣿⣿⢰⣿⣿⣿⣿⣿⣿⣿⣿⣿⠀⢸⣿⣿⡿⠋⠀⣤⠤⠤⣤⡤⣀⡙⠻⣿⣿⣿⡇⢸⣿⣿⣿⣿⣿⣿⣿⣷⣄⠀⠀⢧⣈⣍⡸⠃\n" +
                "⣿⣿⣿⣿⣯⣿⣿⣿⣿⣿⣿⣿⣿⣿⡅⣾⣿⠟⠀⠀⠀⢇⠀⠛⢀⡇⠀⠈⢙⣷⣿⣿⣷⣸⣿⣿⣿⣿⣿⣿⣿⣿⣿⣷⣦⠀⠀⠀⢀⣴\n" +
                "⣿⣿⠿⠛⠛⢿⣿⣿⣿⣿⣿⣿⣿⣿⡇⣿⣟⣚⣀⣀⣀⠈⠉⠉⠉⠀⢀⣠⣼⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣷⠀⠀⣾⣿\n" +
                "⡿⠋⠤⠶⠀⢀⡙⠻⢿⣿⣿⣿⣿⣿⡇⣿⣿⣿⣿⣿⣿⣷⣦⡀⢀⣾⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡆⠀⣿⣿\n" +
                "⠀⡴⡻⡋⠓⡄⠈⠉⠒⣝⢻⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡇⢸⣿⣿⣿⡿⠛⣉⣤⣤⣤⣀⡉⠛⠿⢿⣿⣿⣿⣿⣿⣿⣿⡇⣸⣿⣿\n" +
                "⠀⢧⡁⠛⢀⠇⠀⠀⠀⢈⡼⠿⠿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣧⣸⣿⡿⠃⠐⢉⣀⣀⠀⠀⠀⠈⠉⠁⠂⠠⢤⣿⣿⣿⣿⣿⣿⣿⣿⣿\n" +
                "⠀⠀⠉⠉⠁⠀⢀⣠⣾⣿⣶⢾⠛⠙⢿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡏⠐⢀⠔⠋⠁⢰⡟⠡⢒⣒⠤⡄⣀⣀⣀⠉⠻⠿⣿⣿⣿⣿⣿⣿\n" +
                "⣶⣦⡄⠀⠀⣴⣿⣿⣿⣊⠑⠲⠚⢀⣾⣿⣿⣿⣿⣿⣿⣿⣿⣿⠟⢀⢴⠀⠀⠀⠀⢸⡇⠃⠹⠿⢁⢁⣿⠀⠀⠉⠹⠛⢿⣿⣿⣿⣿⣿\n" +
                "⣿⣿⣷⠀⢸⣿⣿⣿⣿⣿⣦⡀⣶⣿⣿⣿⣿⠿⣿⣿⣿⣿⣿⣿⣀⣑⣈⠀⠀⠀⠀⠀⠻⠶⣤⣥⠤⠞⠃⠀⠀⠐⠀⣪⣿⣿⣿⣿⣿⣿\n" +
                "⣿⣿⣿⡄⣾⣿⣿⣿⣿⣿⣿⡟⣿⣿⣿⣿⠁⢴⣶⠊⢻⣿⣿⣿⣿⣿⣿⣿⣿⣷⣶⣤⣀⣀⡀⠀⠀⠀⠀⢀⣠⣶⣾⣿⣿⣿⣿⣿⣿⣿\n" +
                "⣿⣿⣿⡇⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣯⣦⣬⡁⣠⣾⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡀⠀⠀⣴⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿\n" +
                "⣿⣿⣿⣇⣿⣿⣿⣿⣿⠟⠉⠉⠀⠀⠀⠉⠻⢿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡿⣿⣿⣿⣿⣿⣿⣿⣷⣴⣾⣿⣿⠿⠟⠛⠛⠛⠋⠙⢋⣛⣛\n" +
                "⣿⣿⣿⣿⣿⣿⠿⠋⣁⣤⡤⠦⠀⠤⢤⣄⣀⠀⠈⠻⢿⣿⣿⣿⣿⣿⠏⣤⣤⣹⣿⣿⣿⣿⣿⡿⠟⠉⠀⠀⢀⢠⠴⣶⣭⣭⣯⣿⣿⣿\n" +
                "⣿⣿⣿⣿⡿⢁⠴⠋⠁⡞⠀⢢⣤⡄⡀⢳⠈⠙⠷⣤⡀⠉⢻⣿⣿⣽⠀⠈⠋⣿⣿⣿⣿⡿⠋⠀⠀⣠⣄⣬⠿⠟⠛⠉⠉⠉⠉⠁⠈⠉\n" +
                "⣿⣿⣿⣿⢃⠄⠀⠀⠀⢧⡀⠘⠛⡃⢁⡾⠀⠀⠀⠈⠟⢶⣾⣿⣿⣿⡇⢸⣿⣿⣿⣿⡿⡇⢀⢀⡼⠞⠉⣠⣤⠴⠶⠶⠤⣄⡀⠀⠀⠀\n" +
                "⣿⣿⣿⡿⢀⠀⠀⠀⠀⠈⠛⠶⠤⠴⠛⠀⠀⠀⠀⠀⣴⣿⣿⣿⣿⣿⡇⣼⣿⣿⣿⠏⠀⣴⡽⠋⠀⣠⠞⠉⠀⠀⣀⣀⠀⠈⠙⢦⡀⠀\n" +
                "⣿⣿⣿⣧⣪⣦⣀⣀⠀⠀⢂⢀⠀⠀⠀⠀⠀⠀⣠⣾⣿⣿⣿⣿⣿⣿⡇⣿⣿⣿⡇⢠⡾⠋⠁⠀⢠⡟⠀⢀⠜⢀⣠⣀⠈⢤⠀⠈⣷⠀\n" +
                "⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡕⠀⠀⠀⣴⣿⣿⣿⣿⣿⣿⣿⣿⣿⡇⣿⣿⡟⢠⠏⠀⠀⠀⠀⢸⡇⠀⠈⠀⣿⣿⣿⡇⢀⠄⠀⣿⠀\n" +
                "⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡄⠀⢠⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡟⢠⡏⠀⠀⠀⠀⠀⠘⣇⠀⠀⠢⡈⠛⠋⢀⡔⠀⠀⡟⠀\n" +
                "⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣇⠀⣸⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⢣⡟⠀⠀⠀⠀⠀⠀⠀⠘⢧⣄⠀⠀⠁⠈⠀⠀⣠⠞⠁⠀\n" +
                "⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡄⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡇⡟⠂⠀⠀⠀⠀⠀⠀⠀⠀⠀⠉⠛⠲⠶⠶⠒⠋⠁⠀⠀⠀\n" +
                "⣿⣿⣿⣿⣿⠿⠿⠛⠛⠛⠛⠻⢿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡵⠷⠒⠒⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⠀⠀⠀⠀⠀\n" +
                "⣿⣿⠟⠋⠁⠀⠀⢠⡤⠀⠀⣀⠀⠈⠙⢿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣷⣶⣶⣤⣀⡀⠀⢀⠀⠀⠀⠀⠀⠀⠀⠀⢀⡔⢠\n" +
                "⣿⣁⡤⠎⠁⠀⠀⡞⠀⣵⡇⡌⡇⠉⠐⠠⠘⢿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣶⣾⣷⣦⣀⠀⠀⠀⠀⠀⠀⢿\n" +
                "⣿⣿⣷⣄⠀⠀⠀⠱⣄⡉⣁⡱⠃⠀⠀⠀⠈⠪⣿⣿⣿⣿⣿⣟⠛⠉⠙⠻⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣷⡀⠀⠀⠀⠀⢉\n" +
                "⣿⣿⣿⣿⣷⣶⣄⣀⠀⠉⠉⠀⠀⢀⣴⣤⣴⣾⣿⣿⣿⣿⣿⡁⠈⢿⣷⠒⠘⢿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣧⠀⠀⠀⠀⢸\n" +
                "⣿⣿⣿⣿⣿⣿⣿⣿⡆⠀⠀⢀⣴⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣤⣀⣤⣤⣀⣈⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠀⠀⠀⠀⣸\n" +
                "⣿⣿⣿⣿⣿⣿⣿⣿⣿⠀⠀⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡇⠀⠀⠀⣿\n" +
                "⣿⣿⣿⣿⣿⣿⣿⣿⡇⠀⠀⢿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡇⠀⠀⠀⣿");
        Map a = new Map(7);
        Window b = new Window(a);
        //b.setMapText(new JLabel("AAAAA"));
    }

}
