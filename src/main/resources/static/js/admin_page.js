let viewAllUsersUrl = 'http://localhost:5556/api/admin/getAllUsers';
let getUserById = 'http://localhost:5556/api/admin/getUserById';
let deleteUserById = 'http://localhost:5556/api/admin/deleteUser';

let adminUsersTable = $('#userTableJs tbody');
let deleteButtonInModalForm = $('#deleteButtonInModal div');
let elementCloseDeleteModal1 = document.getElementById('closeDeleteModal');
let elementCloseDeleteModal2 = document.getElementById('closeDeleteModal2');

let elementUserTable = document.getElementById('userTableAtAdminPanel');
let elementNewUser = document.getElementById('createNewUserAtAdminPanel');

$(document).ready(function () {
    showAllUsersTable();
});

//ОСНОВНЫЕ ФУНКЦИИ

//Функция заполняющая таблицу пользователей
function showAllUsersTable() {

    let userIdForDelete = 0;

    fetch(viewAllUsersUrl)
        .then((response) => {
            return response.json();
        })
        .then((data) => {
            console.log(data);
            if (data.success) {
                data.data.map(user => {

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

                    for (let o in user) {
                        let td = document.createElement('td');
                        if (counter === 0) {
                               userIdForDelete = "fillingModalFormDelete" + "(" + user[o] + ")";
                        }
                        if (counter !== 6 && counter !== 9) {
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
                    buttonDelete.setAttribute('onclick', `${userIdForDelete}`);
                    buttonDelete.appendChild(document.createTextNode("Delete"));

                    tdEdit.appendChild(buttonEdit);
                    tdDelete.appendChild(buttonDelete);
                    tr.appendChild(tdEdit);
                    tr.appendChild(tdDelete);
                    adminUsersTable.append(tr);
                });
            }
        })
        .catch(error => {
            console.log(error);
        })
}

/*заполнение модальной формы на удаление*/
function fillingModalFormDelete(id) {

    let deleteButtonInModal = document.createElement('button');
    let userIdForDeleteButton = "deleteData" + "(" + id + ")";

    deleteButtonInModal.setAttribute('type', "button");
    deleteButtonInModal.setAttribute('id', "delButtInModal");
    deleteButtonInModal.setAttribute('class', "btn btn-danger");
    deleteButtonInModal.setAttribute('data-dismiss', "modal");
    deleteButtonInModal.setAttribute('onclick', `${userIdForDeleteButton}`);
    deleteButtonInModal.appendChild(document.createTextNode("Delete"));

    deleteButtonInModalForm.append(deleteButtonInModal);

    fetch(getUserById + "/" + id).then(function (response) {
        response.json().then(function (data) {


            const userRoles = data.data.roles.map(role => {
                return role.name;
            }).join(", ");

            $('#delUserID').val(id);
            $('#delUserName').val(data.data.firstName);
            $('#delUserDataReg').val(data.data.dataRegistration);
            $('#delUserEmail').val(data.data.email);
            $('#delUserRoles').val(userRoles);
        });
    });

}

//Удаление пользователя

async function deleteData(value) {

    await fetch(deleteUserById + "/" + value, {
        method: 'DELETE',
        mode: 'cors',
        cache: 'no-cache',
        credentials: 'same-origin',
        headers: {
            'Content-Type': 'application/json'
        },
        redirect: 'follow',
        referrerPolicy: 'no-referrer',
    });

    document.getElementById('delButtInModal').remove();
    clearTable();
    showAllUsersTable()
}


// ВСПОМОГАТЕЛЬНЫЕ ФУНКЦИИ НА ON.CLICK

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

//Очистка таблиц при закрытии модального окна
function clearTable() {
    while (document.getElementById("userDataTable") != null) {
        document.getElementById("userDataTable").remove();
    }
}

/*необходимы для избежания дублирования кнопок при закрытии модального окна*/
elementCloseDeleteModal1.onclick = function () {
    document.getElementById('delButtInModal').remove();
};

elementCloseDeleteModal2.onclick = function () {
    document.getElementById('delButtInModal').remove();
};