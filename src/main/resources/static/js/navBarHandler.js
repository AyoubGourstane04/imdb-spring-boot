function nav(){
    const parent = document.querySelector("div ul");

    const register = document.createElement("li");
    register.setAttribute("class", "nav-item");

    const registerLink = document.createElement("a");
    registerLink.setAttribute("class", "nav-link text-white");
    registerLink.href = "register";
    registerLink.innerText = "Register users";

    register.appendChild(registerLink);

    const users = document.createElement("li");
    users.setAttribute("class", "nav-item");

    const usersLink = document.createElement("a");
    usersLink.setAttribute("class", "nav-link text-white");
    usersLink.href = "usersPage";
    usersLink.innerText = "Users";

    users.appendChild(usersLink);

    
    const token = localStorage.getItem("token");

    if(!token) 
        window.location.href = "/static/login";

    const response = async(token) =>{ 
        try {
            const res = await fetch("http://localhost:8080/users/auth/me",
                {
                    headers:{
                        "Authorization": "Bearer " + token
                    },
                    mode:"cors"
                })
                
            if (res.status != 200)
                window.location.href = "/static/login";

            const user = await res.json();
            
            if(user.authorities[0].authority === "ROLE_ADMIN"){

            }

        } catch (error) {
            console.error(error);
        }
    }
    response(token);
}

nav();

 