
function login(){
    const formData = new FormData(document.querySelector(".form"));
    const usernameHolder =formData.get("username");
    const passwordHolder =formData.get("password");


    const data = {
        'username': usernameHolder,
        'password': passwordHolder 
    }

    // console.log(data);

    const response = async (data) => {
        try {
            const res = await fetch('http://localhost:8080/users/auth/login', {
                method: 'POST',
                headers:{
                    'Content-Type': 'application/json'
                },
                mode: 'cors',
                body: JSON.stringify(data)

            });
            // console.log("FUUUUUUUCK!!");            
            if(res.status == 302){
                window.location.href = res.url;
            }else if (res.status == 200){
                // const data = await res.json();
                //  localStorage.put("token", data.token)
                console.log("Success!!");
            }else{
                console.error("Errrrrrrooor!!");
            }
               
        } catch (error) {
            console.error(error);
        }
    } 

    response(data);

    
}