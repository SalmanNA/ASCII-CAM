import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamResolution;

import java.awt.Color;
import java.awt.Font;
import java.awt.image.BufferedImage;


public class TakeSnapshotFromVideoExample {

	public static void main(String[] args) throws InterruptedException {

		Webcam webcam = Webcam.getDefault();
		webcam.setViewSize(WebcamResolution.VGA.getSize());

		WebcamPanel panel = new WebcamPanel(webcam);
		panel.setFPSDisplayed(true);
		panel.setDisplayDebugInfo(true);
		panel.setImageSizeDisplayed(true);
		panel.setMirrored(false);

		JFrame window = new JFrame("Test webcam panel");
	    window.add(panel);
		window.setResizable(true);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.pack();
		window.setVisible(true);
		
		JLabel text = new JLabel("Test");
		text.setFont(new Font(Font.MONOSPACED,Font.BOLD, 10));
		text.setForeground(Color.black);
		
		
		
		JPanel asciiPanel = new JPanel();
		asciiPanel.setBackground(null);
		asciiPanel.add(text);
		asciiPanel.setBackground(Color.white);
		
		JScrollPane jScrollPane = new JScrollPane(asciiPanel);
		jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		jScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		

		JFrame ascii = new JFrame("Test ascii panel");
		ascii.getContentPane().add(jScrollPane);
		ascii.setResizable(true);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ascii.pack();
		ascii.setVisible(true);
		ascii.setSize(500, 500);
		
		char[] lets = {'Ñ','@','#','W','$','9','8','7','6','5','4','3','2','1','0','?','!','a','b','c',';',':','+','=','-',',','.','_'};
		BufferedImage vid;
		String pic = "<html>";
		while(true) {
			pic = "<html>";
			int color;
			vid = webcam.getImage();
			for(int length = 0; length<480; length+=5) {
				pic += "<br/>";
				System.out.println("");
				for(int width = 0; width<640; width+=5) {					
					color = vid.getRGB(width, length);
					int blue = color & 0xff;
					int green = (color & 0xff00) >> 8;
					int red = (color & 0xff0000) >> 16;
					int avg = (blue+red+green)/3;					
					pic += lets[(int)(avg/9.2)];

				}
			}
			pic += "</html>";
			text.setText(pic);
		}
		

		
		
		
	}
}
//140-499