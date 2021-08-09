package shopsale.com.asmspringboot.Service;

import shopsale.com.asmspringboot.Controller.GobalController.LAST_PAGE;
import shopsale.com.asmspringboot.Model.Users;

public interface CustomerService {
	Users getUsers();

	boolean dangKy(Users user);

	void dangXuat();

	boolean dangNhap(String tenDangNhap, String matKhau);

	boolean isUserLogin();

	void setLastPage(LAST_PAGE lastPage);

	LAST_PAGE getLastPage();
}
