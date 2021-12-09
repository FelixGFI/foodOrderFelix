package com.example.demo.foodOrder.gui;

import com.example.demo.foodOrder.SpeiseplanApp;
import com.example.demo.foodOrder.logic.Gericht;
import com.example.demo.foodOrder.logic.Speiseplan;
import com.example.demo.foodOrder.output.PDFCreator;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Paint;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.net.MalformedURLException;
import java.util.*;

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




    private final HashMap<Button, GerichtControlsSpeicher> zuordnung = new HashMap<>();
    private FileChooser fileChooserImage;
    private FileChooser fileChooserDat;
    private Image noImage;
    private Boolean validated = false;
    private ArrayList <Button> buttonList = new ArrayList<>();
    private Speiseplan speiseplan;

    @FXML private Button btAbbrechen = new Button();
    @FXML private Button btSpeichern = new Button();
    @FXML private TextField tfKW;
    @FXML private Label lblFehler;
    @FXML private Button btLaden = new Button();

    public static Speiseplan showDialog(Speiseplan sp) throws IOException {

        Stage stage = new Stage();

        FXMLLoader loader = new FXMLLoader(SpeiseplanApp.class.getResource("speiseplan.fxml"));
        Scene scene = new Scene(loader.load());
        SpeiseplanController controller = loader.getController();
        controller.initializeFields(sp);

        stage.setTitle("Speiseplan Erstellen");
        stage.setScene(scene);
        stage.showAndWait();

        return controller.speiseplan;
    }

    @FXML
    public void initialize() throws IOException {

        //zuordnung.put(btMonA, new ArrayList<ArrayList>(Arrays.asList(new ArrayList<ImageView>(Arrays.asList(imMonA)))));
        String noImagePath = "src/javaBilder/addImage.png";
        File file = new File(noImagePath);
        FileInputStream input = new FileInputStream(file);
        noImage = new Image(input);

        buttonList = new ArrayList<>(Arrays.asList(
                btMonA, btDinA, btMitA, btDonA, btFreA,
                btMonB, btDinB, btMitB, btDonB, btFreB,
                btMonC, btDinC, btMitC, btDonC, btFreC));

        zuordnung.put(btMonA, new GerichtControlsSpeicher(btMonA, tfNameMonA, tfPreisMonA, imMonA, noImagePath, "Mon"));
        zuordnung.put(btDinA, new GerichtControlsSpeicher(btDinA, tfNameDinA, tfPreisDinA, imDinA, noImagePath,"Die"));
        zuordnung.put(btMitA, new GerichtControlsSpeicher(btMitA, tfNameMitA, tfPreisMitA, imMitA, noImagePath,"Mit"));
        zuordnung.put(btDonA, new GerichtControlsSpeicher(btDonA, tfNameDonA, tfPreisDonA, imDonA, noImagePath,"Don"));
        zuordnung.put(btFreA, new GerichtControlsSpeicher(btFreA, tfNameFreA, tfPreisFreA, imFreA, noImagePath,"Fre"));

        zuordnung.put(btMonB, new GerichtControlsSpeicher(btMonB, tfNameMonB, tfPreisMonB, imMonB, noImagePath,"Mon"));
        zuordnung.put(btDinB, new GerichtControlsSpeicher(btDinB, tfNameDinB, tfPreisDinB, imDinB, noImagePath,"Die"));
        zuordnung.put(btMitB, new GerichtControlsSpeicher(btMitB, tfNameMitB, tfPreisMitB, imMitB, noImagePath,"Mit"));
        zuordnung.put(btDonB, new GerichtControlsSpeicher(btDonB, tfNameDonB, tfPreisDonB, imDonB, noImagePath,"Don"));
        zuordnung.put(btFreB, new GerichtControlsSpeicher(btFreB, tfNameFreB, tfPreisFreB, imFreB, noImagePath,"Fre"));

        zuordnung.put(btMonC, new GerichtControlsSpeicher(btMonC, tfNameMonC, tfPreisMonC, imMonC, noImagePath,"Mon"));
        zuordnung.put(btDinC, new GerichtControlsSpeicher(btDinC, tfNameDinC, tfPreisDinC, imDinC, noImagePath,"Die"));
        zuordnung.put(btMitC, new GerichtControlsSpeicher(btMitC, tfNameMitC, tfPreisMitC, imMitC, noImagePath,"Mit"));
        zuordnung.put(btDonC, new GerichtControlsSpeicher(btDonC, tfNameDonC, tfPreisDonC, imDonC, noImagePath,"Don"));
        zuordnung.put(btFreC, new GerichtControlsSpeicher(btFreC, tfNameFreC, tfPreisFreC, imFreC, noImagePath,"Fre"));


        for (Map.Entry<Button, GerichtControlsSpeicher> set : zuordnung.entrySet()) {
            set.getValue().getIm().setImage(noImage);
            set.getValue().getTfName().setPromptText("Bezeichnung");
            set.getValue().getTfPreis().setPromptText("Preis");
        }

        tfKW.setPromptText("KW");
    }

    @FXML void onBtLadenClick() throws FileNotFoundException {
        if (this.fileChooserDat == null) {
            fileChooserDat = new FileChooser();
            File defaultPath = new File("src/generated");
            fileChooserDat.setInitialDirectory(defaultPath);
            fileChooserDat.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("Dat Files", "*.dat")
            );
        }

        Stage stage = (Stage) btLaden.getScene().getWindow();

        File file = fileChooserDat.showOpenDialog(stage);

        fileChooserDat.setInitialDirectory(new File(file.getParent()));

        try {
            FileInputStream fis = new FileInputStream(file.getPath());
            ObjectInputStream in = new ObjectInputStream(fis);
            this.speiseplan = (Speiseplan) in.readObject();
            in.close();
            fis.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        initializeFields(speiseplan);

    }

    void initializeFields(Speiseplan sp) {

        this.speiseplan = sp;

        if(speiseplan != null) {
            ArrayList<Gericht> monList = speiseplan.getMon();
            ArrayList<Gericht> dieList = speiseplan.getDie();
            ArrayList<Gericht> mitList = speiseplan.getMit();
            ArrayList<Gericht> donList = speiseplan.getDon();
            ArrayList<Gericht> freList = speiseplan.getFre();

            ArrayList<GerichtControlsSpeicher> monFXMLList = new ArrayList<>(Arrays.asList(
                    zuordnung.get(btMonA),
                    zuordnung.get(btMonB),
                    zuordnung.get(btMonC)
            ));
            ArrayList<GerichtControlsSpeicher> dieFXMLList = new ArrayList<>(Arrays.asList(
                    zuordnung.get(btDinA),
                    zuordnung.get(btDinB),
                    zuordnung.get(btDinC)
            ));
            ArrayList<GerichtControlsSpeicher> mitFXMLList = new ArrayList<>(Arrays.asList(
                    zuordnung.get(btMitA),
                    zuordnung.get(btMitB),
                    zuordnung.get(btMitC)
            ));
            ArrayList<GerichtControlsSpeicher> donFXMLList = new ArrayList<>(Arrays.asList(
                    zuordnung.get(btDonA),
                    zuordnung.get(btDonB),
                    zuordnung.get(btDonC)
            ));
            ArrayList<GerichtControlsSpeicher> freFXMLList = new ArrayList<>(Arrays.asList(
                    zuordnung.get(btFreA),
                    zuordnung.get(btFreB),
                    zuordnung.get(btFreC)
            ));


            addAllGerichteToTag(monList, monFXMLList);
            addAllGerichteToTag(dieList, dieFXMLList);
            addAllGerichteToTag(mitList, mitFXMLList);
            addAllGerichteToTag(donList, donFXMLList);
            addAllGerichteToTag(freList, freFXMLList);

            if(speiseplan.getKw() > 0 && speiseplan.getKw() <= 53) {
                try{
                    tfKW.setText(Integer.toString(speiseplan.getKw()));
                } catch (Exception e) {

                }
            } else {
                tfKW.setText("");
            }


        }


    }

    private void addAllGerichteToTag(ArrayList<Gericht> gerichtList, ArrayList<GerichtControlsSpeicher> fxmlList) {
        /**
         * Alle Felder Leeren
         */

        for (int i = 0; i < 3; i++) {
            fxmlList.get(i).getTfName().setText("");
            fxmlList.get(i).getTfPreis().setText("");
            fxmlList.get(i).getIm().setImage(noImage);
        }

        /**
         * Wenn Daten für diese Vorhanden sind, Felder mit diesen Ausfüllen
         */

        if(gerichtList != null) {

            for (int i = 0; i < gerichtList.size(); i++) {

                fxmlList.get(i).getTfName().setText(gerichtList.get(i).getBezeichnung());
                fxmlList.get(i).getTfPreis().setText(String.valueOf(gerichtList.get(i).getPreis()));
                if(gerichtList.get(i).getGerichtBildPath() != null) {
                    setImage(gerichtList.get(i), fxmlList.get(i));

                }
            }
        }
    }

    private void setImage(Gericht ger, GerichtControlsSpeicher fxmlSpeicher) {
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
        GerichtControlsSpeicher fxmlGS = zuordnung.get(b);
        fxmlGS.getIm().setImage(noImage);
        fxmlGS.getTfName().setText("");
        fxmlGS.getTfPreis().setText("");
        if(validated) {
            validieren();
        }
    }

    @FXML
    protected void onImageClick(MouseEvent event) throws FileNotFoundException {
        if (this.fileChooserImage == null) {
            fileChooserImage = new FileChooser();
            File defaultPath = new File("src/javaBilder");
            fileChooserImage.setInitialDirectory(defaultPath);
            fileChooserImage.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("Media Files", "*.png", "*.jpg")
            );
        }

        Stage stage = (Stage) btMonA.getScene().getWindow();

        ImageView im = (ImageView) event.getSource();
        File file = fileChooserImage.showOpenDialog(stage);
        FileInputStream input = new FileInputStream(file);
        fileChooserImage.setInitialDirectory(new File(file.getParent()));
        Image image = new Image(input);
        im.setImage(image);

        for (Map.Entry<Button, GerichtControlsSpeicher> set : zuordnung.entrySet()) {
            GerichtControlsSpeicher gerichtSpeicher = set.getValue();
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
    protected void onBtSpeichernClick() throws IOException {

        ValidierungsDaten validierungsDaten = validieren();

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
                } catch (Exception exception) {
                    kw = -1;
                    System.out.println("Unvorhergesehener Fehler mit der eingegebenen Kalenderwoche. Kalenderwoche wurde auf -1 gesetzt");
                }
            }
            for(Button b : buttonList) {
                GerichtControlsSpeicher gerichteSpeicher = zuordnung.get(b);

                if(!gerichteSpeicher.getTfName().getText().equals("") && !gerichteSpeicher.getTfPreis().getText().equals("")) {
                    String name = gerichteSpeicher.getTfName().getText();
                    double preis = -1;
                    try {
                        preis = Double.parseDouble(gerichteSpeicher.getTfPreis().getText().replace(',', '.'));
                    } catch (Exception exception) {
                        System.out.println("Unvorhergesehener Fehler mit dem angegebenen Preis. Preis wurde auf -1 gesetzt");
                    }
                    Gericht gericht = new Gericht(name, preis, null);

                    if(gerichteSpeicher.getIm().getImage() != this.noImage) {
                        gericht.setGerichtBildPath(gerichteSpeicher.getImPath());
                    }
                    if(gerichteSpeicher.getTag().equals("Mon")) {
                        monList.add(gericht);
                    } else if(gerichteSpeicher.getTag().equals("Die")) {
                        dieList.add(gericht);
                    } else if(gerichteSpeicher.getTag().equals("Mit")) {
                        mitList.add(gericht);
                    } else if(gerichteSpeicher.getTag().equals("Don")) {
                        donList.add(gericht);
                    } else if(gerichteSpeicher.getTag().equals("Fre")) {
                        freList.add(gericht);
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

            this.speiseplan = new Speiseplan(kw, monList, dieList, mitList, donList, freList);

            speichernSpeiseplanDat(this.speiseplan);

            abfragePDFSpeichern(speiseplan);
        } else {
            validierungsDaten.getFocusedFehler().requestFocus();
        }



    }

    private void abfragePDFSpeichern(Speiseplan sp) throws IOException {
        Alert a = new Alert(Alert.AlertType.NONE);
        a.setHeaderText("PDF Erstellen?");
        a.setContentText("Möchten Sie den Speiseplan auch als PDF Speichern?");

        ButtonType ja = new ButtonType("Ja");
        ButtonType nein = new ButtonType("Nein");

        a.getButtonTypes().clear();
        a.getButtonTypes().add(ja);
        a.getButtonTypes().add(nein);

        Optional<ButtonType> option = a.showAndWait();

        if(option.get() == ja) {
            speichernSpeiseplanPDF(sp);
        }

        Stage stage = (Stage) btSpeichern.getScene().getWindow();
        stage.close();
        //evtl create PDF out of Speiseplan
    }

    private void speichernSpeiseplanPDF(Speiseplan sp) throws IOException {

        FileChooser fileChooserPDF = new FileChooser();
        File defaultPath = new File("src/generated");
        fileChooserPDF.setInitialDirectory(defaultPath);
        fileChooserPDF.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("PDF Files", "*.pdf"));

        Stage stage = (Stage) btLaden.getScene().getWindow();

        File file = fileChooserPDF.showSaveDialog(stage);
        file.createNewFile();
        fileChooserPDF.setInitialDirectory(new File(file.getParent()));

        PDFCreator.createSpeiseplanPDF(sp, file);

        /**
         * somehow opens PDF in Browser
         */

        if (file.toString().endsWith(".pdf")) {
            Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + file);
        }
    }

    private void speichernSpeiseplanDat(Speiseplan sp) throws IOException {
        if (this.fileChooserDat == null) {
            fileChooserDat = new FileChooser();
            File defaultPath = new File("src/generated");
            fileChooserDat.setInitialDirectory(defaultPath);
            fileChooserDat.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("Dat Files", "*.dat")
            );
        }
        Stage stage = (Stage) btLaden.getScene().getWindow();

        File file = fileChooserDat.showSaveDialog(stage);
        file.createNewFile();
        fileChooserDat.setInitialDirectory(new File(file.getParent()));

        try {
            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream out = new ObjectOutputStream(fos);
            out.writeObject(sp);
            out.close();
            fos.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    protected ValidierungsDaten validieren() {
        int fehlerCode = -1;
        TextField firstFehlerField = null;
        Paint farbeGelb = Paint.valueOf("yellow");
        Paint farbeGruen = Paint.valueOf("lightGreen");

        int anzahlFehler = 0;

        /**
         * Validierung der Kalenderwoche
         */
        tfKW.setStyle("-fx-control-inner-background: #"+farbeGruen.toString().substring(2));

        int kw;

        try {
            kw = Integer.valueOf(tfKW.getText().trim());
        } catch (Exception exception) {
            kw = -1;
        }
        if(kw <= 0 || kw > 53) {
            anzahlFehler++;
            if(firstFehlerField == null) {
                firstFehlerField = tfKW;
                fehlerCode = 0;
            } else if(fehlerCode == 1 || fehlerCode == 2) {
                fehlerCode = 3;
            }
            tfKW.setStyle("-fx-control-inner-background: #"+farbeGelb.toString().substring(2));
        }

        /**
         * Validierung der mehrfach Vorhandenen Felder (Gericht Bezeichnung, Preis)
         */

        for (Map.Entry<Button, GerichtControlsSpeicher> set : zuordnung.entrySet()) {

            GerichtControlsSpeicher gericht = set.getValue();

            gericht.getTfName().setStyle("-fx-control-inner-background: #"+farbeGruen.toString().substring(2));
            gericht.getTfPreis().setStyle("-fx-control-inner-background: #"+farbeGruen.toString().substring(2));

            String preisGerichtAsText = gericht.getTfPreis().getText().trim().replace(',', '.');
            String nameGericht = gericht.getTfName().getText().trim();

            boolean preisVorhanden = !preisGerichtAsText.equals("");
            boolean nameVorhanden = !nameGericht.equals("");

            /**
             * Validierung des Preis Feldes
             */

            if(preisVorhanden) {
                try {
                    Double.valueOf(preisGerichtAsText);
                } catch (Exception exception) {
                    anzahlFehler++;
                    if(firstFehlerField == null) {
                        firstFehlerField = gericht.getTfPreis();
                        fehlerCode = 2; // Fehlerhafter Preis
                    } else if(fehlerCode == 1 || fehlerCode == 0) {
                        fehlerCode = 3; // Mehrer Unterschiedliche Fehler
                    }

                    gericht.getTfPreis().setStyle("-fx-control-inner-background: #"+farbeGelb.toString().substring(2));
                }
            }

            /**
             * Validuerung des Bezeichung Feldes
             */

            if(nameVorhanden) {
                try {
                    Double.valueOf(nameGericht);
                    anzahlFehler++;
                    if(firstFehlerField == null) {
                        firstFehlerField = gericht.getTfName();
                        fehlerCode = 4; // Fehlerhafte Bezeichnung
                    } else if(fehlerCode == 1 || fehlerCode == 0 || fehlerCode == 2) {
                        fehlerCode = 3; // Mehrer Unterschiedliche Fehler
                    }

                    gericht.getTfName().setStyle("-fx-control-inner-background: #"+farbeGelb.toString().substring(2));
                } catch (Exception exception) {

                }
            }

            /**
             * Validierung ob zu jedem eingegebenen Preis auch eine Bezeichung eingegeben wurde,
             * bzw. zu jeder eingegebenen Bezeichung auch ein Preis eingegeben wurde.
             */

            if(preisVorhanden && !nameVorhanden) {
                anzahlFehler++;
                if(firstFehlerField == null) {
                    firstFehlerField = gericht.getTfName();
                    fehlerCode = 1; // Fehlendes Feld
                } else if(fehlerCode == 2 || fehlerCode == 0) {
                    fehlerCode = 3; // Mehrer Unterschiedliche Fehler
                }
                gericht.getTfName().setStyle("-fx-control-inner-background: #"+farbeGelb.toString().substring(2));
            }

            if(!preisVorhanden && nameVorhanden) {
                anzahlFehler++;
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
            lblFehler.setText("Kalenderwoche ist ungültig.");
        } else if(fehlerCode == 2) {
            lblFehler.setText("Preiseingaben sind ungültig: " + anzahlFehler);
        } else if(fehlerCode == 4) {
            lblFehler.setText("Bezeichnungen sind ungültig: " + anzahlFehler);
        } else if(fehlerCode == 1) {
            lblFehler.setText("Gerichte sind unvollständig ausgefüllt: " + anzahlFehler);
        } else if(fehlerCode == 3) {
            lblFehler.setText("Eingaben sind fehlerhaft und/oder unvollständig: " + anzahlFehler);
        } else {
            lblFehler.setText("Eingabe erfolgreich validiert!");
        }

        if(fehlerCode == -1) {
            return new ValidierungsDaten(fehlerCode, firstFehlerField, true);
        } else {
            return new ValidierungsDaten(fehlerCode, firstFehlerField, false);
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

        GerichtControlsSpeicher gerichtAkktuel = null;

        if(!(event.getSource() == tfKW)) {
            for (Map.Entry<Button, GerichtControlsSpeicher> set : zuordnung.entrySet()) {
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
