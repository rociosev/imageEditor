package a8;

import java.awt.event.MouseEvent;

import javax.swing.JPanel;

public class BlurMethod implements Tool {


	private BlurMethodUI ui;
	private ImageEditorModel model;
	private int brush_size = 5;
	
	public BlurMethod(ImageEditorModel model) {
		this.model = model;
		ui = new BlurMethodUI();
	
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		model.blurAt(e.getX(), e.getY(), ui.getBrushSize(), ui.getBrushblur());
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		model.blurAt(e.getX(), e.getY(), ui.getBrushSize(), ui.getBrushblur());	
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public String getName() {
		return "Paint Brush";
	}

	@Override
	public JPanel getUI() {
		return ui;
	}
}
