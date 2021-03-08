/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.indovinailnumero;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class FXMLController {
	
	private final int NMAX=100;
	private final int TMAX=8;
	private int segreto;
	private int tentativiFatti;
	private boolean inGioco = false;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="btnNuova"
    private Button btnNuova; // Value injected by FXMLLoader

    @FXML // fx:id="txtRimasti"
    private TextField txtRimasti; // Value injected by FXMLLoader
    @FXML // fx:id="layoutTentativo"
    
    private HBox layoutTentativo; // Value injected by FXMLLoader

    @FXML // fx:id="txtTentativoUtente"
    private TextField txtTentativoUtente; // Value injected by FXMLLoader

    @FXML // fx:id="btnProva"
    private Button btnProva; // Value injected by FXMLLoader

    @FXML // fx:id="txtRisultato"
    private TextArea txtRisultato; // Value injected by FXMLLoader

    @FXML
    void doNuova(ActionEvent event) {
    	this.segreto=(int) (Math.random()*NMAX)+1;
    	this.inGioco=true;
    	this.tentativiFatti=0;
    	this.txtRimasti.setText(Integer.toString(TMAX));
    	this.layoutTentativo.setDisable(false);
    }

    @FXML
    void doTentativo(ActionEvent event) {
    	String tS = txtTentativoUtente.getText();
    	int tentativo;
    	try {
    		tentativo= Integer.parseInt(tS);
    	}catch(NumberFormatException e) {
    		txtRisultato.setText("Devi inserire un numero!");
    		this.txtTentativoUtente.setText("");
    		return;
    	}
    	this.txtTentativoUtente.setText("");
    	this.tentativiFatti++;
    	this.txtRimasti.setText(Integer.toString(TMAX-this.tentativiFatti));
    	if(tentativo==this.segreto) {
    		txtRisultato.setText("HAI INDOVINATO CON "+this.tentativiFatti+" TENTATIVI");
    		this.inGioco=false;
    		this.layoutTentativo.setDisable(true);
    		return;
    	}
    	if(this.tentativiFatti==TMAX) {
    		txtRisultato.setText("HAI PERSO. Il numero era: "+this.segreto);
    		this.inGioco = false;
    		this.layoutTentativo.setDisable(true);
    		return;
    	}
    	if(tentativo<this.segreto) {
    		txtRisultato.setText(tentativo+" é troppo basso");
    	} else {
    		txtRisultato.setText(tentativo+" é troppo alto\n");
    	}	
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert btnNuova != null : "fx:id=\"btnNuova\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtRimasti != null : "fx:id=\"txtRimasti\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtTentativoUtente != null : "fx:id=\"txtTentativoUtente\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnProva != null : "fx:id=\"btnProva\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtRisultato != null : "fx:id=\"txtRisultato\" was not injected: check your FXML file 'Scene.fxml'.";

    }
}
