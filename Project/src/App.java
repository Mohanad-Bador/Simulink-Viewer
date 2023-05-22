
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.stage.Stage;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;

import static javafx.scene.paint.Color.WHITE;


public class App extends Application {
	@Override
	public void start(Stage primaryStage) {
		// Create a pane
		Pane pane = new Pane();
		int[] x=new int[320];
		int[] y=new int[320];
		int[] l=new int[320];
		int[] w=new int[320];
		for(Block b:SimulinkParser.getBlocks())
		{
			int nX=b.getX()-730;
			int nY=b.getY()-150;
			int z=b.getZ();

			x[z]=nX;
			y[z]=nY;
			l[z]=b.getLength();
			w[z]=b.getWidth();

			Rectangle r =new Rectangle(nX,nY,b.getWidth(),b.getLength());
			r.setStroke(Color.BLACK);
			r.setFill(Color.WHITE);

			Rectangle r2=new Rectangle(nX-1,nY-1,b.getWidth()+2,b.getLength()+2);
			r2.setStroke(Color.DODGERBLUE);
			r2.setFill(null);

			Text t=new Text(b.getName());
			t.setX(nX+b.getWidth()/2-t.getBoundsInLocal().getWidth()/2);
			t.setY(nY+b.getLength()+15);
			t.setFont(Font.font("Courier", FontWeight.SEMI_BOLD, FontPosture.REGULAR, 12));
			t.setFill(Color.DODGERBLUE);
			pane.getChildren().add(r);
			if(b.getName().equals("Saturation")){
				Image image = new Image("file:lib\\Saturation.jpg");
				ImagePattern imagePattern = new ImagePattern(image);
				Rectangle r3 =new Rectangle(nX+2,nY+1,b.getWidth()-4,b.getLength()-2);
				r3.setFill(imagePattern);
				pane.getChildren().add(r3);
			}
			else if(b.getName().equals("Constant")){
				Text t2=new Text("1");
				t2.setX(nX+b.getWidth()/2.7);
				t2.setY(nY+b.getLength()/1.65);
				t2.setFont(Font.font("Courier", FontWeight.SEMI_BOLD, FontPosture.REGULAR, 12));
				pane.getChildren().add(t2);

				// Create the corner lines for the selection box
				int cornerSize = 2;
				int gap = 4;
				Line topLeftCorner1 = new Line(nX-gap, nY-gap, nX -gap + cornerSize, nY-gap);
				Line topLeftCorner2= new Line(nX-gap, nY-gap, nX -gap, nY-gap + cornerSize);

				Line topRightCorner1 = new Line(nX + b.getWidth()+gap, nY-gap, nX + b.getWidth()+gap - cornerSize, nY -gap);
				Line topRightCorner2 = new Line(nX + b.getWidth()+gap, nY-gap, nX + b.getWidth()+gap, nY -gap + cornerSize);

				Line bottomLeftCorner1 = new Line(nX-gap, nY + b.getLength()+gap, nX -gap + cornerSize, nY + b.getLength()+gap);
				Line bottomLeftCorner2 = new Line(nX-gap, nY + b.getLength()+gap, nX -gap, nY + b.getLength()+gap - cornerSize);

				Line bottomRightCorner1 = new Line(nX + b.getWidth()+gap, nY + b.getLength()+gap, nX + b.getWidth()+gap - cornerSize, nY + b.getLength()+gap );
				Line bottomRightCorner2 = new Line(nX + b.getWidth()+gap, nY + b.getLength()+gap, nX + b.getWidth()+gap, nY + b.getLength()+gap - cornerSize);


				Color cornerColor = Color.DODGERBLUE;
				double cornerWidth = 1;
				topLeftCorner1.setStroke(cornerColor);
				topLeftCorner2.setStroke(cornerColor);
				topLeftCorner1.setStrokeWidth(cornerWidth);
				topLeftCorner2.setStrokeWidth(cornerWidth);
				topRightCorner1.setStroke(cornerColor);
				topRightCorner2.setStroke(cornerColor);
				topRightCorner1.setStrokeWidth(cornerWidth);
				topRightCorner2.setStrokeWidth(cornerWidth);
				bottomLeftCorner1.setStroke(cornerColor);
				bottomLeftCorner2.setStroke(cornerColor);
				bottomLeftCorner1.setStrokeWidth(cornerWidth);
				bottomLeftCorner2.setStrokeWidth(cornerWidth);
				bottomRightCorner1.setStroke(cornerColor);
				bottomRightCorner2.setStroke(cornerColor);
				bottomRightCorner1.setStrokeWidth(cornerWidth);
				bottomRightCorner2.setStrokeWidth(cornerWidth);

				// Add the corner lines to the pane
				pane.getChildren().addAll(topLeftCorner1, topRightCorner1, bottomLeftCorner1, bottomRightCorner1);
				pane.getChildren().addAll(topLeftCorner2, topRightCorner2, bottomLeftCorner2, bottomRightCorner2);



			}else if(b.getName().equals("Add")){
				Text t2=new Text("+");
				t2.setX(nX+2);
				t2.setY(nY+b.getLength()/3.1);
				t2.setFont(Font.font("Courier", FontWeight.SEMI_BOLD, FontPosture.REGULAR, 12));
				pane.getChildren().add(t2);
				Text t3=new Text("+");
				t3.setX(nX+2);
				t3.setY(nY+b.getLength()/1.65);
				t3.setFont(Font.font("Courier", FontWeight.SEMI_BOLD, FontPosture.REGULAR, 12));
				pane.getChildren().add(t3);
				Text t4=new Text("+");
				t4.setX(nX+2);
				t4.setY(nY+b.getLength()/1.1);
				t4.setFont(Font.font("Courier", FontWeight.SEMI_BOLD, FontPosture.REGULAR, 12));
				pane.getChildren().add(t4);
			}else if(b.getName().equals("Scope")){
				Rectangle r3 =new Rectangle(nX+4.5,nY+4.5,b.getWidth()-9,b.getLength()/2.3);
				r3.setArcHeight(4.5);
				r3.setArcWidth(4.5);
				r3.setStroke(Color.BLACK);
				r3.setStrokeWidth(0.7);
				r3.setFill(WHITE);
				pane.getChildren().add(r3);
			}else if(b.getName().equals("Unit Delay")){
				Image image = new Image("file:lib\\Unit Delay.jpg");
				ImagePattern imagePattern = new ImagePattern(image);
				Rectangle r3 =new Rectangle(nX+1,nY+1,b.getWidth()-2,b.getLength()-2);
				r3.setFill(imagePattern);
				pane.getChildren().add(r3);
			}
			pane.getChildren().add(t);
			pane.getChildren().add(r2);
		}

		for(Lines L:SimulinkParser.getLines())
		{
			int s= L.getSource();
			if(L.points.isEmpty())
			{
				int dL=L.getDestination();
				if(x[dL]-x[s]>0)
				{
					int sourceX= x[s]+ w[s];
					int sourceY= y[s]+ l[s]/2;
					int destinationX=x[dL];
					int destinationY=y[dL]+l[dL]/2;
					Line LI=new Line(sourceX, sourceY, destinationX, destinationY);
					LI.setStrokeWidth(0.5);
					Line LI2=new Line(sourceX+1, sourceY, destinationX, destinationY);
					LI2.setStrokeWidth(2);
					LI2.setStroke(Color.DODGERBLUE);
					pane.getChildren().add(LI2);
					pane.getChildren().add(LI);
					Polygon arrowhead = new Polygon();
					arrowhead.getPoints().addAll(new Double[]{
						destinationX-10.0, destinationY-5.0,
						(double) destinationX, (double) (destinationY),
						destinationX-10.0, destinationY+5.0 });
					arrowhead.setFill(Color.BLACK);
					arrowhead.setStrokeWidth(1);
					arrowhead.setStroke(Color.DODGERBLUE);
					pane.getChildren().add(arrowhead);
				}
				else
				{
					int sourceX= x[s];
					int sourceY= y[s]+ l[s]/2;
					int destinationX=x[dL]+w[dL];
					int destinationY=y[dL]+l[dL]/2;
					Line LI=new Line(sourceX, sourceY, destinationX, destinationY);
					LI.setStrokeWidth(0.5);
					Line LI2=new Line(sourceX-1, sourceY, destinationX, destinationY);
					LI2.setStrokeWidth(2);
					LI2.setStroke(Color.DODGERBLUE);
					pane.getChildren().add(LI2);
					pane.getChildren().add(LI);
					Polygon arrowhead = new Polygon();
					arrowhead.getPoints().addAll(new Double[]{
						destinationX-10.0, destinationY-5.0,
						(double) destinationX, (double) (destinationY),
						destinationX-10.0, destinationY+5.0 });
					arrowhead.setFill(Color.BLACK);
					arrowhead.setStrokeWidth(1);
					arrowhead.setStroke(Color.DODGERBLUE);
					double angle = Math.atan2(((destinationY)-(sourceY)), ((destinationX)-(sourceX))) * 180/Math.PI;
					if(angle==180.0)
					{
						arrowhead.setRotate(angle);
						arrowhead.setTranslateX(10);
					}
					pane.getChildren().add(arrowhead);
				}
			}

			else if(L.dst.isEmpty())
			{	int sourceX;
				int sourceY;
				int destinationX;
				int destinationY;
				if(L.getX()>0)
				{
					sourceX= x[s]+ w[s];
					sourceY= y[s]+ l[s]/2;
					destinationX=sourceX+L.getX();
					destinationY=sourceY+L.getY();
					Line LI=new Line(sourceX, sourceY, destinationX, destinationY);
					LI.setStrokeWidth(0.5);
					Line LI2=new Line(sourceX+1, sourceY, destinationX, destinationY);
					LI2.setStrokeWidth(2);
					LI2.setStroke(Color.DODGERBLUE);
					pane.getChildren().add(LI2);
					pane.getChildren().add(LI);
				}
				else
				{
					sourceX= x[s];
					sourceY= y[s]+ l[s]/2;
					destinationX=sourceX+L.getX();
					destinationY=sourceY+L.getY();
					Line LI=new Line(sourceX, sourceY, destinationX, destinationY);
					LI.setStrokeWidth(0.5);
					Line LI2=new Line(sourceX-1, sourceY, destinationX, destinationY);
					LI2.setStrokeWidth(2);
					LI2.setStroke(Color.DODGERBLUE);
					pane.getChildren().add(LI);
				}

				if(!L.branches.isEmpty())
				{	int sourceBX= destinationX;
					int sourceBY= destinationY;
					int destinationBX;
					int destinationBY;
					Circle C=new Circle(sourceBX, sourceBY, 2, Color.BLACK);

					for(Branch B:L.getBranches())
					{
						int dB=B.getDestination();
						if(B.points.isEmpty())
						{
							sourceBX= destinationX;
							sourceBY= destinationY;
							if(x[dB]-sourceBX>0)
							{
								destinationBX=x[dB];
								destinationBY=sourceBY;
								Line LB= new Line(sourceBX, sourceBY,destinationBX,destinationBY);
								LB.setStrokeWidth(0.5);
								Line LB2= new Line(sourceBX, sourceBY,destinationBX,destinationBY);
								LB2.setStrokeWidth(2);
								LB2.setStroke(Color.DODGERBLUE);
								pane.getChildren().add(LB2);
								pane.getChildren().add(LB);
								Polygon arrowhead = new Polygon();
								arrowhead.getPoints().addAll(new Double[]{
									destinationBX-10.0, destinationBY-5.0,
									(double) destinationBX, (double) (destinationBY),
									destinationBX-10.0, destinationBY+5.0 });
								arrowhead.setFill(Color.BLACK);
								arrowhead.setStrokeWidth(1);
								arrowhead.setStroke(Color.DODGERBLUE);
								double angle = Math.atan2(((destinationBY)-(sourceBX)), ((destinationBX)-(sourceBY))) * 180/Math.PI;
								if(angle==180.0)
								{
									arrowhead.setRotate(angle);
									arrowhead.setTranslateX(10);
								}
								pane.getChildren().add(arrowhead);
							}
							else
							{
								destinationBX=x[dB]+w[dB];
								destinationBY=sourceBY;
								Line LB= new Line(sourceBX, sourceBY,destinationBX,destinationBY);
								LB.setStrokeWidth(0.5);
								Line LB2= new Line(sourceBX, sourceBY,destinationBX,destinationBY);
								LB2.setStrokeWidth(2);
								LB2.setStroke(Color.DODGERBLUE);
								pane.getChildren().add(LB2);
								pane.getChildren().add(LB);
								Polygon arrowhead = new Polygon();
								arrowhead.getPoints().addAll(new Double[]{
									destinationBX-10.0, destinationBY-5.0,
									(double) destinationBX, (double) (destinationBY),
									destinationBX-10.0, destinationBY+5.0 });
								arrowhead.setFill(Color.BLACK);
								arrowhead.setStrokeWidth(1);
								arrowhead.setStroke(Color.DODGERBLUE);
								double angle = Math.atan2(((destinationBY)-(sourceBX)), ((destinationBX)-(sourceBY))) * 180/Math.PI;
								if(angle==180.0)
								{
									arrowhead.setRotate(angle);
									arrowhead.setTranslateX(10);
								}
								pane.getChildren().add(arrowhead);
							}
						}
						else
						{
							destinationBX=sourceBX+B.getX();
							destinationBY=sourceBY+B.getY();
							Line LB=new Line(sourceBX,sourceBY, destinationBX, destinationBY);
							LB.setStrokeWidth(0.5);
							Line LB2= new Line(sourceBX, sourceBY,destinationBX,destinationBY);
							LB2.setStrokeWidth(2);
							LB2.setStroke(Color.DODGERBLUE);
							pane.getChildren().add(LB2);
							pane.getChildren().add(LB);
							sourceBX=destinationBX;
							sourceBY=destinationBY;
							if(x[dB]-sourceX>0)
							{
								destinationBX=x[dB];
								destinationBY=y[dB]+l[dB]/2;
								Line LV=new Line(sourceBX,sourceBY,destinationBX , destinationBY);
								LV.setStrokeWidth(0.5);
								Line LV2=new Line(sourceBX+1,sourceBY,destinationBX , destinationBY);
								LV2.setStrokeWidth(2);
								LV2.setStroke(Color.DODGERBLUE);
								pane.getChildren().add(LV2);
								pane.getChildren().add(LV);
								Polygon arrowhead = new Polygon();
								arrowhead.getPoints().addAll(new Double[]{
									destinationBX-10.0, destinationBY-5.0,
									(double) destinationBX, (double) destinationBY,
									destinationBX-10.0, destinationBY+5.0 });
								arrowhead.setFill(Color.BLACK);
								arrowhead.setStrokeWidth(1);
								arrowhead.setStroke(Color.DODGERBLUE);
								double angle = Math.atan2(((destinationBY)-(sourceBY)), (destinationBX-sourceBX)) * 180/Math.PI;
								if(angle==180.0)
								{
									arrowhead.setRotate(angle);
									arrowhead.setTranslateX(10);
								}
								pane.getChildren().add(arrowhead);
							}
							else
							{
								destinationBX=x[dB]+w[dB];
								destinationBY=y[dB]+l[dB]/2;
								Line LV=new Line(sourceBX,sourceBY,destinationBX , destinationBY);
								LV.setStrokeWidth(0.5);
								Line LV2=new Line(sourceBX-1,sourceBY,destinationBX , destinationBY);
								LV2.setStrokeWidth(2);
								LV2.setStroke(Color.DODGERBLUE);
								pane.getChildren().add(LV2);
								pane.getChildren().add(LV);
								Polygon arrowhead = new Polygon();
								arrowhead.getPoints().addAll(new Double[]{
									destinationBX-10.0, destinationBY-5.0,
									(double) destinationBX, (double) destinationBY,
									destinationBX-10.0, destinationBY+5.0 });
								arrowhead.setFill(Color.BLACK);
								arrowhead.setStrokeWidth(1);
								arrowhead.setStroke(Color.DODGERBLUE);
								double angle = Math.atan2(((destinationBY)-(sourceBY)), (destinationBX-sourceBX)) * 180/Math.PI;
								if(angle==180.0)
								{
									arrowhead.setRotate(angle);
									arrowhead.setTranslateX(10);
								}
								pane.getChildren().add(arrowhead);
							}

						}
					}
					pane.getChildren().add(C);
				}
			}

			else
			{
				if(L.points.contains(";"))
				{
					int dL=L.getDestination();
					if(L.getX()>0)
					{
						int sourceX= x[s]+w[s];
						int sourceY= y[s]+ l[s]/2;
						int destinationX=sourceX+L.getX();
						int destinationY=sourceY+L.getY();
						Line L1=new Line(sourceX, sourceY, destinationX, destinationY);
						L1.setStrokeWidth(0.5);
						Line L12= new Line(sourceX+1, sourceY, destinationX, destinationY);
						L12.setStrokeWidth(2);
						L12.setStroke(Color.DODGERBLUE);
						pane.getChildren().add(L12);
						sourceX=destinationX;
						sourceY=destinationY;
						destinationX=sourceX+L.getX2();
						destinationY=sourceY+L.getY2();
						Line L2=new Line(sourceX, sourceY, destinationX, destinationY);
						L2.setStrokeWidth(0.5);
						Line L22= new Line(sourceX, sourceY, destinationX, destinationY);
						L22.setStrokeWidth(2);
						L22.setStroke(Color.DODGERBLUE);
						pane.getChildren().add(L22);
						sourceX=destinationX;
						sourceY=destinationY;
						destinationX=x[dL]+w[s];
						destinationY=sourceY;
						Line L3=new Line(sourceX, sourceY, destinationX, destinationY);
						L3.setStrokeWidth(0.5);
						Line L32= new Line(sourceX, sourceY, destinationX, destinationY);
						L32.setStrokeWidth(2);
						L32.setStroke(Color.DODGERBLUE);
						pane.getChildren().add(L32);
						pane.getChildren().add(L1);
						pane.getChildren().add(L2);
						pane.getChildren().add(L3);
						Polygon arrowhead = new Polygon();
						arrowhead.getPoints().addAll(new Double[]{
							destinationX-10.0, destinationY-5.0,
							(double) destinationX, (double) (destinationY),
							destinationX-10.0, destinationY+5.0 });
						arrowhead.setFill(Color.BLACK);
						arrowhead.setStrokeWidth(1);
						arrowhead.setStroke(Color.DODGERBLUE);
						double angle = Math.atan2(((destinationY)-(sourceX)), ((destinationX)-(sourceY))) * 180/Math.PI;
						if(angle==180.0)
						{
							arrowhead.setRotate(angle);
							arrowhead.setTranslateX(10);
						}
						pane.getChildren().add(arrowhead);
					}
					else
					{
						int sourceX= x[s];
						int sourceY= y[s]+ l[s]/2;
						int destinationX=sourceX+L.getX();
						int destinationY=sourceY+L.getY();
						Line L1=new Line(sourceX, sourceY, destinationX, destinationY);
						L1.setStrokeWidth(0.5);
						Line L12= new Line(sourceX-1, sourceY, destinationX, destinationY);
						L12.setStrokeWidth(2);
						L12.setStroke(Color.DODGERBLUE);
						pane.getChildren().add(L12);
						sourceX=destinationX;
						sourceY=destinationY;
						destinationX=sourceX+L.getX2();
						destinationY=sourceY+L.getY2();
						Line L2=new Line(sourceX, sourceY, destinationX, destinationY);
						L2.setStrokeWidth(0.5);
						Line L22= new Line(sourceX, sourceY, destinationX, destinationY);
						L22.setStrokeWidth(2);
						L22.setStroke(Color.DODGERBLUE);
						pane.getChildren().add(L22);
						sourceX=destinationX;
						sourceY=destinationY;
						destinationX=x[dL];
						destinationY=sourceY;
						Line L3=new Line(sourceX, sourceY, destinationX, destinationY);
						L3.setStrokeWidth(0.5);
						Line L32= new Line(sourceX, sourceY, destinationX, destinationY);
						L32.setStrokeWidth(2);
						L32.setStroke(Color.DODGERBLUE);
						pane.getChildren().add(L32);
						pane.getChildren().add(L1);
						pane.getChildren().add(L2);
						pane.getChildren().add(L3);
						Polygon arrowhead = new Polygon();
						arrowhead.getPoints().addAll(new Double[]{
							destinationX-10.0, destinationY-5.0,
							(double) destinationX, (double) (destinationY),
							destinationX-10.0, destinationY+5.0 });
						arrowhead.setFill(Color.BLACK);
						arrowhead.setStrokeWidth(1);
						arrowhead.setStroke(Color.DODGERBLUE);
						double angle = Math.atan2(((destinationY)-(sourceX)), ((destinationX)-(sourceY))) * 180/Math.PI;
						if(angle==180.0)
						{
							arrowhead.setRotate(angle);
							arrowhead.setTranslateX(10);
						}
						pane.getChildren().add(arrowhead);
					}
				}
			}

		}
		// Create a scene and place it in the stage
		Scene scene = new Scene(pane,500,250);
		primaryStage.setTitle("ShowRectangle"); // Set the stage title
		primaryStage.setScene(scene); // Place the scene in the stage
		primaryStage.show(); // Display the stage
	}

	public static void main(String[] args) {
		String filepath=args[0];
		SimulinkParser.parse(filepath);
		launch(args);
	}
}
