package sii.zadanie.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SQLiteDatabase {

    private final Connection connection;

    public SQLiteDatabase() {
        Connection database = null;
        try {
            database = DriverManager.getConnection("jdbc:sqlite::memory:");
            database.createStatement().execute("CREATE TABLE products (name TEXT PRIMARY KEY, description TEXT, price REAL NOT NULL, currency TEXT NOT NULL)");
        } catch (SQLException e) {
            System.err.println("Failed to create an SQLite database.\n" + e.getMessage());
        }
        connection = database;
    }

    public void insertProduct(Product product) {
        try {
            final var insertion = connection.prepareStatement("INSERT INTO products (name, description, price, currency) VALUES (?, ?, ?, ?)");
            insertion.setString(1, product.getName());
            insertion.setString(2, product.getDescription());
            insertion.setDouble(3, product.getPrice());
            insertion.setString(4, product.getCurrency());
            insertion.execute();
        } catch (SQLException e) {
            System.err.println("Failed to insert a product into the SQLite database.\n" + e.getMessage());
        }
    }

    public void editProduct(String currentName, Product newProductState) {
        try {
            final var update = connection.prepareStatement("UPDATE products SET name = ?, description = ?, price = ?, currency = ? WHERE name = ?");
            update.setString(1, newProductState.getName());
            update.setString(2, newProductState.getDescription());
            update.setDouble(3, newProductState.getPrice());
            update.setString(4, newProductState.getCurrency());
            update.setString(5, currentName);
            update.execute();
        } catch (SQLException e) {
            System.err.println("Failed to update a product in the SQLite database.\n" + e.getMessage());
        }
    }

    public List<Product> getProducts() {
        final List<Product> products = new ArrayList<>();
        try {
            final var query = connection.createStatement().executeQuery("SELECT * FROM products");
            while (query.next())
                products.add(new Product(query.getString("name"), query.getString("description"), query.getFloat("price"), query.getString("currency")));
        } catch (SQLException e) {
            System.err.println("Failed to get products from the SQLite database.\n" + e.getMessage());
        }
        return products;
    }

    public Product searchByName(String name) {
        try {
            final var query = connection.prepareStatement("SELECT * FROM products WHERE name = ?");
            query.setString(1, name);
            final var result = query.executeQuery();
            if (result.next())
                return new Product(result.getString("name"), result.getString("description"), result.getFloat("price"), result.getString("currency"));
        } catch (SQLException e) {
            System.err.println("Failed to search for a product in the SQLite database.\n" + e.getMessage());
        }
        return null;
    }

    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            System.err.println("Failed to close the SQLite database.\n" + e.getMessage());
        }
    }
}