let elementUserTable = document.getElementById('userTableAtAdminPanel');
let elementNewUser = document.getElementById('createNewUserAtAdminPanel');

elementUserTable.onclick = function () {
    document.getElementById('hideTheCreateUserForm').hidden = true;
    document.getElementById('hideTheUsersTable').hidden = false;
};

elementNewUser.onclick = function () {
    document.getElementById('hideTheUsersTable').hidden = true;
    document.getElementById('hideTheCreateUserForm').hidden = false;
};
