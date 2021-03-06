package app;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import db.DB;
import entities.Order;
import entities.OrderStatus;
import entities.Product;

public class Program {

	public static void main(String[] args) throws SQLException {
		
		Connection conn = DB.getConnection();
	
		Statement st = conn.createStatement();
		
//		ResultSet rs = st.executeQuery("select * from tb_product");
//		ResultSet rs = st.executeQuery("select * from tb_order");
		ResultSet rs = st.executeQuery("SELECT * FROM tb_order "
				+ "INNER JOIN tb_order_product ON tb_order.id = tb_order_product.order_id "
				+ "INNER JOIN tb_product ON tb_product.id = tb_order_product.product_id");
			
		Map<Long, Order> map = new HashMap<>(); // Map (Dictionary em JS e C#) = coleção de pares chave-valor - interface instaciada pela classe HashMap
		// Map utilizado aqui para não haver repetição do pedido, quando os produtos forem diferentes
		Map<Long, Product> prods = new HashMap<>(); // o mesmo é feito com produtos - cria-se o Map para evitar a repeticão
		
		while (rs.next()) {
			
			Long orderId = rs.getLong("order_id");
			if (map.get(orderId) == null) {
				Order order = instantiateOrder(rs); // o pedido é instanciado se não existir no Map
				map.put(orderId, order); // após ser instaciado, o pedido é salvo dentro do Map
			}
			
			Long productId = rs.getLong("product_id");
			if (prods.get(productId) == null) {
				Product p = instantiateProduct(rs);
				prods.put(productId, p);
			}
			
			// Associação do produto com o pedido dele - isto é, aqui acessamos a lista de produtos do pedido e adicionamos um novo produto a ela 
			map.get(orderId).getProducts().add(prods.get(productId));
			
			// Dessa maneira, nem o pedido nem o produto se repetirão
			
//			System.out.println(p);
//			System.out.println(order);
			
		}
		
		// keySet - vai retornar todos os ids de pedidos no Map
		// Para cada pedido contido no Map, o pedido vai ser impresso no output
		// Para cada produto p dentro da lista de produtos de cada pedido, imprima p
		
		for (Long orderId : map.keySet()) { 
			System.out.println(map.get(orderId));
			for (Product p : map.get(orderId).getProducts()) {
				System.out.println(p);
			}
			System.out.println(); // para saltar uma linha
		}
		
	}
	
	private static Product instantiateProduct(ResultSet rs) throws SQLException {
		Product p = new Product();
		p.setId(rs.getLong("product_id"));
		p.setDescription(rs.getString("description"));
		p.setName(rs.getString("name"));
		p.setImageUri(rs.getString("image_uri"));
		p.setPrice(rs.getDouble("price"));
		
		return p;
	}
	
	private static Order instantiateOrder(ResultSet rs) throws SQLException {
		Order order = new Order();
		order.setId(rs.getLong("order_id"));
		order.setLatitude(rs.getDouble("latitude"));
		order.setLongitude(rs.getDouble("longitude"));
		order.setMoment(rs.getTimestamp("moment").toInstant());
		order.setStatus(OrderStatus.values()[rs.getInt("status")]);
		
		return order;
	}
	
	
}

// while (rs.next()) = enquanto existir uma próxima linha no resultado da query (consulta)
 