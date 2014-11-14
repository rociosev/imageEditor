package a8;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.Hashtable;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class PaintBrushToolUI extends JPanel implements ChangeListener {

	private JSlider red_slider;
	private JSlider green_slider;
	private JSlider blue_slider;
	private JSlider brush_size;
	private FrameView color_preview;
	
	
	public PaintBrushToolUI() {
		setLayout(new GridLayout(0,1));
		
		JPanel color_chooser_panel = new JPanel();
		color_chooser_panel.setLayout(new BorderLayout());
		
		JPanel slider_panel = new JPanel();
		slider_panel.setLayout(new GridLayout(0,1));
		
		JPanel red_slider_panel = new JPanel();
		JLabel red_label = new JLabel("Red:");
		red_slider_panel.setLayout(new BorderLayout());
		red_slider_panel.add(red_label, BorderLayout.WEST);
		
		red_slider = new JSlider(-100,100,0);
		red_slider.addChangeListener(this);
		red_slider_panel.add(red_slider, BorderLayout.CENTER);
		red_slider.setMajorTickSpacing(10);
		red_slider.setSnapToTicks(true);
		red_slider.setPaintTicks(true);
		
		Hashtable red_sliderLabels=new Hashtable();
		for(int x=-100;x<=100;x+=25){
			red_sliderLabels.put(x, new JLabel(x+""));
		}
		red_slider.setLabelTable(red_sliderLabels);
		red_slider.setPaintLabels(true);
		red_slider.addChangeListener(this);
		
		JPanel green_slider_panel = new JPanel();
		JLabel green_label = new JLabel("Green:");
		green_slider_panel.setLayout(new BorderLayout());
		green_slider_panel.add(green_label, BorderLayout.WEST);
		green_slider = new JSlider(0,100);
		green_slider.addChangeListener(this);
		green_slider_panel.add(green_slider, BorderLayout.CENTER);
		//Labels
		green_slider = new JSlider(-100,100,0);
		green_slider.addChangeListener(this);
		green_slider_panel.add(green_slider, BorderLayout.CENTER);
		green_slider.setMajorTickSpacing(10);
		green_slider.setSnapToTicks(true);
		green_slider.setPaintTicks(true);
		
		Hashtable green_sliderLabels=new Hashtable();
		for(int x=-100;x<=100;x+=25){
			green_sliderLabels.put(x, new JLabel(x+""));
		}
		green_slider.setLabelTable(green_sliderLabels);
		green_slider.setPaintLabels(true);
		green_slider.addChangeListener(this);
		
		JPanel blue_slider_panel = new JPanel();
		JLabel blue_label = new JLabel("Blue: ");
		blue_slider_panel.setLayout(new BorderLayout());
		blue_slider_panel.add(blue_label, BorderLayout.WEST);
		blue_slider = new JSlider(0,100);
		blue_slider.addChangeListener(this);
		blue_slider_panel.add(blue_slider, BorderLayout.CENTER);
//Labels
		blue_slider = new JSlider(-100,100,0);
		blue_slider.addChangeListener(this);
		blue_slider_panel.add(blue_slider, BorderLayout.CENTER);
		blue_slider.setMajorTickSpacing(10);
		blue_slider.setSnapToTicks(true);
		blue_slider.setPaintTicks(true);
		
		Hashtable blue_sliderLabels=new Hashtable();
		for(int x=-100;x<=100;x+=25){
			blue_sliderLabels.put(x, new JLabel(x+""));
		}
		blue_slider.setLabelTable(blue_sliderLabels);
		blue_slider.setPaintLabels(true);
		blue_slider.addChangeListener(this);
		
		JPanel brush_size_panel = new JPanel();
		JLabel brush_size_label = new JLabel("Brush size: ");
		brush_size_panel.setLayout(new BorderLayout());
		brush_size_panel.add(brush_size_label, BorderLayout.WEST);
		brush_size = new JSlider(5,30);
		brush_size.addChangeListener(this);
		brush_size_panel.add(brush_size, BorderLayout.CENTER);
		//Label
		brush_size = new JSlider(0,30,10);
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
		
		// Assumes paint_brush label is widest and asks red and blue label
		// to be the same.
		Dimension d = brush_size_label.getPreferredSize();
		red_label.setPreferredSize(d);
		blue_label.setPreferredSize(d);
		green_label.setPreferredSize(d);
		
		slider_panel.add(red_slider_panel);
		slider_panel.add(green_slider_panel);
		slider_panel.add(blue_slider_panel);
		slider_panel.add(brush_size_panel);
		color_chooser_panel.add(slider_panel, BorderLayout.CENTER);

		color_preview = new FrameView(new ColorFrame(50,50));
		color_chooser_panel.add(color_preview, BorderLayout.EAST);

		add(color_chooser_panel);
		
		stateChanged(null);
	}
public int getBrushSize(){
	return brush_size.getValue();
}
	@Override
	public void stateChanged(ChangeEvent e) {
		Pixel p = new ColorPixel(red_slider.getValue()/100.0,
				                 green_slider.getValue()/100.0,
				                 blue_slider.getValue()/100.0);
		Frame preview_frame = color_preview.getFrame();
		preview_frame.suspendNotifications();
		for (int x=0; x<preview_frame.getWidth(); x++) {
			for (int y=0; y<preview_frame.getHeight(); y++) {
				preview_frame.setPixel(x, y, p);
			}
		}
		preview_frame.resumeNotifications();
	}
	
	public Pixel getBrushColor() {
		return color_preview.getFrame().getPixel(0,0);
	}
	public void  setBrushColor(Pixel p){
		Frame preview_frame = color_preview.getFrame();
		preview_frame.suspendNotifications();
		for (int x=0; x<preview_frame.getWidth();x++){
			for(int i=0; i<preview_frame.getHeight(); i++){
				preview_frame.setPixel(x, i, p);
			}
		}
		preview_frame.resumeNotifications();	
		red_slider.setValue((int)Math.floor(p.getRed()*100));
		green_slider.setValue((int)Math.floor(p.getGreen()*100));
		blue_slider.setValue((int)Math.floor(p.getBlue()*100));
	}
	
}
