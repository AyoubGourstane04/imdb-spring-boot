const token = localStorage.getItem("token");
    if (!token)
        window.location.href = "/static/login"



function search(){

    const formData = new FormData(document.querySelector(".form"));
    
    const searchData = formData.get("search");

    const response = async(token,searchData) => {
       try {
         const res = await fetch(`http://localhost:8080/users/films/title/${searchData}`,{
                 headers:{
                     "Authorization": "Bearer " + token
                 },
                 mode:"cors"
         });
 
         if(res.ok){
            const data = await res.json();
 
            window.location.href = `movieDetails/${data[0].id}`;
 
         }else{
            throw new Error("Invalid search input!");
         }
       } catch (error) {
            console.error(error);
       }
    }

    response(token,searchData);


}