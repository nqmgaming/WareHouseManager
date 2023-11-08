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
        province_id TEXT,
        representative TEXT,
        phone_number TEXT,
        FOREIGN KEY (province_id) REFERENCES provinces(id)
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
        avatar TEXT,
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
        avatar TEXT,
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

        //Table provinces-vietnam
        val CREATE_PROVINCES_TABLE = """
        CREATE TABLE provinces (
        id TEXT PRIMARY KEY,
        name TEXT
        )
        """


    }


    override fun onCreate(db: SQLiteDatabase?) {

        //warehouse table
        db?.execSQL(CREATE_WAREHOUSE_TABLE)

        //warehouse_keeper table
        db?.execSQL(CREATE_WAREHOUSE_KEEPER_TABLE)

        //warehouse_staff table
        db?.execSQL(CREATE_WAREHOUSE_STAFF_TABLE)

        //product_category table
        db?.execSQL(CREATE_PRODUCT_CATEGORY_TABLE)

        //suppliers table
        db?.execSQL(CREATE_SUPPLIERS_TABLE)
        //sample data for suppliers

        //products table
        db?.execSQL(CREATE_PRODUCTS_TABLE)

        //shipping_carriers table
        db?.execSQL(CREATE_SHIPPING_CARRIERS_TABLE)
        db?.execSQL(CREATE_PURCHASE_ORDERS_TABLE)
        db?.execSQL(CREATE_PURCHASE_ORDER_DETAILS_TABLE)
        db?.execSQL(CREATE_STORES_TABLE)
        db?.execSQL(CREATE_SALES_ORDERS_TABLE)
        db?.execSQL(CREATE_SALES_ORDER_DETAILS_TABLE)
        db?.execSQL(CREATE_PAYMENT_TABLE)

        //provinces table
        db?.execSQL(CREATE_PROVINCES_TABLE)
        db?.execSQL("INSERT INTO provinces VALUES ('HN', 'Hà Nội')")
        db?.execSQL("INSERT INTO provinces VALUES ('HCM', 'Hồ Chí Minh')")
        db?.execSQL("INSERT INTO provinces VALUES ('HP', 'Hải Phòng')")
        db?.execSQL("INSERT INTO provinces VALUES ('DN', 'Đà Nẵng')")

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