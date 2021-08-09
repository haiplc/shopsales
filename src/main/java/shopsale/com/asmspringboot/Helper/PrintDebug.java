package shopsale.com.asmspringboot.Helper;

import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

public class PrintDebug {

	public static void printReult(BindingResult result) {

		for (ObjectError error : result.getAllErrors()) {
			String out = String.format("%s : %s", error.getObjectName(), error.getDefaultMessage());
			System.out.println(out);
		}
	}

}
