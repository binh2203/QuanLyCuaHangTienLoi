package gui;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;
import javax.swing.SwingWorker;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

public class Frm_SplashScreen extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private static JProgressBar progressBar;

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
                try {
                    Frm_SplashScreen frame = new Frm_SplashScreen();
                    frame.setLocationRelativeTo(null);
                    frame.setVisible(true);
                    new BackgroundTask(frame).execute();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Frm_SplashScreen() {
        setUndecorated(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(100, 100, 600, 420);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setBackground(new Color(0, 125, 254));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        ImageIcon icon = new ImageIcon(Frm_SplashScreen.class.getResource("/image/SplashScreen.gif"));
        JLabel lblImg = new JLabel("");
        lblImg.setIcon(icon);
        lblImg.setBounds(0, 210, 600, 150);
        contentPane.add(lblImg);

        JLabel lblTitle = new JLabel("MINI STORE");
        lblTitle.setFont(new Font("Showcard Gothic", Font.BOLD, 36));
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitle.setForeground(new Color(255, 255, 255));
        lblTitle.setBounds(57, 92, 480, 50);
        contentPane.add(lblTitle);

        progressBar = new JProgressBar();
        progressBar.setBackground(Color.WHITE);
        progressBar.setForeground(Color.GREEN);
        progressBar.setBounds(0, 408, 600, 12);
        contentPane.add(progressBar);
    }


    private static class BackgroundTask extends SwingWorker<Void, Integer> {
    	private Frm_SplashScreen splashScreen;

		public BackgroundTask(final Frm_SplashScreen splashScreen) {
            this.splashScreen = splashScreen;
        }

        @Override
        protected Void doInBackground() throws Exception {

            for (int i = 0; i <= 100; i++) {
                Thread.sleep(66);
                publish(i);
            }
            return null;
        }

        @Override
        protected void process(java.util.List<Integer> chunks) {
            for (Integer value : chunks) {
                progressBar.setValue(value);
            }
        }

        @Override
        protected void done() {
        	if (splashScreen != null) {
                splashScreen.dispose();
                Frm_DangNhap frm = new Frm_DangNhap();
                frm.setVisible(true);
            }

        }
    }
}
