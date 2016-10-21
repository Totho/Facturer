/*
 * Copyright (c) 2011, Pro JavaFX Authors
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 * 1. Redistributions of source code must retain the above copyright notice,
 *    this list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 * 3. Neither the name of JFXtras nor the names of its contributors may be used
 *    to endorse or promote products derived from this software without
 *    specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 *
 *  StarterAppMain.java - An example of using the classes in the 
 *  javafx.scene.control package.
 *
 *  Developed 2011 by James L. Weaver jim.weaver [at] javafxpert.com
 *  as a JavaFX SDK 2.0 example for the Pro JavaFX book.
 */
package projavafx.starterapp.ui;

import java.time.LocalDate;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Popup;
import javafx.stage.Stage;
import projavafx.starterapp.model.StarterAppModel;

public class StarterAppMain extends Application {

    StarterAppModel model = new StarterAppModel();
    Stage stage;

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(final Stage primaryStage) {
        stage = primaryStage;
        VBox topBox = new VBox(createMenus());
        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(createTabs());
        borderPane.setTop(topBox);
        Scene scene = new Scene(borderPane, 980, 600);
        scene.getStylesheets().add("/projavafx/starterapp/ui/starterApp.css");
        stage.setScene(scene);
        stage.setTitle("Facturador");
        stage.show();
    }

    MenuBar createMenus() {
        MenuItem itemNew = new MenuItem("New...", new ImageView(
                new Image(getClass().getResourceAsStream("images/paper.png"))));
        itemNew.setAccelerator(KeyCombination.keyCombination("Ctrl+N"));
        itemNew.setOnAction(e -> System.out.println(e.getEventType()
                + " occurred on MenuItem New"));
        MenuItem itemSave = new MenuItem("Save");
        Menu menuFile = new Menu("File");
        menuFile.getItems().addAll(itemNew, itemSave);
        MenuItem itemCut = new MenuItem("Cut");
        MenuItem itemCopy = new MenuItem("Copy");
        MenuItem itemPaste = new MenuItem("Paste");
        Menu menuEdit = new Menu("Edit");
        menuEdit.getItems().addAll(itemCut, itemCopy, itemPaste);
        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().addAll(menuFile, menuEdit);
        return menuBar;
    }



    TabPane createTabs() {


        Tab scrollTab = new Tab("ScrollPane/Miscellaneous");
        scrollTab.setContent(createScrollMiscDemoNode());
        scrollTab.setClosable(false);



        TabPane tabPane = new TabPane();
        tabPane.getTabs().addAll(

                scrollTab);

        return tabPane;
    }

   

   
    

    Node createScrollMiscDemoNode() {
        Button button = new Button("Button");
        button.setOnAction(e -> System.out.println(e.getEventType() + " occurred on Button"));
        final CheckBox checkBox = new CheckBox("CheckBox");
        checkBox.setOnAction(e -> {
            System.out.print(e.getEventType() + " occurred on CheckBox");
            System.out.print(", and selectedProperty is: ");
            System.out.println(checkBox.selectedProperty().getValue());
        });

        final ToggleGroup radioToggleGroup = new ToggleGroup();
        RadioButton radioButton1 = new RadioButton("RadioButton1");
        radioButton1.setToggleGroup(radioToggleGroup);
        RadioButton radioButton2 = new RadioButton("RadioButton2");
        radioButton2.setToggleGroup(radioToggleGroup);
        HBox radioBox = new HBox(10, radioButton1, radioButton2);

        Hyperlink link = new Hyperlink("Hyperlink");
        link.setOnAction(e -> System.out.println(e.getEventType() + " occurred on Hyperlink"));

        ChoiceBox choiceBox;
        choiceBox = new ChoiceBox(model.choiceBoxItems);
        choiceBox.getSelectionModel().selectFirst();
        choiceBox.getSelectionModel().selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> {
                    System.out.println(newValue + " chosen in ChoiceBox");
                });

        MenuItem menuA = new MenuItem("MenuItem A");
        menuA.setOnAction(e -> System.out.println(e.getEventType() + " occurred on Menu Item A"));
        MenuItem menuB = new MenuItem("MenuItem B");
        MenuButton menuButton = new MenuButton("MenuButton");
        menuButton.getItems().addAll(menuA, menuB);

        MenuItem splitMenuA = new MenuItem("MenuItem A");
        splitMenuA.setOnAction(e -> System.out.println(e.getEventType()
                + " occurred on Menu Item A"));
        MenuItem splitMenuB = new MenuItem("MenuItem B");
        SplitMenuButton splitMenuButton = new SplitMenuButton(splitMenuA, splitMenuB);
        splitMenuButton.setText("SplitMenuButton");
        splitMenuButton.setOnAction(e -> System.out.println(e.getEventType()
                + " occurred on SplitMenuButton"));

