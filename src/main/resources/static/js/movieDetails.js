const slice = window.location.href.split('/');
const filmId = slice[5];

const token = localStorage.getItem("token");
    if (!token)
        window.location.href = "/static/login"


function details(){

    const title = document.querySelector("title");

    const image = document.getElementById("image");
    
    const h2 = document.getElementById("h2");
    
    const description = document.getElementById("p");

    const director = document.getElementById("director");

    const likesCount = document.getElementById("likesCount");

    const cast = document.getElementById("cast");

    const commentsDiv = document.getElementById("commentsDiv");

    const commentsH3 = document.getElementById("commentsH3");

    
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

    const response = async(token,filmId) => {
        try {
            const res = await fetch(`http://localhost:8080/users/films/${filmId}`, {
                headers: {
                    "Authorization": "Bearer " + token
                },
                mode:'cors',
            });
            
            if(res.status == 200){
                const film = await res.json();

                title.textContent = film.title;

                const img = await file(film.image.imageName,token);

                image.setAttribute("src", URL.createObjectURL(img));
                image.setAttribute("alt", film.image.imageName);

                h2.textContent = film.title;

                description.textContent = film.description != null ? film.description : "no description";

                director.textContent = "Director :  " + film.director;

                let castStr = "Main cast :  ";

                for(let i=0; i<film.mainCast.length; i++){
                    if(i < film.mainCast.length - 1){
                        castStr += (film.mainCast[i] + ', ');
                    }else{
                        castStr += (film.mainCast[i] + '.');
                    }
                }

                cast.textContent = castStr;

                likesCount.textContent = film.likesCount;

                commentsH3.textContent = 'Comments (' + film.commentsCount + ') ';


                for(let i=0; i<film.comments.length; i++){
                    const newComment = document.createElement("div");
                    newComment.setAttribute("class", "p-3 bg-dark rounded mb-2");
                    newComment.textContent = film.comments[i].content;

                    commentsDiv.appendChild(newComment);
                }                
            }else{
                throw new Error("Fetch Error");
            }
        } catch (error) {
            console.error(error);
        }
    }
    response(token, filmId);
}

details();



function like(){
    const response = async(token,filmId) => {
        try {
            const res = await fetch(`http://localhost:8080/users/films/like/${filmId}`, {
                    method: 'PUT',
                    headers: {
                        "Authorization": "Bearer " + token
                    },
                    mode:'cors',
                });
            if(res.status == 200){
                const data =  await res.json();
                const likesCount = document.getElementById("likesCount");
                likesCount.textContent = data.likesCount;
            }else{
                throw new Error("Fetch Error");
            }
        } catch (error) {
            console.error(error);
        }
    };
    
    response(token, filmId);
}