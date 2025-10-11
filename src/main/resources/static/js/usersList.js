function listUsers(){
    const table = document.querySelector("table tbody");

    const token = localStorage.getItem("token");
        if (!token)
            window.location.href = "/static/login"

    const response = async (token) => {
        
        try {
            const res = await fetch('http://localhost:8080/users/admin/allUsers',{
                headers:{
                    "Authorization": "Bearer " + token
                },
                mode:"cors"
            });

            if(res.status == 200){
                const data = await res.json();
                console.log(data);
                for(let i=0;i<data.length;i++){
                    const row = document.createElement("tr");
                    
                    const td1 = document.createElement("td");
                    td1.innerText = data[i].id;

                    const td2 = document.createElement("td");
                    td2.textContent = data[i].firstname;

                    const td3 = document.createElement("td");
                    td3.textContent = data[i].lastname;

                    const td4 = document.createElement("td");
                    td4.textContent = data[i].age;

                    const td5 = document.createElement("td");
                    td5.textContent = data[i].username;

                    const td6 = document.createElement("td");
                    td6.textContent = data[i].authorities[0].authority;                                    

                    row.append(td1,td2,td3,td4,td5,td6);

                    table.appendChild(row);
                }
            }else{
                throw new Error("something wrong in fetch!")
            }
        } catch (error) {
            console.error(error);
        }
    }
    response(token);
}

listUsers();