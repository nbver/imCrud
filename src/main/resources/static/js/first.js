
function sendRequest(method, url, body=null){
    return fetch(url).then(response => {
        return response.json()
    })
}



const userId = window.location.href.substring(window.location.href.lastIndexOf('/') + 1)
sendRequest('GET',"/restuser/"+userId)
    .then(user => {
        const username = document.getElementById("username")
        username.textContent = user.username
        document.getElementById("userTableId").textContent = user.id
        document.getElementById("userTableUsername").textContent = user.username
    })





