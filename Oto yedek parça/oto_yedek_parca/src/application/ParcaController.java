package application;



import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ResourceBundle;


import com.isteMySQL.util.oto_yedek_parca_veritabanýUtil;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;



public class ParcaController {
	Connection baglanti=null;
	PreparedStatement sorguifadesi=null;
    ResultSet getirilen=null;
    String sql, sql1;

	public ParcaController(){
	baglanti=oto_yedek_parca_veritabanýUtil.Baglan();
	}
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> combo_marka;

    @FXML
    private ComboBox<String> combo_model;
    
    @FXML
    private ComboBox<Integer> combo_yil;

    @FXML
    private ComboBox<String> combo_yakit;

    @FXML
    private ComboBox<String> combo_kategori;

    @FXML
    public ComboBox<String> combo_parca;

    @FXML
    private TextField txt_adet;

    @FXML
    private TextField txt_müsteri_adi;

    @FXML
    public Button btn_sat;

    @FXML
    private Button btn_temizle;

    @FXML
    private Button btn_cikis;

    @FXML
    private TableView<degerler> tbl_raf_fiyat;
    
    ObservableList<degerler> göster;

    @FXML
    private TableColumn<degerler, String> tbl_raf;

    @FXML
    private TableColumn<degerler, Integer> tbl_fiyat;
    
    @FXML
    private TableColumn<degerler, String> tbl_parca;
    
    @FXML
    private TextField txt_tarih;

    @FXML
    private Button btn_goruntule;

    @FXML
    private Button btn_personel;

    @FXML
    private TextField txt_raf_ekle;

    @FXML
    private TextField txt_adet_ekle;

    @FXML
    private Button btn_ekle;
    
    @FXML
    private Button btn_musterigecmisi;

    @FXML
    private ComboBox<String> combo_kategori_ekle;

    @FXML
    private ComboBox<String> combo_parca_ekle;

    @FXML
    private Button btn_temizle_ekle;

    @FXML
    private ComboBox<String> combo_marka_ekle;

    @FXML
    private ComboBox<String> combo_model_ekle;
    
    @FXML
    private ComboBox<String> combo_raf_ekle;
    
  

    @FXML
    void btn_cikis_click(ActionEvent event) {
    	System.exit(0);

    }

    @FXML
    void btn_ekle_click(ActionEvent event) {
		sql1="insert into parca(kategori,parca, raf,adet) values(?,?,?,?)";
		try {
			sorguifadesi=baglanti.prepareStatement(sql1);
			sorguifadesi.setString(1, combo_kategori_ekle.getSelectionModel().getSelectedItem().toString().trim());
			sorguifadesi.setString(2, combo_parca_ekle.getSelectionModel().getSelectedItem().toString().trim());
			sorguifadesi.setString(3, combo_raf_ekle.getSelectionModel().getSelectedItem().toString().trim());
			sorguifadesi.setString(4, txt_adet.getText().trim());
			sorguifadesi.executeUpdate();
			
			int adet=getirilen.getInt("adet");
			while (getirilen.next()) {
				adet++;
				adet=getirilen.getInt(adet);
				sorguifadesi.setString(4, txt_adet.getText().trim());
			
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			
			
			
		}
	
		Alert alert=new Alert(AlertType.INFORMATION);
		alert.setTitle("Bilgi mesajý");
		alert.setContentText("Parça eklenmiþtir");
		alert.showAndWait();
    
    }
    @FXML
    void btn_goruntule_click(ActionEvent event) {
    	sql="select * from parca";
    	ObservableList<degerler> deger=FXCollections.observableArrayList();
    	try {
			sorguifadesi=baglanti.prepareStatement(sql);
			ResultSet getirilen=sorguifadesi.executeQuery();
			while(getirilen.next()) {
				deger.add(new degerler(getirilen.getString("raf"), getirilen.getInt("fiyat"), getirilen.getString("parca")));			
			}
			tbl_raf.setCellValueFactory(new PropertyValueFactory<>("raf"));
	    	tbl_fiyat.setCellValueFactory(new PropertyValueFactory<>("fiyat"));
	    	tbl_parca.setCellValueFactory(new PropertyValueFactory<>("parca"));
	    	tbl_raf_fiyat.setItems(deger);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage().toString());
		}
  

    }

