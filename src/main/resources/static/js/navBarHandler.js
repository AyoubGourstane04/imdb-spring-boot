function nav(){
    const parent = document.querySelector("div ul");

    const register = document.getElementById("register");

    const users = document.getElementById("users");

    
    const nameSpot = document.getElementById("usersname");

    
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

            nameSpot.textContent = 'Hello ' + user.username;
            
            if(user.authorities[0].authority !== "ROLE_ADMIN"){
                parent.removeChild(register);
                parent.removeChild(users);
            }

        } catch (error) {
            console.error(error);
        }
    }
    response(token);
}

nav();

 