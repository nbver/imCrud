function activaTab(tab){
    $('.nav-tabs a[href="#' + tab + '"]').tab('show')
}



function sendPostRequest(method = "POST", url, body = null) {
    const headers = {
        'Content-Type': 'application/json'
    }

    return fetch(url, {
        method: method,
        body: JSON.stringify(body),
        headers: headers
    }).then(response => {
        if (response.status < 400) {
            return response.json()
        }

        return response.json().then(error => {
            const e = new Error('Что-то пошло не так')
            e.data = error
            throw e
        })
    })
}

function sendGetRequest(url) {

    return fetch(url).then(response => {
        return response.json()
    })

}

async function createUsersTable() {
    let usersArray = await sendGetRequest("admin/v1/users")
//    console.log(usersArray)
    let tableBody = document.querySelector("#usersTable > tbody")
    while (tableBody.firstChild) {
        tableBody.removeChild(tableBody.firstChild)
    }

    usersArray.forEach((user) => {
        let tr = document.createElement("tr")

        let tdId = document.createElement("td")
        tdId.textContent = user.id
        tr.appendChild(tdId)

        let tdUsername = document.createElement("td")
        tdUsername.textContent = user.username
        tr.appendChild(tdUsername)

        let tdRole = document.createElement("td")
        let adminRole = ""
        if (user.admin) adminRole = "ADMIN"
        let userRole = ""
        if (user.user) userRole = "USER"
        let userRoles = adminRole + " " + userRole
        tdRole.textContent = userRoles
        tr.appendChild(tdRole)
// Edit block
        let tdEdit = document.createElement("td")
        // let btn = document.createElement('input');
        // btn.type = "button";
        // btn.className = "btn btn-info";
        // btn.value = "Edit";
        // btn.id = "btnEdit"+user.id
        // btn.onclick = async function(){
        //     await updateUser(user.id)
        // }

        tdEdit.insertAdjacentHTML('afterbegin', `<div class="btn btn-info"
                                                     data-toggle="modal" data-target="#exampleModal`+user.id+`"
                                                     >
                                                    Edit
                                                </div>

                                                
                                                    <div class="modal fade" id="exampleModal`+user.id+`"
                                                         
                                                         tabindex="-1"
                                                         role="dialog"
                                                         aria-labelledby="exampleModalLabel" aria-hidden="true">
                                                        <div class="modal-dialog" role="document">
                                                            <div class="modal-content">
                                                                <div class="modal-header">
                                                                    <h5 class="modal-title" id="exampleModalLabel`+user.id+`">
                                                                        Edit</h5>
                                                                    <button type="button" class="close"
                                                                            data-dismiss="modal"
                                                                            aria-label="Close">
                                                                        <span aria-hidden="true">&times;</span>
                                                                    </button>
                                                                </div>
                                                                <div class="modal-body">

                                                                    <div class="form-group">
                                                                        <p class="text-center"><b>ID</b></p>
                                                                        <input type="text" class="form-control"
                                                                               id="id" name="id" 
                                                                               value="`+user.id+`"
                                                                               readonly>
                                                                    </div>
                                                                    <div class="form-group">
                                                                        <p class="text-center"><b>Username</b></p>
                                                                        <input type="text" class="form-control"
                                                                               name="username" id="username"
                                                                               value="` + user.username + `">
                                                                    </div>
                                                                    <div class="form-group">
                                                                        <p class="text-center"><b>Password</b></p>
                                                                        <input type="text" class="form-control"
                                                                               name="password" id="password"
                                                                        >
                                                                    </div>

                                                                    <p class="text-center"><b>Roles</b></p>
                                                                    <div align="center">
                                                                        <input type="checkbox" name="list" id = "userEditCheckbox"
                                                                               value="ROLE_ADMIN" `+(user.admin ? "checked " : "")+`
                                                                               >
                                                                        <label>ADMIN</label>
                                                                        <input type="checkbox" name="list" id = "adminEditCheckbox"
                                                                               value="ROLE_USER"  `+ (user.user ? "checked " : "") +`
                                                                               readonly/>
                                                                        <label>USER</label>
                                                                    </div>


                                                                </div>
                                                                <div class="modal-footer">
                                                                    <button type="button" class="btn btn-secondary"
                                                                            data-dismiss="modal">Close
                                                                    </button>
                                                                    <button type="submit" class="btn btn-primary" id = "btnEditSubmit">
                                                                        Edit
                                                                    </button>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                
`)

        //tdEdit.appendChild(btn);
        tr.appendChild(tdEdit)

//Delete block
        let tdDelete = document.createElement("td")
        tdDelete.insertAdjacentHTML('afterbegin', `<div class="btn btn-danger"
                                                     data-toggle="modal" data-target="#exampleModalDelete`+user.id+`"
                                                     >
                                                    Delete
                                                </div>

                                                
                                                    <div class="modal fade" id="exampleModalDelete`+user.id+`"
                                                         
                                                         tabindex="-1"
                                                         role="dialog"
                                                         aria-labelledby="exampleModalLabel" aria-hidden="true">
                                                        <div class="modal-dialog" role="document">
                                                            <div class="modal-content">
                                                                <div class="modal-header">
                                                                    <h5 class="modal-title" id="exampleModalDeleteLabel`+user.id+`">
                                                                        Delete</h5>
                                                                    <button type="button" class="close"
                                                                            data-dismiss="modal"
                                                                            aria-label="Close">
                                                                        <span aria-hidden="true">&times;</span>
                                                                    </button>
                                                                </div>
                                                                <div class="modal-body">

                                                                    <div class="form-group">
                                                                        <p class="text-center"><b>ID</b></p>
                                                                        <input type="text" class="form-control"
                                                                               id="idToDelete" name="id" 
                                                                               value="`+user.id+`"
                                                                               readonly>
                                                                    </div>
                                                                    <div class="form-group">
                                                                        <p class="text-center"><b>Username</b></p>
                                                                        <input type="text" class="form-control"
                                                                               name="username" id="username"
                                                                               value="` + user.username + `"
                                                                               readonly>
                                                                    </div>
                                                                    

                                                                    <p class="text-center"><b>Roles</b></p>
                                                                    <div align="center">
                                                                        <input type="checkbox" name="list" id = "userEditCheckbox"
                                                                               value="ROLE_ADMIN" `+(user.admin ? "checked " : "")+`
                                                                               readonly>
                                                                        <label>ADMIN</label>
                                                                        <input type="checkbox" name="list" id = "adminEditCheckbox"
                                                                               value="ROLE_USER"  `+ (user.user ? "checked " : "") +`
                                                                               readonly/>
                                                                        <label>USER</label>
                                                                    </div>


                                                                </div>
                                                                <div class="modal-footer">
                                                                    <button type="button" class="btn btn-secondary"
                                                                            data-dismiss="modal">Close
                                                                    </button>
                                                                    <button type="submit" class="btn btn-danger" id = "btnDeleteSubmit">
                                                                        Delete
                                                                    </button>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                
`)
        tr.appendChild(tdDelete)


        tableBody.appendChild(tr)
    })

    activaTab('all_users');

}

document.querySelector("#btnEditSubmit").onclick = async function () {

    let editedUser = {
        id: document.querySelector("#id").value,
        username: document.querySelector("#username").value,
        password: document.querySelector("#password").value,
        admin: document.querySelector("#adminEditCheckbox").checked,
        user: true
    }

    let createdUser = await sendPostRequest("POST", "admin/v1/users/edit", editedUser)

    await createUsersTable();

}

document.querySelector("#btnDeleteSubmit").onclick = async function () {

    let userId = document.querySelector("#idToDelete").value

    let createdUser = await sendGetRequest("admin/v1/users/delete/"+userId)

    await createUsersTable();

}

document.querySelector("#submitNewUserButton").onclick = async function () {

    let newUser = {
        username: document.querySelector("#newUsername").value,
        password: document.querySelector("#newPassword").value,
        admin: document.querySelector("#newAdminRole").checked,
        user: true
    }


    let createdUser = await sendPostRequest("POST", "admin/v1/users", newUser)


    await createUsersTable();


}

createUsersTable()



