package com.insidetrackdata.purchasereport.util;

import static com.insidetrackdata.purchasereport.util.Constants.JWT_KEY;

import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Objects;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;

/**
 * Class that contains utils.
 *
 * @author  Giancarlo Manzano
 * @version 1.0
 * @since   2022-09-27
 */
public final class Utils {
  /**
   * Method that returns the Secret Key for the JWT.
   *
   * @author  Giancarlo Manzano
   * @since   2022-09-27
   */
  public static SecretKey getKey()  {
    String base64Key = DatatypeConverter.printBase64Binary(JWT_KEY.getBytes());
    byte[] secretBytes = DatatypeConverter.parseBase64Binary(base64Key);
    return new SecretKeySpec(secretBytes, SignatureAlgorithm.HS256.getJcaName());
  }

  /**
   * Method that checks if an Excel Row is empty.
   *
   * @author  Giancarlo Manzano
   * @since   2022-09-27
   */
  public static boolean checkIfRowIsEmpty(Row row) {
    if (Objects.isNull(row)) {
      return true;
    }
    if (row.getLastCellNum() <= 0) {
      return true;
    }
    for (int cellNum = row.getFirstCellNum(); cellNum < row.getLastCellNum(); cellNum++) {
      Cell cell = row.getCell(cellNum);
      if (!Objects.isNull(cell) && cell.getCellType() != CellType.BLANK
          && StringUtils.isNotBlank(cell.toString())) {
        return false;
      }
    }
    return true;
  }
}
