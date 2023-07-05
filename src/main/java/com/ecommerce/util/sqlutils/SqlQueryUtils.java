package com.ecommerce.util.sqlutils;

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

    final static public class TABLE_PROD_IMAGES {
        public static String name = "productImages";

        public static String id = "id";
        public static String pId = "pId";
        public static String image = "image";
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

    final static public class TABLE_CART {
        public static String TABLE_NAME = "cart";
        public static String id = "id";
        public static String userId = "userId";
        public static String total = "total";
        public static String createdAt = "createdAt";
        public static String updatedAt = "updatedAt";
    }

    final static public class TABLE_CART_ITEMS {
        public static String TABLE_NAME = "cartItems";
        public static String id = "id";
        public static String prodId = "prodId";
        public static String cartId = "cartId";
        public static String qty = "qty";
        public static String createdAt = "createdAt";
        public static String updatedAt = "updatedAt";
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
            "searchQuery" +
            "LIMIT 20 OFFSET pageOffset";
    public static final String VALID_CART = "SELECT COUNT(*) FROM " + TABLE_CART.TABLE_NAME + " where id:cartId";

    public static final String LOAD_INV_PRODUCTS_FOR_A_CART = "SELECT p.id, p.sku, p.price, s.name, s.description, s.unit, s.weight, ci.qty FROM product p , sku s ,cartItems ci,cart c  WHERE p.sku= s.id and c.id = :id and c.id=ci.cartId and ci.prodId=p.id";
    public static final String CREATE_CART = "INSERT INTO "
            + TABLE_CART.TABLE_NAME
            + " (" + TABLE_CART.userId + ",\n"
            + " " + TABLE_CART.total + ")\n"
            + " VALUES (?,?)";

    public static final String INSERT_INTO_CART = "INSERT INTO "
            + TABLE_CART_ITEMS.TABLE_NAME
            + " (" + TABLE_CART_ITEMS.cartId + ",\n"
            + " " + TABLE_CART_ITEMS.prodId + ",\n"
            + " " + TABLE_CART_ITEMS.qty + ")\n"
            + " VALUES (?,?,?)";
    public static final String UPDATE_INTO_CART = "UPDATE "
            + TABLE_CART_ITEMS.TABLE_NAME +" SET"
            + " " + TABLE_CART_ITEMS.qty
            + " VALUES (?,?,?,?)";
    /*UPDATE cartItems SET qty =5 WHERE prodId=22 and cartId=12
     */
    public static final String UPDATE_CART_ITEM = "INSERT INTO "
            + TABLE_CART_ITEMS.TABLE_NAME
            + " (" + TABLE_CART_ITEMS.cartId + ",\n"
            + " " + TABLE_CART_ITEMS.prodId + ",\n"
            + " " + TABLE_CART_ITEMS.qty + ")\n"
            + " VALUES (?,?,?)";

}

