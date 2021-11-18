package com.example.demo;

import com.example.demo.foodOrder.logic.classes.Gericht;
import com.example.demo.foodOrder.logic.classes.Speiseplan;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Paint;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class SpeiseplanController {

    @FXML private TextField tfNameMonA = new TextField();
    @FXML private TextField tfPreisMonA = new TextField();
    @FXML private ImageView imMonA = new ImageView();
    @FXML private Button btMonA = new Button();

    @FXML private TextField tfNameDinA = new TextField();
    @FXML private TextField tfPreisDinA = new TextField();
    @FXML private ImageView imDinA = new ImageView();
    @FXML private Button btDinA = new Button();

    @FXML private TextField tfNameMitA = new TextField();
    @FXML private TextField tfPreisMitA = new TextField();
    @FXML private ImageView imMitA = new ImageView();
    @FXML private Button btMitA = new Button();

    @FXML private TextField tfNameDonA = new TextField();
    @FXML private TextField tfPreisDonA = new TextField();
    @FXML private ImageView imDonA = new ImageView();
    @FXML private Button btDonA = new Button();

    @FXML private TextField tfNameFreA = new TextField();
    @FXML private TextField tfPreisFreA = new TextField();
    @FXML private ImageView imFreA = new ImageView();
    @FXML private Button btFreA = new Button();


    @FXML private TextField tfNameMonB = new TextField();
    @FXML private TextField tfPreisMonB = new TextField();
    @FXML private ImageView imMonB = new ImageView();
    @FXML private Button btMonB = new Button();

    @FXML private TextField tfNameDinB = new TextField();
    @FXML private TextField tfPreisDinB = new TextField();
    @FXML private ImageView imDinB = new ImageView();
    @FXML private Button btDinB = new Button();

    @FXML private TextField tfNameMitB = new TextField();
    @FXML private TextField tfPreisMitB = new TextField();
    @FXML private ImageView imMitB = new ImageView();
    @FXML private Button btMitB = new Button();

    @FXML private TextField tfNameDonB = new TextField();
    @FXML private TextField tfPreisDonB = new TextField();
    @FXML private ImageView imDonB = new ImageView();
    @FXML private Button btDonB = new Button();

    @FXML private TextField tfNameFreB = new TextField();
    @FXML private TextField tfPreisFreB = new TextField();
    @FXML private ImageView imFreB = new ImageView();
    @FXML private Button btFreB = new Button();


    @FXML private TextField tfNameMonC = new TextField();
    @FXML private TextField tfPreisMonC = new TextField();
    @FXML private ImageView imMonC = new ImageView();
    @FXML private Button btMonC = new Button();

    @FXML private TextField tfNameDinC = new TextField();
    @FXML private TextField tfPreisDinC = new TextField();
    @FXML private ImageView imDinC = new ImageView();
    @FXML private Button btDinC = new Button();

    @FXML private TextField tfNameMitC = new TextField();
    @FXML private TextField tfPreisMitC = new TextField();
    @FXML private ImageView imMitC = new ImageView();
    @FXML private Button btMitC = new Button();

    @FXML private TextField tfNameDonC = new TextField();
    @FXML private TextField tfPreisDonC = new TextField();
    @FXML private ImageView imDonC = new ImageView();
    @FXML private Button btDonC = new Button();

    @FXML private TextField tfNameFreC = new TextField();
    @FXML private TextField tfPreisFreC = new TextField();
    @FXML private ImageView imFreC = new ImageView();
    @FXML private Button btFreC = new Button();


    private final HashMap<Button, FXMLGerichtSpeicher> zuordnung = new HashMap<>();
    private FileChooser fileChooser = new FileChooser();
    private Image noImage;
    private Boolean validated = false;
    private ArrayList <Button> buttonList = new ArrayList<>();
    private Speiseplan speiseplan;

    @FXML private Button btAbbrechen = new Button();
    @FXML private Button btBestaetigen = new Button();
    @FXML private TextField tfKW;
    @FXML private Label lblFehler;

    static Speiseplan showDialog(Speiseplan sp) throws IOException {

        Stage stage = new Stage();

        FXMLLoader loader = new FXMLLoader(SpeiseplanApp.class.getResource("speiseplan.fxml"));
        Scene scene = new Scene(loader.load());
        SpeiseplanController controller = loader.getController();
        controller.initializeFields(sp);

        stage.setTitle("Speisepan Erstellen");
        stage.setScene(scene);
        stage.showAndWait();

        return controller.speiseplan;
    }

    @FXML
    public void initialize() throws IOException {

        //zuordnung.put(btMonA, new ArrayList<ArrayList>(Arrays.asList(new ArrayList<ImageView>(Arrays.asList(imMonA)))));
        String noImagePath = "C:\\JavaBilder\\noImage.jpg";
        File file = new File(noImagePath);
        FileInputStream input = new FileInputStream(file);
        noImage = new Image(input);

        buttonList = new ArrayList<>(Arrays.asList(
                btMonA, btDinA, btMitA, btDonA, btFreA,
                btMonB, btDinB, btMitB, btDonB, btFreB,
                btMonC, btDinC, btMitC, btDonC, btFreC));

        zuordnung.put(btMonA, new FXMLGerichtSpeicher (btMonA, tfNameMonA, tfPreisMonA, imMonA, noImagePath, "Mon"));
        zuordnung.put(btDinA, new FXMLGerichtSpeicher (btDinA, tfNameDinA, tfPreisDinA, imDinA, noImagePath,"Die"));
        zuordnung.put(btMitA, new FXMLGerichtSpeicher (btMitA, tfNameMitA, tfPreisMitA, imMitA, noImagePath,"Mit"));
        zuordnung.put(btDonA, new FXMLGerichtSpeicher (btDonA, tfNameDonA, tfPreisDonA, imDonA, noImagePath,"Don"));
        zuordnung.put(btFreA, new FXMLGerichtSpeicher (btFreA, tfNameFreA, tfPreisFreA, imFreA, noImagePath,"Fre"));

        zuordnung.put(btMonB, new FXMLGerichtSpeicher (btMonB, tfNameMonB, tfPreisMonB, imMonB, noImagePath,"Mon"));
        zuordnung.put(btDinB, new FXMLGerichtSpeicher (btDinB, tfNameDinB, tfPreisDinB, imDinB, noImagePath,"Die"));
        zuordnung.put(btMitB, new FXMLGerichtSpeicher (btMitB, tfNameMitB, tfPreisMitB, imMitB, noImagePath,"Mit"));
        zuordnung.put(btDonB, new FXMLGerichtSpeicher (btDonB, tfNameDonB, tfPreisDonB, imDonB, noImagePath,"Don"));
        zuordnung.put(btFreB, new FXMLGerichtSpeicher (btFreB, tfNameFreB, tfPreisFreB, imFreB, noImagePath,"Fre"));

        zuordnung.put(btMonC, new FXMLGerichtSpeicher (btMonC, tfNameMonC, tfPreisMonC, imMonC, noImagePath,"Mon"));
        zuordnung.put(btDinC, new FXMLGerichtSpeicher (btDinC, tfNameDinC, tfPreisDinC, imDinC, noImagePath,"Die"));
        zuordnung.put(btMitC, new FXMLGerichtSpeicher (btMitC, tfNameMitC, tfPreisMitC, imMitC, noImagePath,"Mit"));
        zuordnung.put(btDonC, new FXMLGerichtSpeicher (btDonC, tfNameDonC, tfPreisDonC, imDonC, noImagePath,"Don"));
        zuordnung.put(btFreC, new FXMLGerichtSpeicher (btFreC, tfNameFreC, tfPreisFreC, imFreC, noImagePath,"Fre"));


        for (Map.Entry<Button, FXMLGerichtSpeicher> set : zuordnung.entrySet()) {
            set.getValue().getIm().setImage(noImage);
            set.getValue().getTfName().setPromptText("Bezeichnung");
            set.getValue().getTfPreis().setPromptText("Preis");
        }

        tfKW.setPromptText("KW");
    }


    void initializeFields(Speiseplan sp) {

        this.speiseplan = sp;

        if(speiseplan != null) {
            ArrayList<Gericht> monList = speiseplan.getMon();
            ArrayList<Gericht> dieList = speiseplan.getDie();
            ArrayList<Gericht> mitList = speiseplan.getMit();
            ArrayList<Gericht> donList = speiseplan.getDon();
            ArrayList<Gericht> freList = speiseplan.getFre();

            ArrayList<FXMLGerichtSpeicher> monFXMLList = new ArrayList<>(Arrays.asList(
                    zuordnung.get(btMonA),
                    zuordnung.get(btMonB),
                    zuordnung.get(btMonC)
            ));
            ArrayList<FXMLGerichtSpeicher> dieFXMLList = new ArrayList<>(Arrays.asList(
                    zuordnung.get(btDinA),
                    zuordnung.get(btDinB),
                    zuordnung.get(btDinC)
            ));
            ArrayList<FXMLGerichtSpeicher> mitFXMLList = new ArrayList<>(Arrays.asList(
                    zuordnung.get(btMitA),
                    zuordnung.get(btMitB),
                    zuordnung.get(btMitC)
            ));
            ArrayList<FXMLGerichtSpeicher> donFXMLList = new ArrayList<>(Arrays.asList(
                    zuordnung.get(btDonA),
                    zuordnung.get(btDonB),
                    zuordnung.get(btDonC)
            ));
            ArrayList<FXMLGerichtSpeicher> freFXMLList = new ArrayList<>(Arrays.asList(
                    zuordnung.get(btFreA),
                    zuordnung.get(btFreB),
                    zuordnung.get(btFreC)
            ));


            addAllGerichteToTag(monList, monFXMLList);
            addAllGerichteToTag(dieList, dieFXMLList);
            addAllGerichteToTag(mitList, mitFXMLList);
            addAllGerichteToTag(donList, donFXMLList);
            addAllGerichteToTag(freList, freFXMLList);

        }


    }

    private void addAllGerichteToTag(ArrayList<Gericht> gerichtList, ArrayList<FXMLGerichtSpeicher> fxmlList) {
        if(gerichtList != null) {
            fxmlList.get(0).getTfName().setText(gerichtList.get(0).getBezeichnung());
            fxmlList.get(0).getTfPreis().setText(String.valueOf(gerichtList.get(0).getPreis()));
            if(gerichtList.get(0).getGerichtBildPath() != null) {
                setImage(gerichtList.get(0), fxmlList.get(0));
            }
            if(gerichtList.size() >= 2) {
                tfNameMonB.setText(gerichtList.get(1).getBezeichnung());
                tfPreisMonB.setText(String.valueOf(gerichtList.get(1).getPreis()));
                if(gerichtList.get(1).getGerichtBildPath() != null) {
                    setImage(gerichtList.get(1), fxmlList.get(1));
                }
                if(gerichtList.size() >= 3) {
                    tfNameMonC.setText(gerichtList.get(2).getBezeichnung());
                    tfPreisMonC.setText(String.valueOf(gerichtList.get(2).getPreis()));
                    if(gerichtList.get(2).getGerichtBildPath() != null) {
                        setImage(gerichtList.get(2), fxmlList.get(2));
                    }
                }
            }
        }
    }

    private void setImage(Gericht ger, FXMLGerichtSpeicher fxmlSpeicher) {
        try {
            File f = new File(ger.getGerichtBildPath());
            FileInputStream input = new FileInputStream(f);
            Image i = new Image(input);
            fxmlSpeicher.getIm().setImage(i);
            zuordnung.get(fxmlSpeicher.getBt()).setImPath(ger.getGerichtBildPath());
        } catch (Exception e) {
            System.out.println("Bild nicht gefunden. Verwende Standard Bild");
        }
    }

    @FXML
    protected void onResetButtonClick() {
        Stage stage = (Stage) btMonA.getScene().getWindow();
        Button b = (Button) stage.getScene().getFocusOwner();
        FXMLGerichtSpeicher fxmlGS = zuordnung.get(b);
        fxmlGS.getIm().setImage(noImage);
        fxmlGS.getTfName().setText("");
        fxmlGS.getTfPreis().setText("");
        if(validated) {
            validieren();
        }
    }

    /*@FXML
    protected void onBildButtonClick() throws FileNotFoundException {
        Stage stage = (Stage) btMonA.getScene().getWindow();

        Button b = (Button) stage.getScene().getFocusOwner();
        ImageView im = zuordnung.get(b).getIm();

        if(im.getImage() == null || im.getImage() == this.noImage) {
            File file = fileChooser.showOpenDialog(stage);
            FileInputStream input = new FileInputStream(file);
            Image image = new Image(input);
            im.setImage(image);
        } else {
            im.setImage(this.noImage);
        }
    }*/

    @FXML
    protected void onImageClick(MouseEvent event) throws FileNotFoundException {
        if (this.fileChooser == null) {
            File defaultPath = new File("C:\\JavaBilder");
            fileChooser.setInitialDirectory(defaultPath);
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("Media Files", "*.png", "*.jpg")
            );
        }

        Stage stage = (Stage) btMonA.getScene().getWindow();

        ImageView im = (ImageView) event.getSource();
        System.out.println(im);
        File file = fileChooser.showOpenDialog(stage);
        FileInputStream input = new FileInputStream(file);
        fileChooser.setInitialDirectory(new File(file.getParent()));
        Image image = new Image(input);
        im.setImage(image);

        for (Map.Entry<Button, FXMLGerichtSpeicher> set : zuordnung.entrySet()) {
            FXMLGerichtSpeicher gerichtSpeicher = set.getValue();
            if(gerichtSpeicher.getIm() == im) {
                zuordnung.get(gerichtSpeicher.getBt()).setImPath(file.getPath());
                break;
            }
        }

    }

    @FXML
    protected void onBtAbbrechenClick() {
        Stage stage = (Stage) btAbbrechen.getScene().getWindow();
        stage.close();
    }

    @FXML
    protected void onBtBestaetigenClick() {

        validierungsDaten validierungsDaten = validieren();

        if(validierungsDaten.isValide()) {

            int kw = -1;

            ArrayList<Gericht> monList = new ArrayList<>();
            ArrayList<Gericht> dieList = new ArrayList<>();
            ArrayList<Gericht> mitList = new ArrayList<>();
            ArrayList<Gericht> donList = new ArrayList<>();
            ArrayList<Gericht> freList = new ArrayList<>();

            if(!tfKW.getText().equals("")){
                try {
                    kw = Integer.parseInt(tfKW.getText().trim());
                    System.out.println("KW " + kw);
                } catch (Exception exception) {
                    kw = -1;
                    System.out.println("Unvorhergesehener Fehler mit der eingegebenen Kalenderwoche. Kalenderwoche wurde auf -1 gesetzt");
                }
            }
            for(Button b : buttonList) {
                FXMLGerichtSpeicher gerichteSpeicher = zuordnung.get(b);

                if(!gerichteSpeicher.getTfName().getText().equals("") && !gerichteSpeicher.getTfPreis().getText().equals("")) {
                    String name = gerichteSpeicher.getTfName().getText();
                    double preis = -1;
                    try {
                        preis = Double.parseDouble(gerichteSpeicher.getTfPreis().getText().replace(',', '.'));
                        System.out.println("reached");
                    } catch (Exception exception) {
                        System.out.println("Unvorhergesehener Fehler mit dem angegebenen Preis. Preis wurde auf -1 gesetzt");
                    }
                    Gericht gericht = new Gericht(name, preis, null);
                    System.out.print(gerichteSpeicher.getTfName().getText() + ", " + preis);

                    if(gerichteSpeicher.getIm().getImage() != this.noImage) {
                        gericht.setGerichtBildPath(gerichteSpeicher.getImPath());
                        System.out.print(", hat Bild");
                    }
                    if(gerichteSpeicher.getTag().equals("Mon")) {
                        monList.add(gericht);
                        System.out.println(", Mon");
                    } else if(gerichteSpeicher.getTag().equals("Die")) {
                        dieList.add(gericht);
                        System.out.println(", Die");
                    } else if(gerichteSpeicher.getTag().equals("Mit")) {
                        mitList.add(gericht);
                        System.out.println(", Mit");
                    } else if(gerichteSpeicher.getTag().equals("Don")) {
                        donList.add(gericht);
                        System.out.println(", Don");
                    } else if(gerichteSpeicher.getTag().equals("Fre")) {
                        freList.add(gericht);
                        System.out.println(", Fre");
                    }
                }
            }

            if(monList.isEmpty()) {
                monList = null;
            }
            if(dieList.isEmpty()) {
                dieList = null;
            }
            if(mitList.isEmpty()) {
                mitList = null;
            }
            if(donList.isEmpty()) {
                donList = null;
            }
            if(freList.isEmpty()) {
                freList = null;
            }

            speiseplan = new Speiseplan(kw, monList, dieList, mitList, donList, freList);

            Stage stage = (Stage) btBestaetigen.getScene().getWindow();
            stage.close();
            //evtl create PDF out of Speiseplan
        } else {
            validierungsDaten.getFocusedFehler().requestFocus();
        }

    }

    protected validierungsDaten validieren() {
        int fehlerCode = -1;
        TextField firstFehlerField = null;
        Paint farbeGelb = Paint.valueOf("yellow");
        Paint farbeGruen = Paint.valueOf("lightGreen");

        tfKW.setStyle("-fx-control-inner-background: #"+farbeGruen.toString().substring(2));

        try {
            Integer.valueOf(tfKW.getText().trim());
        } catch (Exception exception) {
            if(firstFehlerField == null) {
                firstFehlerField = tfKW;
                fehlerCode = 0;
            } else if(fehlerCode == 1 || fehlerCode == 2) {
                fehlerCode = 3;
            }
            tfKW.setStyle("-fx-control-inner-background: #"+farbeGelb.toString().substring(2));
        }

        for (Map.Entry<Button, FXMLGerichtSpeicher> set : zuordnung.entrySet()) {

            FXMLGerichtSpeicher gericht = set.getValue();

            gericht.getTfName().setStyle("-fx-control-inner-background: #"+farbeGruen.toString().substring(2));
            gericht.getTfPreis().setStyle("-fx-control-inner-background: #"+farbeGruen.toString().substring(2));

            String preisGerichtAsText = gericht.getTfPreis().getText().trim().replace(',', '.');
            String nameGericht = gericht.getTfName().getText().trim();

            boolean preisVorhanden = !preisGerichtAsText.equals("");
            boolean nameVorhanden = !nameGericht.equals("");



            if(preisVorhanden) {
                try {
                    Double.valueOf(preisGerichtAsText);
                } catch (Exception exception) {
                    if(firstFehlerField == null) {
                        firstFehlerField = gericht.getTfPreis();
                        fehlerCode = 2; // Fehlerhafter Preis
                    } else if(fehlerCode == 1 || fehlerCode == 0) {
                        fehlerCode = 3; // Mehrer Unterschiedliche Fehler
                    }

                    gericht.getTfPreis().setStyle("-fx-control-inner-background: #"+farbeGelb.toString().substring(2));
                }
            }

            if(preisVorhanden && !nameVorhanden) {
                if(firstFehlerField == null) {
                    firstFehlerField = gericht.getTfName();
                    fehlerCode = 1; // Fehlendes Feld
                } else if(fehlerCode == 2 || fehlerCode == 0) {
                    fehlerCode = 3; // Mehrer Unterschiedliche Fehler
                }
                gericht.getTfName().setStyle("-fx-control-inner-background: #"+farbeGelb.toString().substring(2));
            }

            if(!preisVorhanden && nameVorhanden) {
                if(firstFehlerField == null) {
                    firstFehlerField = gericht.getTfPreis();
                    fehlerCode = 1; // Fehlendes Feld
                } else if(fehlerCode == 2 || fehlerCode == 0) {
                    fehlerCode = 3; // Mehrer Unterschiedliche Fehler
                }
                gericht.getTfPreis().setStyle("-fx-control-inner-background: #"+farbeGelb.toString().substring(2));
            }
        }

        validated = true;

        if (fehlerCode == 0) {
            lblFehler.setText("Kalender Woche ist Ungültig");
        } else if(fehlerCode == 2) {
            lblFehler.setText("Preiseingaben sind Ungültig");
        } else if(fehlerCode == 1) {
            lblFehler.setText("Manche Gerichte sind nur Teilweise Ausgefüllt");
        } else if(fehlerCode == 3) {
            lblFehler.setText("Eingaben sind fehlerhaft und/oder unvollständig");
        } else {
            lblFehler.setText("Eingabe Erfolgreich Validiert");
        }

        if(fehlerCode == -1) {
            return new validierungsDaten(fehlerCode, firstFehlerField, true);
        } else {
            return new validierungsDaten(fehlerCode, firstFehlerField, false);
        }
    }

    @FXML
    void onTextFieldKeyReleased(KeyEvent event) {
        if(event.getCode() == KeyCode.ENTER && validated && validieren().getFocusedFehler() != null) {
            validieren().getFocusedFehler().requestFocus();
        } else if (validated) {
            validieren();
        } else if (event.getCode() == KeyCode.ENTER) {
            setFocusOnNextTextEmptyTextField(event);
        }
    }

    private void setFocusOnNextTextEmptyTextField(KeyEvent event) {

        FXMLGerichtSpeicher gerichtAkktuel = null;

        if(!(event.getSource() == tfKW)) {
            for (Map.Entry<Button, FXMLGerichtSpeicher> set : zuordnung.entrySet()) {
                if(set.getValue().getTfPreis() == event.getSource() || set.getValue().getTfName() == event.getSource()){
                    gerichtAkktuel = set.getValue();
                }
            }
        }

        if(gerichtAkktuel != null) {
            int buttonIndex = buttonList.indexOf(gerichtAkktuel.getBt());
            if(event.getSource() == gerichtAkktuel.getTfName()) {
                gerichtAkktuel.getTfPreis().requestFocus();
                gerichtAkktuel.getTfPreis().selectEnd();
                gerichtAkktuel.getTfPreis().deselect();
            } else if(event.getSource() == gerichtAkktuel.getTfPreis()) {
                int nextButtonIndex = buttonIndex + 1;
                    TextField nextTextField = new TextField();
                try {
                    Button nextButton = buttonList.get(nextButtonIndex);
                    nextTextField = zuordnung.get(nextButton).getTfName();
                } catch(Exception e) {
                    nextTextField = tfKW;
                }
                nextTextField.requestFocus();
                nextTextField.selectEnd();
                nextTextField.deselect();
            }
        } else {
            if(event.getSource() == tfKW) {
                tfNameMonA.requestFocus();
                tfNameMonA.selectEnd();
                tfNameMonA.deselect();
            } else if(event.getSource() == tfNameDinC) {
                tfPreisDinC.requestFocus();
                tfPreisDinC.selectEnd();
                tfPreisDinC.deselect();
            }
        }

    }
}
