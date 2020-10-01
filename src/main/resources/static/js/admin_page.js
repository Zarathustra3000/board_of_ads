let viewAllUsersUrl = 'http://localhost:5556/api/admin/getAllUsers';

let adminUsersTable = $('#userTableJs tbody');

let elementUserTable = document.getElementById('userTableAtAdminPanel');
let elementNewUser = document.getElementById('createNewUserAtAdminPanel');

//Сокрытие информации о создании нового пользователя
elementUserTable.onclick = function () {
    document.getElementById('hideTheCreateUserForm').hidden = true;
    document.getElementById('hideTheUsersTable').hidden = false;
};

//Сокрытие таблицы пользователей
elementNewUser.onclick = function () {
    document.getElementById('hideTheUsersTable').hidden = true;
    document.getElementById('hideTheCreateUserForm').hidden = false;
};

$(document).ready(function () {
    showAllUsersTable();
});

function showAllUsersTable() {
    fetch(viewAllUsersUrl)
        .then((response) => {
            if (!response.ok) {
                throw Error("Error: " + response.status);
            }
            return response.json();
        })
        .then((data) => {
            data.map(user => {

                let buttonDelete = document.createElement('button');
                let buttonEdit = document.createElement('button');
                let tdEdit = document.createElement('td');
                let tdDelete = document.createElement('td');
                let tr = document.createElement('tr');
                let counter = 0;

                tr.setAttribute('id', "userDataTable");

                const userRoles = user.roles.map(role => {
                    return role.name;
                }).join(", ");

                console.log(userRoles);

                for (let o in user) {
                    let td = document.createElement('td');

                    if (counter !== 6 && counter !==9) {
                        td.appendChild(document.createTextNode(user[o]));
                        tr.appendChild(td);
                    }
                    if (counter === 9) {
                        td.appendChild(document.createTextNode(userRoles));
                        tr.appendChild(td);
                        break;
                    }
                    counter++;
                }

                buttonEdit.setAttribute('id', "updateButton");
                buttonEdit.setAttribute('class', "btn btn-info");
                buttonEdit.setAttribute('role', "button");
                buttonEdit.setAttribute('data-toggle', "modal");
                buttonEdit.setAttribute('data-target', "#updateModal");
 //               buttonEdit.setAttribute('onclick', `${userIdForUpdate}`);
                buttonEdit.appendChild(document.createTextNode("Update"));

                buttonDelete.setAttribute('id', "deleteButton");
                buttonDelete.setAttribute('class', "btn btn-danger");
                buttonDelete.setAttribute('role', "button");
                buttonDelete.setAttribute('data-toggle', "modal");
                buttonDelete.setAttribute('data-target', "#deleteModal");
 //               buttonDelete.setAttribute('onclick', `${userIdForDelete}`);
                buttonDelete.appendChild(document.createTextNode("Delete"));

                tdEdit.appendChild(buttonEdit);
                tdDelete.appendChild(buttonDelete);
                tr.appendChild(tdEdit);
                tr.appendChild(tdDelete);
                adminUsersTable.append(tr);
            });
        })
        .catch(error => {
            console.log(error);
        })
}