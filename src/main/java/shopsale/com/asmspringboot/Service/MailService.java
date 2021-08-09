package shopsale.com.asmspringboot.Service;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import shopsale.com.asmspringboot.Model.Orders;
import shopsale.com.asmspringboot.Model.SanPhamDT;
import shopsale.com.asmspringboot.Model.Users;
import shopsale.com.asmspringboot.Reponsitory.UserReponsitory;

@Service
public class MailService {
	@Autowired
	JavaMailSender sender;

	@Autowired
	UserReponsitory userReponsitory;
	private List<MimeMessage> mailList = new ArrayList<>();

	public void guiMailXacNhanDonHang(Orders order, Map<SanPhamDT, Integer> listItems) {

		DecimalFormat df = new DecimalFormat("###,###,###");

		String content = "<!DOCTYPE html>" + "<html lang=\"en\">" + "<head>" + "    <meta charset=\"UTF-8\">"
				+ "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">"
				+ "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">"
				+ "    <title>Document</title>" + "</head>" + "<style>" + "    .thongtindonhang{"
				+ "        width: 700px;" + "        min-height: 100px;" + "        border: 1px solid #ccc;"
				+ "        border-radius: 20px;" + "    }" + "    .ngaynhan a{" + "        font-weight: bold;" + "    }"
				+ "    body a{\r\n" + "        color: blue;\r\n" + "    }\r\n" + "</style>" + "<body>"
				+ "    <div style=\"width: 700px; text-align: center; font-family: serif;\">"
				+ "        <h2>THANH TOÁN THÀNH CÔNG ĐƠN HÀNG</h2>"
				+ "            <p>Chúc mừng anh/chị: <a style=\"color: red; font-weight: bold;\">" + order.getName()
				+ "</a></p>" + "            <p>Số điện thoại: <a style=\"color: red; font-weight: bold;\">"
				+ order.getPhoneNumber() + "</a></p>"
				+ "            <p>Email: <a style=\"color: red; font-weight: bold;\">" + order.getEmail() + "</a></p>"
				+ "        <p style=\"font-style: italic; color: blue;\">Đã thanh toán thành công đơn hàng tại <a href=`http://localhost:8080/home`>ShopSale</a></p>"
				+ "    </div>" + "    <div style=\"width: 700px; text-align: right; font-family: serif;\">"
				+ "        <p style=\"font-style: italic; color: red;\">Ngày thanh toán: " + order.getDateFormat()
				+ "</p>" + "    </div>" + "    <div class=\"thongtindonhang\">"
				+ "        <h3 style=\"text-align: center;\">CHI TIẾT ĐƠN HÀNG</h3>"
				+ "        <p style=\"text-align: center; color: #ccc; font-style: italic; position: absolute; margin-left: 600px; margin-top: -40px;\">"
				+ listItems.size() + " sản phẩm</p>" + "        <table>" + "            <thead>"
				+ "                <tr>" + "                    <th style=\"width: 50px; text-align: center;\">STT</th>"
				+ "                    <th style=\"width: 400px; text-align: center;\">Tên sản phẩm</th>"
				+ "                    <th style=\"width: 250px; text-align: center;\">Đơn giá</th>"
				+ "                    <th style=\"width: 100px; text-align: center;\">Số lượng</th>"
				+ "                    <th style=\"width: 250px; text-align: center;\">Thành tiền</th>"
				+ "                </tr>" + "            </thead>" + "            <tbody>";

		int STT = 1;
		double TongThietHai = 0;
		for (SanPhamDT dt : listItems.keySet()) {

			String name = dt.getSanpham_name();
			double gia = dt.getSanpham_giaban();
			int soluong = listItems.get(dt);
			double thanhTien = gia * soluong;
			TongThietHai += thanhTien;
			String item = "<tr>";
			item += "<td style=\"width: 50px;text-align:center;\">" + STT + "</td>";
			item += "<td style=\"width: 400px; text-align: center; color: green;\">" + name + "</td>";
			item += "<td style=\"width: 250px; text-align: center; color: red;\">" + df.format(gia) + " đ</td>";
			item += "<td style=\"width: 100px; text-align: center;\">" + soluong + " chiếc</td>";
			item += "<td style=\"width: 250px; text-align: center; color: red;\">" + df.format(thanhTien) + " đ</td>";
			item += "</tr>";

			STT++;
			content += item;
		}

		content += "</tbody>"

				+ "        </table>" + "    </div>"
				+ "    <div style=\"width: 700px; text-align: right; font-size: 18px;\">"
				+ "        <p>Tổng tiền: <a style=\"font-weight: bold;\">" + df.format(TongThietHai) + " đ</a></p>"
				+ "    </div>" + "    <div style=\"width: 700px;\" class=\"ngaynhan\">"
				+ "        <p>- Bạn sẽ nhận hàng dự kiến sau: <a style=\"color: red; font-weight: bold;\">3 - 5 ngày kể từ ngày đặt hàng (kể cả thứ 7 và Chủ nhật.)</a></p>"
				+ "        <p>- Địa chỉ nhận hàng: <a style=\"color: red; font-weight: bold;\">" + order.getAddress()
				+ "</a></p>" + "    </div>" + "    <div style=\"width: 700px; text-align: center; color: blue;\">"
				+ "        <p style=\"font-style: italic; color: red;\">Cảm ơn bạn đã tin tưởng và đặt hàng trên ShopSale! Thân ái!</p>"
				+ "    </div>" + "</body>" + "</html>";
		MimeMessage message = sender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);
		try {
			helper.setFrom("ShopSale.com <dqhai_plc@outlook.com>");
			helper.setTo(order.getEmail());
			helper.setSubject("Cảm ơn bạn đã đặt hàng");
			helper.setText(content, true);
			System.out.println("Gửi mail thành công!!!");
		} catch (MessagingException e) {
			System.out.println("Không thể thêm mail vào hàng đợi");
			e.printStackTrace();
		}
		mailList.add(message);

	}

	public void guiMailDangKyThanhCong(Users user) {
		String content = "<!DOCTYPE html>\r\n" + "<html lang=\"en\">\r\n" + "<head>\r\n"
				+ "    <meta charset=\"UTF-8\">\r\n"
				+ "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\r\n"
				+ "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n"
				+ "    <title>Document</title>\r\n" + "</head>\r\n" + "<style>\r\n" + "    .thongtindonhang{\r\n"
				+ "        width: 700px;\r\n" + "        min-height: 100px;\r\n" + "        border: 1px solid #ccc;\r\n"
				+ "        border-radius: 20px;\r\n" + "    }\r\n" + "    .ngaynhan p{\r\n"
				+ "        font-weight: bold;\r\n" + "    }\r\n" + "    body a{\r\n" + "        color: blue;\r\n"
				+ "    }\r\n" + "</style>\r\n" + "<body>\r\n"
				+ "    <div style=\"width: 700px; text-align: center; font-family: serif;\">\r\n"
				+ "        <h2>ĐĂNG KÝ THÀNH CÔNG TÀI KHOẢN</h2>\r\n" + "            <p>Chúc mừng anh/chị: <a>"
				+ user.getUser_name() + "</a></p>\r\n" + "            <p>Số điện thoại: <a>" + user.getUser_sdt()
				+ "</a></p>\r\n" + "            <p>Email: <a>" + user.getUser_email() + "</a></p>\r\n"
				+ "        <p style=\"font-style: italic; color: red;\">Đã đăng ký thành công tài khoản tại ShopSale</p>\r\n"
				+ "    </div>\r\n"
				+ "    <div style=\"margin-left: 100px;width: 500px; min-height: 100px; border: 1px solid #ccc; box-shadow: 0px 0px 5px rgb(51, 50, 50); border-radius: 10px;\">\r\n"
				+ "        <h4 style=\"text-align: center;color: rgb(240, 89, 89);\">THÔNG TIN TÀI KHOẢN</h4>\r\n"
				+ "        <p style=\"margin-left: 50px;\">Tên đăng nhập: <a style=\"margin-left: 10px;\">"
				+ user.getUser_tendangnhap() + "</a></p>\r\n"
				+ "        <p style=\"margin-left: 50px;\">Mật khẩu: <a style=\"margin-left: 10px;\">"
				+ user.getUser_matkhau() + "</a></p>\r\n"
				+ "        <p style=\"font-style: italic; color: blue;; text-align: center;\">Khuyến cáo không chia sẻ thông tin tài khoản cho người khác!</p>\r\n"
				+ "    </div>\r\n" + "    <div style=\"width: 700px; text-align: center; color: blue;\">\r\n"
				+ "        <p style=\"font-style: italic; color: red;\">Cảm ơn bạn đã tin tưởng ShopSale! Thân ái!</p>\r\n"
				+ "        \r\n" + "    </div>\r\n" + "</body>\r\n" + "</html>";
		MimeMessage message = sender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);
		try {
			helper.setFrom("ShopSale.com <dqhai_plc@outlook.com>");
			helper.setTo(user.getUser_email());
			helper.setSubject("Chúc mừng bạn đã đăng ký thành công tài khoản!");
			helper.setText(content, true);
			System.out.println("Gửi mail thành công!!!");
		} catch (MessagingException e) {
			System.out.println("Không thể thêm mail vào hàng đợi");
			e.printStackTrace();
		}
		mailList.add(message);
	}

	@Scheduled(fixedDelay = 2000)
	public void run() {
		while (!mailList.isEmpty()) {
			MimeMessage message = mailList.remove(0);
			try {
				sender.send(message);
			} catch (Exception e) {
				System.out.println("Gửi mail lỗi");
				e.printStackTrace();
			}
		}
	}
}
