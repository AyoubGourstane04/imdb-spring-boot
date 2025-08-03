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

    const response = async (data) => {
        const token = localStorage.getItem("token");
        if (!token)
            window.location.href = "/static/login"
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
                window.location.href = "/static/usersPage";
            }
        } catch (error) {
            console.error(error);
        }
    }

    response(data);

}