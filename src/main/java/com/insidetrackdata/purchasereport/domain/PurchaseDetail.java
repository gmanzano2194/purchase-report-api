package com.insidetrackdata.purchasereport.domain;

import java.util.Date;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Purchase Detail entity.
 *
 * @author  Giancarlo Manzano
 * @version 1.0
 * @since   2022-09-27
 */
@Getter
@Setter
@Builder
@ToString
public class PurchaseDetail {
  private Date purchaseDate;
  private Integer invoice;
  private String customerRoot;
  private String customerLeaf;
  private String productDescription;
  private Double packSize;
  private String sizeType;
  private String unitType;
  private String category;
  private String distributorRoot;
  private String distributorLeaf;
  private String manufacturer;
  private Integer quantity;
  private Double price;
  private Double total;
}
