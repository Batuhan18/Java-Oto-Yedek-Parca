package application;

import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;



import com.isteMySQL.util.oto_yedek_parca_veritaban�Util;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;

public class MusteriGecmisiController {
	public MusteriGecmisiController(){
		baglanti=oto_yedek_parca_veritaban�Util.Baglan();
		}

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btn_temizle;

    Connection baglanti=null;
    PreparedStatement sorguifadesi=null;
    ResultSet getirilen=null;
    String sql;

    @FXML
    private Button btn_goruntule;

    @FXML
    private TextField txt_ad;

    @FXML
    private TableView<getir> table_m�steri;

    @FXML
    private TableColumn<getir, String> table_musteri_adi;

    @FXML
    private TableColumn<getir, Date> table_tarih;

    @FXML
    void btn_goruntule_click(ActionEvent event) {
    	sql="select * from m�steri";
    	ObservableList<getir> getirliste=FXCollections.observableArrayList();
    	try {
    		sorguifadesi=baglanti.prepareStatement(sql);
			ResultSet getirilen=sorguifadesi.executeQuery();
			while (getirilen.next()) {
				getirliste.add(new getir(getirilen.getString("ad"), getirilen.getDate("tarih")));
				
			}
			table_musteri_adi.setCellValueFactory(new PropertyValueFactory<>("ad"));
			table_tarih.setCellValueFactory(new PropertyValueFactory<>("tarih"));
			table_m�steri.setItems(getirliste);
		} catch (Exception e) {
			// TODO: handle exception
			
		}

    }
    public void DegerGetir(TableView table, String sql) {
    	ObservableList<getir> getirliste=FXCollections.observableArrayList();
    	try {
			ResultSet getirilen=sorguifadesi.executeQuery();
			while (getirilen.next()) {
				getirliste.add(new getir(getirilen.getString("ad"), getirilen.getDate("tarih")));
				
			}
			table_musteri_adi.setCellValueFactory(new PropertyValueFactory<>("ad"));
			table_tarih.setCellValueFactory(new PropertyValueFactory<>("tarih"));
			table_m�steri.setItems(getirliste);
		} catch (Exception e) {
			// TODO: handle exception
		
		}
    }

    @FXML
    void btn_temizle_click(ActionEvent event) {

    	txt_ad.clear();
    
    }

    @FXML
    void txt_ad_KeyPressed(KeyEvent event) {
    	if(txt_ad.getText().equals("")) {
    		sql="select * from m�steri";
    		
    	}else {
    		sql="select * from m�steri where ad like '%"+txt_ad.getText()+"%'";
    	}
    	
    	DegerGetir(table_m�steri, sql);

    }

    @FXML
    void txt_ad_click(ActionEvent event) {

    }

    @FXML
    void initialize() {
    	sql="select * from m�steri";
    	DegerGetir(table_m�steri, sql);
       
    }
    public static class getir{
    	String ad;
    	Date tarih;
    	public getir() {
    		
    	}
    	getir(String ad, Date tarih){
    		this.ad=ad;
    		this.tarih=tarih;
    	}
		public String getAd() {
			return ad;
		}
		public void setAd(String ad) {
			this.ad = ad;
		}
		public Date getTarih() {
			return tarih;
		}
		public void setTarih(Date tarih) {
			this.tarih = tarih;
		}
    }
}
