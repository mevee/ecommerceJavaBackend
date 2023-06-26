package com.ecommerce.repository;

import com.ecommerce.model.Category;
import com.ecommerce.model.request.product.AddProductRequest;
import com.ecommerce.model.response.product.InventoryProduct;
import com.ecommerce.model.response.product.Product;
import com.ecommerce.model.sku.ProductImage;
import com.ecommerce.model.sku.Sku;
import com.ecommerce.repository.repo.InventoryRepository;
import com.ecommerce.rowmapper.CategoryListMapper;
import com.ecommerce.rowmapper.InventoryProductListMapper;
import com.ecommerce.rowmapper.ProductImagesMapper;
import com.ecommerce.rowmapper.SkuListMapper;
import com.ecommerce.util.sqlutils.SqlQueryUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@Slf4j
public class InventoryRepositoryImpl implements InventoryRepository {

    @Autowired
    private JdbcTemplate jdbc;

    @Override
    public Product getProductDetail(String productId, String cartId, String userId) {
        return null;
    }

    @Override
    public boolean updateProductImage(ProductImage product) throws Exception {
        return false;
    }

    @Override
    public boolean removeProductImage(ProductImage inventoryProduct) throws Exception {
        return false;
    }

    @Override
    public List<ProductImage> getProductImage(String productId) throws Exception {
        log.info("----- getProductImage(" + productId + ")-------------");
        List<ProductImage> imageList = null;
        String query = "SELECT *,imageLink as image FROM " + SqlQueryUtils.TABLE_PROD_IMAGES.name + " WHERE pId=" + productId;
        log.info("----- query " + query + "-------------");
        int prodId = 0;
        try {
            prodId =Integer.parseInt(productId);
        }catch (Exception e){
        }

        if (Integer.parseInt(productId)<=0){
            return null;
        }else{
            try {
                imageList = jdbc.queryForObject(query,new ProductImagesMapper());
                log.info("----- PID " + prodId +"imageList :" +imageList.size()+"-------------");
            }catch (Exception e){
                log.info("----- imageList Exception " + e.getMessage() + "-------------");
            }
        }

        return imageList;
    }

