package com.jsf.hello.MBs;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import javax.faces.bean.ManagedBean;
import javax.faces.model.SelectItem;


import org.omnifaces.cdi.ViewScoped;
import com.jsf.hello.EJBs.Roomtest;



@ManagedBean(name = "test")
@ViewScoped
public class SelectItemsBean implements Serializable {
	
    private static final long serialVersionUID = 1L;

    private List<Roomtest> products;
    private Roomtest product;
    private String productName;
    
    
    Connection con = null;
	PreparedStatement stat = null;
	ResultSet rs = null;
    
	public List<Roomtest> getProducts() throws SQLException
    {
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hmsdb?autoReconnect=true&useSSL=false", "root", "Sommar15");
		String myStat = "SELECT * FROM rooms";
		stat = con.prepareStatement(myStat);
		rs = stat.executeQuery();
		 products = new ArrayList<Roomtest>();
		while(rs.next()){
		
       
        
     
            product = new Roomtest();
            product.setRoomId(rs.getInt("roomId"));
            product.setRoomType(rs.getString("roomType"));
            product.setMaxCapacity(rs.getInt("maxCapacity"));
            product.setCapacityNow(rs.getInt("capacityNow"));
            products.add(product);
        }
        return products;
   
    
    }

	
	//setters and getters
	public Roomtest getProduct() {
		return product;
	}

	public void setProduct(Roomtest product) {
		this.product = product;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public void setProducts(List<Roomtest> products) {
		this.products = products;
	}
}