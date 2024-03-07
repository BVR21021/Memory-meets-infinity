package com.momory.util;

import java.time.LocalDate;
import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class CustomizedUserId {

	public static String customized_user_id(String user_id) {
		String pad_string = "00000";
		String last = "";
		int last_id = 0;
		String last_user = "";
		String last_user_id = "";

		String yearString = Integer.toString(LocalDate.now().getYear());

		String lastTwoDigits = yearString.substring(yearString.length() - 2);

		if (user_id != null) {
			if (user_id.substring(3).length() <= pad_string.length()) {
				user_id = "USR" + lastTwoDigits + user_id.substring(3);
			} else {
				String last_user_year = user_id.substring(3, 5);

				if (!lastTwoDigits.equalsIgnoreCase(last_user_year)) {
					user_id = "USR" + lastTwoDigits + pad_string;
				}
			}

		}

		if (user_id != null && user_id != "") {
			last_user_id = user_id.substring(5);
		}

		if (user_id == null || user_id.equalsIgnoreCase("")) {
			last = String.valueOf(1);
			last_user = "USR" + lastTwoDigits + pad_string.substring(last.length()) + last;// fetch_user_charts
		} else if ((Integer.parseInt(last_user_id.substring(last_user_id.length() - 1))) <= 9
				&& (last_user_id.substring(0, last_user_id.length() - 1).equalsIgnoreCase("0000"))) {
			last_id = Integer.parseInt(last_user_id.substring(last_user_id.length() - 1));
			last = String.valueOf(last_id + 1);
			last_user = "USR" + lastTwoDigits + pad_string.substring(last.length()) + last;
			return last_user;

		} else if ((Integer.parseInt(last_user_id.substring(last_user_id.length() - 2))) <= 99
				&& (last_user_id.substring(0, last_user_id.length() - 2)).equalsIgnoreCase("000")) {
			last_id = Integer.parseInt(last_user_id.substring(last_user_id.length() - 2));
			last = String.valueOf(last_id + 1);
			last_user = "USR" + lastTwoDigits + pad_string.substring(last.length()) + last;
			return last_user;
		} else if ((Integer.parseInt(last_user_id.substring(last_user_id.length() - 3))) <= 999
				&& (last_user_id.substring(0, last_user_id.length() - 3)).equalsIgnoreCase("00")) {
			last_id = Integer.parseInt(last_user_id.substring(last_user_id.length() - 3));
			last = String.valueOf(last_id + 1);
			last_user = "USR" + lastTwoDigits + pad_string.substring(last.length()) + last;
			return last_user;
		} else if ((Integer.parseInt(last_user_id.substring(last_user_id.length() - 4))) <= 9999
				&& (last_user_id.substring(0, last_user_id.length() - 4)).equalsIgnoreCase("0")) {
			last_id = Integer.parseInt(last_user_id.substring(last_user_id.length() - 4));
			last = String.valueOf(last_id + 1);
			last_user = "USR" + lastTwoDigits + pad_string.substring(last.length()) + last;
			return last_user;
		} else if ((Integer.parseInt(last_user_id.substring(last_user_id.length() - 5))) <= 99999
				&& (last_user_id.substring(0, last_user_id.length() - 5)).equalsIgnoreCase("")) {
			last_id = Integer.parseInt(last_user_id.substring(last_user_id.length() - 5));
			last = String.valueOf(last_id + 1);
			last_user = "USR" + lastTwoDigits + pad_string.substring(last.length()) + last;
			return last_user;
		} else {
			last_user = user_id;
		}
		return last_user;

	}

	public static String customizedRollId(String student_id) {
		String pad_string = "0000000";
		String last = "";
		int last_id = 0;
		String last_student = "";
		String last_student_id = "";

		String yearString = Integer.toString(LocalDate.now().getYear());

		String lastTwoDigits = yearString.substring(yearString.length() - 2);

		if (student_id != null) {
			if (student_id.substring(3).length() <= pad_string.length()) {
				student_id = "ROL" + lastTwoDigits + student_id.substring(3);
			} else {
				String last_student_year = student_id.substring(3, 5);

				if (!lastTwoDigits.equalsIgnoreCase(last_student_year)) {
					student_id = "ROL" + lastTwoDigits + pad_string;
				}

			}
		}

		if (student_id != null && student_id != "") {
			last_student_id = student_id.substring(5);
		}

		if (student_id == null || student_id.equalsIgnoreCase("")) {
			last = String.valueOf(1);
			last_student = "ROL" + lastTwoDigits + pad_string.substring(last.length()) + last;
		} else if ((Integer.parseInt(last_student_id.substring(last_student_id.length() - 1))) <= 9
				&& (last_student_id.substring(0, last_student_id.length() - 1).equalsIgnoreCase("000000"))) {
			last_id = Integer.parseInt(last_student_id.substring(last_student_id.length() - 1));
			last = String.valueOf(last_id + 1);
			last_student = "ROL" + lastTwoDigits + pad_string.substring(last.length()) + last;
			return last_student;

		} else if ((Integer.parseInt(last_student_id.substring(last_student_id.length() - 2))) <= 99
				&& (last_student_id.substring(0, last_student_id.length() - 2)).equalsIgnoreCase("00000")) {
			last_id = Integer.parseInt(last_student_id.substring(last_student_id.length() - 2));
			last = String.valueOf(last_id + 1);
			last_student = "ROL" + lastTwoDigits + pad_string.substring(last.length()) + last;
			return last_student;
		} else if ((Integer.parseInt(last_student_id.substring(last_student_id.length() - 3))) <= 999
				&& (last_student_id.substring(0, last_student_id.length() - 3)).equalsIgnoreCase("0000")) {
			last_id = Integer.parseInt(last_student_id.substring(last_student_id.length() - 3));
			last = String.valueOf(last_id + 1);
			last_student = "ROL" + lastTwoDigits + pad_string.substring(last.length()) + last;
			return last_student;
		} else if ((Integer.parseInt(last_student_id.substring(last_student_id.length() - 4))) <= 9999
				&& (last_student_id.substring(0, last_student_id.length() - 4)).equalsIgnoreCase("000")) {
			last_id = Integer.parseInt(last_student_id.substring(last_student_id.length() - 4));
			last = String.valueOf(last_id + 1);
			last_student = "ROL" + lastTwoDigits + pad_string.substring(last.length()) + last;
			return last_student;
		} else if ((Integer.parseInt(last_student_id.substring(last_student_id.length() - 5))) <= 99999
				&& (last_student_id.substring(0, last_student_id.length() - 5)).equalsIgnoreCase("00")) {
			last_id = Integer.parseInt(last_student_id.substring(last_student_id.length() - 5));
			last = String.valueOf(last_id + 1);
			last_student = "ROL" + lastTwoDigits + pad_string.substring(last.length()) + last;
			return last_student;
		} else if ((Integer.parseInt(last_student_id.substring(last_student_id.length() - 6))) <= 999999
				&& (last_student_id.substring(0, last_student_id.length() - 6)).equalsIgnoreCase("0")) {
			last_id = Integer.parseInt(last_student_id.substring(last_student_id.length() - 6));
			last = String.valueOf(last_id + 1);
			last_student = "ROL" + lastTwoDigits + pad_string.substring(last.length()) + last;
			return last_student;
		} else if ((Integer.parseInt(last_student_id.substring(last_student_id.length() - 6))) <= 9999999
				&& (last_student_id.substring(0, last_student_id.length() - 6)).equalsIgnoreCase("")) {
			last_id = Integer.parseInt(last_student_id.substring(last_student_id.length() - 6));
			last = String.valueOf(last_id + 1);
			last_student = "ROL" + lastTwoDigits + pad_string.substring(last.length()) + last;
			return last_student;
		} else {
			last_student = student_id;
		}
		return last_student;

	}
}
