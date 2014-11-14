package a8;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.Hashtable;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class BlurMethodUI extends JPanel implements ChangeListener {
	private JSlider blur_slider;
	private JSlider brush_size;

	public BlurMethodUI() {
		setLayout(new GridLayout(0,1));
	
		JPanel slider_panel = new JPanel();
		slider_panel.setLayout(new GridLayout(0,1));
//value or intensity
		JPanel blur_panel = new JPanel();
		JLabel blur_label = new JLabel("Blur Intensity: ");
		blur_panel.setLayout(new BorderLayout());
		blur_panel.add(blur_label, BorderLayout.WEST);
		blur_slider = new JSlider(5,20);
		blur_slider.addChangeListener(this);
		blur_panel.add(blur_slider, BorderLayout.CENTER);
//Label
		blur_slider = new JSlider(5,20,5);
		blur_slider.addChangeListener(this);
		blur_panel.add(blur_slider, BorderLayout.CENTER);
		blur_slider.setMajorTickSpacing(5);
		blur_slider.setSnapToTicks(true);
		blur_slider.setPaintTicks(true);
		
		Hashtable blur_sliderLabels=new Hashtable();
		for(int x=-100;x<=100;x+=5){
			blur_sliderLabels.put(x, new JLabel(x+""));
		}
		blur_slider.setLabelTable(blur_sliderLabels);
		blur_slider.setPaintLabels(true);
		blur_slider.addChangeListener(this);
		
	//size of blur	
		JPanel brush_size_panel = new JPanel();
		JLabel brush_size_label = new JLabel("Brush size: ");
		brush_size_panel.setLayout(new BorderLayout());
		brush_size_panel.add(brush_size_label, BorderLayout.WEST);
		brush_size = new JSlider(5,20);
		brush_size.addChangeListener(this);
		brush_size_panel.add(brush_size, BorderLayout.CENTER);
		
		brush_size = new JSlider(5,20,5);
		brush_size.addChangeListener(this);
		brush_size_panel.add(brush_size, BorderLayout.CENTER);
		brush_size.setMajorTickSpacing(5);
		brush_size.setSnapToTicks(true);
		brush_size.setPaintTicks(true);
		
		Hashtable brush_sizeLabels=new Hashtable();
		for(int x=-100;x<=100;x+=5){
			brush_sizeLabels.put(x, new JLabel(x+""));
		}
		brush_size.setLabelTable(brush_sizeLabels);
		brush_size.setPaintLabels(true);
		brush_size.addChangeListener(this);
		
		add(brush_size_panel);
		add(blur_panel);
		stateChanged(null);
}
	@Override
	public void stateChanged(ChangeEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	public int getBrushblur() {
		return blur_slider.getValue();
		
}
	public int getBrushSize(){
		return brush_size.getValue();
	}
}
