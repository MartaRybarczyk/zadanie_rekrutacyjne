package sii.zadanie.demo;

import sii.zadanie.demo.model.Product;
import sii.zadanie.demo.model.PromoCode;
import sii.zadanie.demo.model.Purchase;

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
            database.createStatement().execute("""
                                               CREATE TABLE products (
                                                   name        TEXT PRIMARY KEY,
                                                   description TEXT,
                                                   price       REAL NOT NULL,
                                                   currency    TEXT NOT NULL
                                               )""");
            database.createStatement().execute("""
                                               CREATE TABLE promo_codes (
                                                   id                 TEXT    PRIMARY KEY,
                                                   currency           TEXT    NOT NULL,
                                                   discount_amount    REAL    NOT NULL,
                                                   max_allowed_usages NUMERIC NOT NULL,
                                                   expiration_date    NUMERIC NOT NULL
                                               )""");
            database.createStatement().execute("""
                                               CREATE TABLE purchases (
                                                   id            TEXT    PRIMARY KEY,
                                                   purchase_date NUMERIC NOT NULL,
                                                   name          TEXT    NOT NULL,
                                                   regular_price NUMERIC NOT NULL,
                                                   discount      NUMERIC
                                               )""");
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

    public void insertPromoCode(PromoCode promocode) {
        try {
            final var insertion = connection.prepareStatement("INSERT INTO promo_codes (id,currency, discount_amount, max_allowed_usages, expiration_date) VALUES (?, ?, ?, ?, ?)");
            insertion.setString(1, promocode.getId());
            insertion.setString(2, promocode.getCurrency());
            insertion.setFloat(3, promocode.getDiscountAmount());
            insertion.setInt(4, promocode.getMaxAllowedUsages());
            insertion.setDate(5, promocode.getExpirationDate());
            insertion.execute();
        } catch (SQLException e) {
            System.err.println("Failed to insert a promocode into the SQLite database.\n" + e.getMessage());
        }
    }

    public void insertPurchase(Purchase purchase) {
        try {
            final var insertion = connection.prepareStatement("INSERT INTO purchases (id, purchase_date, name, regular_price, discount) VALUES (?, ?, ?, ?, ?)");
            insertion.setString(1, purchase.getId());
            insertion.setDate(2, purchase.getPurchaseDate());
            insertion.setString(3, purchase.getName());
            insertion.setFloat(4, purchase.getRegularPrice());
            insertion.setFloat(5, purchase.getDiscountAmount());
            insertion.execute();
        } catch (SQLException e) {
            System.err.println("Failed to insert a purchase into the SQLite database.\n" + e.getMessage());
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

    public void editPromoCode(String currentName, PromoCode newPromoCodeState) {
        try {
            final var update = connection.prepareStatement("UPDATE promoCodes SET currency = ?, discount = ?, usages = ? WHERE name = ?");
            update.setString(1, newPromoCodeState.getCurrency());
            update.setDouble(2, newPromoCodeState.getDiscountAmount());
            update.setDouble(3, newPromoCodeState.getMaxAllowedUsages());
            update.setString(4, currentName);
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

    public List<PromoCode> getPromoCodes() {
        final List<PromoCode> promoCodes = new ArrayList<>();
        try {
            final var query = connection.createStatement().executeQuery("SELECT * FROM promo_codes");
            while (query.next())
                promoCodes.add(new PromoCode(query.getString("id"), query.getString("currency"), query.getFloat("discount_amount"), query.getInt("max_allowed_usages"), query.getDate("expiration_date")));
        } catch (SQLException e) {
            System.err.println("Failed to get promo codes from the SQLite database.\n" + e.getMessage());
        }
        return promoCodes;
    }

    public List<Purchase> getPurchases() {
        final List<Purchase> purchases = new ArrayList<>();
        try {
            final var query = connection.createStatement().executeQuery("SELECT * FROM purchases");
            while (query.next())
                purchases.add(new Purchase(query.getString("id"), query.getDate("purchase_date"), query.getString("name"), query.getFloat("regular_price"), query.getFloat("discount")));
        } catch (SQLException e) {
            System.err.println("Failed to get purchases from the SQLite database.\n" + e.getMessage());
        }
        return purchases;
    }

    public Product searchProductByName(String name) {
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

    public PromoCode searchPromoCodeById(String id) {
        try {
            final var query = connection.prepareStatement("SELECT * FROM promo_codes WHERE id = ?");
            query.setString(1, id);
            final var result = query.executeQuery();
            if (result.next())
                return new PromoCode(result.getString("id"), result.getString("currency"), result.getFloat("discount_amount"), result.getInt("max_allowed_usages"), result.getDate("expiration_date"));
        } catch (SQLException e) {
            System.err.println("Failed to search for a promo code in the SQLite database.\n" + e.getMessage());
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