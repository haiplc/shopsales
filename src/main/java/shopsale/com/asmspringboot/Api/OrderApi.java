package shopsale.com.asmspringboot.Api;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import shopsale.com.asmspringboot.Model.OrderItems;
import shopsale.com.asmspringboot.Model.Orders;
import shopsale.com.asmspringboot.Reponsitory.OrderReponsitory;

@RestController
@RequestMapping("api")
public class OrderApi {

	@Autowired
	OrderReponsitory orderReponsitory;

	@GetMapping("order-detail") // "/api/order-detail?id=89"
	public Map<String, Object> getOderDetail(@RequestParam(value = "id", required = true) int id) {
		DecimalFormat df = new DecimalFormat("###,###,###");
		Map<String, Object> data = new HashMap<String, Object>();

		Optional<Orders> order = orderReponsitory.findById(id);

		List<Object> listItems = new ArrayList<Object>();

		for (OrderItems item : order.get().getOrder_items()) {

			Map<String, Object> itemsJs = new HashMap<String, Object>();

			itemsJs.put("id_product", item.getSanpham().getSanpham_id());
			itemsJs.put("image", item.getSanpham().getSanpham_anh1());
			itemsJs.put("name", item.getName());
			itemsJs.put("price", df.format(item.getPrice()));
			itemsJs.put("amount", item.getAmount());
			itemsJs.put("total", df.format(item.getTotal()));

			listItems.add(itemsJs);
		}

		data.put("items", listItems);
		data.put("total", df.format(order.get().getTotal()));

		return data;
	}

}
