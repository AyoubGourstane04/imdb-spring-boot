const token = localStorage.getItem("token");
    if (!token)
        window.location.href = "/static/login"


function addFilm(){

    const formData = new FormData(document.querySelector(".form"));

    const title = formData.get("title");

    const director = formData.get("director");

    const description = formData.get("description");

    const image = formData.get("image");

    const cast = [];

    for(const [key,value] of formData.entries()){
        if(/^cast\d+$/.test(key)){
            cast.push(value);
        }
    }

    const film = {
        "title": title,
        "director": director,
        "likesCount": 0,
        "mainCast": cast,
        "commentsCount": 0,
        "comments": [],
        "description": description,
        "image": {
            "imageName": null,
            "imagePath": null
        }
    }

    const data = new FormData();

    data.append("film", JSON.stringify(film));
    data.append("image", image);


    const response = async(token,data) => {
        try {
            const res = await fetch('http://localhost:8080/users/films/add',{
                method: 'POST',
                headers: {
                    "Authorization": "Bearer " + token
                },
                mode: 'cors',
                body: data
            });

            if(res.status === 200){
                window.location.href = "/static/index";
            }else{
                throw new Error("Fetch Error");
            }
        } catch (error) {
            console.error(error);
        }
    }

    response(token, data);
}