package shopsale.com.asmspringboot.Service;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import shopsale.com.asmspringboot.Controller.GobalController.LAST_PAGE;
import shopsale.com.asmspringboot.Model.Users;

@Component
@SessionScope
public class CustomerSession {
	Users user;
	LAST_PAGE lastPage = LAST_PAGE.HOME;

	public Users getUsers() {
		return user;
	}

	public void setUsers(Users user) {
		this.user = user;
	}

	public LAST_PAGE getLastPage() {
		return lastPage;
	}

	public void setLastPage(LAST_PAGE lastPage) {
		this.lastPage = lastPage;
	}

}
