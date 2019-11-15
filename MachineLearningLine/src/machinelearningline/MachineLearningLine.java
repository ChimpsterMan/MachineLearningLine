package machinelearningline;

import processing.core.PApplet;


public class MachineLearningLine extends PApplet {
	
	public static void main(String[] args) {
        PApplet.main("machinelearningline.MachineLearningLine");
   }
	
	Point[] positions;
	float[] pointsDiff;
	float pointDiff;
	Line line;
	int points;
	public void settings()
	{
		size(600, 600);
	}
	
	public void setup() 
	{
		points = 6;
		positions = new Point[points];
		pointsDiff = new float[points];
		locations();
		line = new Line();
		line.setup();
	}

	public void draw() 
	{
		background(80);
		for (int i = 0; i < points; i++)
		{
			positions[i].draw();
		}
		line.update();
		line.draw();
		
	}
	
	public class Point
	{
		int xPos;
		int yPos;
		int size;
		void setup()
		{
			size = 10;
		}
		
		void draw()
		{
			fill(66, 134, 244);
			ellipse(xPos*60, yPos*60, size, size);
		}
	}
	
	public class Line
	{
		float yIntercept;
		float slope;
		void setup()
		{
			yIntercept = 10;
			slope = 1;
		}
		
		void draw()
		{
			fill(66, 134, 244);
			line(0, yIntercept, 600, (slope * 600) + yIntercept);
			println("slope: " + slope + " yint: " + yIntercept);
		}
		
		void update()
		{
			float derivativeSlope = 0;
			float derivativeYIntercept = 0;
			float slopeTrainRate = 0.01f;
			float yInterceptTrainRate = 0.01f;
			pointDiff = 0.0f;
			for (int i = 0; i < points; i++)
			{
				pointsDiff[i] = (((slope * (float) positions[i].xPos) + yIntercept) - (float) positions[i].yPos);
				pointsDiff[i] = pointsDiff[i] * pointsDiff[i];
				println(i + " pointsdiff: " + pointsDiff[i]);
				pointDiff += pointsDiff[i];
			}
			println("cost: " + pointDiff);
			for (int i = 0; i < points; i++)
			{
				 derivativeSlope += (float) positions[i].xPos * ((slope * (float) positions[i].xPos) + (yIntercept - (float) positions[i].yPos));
			}
			for (int i = 0; i < points; i++)
			{
				 derivativeYIntercept += (float) ((slope * (float) positions[i].xPos) + (yIntercept - (float) positions[i].yPos));
			}
			println("derivative slope: " + derivativeSlope);
			println(slope);
			slope = slope - (slopeTrainRate * derivativeSlope);
			println(slope);
			yIntercept = yIntercept - (yInterceptTrainRate * derivativeYIntercept);
		}
	}
	
	public void locations()
	{
		for (int i = 0; i < points; i++)
		{
			positions[i] = new Point();
			positions[i].setup();
			positions[i].xPos = i;
			positions[i].yPos = i;
		}
	}
}
