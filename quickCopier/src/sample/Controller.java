package sample;

import anoherMethods.AFileIO;
import anoherMethods.BindsIO;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.crypto.NoSuchPaddingException;
import java.awt.*;
import java.io.*;
import java.net.MalformedURLException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class Controller {
    public static String key;
    @FXML
    private Button startButton;
    private boolean wasLaunched;

    @FXML
    private Menu ImageCollector;

    @FXML
    private ColorPicker colorPicker1;

    @FXML
    private ColorPicker colorPicker2;


    @FXML
    private Menu ColorCollector;

    @FXML
    private TextField ColorTF;

    @FXML
    private ColorPicker colorPicker;

    @FXML
    private TextField ColorTF1;

    @FXML
    private TextField ColorTF2;

    @FXML
    private AnchorPane pane;

    private short SLines;

    private short ILines;

    private static void copyFileUsingStream(File source, File dest) throws IOException {
        InputStream is = null;
        OutputStream os = null;
        try {
            is = new FileInputStream(source);
            os = new FileOutputStream(dest);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = is.read(buffer)) > 0) {
                os.write(buffer, 0, length);
            }
        } finally {
            is.close();
            os.close();
        }
    }

    public static void changeToText(Button button, Text text, VBox box) {
        Platform.runLater(() -> {
            box.getChildren().remove(button);
            box.getChildren().add(text);
        });
    }

    public static void changeToButton(Button button, Text text, VBox box) {
        Platform.runLater(() -> {
            box.getChildren().remove(text);
            box.getChildren().add(button);
        });
    }

    public static String reColor(Color value) {
        return "#" + (format(value.getRed()) + format(value.getGreen()) + format(value.getBlue()) + format(value.getOpacity()))
                .toUpperCase();
    }

    public static String reColor(String value) {
        return "#" + value.substring(2);
    }

    private static String format(double val) {
        String in = Integer.toHexString((int) Math.round(val * 255));
        return in.length() == 1 ? "0" + in : in;
    }

    public void OnenMSMLC() throws IOException {
        File file = new File("MSMLCreator\\MSMLCreator.jar");
        Desktop.getDesktop().open(file);
    }

    public void SBA() throws IOException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, NoSuchPaddingException, InvalidKeyException {
        SoftCipher softCipher = new SoftCipher("utYRp#~t2WAfnj*~", SoftCipher.CLEAR_MODE);
        short lines = AFileIO.countStrings("pass.txt");
        if (wasLaunched) {
            pane = new AnchorPane();
        }
        File CustomAFile = new File("CustomA.txt");
        File CustomSFile = new File("CustomS.txt");
        File CustomXFile = new File("CustomX.txt");
        File CustomZFile = new File("CustomZ.txt");
        if (!wasLaunched) {
            pane.getChildren().remove(startButton);
        }
        DataCase[] cases = new DataCase[lines];
        String[][] buffer = BindsIO.toArray("pass.txt", AFileIO.countStrings("pass.txt"));
        String[] buffer2 = new String[2];
        ASN[] anss = new ASN[lines];
        short strings = 0;
        for (int i = 0; i != lines; i++) {
            short xVal;
            buffer2[0] = buffer[i][0];
            buffer2[1] = buffer[i][1];
            if (i % 7 == 0) {
                xVal = 0;
                strings = (short) (i / 7);
            } else {
                xVal = (short) ((i % 7));
                strings = (short) ((i - i % 7) / 7);

            }
            cases[i] = new DataCase(buffer2, xVal, strings);
            anss[i] = new ASN(cases[i], pane, softCipher, AFileIO.stringNumSelectline(CustomZFile, 1), AFileIO.stringNumSelectline(CustomZFile, 2));
        }
        int buf = AFileIO.countStrings("CustomS.txt");
        if (SLines != buf) {
            SLines = (short) buf;
            MenuItem[] items = new MenuItem[SLines - 1];
            for (int i = 0; i < items.length; i++) {
                String Color = AFileIO.stringNumSelectline(CustomSFile, i + 2);
                items[i] = new MenuItem(Color);
                items[i].setStyle("-fx-background-color : " + Color);
                items[i].setOnAction(event1 -> {
                    pane.setStyle("-fx-background-color : " + Color);

                    try {
                        FileWriter writer = new FileWriter("CustomA.txt");
                        writer.write("InterfaceColor : " + Color);
                        writer.flush();
                        writer.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                });
                ColorCollector.getItems().addAll(items[i]);
            }
        }
        int iBuf = AFileIO.countStrings("CustomX.txt");
        System.out.println("end");
        wasLaunched = true;
        if (ILines != iBuf) {
            ILines = (short) iBuf;
            MenuItem[] items = new MenuItem[ILines - 1];
            for (int i = 0; i < items.length; i++) {
                String image = AFileIO.stringNumSelectline(CustomXFile, i + 2);
                items[i] = new MenuItem(image);
                items[i].setOnAction(event1 -> {
                    try {
                        pane.setStyle("-fx-background-image : url(" + new File("images\\" + image).toURI().toURL() + ");");
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                    FileWriter writer = null;
                    try {
                        Scanner sc = new Scanner(CustomAFile);
                        writer = new FileWriter(CustomAFile);
                        writer.write("InterfaceImage : " + image);
                        writer.flush();
                        writer.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                });
                ImageCollector.getItems().addAll(items[i]);
            }
        }
    }

    public void getTFV(ActionEvent event) throws IOException {
        System.out.println("fgh");
        String buttonId = ((Button) event.getSource()).getId();
        File CustomZFile = new File("CustomZ.txt");
        String color;
        FileWriter writer;
        FileWriter writer2;
        String buf1;
        String buf2;
        switch (buttonId) {
            case "TFVB":
                File CustomSFile = new File("CustomS.txt");
                File CustomAFile = new File("CustomA.txt");
                color = ColorTF.getText();
                writer = new FileWriter(CustomAFile, true);
                writer.write("InterfaceColor : " + color + "ff\n");
                writer.flush();
                writer.close();
                FileWriter writer1 = new FileWriter(CustomSFile, true);
                writer1.write(color + "ff" + "\n");
                writer1.flush();
                writer1.close();
                break;
            case "TFVB1":
                buf1 = AFileIO.stringNumSelectline(CustomZFile, 1);
                buf2 = AFileIO.stringNumSelectline(CustomZFile, 2);
                color = ColorTF1.getText();
                writer = new FileWriter(CustomZFile);
                writer.write("-fx-background-color : " + color + "ff\n");
                if (!buf2.equals("noString")) writer.write("\n");
                writer.flush();
                writer.close();
                break;
            case "TFVB2":
                buf1 = AFileIO.stringNumSelectline(CustomZFile, 1);
                buf2 = AFileIO.stringNumSelectline(CustomZFile, 2);
                color = ColorTF2.getText();
                writer = new FileWriter(CustomZFile);
                if (!buf1.equals("noString")) writer.write("\n");
                writer.write("-fx-background-color : " + color + "ff");
                writer.flush();
                writer.close();
                break;
        }
    }

    public void getColorCP(ActionEvent event) throws IOException {
        System.out.println("fgh");
        String cpId = ((ColorPicker) event.getSource()).getId();
        File CustomZFile = new File("CustomZ.txt");
        String color;
        FileWriter writer;
        FileWriter writer2;
        String buf1;
        String buf2;
        switch (cpId) {
            case "colorPicker":
                File CustomSFile = new File("CustomS.txt");
                File CustomAFile = new File("CustomA.txt");
                color = reColor(colorPicker.getValue());
                writer = new FileWriter(CustomAFile, true);
                writer.write("InterfaceColor : " + color + "\n");
                writer.flush();
                writer.close();
                FileWriter writer1 = new FileWriter(CustomSFile, true);
                writer1.write(color + "\n");
                writer1.flush();
                writer1.close();
                break;
            case "colorPicker1":
                buf1 = AFileIO.stringNumSelectline(CustomZFile, 1);
                buf2 = AFileIO.stringNumSelectline(CustomZFile, 2);
                color = reColor(colorPicker1.getValue());
                writer = new FileWriter(CustomZFile);
                writer.write("-fx-background-color : " + color + "\n");
                if (!buf2.equals("noString")) writer.write("\n");
                writer.flush();
                writer.close();
                break;
            case "colorPicker2":
                buf1 = AFileIO.stringNumSelectline(CustomZFile, 1);
                buf2 = AFileIO.stringNumSelectline(CustomZFile, 2);
                color = reColor(colorPicker2.getValue());
                writer = new FileWriter(CustomZFile);
                if (buf1.equals("noString")) writer.write("\n");
                else writer.write(buf1 + "\n");
                writer.write("-fx-background-color : " + color);
                writer.flush();
                writer.close();
                break;
        }
    }

    public void getImage() throws IOException {
        Stage stage = new Stage();
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(stage);
        File f = new File("images\\" + file.getName());
        f.createNewFile();
        copyFileUsingStream(file, f);
        File CustomXFile = new File("CustomX.txt");
        String image = f.getName();
        FileWriter writer = new FileWriter(CustomXFile, true);
        writer.write(image + "\n");
        writer.flush();
        writer.close();
    }

    @FXML
    void initialize() throws FileNotFoundException, MalformedURLException {
        Scanner sc = new Scanner(new File("CustomA.txt"));
        String buffer = sc.nextLine();
        String custom = buffer.substring(17);
        if (buffer.startsWith("InterfaceImage")) {
            pane.setStyle("-fx-background-image : url(" + new File("images\\" + custom).toURI().toURL() + ");");
        } else pane.setStyle("-fx-background-color : " + custom);
    }
}

