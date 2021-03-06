import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JFileChooser;
import javax.swing.JPanel;


public class CanvasPanel extends JPanel{
	private ArrayList<Shape> shapes;
	private String shapeType = "none";
	private Color color;
	private boolean firstClick = false;
	private Shape s, fd;

	CanvasPanel(){
		this.setPreferredSize(new Dimension(1000, 800));
		this.setBackground(Color.WHITE);
		this.setBorder(BorderFactory.createLineBorder(Color.black, 1));
		this.addMouseListener(new DrawListener());
		this.addMouseMotionListener(new DrawListener2());
		
		shapes = new ArrayList<Shape>();
		
	}
	
	private class DrawListener implements MouseListener
	{
		public void mousePressed(MouseEvent event) {
			if (shapeType.equals("Circle"))
			{
				s = new Circle(event.getPoint(), color);
				shapes.add(s);
				repaint();
			}
			
			if (shapeType.equals("Rectangle"))
			{
				s = new Rectangle(event.getPoint(), color);
				shapes.add(s);
				repaint();
			}
			
			if (shapeType.equals("Polygon"))
			{
				if (firstClick==false)
				{
					s = new Polygon(event.getPoint(), color);
					s.setIsComplete(false);
					shapes.add(s);
					firstClick=true;
				}else{
					s.addPoint(event.getPoint());
				}
				
				if (event.getButton()==MouseEvent.BUTTON3)
				{
					s.setIsComplete(true);
					firstClick=false;
				}

				repaint();
			}
			
			if (shapeType.equals("FreeDraw"))
			{
				fd = new Stroke(event.getPoint(), color);
				fd.setIsComplete(false);
				shapes.add(fd);
				repaint();
			}
		}	

		public void mouseClicked(MouseEvent arg0) {}
		public void mouseEntered(MouseEvent arg0) {}
		public void mouseExited(MouseEvent arg0) {}
		public void mouseReleased(MouseEvent arg0) {}
	}
	
	private class DrawListener2 implements MouseMotionListener
	{
		public void mouseDragged(MouseEvent event) {
			if (shapeType.equals("FreeDraw"))
			{
				Point point2 = event.getPoint();
				fd.addPoint(point2);
				fd.setIsComplete(true);
				repaint();
			}
		}
		
		public void mouseMoved(MouseEvent arg0) {}
	}
	
	public void setShapeType(String st)
	{
		shapeType = st;
	}
	
	public void setColor(Color c)
	{
		color = c;
	}

	
	public void paintComponent(Graphics page)
	{
		super.paintComponent(page);
		for (Shape currShape: shapes)
		{
			page.setColor(currShape.getColor());
			currShape.draw(page);
		}
	}
	
	public void erasePanel()
	{
		shapes = new ArrayList<Shape>();
		repaint();
	}
	
	public void saveShapes()
	{
		JFileChooser chooser = new JFileChooser();
		int returnVal = chooser.showSaveDialog(getParent());
		if (returnVal==JFileChooser.APPROVE_OPTION)
		{
			File file = chooser.getSelectedFile();
			PrintWriter writer=null;
			try {
				writer = new PrintWriter(new FileWriter(file));
			} catch (IOException e) {
				System.out.println("Error saving file.");
			}
			
			writer.println(shapes.size());
			for (int i=0; i<shapes.size(); i++)
			{
				writer.println(shapes.get(i).getClass().getName());
				writer.println(shapes.get(i).getColor().getRed() + " " + shapes.get(i).getColor().getGreen() + " " + shapes.get(i).getColor().getBlue());
				writer.println(shapes.get(i).getData());
			}
			
			writer.flush();
		}
	}
	
	public void loadShapes()
	{
		JFileChooser chooser = new JFileChooser();
		int returnVal = chooser.showOpenDialog(getParent());
		if (returnVal==JFileChooser.APPROVE_OPTION)
		{
			File file = chooser.getSelectedFile();
			BufferedReader reader=null;
			try {
				reader = new BufferedReader(new FileReader(file));
			} catch (IOException e) {
				System.out.println("Error opening file.");
			}	shapes = new ArrayList<Shape>();
			try {
				String l = reader.readLine();
				int length = Integer.parseInt(l);
				
				while (reader.ready())
				{
					for(int i=0; i<length; i++)
					{
						String shapeType = reader.readLine();
						String c = reader.readLine();
						String[] RGB = c.split(" ");
						int[] rgb = new int[3];
						for (int j=0; j<3; j++)
						{
							rgb[j] = Integer.parseInt(RGB[j]);
						}
						color = new Color(rgb[0], rgb[1], rgb[2]);
						
						if (shapeType.equals("Circle"))
						{
							String center = reader.readLine();
							String[] centerPoint = center.split(" ");
							int[] centerXY = new int[2];
							for (int j=0; j<2; j++)
							{
								centerXY[j] = Integer.parseInt(centerPoint[j]);
							}
							Point point = new Point(centerXY[0], centerXY[1]);
							String r = reader.readLine();
							int radius = Integer.parseInt(r);

							Shape s = new Circle(point, color, radius);
							
							shapes.add(s);
							repaint();
						}
						if (shapeType.equals("Rectangle"))
						{
							String p = reader.readLine();
							String[] rPoint = p.split(" ");
							int[] rp = new int[2];
							rp[0] = Integer.parseInt(rPoint[0]);
							rp[1] = Integer.parseInt(rPoint[1]);
							
							Point point = new Point(rp[0], rp[1]);
							
							String w = reader.readLine();
							int width = Integer.parseInt(w);
							
							String h = reader.readLine();
							int height = Integer.parseInt(h);
							
							Shape s = new Rectangle(point, color, width, height);
							
							shapes.add(s);
							repaint();
						}
						if (shapeType.equals("Polygon"))
						{
							String sz = reader.readLine();
							int size = Integer.parseInt(sz);
							Point[] points = new Point[size];
							
							for (int j=0; j<size; j++)
							{
								String p = reader.readLine();
								String[] pt = p.split(" ");
								int[] ptXY = new int[2];
								ptXY[0] = Integer.parseInt(pt[0]);
								ptXY[1] = Integer.parseInt(pt[1]);
								Point point = new Point(ptXY[0], ptXY[1]);
								points[j] = point;
							}
							Shape s = new Polygon(points[0], color);
							
							for(int k=1; k<size; k++)
							{
								s.addPoint(points[k]);
							}
							s.setIsComplete(true);
							
							shapes.add(s);
							repaint();
						}
						if (shapeType.equals("FreeDraw"))
						{
							String sz = reader.readLine();
							int size = Integer.parseInt(sz);
							Point[] points = new Point[size];
							
							for (int j=0; j<size; j++)
							{
								String p = reader.readLine();
								String[] pt = p.split(" ");
								int[] ptXY = new int[2];
								ptXY[0] = Integer.parseInt(pt[0]);
								ptXY[1] = Integer.parseInt(pt[1]);
								Point point = new Point(ptXY[0], ptXY[1]);
								points[j] = point;
							}
							Shape s = new Stroke(points[0], color);
							
							for(int k=1; k<size; k++)
							{
								s.addPoint(points[k]);
							}
							shapes.add(s);
							repaint();
						}
					}
				}
			} catch (IOException e) {
				System.out.println("Error reading file.");
			}
			
		}
	}
}
