package exercise32_03;

import javafx.animation.PathTransition;
import javafx.scene.shape.Line;
import javafx.util.Duration;
import javafx.scene.image.ImageView;

public class MyThread extends Thread{
	ImageView imageView = new ImageView(); 
	int l = 0;
	MyThread(ImageView imageview1){
		this.imageView = imageview1; 
	}
	
	public void run() {
			PathTransition pt = new PathTransition(Duration.millis(10000),
			new Line(100, 200, 100, 0), imageView); pt.setCycleCount(5);
			pt.setCycleCount(5);
			pt.play(); // Start animation
		
	}
}

