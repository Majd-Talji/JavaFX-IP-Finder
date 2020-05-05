
import java.net.InetAddress;
import java.util.Scanner;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 *
 * @author Majd Talji <en.majd.talji@gmail.com>
 */
public class Main extends Application {

    Pane backgroundPane;
    ImageView imageView;
    Label label, labelResult, labelDevelopedBy;
    TextField textField;
    Button buttonFind;
    String styleRed = "-fx-background-color: red;";
    String styleBlack = "-fx-background-color: black;";

    @Override
    public void start(Stage stage) throws Exception {

        backgroundPane = new Pane();
        imageView = new ImageView(new Image(this.getClass().getResourceAsStream("/images/world-map.png")));
        label = new Label("Enter Domain");
        textField = new TextField();
        buttonFind = new Button("Get IP");
        labelResult = new Label("");
        labelDevelopedBy = new Label("Developer by Majd Talji");

        backgroundPane.setPrefSize(600, 340);
        imageView.setFitWidth(620);
        imageView.setFitHeight(360);
        label.setPrefSize(200, 30);
        textField.setPrefSize(200, 36);
        buttonFind.setPrefSize(200, 36);
        labelResult.setPrefSize(600, 30);
        labelDevelopedBy.setPrefSize(600, 30);

        label.setTranslateX(200);
        label.setTranslateY(75);
        textField.setTranslateX(200);
        textField.setTranslateY(115);
        buttonFind.setTranslateX(200);
        buttonFind.setTranslateY(165);
        labelResult.setTranslateX(0);
        labelResult.setTranslateY(225);
        labelDevelopedBy.setTranslateX(0);
        labelDevelopedBy.setTranslateY(300);

        label.setAlignment(Pos.CENTER);
        textField.setAlignment(Pos.CENTER);
        labelResult.setAlignment(Pos.CENTER);
        labelDevelopedBy.setAlignment(Pos.CENTER);

        label.setStyle(
                "-fx-font-family: Arial;"
                + "-fx-font-size: 20px;"
                + "-fx-text-fill: white;"
                + "-fx-font-weight: bold;"
        );

        textField.setStyle(
                "-fx-font-family: Arial;"
                + "-fx-font-size: 16px;"
                + "-fx-text-fill: lightgrey;"
                + "-fx-border-color: gray;"
                + "-fx-border-style: solid;"
                + "-fx-border-width: 2px;"
                + "-fx-background-color: black;"
        );

        buttonFind.setStyle(
                "-fx-font-family: Arial;"
                + "-fx-font-size: 16px;"
                + "-fx-text-fill: lightgrey;"
                + "-fx-border-color: gray;"
                + "-fx-border-style: solid;"
                + "-fx-border-width: 2px;"
                + "-fx-background-color: #333;"
                + "-fx-cursor: hand;"
        );

        labelResult.setStyle(
                "-fx-font-family: Arial;"
                + "-fx-font-size: 18px;"
                + "-fx-font-weight: bold;"
        );

        labelDevelopedBy.setStyle(
                "-fx-font-family: Arial;"
                + "-fx-font-size: 18px;"
                + "-fx-text-fill: Blue;"
                + "-fx-font-weight: bold;"
        );

        buttonFind.setOnAction((ActionEvent) -> {

            String url = textField.getText();

            if (!url.isEmpty()) {

                try {
                    InetAddress address = InetAddress.getByName(url);
                    String ip = address.getHostAddress();
                    labelResult.setText(ip);
                    labelResult.setTextFill(Color.GREEN);
                } catch (Exception e) {
                    labelResult.setText("Invalid URL or Network is Down");
                    labelResult.setTextFill(Color.RED);
                    setStyle(styleRed);
                }

            } else {
                labelResult.setText("Please enter a valid URL in the above field");
                labelResult.setTextFill(Color.RED);
                setStyle(styleRed);
            }

            textField.textProperty().addListener((observable) -> {
                labelResult.setText("");
                labelResult.setTextFill(Color.GREEN);
                setStyle(styleBlack);
            });

        });

        Group root = new Group();

        root.getChildren().add(backgroundPane);
        root.getChildren().add(imageView);
        root.getChildren().add(label);
        root.getChildren().add(textField);
        root.getChildren().add(buttonFind);
        root.getChildren().add(labelResult);
        root.getChildren().add(labelDevelopedBy);

        Scene scene = new Scene(root, 600, 340);
        stage.setTitle("IP Finder");
        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);

    }

    public void setStyle(String css) {
        textField.setStyle(textField.getStyle().replace("-fx-background-color: red;", css));
        textField.setStyle(textField.getStyle().replace("-fx-background-color: black;", css));
    }

    public static void main(String[] args) {
        launch(args);
    }

}
