const authenticateUser = () => {
    const username = document.getElementById("username").value;
    const password = document.getElementById("password").value;
    const xhttp = new XMLHttpRequest();
    xhttp.onload = function() {
        alert(this.responseText);
    };
    xhttp.open("GET", `/auth?user=${username}&pass=${password}`);
    xhttp.send();
};
