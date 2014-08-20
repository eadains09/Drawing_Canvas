import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;


public class Circle extends Shape {
	
	private int radius;
	
	Circle(Point p, Color c)
	{
		super(p, c);
		
		radius = 25;
	}
	
	Circle(Point p, Color c, int r)
	{
		super(p, c);
		radius = r;
	}
	
	public int getRadius()
	{
		return radius;
	}
	
	public String getShapeType()
	{
		return "Circle";
	}
	
	public String getData()
	{
		String data = null;
		
		data = this.getPoint().x + " " + this.getPoint().y + "\n" + this.getRadius();
		return data;
	}
	
	public void addPoint(Point e){}
	public void setIsComplete(boolean b){}
	
	public void draw(Graphics page)
	{
		page.fillOval((getPoint().x)-radius, (getPoint().y)-radius, radius*2, radius*2);
	}
}
