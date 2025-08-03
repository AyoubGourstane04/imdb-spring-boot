function getToken(){
    const token = localStorage.getItem("token");
    if(!token)
        window.location.href = "/static/login";
    return token;
}

async function getUser()
{
    const token = getToken();
   try {
        const response = await fetch("http://localhost:8080/users/auth/me",
            {
                headers:{
                    "Authorization": "Bearer " + getToken()
                },
                mode:"cors"
            })
            
        if (response.status != 200)
            window.location.href = "/static/login";


        try {
            const data = await response.json();
                return data;

        } catch (err) {
            console.error(err);
        }

    } catch (error) {
        console.error(error);
   }

}


const user = getUser();



