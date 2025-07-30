function submitPassword()
{
    const pass1Holder = document.getElementById("password").value
    const pass2Holder = document.getElementById("confirm").value

    const changePaswordId = window.location.search.split("?")[1].split("=")[1]

    const data = {
        'password': pass1Holder,
        'confirmPassword': pass2Holder
    }

    // console.log(data);
    // console.log(JSON.stringify(data));

    const response = async (id, data) => {
        try {
            const res = await fetch(`http://localhost:8080/users/auth/updatePassword/${id}`, {
                method:'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                mode:'cors',
                body: JSON.stringify(data)
            });

            if (res.status == 200)
                window.location.href = "/login"; 
           
        } catch (err) {
            console.error(err);
        }  
    }

    response(changePaswordId, data);
}