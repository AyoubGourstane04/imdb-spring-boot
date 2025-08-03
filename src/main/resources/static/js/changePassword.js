function submitPassword()
{
    const pass1Holder = document.getElementById("password").value
    const pass2Holder = document.getElementById("confirm").value

    const slice = window.location.href.split('/');
    const changePaswordId = slice[5];
    
    console.log(changePaswordId);

    const data = {
        'password': pass1Holder,
        'confirmPassword': pass2Holder
    }

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
                window.location.href = "/static/login"; 
           
        } catch (err) {
            console.error(err);
        }  
    }

    response(changePaswordId, data);
}