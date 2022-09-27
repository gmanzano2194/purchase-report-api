package com.insidetrackdata.purchasereport.util;

import static com.insidetrackdata.purchasereport.util.Constants.JWT_KEY;

import io.jsonwebtoken.SignatureAlgorithm;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

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
}
