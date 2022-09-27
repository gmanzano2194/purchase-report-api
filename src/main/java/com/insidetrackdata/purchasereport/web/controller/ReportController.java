package com.insidetrackdata.purchasereport.web.controller;

import static com.insidetrackdata.purchasereport.filter.CustomFilter.filterByCategory;
import static com.insidetrackdata.purchasereport.filter.CustomFilter.filterByCustomerLeaf;
import static com.insidetrackdata.purchasereport.filter.CustomFilter.filterByCustomerRoot;
import static com.insidetrackdata.purchasereport.filter.CustomFilter.filterByDistributorLeaf;
import static com.insidetrackdata.purchasereport.filter.CustomFilter.filterByDistributorRoot;
import static com.insidetrackdata.purchasereport.filter.CustomFilter.filterByManufacturer;
import static com.insidetrackdata.purchasereport.filter.CustomFilter.filterByPackSize;
import static com.insidetrackdata.purchasereport.filter.CustomFilter.filterByPrice;
import static com.insidetrackdata.purchasereport.filter.CustomFilter.filterByProductDescription;
import static com.insidetrackdata.purchasereport.filter.CustomFilter.filterByQuantity;
import static com.insidetrackdata.purchasereport.filter.CustomFilter.filterByUnitType;

import com.insidetrackdata.purchasereport.domain.PurchaseDetail;
import com.insidetrackdata.purchasereport.service.PurchaseDetailService;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Reports Controller.
 *
 * @author  Giancarlo Manzano
 * @version 1.0
 * @since   2022-09-27
 */
@RestController
@RequestMapping("/v1/purchase-details")
@RequiredArgsConstructor
public class ReportController {
  private final PurchaseDetailService purchaseDetailService;

  /**
   * Method that returns the report of purchases paginated.
   *
   * @author  Giancarlo Manzano
   * @since   2022-09-27
   */
  @GetMapping("/report")
  public ResponseEntity<List<PurchaseDetail>> getReport(
      @RequestParam("customerRoot") Optional<String> customerRoot,
      @RequestParam("customerLeaf") Optional<String> customerLeaf,
      @RequestParam("productDescription") Optional<String> productDescription,
      @RequestParam("packSize") Optional<String> packSize,
      @RequestParam("unitType") Optional<String> unitType,
      @RequestParam("category") Optional<String> category,
      @RequestParam("distributorRoot") Optional<String> distributorRoot,
      @RequestParam("distributorLeaf") Optional<String> distributorLeaf,
      @RequestParam("manufacturer") Optional<String> manufacturer,
      @RequestParam("quantity") Optional<Integer> quantity,
      @RequestParam("price") Optional<Double> price,
      @RequestParam("page") Integer page,
      @RequestParam("pageSize") Integer pageSize) {
    List<PurchaseDetail> purchaseDetails = purchaseDetailService.getAll().stream()
        .filter(filterByCustomerRoot(customerRoot.orElse("")))
        .filter(filterByCustomerLeaf(customerLeaf.orElse("")))
        .filter(filterByProductDescription(productDescription.orElse("")))
        .filter(filterByPackSize(packSize.orElse("")))
        .filter(filterByUnitType(unitType.orElse("")))
        .filter(filterByCategory(category.orElse("")))
        .filter(filterByDistributorRoot(distributorRoot.orElse("")))
        .filter(filterByDistributorLeaf(distributorLeaf.orElse("")))
        .filter(filterByManufacturer(manufacturer.orElse("")))
        .filter(filterByQuantity(quantity.orElse(0)))
        .filter(filterByPrice(price.orElse(0.0)))
        .skip((page - 1) * pageSize)
        .limit(pageSize)
        .collect(Collectors.toList());
    return Optional.of(purchaseDetails)
        .map(report -> new ResponseEntity<>(report, HttpStatus.OK))
        .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }
}
