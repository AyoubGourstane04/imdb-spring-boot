function logout(){
    const token = localStorage.getItem("token");
    if(token)
        localStorage.removeItem("token");

    window.location.href = "/static/login";
}
