
function login(){
    const formData = new FormData(document.querySelector(".form"));
    const usernameHolder =formData.get("username");
    const passwordHolder =formData.get("password");


    const data = {
        'username': usernameHolder,
        'password': passwordHolder 
    }

    const response = async (data) => {
        try {
            const res = await fetch('http://localhost:8080/users/auth/login', {
                method: 'POST',
                headers:{
                    'Content-Type': 'application/json'
                },
                mode: 'cors',
                redirect:"follow",
                body: JSON.stringify(data)
            });

            if (res.redirected)
                window.location.href = res.url

            
            if (res.status == 200){
                
                const data = await res.json();
                
                localStorage.setItem("token", data.token);
                window.location.href = "/static/index";
                
            }else{
                const div = document.createElement("div");
                div.setAttribute("class","alert alert-danger");
                div.setAttribute("role","alert");
                
                const text =  document.createTextNode("invalid credentials");
                div.appendChild(text);
                
                const section = document.querySelector("section");
                document.body.insertBefore(div, section);

                setTimeout(()=>{ div.remove(); },3000);
            }
               
        } catch (error) {
            console.error(error);
        }
    } 

    response(data);

    
}