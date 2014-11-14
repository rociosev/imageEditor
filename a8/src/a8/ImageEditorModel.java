package a8;

public class ImageEditorModel {

	private Frame original;
	private Frame current;

	public ImageEditorModel(Frame f) {
		original = f;
		current = original.copy();
	}

	public Frame getCurrent() {
		return current;
	}

	public Pixel getPixel(int x, int y) {
		return current.getPixel(x, y);
	}

	public void paintAt(int x, int y, Pixel brushColor, int brush_size) {
		current.suspendNotifications();

		for (int xpos=x-brush_size+1; xpos <=x+brush_size-1; xpos++) {
			for (int ypos=y-brush_size+1; ypos <=y+brush_size-1; ypos++) {
				if (xpos >= 0 &&
						xpos < current.getWidth() &&
						ypos >= 0 &&
						ypos < current.getHeight()) {
					current.setPixel(xpos, ypos, brushColor);
				}
			}
		}
		current.resumeNotifications();
	}
	public void blurAt(int x, int y, int brush_size, int value){
		if (value == 0)
			return;
		current.suspendNotifications();
		Frame temp = current.copy();
		for (int xpos=x-brush_size+1; xpos <=x+brush_size-1; xpos++) {
			for (int ypos=y-brush_size+1; ypos <=y+brush_size-1; ypos++) {
				if (xpos >= 0 &&
						xpos < current.getWidth() &&
						ypos >= 0 &&
						ypos < current.getHeight()){ 
				int minX = x-value;
				int minY = y-value;
				int maxX = x+value;
				int maxY = y+value;
				if (minX<0)
					minX = 0;
				if (minY <0)
					minY = 0;
				if (maxX>current.getWidth()-1)
					maxX = current.getWidth()-1;
				if (maxY>current.getHeight()-1)
					maxY = current.getHeight()-1;
				IndirectFrame g = temp.crop(minX, minY, maxX-minX, maxY-minY);
				current.setPixel(xpos,ypos,g.getAverage());
			}
		}
	}

	current.resumeNotifications();
}
}
