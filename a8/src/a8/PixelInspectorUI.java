package a8;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PixelInspectorUI extends JPanel implements ActionListener {

	private JLabel x_label;
	private JLabel y_label;
	private JLabel pixel_info;
	private Pixel chosenPixel;
	
	public PixelInspectorUI() {
		x_label = new JLabel("X: ");
		y_label = new JLabel("Y: ");
		pixel_info = new JLabel("(r,g,b)");

		setLayout(new GridLayout(3,1));
		add(x_label);
		add(y_label);
		add(pixel_info);
		
		JButton chosenColor = new JButton("Color Chosen");
		chosenColor.setActionCommand("Save Color");
		chosenColor.addActionListener(this);
		add(chosenColor);
	}
	

	public void setInfo(int x, int y, Pixel p) {
		x_label.setText("X: " + x);
		y_label.setText("Y: " + y);
		pixel_info.setText(String.format("(%3.2f, %3.2f, %3.2f)", p.getRed(), p.getBlue(), p.getGreen()));		
		chosenPixel = new ColorPixel(p.getRed(),p.getGreen(), p.getBlue());
	}
	ArrayList<PaintBrushToolUI>brushlist = new ArrayList<PaintBrushToolUI>();
public void addPaintBrush(PaintBrushToolUI p){
	brushlist.add(p);
}


@Override
public void actionPerformed(ActionEvent e) {
	String action = e.getActionCommand();
	if(action.equals("Save Color")){
		for (int p=0; p<brushlist.size();p++){
			brushlist.get(p).setBrushColor(chosenPixel);
			System.out.println(brushlist.get(p).getBrushColor());
		}
			
	}
	
}

}