    @FXML
    void btn_personel_click(ActionEvent event) {
    	try {
			Stage stage2= new Stage();
			AnchorPane pane2 = (AnchorPane)FXMLLoader.load(getClass().getResource("Personel.fxml"));
			Scene scene = new Scene(pane2);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			stage2.setScene(scene);
			stage2.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}

    }

    @FXML
    void btn_sat_click(ActionEvent event) {

    	sql="insert into müsteri(ad, tarih) values(?,?) ";
    	
    	try {
			sorguifadesi=baglanti.prepareStatement(sql);
			sorguifadesi.setString(1, txt_müsteri_adi.getText().trim());
			sorguifadesi.setString(2, txt_tarih.getText().trim());
			sorguifadesi.executeUpdate();
			sql1="insert into parca(adet) values(?)";
			try {
				sorguifadesi=baglanti.prepareStatement(sql1);
				sorguifadesi.executeUpdate();
				
				int adet=getirilen.getInt("adet");
				while (getirilen.next()) {
					adet--;
					adet=getirilen.getInt(adet);
				
				}
				
			} catch (Exception e) {
				// TODO: handle exception
				
			}
		
			Alert alert=new Alert(AlertType.INFORMATION);
			alert.setTitle("Bilgi mesajý");
			alert.setContentText("Parça satýlmýþtýr");
			alert.showAndWait();
			
			
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage().toString());
		}
 
    	
  
    }

    @FXML
    void btn_temizle_click(ActionEvent event) {
    	combo_kategori.getItems().clear();
    	combo_marka.getItems().clear();
    	combo_model.getItems().clear();
    	combo_parca.getItems().clear();
    	combo_yakit.getItems().clear();
    	combo_yil.getItems().clear();
    	txt_adet.clear();
    	txt_müsteri_adi.clear();
    	txt_tarih.clear();
    	
    	
    	
    	

    }

    @FXML
    void btn_temizle_ekle_click(ActionEvent event) {
    	combo_kategori_ekle.getItems().clear();
    	combo_parca_ekle.getItems().clear();
    	txt_adet_ekle.clear();
    	combo_raf_ekle.getItems().clear();
    	combo_marka_ekle.getItems().clear();
    	combo_model_ekle.getItems().clear();

    }

    @FXML
    void combo_kategori_click(ActionEvent event) {
 
   
		String kategori=combo_kategori.getSelectionModel().getSelectedItem();
		switch (kategori) {
		case "Motor": {
			combo_parca.getItems().clear();
			
			try {
				sql="select * from parca where kategori='Motor'";
				sorguifadesi=baglanti.prepareStatement(sql);
				ResultSet getirilen=sorguifadesi.executeQuery();
				while (getirilen.next()) {
					String doldur2=getirilen.getString("parca");
					combo_parca.getItems().add(doldur2);
					
				}
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println(e.getMessage().toString());
			}
		}break;
        	case "Kaporta": {
        		combo_parca.getItems().clear();
        		
        		try {
					sql="select * from parca where kategori='Kaporta'";
					sorguifadesi=baglanti.prepareStatement(sql);
					ResultSet getirilen=sorguifadesi.executeQuery();
					while (getirilen.next()) {
						String doldur2=getirilen.getString("parca");
						combo_parca.getItems().add(doldur2);
						
					}
				} catch (Exception e) {
					// TODO: handle exception
					System.out.println(e.getMessage().toString());
				}
			
		}break;
        	case "Yürür Aksam": {

        		combo_parca.getItems().clear();
        		
        		try {
					sql="select * from parca where kategori='Yürür Aksam'";
					sorguifadesi=baglanti.prepareStatement(sql);
					ResultSet getirilen=sorguifadesi.executeQuery();
					while (getirilen.next()) {
						String doldur2=getirilen.getString("parca");
						combo_parca.getItems().add(doldur2);
						
					}
				} catch (Exception e) {
					// TODO: handle exception
					System.out.println(e.getMessage().toString());
				}
        	}break;
        	case "Elektrik": {

        		combo_parca.getItems().clear();
        		
        		
        		try {
					sql="select * from parca where kategori='Elektrik'";
					sorguifadesi=baglanti.prepareStatement(sql);
					ResultSet getirilen=sorguifadesi.executeQuery();
					while (getirilen.next()) {
						String doldur2=getirilen.getString("parca");
						combo_parca.getItems().add(doldur2);
						
					}
				} catch (Exception e) {
					// TODO: handle exception
					System.out.println(e.getMessage().toString());
				}
        		}break;
        	case "Güç Aktarma": {

        		combo_parca.getItems().clear();
        	
        		try {
					sql="select * from parca where kategori='Güç Aktarma'";
					sorguifadesi=baglanti.prepareStatement(sql);
					ResultSet getirilen=sorguifadesi.executeQuery();
					while (getirilen.next()) {
						String doldur2=getirilen.getString("parca");
						combo_parca.getItems().add(doldur2);
						
					}
				} catch (Exception e) {
					// TODO: handle exception
					System.out.println(e.getMessage().toString());
				}
        	}break;
		default:
			throw new IllegalArgumentException("Unexpected value: " + kategori);
		}
		

    }

    @FXML
    void combo_kategori_ekle_click(ActionEvent event) {
    	
    		String kategori1=combo_kategori_ekle.getSelectionModel().getSelectedItem();
    		switch (kategori1) {
    		case "Motor": {
    			combo_parca_ekle.getItems().clear();
    	
    			try {
					sql="select * from parca where kategori='Motor'";
					sorguifadesi=baglanti.prepareStatement(sql);
					ResultSet getirilen=sorguifadesi.executeQuery();
					while (getirilen.next()) {
						String doldur2=getirilen.getString("parca");
						combo_parca_ekle.getItems().add(doldur2);
						
					}
				} catch (Exception e) {
					// TODO: handle exception
					System.out.println(e.getMessage().toString());
				}
    		}break;
            	case "Kaporta": {
            		combo_parca_ekle.getItems().clear();
            	
            		try {
    					sql="select * from parca where kategori='Kaporta'";
    					sorguifadesi=baglanti.prepareStatement(sql);
    					ResultSet getirilen=sorguifadesi.executeQuery();
    					while (getirilen.next()) {
    						String doldur2=getirilen.getString("parca");
    						combo_parca_ekle.getItems().add(doldur2);
    						
    					}
    				} catch (Exception e) {
    					// TODO: handle exception
    					System.out.println(e.getMessage().toString());
    				}
    			
    		}break;
            	case "Yürür Aksam": {

            		combo_parca_ekle.getItems().clear();
            	
            		try {
    					sql="select * from parca where kategori='Yürür Aksam'";
    					sorguifadesi=baglanti.prepareStatement(sql);
    					ResultSet getirilen=sorguifadesi.executeQuery();
    					while (getirilen.next()) {
    						String doldur2=getirilen.getString("parca");
    						combo_parca_ekle.getItems().add(doldur2);
    						
    					}
    				} catch (Exception e) {
    					// TODO: handle exception
    					System.out.println(e.getMessage().toString());
    				}
            	}break;
            	case "Elektrik": {

            		combo_parca_ekle.getItems().clear();
            		
            		
            		try {
    					sql="select * from parca where kategori='Elektrik'";
    					sorguifadesi=baglanti.prepareStatement(sql);
    					ResultSet getirilen=sorguifadesi.executeQuery();
    					while (getirilen.next()) {
    						String doldur2=getirilen.getString("parca");
    						combo_parca_ekle.getItems().add(doldur2);
    						
    					}
    				} catch (Exception e) {
    					// TODO: handle exception
    					System.out.println(e.getMessage().toString());
    				}
            		}break;
            	case "Güç Aktarma": {

            		combo_parca_ekle.getItems().clear();
            	
            		try {
    					sql="select * from parca where kategori='Güç Aktarma'";
    					sorguifadesi=baglanti.prepareStatement(sql);
    					ResultSet getirilen=sorguifadesi.executeQuery();
    					while (getirilen.next()) {
    						String doldur2=getirilen.getString("parca");
    						combo_parca_ekle.getItems().add(doldur2);
    						
    					}
    				} catch (Exception e) {
    					// TODO: handle exception
    					System.out.println(e.getMessage().toString());
    				}
            	}break;
    		default:
    			throw new IllegalArgumentException("Unexpected value: " + kategori1);
    		}

    }

    @FXML
    void combo_marka_click(ActionEvent event) {
    
    
    		String marka=combo_marka.getSelectionModel().getSelectedItem();
    		switch (marka) {
			case "Mercedes": {
				combo_model.getItems().clear();
				
				try {
					sql="select * from araba where marka='Mercedes'";
					sorguifadesi=baglanti.prepareStatement(sql);
					ResultSet getirilen=sorguifadesi.executeQuery();
					while (getirilen.next()) {
						String doldur2=getirilen.getString("model");
						combo_model.getItems().add(doldur2);
						
					}
				} catch (Exception e) {
					// TODO: handle exception
					System.out.println(e.getMessage().toString());
				}
			}break;
            	case "Volvo": {
            		combo_model.getItems().clear();
            		
            		try {
    					sql="select * from araba where marka='Volvo'";
						sorguifadesi=baglanti.prepareStatement(sql);
						ResultSet getirilen=sorguifadesi.executeQuery();
						while (getirilen.next()) {
							String doldur2=getirilen.getString("model");
							combo_model.getItems().add(doldur2);
							
						}
					} catch (Exception e) {
						// TODO: handle exception
						System.out.println(e.getMessage().toString());
					}
				
			}break;
            	case "Wolksvagen": {
	
            		combo_model.getItems().clear();
            		
            		try {
    					sql="select * from araba where marka='Wolksvagen'";
						sorguifadesi=baglanti.prepareStatement(sql);
						ResultSet getirilen=sorguifadesi.executeQuery();
						while (getirilen.next()) {
							String doldur2=getirilen.getString("model");
							combo_model.getItems().add(doldur2);
							
						}
					} catch (Exception e) {
						// TODO: handle exception
						System.out.println(e.getMessage().toString());
					}
            	}break;
            	case "BMW": {
	
            		combo_model.getItems().clear();
            		
            	
            		try {
    					sql="select * from araba where marka='BMW'";
						sorguifadesi=baglanti.prepareStatement(sql);
						ResultSet getirilen=sorguifadesi.executeQuery();
						while (getirilen.next()) {
							String doldur2=getirilen.getString("model");
							combo_model.getItems().add(doldur2);
							
						}
					} catch (Exception e) {
						// TODO: handle exception
						System.out.println(e.getMessage().toString());
					}
            		}break;
            	case "AUDI": {
	
            		combo_model.getItems().clear();
            		
            		try {
    					sql="select * from araba where marka='AUDI'";
						sorguifadesi=baglanti.prepareStatement(sql);
						ResultSet getirilen=sorguifadesi.executeQuery();
						while (getirilen.next()) {
							String doldur2=getirilen.getString("model");
							combo_model.getItems().add(doldur2);
							
						}
					} catch (Exception e) {
						// TODO: handle exception
						System.out.println(e.getMessage().toString());
					}
            	}break;
			default:
				throw new IllegalArgumentException("Unexpected value: " + marka);
			}
    		
    		
			
			
    	}

    @FXML
    void combo_model_click(ActionEvent event) {
    
    	

    	}
    
    @FXML
    void btn_musterigecmisi_click(ActionEvent event) {
    	try {
			Stage stage2= new Stage();
			AnchorPane pane2 = (AnchorPane)FXMLLoader.load(getClass().getResource("MusteriGecmisi.fxml"));
			Scene scene = new Scene(pane2);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			stage2.setScene(scene);
			stage2.show();
		} catch(Exception e) {
			e.printStackTrace();
		}

    }
    

    

    @FXML
    void combo_parca_click(ActionEvent event) {
    
    }

    @FXML
    void combo_parca_ekle_click(ActionEvent event) {
    
    }

    @FXML
    void combo_yakit_click(ActionEvent event) {

    }
    @FXML
    void combo_marka_ekle_click(ActionEvent event) {
    		
         	
        		String marka1=combo_marka_ekle.getSelectionModel().getSelectedItem();
        		switch (marka1) {
    			case "Mercedes": {
    				combo_model_ekle.getItems().clear();
    				
    				
    				try {
    					sql="select * from araba where marka='Mercedes'";
						sorguifadesi=baglanti.prepareStatement(sql);
						ResultSet getirilen=sorguifadesi.executeQuery();
						while (getirilen.next()) {
							String doldur2=getirilen.getString("model");
							combo_model_ekle.getItems().add(doldur2);
							
						}
					} catch (Exception e) {
						// TODO: handle exception
						System.out.println(e.getMessage().toString());
					}
    			}break;
                	case "Volvo": {
                		combo_model_ekle.getItems().clear();
                		
                		try {
        					sql="select * from araba where marka='Volvo'";
    						sorguifadesi=baglanti.prepareStatement(sql);
    						ResultSet getirilen=sorguifadesi.executeQuery();
    						while (getirilen.next()) {
    							String doldur2=getirilen.getString("model");
    							combo_model_ekle.getItems().add(doldur2);
    							
    						}
    					} catch (Exception e) {
    						// TODO: handle exception
    						System.out.println(e.getMessage().toString());
    					}
    				
    			}break;
                	case "Wolksvagen": {
    	
                		combo_model_ekle.getItems().clear();
                		
                		try {
        					sql="select * from araba where marka='Wolksvagen'";
    						sorguifadesi=baglanti.prepareStatement(sql);
    						ResultSet getirilen=sorguifadesi.executeQuery();
    						while (getirilen.next()) {
    							String doldur2=getirilen.getString("model");
    							combo_model_ekle.getItems().add(doldur2);
    							
    						}
    					} catch (Exception e) {
    						// TODO: handle exception
    						System.out.println(e.getMessage().toString());
    					}
                	}break;
                	case "BMW": {
    	
                		combo_model_ekle.getItems().clear();
                		
                		
                		try {
        					sql="select * from araba where marka='BMW'";
    						sorguifadesi=baglanti.prepareStatement(sql);
    						ResultSet getirilen=sorguifadesi.executeQuery();
    						while (getirilen.next()) {
    							String doldur2=getirilen.getString("model");
    							combo_model_ekle.getItems().add(doldur2);
    							
    						}
    					} catch (Exception e) {
    						// TODO: handle exception
    						System.out.println(e.getMessage().toString());
    					}
                		}break;
                	case "AUDI": {
    	
                		combo_model_ekle.getItems().clear();
                		
                		try {
        					sql="select * from araba where marka='AUDI'";
    						sorguifadesi=baglanti.prepareStatement(sql);
    						ResultSet getirilen=sorguifadesi.executeQuery();
    						while (getirilen.next()) {
    							String doldur2=getirilen.getString("model");
    							combo_model_ekle.getItems().add(doldur2);
    							
    						}
    					} catch (Exception e) {
    						// TODO: handle exception
    						System.out.println(e.getMessage().toString());
    					}
                	}break;
    			default:
    				throw new IllegalArgumentException("Unexpected value: " + marka1);
    			}
        		
    	
    }
    @FXML
    void combo_model_ekle_click(ActionEvent event) {
    	
    }
    @FXML
    void combo_raf_ekle_click(ActionEvent event) {
    	
    }


    @FXML
    void combo_yil_click(ActionEvent event) {

    }

    @FXML
   public void initialize() {
    	combo_marka.setPromptText("Marka Seçiniz");
     
    	sql="select distinct marka from araba";
        
    	try {
			sorguifadesi=baglanti.prepareStatement(sql);
			ResultSet getirilen=sorguifadesi.executeQuery();
			while (getirilen.next()) {
				String doldur=getirilen.getString("marka");
				combo_marka.getItems().add(doldur);
				combo_marka_ekle.getItems().add(doldur);
				
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage().toString());
		}
    	
    	
		
    	combo_model.setPromptText("Model Seçiniz");
   
    	

    
    	combo_yil.setPromptText("Yýlýný Seçiniz");
    	combo_yil.getItems().addAll(2000, 2001, 2002, 2003, 2004, 2005, 2006, 2007, 2008, 2009, 2010, 2011, 2012, 2013, 2014, 2015, 2016, 2017, 2018, 2019, 2020, 2021); 
    	
    	combo_yakit.setPromptText("Yakýt Seçiniz");
    	String[] dizi2= {"Benzin", "Benzin/LPG", "Dizel", "Hybrid", "Elektrikli"};
    	combo_yakit.getItems().addAll(dizi2);
    	
    
     	combo_kategori_ekle.setPromptText("Kategori Seçiniz");
    	combo_kategori.setPromptText("Kategori Seçiniz");
    	
    	sql="select distinct kategori from parca";
        
    	try {
			sorguifadesi=baglanti.prepareStatement(sql);
			
			
			ResultSet getirilen=sorguifadesi.executeQuery();
			while (getirilen.next()) {
				String doldur=getirilen.getString("kategori");
				combo_kategori.getItems().add(doldur);
				combo_kategori_ekle.getItems().add(doldur);
				
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage().toString());
		}
      
    	
    	
    	
    	
    	combo_parca.setPromptText("Parça Seçiniz");
    	
    
    	
  
    	
    	
    	combo_parca_ekle.setPromptText("Parça Seçiniz");
    	
    	combo_raf_ekle.setPromptText("Raf Seçiniz");
    	
    	
    	sql="select distinct raf from parca";
        
    	try {
			sorguifadesi=baglanti.prepareStatement(sql);
			
			
			ResultSet getirilen=sorguifadesi.executeQuery();
			while (getirilen.next()) {
				String doldur=getirilen.getString("raf");
				combo_raf_ekle.getItems().add(doldur);
				
				
				
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage().toString());
		}
      
    	
    	
    	combo_marka_ekle.setPromptText("Marka Seçiniz");
    	
    	
    	
    	combo_model_ekle.setPromptText("Model Seçiniz");
    	
    	
    	
   
}

	public static class degerler {
		String raf;
		int fiyat;
		String parca;
		
		public degerler() {
			
		}
		degerler(String raf, int fiyat, String parca){
			this.raf=raf;
			this.fiyat=fiyat;
			this.parca=parca;
					
		}
		public String getParca() {
			return parca;
		}
		public void setParca(String parca) {
			this.parca = parca;
		}
		public String getRaf() {
			return raf;
		}
		public void setRaf(String raf) {
			this.raf = raf;
		}
		public int getFiyat() {
			return fiyat;
		}
		public void setFiyat(int fiyat) {
			this.fiyat = fiyat;
		}
		
	
	}}
