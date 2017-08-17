function validateForm() {
	var result = true; 
	
	var FILL_FIELD = "*заполните поле", 
	PWD_NOT_EQUAL = "*не совпадают значения паролей",
	BAD_E_MAIL = "*неверный формат e-mail(должен содержать '@' и '.' )",
	BAD_PHONE = "*неверно указан телефон",
	BAD_AGE_MIN = "*возраст старше 7-ми лет",
	BAD_AGE_MAX = "*возраст младше 120-ти лет",
	BAD_LOGIN = "*некорректный логин, следует использовать только латинские буквы, цифры,'_', не менее 5 символов"
	BAD_FIRST_SYMBOL = "*некорректный первый символ(A-z)",
	BAD_PASSWORD_LENGTH = "*пароль должен содержать не менее 6 символов",
	BAD_PASSWORD_SYMBOLS = "*пароль должен содержать не менее одной буквы в каждом регистре и не менее одной цифры",
	NUMBERS_ONLY = "*допустимы только цифры",
	BAD_LOGIN_LENGTH = "*не менее 5-ти символов",
	LATHIN_SYMBOLS_ONLY = "*только латинские буквы, цифры,'_'";
			
	var errFirstName = document.getElementById("err-first-name"),
	errLastName = document.getElementById("err-last-name"),
	errAge = document.getElementById("err-age"),
	errEmail1 = document.getElementById("err-e-mail1"),
	errEmail2 = document.getElementById("err-e-mail2"),
	errPhone = document.getElementById("err-phone"),
	errLogin = document.getElementById("err-login"),
	errPwd1 = document.getElementById("err-pwd1"), 
	errPwd2 = document.getElementById("err-pwd2");
	
	errFirstName.innerHTML = "";
	errLastName.innerHTML = "";
	errAge.innerHTML = "";
	errEmail1.innerHTML = "";
	errPhone.innerHTML = "";
	errLogin.innerHTML = "";
	errPwd1.innerHTML = "";
	errPwd2.innerHTML = "";
	
	if(errEmail2){
		errEmail2.innerHTML = "";
	}
		
	var firstName = document.forms[0]["firstName"].value, 
	lastName = document.forms[0]["lastName"].value,
	age = document.forms[0]["age"].value,
	eMail1 = document.forms[0]["eMail1"].value,
	phone = document.forms[0]["phone"].value,
	login = document.forms[0]["login"].value,
	pwd1 = document.forms[0]["pwd1"].value, 
	pwd2 = document.forms[0]["pwd2"].value; 
	
	var eMail2;
	try{ eMail2 = document.forms[0]["eMail2"].value;}catch(e){eMail2 = null;}
	
	// First name validation------------------------------------------//
	if (!firstName) {
		errFirstName.innerHTML = FILL_FIELD;
		result = false;
	} 
	// Last name validation------------------------------------------//
	if (!lastName) {
		errLastName.innerHTML = FILL_FIELD;
		result = false;
	} 
	// Age validation------------------------------------------//
	 
	if (age && isNaN(age)) {
		errAge.innerHTML = NUMBERS_ONLY;
		result = false;
	}
	
	if (age <7) {
		errAge.innerHTML = BAD_AGE_MIN;
		result = false;
	} 
	
	if (age>120) {
		errAge.innerHTML = BAD_AGE_MAX;
		result = false;
	}
	if (!age) {
		errAge.innerHTML = FILL_FIELD;
		result = false;
	}
	// E-mail1 validation------------------------------------------//
	if (!eMail1) {
		errEmail1.innerHTML = FILL_FIELD;
		result = false;
	} 
	if (eMail1 && eMail1.search(/.+@.+\..+/i) !== 0) {
		errEmail1.innerHTML = BAD_E_MAIL;
		result = false;
	}
	// E-mail2 validation------------------------------------------//
	if (!eMail2 && eMail2 !== null) {
		errEmail2.innerHTML = FILL_FIELD;
		result = false;
	} 
	if (eMail2 && (eMail2.search(/.+@.+\..+/i) !== 0) && eMail2 !== null) {
		errEmail2.innerHTML = BAD_E_MAIL;
		result = false;
	}
	// Phone validation------------------------------------------//
	if (!phone) {
		errPhone.innerHTML = FILL_FIELD;
		result = false;
	} 
	if (phone && phone.search("\\([0-9]{3}\\)\\s[0-9]{3}-[0-9]{2}-[0-9]{2}") !== 0) {
		errPhone.innerHTML = BAD_PHONE;
		result = false;
	}
	// Login validation------------------------------------------//
	 
	if (!login) {
		errLogin.innerHTML = FILL_FIELD;
		result = false;
	}
	if (login && (login.length <5)) {
		errLogin.innerHTML = BAD_LOGIN_LENGTH;
		result = false;
	}
	if (login && login.search(/[a-z]/i) !== 0) {
		errLogin.innerHTML = BAD_FIRST_SYMBOL;
		result = false;
	}
	if (login && login.search(/^[A-Za-z0-9_]+$/) !== 0) {
		errLogin.innerHTML = LATHIN_SYMBOLS_ONLY;
		result = false;
	}
			
	// Password validation------------------------------------------//
	if (!pwd1) {
		errPwd1.innerHTML = FILL_FIELD;
		result = false;
	} 
	if (!pwd2) {
		errPwd2.innerHTML = FILL_FIELD;
		result = false;
	} 
	if (pwd1 && (pwd1.search("^(?=.*[a-zа-я])(?=.*[A-ZА-Я])(?=.*[0-9])(?=.*[^\w\s]).{0,}") !== 0)) {
		errPwd1.innerHTML = BAD_PASSWORD_SYMBOLS;
		result = false;
	}

	if (pwd1 && (pwd1.length <6)) {
		errPwd1.innerHTML = BAD_PASSWORD_LENGTH;
		result = false;
	}
		
	if (pwd1 && pwd2 && pwd1 !== pwd2) {
		errPwd1.innerHTML = PWD_NOT_EQUAL;
		errPwd2.innerHTML = PWD_NOT_EQUAL;
		document.forms[0]["pwd1"].value = ""; 
		document.forms[0]["pwd2"].value = ""; 
		result = false;
	} 
	return result;
}
    var countOfFields = 1;
    var maxFieldLimit = 2; // Максимальное число возможных полей
function deleteField(a) {
	var contDiv = a.parentNode;
	contDiv.parentNode.removeChild(contDiv);
	countOfFields--;
	return false;
}
function addField() {
	if (countOfFields >= maxFieldLimit) {
		alert("Только однин альтернативный e-mail");
		return false;
	}
	countOfFields++;
	var div = document.createElement("div");
	div.innerHTML = "<span class=\"in\">E-mail:</span><input name=\"eMail2\" type=\"text\" /> <span class=\"err\" id=\"err-e-mail2\"></span><a onclick=\"return deleteField(this)\" href=\"#\"><button>Удалить</button></a>";
	document.getElementById("parentId").appendChild(div);
	return false;
}