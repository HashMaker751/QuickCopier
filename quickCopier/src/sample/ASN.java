package sample;

import javafx.scene.control.Button;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;

public class ASN extends Thread {
    public VBox box;
    public Button button;
    public Button button2;
    public Text text;
    public DataCase dataCase;
    AnchorPane pane;

    public ASN(DataCase dataCase, AnchorPane pane, SoftCipher softCipher, String customA, String customB) {
        this.dataCase = dataCase;
        this.pane = pane;
        box = initBox(box, dataCase);
        button = initButton(25, dataCase.caseName, button);
        button2 = initButton(20, "Показать", button2);
        try {

            if (!customA.equals(""))
                if (customA.substring(0, 4).equals("-fx-")) button.setStyle(customA);
            if (!customB.equals(""))
                if (customB.substring(0, 4).equals("-fx-")) button2.setStyle(customB);
        } catch (Exception e) {
            e.printStackTrace();
        }

        text = initText(dataCase.secretCopingInfo);
        button.setOnAction(event -> {
            Clipboard clipboard = Clipboard.getSystemClipboard();
            ClipboardContent content = new ClipboardContent();
            try {
                content.putString(softCipher.decodeString(dataCase.secretCopingInfo));
            } catch (BadPaddingException e) {
                e.printStackTrace();
            } catch (IllegalBlockSizeException e) {
                e.printStackTrace();
            }
            clipboard.setContent(content);
        });
        button2.setOnAction(event -> {
            try {
                text.setText(softCipher.decodeString(dataCase.secretCopingInfo));
            } catch (BadPaddingException e) {
                e.printStackTrace();
            } catch (IllegalBlockSizeException e) {
                e.printStackTrace();
            }
            Controller.changeToText(button2, text, box);
        });
        text.setOnMouseClicked(event -> {
            Controller.changeToButton(button2, text, box);
            text.setText(dataCase.secretCopingInfo);
        });
        box.setPrefWidth(100);
        box.setPrefHeight(50);
        box.getChildren().addAll(button, button2);
        pane.getChildren().addAll(box);
    }

    public Button initButton(double size, String text, Button buttonx) {
        buttonx = new Button(text);
        buttonx.setPrefHeight(size);
        buttonx.setPrefWidth(100);
        return buttonx;
    }

    public Text initText(String text) {
        Text text1 = new Text(text);
        text1.setTextAlignment(TextAlignment.CENTER);
        text1.setWrappingWidth(100);
        text1.setStyle("-fx-font: 9 System;");
        return text1;
    }

    public VBox initBox(VBox box, DataCase dataCase) {
        box = new VBox();
        box.setLayoutX(7.5 + (14 * dataCase.xPosition) + dataCase.xPosition * 100);
        box.setLayoutY(25 + 7.5 + dataCase.yPosition * 65);
        return box;
    }
}
