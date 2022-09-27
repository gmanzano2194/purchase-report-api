package com.insidetrackdata.purchasereport.service;

import com.insidetrackdata.purchasereport.domain.PurchaseDetail;
import com.insidetrackdata.purchasereport.domain.repository.ExcelDatabase;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Service to obtain the purchase detail data.
 *
 * @author  Giancarlo Manzano
 * @version 1.0
 * @since   2022-09-27
 */
@RequiredArgsConstructor
@Service
public class PurchaseDetailService {
  private final ExcelDatabase excelDatabase;

  public List<PurchaseDetail> getAll() {
    return excelDatabase.getAll();
  }
}