    private boolean checkHasNext(String searchQuery, int nextOffset) {
        log.info("----- checkHasNext() -------------");

        String query = SqlQueryUtils.LOAD_INV_PRODUCTS.replace("searchQuery", searchQuery);
        query = query.replace("?", searchQuery);
        query = query.replace("pageOffset", String.valueOf(nextOffset));

        log.info("----- QUERY" + query + "-------------");

        int result = 0;
        try {
            List<Product> products = jdbc.queryForObject(query, new InventoryProductListMapper());
            if (products != null) {
                result = products.size();
            }
            log.info("----- hasNext count " + result + "-------------");
        } catch (Exception e) {
            log.info("----- hasNext count Exception " + e.getMessage() + "-------------");
            result = 0;
        }
        if (result > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public InventoryProduct getProducts(String categoryId, String searchQuery, String userId, String cartId, int page) {
        InventoryProduct result = new InventoryProduct();
        List<Product> productList = new ArrayList<>();
        log.info("----- getProducts() -------------");
        if (searchQuery == null) {
            searchQuery = "";
        } else {
            searchQuery = SqlQueryUtils.SEARCH_INV_PRODUCTS.replace("searchQuery", searchQuery);
        }
        int offset = 0;
        if (page != 0) {
            offset = page * 20;
        }
        String query = SqlQueryUtils.LOAD_INV_PRODUCTS.replace("searchQuery", searchQuery);
        query = query.replace("?", searchQuery);
        query = query.replace("pageOffset", String.valueOf(offset));

        log.info("-----searchQuery " + searchQuery + " -------------");
        log.info("-----QUERY " + query + " -------------");
        try {
            productList = jdbc.queryForObject(query, new InventoryProductListMapper());
            result.setProducts(productList);
            if (productList != null) {
                for (int i = 0;i <productList.size();i++){
                    Product element = productList.get(i);
                    log.info("-----foreach " + element + " -------------");
                    try {
                        element.setImages(getProductImage(""+element.getId()));
                    }catch (Exception e){
                        log.info("-----Exception image " + e.getMessage() + " -------------");
                    }
                }
            }

            boolean hasNext = checkHasNext(searchQuery, (page + 1) * 20);
            log.info("-----hasNext " + hasNext + " -------------");
            result.setHasNext(hasNext);

            if (hasNext)
                result.setNextIndex(page + 1);
        } catch (Exception e) {
            log.info("-----Exception " + e.getMessage() + " -------------");
            result.setHasNext(false);
            result.setProducts(productList);
        }

        log.info("-----result size " + productList.size() + " -------------");

        return result;
    }

    @Override
    public List<Category> getCategory() {
        List<Category> categoryList = new ArrayList<>();

        String query = "SELECT * FROM " + SqlQueryUtils.TABLE_CATEGORY.TABLE_NAME;
        log.info("--------Query " + query + " ----");
        try {
            List<Category> catList = jdbc.queryForObject(query, new CategoryListMapper());
            categoryList = catList;
        } catch (Exception e) {
            log.info("--------Error " + e.getMessage() + " ----");
        }
        log.info("--------Query RESULT" + categoryList + " ----");

        return categoryList;
    }

    @Override
    public List<Category> getSubCategory(String categoryId) throws Exception {
        List<Category> categoryList = new ArrayList<>();
        if (isValidCategory(categoryId)) {

            String query = "SELECT * FROM " + SqlQueryUtils.TABLE_CATEGORY.TABLE_NAME + " c WHERE c.subCat =?";
            log.info("--------Query " + query + " ----");
            try {
                List<Category> catList = jdbc.queryForObject(query, new Object[]{categoryId}, new CategoryListMapper());
                categoryList = catList;
            } catch (Exception e) {
                log.info("--------Error " + e.getMessage() + " ----");
            }
            log.info("--------Query RESULT" + categoryList + " ----");
        } else {
            throw new Exception("Invalid Category");
        }

        return categoryList;
    }

    @Override
    public boolean isValidCategory(String categoryId) {
        String query = "SELECT COUNT(*) FROM " + SqlQueryUtils.TABLE_CATEGORY.TABLE_NAME + " WHERE id = ?";
        int userId = -1;
        try {
            userId = Integer.parseInt(categoryId);
        } catch (NumberFormatException exception) {

        }
        if (userId <= 0) {
            return false;
        }

        log.info("-------- Is Exists By category Query----------------");
        log.info(query);
        Integer count = jdbc.queryForObject(query, new Object[]{userId}, Integer.class);
        log.info("----is Exists By category count: " + (count) + " -------------");
        return count != null && count > 0;
    }

    @Override
    public boolean updateCategory(Category sku) throws Exception {
        return false;
    }

    @Override
    public boolean removeCategory(Category sku) throws Exception {
        return false;
    }


    @Override
    public boolean addSku(Sku request) throws Exception {

        if (request.getName() == null || request.getName().isEmpty()) {
            throw new Exception("Invalid Name");
        } else if (request.getDescription() == null || request.getDescription().isEmpty()) {
            throw new Exception("Invalid Name");
        } else if (request.getUnit() == 0) {
            throw new Exception("Invalid Unit");
        } else if (request.getWeight() == 0) {
            throw new Exception("Invalid weight");
        } else {
            log.info("--------Added SKU QUERY " + SqlQueryUtils.ADD_SKU + " --------");

            int count = jdbc.update(SqlQueryUtils.ADD_SKU, new Object[]{
                    request.getName(),
                    request.getDescription(),
                    request.getState(),
                    request.getHighValue(),
                    request.getWeight(),
                    request.getUnit(),
                    "N"
            });
            if (count > 0) {
                log.info("-------- SKU Added successfully ----");
                return true;
            } else {
                log.error("--------------------- Save SKU Data Detail inserted failed ----------------");
                return false;
            }
        }
    }

    @Override
    public boolean removeSku(Sku sku) throws Exception {
        return false;
    }


    @Override
    public List<Sku> getAllSku() throws Exception {
        List<Sku> categoryList = new ArrayList<>();
        String query = "Select * from " + SqlQueryUtils.TABLE_SKU.TABLE_NAME;
        log.info("--------Query " + query + " ----");
        try {
            List<Sku> catList = jdbc.queryForObject(query, new SkuListMapper());
            categoryList = catList;
        } catch (Exception e) {
            log.info("--------Error " + e.getMessage() + " ----");
        }
        log.info("--------Query RESULT" + categoryList + " ----");

        return categoryList;
    }

    @Override
    public boolean isValidProduct(int skuId) throws Exception {
        String query = "SELECT COUNT(*) FROM " + SqlQueryUtils.TABLE_SKU.TABLE_NAME + " WHERE id=?";
        log.info("-------------------QUERY " + query + "-------------------");
        try {
            int rowCount = jdbc.queryForObject(query, new Object[]{skuId}, Integer.class);
            log.info("-------------------QUERY RESULT rowCount" + rowCount + "-------------------");

            if (rowCount > 0) {
                return true;
            } else {
                return false;
            }

        } catch (Exception e) {

        }

        return false;
    }

    @Override
    public boolean addProduct(AddProductRequest product) throws Exception {
        boolean isValidSku = isValidProduct(product.getSku());
        if (product.getSku() <= 0) {
            throw new Exception("Invalid SKU");
        } else if (product.getPrice() <= 0) {
            throw new Exception("Invalid price");
        } else if (product.getInventoryQty() < 0) {
            throw new Exception("Invalid inventory qty");
        } else if (!isValidSku) {
            throw new Exception("Invalid SKU id " + product.getSku());
        } else {
            boolean isSavedSuccess = false;
            log.info("-------- Query " + SqlQueryUtils.ADD_PRODUCT + "------");
            int rowCount = jdbc.update(SqlQueryUtils.ADD_PRODUCT, new Object[]{
                    product.getId(),
                    product.getSku(),
                    product.getPrice(),
                    product.getInventoryQty(),
                    product.getTags()
            });

            log.info("-------- Query Result rowCount " + rowCount + " ------");

            if (rowCount > 0) {
                return true;
            } else {
                return false;
            }
        }
    }
}
