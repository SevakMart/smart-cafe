function validate() {

    if (document.register.uname.value == "") {
        alert("Please provide your first name!");
        document.register.uname.focus();
        return false;
    }
    if (document.register.usurname.value == "") {
        alert("Please provide your last name!");
        document.register.usurname.focus();
        return false;
    }
    return (true);
}

function ValidateEmail(mail) {
    if (/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(register.uemail.value)) {
        return (true)
    }
    alert("You have entered an invalid email address!")
    return (false)
}