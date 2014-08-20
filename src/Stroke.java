import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;


public class Stroke extends Shape {
	private ArrayList<Point> aStroke;
	private Point point1, point2;
	
	Stroke(Point p, Color c)
	{
		super(p, c);
		aStroke = new ArrayList<Point>();
		aStroke.add(p);
	}
	
	public void draw(Graphics page) {
		
		Graphics2D page2 = (Graphics2D)page;
		page2.setStroke(new BasicStroke(5));

		for (int i=0; i<aStroke.size()-1; i++)
		{
			point1 = aStroke.get(i);
			point2 = aStroke.get(i+1);
			page2.drawLine(point1.x, point1.y, point2.x, point2.y);
		}
	}

	public void addPoint(Point e) {
		if (aStroke.size()==0)
		{
			aStroke = new ArrayList<Point>();
		}
		else
		{
			aStroke.add(e);
		}
	}
	
	public String getShapeType()
	{
		return "FreeDraw";
	}
	
	public String getData()
	{
		String data = null;
		
		data = Integer.toString(this.aStroke.size());
		
		for (int i=0; i<aStroke.size(); i++)
		{
			data = data + "\n" + aStroke.get(i).x + " " + aStroke.get(i).y;
		}
		
		return data;
	}
	
	void setIsComplete(boolean b) {}
}
