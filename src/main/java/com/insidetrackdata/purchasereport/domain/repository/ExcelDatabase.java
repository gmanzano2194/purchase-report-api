package com.insidetrackdata.purchasereport.domain.repository;

import static com.insidetrackdata.purchasereport.util.Utils.checkIfRowIsEmpty;

import com.insidetrackdata.purchasereport.domain.PurchaseDetail;
import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

/**
 * Class that obtains the data from the excel file.
 *
 * @author  Giancarlo Manzano
 * @version 1.0
 * @since   2022-09-27
 */
@Component
@Slf4j
public class ExcelDatabase {
  /**
   * Obtains all data.
   *
   * @author  Giancarlo Manzano
   * @version 1.0
   * @since   2022-09-27
   */
  public List<PurchaseDetail> getAll() {
    List<PurchaseDetail> purchaseDetailList = new ArrayList<>();
    try {
      ClassLoader classloader = Thread.currentThread().getContextClassLoader();
      InputStream cpResource = classloader.getResourceAsStream("database.xlsx");
      File tmpFile = File.createTempFile("database", "xlsx");
      FileUtils.copyInputStreamToFile(cpResource, tmpFile);
      XSSFWorkbook workbook = new XSSFWorkbook(tmpFile);
      XSSFSheet sheet = workbook.getSheetAt(0);
      sheet.removeRow(sheet.getRow(0));
      sheet.forEach(row -> {
        if (!checkIfRowIsEmpty(row)) {
          purchaseDetailList.add(PurchaseDetail.builder()
              .purchaseDate(row.getCell(0).getDateCellValue())
              .invoice((int) row.getCell(1).getNumericCellValue())
              .customerRoot(row.getCell(2).getStringCellValue())
              .customerLeaf(row.getCell(3).getStringCellValue())
              .productDescription(row.getCell(4).getStringCellValue())
              .sizeType(row.getCell(5).getStringCellValue())
              .unitType(row.getCell(6).getStringCellValue())
              .category(row.getCell(7).getStringCellValue())
              .distributorRoot(row.getCell(8).getStringCellValue())
              .distributorLeaf(row.getCell(9).getStringCellValue())
              .manufacturer(row.getCell(10).getStringCellValue())
              .quantity((int) row.getCell(1).getNumericCellValue())
              .price(row.getCell(1).getNumericCellValue())
              .total(row.getCell(1).getNumericCellValue())
              .build());
        }
      });
      tmpFile.deleteOnExit();
    } catch (Exception e) {
      log.error("An error occurred trying to read the excel file", e);
    }
    return purchaseDetailList;
  }
}