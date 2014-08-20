import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;


public class Rectangle extends Shape {
	
	private int height, width;

	Rectangle(Point p, Color c)
	{
		super(p, c);
		height = 30;
		width = 30;
	}
	
	Rectangle(Point p, Color c, int h, int w)
	{
		super(p, c);
		height = h;
		width = w;
	}

	public int getHeight() {
		return height;
	}

	public int getWidth() {
		return width;
	}
	
	public String getShapeType()
	{
		return "Rectangle";
	}
	
	public String getData()
	{
		String data = null;
		
		data = this.getPoint().x + " " + this.getPoint().y + "\n" + this.getWidth() + "\n" + this.getHeight();
		
		return data;
	}
	
	public void addPoint(Point e){}
	public void setIsComplete(boolean b){}
	
	public void draw(Graphics page)
	{
		page.fillRect(getPoint().x, getPoint().y, width, height);
	}
	
	
}
