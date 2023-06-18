package com.jbk;

import java.util.List;
import java.util.Scanner;

import com.jbk.dao.ProductDao;
import com.jbk.entity.Product;
import com.jbk.entity.ProductModel;
import com.jbk.utility.ProductUtility;

public class ProductController {

	public static void main(String[] args) {
		ProductDao dao = new ProductDao();
		Scanner scanner = new Scanner(System.in);
		int choice;
		char c;
		do {
			System.out.println("Press 1 For Save Product");
			System.out.println("Press 2 For get Product By Id");
			System.out.println("Press 3 for delete product");
			System.out.println("Press 4 for update product");

			System.out.println("Press 5 for getAllProducts");
			System.out.println("Press 6 for sort product");

			System.out.println("Press 7 for Restriction Ex");
			System.out.println("press 8 for Projection Ex");

			System.out.println("press 9 for Query Ex");

			choice = scanner.nextInt();

			switch (choice) {
			case 1: {
				Product product = ProductUtility.prepareProductData();

				if (product != null) {
					String msg = dao.addProduct(product);
					System.out.println(msg);
				} else {
					System.out.println("Product Not Valid");
				}

				break;
			}

			case 2: {
				System.out.println("Enter Product Id");
				long productId = scanner.nextLong();
				Product product = dao.getProductById(productId);
				if (product != null) {
					System.out.println(product);
				} else {
					System.out.println("Product Not Found With Id =" + productId);
				}
				break;
			}

			case 3: {

				System.out.println("Enter Product Id");
				long productId = scanner.nextLong();
				String msg = dao.deleteProductById(productId);
				System.out.println(msg);
				break;
			}

			case 4: {
				Product product = ProductUtility.prepareProductData();
				if (product != null) {
					String msg = dao.updateProduct(product);
					System.out.println(msg);
				} else {
					System.out.println("Product Not Valid");
				}

				break;

			}
			case 5: {

				List<Product> list = dao.getAllProducts();

				if (list.isEmpty() || list == null) {
					System.out.println("Product Not Founds");
				} else {
					for (Product product : list) {
						System.out.println(product);
					}
				}

				break;
			}

			case 6: {

				System.out.println("Enter order type asc/desc");
				String orderType = scanner.next();
				System.out.println("Enter property name");
				String propertyName = scanner.next();
				List<Product> list = dao.sortProduct(orderType, propertyName);

				if (list.isEmpty() || list == null) {
					System.out.println("Product Not Founds");
				} else {
					for (Product product : list) {
						System.out.println(product);
					}
				}

				break;
			}

			case 7: {
				List<Product> list = dao.restrictionEx();
				if (list.isEmpty() || list == null) {
					System.out.println("Product Not Founds");
				} else {
					for (Product product : list) {
						System.out.println(product);
					}
				}
				break;
			}

			case 8: {

				List<Product> list = dao.projectionEx();
				for (Product product : list) {
					System.out.println(product);
				}

				break;
			}

			case 9: {
				List<ProductModel> list = dao.queryEx();
				for (ProductModel product : list) {
					System.out.println(product);
				}
				break;
			}

			default:
				System.out.println("Invalid Choice");
				break;
			}
			System.out.println("Do you want to continue y/n ");
			c = scanner.next().charAt(0);

		} while (c == 'y' || c == 'Y');

		System.out.println("App Terminated");

	}

}
