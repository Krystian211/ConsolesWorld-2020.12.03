package pl.camp.it.consoles.world.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.camp.it.consoles.world.dao.IProductDAO;
import pl.camp.it.consoles.world.model.Product;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductDAOImpl implements IProductDAO {

    @Autowired
    Connection connection;

    @Override
    public List<Product> getProductsByCategory(String category) {
        String sql = "SELECT * FROM tproduct WHERE category=?";
        List<Product> products=new ArrayList<>();
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
            preparedStatement.setString(1, category);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                products.add(this.mapResultSetToProduct(resultSet));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return products;
    }

    @Override
    public Product getProductByManufacturerCode(String manufacturerCode) {
        String sql = "SELECT * FROM tproduct WHERE manufacturerCode=?";
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
            preparedStatement.setString(1, manufacturerCode);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return this.mapResultSetToProduct(resultSet);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public Product getProductById(int productId) {
        String sql = "SELECT * FROM tproduct WHERE id=?";
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
            preparedStatement.setInt(1, productId);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return this.mapResultSetToProduct(resultSet);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Product> getAllProducts() {
        String sql = "SELECT * FROM tproduct";
        List<Product> products=new ArrayList<>();
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                products.add(this.mapResultSetToProduct(resultSet));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return products;
    }

    @Override
    public void persistProduct(Product product) {
        String sql="INSERT INTO tproduct (name,brand,category,pieces, manufacturerCode, price) VALUES(?,?,?,?,?,?)";

        try {
            PreparedStatement preparedStatement=this.connection.prepareStatement(sql);
            preparedStatement.setString(1,product.getName());
            preparedStatement.setString(2,product.getBrand());
            preparedStatement.setString(3,product.getCategory().toString().toLowerCase());
            preparedStatement.setInt(4,product.getPieces());
            preparedStatement.setString(5,product.getManufacturerCode());
            preparedStatement.setDouble(6,product.getPrice().doubleValue());

            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void updateProduct(Product productData) {
        String sql="UPDATE tproduct SET name=?, brand=?, category=?, pieces=?, manufacturerCode=?, price=? WHERE id=?";

        try {
            PreparedStatement preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setString(1,productData.getName());
            preparedStatement.setString(2,productData.getBrand());
            preparedStatement.setString(3,productData.getCategory().toString());
            preparedStatement.setInt(4,productData.getPieces());
            preparedStatement.setString(5,productData.getManufacturerCode());
            preparedStatement.setDouble(6, productData.getPrice().doubleValue());
            preparedStatement.setInt(7, productData.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private Product mapResultSetToProduct(ResultSet resultSet) throws SQLException {
        return new Product(resultSet.getInt("id"),
                resultSet.getString("name"),
                resultSet.getString("brand"),
                Product.Category.valueOf(resultSet.getString("category").toUpperCase()),
                resultSet.getInt("pieces"),
                resultSet.getString("manufacturerCode"),
                BigDecimal.valueOf(resultSet.getDouble("price")));
    }
}
