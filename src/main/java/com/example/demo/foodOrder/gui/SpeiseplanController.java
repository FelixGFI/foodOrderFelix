package com.example.demo.foodOrder.gui;

import com.example.demo.foodOrder.SpeiseplanApp;
import com.example.demo.foodOrder.logic.Gericht;
import com.example.demo.foodOrder.logic.Speiseplan;
import com.example.demo.foodOrder.output.PDFCreator;
import javafx.event.EventHandler;
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
import javafx.stage.WindowEvent;

import java.io.*;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
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
    private boolean kalenderwocheAutomatischBerrechnen = true;
    private String platzhalterDatumString = "0000-00-00";

    @FXML private Button btAbbrechen = new Button();
    @FXML private Button btSpeichern = new Button();
    @FXML private TextField tfKW;
    @FXML private Label lblFehler;
    @FXML private Button btLaden = new Button();

    @FXML private Label lblDatMon = new Label();
    @FXML private Label lblDatDin = new Label();
    @FXML private Label lblDatMit = new Label();
    @FXML private Label lblDatDon = new Label();
    @FXML private Label lblDatFre = new Label();

    /**
     * erhält initial Speiseplan. starte Controller und Setzt Fenster auf.
     * Ruft Methode initializeFields(sp) zum einfügen der Daten des übergebenen anfangs Speiseplans in die GUI auf.
     * handeld WindowEvent wenn das Fenster per Kreuzchen Geschlossen wird. Wenn bedinungen erfüllt sind ruft Methode zum Anzeigen von Warnung vor dem Schließen auf.
     * @param sp
     * @return
     * @throws IOException
     */

    public static Speiseplan showDialog(Speiseplan sp) throws IOException {

        Stage stage = new Stage();

        FXMLLoader loader = new FXMLLoader(SpeiseplanApp.class.getResource("speiseplan.fxml"));
        Scene scene = new Scene(loader.load());
        SpeiseplanController controller = loader.getController();
        controller.initializeFields(sp);

        stage.setTitle("Speiseplan Erstellen");
        stage.setScene(scene);

        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            public void handle(WindowEvent we) {

                if(controller.mindestensEineEingabeVorhanden()) {
                    Alert a = controller.createWarningAlertBeforeCloseWindow();

                    Optional<ButtonType> option = a.showAndWait();

                    if(option.get() == a.getButtonTypes().get(0)) {
                        stage.close();
                    } else if(option.get() == a.getButtonTypes().get(1)) {
                        we.consume();
                    }
                }

            }
        });

        stage.showAndWait();


        return controller.speiseplan;
    }

    /**
     * initialisiert den String noImagePath, welcher der Pfad zu dem Bild ist angeeziegt wird wenn (noch) kein bild vorhanden ist.
     * füllt die ArrayList<Button> buttonList mit allen FXML Buttons die zu einem Gericht gehören. (Reset Button) in der Reihnefolge von Gericht a nach C und von Montag nach Freitag
     * Die FXML Elemente der einzelnen Gerichte später in der Reichtigen Reihenfolge abrufen zu können
     * füllt die Hashmaop zuordnung mit Elementen der Klasse GerichtControlsSpeicher. In einer Solchen Klasse sind alle FXML Elemente die einem Gericht zugehörig sind abgelegt,
     * sowie der Zugehörige Tag. ein Jeder GerichtControlsSpeicehr wird dem Passenden FXML Button zur Identifizierung zugeordnet.
     * Diese Hashmap dient dazu die einzelnen FXML Elemente Später einander Zuordnen zu können.
     * Anschließend wird durch die gesamte Hashmap iterriert. FÜr jedes Gericht erhält beziechnung und Preis Textfelder entsprechende Textprompts und die jeweilige Image View
     * erhält das Defaultbild NoImage. Das Textfeld tfKW erhält einen Entsprechenden Textprompt
     * @throws IOException
     */
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

    /**
     * Ließt den Ihhalt des Textfeldes tfKW aus und Überprüft ob daraus ein Valider Integer gebildet werden kann.
     * Ist dies der Fall und der gebildte Intager int ausgeleseneKalenderwoche eine Mögliche Kalenderwoche so werden für diese Kalenderwoche
     * die Methode insertTagesdaten und getTagesDatenArray Aufgerufen. diese Methoden ErmittelnDiese eine Arraylist mit den Kalenderdaten von Montag bis Freitag
     * für diese Kalenderwoche und fügen diese Daten in die Entsprechenden Label in der GUI ein.
     * Ist ausgeleseneKalenderwoche keine Gültige Kalnderwoche so wird mittels des bei start des Programms auf true gesetzen Booleans kalenderwocheAutomatischBerechnen
     * festgestellt ob die Kalenderwoche automatisch Berechnet werden soll. Ist der Boolean true wird er auf Fals gesetzt und anschließend die Akktuelle Kalenderwoche Berechnet
     * und für diese Die Akktuellen Daten in die Tages Daten Label eingefügt. Ist der Boolean fals wird besagter Programmteil übersprungen.
     * Ist ausgelsene Kalenderwoche keine Mögliche Kalenderwoche und kalenderwocheAutomatischBerechen auf fals wird else die Methode insertTagesPlatzhlater ausgeführt.
     * diese Fügt ein Platzhalterdatum in die Tages Datums label in der GUI ein.
     *
     * Issues: jede Kalenderwoche zwischen 1 und 53 wird als mögliche Kalenderwoche Akzeptiert auch wenn das akktuelle jahr keine kalenderwoche 53 hat.
     *
     */
    private void setKWundTagesDatenFelder() {

        int ausgeleseneKalenderwoche;

        try {
            ausgeleseneKalenderwoche = Integer.valueOf(tfKW.getText().trim());
        } catch (Exception exception) {
            ausgeleseneKalenderwoche = -1;
        }

        if(ausgeleseneKalenderwoche > 0 && ausgeleseneKalenderwoche <= 53) {
            insertTagesdaten(getTagesDatenArray(ausgeleseneKalenderwoche));
        } else if(kalenderwocheAutomatischBerrechnen){
            kalenderwocheAutomatischBerrechnen = false;
            int kalenderWoche = getKalenderwoche(LocalDate.now());
            tfKW.setText(Integer.toString(kalenderWoche));
            insertTagesdaten(getTagesDatenArray(kalenderWoche));
            tfKW.requestFocus();
            tfKW.selectAll();
        } else {
            insertTagesPlatzhalter();
        }
    }

    /**
     * Erhält eine ArrayList<LocalDate> tagesDatenListe welche die einzufügenden daten von Montag bis Freitag in dieser Reihnefolge enthält.
     * schreibt die Daten aus der Arraylist als String in die entsprechenden FXML label
     * @param tagesDatenListe
     */

    private void insertTagesdaten(ArrayList<LocalDate> tagesDatenListe) {
        lblDatMon.setText(tagesDatenListe.get(0).toString());
        lblDatDin.setText(tagesDatenListe.get(1).toString());
        lblDatMit.setText(tagesDatenListe.get(2).toString());
        lblDatDon.setText(tagesDatenListe.get(3).toString());
        lblDatFre.setText(tagesDatenListe.get(4).toString());
    }

    /**
     * schreibt einen Definierten Plathalterstring in die FXML Label für die Tages Daten.
     */
    private void insertTagesPlatzhalter() {
        lblDatMon.setText(platzhalterDatumString);
        lblDatDin.setText(platzhalterDatumString);
        lblDatMit.setText(platzhalterDatumString);
        lblDatDon.setText(platzhalterDatumString);
        lblDatFre.setText(platzhalterDatumString);
    }

    /**
     * ermittelt anhand des int kalenderwoche das Datum zu den Tagen Montag bis Freitag in besagter Kalenderwoche.
     * returns eine ArrayList mit den ermittelten LocalDates von Montag bis Freitag der Gegebenen Kalenderwoche
     * Das Vorhandenseit der angegebenen Kalenderwoche wird nicht Validiert.
     * @param kalenderwoche
     * @return
     */
    private ArrayList<LocalDate> getTagesDatenArray(int kalenderwoche) {
        ArrayList<LocalDate> tagesDaten = new ArrayList<>();
        LocalDate ersterTagDesJahres = LocalDate.of(LocalDate.now().getYear(), Month.JANUARY, 1);
        LocalDate ersterWochenTag = ersterTagDesJahres;
        switch (ersterTagDesJahres.getDayOfWeek()) {
            case MONDAY -> ersterWochenTag = ersterTagDesJahres;
            case TUESDAY -> ersterWochenTag = ersterWochenTag.minusDays(1);
            case WEDNESDAY -> ersterWochenTag = ersterWochenTag.minusDays(2);
            case THURSDAY -> ersterWochenTag = ersterWochenTag.minusDays(3);
            case FRIDAY -> ersterWochenTag = ersterWochenTag.plusDays(3);
            case SATURDAY -> ersterWochenTag = ersterWochenTag.plusDays(2);
            case SUNDAY -> ersterWochenTag = ersterWochenTag.plusDays(1);
            default -> throw new IllegalStateException("Unexpected value: " + ersterTagDesJahres.getDayOfWeek());
        }

        for (int i = 0; i < kalenderwoche - 1; i++) {
            ersterWochenTag = ersterWochenTag.plusDays(7);

        }
        tagesDaten.add(ersterWochenTag);
        tagesDaten.add(ersterWochenTag.plusDays(1));
        tagesDaten.add(ersterWochenTag.plusDays(2));
        tagesDaten.add(ersterWochenTag.plusDays(3));
        tagesDaten.add(ersterWochenTag.plusDays(4));

        return tagesDaten;
    }

    /**
     * Erhält ein LocalDate Datum eines Tags. Berechnet die Kalenderwoche in der sich besagter Tag befindet als Integer.
     * gibt die Kalenderwoche als Integer Zurück.
     * @param date
     * @return
     */
    private int getKalenderwoche(LocalDate date) {

        int umrechnung;
        int year = date.getYear();
        LocalDate ersterTagDesJahres = LocalDate.of(year, Month.JANUARY, 1);
        //   LocalDate first=date.getYear();
        switch (ersterTagDesJahres.getDayOfWeek()) {
            case MONDAY -> umrechnung = 0;
            case TUESDAY -> umrechnung = 1;
            case WEDNESDAY -> umrechnung = 2;
            case THURSDAY -> umrechnung = 3;
            case FRIDAY -> umrechnung = -3;
            case SATURDAY -> umrechnung = -2;
            case SUNDAY -> umrechnung = -1;
            default -> throw new IllegalStateException("Unexpected value: " + ersterTagDesJahres.getDayOfWeek());
        }
        int daysCW = (date.getDayOfYear()) + umrechnung;
        int kalenderWoche = daysCW / 7;
        kalenderWoche++;

        if(date.getDayOfWeek() == DayOfWeek.SUNDAY) {
            kalenderWoche--;
        }

        return kalenderWoche;
    }

    /**
     * Aktiviert onClick auf den Butten btLaden.
     * öffnet einen FileChooser der nur Datein mit der endung .dat anzeigt.
     * Lädt ein file wenn eines Ausgewählt wurde mit hilfe der Serialisierung.
     * Wenn das File ein Gültiges Speiseplan Object enthält erstellt die Methode daraus einen Speiseplan und übergibt diesen der entsprechendne Methode
     * um Ihn in der GUI korrekt anziegen zu lassen
     *
     * Issues: es ist möglich mittels eingabe in der Suchzeile des durch den Fileschooser geöffeneten Fensters ein file welches nicht die endung .dat
     * hat auszuwählen
     * die MEthode wirft eine Exception wenn das .dat File keinen Gültigen Speiseplan enthält
     * @throws FileNotFoundException
     */

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

    /**
     * erhält einen Speiseplan sp.
     * füllt alle Felder der GUI mit den im Speiseplan enthaltenen Gerichten sofern vorhanden.
     * hierzu erzeugt dei Methode für jeden Tag eine ArrayList<Gericht> mit allen FÜr den Tag im speiseplan object vorhanden Gerichten.
     * erzeugt ausserdem für jeden Tag mit Hilfe der Hashmap Zuordnung eine ArrayList<GerichtControlsSpeicher> mit den zu dem Tag gehörenden GerichtControlsSpeicher objekten.
     * ruft dan für jeden Tag eine Methode auf die aus den Zwei listen die Gerichte am Entsprechenden Tag befüllt.
     * fügt im Speiseplan gespeicherte Kalenderwoche in das Textfeld tfKW ein. +
     * ruft die Methode setKWundTagesDatenFelder() zur Aktuellisierung der Tagesdaten auf
     *
     * Issues: Speisepläne können theoretisch mehr als 3 Gerichte Pro Tag enthalten. Da die Gui allerdigns nur maximal 3 Pro Tag anzeigt werden überzhälige gerichte quasi verworfen.
     * @param sp
     */
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
                    e.printStackTrace();
                }
            } else {

            }
        }
        setKWundTagesDatenFelder();
    }

    /**
     * Setzt alle Felder auf Ausgangszustand zurück.
     * Fügt Anschließend solange Daten Vorhanden sind (Maximal Drei Gerichte) alle Gerichte aus der gerichtList in die korrekten FXML Elemente aus der fxmlList ein.
     * Prüft ob für ein Gericht ein Image eingefügt werden soll. Wenn ja Ruft Methode setImage auf
     * @param gerichtList
     * @param fxmlList
     */
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
         * Wenn Daten für diese Vorhanden sind, Felder mit diesen ausfüllen.
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

    /**
     * erstellte aus ImagePath String aus übergebenem Gericht Object ein Image Object und Fügte dieses in die,
     * im übergebenen GerichtControlsSPeicher obeject enthaltene, ImageView ein.
     * Tut nichts wenn im Gericht kein ImagePath vorhanden ist bzw. wenn das verlinkte File nicht gefunden wurde.
     * Vorherige Validierung des Vorhandenseins eines Korrekten ImagePath empfhohlen.
     *
     * @param ger
     * @param fxmlSpeicher
     */
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

    /**
     * Wird Ausgelöst wenn auf einen der für jedes Gericht einmal vorhandenen Reset Buttons gedrückt wird.
     * ließt aus welcher Button Gedrückt wurde. Wählt anschleißend anhand des ermittelten Buttons das zugehörige GerichtControlsSpeicerh Object
     * aus der HashMap Zuordnung und setzt die darin enthaltenen FXML Elemente in den Ausgangszustand zurück.
     * Wenn anhand des boolen validated festgestellt wird das bereits validiert wurde wird nun durch den aufruf der methode validieren() erneut validiert
     */

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

    /**
     * wählt ein Image für ein Gericht aus.
     * wird ausgeführt wenn auf eines der Imageviews in der GUI eine Mausklick ausgeführt wird.
     * wenn noch nicht passiert initialisiert den filechooser so das er nur .png und .jpg datein anzeigt.
     * Das Gecklichte ImageView wird ermittelt. Es wird anschließend durch die Hashmap Zuordnung Iteriert und auf Übereinstimmungen geprüft.
     * Wenn eine korrektes File ausgewählt wurde wird dieses in die ermitelte, angeklickte Imageview engefügt.
     * @param event
     * @throws FileNotFoundException
     */

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
            GerichtControlsSpeicher gerichtControlsSpeicher = set.getValue();
            if(gerichtControlsSpeicher.getIm() == im) {
                zuordnung.get(gerichtControlsSpeicher.getBt()).setImPath(file.getPath());
                break;
            }
        }

    }

    /**
     * wird ausgeführt wenn der Button btAbbrechen gecklickt wird.
     * wenn mindestens eine Nutzereingabe vorhanden ist wird ein Alert an den Nutzer Ausgegeben.
     * In diesem muss dieser Bestätigen das er die eingegegebnen Daten wirklich verwerfen will.
     * Das Programm wird geschlossen wenn eingaben Gemacht wurden und der Nutzer deren Verwerfen Bestätigt oder Wenn keine Nutzereingaben gemacht wurden
     * das Programm bleibt offen wenn eingaben Gemacht wurdn und der Nutzer deren Verwefen ablehnt.
     */

    @FXML
    protected void onBtAbbrechenClick() {

        Stage stage = (Stage) btAbbrechen.getScene().getWindow();

        if(mindestensEineEingabeVorhanden()) {
            Alert a = createWarningAlertBeforeCloseWindow();

            Optional<ButtonType> option = a.showAndWait();

            if(option.get() == a.getButtonTypes().get(0)) {

                stage.close();
            }
        } else {
            stage.close();
        }
    }

    /**
     * Erstellt bei Aufrufen einen Alert als warnung das bei Bestätigen alle ungespeicherten Daten verloren gehen.
     * @return
     */

    private Alert createWarningAlertBeforeCloseWindow() {
        Alert a = new Alert(Alert.AlertType.WARNING);
        a.setHeaderText("Möchten Sie die Speiseplanerstellung wirklich abbrechen?");
        a.setContentText("Alle ungespeicherten Daten werden verworfen");

        ButtonType ja = new ButtonType("Ja");
        ButtonType nein = new ButtonType("Nein");

        a.getButtonTypes().clear();
        a.getButtonTypes().add(ja);
        a.getButtonTypes().add(nein);

        Button jaButton = (Button) a.getDialogPane().lookupButton( ja );
        jaButton.setDefaultButton( false );

        //Activate Defaultbehavior for no-Button:
        Button neinButton = (Button) a.getDialogPane().lookupButton( nein );
        neinButton.setDefaultButton( true );
        return a;
    }

    /**
     * Überprüft ob der Nutzer bereits eingaben gemacht hat.
     * Sind eingaben vom Nutzer in einem der tfPreisTag oder tfBeziechnungTag Textfelder Gemacht worden gibt die Methode true zurück
     * Sind eingaben im Textfeld  tfKW gemacht worde gibt die Methode true zurück,
     * es sei den die eingabe entspricht der Automatisch eingefügten Akktuellen Kalenderwoche. In diesem Fall gibt die Methode false zurück
     * werden keine eingaben in den Bezeichungs und Preis Textfeldern gefunden und werden keine eingaben im Textfeld tfKW gefunden gibt die Methode false zurück
     * @return
     */

    protected boolean mindestensEineEingabeVorhanden() {
        boolean eingabeVorhanden = false;

        System.out.println(KwKannVerworfenWerden());

        if(!this.tfKW.getText().equals("") && !KwKannVerworfenWerden()) {
            eingabeVorhanden = true;
        }

        if(!eingabeVorhanden) {
            for (Map.Entry<Button, GerichtControlsSpeicher> set : zuordnung.entrySet()) {
                GerichtControlsSpeicher gerichtControlsSpeicher = set.getValue();
                if(!gerichtControlsSpeicher.getTfName().getText().equals("") ||
                        !gerichtControlsSpeicher.getTfPreis().getText().equals("")) {
                    eingabeVorhanden = true;
                    break;
                }
            }
        }

        return eingabeVorhanden;
    }

    /**
     * überprüft ob die Momentane Eingabe im Textfeld tfKW der Automtisch erreichneten Akktuellen Kalenderwoche entspricht.
     * Wenn ja gibt true zurück, wenn nein gibt false zurück
     * @return
     */

    private boolean KwKannVerworfenWerden() {
        boolean kwKannVerworfenWerden = false;
        String akktuelleKalenderwoche = getKalenderwoche(LocalDate.now()) + "";
        if(tfKW.getText().trim().equals(akktuelleKalenderwoche)) {
            kwKannVerworfenWerden = true;
        }
        return kwKannVerworfenWerden;
    }

    /**
     * führt die Methode validieren aus.
     * Wenn Validierung nicht erfolgreic:h requests Focus auf das Erste Fehelrhafte Feld
     *
     * Wenn Validierung erfoglreich:
     *
     * erstellt für jeden Tag Montag bis freitag eine ArrayList<Gericht> tagList
     * ließt Integer kw aus textfeld tfKW aus.
     * itteriert durch zuordnung Hashmap
     * Wenn ein Gericht im Textfeld tfPreisTag und tfBezeichnungTag eingaben enthält werden diese Verarbeitet und daraus ein Gerichtsobject erzeugt.
     * Ist ein Bild Vorhanden wird sein Pfad ermittelt und dieser zum Geircht hinzugefügt. Das Gericht wird in die Arraylist des Jewieligen Tages hinzugefügt.
     *
     * ist Eine Arraylist nach beendigung der Schleife lehr wird sie auf null gesetzt.
     * Anschließend: Erstellt Speiseplan object aus kw und den tagList Arraylists
     *
     * ein speicherplatz für den Speiseplan wird mit der Methode speichernSpeiseplanDat() vom Nutzer erfragt
     * die Methode abfragePDFSpeichern fragt den Nutzer ob er den SPeiseplan als PDF speichern möchte und leitet wenn ja alles notwendige in die Wege
     *
     * @throws IOException
     */

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
                GerichtControlsSpeicher gerichtControlsSpeicher = zuordnung.get(b);

                if(!gerichtControlsSpeicher.getTfName().getText().equals("") && !gerichtControlsSpeicher.getTfPreis().getText().equals("")) {
                    String name = gerichtControlsSpeicher.getTfName().getText();
                    double preis = -1;
                    try {
                        preis = Double.parseDouble(gerichtControlsSpeicher.getTfPreis().getText().replace(',', '.'));
                    } catch (Exception exception) {
                        System.out.println("Unvorhergesehener Fehler mit dem angegebenen Preis. Preis wurde auf -1 gesetzt");
                    }
                    Gericht gericht = new Gericht(name, preis, null);

                    if(gerichtControlsSpeicher.getIm().getImage() != this.noImage) {
                        gericht.setGerichtBildPath(gerichtControlsSpeicher.getImPath());
                    }
                    if(gerichtControlsSpeicher.getTag().equals("Mon")) {
                        monList.add(gericht);
                    } else if(gerichtControlsSpeicher.getTag().equals("Die")) {
                        dieList.add(gericht);
                    } else if(gerichtControlsSpeicher.getTag().equals("Mit")) {
                        mitList.add(gericht);
                    } else if(gerichtControlsSpeicher.getTag().equals("Don")) {
                        donList.add(gericht);
                    } else if(gerichtControlsSpeicher.getTag().equals("Fre")) {
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

    /**
     * erzeugt ein Alert mit Ja und Nein Button um den User zu Fragen ob er den übergebenen Speiseplan als PDF Abbspeichern möchte.
     * ließt die eingabe aus.
     * Klickt der User Ja wird die methode speichernSpeiseplanPDF(). Anscheließend schließt sich das Fenster
     * Klickt der User Nein schließt sich das Fenster lediglich
     * @param sp
     * @throws IOException
     */
    private void abfragePDFSpeichern(Speiseplan sp) throws IOException {
        Alert a = new Alert(Alert.AlertType.NONE);
        a.setTitle("PDF Erstellen");
        a.setHeaderText("Erstellen eines Speiseplan PDFs");
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
    }

    /**
     * Erzeugt eine Filechooser der nur .pdf datein anzeigt.
     * lässt den User ein Gewünschtes File zum speichern auswählen.
     * Aus dem Zurückgegeben Pfad wird eine File erstellt.
     * sollte das file noch nciht vorhanden sein wird ein neues Erstellt
     * Gibt es das file schon wird es überschrieben mit dem PDF erzeugt duch die Methode createSpeiseplanPDF der Klasse PDFCreator
     * Das Programm öffnet das PDF file im auf dem System dafür als Standard eingestellten Browser
     * @param sp
     * @throws IOException
     */

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

            GerichtControlsSpeicher gerichtControlsSpeicher = set.getValue();

            gerichtControlsSpeicher.getTfName().setStyle("-fx-control-inner-background: #"+farbeGruen.toString().substring(2));
            gerichtControlsSpeicher.getTfPreis().setStyle("-fx-control-inner-background: #"+farbeGruen.toString().substring(2));

            String preisGerichtAsText = gerichtControlsSpeicher.getTfPreis().getText().trim().replace(',', '.');
            String nameGericht = gerichtControlsSpeicher.getTfName().getText().trim();

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
                        firstFehlerField = gerichtControlsSpeicher.getTfPreis();
                        fehlerCode = 2; // Fehlerhafter Preis
                    } else if(fehlerCode == 1 || fehlerCode == 0) {
                        fehlerCode = 3; // Mehrer Unterschiedliche Fehler
                    }

                    gerichtControlsSpeicher.getTfPreis().setStyle("-fx-control-inner-background: #"+farbeGelb.toString().substring(2));
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
                        firstFehlerField = gerichtControlsSpeicher.getTfName();
                        fehlerCode = 4; // Fehlerhafte Bezeichnung
                    } else if(fehlerCode == 1 || fehlerCode == 0 || fehlerCode == 2) {
                        fehlerCode = 3; // Mehrer Unterschiedliche Fehler
                    }

                    gerichtControlsSpeicher.getTfName().setStyle("-fx-control-inner-background: #"+farbeGelb.toString().substring(2));
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
                    firstFehlerField = gerichtControlsSpeicher.getTfName();
                    fehlerCode = 1; // Fehlendes Feld
                } else if(fehlerCode == 2 || fehlerCode == 0) {
                    fehlerCode = 3; // Mehrer Unterschiedliche Fehler
                }
                gerichtControlsSpeicher.getTfName().setStyle("-fx-control-inner-background: #"+farbeGelb.toString().substring(2));
            }

            if(!preisVorhanden && nameVorhanden) {
                anzahlFehler++;
                if(firstFehlerField == null) {
                    firstFehlerField = gerichtControlsSpeicher.getTfPreis();
                    fehlerCode = 1; // Fehlendes Feld
                } else if(fehlerCode == 2 || fehlerCode == 0) {
                    fehlerCode = 3; // Mehrer Unterschiedliche Fehler
                }
                gerichtControlsSpeicher.getTfPreis().setStyle("-fx-control-inner-background: #"+farbeGelb.toString().substring(2));
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
        if(event.getSource() == tfKW) {
            setKWundTagesDatenFelder();
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
