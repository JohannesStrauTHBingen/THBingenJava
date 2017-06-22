
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.BorderPane;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {

		
		
		
		try {
			WritableImage image = new WritableImage(1000, 1000);
			PixelWriter pixelWriter = image.getPixelWriter();

			for (int x = 0; x < image.getWidth(); x++) {
				for (int y = 0; y < image.getHeight(); y++) {
					double xD = x / 1000;
					double yD = y / 1000;
					pixelWriter.setColor(x, y, new Folge().setRealZ(0).setImgZ(0).setRealC(xD).setImgC(yD).iterate());
				}
			}

			ImageView imageView = new ImageView(image);

			BorderPane root = new BorderPane();
			root.getChildren().add(imageView);
			Scene scene = new Scene(root, 400, 400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
