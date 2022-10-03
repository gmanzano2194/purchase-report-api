package com.insidetrackdata.purchasereport.filter;

import com.insidetrackdata.purchasereport.domain.PurchaseDetail;
import java.util.function.Predicate;
import org.apache.commons.lang3.StringUtils;

/**
 * Custom filter for reports.
 *
 * @author  Giancarlo Manzano
 * @version 1.0
 * @since   2022-09-27
 */
public class CustomFilter {
  /**
   * Filters the stream by customer root.
   *
   * @author  Giancarlo Manzano
   * @since   2022-09-27
   */
  public static Predicate<PurchaseDetail> filterByCustomerRoot(String customerRoot) {
    Predicate<PurchaseDetail> predicate = purchaseDetail -> true;
    if (StringUtils.isNotEmpty(customerRoot)) {
      predicate = predicate.and(purchaseDetail ->
          purchaseDetail.getCustomerRoot().equals(customerRoot.trim()));
    }
    return predicate;
  }

  /**
   * Filters the stream by customer leaf.
   *
   * @author  Giancarlo Manzano
   * @since   2022-09-27
   */
  public static Predicate<PurchaseDetail> filterByCustomerLeaf(String customerLeaf) {
    Predicate<PurchaseDetail> predicate = purchaseDetail -> true;
    if (StringUtils.isNotEmpty(customerLeaf)) {
      predicate = predicate.and(purchaseDetail ->
          purchaseDetail.getCustomerLeaf().equals(customerLeaf.trim()));
    }
    return predicate;
  }

  /**
   * Filters the stream by product description.
   *
   * @author  Giancarlo Manzano
   * @since   2022-09-27
   */
  public static Predicate<PurchaseDetail> filterByProductDescription(String productDescription) {
    Predicate<PurchaseDetail> predicate = purchaseDetail -> true;
    if (StringUtils.isNotEmpty(productDescription)) {
      predicate = predicate.and(purchaseDetail ->
          purchaseDetail.getProductDescription().contains(productDescription.trim()));
    }
    return predicate;
  }

  /**
   * Filters the stream by pack size.
   *
   * @author  Giancarlo Manzano
   * @since   2022-09-27
   */
  public static Predicate<PurchaseDetail> filterByPackSize(String packSize) {
    Predicate<PurchaseDetail> predicate = purchaseDetail -> true;
    if (StringUtils.isNotEmpty(packSize)) {
      predicate = predicate.and(purchaseDetail ->
          purchaseDetail.getPackSize().equals(packSize.trim()));
    }
    return predicate;
  }

  /**
   * Filters the stream by unit type.
   *
   * @author  Giancarlo Manzano
   * @since   2022-09-27
   */
  public static Predicate<PurchaseDetail> filterByUnitType(String unitType) {
    Predicate<PurchaseDetail> predicate = purchaseDetail -> true;
    if (StringUtils.isNotEmpty(unitType)) {
      predicate = predicate.and(purchaseDetail ->
          purchaseDetail.getUnitType().equals(unitType.trim()));
    }
    return predicate;
  }

  /**
   * Filters the stream by category.
   *
   * @author  Giancarlo Manzano
   * @since   2022-09-27
   */
  public static Predicate<PurchaseDetail> filterByCategory(String category) {
    Predicate<PurchaseDetail> predicate = purchaseDetail -> true;
    if (StringUtils.isNotEmpty(category)) {
      predicate = predicate.and(purchaseDetail ->
          purchaseDetail.getCategory().equals(category.trim()));
    }
    return predicate;
  }

  /**
   * Filters the stream by distributor root.
   *
   * @author  Giancarlo Manzano
   * @since   2022-09-27
   */
  public static Predicate<PurchaseDetail> filterByDistributorRoot(String distributorRoot) {
    Predicate<PurchaseDetail> predicate = purchaseDetail -> true;
    if (StringUtils.isNotEmpty(distributorRoot)) {
      predicate = predicate.and(purchaseDetail ->
          purchaseDetail.getDistributorRoot().equals(distributorRoot.trim()));
    }
    return predicate;
  }

  /**
   * Filters the stream by distributor leaf.
   *
   * @author  Giancarlo Manzano
   * @since   2022-09-27
   */
  public static Predicate<PurchaseDetail> filterByDistributorLeaf(String distributorLeaf) {
    Predicate<PurchaseDetail> predicate = purchaseDetail -> true;
    if (StringUtils.isNotEmpty(distributorLeaf)) {
      predicate = predicate.and(purchaseDetail ->
          purchaseDetail.getDistributorLeaf().equals(distributorLeaf.trim()));
    }
    return predicate;
  }

  /**
   * Filters the stream by manufacturer.
   *
   * @author  Giancarlo Manzano
   * @since   2022-09-27
   */
  public static Predicate<PurchaseDetail> filterByManufacturer(String manufacturer) {
    Predicate<PurchaseDetail> predicate = purchaseDetail -> true;
    if (StringUtils.isNotEmpty(manufacturer)) {
      predicate = predicate.and(purchaseDetail ->
          purchaseDetail.getManufacturer().equals(manufacturer.trim()));
    }
    return predicate;
  }

  /**
   * Filters the stream by quantity.
   *
   * @author  Giancarlo Manzano
   * @since   2022-09-27
   */
  public static Predicate<PurchaseDetail> filterByQuantity(Integer quantity) {
    Predicate<PurchaseDetail> predicate = purchaseDetail -> true;
    if (quantity > 0) {
      predicate = predicate.and(purchaseDetail ->
          purchaseDetail.getQuantity().equals(quantity));
    }
    return predicate;
  }

  /**
   * Filters the stream by price.
   *
   * @author  Giancarlo Manzano
   * @since   2022-09-27
   */
  public static Predicate<PurchaseDetail> filterByPrice(Double price) {
    Predicate<PurchaseDetail> predicate = purchaseDetail -> true;
    if (price > 0) {
      predicate = predicate.and(purchaseDetail -> purchaseDetail.getPrice().equals(price));
    }
    return predicate;
  }
}
