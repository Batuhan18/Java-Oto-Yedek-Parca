package com.isteMySQL.util;
import java.sql.*;

public class oto_yedek_parca_veritaban�Util {
	static Connection con=null;
	
	public static Connection Baglan() {
		try {
			//"jdbc:mysql://ServerIPAdresi/db_ismi", "kullanici", "�ifre"
			con=DriverManager.getConnection("jdbc:mysql://localhost/oto_yedek_parca_db", "root", "mysql");
			return con;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage().toString());
			return null;
		}
	}
	

}
