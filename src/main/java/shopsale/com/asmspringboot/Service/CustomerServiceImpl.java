package shopsale.com.asmspringboot.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import shopsale.com.asmspringboot.Controller.GobalController.LAST_PAGE;
import shopsale.com.asmspringboot.Model.Users;
import shopsale.com.asmspringboot.Reponsitory.UserReponsitory;

@Service
public class CustomerServiceImpl implements CustomerService {
	@Autowired
	UserReponsitory userReponsitory;

	@Autowired
	CustomerSession customerSession;

	@Override
	public Users getUsers() {
		return customerSession.getUsers();
	}

	@Override
	public boolean dangKy(Users user) {
		Users userSave = userReponsitory.save(user);

		if (userSave != null) {
			customerSession.setUsers(userSave);
			return true;
		}
		return false;
	}

	@Override
	public void dangXuat() {
		customerSession.setUsers(null);
	}

	@Override
	public boolean dangNhap(String tendangnhap, String matkhau) {
		Users user = userReponsitory.login(tendangnhap, matkhau);
		if (user != null) {
			customerSession.setUsers(user);
			return true;
		}
		return false;
	}

	@Override
	public boolean isUserLogin() {
		return customerSession.getUsers() != null;
	}

	@Override
	public LAST_PAGE getLastPage() {

		return customerSession.getLastPage();
	}

	@Override
	public void setLastPage(LAST_PAGE lastPage) {
		customerSession.setLastPage(lastPage);

	}
}
