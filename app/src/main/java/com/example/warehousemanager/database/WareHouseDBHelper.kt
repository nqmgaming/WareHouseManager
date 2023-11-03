package com.example.warehousemanager.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class WareHouseDBHelper(context: Context) : SQLiteOpenHelper(
    context,
    DATABASE_NAME,
    null,
    DATABASE_VERSION
) {
    companion object {
        val DATABASE_NAME = "warehouse.db"
        val DATABASE_VERSION = 1

        //Table warehouse
        val CREATE_WAREHOUSE_TABLE = """
        CREATE TABLE warehouse (
        id TEXT PRIMARY KEY,
        name TEXT,
        address TEXT,
        representative TEXT,
        phone_number TEXT
        )
        """

        //Table keepers
        val CREATE_WAREHOUSE_KEEPER_TABLE = """
         CREATE TABLE warehouse_keeper (
        id TEXT PRIMARY KEY,
        password TEXT,
        name TEXT,
        email TEXT,
        phone_number TEXT,
        birthday DATE,
        warehouse_id TEXT,
        FOREIGN KEY (warehouse_id) REFERENCES warehouse(id)
        )
        """

        // Table warehouse_staff
        val CREATE_WAREHOUSE_STAFF_TABLE = """
    CREATE TABLE warehouse_staff (
        id TEXT PRIMARY KEY,
        password TEXT,
        name TEXT,
        email TEXT,
        phone_number TEXT,
        birthday DATE,
        warehouse_id TEXT,
        keeper_id TEXT,
        FOREIGN KEY (warehouse_id) REFERENCES warehouse(id),
        FOREIGN KEY (keeper_id) REFERENCES warehouse_keeper(id)
    )
"""

        // Table product_category
        val CREATE_PRODUCT_CATEGORY_TABLE = """
    CREATE TABLE product_category (
        id TEXT PRIMARY KEY,
        name TEXT,
        description TEXT
    )
"""

        // Table suppliers
        val CREATE_SUPPLIERS_TABLE = """
    CREATE TABLE suppliers (
        id INTEGER PRIMARY KEY,
        name TEXT,
        contact_name TEXT,
        contact_phone TEXT,
        contact_email TEXT,
        address TEXT
    )
"""

        // Table products
        val CREATE_PRODUCTS_TABLE = """
    CREATE TABLE products (
        id TEXT PRIMARY KEY,
        img_url TEXT,
        name TEXT,
        description TEXT,
        price REAL,
        cost REAL,
        inventory INTEGER,
        position TEXT,
        weight REAL,
        status INTEGER,
        supplier_id TEXT,
        product_category_id TEXT,
        FOREIGN KEY (supplier_id) REFERENCES suppliers(id),
        FOREIGN KEY (product_category_id) REFERENCES product_category(id)
    )
"""

        // Table shipping_carriers
        val CREATE_SHIPPING_CARRIERS_TABLE = """
    CREATE TABLE shipping_carriers (
        id TEXT PRIMARY KEY,
        name TEXT,
        contact_name TEXT,
        contact_email TEXT,
        contact_phone TEXT,
        address TEXT
    )
"""

        // Table purchase_orders
        val CREATE_PURCHASE_ORDERS_TABLE = """
    CREATE TABLE purchase_orders (
        id TEXT PRIMARY KEY,
        supplier_id INTEGER,
        shipping_carrier_id TEXT,
        order_date DATE,
        status TEXT,
        total_amount REAL,
        FOREIGN KEY (supplier_id) REFERENCES suppliers(id),
        FOREIGN KEY (shipping_carrier_id) REFERENCES shipping_carriers(id)
    )
"""

        // Table purchase_order_details
        val CREATE_PURCHASE_ORDER_DETAILS_TABLE = """
    CREATE TABLE purchase_order_details (
        id TEXT PRIMARY KEY,
        purchase_order_id TEXT,
        product_id TEXT,
        quantity INTEGER,
        price_per_unit REAL,
        FOREIGN KEY (purchase_order_id) REFERENCES purchase_orders(id),
        FOREIGN KEY (product_id) REFERENCES products(id)
    )
"""

        // Table stores
        val CREATE_STORES_TABLE = """
    CREATE TABLE stores (
        id TEXT PRIMARY KEY,
        name TEXT,
        contact_name TEXT,
        contact_email TEXT,
        contact_phone TEXT,
        address TEXT
    )
"""

        // Table sales_orders
        val CREATE_SALES_ORDERS_TABLE = """
    CREATE TABLE sales_orders (
        id TEXT PRIMARY KEY,
        store_id TEXT,
        shipping_carrier_id TEXT,
        order_date DATE,
        status TEXT,
        total_amount REAL,
        FOREIGN KEY (store_id) REFERENCES stores(id),
        FOREIGN KEY (shipping_carrier_id) REFERENCES shipping_carriers(id)
    )
"""

        // Table sales_order_details
        val CREATE_SALES_ORDER_DETAILS_TABLE = """
    CREATE TABLE sales_order_details (
        id TEXT PRIMARY KEY,
        sales_order_id TEXT,
        product_id TEXT,
        quantity INTEGER,
        price_per_unit REAL,
        FOREIGN KEY (sales_order_id) REFERENCES sales_orders(id),
        FOREIGN KEY (product_id) REFERENCES products(id)
    )
"""

        // Table payment
        val CREATE_PAYMENT_TABLE = """
    CREATE TABLE payment (
        id TEXT PRIMARY KEY,
        order_id TEXT,
        payment_method TEXT,
        payment_date DATE,
        amount REAL,
        FOREIGN KEY (order_id) REFERENCES sales_orders(id)
    )
"""


    }


    override fun onCreate(db: SQLiteDatabase?) {

        //warehouse table
        db?.execSQL(CREATE_WAREHOUSE_TABLE)
        //sample data for warehouse
        db?.execSQL("INSERT INTO warehouse VALUES ('WH001', 'Kho Hà Nội', 'Hà Nội', 'Nguyễn Văn A', '0123456789')")
        db?.execSQL("INSERT INTO warehouse VALUES ('WH002', 'Kho Hồ Chí Minh', 'Hồ Chí Minh', 'Nguyễn Văn B', '0123456789')")
        db?.execSQL("INSERT INTO warehouse VALUES ('WH003', 'Kho Đà Nẵng', 'Đà Nẵng', 'Nguyễn Văn C', '0123456789')")

        //warehouse_keeper table
        db?.execSQL(CREATE_WAREHOUSE_KEEPER_TABLE)
        //sample data for warehouse_keeper
        db?.execSQL("INSERT INTO warehouse_keeper VALUES ('WK001', 'password1', 'John Doe', 'john@example.com', '0123456789', '1990-01-01', 'WH001')")
        db?.execSQL("INSERT INTO warehouse_keeper VALUES ('WK002', 'password2', 'Jane Doe', 'jane@example.com', '0123456789', '1990-02-02', 'WH002')")
        db?.execSQL("INSERT INTO warehouse_keeper VALUES ('WK003', 'password3', 'Bob Smith', 'bob@example.com', '0123456789', '1990-03-03', 'WH003')")

        //warehouse_staff table
        db?.execSQL(CREATE_WAREHOUSE_STAFF_TABLE)
        //sample data for warehouse_staff
        db?.execSQL("INSERT INTO warehouse_staff VALUES ('WS001', 'password1', 'John Smith', 'john@example.com', '0123456789', '1990-01-01', 'WH001', 'WK001')")
        db?.execSQL("INSERT INTO warehouse_staff VALUES ('WS002', 'password2', 'Jane Smith', 'jane@example.com', '0123456789', '1990-02-02', 'WH002', 'WK002')")
        db?.execSQL("INSERT INTO warehouse_staff VALUES ('WS003', 'password3', 'Bob Johnson', 'bob@example.com', '0123456789', '1990-03-03', 'WH003', 'WK003')")

        //product_category table
        db?.execSQL(CREATE_PRODUCT_CATEGORY_TABLE)
        //sample data for product_category
        db?.execSQL("INSERT INTO product_category VALUES ('PC001', 'Điện thoại', 'Điện thoại')")
        db?.execSQL("INSERT INTO product_category VALUES ('PC002', 'Máy tính', 'Máy tính')")
        db?.execSQL("INSERT INTO product_category VALUES ('PC003', 'Máy ảnh', 'Máy ảnh')")
        db?.execSQL("INSERT INTO product_category VALUES ('PC004', 'Máy quay phim', 'Máy quay phim')")
        db?.execSQL("INSERT INTO product_category VALUES ('PC005', 'Máy in', 'Máy in')")

        //suppliers table
        db?.execSQL(CREATE_SUPPLIERS_TABLE)
        //sample data for suppliers
        db?.execSQL("INSERT INTO suppliers VALUES ('S001', 'Supplier 1', 'Contact 1', '123456789', 'contact1@example.com', 'Address 1')")
        db?.execSQL("INSERT INTO suppliers VALUES ('S002', 'Supplier 2', 'Contact 2', '987654321', 'contact2@example.com', 'Address 2')")
        db?.execSQL("INSERT INTO suppliers VALUES ('S003', 'Supplier 3', 'Contact 3', '456789123', 'contact3@example.com', 'Address 3')")

        //products table
        db?.execSQL(CREATE_PRODUCTS_TABLE)
        //make 20 sample products
        for (i in 1..20) {
            db?.execSQL("INSERT INTO products VALUES ('P00$i', 'https://picsum.photos/200', 'Product $i', 'Description $i', 1000000, 500000, 100, 'A1', 1.0, 1, 'S00${i % 3 + 1}', 'PC00${i % 5 + 1}')")
        }

        //shipping_carriers table
        db?.execSQL(CREATE_SHIPPING_CARRIERS_TABLE)
        db?.execSQL("INSERT INTO shipping_carriers VALUES ('SC001', 'Carrier 1', 'Contact 1', 'contact1@example.com', '123456789', 'Address 1')")
        db?.execSQL("INSERT INTO shipping_carriers VALUES ('SC002', 'Carrier 2', 'Contact 2', 'contact2@example.com', '987654321', 'Address 2')")
        db?.execSQL("INSERT INTO shipping_carriers VALUES ('SC003', 'Carrier 3', 'Contact 3', 'contact3@example.com', '456789123', 'Address 3')")

        db?.execSQL(CREATE_PURCHASE_ORDERS_TABLE)
        db?.execSQL(CREATE_PURCHASE_ORDER_DETAILS_TABLE)
        db?.execSQL(CREATE_STORES_TABLE)
        db?.execSQL(CREATE_SALES_ORDERS_TABLE)
        db?.execSQL(CREATE_SALES_ORDER_DETAILS_TABLE)
        db?.execSQL(CREATE_PAYMENT_TABLE)

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        //drop all tables
        db?.execSQL("DROP TABLE IF EXISTS warehouse")
        db?.execSQL("DROP TABLE IF EXISTS warehouse_keeper")
        db?.execSQL("DROP TABLE IF EXISTS warehouse_staff")
        db?.execSQL("DROP TABLE IF EXISTS product_category")
        db?.execSQL("DROP TABLE IF EXISTS suppliers")
        db?.execSQL("DROP TABLE IF EXISTS products")
        db?.execSQL("DROP TABLE IF EXISTS shipping_carriers")
        db?.execSQL("DROP TABLE IF EXISTS purchase_orders")
        db?.execSQL("DROP TABLE IF EXISTS purchase_order_details")
        db?.execSQL("DROP TABLE IF EXISTS stores")
        db?.execSQL("DROP TABLE IF EXISTS sales_orders")
        db?.execSQL("DROP TABLE IF EXISTS sales_order_details")
        db?.execSQL("DROP TABLE IF EXISTS payment")

    }
}