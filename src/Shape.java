import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;



abstract public class Shape {

	private int x;
	private int y;
	private Color color;
	
	private Point startPoint;
	
	Shape(Point p, Color c)
	{
		startPoint = p;
		color = c;
	}
	
	public Point getPoint()
	{
		return startPoint;
	}
	
	public int getX()
	{
		return x;
	}
	
	public int getY()
	{
		return y;
	}
	
	public Color getColor() {
		return color;
	}
	
	abstract String getShapeType();
	abstract String getData();
	abstract void draw(Graphics page);
	abstract void addPoint(Point e);
	abstract void setIsComplete(boolean b);
}