        final TextField textField = new TextField();
        textField.setPromptText("Enter user name");
        textField.setPrefColumnCount(16);
        textField.textProperty().addListener((ov, oldValue, newValue) -> {
            System.out.println("TextField text is: " + textField.getText());
        });

        final PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Enter password");
        passwordField.setPrefColumnCount(16);
        passwordField.focusedProperty().addListener((ov, oldValue, newValue) -> {
            if (!passwordField.isFocused()) {
                System.out.println("PasswordField text is: "
                        + passwordField.getText());
            }
        });

        final TextArea textArea = new TextArea();
        textArea.setPrefColumnCount(12);
        textArea.setPrefRowCount(4);
        textArea.focusedProperty().addListener((ov, oldValue, newValue) -> {
            if (!textArea.isFocused()) {
                System.out.println("TextArea text is: " + textArea.getText());
            }
        });

        LocalDate today = LocalDate.now();
        DatePicker datePicker = new DatePicker(today);
        datePicker.setOnAction(e -> System.out.println("Selected date: " + datePicker.getValue()));

        ColorPicker colorPicker = new ColorPicker(Color.BLUEVIOLET);
        colorPicker.setOnAction(e -> System.out.println("Selected color: " + colorPicker.getValue()));

        final ProgressIndicator progressIndicator = new ProgressIndicator();
        progressIndicator.setPrefWidth(200);
        progressIndicator.progressProperty().bind(model.rpm.divide(model.maxRpm));

        final Slider slider = new Slider(-1, model.maxRpm, 0);
        slider.setPrefWidth(200);
        slider.valueProperty().bindBidirectional(model.rpm);

        final ProgressBar progressBar = new ProgressBar();
        progressBar.setPrefWidth(200);
        progressBar.progressProperty().bind(model.kph.divide(model.maxKph));

        final ScrollBar scrollBar = new ScrollBar();
        scrollBar.setPrefWidth(200);
        scrollBar.setMin(-1);
        scrollBar.setMax(model.maxKph);
        scrollBar.valueProperty().bindBidirectional(model.kph);

        VBox variousControls = new VBox(20,
                button,
                checkBox,
                radioBox,
                link,
                choiceBox,
                menuButton,
                splitMenuButton,
                textField,
                passwordField,
                new HBox(10, new Label("TextArea:"), textArea),
                datePicker, colorPicker,
                progressIndicator, slider,
                progressBar, scrollBar);

        variousControls.setPadding(new Insets(10, 10, 10, 10));
        radioToggleGroup.selectToggle(radioToggleGroup.getToggles().get(0));
        radioToggleGroup.selectedToggleProperty().addListener((ov, oldValue, newValue) -> {
            RadioButton rb = ((RadioButton) radioToggleGroup.getSelectedToggle());
            if (rb != null) {
                System.out.println(rb.getText() + " selected");
            }
        });

        MenuItem contextA = new MenuItem("MenuItem A");
        contextA.setOnAction(e -> System.out.println(e.getEventType()
                + " occurred on Menu Item A"));
        MenuItem contextB = new MenuItem("MenuItem B");
        final ContextMenu contextMenu = new ContextMenu(contextA, contextB);

        ScrollPane scrollPane = new ScrollPane(variousControls);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPane.setOnMousePressed((MouseEvent me) -> {
            if (me.getButton() == MouseButton.SECONDARY) {
                contextMenu.show(stage, me.getScreenX(), me.getScreenY());
            }
        });

        return scrollPane;
    }

  
    Popup createAlertPopup(String text) {
        Popup alertPopup = new Popup();

        final Label htmlLabel = new Label(text);
        htmlLabel.setWrapText(true);
        htmlLabel.setMaxWidth(280);
        htmlLabel.setMaxHeight(140);

        Button okButton = new Button("OK");
        okButton.setOnAction(e -> alertPopup.hide());

        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(htmlLabel);
        borderPane.setBottom(okButton);

        Rectangle rectangle = new Rectangle(300, 200, Color.LIGHTBLUE);
        rectangle.setArcHeight(20);
        rectangle.setArcWidth(20);
        rectangle.setStroke(Color.GRAY);
        rectangle.setStrokeWidth(2);
        StackPane contentPane = new StackPane(rectangle, borderPane);

        alertPopup.getContent().add(contentPane);

        BorderPane.setAlignment(okButton, Pos.CENTER);
        BorderPane.setMargin(okButton, new Insets(10, 0, 10, 0));
        return alertPopup;
    }

}
