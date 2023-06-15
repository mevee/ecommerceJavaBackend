package com.example.todoapp.util.sqlutils;

public class SqlQueryUtils {
    final static public class TABLE_USER {
        public static String TABLE_NAME = "users";
        public static String COL_ID = "id";
        public static String COL_F_NAME = "fName";
        public static String COL_L_NAME = "lName";
        public static String COL_EMAIL = "email";
        public static String COL_PHONE = "phone";
        public static String COL_PASSWORD = "password";
        public static String COL_IMAGE = "image";
        public static String COL_TOKEN = "token";
        public static String COL_ROLE_ID = "roleId";
        public static String COL_COUNTRY_CODE = "countryCode";

    }


    final static public class TABLE_CATEGORY {
        public static String TABLE_NAME = "category";
        public static String COL_ID = "id";
        public static String COL_NAME = "name";
        public static String COL_DESCRIPTION = "description";
        public static String COL_SUB_CAT = "subCat";
    }

    final static public class TABLE_SKU {
        public static String TABLE_NAME = "sku";
        public static String COL_ID = "id";
        public static String COL_NAME = "name";
        public static String COL_DESCRIPTION = "description";
        public static String COL_state = "state";
        public static String highValue = "highValue";
        public static String weight = "weight";
        public static String unit = "unit";
        public static String liquidIndicator = "liquidIndicator";
    }


    final static public class TABLE_PRODUCT {
        public static String TABLE_NAME = "product";
        public static String id = "id";
        public static String sku = "sku";
        public static String price = "price";
        public static String inventoryQty = "inventoryQty";
        public static String tags = "tags";
    }
    /*INSERT INTO users(fName,lName,email,phone,countryCode,roleId,password) values("Oye","Raju","email@gmail.com","1234567890","IN",1,"123456");
     */

    public static final String REGISTER_USER = "INSERT INTO "
            + TABLE_USER.TABLE_NAME
            + " (" + TABLE_USER.COL_F_NAME + ",\n"
            + " " + TABLE_USER.COL_L_NAME + ",\n"
            + " " + TABLE_USER.COL_EMAIL + ",\n"
            + " " + TABLE_USER.COL_PHONE + ",\n"
            + " " + TABLE_USER.COL_COUNTRY_CODE + ",\n"
            + " " + TABLE_USER.COL_ROLE_ID + ",\n"
            + " " + TABLE_USER.COL_PASSWORD + ")\n"
            + " VALUES (?, ?, ?,?, ?, ?, ?)";

    public static final String ADD_SKU = "INSERT INTO "
            + TABLE_SKU.TABLE_NAME
            + " (" + TABLE_SKU.COL_NAME + ",\n"
            + " " + TABLE_SKU.COL_DESCRIPTION + ",\n"
            + " " + TABLE_SKU.COL_state + ",\n"
            + " " + TABLE_SKU.highValue + ",\n"
            + " " + TABLE_SKU.weight + ",\n"
            + " " + TABLE_SKU.unit + ",\n"
            + " " + TABLE_SKU.liquidIndicator + ")\n"
            + " VALUES(?,?,?,?,?,?,?)";
    public static final String REMOVE_SKU = "DELETE FROM " + TABLE_SKU.TABLE_NAME + " where id =?";
//    public static final String All_SKU = "Select * from " + TABLE_SKU.TABLE_NAME;

    public static final String All_CATEGORY = "Select * from " + TABLE_CATEGORY.TABLE_NAME;

    public static final String ADD_PRODUCT = "INSERT INTO "
            + TABLE_PRODUCT.TABLE_NAME
            + " (" + TABLE_PRODUCT.id + ",\n"
            + " " + TABLE_PRODUCT.sku + ",\n"
            + " " + TABLE_PRODUCT.price + ",\n"
            + " " + TABLE_PRODUCT.inventoryQty + ",\n"
            + " " + TABLE_PRODUCT.tags + ")\n"
            + " VALUES(?,?,?,?,?)";

    public static final String All_INV_PRODUCTS = "Select p.id, p.sku, p.price, p.inventoryQty, s.name, s.description, s.unit, s.weight\n" +
            "FROM product p\n" +
            "JOIN sku s ON s.id = p.sku";
    public static final String All_INV_PRODUCTS_COUNT = "Select COUNT(*) \n" +
            "FROM product p\n" +
            "JOIN sku s ON s.id = p.sku";

    public static final String SEARCH_INV_PRODUCTS = "WHERE s.name LIKE '%searchQuery%' or p.tags  LIKE '%searchQuery%' ";
     public static final String LOAD_INV_PRODUCTS = "SELECT p.id, p.sku, p.price, p.inventoryQty, s.name, s.description, s.unit, s.weight " +
            "FROM product p " +
            "JOIN sku s ON s.id = p.sku " +
             "searchQuery"+
            "LIMIT 20 OFFSET pageOffset";



}

