function cards(){
    const parent = document.getElementById("parent");

    const token = localStorage.getItem("token");
        if (!token)
            window.location.href = "/static/login"


    const file = async (fileName,token) => {
        try {
            const repon = await fetch(`http://localhost:8080/users/files/get/${fileName}`,{
                headers:{
                    "Authorization": "Bearer " + token
                },
                mode:"cors"
            });
            if(repon.status == 200){
                const blob = await repon.blob();
                return blob;
            }else{
                throw new Error("FetchError");
            }

            
        } catch (error) {
            console.error(error);
        }
    }




    const response = async (token) => {
        
        try {
            const res = await fetch('http://localhost:8080/users/films/all',{
                headers:{
                    "Authorization": "Bearer " + token
                },
                mode:"cors"
            });

            if(res.status == 200){
                const data = await res.json();
                for(let i=0;i<data.length;i++){
                   const div1 = document.createElement("div");
                   div1.setAttribute("class", "col-md-3");

                   const div2 = document.createElement("div");
                   div2.setAttribute("class", "card bg-dark text-light");

                   const image = await file(data[i].image.imageName,token);

                   const img = document.createElement("img");
                   img.setAttribute("src",URL.createObjectURL(image));
                   img.setAttribute("alt", data[i].image.imageName);
                   img.setAttribute("class", "card-img-top");

                   const div3 = document.createElement("div");
                   div3.setAttribute("class", "card-body");

                   const h5 = document.createElement("h5");
                   h5.setAttribute("class", "card-title");
                   h5.textContent = data[i].title;

                   const p = document.createElement("p");
                   p.setAttribute("class", "card-text");
                   p.textContent = data[i].description;

                   const a = document.createElement("a");
                   a.setAttribute("class", "btn btn-primary");
                   a.setAttribute("href","movieDetails/"+ data[i].id);
                   a.textContent = "More details";

                   div3.append(h5,p,a);

                   div2.append(img,div3);

                   div1.appendChild(div2);

                   parent.appendChild(div1);
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

cards();