import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class Main extends Application {
	int height;
	int width;
	String schema;
	Calc c = new Calc();
	
	final String DEFX = "-0.5";
	final String DEFY = "0.3";

	@Override
	public void start(Stage primaryStage) {
		try {
			// Draw Panes
			GridPane root = new GridPane();
			// root.setGridLinesVisible(false);
			root.setMaxHeight(GridPane.USE_COMPUTED_SIZE);
			root.setMaxWidth(GridPane.USE_COMPUTED_SIZE);
			root.setMinHeight(GridPane.USE_COMPUTED_SIZE);
			root.setMinWidth(GridPane.USE_COMPUTED_SIZE);
			root.setAlignment(Pos.CENTER);
			root.setPadding(new Insets(10));
			root.setVgap(5);
			root.setHgap(5);
			
			// Labels
			Label switchFunctionLabel = new Label("Function:");
			Label heightLabel = new Label("Height:");
			Label widthLabel = new Label("Width:");
			Label xLabel = new Label("x:");
			Label yLabel = new Label("y:");
			Label colorSchemaLabel = new Label("Color Schema:");
			Label colorLabel = new Label("Color:");
			Label error = new Label();
			
			ComboBox<String> switchFunction = new ComboBox<>();
			TextField heightInput = new TextField();
			TextField widthInput = new TextField();
			TextField xInput = new TextField();
			TextField yInput = new TextField();
			ComboBox<String> colorSchema = new ComboBox<>();
			ColorPicker colorPicker = new ColorPicker(Color.BLACK);
			
			
			// ComboBox SwitchFunction
			switchFunction.getItems().add("Mandelbrot");
			switchFunction.getItems().add("Julia");
			switchFunction.valueProperty().addListener(new ChangeListener<String>() {

				@Override
				public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
					// TODO Auto-generated method stub
					if(newValue.equals("Mandelbrot")) {
						xInput.setText(DEFX);
						xInput.setDisable(true);
						yInput.setText(DEFY);
						yInput.setDisable(true);
					} else {
						xInput.setDisable(false);
						yInput.setDisable(false);
					}
				}
				
			});
			
			switchFunction.setValue(switchFunction.getItems().get(0));

			GridPane.setConstraints(switchFunctionLabel, 0, 0);
			root.getChildren().add(switchFunctionLabel);
			GridPane.setConstraints(switchFunction, 1, 0);
			root.getChildren().add(switchFunction);

			
			// Height of Set
			heightInput.setPromptText("Enter the height of the image");
			heightInput.setText("500");
			heightInput.textProperty().addListener(new ChangeListener<String>() {

				@Override
				public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
					// TODO Auto-generated method stub
					if (!newValue.matches("\\d+")) {
						heightInput.setText(newValue.replaceAll("[^\\d]", ""));
					}
				}

			});

			GridPane.setConstraints(heightLabel, 0, 1);
			root.getChildren().add(heightLabel);
			GridPane.setConstraints(heightInput, 1, 1);
			root.getChildren().add(heightInput);

			// Width of Set
			widthInput.setPromptText("Enter the width of the image");
			widthInput.setText("500");
			widthInput.textProperty().addListener(new ChangeListener<String>() {

				@Override
				public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
					// TODO Auto-generated method stub
					if (!newValue.matches("\\d+")) {
						widthInput.setText(newValue.replaceAll("[^\\d]", ""));
					}
				}

			});

			GridPane.setConstraints(widthLabel, 0, 2);
			root.getChildren().add(widthLabel);
			GridPane.setConstraints(widthInput, 1, 2);
			root.getChildren().add(widthInput);

			// Parameter of Set
			xInput.setText(DEFX);
			xInput.textProperty().addListener(new ChangeListener<String>() {

				@Override
				public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
					// TODO Auto-generated method stub
					if (!newValue.matches("^((\\-)?(\\d)?(\\.)?(\\d)+)$")) {
						xInput.setText(newValue.replaceAll("[^((\\-)?(\\d)?(\\.)?(\\d)+)$]", ""));
					}
				}

			});

			GridPane.setConstraints(xLabel, 0, 3);
			root.getChildren().add(xLabel);
			GridPane.setConstraints(xInput, 1, 3);
			root.getChildren().add(xInput);
			
			
			yInput.setText(DEFY);
			yInput.textProperty().addListener(new ChangeListener<String>() {

				@Override
				public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
					// TODO Auto-generated method stub
					if (!newValue.matches("^((\\-)?(\\d)?(\\.)?(\\d)+)$")) {
						yInput.setText(newValue.replaceAll("[^((\\-)?(\\d)?(\\.)?(\\d)+)$]", ""));
					}
				}

			});

			GridPane.setConstraints(yLabel, 0, 4);
			root.getChildren().add(yLabel);
			GridPane.setConstraints(yInput, 1, 4);
			root.getChildren().add(yInput);
			
			// Combobox Color Schema
			for (int i = 0; i < c.colorList.length; i++) {
				colorSchema.getItems().add("Color Schema " + i);
			}
			colorSchema.setValue(colorSchema.getItems().get(0));

			GridPane.setConstraints(colorSchemaLabel, 0, 5);
			root.getChildren().add(colorSchemaLabel);
			GridPane.setConstraints(colorSchema, 1, 5);
			root.getChildren().add(colorSchema);

			
			// Fractal Color Picker
			GridPane.setConstraints(colorLabel, 0, 6);
			root.getChildren().add(colorLabel);
			GridPane.setConstraints(colorPicker, 1, 6);
			root.getChildren().add(colorPicker);
			
			// Wrong Input Label
			
			error.setTextFill(Color.RED);
			GridPane.setConstraints(error, 0, 7);
			GridPane.setColumnSpan(error, 2);
			root.getChildren().add(error);

			// Create Buttons
			Button show = new Button("Show");

			show.setOnAction(new EventHandler<ActionEvent>() {
				public void handle(ActionEvent event) {
					boolean tmpHeight = heightInput.getText().equals("");
					boolean tmpWidth = widthInput.getText().equals("");
					boolean tmpX = xInput.getText().equals("");
					boolean tmpY = yInput.getText().equals("");

					if (tmpHeight || tmpWidth || tmpX || tmpY) {
						error.setText("Missing argument!");
					} else {
						error.setText("");

						height = Integer.parseInt(heightInput.getText());
						width = Integer.parseInt(widthInput.getText());

						schema = colorSchema.getValue();
						schema = schema.substring(schema.length() - 1, schema.length());

						VBox window = new VBox();
						window.setAlignment(Pos.CENTER);
						window.setMaxHeight(GridPane.USE_COMPUTED_SIZE);
						window.setMaxWidth(GridPane.USE_COMPUTED_SIZE);
						window.setMinHeight(GridPane.USE_COMPUTED_SIZE);
						window.setMinWidth(GridPane.USE_COMPUTED_SIZE);

						WritableImage image = new WritableImage(width, height);
						
						if(switchFunction.getValue().equals("Julia"))
							c.renderImageJulia(image, Integer.parseInt(schema), colorPicker.getValue(), Double.parseDouble(xInput.getText()), Double.parseDouble(yInput.getText()));
						else
							c.renderImage(image, Integer.parseInt(schema), colorPicker.getValue(), Double.parseDouble(xInput.getText()), Double.parseDouble(yInput.getText()));
						
						ImageView img = new ImageView(image);
						window.getChildren().add(img);

						Button save = new Button("Save");

						if(switchFunction.getValue().equals("Julia")){
							Slider slider = new Slider(-1.5, 1.5, Double.parseDouble(xInput.getText()));
							slider.valueProperty().addListener(new ChangeListener<Number>() {

								@Override
								public void changed(ObservableValue<? extends Number> observable, Number oldValue,
										Number newValue) {
									c.renderImageJulia(image, Integer.parseInt(schema), colorPicker.getValue(), (double)newValue, Double.parseDouble(yInput.getText()));
								}
								
							});
							window.getChildren().add(slider);
						}
							
						
						save.setOnAction(new EventHandler<ActionEvent>() {
							@Override
							public void handle(ActionEvent event) {
								// TODO Auto-generated method stub
								saveToFile(image);
							}
						});

						window.getChildren().add(save);

						Stage stage = new Stage();
						stage.setTitle(switchFunction.getValue());
						stage.setScene(new Scene(window));
						stage.setResizable(false);
						stage.show();
					}
				}
			});

			GridPane.setConstraints(show, 0, 8);
			GridPane.setColumnSpan(show, 2);
			root.getChildren().add(show);

			Scene scene = new Scene(root);
			primaryStage.setTitle("Mandelbrot and Julia Set");
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void saveToFile(WritableImage image) {
		File outputFile = new File("output.jpg.png");
		BufferedImage bImage = SwingFXUtils.fromFXImage(image, null);
		try {
			ImageIO.write(bImage, "png", outputFile);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
