package application;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import com.isteMySQL.util.oto_yedek_parca_veritabanýUtil;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;

public class PersonelController {
	 Connection baglanti=null;
	    PreparedStatement sorguifadesi=null;
	    ResultSet getirilen=null;
	    String sql;


	public PersonelController(){
	baglanti=oto_yedek_parca_veritabanýUtil.Baglan();
	}

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btn_cikar;

    @FXML
    private TextField txt_ad;

    @FXML
    private TextField txt_soyad;

    @FXML
    private TextField txt_telefon;

    @FXML
    private TextField txt_adres;

    @FXML
    private TextField txt_email;

    @FXML
    private TextField txt_sifre;

    @FXML
    private TableView<getir> tbl_table;
   
    @FXML
    private TableColumn<getir, String> tbl_per_ad;
  
    @FXML
    private TableColumn<getir, String> tbl_email;
  
    @FXML
    private Button btn_ekle;

    @FXML
    private Button btn_temizle;

    @FXML
    private Button btn_cikis;

    @FXML
    void tbl_table_mouseClick(MouseEvent event) {
    	getir getir1=new getir();
    	getir1=(getir) tbl_table.getItems().get(tbl_table.getSelectionModel().getSelectedIndex());
    	txt_ad.setText(getir1.getPer_ad());
    	txt_email.setText(getir1.getEmail());
    }
    @FXML
    void btn_cikar_click(ActionEvent event) {
    	
    	sql="delete from personel ";
    	
    	try {
			sorguifadesi=baglanti.prepareStatement(sql);
			tbl_table.getSelectionModel().getSelectedItem();
			sorguifadesi.executeUpdate();
			
			Alert alert=new Alert(AlertType.INFORMATION);
			alert.setTitle("Bilgi mesajý");
			alert.setContentText("Personel silindi");
			alert.showAndWait();
			
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage().toString());
		}

    }

    @FXML
    void btn_cikis_cikis(ActionEvent event) {
    	System.exit(0);

    }

    @FXML
    void btn_ekle_click(ActionEvent event) {
    	sql="insert into personel(ad, soyad, telefon, email, adres, sifre) values(?,?,?,?,?,?)";
    	try {
			sorguifadesi=baglanti.prepareStatement(sql);
			sorguifadesi.setString(1, txt_ad.getText().trim());
			sorguifadesi.setString(2, txt_soyad.getText().trim());
			sorguifadesi.setString(3, txt_telefon.getText().trim());
			sorguifadesi.setString(4, txt_adres.getText().trim());
			sorguifadesi.setString(5, txt_email.getText().trim());
			sorguifadesi.setString(6, txt_sifre.getText().trim());
			sorguifadesi.executeUpdate();
			Alert alert1=new Alert(AlertType.INFORMATION);
			alert1.setTitle("Bilgi mesajý");
			alert1.setContentText("Personel baþarý ile eklenmiþtir");
			alert1.showAndWait();
			if(txt_ad.getText().trim()==(" ") || txt_soyad.getText().trim()==(" ") || txt_telefon.getText().trim()==(" ") || txt_adres.getText().trim()==(" ") || txt_email.getText().trim()==(" ") || txt_sifre.getText().trim()==(" ")) {
				Alert alert2=new Alert(AlertType.ERROR);
				alert2.setTitle("Hata mesajý");
				alert2.setContentText("Lütfen bilgileri kontrol ediniz");
				alert2.showAndWait();
					
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		
		
			
		}

    }
    
    public void DegerGetir(TableView tablo) {
        sql="select * from personel";
        ObservableList<getir> bilgigetir=FXCollections.observableArrayList();
        try {
  		sorguifadesi=baglanti.prepareStatement(sql);
  		ResultSet getirilen=sorguifadesi.executeQuery();
  		while(getirilen.next()) {
  			bilgigetir.add(new getir(getirilen.getString("ad"), getirilen.getString("email")));
  		
  		}
  		tbl_per_ad.setCellValueFactory(new PropertyValueFactory<>("per_ad"));
  		tbl_email.setCellValueFactory(new PropertyValueFactory<>("email"));
  		tbl_table.setItems(bilgigetir);
  		
  		
  	} catch (Exception e) {
  		// TODO: handle exception
  		System.out.println(e.getMessage().toString());
  	}
    	
    }

    @FXML
    void btn_temizle_click(ActionEvent event) {
    	txt_ad.clear();
    	txt_adres.clear();
    	txt_email.clear();
    	txt_sifre.clear();
    	txt_soyad.clear();
    	txt_telefon.clear();

    }

    @FXML
    void initialize() {
    	DegerGetir(tbl_table);
    	sql="select * from personel";
    	ObservableList<getir> getir1=FXCollections.observableArrayList();
    	try {
			sorguifadesi=baglanti.prepareStatement(sql);
			ResultSet getirilen=sorguifadesi.executeQuery();
			while (getirilen.next()) {
				getir1.add(new getir(getirilen.getString("ad"), getirilen.getString("email")));
				
			}
			tbl_per_ad.setCellValueFactory(new PropertyValueFactory<>("per_ad"));
			tbl_email.setCellValueFactory(new PropertyValueFactory<>("email"));
			tbl_table.setItems(getir1);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage().toString());
		}


    }
    
    public static class getir{
    String per_ad;
    String email;
    
    public getir() {
    	
    }
    getir(String per_ad, String email){
    	this.per_ad=per_ad;
    	this.email=email;
    }
	public String getPer_ad() {
		return per_ad;
	}
	public void setPer_ad(String per_ad) {
		this.per_ad = per_ad;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
    }
}
