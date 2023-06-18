package com.jbk.utility;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.jbk.entity.Product;

public class ProductUtility {

	public static Product prepareProductData() {
		Product product = null;
		Scanner scanner = new Scanner(System.in);

		try {
			System.out.println("Enter Product Id");
			long productId = scanner.nextLong();

			if (productId <= 0) {
				return null;

			}

			System.out.println("Enter Product Name");
			String productName = scanner.next();

			System.out.println("Enter Supplier Id");
			long supplierId = scanner.nextLong();

			System.out.println("Enter Category Id");
			long categoryId = scanner.nextLong();

			System.out.println("Enter Product QTY");
			int productQTY = scanner.nextInt();

			System.out.println("Enter Product Price");
			double productPrice = scanner.nextDouble();

			product = new Product(productId, productName, supplierId, categoryId, productQTY, productPrice);

		} catch (InputMismatchException e) {
			return null;

		}

		boolean isValid = validateProduct(product);

		if (isValid) {
			return product;
		} else {
			return null;
		}

	}

	public static boolean validateProduct(Product product) {
		boolean isValid = true;
		if (product.getProductId() <= 0) {
			isValid = false;

		}
		if (product.getProductName() == null) {
			isValid = false;
			return isValid;
		} else {
			  String regex = ".*\\d.*";  // regex to check if string contains any numbers
		      Pattern pattern = Pattern.compile(regex);  // compiles the regex
		      // find match between given string and pattern
		      Matcher matcherText = pattern.matcher(product.getProductName());  
		      Boolean isAnyDigit = matcherText.matches();
		      if(isAnyDigit) {
		    	isValid = false;
				return isValid;  
		      }
		      

		}
		if (product.getSupplierId() <= 0) {
			isValid = false;
			return isValid;
		}
		if (product.getCategoryId() <= 0) {
			isValid = false;
			return isValid;
		}
		if (product.getProductQTY() <= 0) {
			isValid = false;
			return isValid;
		}
		if (product.getProductPrice() <= 0) {
			isValid = false;
			return isValid;
		}
		return isValid;

	}

}
