function register(){
    const formData = new FormData(document.querySelector(".form"));
    const firstnameHolder =formData.get("firstname");
    const lastnameHolder =formData.get("lastname");
    const ageHolder =formData.get("age");
    const usernameHolder = formData.get("username");
    const passwordHolder = formData.get("password");


    const data = {
        'firstname': firstnameHolder,
        'lastname': lastnameHolder,
        'age': ageHolder,
        'username': usernameHolder,
        'password': passwordHolder 
    }
    // console.log(data);

    const response = async (data) => {
        const token = localStorage.get("token")
        if (!token)
            window.location.href = "http://127.0.0.1:5500/src/main/resources/templates/login.html"
        try {
            const res = await fetch('http://localhost:8080/users/admin/register', {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json',
                        'Authorization': "Bearer " + token
                    },
                    mode: 'cors',
                    body: JSON.stringify(data)
                });
            if(res.status == 200){
                window.location.href = "http://127.0.0.1:5500/src/main/resources/templates/usersPage.html";
            }
        } catch (error) {
            console.error(error);
        }
    }

    response(data);

}