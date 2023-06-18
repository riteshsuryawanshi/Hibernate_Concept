package com.jbk.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.OptimisticLockException;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;
import org.hibernate.query.Query;

import com.jbk.config.HibernateConfiguration;
import com.jbk.entity.Product;
import com.jbk.entity.ProductModel;

public class ProductDao {
	SessionFactory sessionFactory = HibernateConfiguration.getSessionFactory();

	public String addProduct(Product product) {

		String msg = null;
		Session session = null;
		try {
			session = sessionFactory.openSession();

			session.save(product);
			session.beginTransaction().commit();
			msg = "Added";

		} catch (Exception e) {
			msg = "Product already Exists with id= " + product.getProductId();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return msg;

	}

	public Product getProductById(long productId) {
		Session session = null;
		Product product = null;
		try {
			session = sessionFactory.openSession();

			product = session.get(Product.class, productId); // alt+shift+L
			
			System.out.println("p1 "+product);
			
		Product	product2 = session.get(Product.class, productId); // alt+shift+L
		System.out.println("p2 "+product2);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}

		return product;

	}

	public String deleteProductById(long productId) {
		Session session = null;
		String msg = null;
		try {
			session = sessionFactory.openSession();
			Product product = getProductById(productId);
			if (product != null) {
				session.delete(product);
				session.beginTransaction().commit();
				msg = "Product Deleted";
			} else {
				msg = "Product Not found With Id = " + productId;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}

		return msg;

	}

	public String updateProduct(Product product) {
		Session session = null;
		String msg = null;
		try {
			session = sessionFactory.openSession();
			session.update(product);
			session.beginTransaction().commit();
			msg = "Product Update";
		} catch (OptimisticLockException e) {
			msg = "Product Not Found To Update With Id = " + product.getProductId();
		} catch (Exception e) {
			e.printStackTrace();
			msg = "Somethig wrong while updating";

		} finally {
			if (session != null) {
				session.close();
			}
		}

		return msg;

	}

	public List<Product> getAllProducts() {
		Session session = null;
		List<Product> list = null;
		try {
			session = sessionFactory.openSession();

			Criteria criteria = session.createCriteria(Product.class);
			// criteria.setMaxResults(3);
			criteria.setFirstResult(2);
			criteria.setMaxResults(3);
			list = criteria.list();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;

	}

	public List<Product> sortProduct(String orderType, String propertyName) {
		Session session = null;
		List<Product> list = null;
		try {
			session = sessionFactory.openSession();

			Criteria criteria = session.createCriteria(Product.class);

			if (orderType.equals("asc")) {
				criteria.addOrder(Order.asc(propertyName));
			} else {
				criteria.addOrder(Order.desc(propertyName));
			}
			list = criteria.list();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;

	}

	public List<Product> restrictionEx() {
		Session session = null;
		List<Product> list = null;
		double price = 20;
		String name = "pen";
		try {
			session = sessionFactory.openSession();

			Criteria criteria = session.createCriteria(Product.class);
			// criteria.addOrder(Order.asc("productPrice"));
			// criteria.add(Restrictions.gt("productPrice", val));

			Criterion proPrice = Restrictions.eq("productPrice", price);
			Criterion proName = Restrictions.eq("productName", name);

			criteria.add(Restrictions.and(proPrice, proName));

			list = criteria.list();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;

	}

	public List<Product> projectionEx() {
		Session session = null;
		List<Product> list = null;
		double maxPrice;
		try {
			session = sessionFactory.openSession();

			Criteria criteria1 = session.createCriteria(Product.class);

			criteria1.setProjection(Projections.min("productPrice"));

			maxPrice = (Double) criteria1.list().get(0);

			Criteria criteria2 = session.createCriteria(Product.class);

			criteria2.add(Restrictions.eq("productPrice", maxPrice));

			list = criteria2.list();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;

	}

	public List<ProductModel> queryEx() {
		Session session = null;
		List<Object[]> list = null;
		List<ProductModel> products = new ArrayList();
		ProductModel model = null;

		double price = 1000;

		try {
			session = sessionFactory.openSession();

			Query query = session.createQuery(
					"SELECT p.productId,p.productName,p.productPrice FROM Product p where productPrice > :price");
			query.setParameter("price", price);
			list = query.list();

			Iterator it = list.iterator();

			while (it.hasNext()) {
				model = new ProductModel();

				Object[] obj = (Object[]) it.next();

				model.setProductId(obj[0]);
				model.setProductName(obj[1]);
				model.setProductPrice(obj[2]);
				products.add(model);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return products;

	}

}
