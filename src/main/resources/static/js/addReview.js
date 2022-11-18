const idUser = document.cookie
    .split('; ')
    .find((row) => row.startsWith('idUser='))
    ?.split('=')[1];
const token = document.cookie
    .split('; ')
    .find((row) => row.startsWith('auth='))
    ?.split('=')[1];
    let data;
function addReview() {
    event.preventDefault();
    let idCategory= document.getElementById("category").value*1;
    let title= document.getElementById("title").value;
    let body= document.getElementById("body").value;
    let valoration= document.getElementById("valoration").value*1;
    data =
        {
            idUser: idUser*1,
            idCategory: idCategory,
            title: title,
            body: body,
            valoration: valoration,
            likes: 0,
            dislikes: 0,
        };
        let options = {
            method: 'POST',
            mode: "no-cors",
            headers: {
                "Authorization": "authToken: " + token,
            },
            body: JSON.stringify(data),
        };
        return fetch("http://localhost:8082/api/reviews", options)
            .then((res) => res)
            .then(data =>
                console.log('Success:', data)
            );

}